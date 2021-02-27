# -*- coding: utf-8

import os
import io
import gzip
import json
import requests
import logging
logger = logging.getLogger(__name__)
# Disable requests debug log
logging.getLogger("requests").setLevel(logging.WARNING)

from requests.cookies import extract_cookies_to_jar

from arcsdk.exceptions import AldebaranException
from arcsdk import config
from arcsdk import environments


class RequestManager(object):
    """The RequestManager, as its name indicate, is supposed to manage HTTP
    request.

    It should contains:
    - functions to make HTTP requests

    """

    def __init__(self,
                 cloud_url=None,
                 enable_logs=False,
                 enable_retry_on_500=False,
                 json_response=True):

        if cloud_url is not None:
            self.url = environments.CloudUrlFromUrl(cloud_url)
        else:
            self.url = environments.CloudUrl()

        self.session = requests.Session()
        self.session.headers = {'Content-type': 'application/json' }

        self.enable_logs = enable_logs
        self.enable_retry_on_500 = enable_retry_on_500
        self.num_500_calls = 1

        self.json_response = json_response

    def set_credentials(self, credentials):
        self.session.auth = credentials

    @property
    def credentials(self):
        return self.session.auth

    @credentials.setter
    def credentials(self, credentials):
        self.set_credentials(credentials)

    def _log_request(self, method, url, headers, data, params):
        if params:
            url = "%s?%s" % (url, "&".join(["%s=%s" % (k, v)
                                            for k, v in params.iteritems()]))
        if method in ['PUT', 'POST']:
            logger.debug("> request(%s %s %s)" % (method, url, data))
        else:
            logger.debug("> request(%s %s)" % (method, url))
        full_headers = dict(self.session.headers.items() + headers.items())
        logger.debug("> headers(%r)" % full_headers)
        if data:
            logger.debug("> data(%r)" % data)

    def _log_response(self, resp):
        logger.debug("< response([%s] %s)" % (resp.status_code, resp.text))

    def gzip_data(self, data):
        iobuff = io.BytesIO()
        with gzip.GzipFile(fileobj=iobuff, mode='w') as f:
            f.write(data)
            return iobuff.getvalue()

    def handle_500(self, r, **kwargs):
        if not self.enable_retry_on_500 \
           or r.status_code < 500 or r.status_code >= 600:
            return r

        num_500_calls = getattr(self, 'num_500_calls', 1)

        if num_500_calls < 4:
            logger.debug("Got %s status, retrying" % r.status_code)
            self.num_500_calls += 1

            # Consume content and release the original connection
            # to allow our new request to reuse the same one.
            r.content
            r.close()
            prep = r.request.copy()
            if "cookies" in prep.headers:
                extract_cookies_to_jar(
                    prep.headers.get("cookies", None), r.request, r.raw)
                prep.prepare_cookies(prep._cookies)

            _r = r.connection.send(prep, **kwargs)
            _r.history.append(r)
            _r.request = prep

            return _r

        self.num_500_calls = 1
        return r

    def handle_logs(self, r, **kwargs):
        self._log_response(r)
        return r

    def request(self, method, url, data=None, as_json=True,
                compress_data=False, **kwargs):
        """
        Do an HTTP request, using the required 'method' (GET, POST,
        PUT, DELETE). It can take some 'data' to pass to the request.
        Return a JSON dictionary.
        """

        headers = kwargs.pop('headers', {})
        data = json.dumps(data)
        if compress_data:
            headers['Content-Encoding'] = 'gzip'
            data = self.gzip_data(data)

        if self.enable_logs:
            self._log_request(method, url, headers, data,
                              kwargs.get('params', None))
        url = self.url(url)

        hooks = [self.handle_500]
        if self.enable_logs:
            hooks.append(self.handle_logs)

        resp = self.session.request(method, url, data=data, headers=headers,
                                    hooks={'response': hooks}, **kwargs)
        if self.json_response:
            resp = self.response_as_json_or_error(resp, as_json)
        return resp

    def response_as_json_or_error(self, resp, as_json):
        if resp.status_code != 200 and resp.status_code != 201:
            try:
                err = resp.json()
            except ValueError:
                err = {}
            raise AldebaranException(
                resp.status_code,
                err.get('message', ''),
                err.get('data', ''),
            )
        if as_json:
            return resp.json()
        return resp.content.decode('utf-8', errors='replace')

    def get(self, url, **kwargs):
        return self.request('GET', url, **kwargs)

    def options(self, url, **kwargs):
        kwargs.setdefault('allow_redirects', True)
        return self.request('options', url, **kwargs)

    def head(self, url, **kwargs):
        kwargs.setdefault('allow_redirects', False)
        return self.request('head', url, **kwargs)

    def post(self, url, data=None, **kwargs):
        return self.request('POST', url, data=data, **kwargs)

    def put(self, url, data=None, **kwargs):
        return self.request('PUT', url, data=data, **kwargs)

    def patch(self, url, data=None, **kwargs):
        return self.request('patch', url, data=data, **kwargs)

    def delete(self, url, **kwargs):
        return self.request('DELETE', url, **kwargs)

    def download(self, url, dest, callback=None,
                 chunkSize=1024 * 1024, **kwargs):
        resp = self.session.request('GET', url, stream=True, allow_redirects=False, **kwargs)
        if resp.status_code == 302:
            if "X-Drop-Headers" in resp.headers:
                resp.close()
                resp = requests.request('GET', resp.headers.get("Location"), stream=True, allow_redirects=False, **kwargs)
            else:
                resp.close()
                resp = self.session.request('GET', resp.headers.get("Location"), stream=True, allow_redirects=False, **kwargs)

        if resp.status_code != 200 and resp.status_code != 201:
            as_json = json.loads(resp.content)
            raise AldebaranException(
                resp.status_code,
                as_json.get('message', ''),
                as_json.get('data', ''),
            )
        total_size = int(resp.headers.get("Content-Length", 1))
        size = 0
        handle = os.open(dest,
                         os.O_WRONLY | os.O_CREAT | os.O_TRUNC | os.O_SYNC)
        for block in resp.iter_content(chunkSize):
            if not block:
                break
            nb_written = os.write(handle, block)
            size += nb_written
            if callback is not None:
                try:
                    if callback(size, total_size) is False:
                        # if callback return False, abort download
                        break
                except Exception, e:
                    logger.error(e)
        os.close(handle)
        resp.close()
