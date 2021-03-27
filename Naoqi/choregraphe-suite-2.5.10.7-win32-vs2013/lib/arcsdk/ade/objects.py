from arcsdk.resource import Resource, Collection


class Administrable(Resource):

    def to_json(self):
        # Workaround: server expect admins as a comma separated
        # list of username
        admins = getattr(self, 'admins', [])
        if len(admins):
            admins = ",".join([a.username for a in admins])
        else:
            admins = ","  # hack, in case admin list is empty
        viewers = getattr(self, 'viewers', [])
        if len(viewers):
            viewers = ",".join([v.username for v in viewers])
        else:
            viewers = ','
        args = {
            'name': getattr(self, 'name', 'no-name-available'),
            'admins': admins,
            'viewers': viewers,
        }
        owner = getattr(self, 'owner', None)
        if owner is not None:
            args['owner'] = owner.username
        else:
            args['owner'] = 'null'
        registered = getattr(self, 'registered', None)
        if registered is not None:
            args['registered'] = registered.username
        else:
            args['registered'] = 'null'  # Master hack -_-'
        robot_type = getattr(self, 'robot_type', None)
        if robot_type is not None:
            args['robot_type'] = getattr(robot_type, 'id', None)
        return dict(super(Administrable, self).to_json().items()
                    + args.items())


class Category(Resource):
    pass


class Language(Resource):
    pass


class AppliVersionProperty(Resource):

    def __init__(self, json, client, api,
                 parent=None):
        super(AppliVersionProperty, self).__init__(json, client, api, parent)
        if isinstance(parent, AppliVersion):
            self.version = parent


class AppliVersion(Resource):

    def __init__(self, json, client, api,
                 parent=None):
        if 'idappli' in json:
            # If parent is not an Appli (when we use
            # property.versions.all() for instance), then make it an
            # Appli)
            if not isinstance(parent, Appli):
                parent = Appli({'id': json['idappli']}, client, api, None)
        super(AppliVersion, self).__init__(json, client, api, parent)
        if isinstance(parent, Appli):
            self.appli = parent
        self._manifest = None

    @property
    def is_beta(self):
        is_published = getattr(self, 'is_published', False)
        return is_published == False

    @property
    def manifest(self):
        if self._manifest is None:
            url = '%s/manifest.xml' % self.url
            self._manifest = self.client.request('GET', url, None, False)
        return self._manifest

    def download_package(self, destination):
        url = getattr(self, 'package_url', 'no-url-available')
        return self.client.download(url, destination)

    @classmethod
    def collection_from_json(cls, json, client, api, parent):
        """ Returns a list of Resource objects from json data """
        res = []
        for json_data in json:
            if json_data['packageSize'] == 0:
                continue
            res.append(cls(json_data, client, api, parent))
        return res


class Appli(Resource):
    pass


class User(Resource):

    def __repr__(self):
        if hasattr(self, 'id'):
            uid = getattr(self, 'id', 'no-id-available')
            email = getattr(self, 'email', 'no-email-available')
            return "%s(%s, %s)" % (self.__class__.__name__,
                                   uid, email)
        return "%s()" % self.__class__.__name__

class UserType(Resource):
    """ Categorie of user
    """
    pass

class Robot(Administrable):

    def __init__(self, json, client, api,
                 parent=None):
        super(Robot, self).__init__(json, client, api, parent)
        self._keys = ['name', 'admins', 'registered', 'robot_type']


class Aldebaran(Resource):
    """ Main class to access the Aldebaran Robotics Cloud API. """
    pass


class Group(Administrable):
    pass


class Profile(Administrable):
    pass


class Install(Resource):
    pass


class System(Resource):
    pass


class ProfileSystem(Resource):
    pass


class PreferenceDomain(Resource):
    def __init__(self, json, client, api,
                 parent=None):
        # When domain are created, 'domain' attribute is called 'name' :|
        if 'name' in json and 'domain' not in json:
            json['domain'] = json['name']
            del json['name']
        super(PreferenceDomain, self).__init__(json, client, api, parent)


class RobotPreferenceKV(Resource):
    pass


class RobotPreferenceDomain(PreferenceDomain):
    pass


class AppliPreferenceKV(Resource):
    pass


class AppliPreferenceDomain(PreferenceDomain):
    pass


class UserPreferenceKV(Resource):
    pass


class UserPreferenceDomain(PreferenceDomain):
    pass


class GroupPreferenceKV(Resource):
    pass


class GroupPreferenceDomain(PreferenceDomain):
    pass


class ProfilePreferenceKV(Resource):
    pass


class ProfilePreferenceDomain(PreferenceDomain):
    pass


class RobotModel(Resource):
    pass


class Alert(Resource):
    pass


class Description(Resource):
    pass


class Visibility(Resource):
    pass


class RobotType(Resource):
    pass


class XMPPSession(Resource):
    def __init__(self, json, client, api,
                 parent=None):
        super(XMPPSession, self).__init__(json.get('ids', {}),
                                          client, api, parent)


class ADEAuth(Resource):
    pass


class RobotUpdate(Resource):
    pass


class RobotPackage(Resource):

    def download(self, dest, callback=None, chunkSize=1024, httpOptions=None):
        if httpOptions is None:
            httpOptions = {}
        self.client.download(self.url, dest, callback,
                             chunkSize, **httpOptions)

class ChannelCollection(Collection):
    """ A set of application installable on a robot
    """
    def add(self, resource, httpOptions=None):
        if httpOptions is None:
            httpOptions = {}
        data = {
            "channel": resource.id
        }
        return self._client.post(self.url, data=data, **httpOptions)


class AppliVersionsCollection(Collection):

    def all(self, httpOptions=None):
        if httpOptions is None:
            httpOptions = {}
        as_json = self._client.get(self.url, **httpOptions)
        res = []
        for package in as_json['packages']:
            # Ignore package 'system-image', as this is not really an
            # app, and it should appear nowhere.
            if package['uuid'] == 'system-image':
                continue
            app = Appli({
                "slug": package.get('uuid', ""),
                "id": package.get('uuid', ""),
                "title": package.get('name', ""),
            }, self._client, self._global_api, self.parent)
            version = AppliVersion({
                "id": package.get('version', ""),
                "version": package.get('version', ""),
                "packageSize": package.get('size', ""),
                "package_url": package.get('url', ""),
            }, self._client, self._global_api, app)
            res.append(version)
        return res


class InstallCollection(Collection):

    def add(self, resource, filter='release', httpOptions=None):
        if httpOptions is None:
            httpOptions = {}
        data = {
            "application": resource.id,
            "filter": filter,
        }
        return self._client.post(self.url, data=data, **httpOptions)
