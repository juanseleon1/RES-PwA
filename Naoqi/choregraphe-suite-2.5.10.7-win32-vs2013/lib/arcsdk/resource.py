import re

from arcsdk.helpers import safeint


def format_url(url, obj):
    args = re.findall(':([^/]+)', url)
    for arg in args:
        larg = arg.split('__')
        val = obj
        while len(larg) > 1:
            val = getattr(val, larg[0], 'no-value')
            larg = larg[1:]
        val = getattr(val, larg[0], 'no-value')
        url = url.replace(':%s' % arg, str(val))
    return url


def get_or_create_subclass(inst, name=None):
    cls = type(inst)
    if not hasattr(cls, '__perinstance'):
        if name is None:
            name = cls.__name__
        cls = type(name, (cls,), {})
        cls.__perinstance = True
        inst.__class__ = cls
    return cls


def addprop(inst, name, variable, doc):
    cls = get_or_create_subclass(inst)
    setattr(cls, name, property(lambda x: variable, None, None, doc))


class Resource(object):

    def __init__(self, json, client, api,
                 parent=None):

        self._client = client
        self.parent = parent
        self._keys = json.keys()
        self._api = api[self.__class__.__name__]
        self._global_api = api

        self._init_fields(json)
        self._init_collections(json)
        self._init_methods()

    def _init_fields(self, json):
        if 'id' in json:
            # Sometimes id is a string o_O
            json['id'] = safeint(json['id'])

        # Populate simple fields from json
        for key, val in json.items():
            key = key.replace('-', '_')
            setattr(self, key, val)

        # For some fields we need to create Resources.
        fields = self._api.get('fields', {})
        for key, resource_cls in fields.iteritems():
            if key in json:
                attr_json = json.get(key, None)
                resource = None
                if attr_json is not None:
                    # Only instanciate resource if value is not None
                    resource = resource_cls(attr_json, self.client,
                                            self._global_api, self)
            else:
                # If value is not present, try to instanciate an empty resource
                try:
                    resource = resource_cls({}, self.client,
                                            self._global_api, self)
                except Exception:
                    resource = None
            setattr(self, key, resource)

        url = self._api.get('url', None)
        if url is not None:
            self.url = format_url(url, self)

    def _init_collections(self, json):
        # Populate collections
        for key, desc in self._api.get('collections', {}).iteritems():
            collection_cls = desc.get('class', Collection)
            collection_json = json.get(key, None)
            collection = collection_cls(collection_json, self.client,
                                        key, desc, self._global_api, self)
            setattr(self, key, collection)

    def _init_methods(self):
        get_or_create_subclass(self)

        def get(self, httpOptions=None):
            if httpOptions is None:
                httpOptions = {}
            self.__init__(
                self._client.get(self.url, **httpOptions),
                self._client, self._global_api, self.parent)
            return self

        def refresh(self, httpOptions=None):
            return get(self, httpOptions)

        def save(self, httpOptions=None):
            if httpOptions is None:
                httpOptions = {}
            return self._client.put(self.url, self.to_json(), **httpOptions)

        def delete(self, httpOptions=None):
            if httpOptions is None:
                httpOptions = {}
            return self._client.delete(self.url, **httpOptions)

        methods = self._api.get('methods', [])
        if 'GET' in methods:
            self.__class__.refresh = refresh
            self.__class__.get = get
        if 'PUT' in methods:
            self.__class__.save = save
        if 'DELETE' in methods:
            self.__class__.delete = delete

    def __repr__(self):
        if hasattr(self, 'id'):
            return "%s(%s)" % (self.__class__.__name__,
                               getattr(self, "id", "no-id-available"))
        return "%s()" % self.__class__.__name__

    def to_json(self):
        """ Serialize this object as Json dict. Will be used in save()
        function to send this object to server. """
        return dict((k, self.__dict__[k])
                    for k in self._keys if k in self.__dict__)

    @classmethod
    def collection_from_json(cls, json, client, api, parent):
        """ Returns a list of Resource objects from json data """
        res = []
        for json_data in json:
            res.append(cls(json_data, client, api, parent))
        return res

    @property
    def client(self):
        """ HTTP client used to make HTTP requests. """
        return self._client


