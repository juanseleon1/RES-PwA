import datetime
import json
import requests
from requests.cookies import extract_cookies_to_jar
import copy
import logging
logger = logging.getLogger(__name__)

from arcsdk.exceptions import AldebaranException
from arcsdk import environments

OAUTH_AUTH_URI = "https://sso.aldebaran-robotics.com/as/authorization.oauth2"
OAUTH_TOKEN_URI = 'https://sso.aldebaran-robotics.com/as/token.oauth2'

# Expiry is stored in RFC3339 UTC format
EXPIRY_FORMAT = '%Y-%m-%dT%H:%M:%SZ'


def expiry_to_date(expiry):
    if not isinstance(expiry, datetime.datetime):
        try:
            return datetime.datetime.strptime(expiry, EXPIRY_FORMAT)
        except ValueError:
            return None


class Credentials(requests.auth.AuthBase):
    """ Base class for all Credentials objects.
    """

    NON_SERIALIZED_MEMBERS = ['store']

    def authorize(self, session):
        session.auth = self

    def unauthorize(self, session):
        session.auth = None

    def is_valid(self):
        return False

    def _to_json(self, strip):
        """Utility function that creates JSON repr. of a Credentials object.
        :param strip: array, An array of names of members to not
        include in the JSON.
        :returns: string, a JSON representation of this instance,
        suitable to pass to from_json().

        """
        t = type(self)
        d = copy.copy(self.__dict__)
        for member in strip:
            if member in d:
                del d[member]
        if (d.get('token_expiry') and
            isinstance(d['token_expiry'], datetime.datetime)):
            d['token_expiry'] = d['token_expiry'].strftime(EXPIRY_FORMAT)
        # Add in information we will need later to reconsistitue this instance.
        d['_class'] = t.__name__
        d['_module'] = t.__module__
        for key, val in d.items():
            if isinstance(val, bytes):
                d[key] = val.decode('utf-8')
        return json.dumps(d)

    def to_json(self):
        """Creating a JSON representation of an instance of Credentials."""
        return self._to_json(Credentials.NON_SERIALIZED_MEMBERS)

    @classmethod
    def new_from_json(cls, s):
        """Utility class method to instantiate a Credentials subclass from a
        JSON representation produced by to_json().
        :param s: string, JSON from to_json().
        :returns: An instance of the subclass of Credentials that was
          serialized with to_json().

        """
        import six
        if six.PY3 and isinstance(s, bytes):
            s = s.decode('utf-8')
        data = json.loads(s)
        # Find and call the right classmethod from_json() to restore
        # the object.
        module = data['_module']
        try:
            m = __import__(module)
        except ImportError:
            # In case there's an object from the old package
            # structure, update it
            module = module.replace('.googleapiclient', '')
            m = __import__(module)

        m = __import__(module, fromlist=module.split('.')[:-1])
        kls = getattr(m, data['_class'])
        from_json = getattr(kls, 'from_json')
        return from_json(s)

    @classmethod
    def from_json(cls, unused_data):
        """Instantiate a Credentials object from a JSON description of it.
        The JSON should have been produced by calling .to_json() on the object.
        :param unused_data: dict, A deserialized JSON object.
        :returns: An instance of a Credentials subclass.
        """
        return Credentials()


class Storage(object):
    """ Base class for all Storage objects.
    """

    def acquire_lock(self):
        pass

    def release_lock(self):
        pass

    def locked_get(self):
        raise NotImplementedError("You need to override this method")

    def locked_put(self, credentials):
        raise NotImplementedError("You need to override this method")

    def locked_delete(self):
        raise NotImplementedError("You need to override this method")

    def get(self):
        self.acquire_lock()
        try:
            return self.locked_get()
        finally:
            self.release_lock()

    def put(self, credentials):
        self.acquire_lock()
        try:
            return self.locked_put(credentials)
        finally:
            self.release_lock()

    def delete(self):
        self.acquire_lock()
        try:
            return self.locked_delete()
        finally:
            self.release_lock()


class LoginPasswordCredentials(Credentials):
    """ Handle authentication via login and password"""

    def __init__(self, login, password):
        super(LoginPasswordCredentials, self).__init__()
        self.login = login
        self.password = password

    # Python Requests compatible API. Overloaded __call__ from
    # requests.auth.AuthBase
    def __call__(self, r):
        r.headers['login'] = self.login
        r.headers['token'] = self.password
        return r

    def is_valid(self):
        return True

    def _set_headers(self, session, headers):
        session.headers.update(headers)

    def _clear_header(self, session, key):
        if key in session.headers:
            del session.headers[key]


