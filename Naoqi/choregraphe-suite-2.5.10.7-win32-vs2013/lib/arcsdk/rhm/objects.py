import datetime

from arcsdk.resource import Resource, Collection


class Aldebaran(Resource):
    pass


class User(Resource):
    pass


class Robot(Resource):

    @classmethod
    def collection_from_json(cls, json, client, api, parent):
        """ Returns a list of Resource objects from json data """
        res = []
        for json_data in json:
            if isinstance(json_data, basestring):
                json_data = {'id': json_data}
            res.append(cls(json_data, client, api, parent))
        return res


class Log(Resource):
    pass


class Probe(Resource):

    @classmethod
    def collection_from_json(cls, json, client, api, parent):
        """ Returns a list of Resource objects from json data """
        res = []
        for json_data in json:
            if isinstance(json_data, basestring):
                json_data = {'name': json_data}
            print json_data
            res.append(cls(json_data, client, api, parent))
        return res


class RobotPart(Resource):
    pass


class Status(Resource):
    pass


class TimeSerie(Resource):
    pass


class Profile(Resource):

    @classmethod
    def collection_from_json(cls, json, client, api, parent):
        """ Returns a list of Resource objects from json data """
        res = []
        for name, json_data in json.iteritems():
            json_data['name'] = name
            res.append(cls(json_data, client, api, parent))
        return res


class ProfileData(Resource):
    pass


class Geoloc(Resource):
    pass


class RobotStatus(Resource):
    pass


class Passivediag(Resource):
    pass


class PassivediagErrors(Resource):
    pass


class RobotCollection(Collection):

    def filter(self, from_date,
               to_date=datetime.datetime.now().strftime("%Y-%m-%d-%H-%M-%S"),
               status='connected',
               httpOptions=None):
        if httpOptions is None:
            httpOptions = {}
        url = '/rhm/api/1/search/robots'
        url = '%s?status:%s+from:%s+to:%s' % (url, status, from_date, to_date)
        return self.from_json(self._client.get(url, **httpOptions), "robots")

    def from_json(self, json, fieldname="fleetstatus"):
        self.fieldname = fieldname
        return super(RobotCollection, self).from_json(json)


class LogCollection(Collection):

    def filter(self, from_date=None,
               to_date=datetime.datetime.now().strftime("%Y-%m-%d-%H-%M-%S"),
               httpOptions=None):
        if httpOptions is None:
            httpOptions = {}
        url = "%s/%s/%s" % (self.url, from_date, to_date)
        return self.from_json(self._client.get(url, **httpOptions))


class TimeSerieCollection(Collection):

    def filter(self, aggregator=None,
               from_date=None,
               to_date=None,
               httpOptions=None):
        if httpOptions is None:
            httpOptions = {}
        if aggregator is None:
            return self.all()
        if from_date is None:
            url = '%s/%s' % (self.url, aggregator)
        elif to_date is None:
            url = '%s/%s/%s' % (self.url, aggregator, from_date)
        else:
            url = '%s/%s/%s/%s' % (self.url, aggregator, from_date, to_date)
        return self.from_json(self._client.get(url, **httpOptions))

    def from_json(self, json):
        self.fieldname = "series"
        return super(TimeSerieCollection, self).from_json(json)


class ProfileCollection(Collection):

    def create(self, name=None, httpOptions=None, **kwargs):
        """ Create a new object in the collection """
        if httpOptions is None:
            httpOptions = {}
        url = "%s/%s" % (self.url, name)
        return self._client.put(url, kwargs, **httpOptions)

    def from_json(self, json):
        self.fieldname = "profiles"
        return super(ProfileCollection, self).from_json(json)


class PassivediagCollection(Collection):
    """
    """
    def from_json(self, json):
        self.fieldname = "groups"
        return super(PassivediagCollection, self).from_json(json)

    def create(self, rid, pdiag, httpOptions=None):
        """ Add a new object in the collection
        """
        if httpOptions is None:
            httpOptions = {}
        #httpOptions.update({"data": pdiag})
        url = "/rhm/api/1/robots/{rid}/passivediags"
        url = url.replace("{rid}", rid)
        return self._client.post(url, pdiag, **httpOptions)