class Collection(object):
    """Base class representing a Collection of Resources.

    The methods this Collection will have depends on the HTTP
    methods allowed on the Collection url.
    Collection will have function:
    - all() if GET /collection_url is allowed
    - create() if POST /collection_url is allowed
    - get(resource_id) if GET /collection_url/resource_id is allowed
    - add(resource_obj) if POST /collection_url/resource_id is allowed
    - remove(resource_obj) if DELETE /collection_url/resource_id is allowed

    Sometimes Collection do not have urls of their own, but are
    embedded in their parent Resource.
    """

    def __init__(self, json, client, fieldname,
                 collection_api, api, parent):
        """Initialize a collection.

        :param json: The JSON value of this Collection, in case it was
          embedded Parent Resource
        :param client: The HTTP client instance used to make requests.
        :param fieldname: The name of this collection field in the
          parent Resource.
        :param collection_api: The api description dictionary for this
          Collection.
        :param api: The global api description dictionary.
        :param parent: The parent Resource.
        """

        self._client = client
        self.resource_cls = collection_api.get('resource_class', None)
        self.parent = parent
        self.url = format_url(collection_api.get('url', ''), parent)
        self.resource_url = collection_api.get('resource_url', '%s/%s'
                                               % (self.url, '%s'))
        self.fieldname = fieldname
        self._global_api = api
        self._embedded_data = None
        resource_methods = collection_api.get('resource_methods', [])
        collection_methods = collection_api.get('methods', [])
        self._init_methods(resource_methods, collection_methods)

        # Some of the collections have their values embedded in
        # the resource returned by server
        if self.resource_cls is not None and json is not None:
            value = self.resource_cls.collection_from_json(json, client,
                                                           api, parent)
            self._set_embedded_data(value)

    def _init_methods(self, resource_methods, collection_methods):
        """Initialize the methods of this Collection.

        The methods the Collection will have depends on the HTTP
        methods allowed on the Collection url.
        Collection will have function:
        - all() if GET /collection_url is allowed
        - create() if POST /collection_url is allowed
        - get(resource_id) if GET /collection_url/resource_id is allowed
        - add(resource_obj) if POST /collection_url/resource_id is allowed
        - remove(resource_obj) if DELETE /collection_url/resource_id is allowed
        """
        if self.resource_cls is not None:
            get_or_create_subclass(self, "%sCollection"
                                   % self.resource_cls.__name__)
        else:
            get_or_create_subclass(self, "NoResourceCollection")

        # Operating on the collection url:

        def all(self, httpOptions=None):
            """ Return a list of all the objects in the collection. """
            if httpOptions is None:
                httpOptions = {}
            return self.from_json(self._client.get(self.url, **httpOptions))

        def create(self, httpOptions=None, **kwargs):
            """ Create a new object in the collection """
            if httpOptions is None:
                httpOptions = {}
            if self.resource_cls is None:
                return self._client.post(self.url, kwargs, **httpOptions)
            json = self._client.post(self.url, kwargs, **httpOptions)
            # ADE/CORE/FLEET returns status messages
            if 'status' in json and json['status'] == 201:
                del json['status']
                del json['message']
                del json['data']
                return self.resource_cls(json, self._client,
                                         self._global_api,
                                         parent=self.parent)
            else:
                # PREFS directly return object
                return self.resource_cls(json, self._client,
                                         self._global_api,
                                         parent=self.parent)
            return json

        # Operating of the resource in the collection url:

        def get(self, resource_id, httpOptions=None):
            """ Retrieve an object from the collection.

            :param resource_id: the id of the object to retrieve.
            """
            if httpOptions is None:
                httpOptions = {}
            url = self.resource_url % resource_id
            return self.resource_cls(self._client.get(url, **httpOptions),
                                     self._client, self._global_api,
                                     parent=self.parent)

        def add(self, resource, httpOptions=None):
            if httpOptions is None:
                httpOptions = {}
            url = self.resource_url % resource.id
            return self._client.post(url, **httpOptions)

        def remove(self, resource, httpOptions=None):
            if httpOptions is None:
                httpOptions = {}
            url = self.resource_url % resource.id
            return self._client.delete(url, **httpOptions)

        if 'GET' in collection_methods:
            self.__class__.all = all
        if 'POST' in collection_methods:
            self.__class__.create = create
        if 'GET' in resource_methods:
            self.__class__.get = get
        if 'POST' in resource_methods:
            self.__class__.add = add
        if 'DELETE' in resource_methods:
            self.__class__.remove = remove

    def _set_embedded_data(self, collection):
        """Set the content of this Collection. Used when Collection is an
        Embedded Collection, ie a collection whose content was
        returned by server as the same time as the parent Resource.

        Embedded Collection do not have their own url to retrieve and
        modify them. We need to use the url of parent Resource.

        To keep a coherent API, Embedded Collections provide the same
        functions as regular Collections: all, add, remove. Embedded
        Collection are also iterable as regular lists.
        """
        self._embedded_data = collection

        def all(self):
            return self._embedded_data

        def add(self, resource):
            if not len([r for r in self._embedded_data
                         if r.id == resource.id ]):
                self._embedded_data.append(resource)
            if self.parent is not None:
                self.parent.save()

        def remove(self, resource):
            self._embedded_data = [r for r in self._embedded_data
                                   if r.id != resource.id ]
            if self.parent is not None:
                self.parent.save()

        def __iter__(self):
            return iter(self._embedded_data)

        def __len__(self):
            return len(self._embedded_data)

        # If collection has not 'all' function (no GET), add one that
        # returns embedded data list
        if not hasattr(self.__class__, 'all'):
            self.__class__.all = all
        # If collection has not 'add' function (no POST), add one that
        # append to embedded data list
        if not hasattr(self.__class__, 'add'):
            self.__class__.add = add
        # If collection has not 'add' function (no POST), add one that
        # append to embedded data list
        if not hasattr(self.__class__, 'remove'):
            self.__class__.remove = remove

        # Make this collection iterable
        self.__class__.__iter__ = __iter__
        self.__class__.__len__ = __len__

    def _repr_embedded_collection(self):
        """Representation of an Embedded Collection, ie a collection whose
        content was returned by server as the same time as the parent
        Resource
        """
        return "%s(%s: %s)" % (
            self.__class__.__name__,
            self.resource_cls.__name__,
            self._embedded_data,
        )

    def _repr_collection(self):
        """ Representation of a regular Collection"""
        return "%s(%s)" % (self.__class__.__name__,
                           self.resource_cls.__name__)

    def _repr_empty_collection(self):
        """Representation of an empty Collection, ie a collection that do not
        allow GET method.
        """
        return "%s()" % (self.__class__.__name__)

    def __repr__(self):
        """ Representation of this Collection """
        if self._embedded_data:
            return self._repr_embedded_collection()
        if self.resource_cls is not None:
            return self._repr_collection()
        return self._repr_empty_collection()

    def from_json(self, json):
        """Create a list of Resources from the JSON received from server
        corresponding to this collection.

        :param json: JSON list received from server, in the form of a
          dictionary containing a list:
          {
            "fieldname": [
               { "id": 42 }, { "id": 43 }, ...
            ]
          }
        :returns: A list of Resource objects.
        """
        resource_list = json.get(self.fieldname, [])
        return self.resource_cls.collection_from_json(
            resource_list, self._client,
            self._global_api, self.parent)

    @property
    def client(self):
        """ HTTP client used to make HTTP requests. """
        return self._client