REDIRECT_STATI = (
    301,  # moved
    302,  # found
    303,  # other
    307,  # temporary redirect
    308,  # permanent redirect
)


class OAuth2Credentials(Credentials):

    def __init__(self, access_token=None,
                 client_id=None, client_secret=None,
                 refresh_token=None,
                 token_uri=OAUTH_TOKEN_URI,
                 access_token_expiry=None):

        super(OAuth2Credentials, self).__init__()
        self.access_token = access_token
        self.client_id = client_id
        self.client_secret = client_secret
        self.refresh_token = refresh_token
        self.token_uri = token_uri
        self.token_expiry = access_token_expiry
        self.store = None
        self._init_handlers()

    def _init_handlers(self):
        self.pos = None
        self.num_401_calls = 1

    def handle_redirect(self, r, **kwargs):
        """Reset num_401_calls counter on redirects."""
        if 'location' in r.headers and r.status_code in REDIRECT_STATI:
            self.num_401_calls = 1

    def handle_401(self, r, **kwargs):
        """Takes the given response and tries using fresh OAuth tokens,
        if needed."""

        if r.status_code != 401:
            return r

        if self.pos is not None:
            # Rewind the file position indicator of the body to where
            # it was to resend the request.
            r.request.body.seek(self.pos)

        num_401_calls = getattr(self, 'num_401_calls', 1)

        if num_401_calls < 2:
            self.num_401_calls += 1
            # Consume content and release the original connection
            # to allow our new request to reuse the same one.
            r.content
            r.close()
            prep = r.request.copy()
            if "cookies" in prep.headers:
                extract_cookies_to_jar(
                    prep.headers.get("cookies", None), r.request, r.raw)
                prep.prepare_cookies(prep._cookies)

            self.refresh()
            prep.headers['Authorization'] = self.access_token
            _r = r.connection.send(prep, **kwargs)
            _r.history.append(r)
            _r.request = prep

            return _r

        self.num_401_calls = 1
        return r

    # Python Requests compatible API. Overloaded __call__ from
    # requests.auth.AuthBase
    def __call__(self, r):
        if not self.access_token or self.access_token_expired:
            self.refresh()
        r.headers['Authorization'] = 'Bearer %s' % self.access_token
        try:
            self.pos = r.body.tell()
        except AttributeError:
            # In the case of HTTPDigestAuth being reused and the body of
            # the previous request was a file-like object, pos has the
            # file position of the previous body. Ensure it's set to
            # None.
            self.pos = None
        r.register_hook('response', self.handle_401)
        r.register_hook('response', self.handle_redirect)
        return r

    def is_valid(self):
        return self.access_token != "" and self.access_token is not None

    def refresh(self):
        """Return updated access information for an OAuth2 authorization grant.
        """
        if not self.store:
            self._refresh_request()
        else:
            self.store.acquire_lock()
            try:
                new_cred = self.store.locked_get()
                if (new_cred and new_cred.is_valid() and
                    new_cred.access_token != self.access_token):
                    logger.info('Updated access_token read from Storage')
                    del new_cred.__dict__['store']
                    self.__dict__.update(new_cred.__dict__)
                else:
                    self._refresh_request()
            finally:
                self.store.release_lock()

    def _refresh_request(self):
        now = datetime.datetime.utcnow()
        data = {
            'client_id': self.client_id,
            'client_secret': self.client_secret,
            'grant_type': 'refresh_token',
            'refresh_token': self.refresh_token,
            'redirect_uri': "http://example.com",
        }
        resp = requests.request('POST',
                                self.token_uri if type(self.token_uri) == str else self.token_uri(),
                                data=data, verify=True)
        if resp.status_code != 200 and resp.status_code != 201:
            raise AldebaranException(resp.status_code, resp.text, '')
        resp = json.loads(resp.text)
        if 'access_token' not in resp:
            logger.error("No access token returned by server")
        elif 'expires_in' not in resp:
            logger.error("No expiration date returned by server")
        else:
            self.access_token = resp['access_token']
            self.token_expiry = now + datetime.timedelta(0, resp['expires_in'])
            if self.store:
                self.store.locked_put(self)

    def get_access_token(self):
        if self.access_token is None:
            self.refresh()
        return self.access_token

    def set_store(self, store):
        """Set the Storage for the credential.
        :param store: Storage, an implementation of Storage object.
        This is needed to store the latest access_token if it
        has expired and been refreshed. This implementation uses
        locking to check for updates before updating the
        access_token.
        """
        self.store = store

    @classmethod
    def from_json(cls, s):
        """Instantiate a Credentials object from a JSON description of it. The
        JSON should have been produced by calling .to_json() on the
        object.
        :param data: dict, A deserialized JSON object.
        :returns: An instance of a Credentials subclass.
        """
        import six
        if six.PY3 and isinstance(s, bytes):
            s = s.decode('utf-8')
        data = json.loads(s)
        if data.get('token_expiry'):
            data['token_expiry'] = expiry_to_date(data['token_expiry'])
        retval = cls(
            access_token=data['access_token'],
            client_id=data['client_id'],
            client_secret=data['client_secret'],
            refresh_token=data['refresh_token'],
            access_token_expiry=data['token_expiry'],
#            token_uri=data['token_uri'],
        )
        return retval

    @property
    def access_token_expired(self):
        if not self.is_valid():
            return True
        if self.token_expiry is None:
            return False
        now = datetime.datetime.utcnow()
        return self.token_expiry <= now


class OAuth2WebServerFlow(object):

    def __init__(self, client_id=None, client_secret=None,
                 redirect_uri=None,
                 auth_uri=OAUTH_AUTH_URI,
                 token_uri=OAUTH_TOKEN_URI):

        self.client_id = client_id
        self.client_secret = client_secret
        self.redirect_uri = redirect_uri
        self.auth_uri = auth_uri
        self.token_uri = token_uri

    def step1_get_authorize_url(self):
        """Return the URL to send the user to for OAuth2 authorization.
        """
        params = {
            'client_id': self.client_id,
            'response_type': 'code',
            'grant_type': 'refresh_token',
            'redirect_uri': self.redirect_uri,
        }
        request = requests.Request('POST', self.auth_uri, params=params)
        return request.prepare().url

    def step2_exchange(self, code):
        """Return the access information for an OAuth2 authorization grant.
        """
        now = datetime.datetime.utcnow()
        data = {
            'client_id': self.client_id,
            'client_secret': self.client_secret,
            'grant_type': 'authorization_code',
            'code': code,
            'redirect_uri': self.redirect_uri,
        }
        resp = requests.request('POST', self.token_uri, data=data, verify=True)
        if resp.status_code != 200 and resp.status_code != 201:
            raise AldebaranException(resp.status_code, resp.text, '')
        resp = json.loads(resp.text)
        if 'access_token' not in resp:
            logger.error("No access token returned by server")
        elif 'expires_in' not in resp:
            logger.error("No expiration date returned by server")
        elif 'refresh_token' not in resp:
            logger.error("No refresh token returned by server")
        else:
            return OAuth2Credentials(
                resp['access_token'],
                self.client_id,
                self.client_secret,
                resp['refresh_token'],
                self.token_uri,
                now + datetime.timedelta(0, resp['expires_in']),
            )
        return None


class UserOAuth2Credentials(OAuth2Credentials):

    def __init__(self, access_token=None, client_id=None,
                 cert=None, key=None, login=None, password=None,
                 token_uri=None, access_token_expiry=None):

        if token_uri is None:
            token_uri = environments.auth_url

        super(UserOAuth2Credentials, self).__init__(
            access_token,
            client_id,
            None,
            None,
            token_uri,
            access_token_expiry,
        )
        self.cert = cert
        self.key = key
        self.login = login
        self.password = password

    def _refresh_request(self):
        now = datetime.datetime.utcnow()
        data = {
            "client_id": self.client_id,
            "grant_type": 'password',
            "username": self.login,
            "password": self.password
        }
        resp = requests.request('POST',
                                self.token_uri if type(self.token_uri) == str else self.token_uri(),
                                data=data,
                                cert=(self.cert, self.key), verify=True)
        if resp.status_code != 200 and resp.status_code != 201:
            raise AldebaranException(resp.status_code, resp.text, '')
        resp = json.loads(resp.text)
        if 'access_token' not in resp:
            logger.error("No access token returned by server")
        elif 'expires_in' not in resp:
            logger.error("No expiration date returned by server")
        else:
            self.access_token = resp['access_token']
            self.token_expiry = now + datetime.timedelta(0, resp['expires_in'])
            if self.store:
                self.store.locked_put(self)

    @classmethod
    def from_json(cls, s):
        """Instantiate a Credentials object from a JSON description of it. The
        JSON should have been produced by calling .to_json() on the
        object.
        :param data: dict, A deserialized JSON object.
        :returns: An instance of a Credentials subclass.
        """
        import six
        if six.PY3 and isinstance(s, bytes):
            s = s.decode('utf-8')
        data = json.loads(s)
        if data.get('token_expiry'):
            data['token_expiry'] = expiry_to_date(data['token_expiry'])
        retval = cls(
            access_token=data['access_token'],
            client_id=data['client_id'],
            cert=data['cert'],
            key=data['key'],
            login=data['login'],
            password=data['password'],
            access_token_expiry=data['token_expiry'],
#            token_uri=data['token_uri'],
        )
        return retval


class RobotOAuth2Credentials(UserOAuth2Credentials):

    def __init__(self):
        import qi
        mod = qi.module("tokenmanager")
        self._cli = mod.createObject("OAuth")
        self.access_token = self._cli.getAccessToken()
        self._init_handlers()

    def _set_headid_header(self, session):
        from robot import get_qi_session, get_headid
        try:
            qiSession = get_qi_session()
            if qiSession is not None:
                headid = get_headid(qiSession)
                if headid is not None:
                    session.headers['X-Aldebaran-HeadId'] = headid
                else:
                    logger.warning("Could not retrieve HeadId, "
                                   "X-Aldebaran-HeadId header  won't be set.")
            else:
                logger.warning("Could not retrieve Naoqi session, "
                               "X-Aldebaran-HeadId header won't be set.")
        except:
            logger.warning("Could not retrieve HeadId, X-Aldebaran-HeadId "
                           "header won't be set.")

    def _set_buildid_header(self, session):
        from robot import get_buildid
        try:
            buildid = get_buildid()
            if buildid is not None:
                session.headers['X-Aldebaran-BuildId'] = buildid
            else:
                logger.warning("Could not retrieve BuildId, "
                               "X-Aldebaran-BuildId header won't be set.")
        except:
            logger.warning("Could not retrieve BuildId, X-Aldebaran-BuildId "
                           "header won't be set.")

    def authorize(self, session):
        self._set_headid_header(session)
        self._set_buildid_header(session)
        super(RobotOAuth2Credentials, self).authorize(session)

    def refresh(self):
        self.access_token = self._cli.refreshAccessToken()

    @property
    def access_token_expired(self):
        return False


class RobotOwnerOAuth2Credentials(UserOAuth2Credentials):

    def __init__(self, access_token=None, client_id=None,
                 cert=None, key=None, login=None, password=None,
                 token_uri=None, access_token_expiry=None):

        if token_uri is None:
            token_uri = environments.auth_url
        super(RobotOwnerOAuth2Credentials, self).__init__(
            access_token,
            client_id,
            cert,
            key,
            login,
            password,
            token_uri,
            access_token_expiry,
        )


class AppOAuth2Credentials(OAuth2Credentials):

    def __init__(self, access_token=None,
                 client_id=None, cert=None, key=None,
                 token_uri=None, access_token_expiry=None):

        if token_uri is None:
            token_uri = environments.auth_url
        super(AppOAuth2Credentials, self).__init__(
            access_token,
            client_id,
            None,
            None,
            token_uri,
            access_token_expiry,
        )
        self.cert = cert
        self.key = key

    def _refresh_request(self):
        now = datetime.datetime.utcnow()
        data = {
            "client_id": self.client_id,
            "grant_type": 'client_credentials',
        }
        resp = requests.request('POST',
                                self.token_uri if type(self.token_uri) == str else self.token_uri(),
                                data=data,
                                cert=(self.cert, self.key), verify=True)
        if resp.status_code != 200 and resp.status_code != 201:
            raise AldebaranException(resp.status_code, resp.text, '')
        resp = json.loads(resp.text)
        if 'access_token' not in resp:
            logger.error("No access token returned by server")
        elif 'expires_in' not in resp:
            logger.error("No expiration date returned by server")
        else:
            self.access_token = resp['access_token']
            self.token_expiry = now + datetime.timedelta(0, resp['expires_in'])
            if self.store:
                self.store.locked_put(self)

    @classmethod
    def from_json(cls, s):
        """Instantiate a Credentials object from a JSON description of it. The
        JSON should have been produced by calling .to_json() on the
        object.
        :param data: dict, A deserialized JSON object.
        :returns: An instance of a Credentials subclass.
        """
        import six
        if six.PY3 and isinstance(s, bytes):
            s = s.decode('utf-8')
        data = json.loads(s)
        if data.get('token_expiry'):
            data['token_expiry'] = expiry_to_date(data['token_expiry'])
        retval = cls(
            access_token=data['access_token'],
            client_id=data['client_id'],
            cert=data['cert'],
            key=data['key'],
            access_token_expiry=data['token_expiry'],
#            token_uri=data['token_uri'],
        )
        return retval
