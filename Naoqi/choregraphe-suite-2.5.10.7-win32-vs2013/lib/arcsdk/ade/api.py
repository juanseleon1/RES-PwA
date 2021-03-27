from arcsdk.ade.objects import (
    Category, Language, AppliVersionProperty, AppliVersion, Appli, User, Robot,
    Group, Profile, Install, System, ProfileSystem, RobotPreferenceKV,
    RobotPreferenceDomain, AppliPreferenceKV, AppliPreferenceDomain,
    UserPreferenceKV, UserPreferenceDomain, GroupPreferenceKV,
    GroupPreferenceDomain, ProfilePreferenceKV, ProfilePreferenceDomain,
    RobotModel, Visibility, RobotType, XMPPSession, ADEAuth,
    AppliVersionsCollection, UserType, ChannelCollection, InstallCollection
)

description = {
    'Aldebaran': {
        'fields': {
            'xmpp_session': XMPPSession,
            'ade_auth': ADEAuth,
        },
        'collections': {
            'applis': {
                'url': '/ade/api/1/applis',
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': Appli,
            },
            'robots': {
                'url': '/core/api/1/robots',
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': Robot,
            },
            'users': {
                'url': '/core/api/1/users',
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': User,
            },
            'groups': {
                'url': '/fleet/api/1/groups',
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': Group,
            },
            'profiles': {
                'url': '/fleet/api/1/profiles',
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': Profile,
            },
            'installs': {
                'url': '/ade/api/1/installs',
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': Install,
            },
            'categories': {
                'url': '/ade/api/1/categories',
                'methods': ['GET'],
                'resource_methods': ['GET'],
                'resource_class': Category,
            },
            'languages': {
                'url': '/ade/api/1/languages',
                'methods': ['GET'],
                'resource_methods': ['GET'],
                'resource_class': Language,
            },
            'systems': {
                'url': '/ade/api/1/systems',
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': System,
            },
            'models': {
                'url': '/ade/api/1/models',
                'methods': ['GET'],
                'resource_methods': ['GET'],
                'resource_class': RobotModel,
            },
            'robot_associations': {
                'url': '/ade/api/1/robot/assign',
                'methods': ['POST'],
            },
        }
    },
    'XMPPSession': {
        'url': "/ade/api/1/xmpp",
        'methods': ['GET'],
    },
    'ADEAuth': {
        'url': "/ade/api/1/auth",
        'methods': ['GET'],
    },
    'RobotPreferenceKV': {
        'url': "/prefs/api/1/robots/:parent__parent__id/preferences/:parent__domain/:key",
        'methods': ['GET', 'PUT', 'DELETE'],
    },
    'RobotPreferenceDomain': {
        'url': "/prefs/api/1/robots/:parent__id/preferences/:domain",
        'methods': ['GET', 'PUT', 'DELETE'],
        'collections': {
            'pairs': {
                'url': '/prefs/api/1/robots/:parent__id/preferences/:domain',
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': RobotPreferenceKV,
            },
        }
    },
    'AppliPreferenceKV': {
        'url': "/prefs/api/1/applis/:parent__parent__id/preferences/:parent__domain/:key",
        'methods': ['GET', 'PUT', 'DELETE'],
    },
    'AppliPreferenceDomain': {
        'url': "/prefs/api/1/applis/:parent__id/preferences/:domain",
        'methods': ['GET', 'PUT', 'DELETE'],
        'collections': {
            'pairs': {
                'url': '/prefs/api/1/applis/:parent__id/preferences/:domain',
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': AppliPreferenceKV,
            },
        }
    },
    'GroupPreferenceKV': {
        'url': "/prefs/api/1/groups/:parent__parent__id/preferences/:parent__domain/:key",
        'methods': ['GET', 'PUT', 'DELETE'],
    },
    'GroupPreferenceDomain': {
        'url': "/prefs/api/1/groups/:parent__id/preferences/:domain",
        'methods': ['GET', 'PUT', 'DELETE'],
        'collections': {
            'pairs': {
                'url': '/prefs/api/1/groups/:parent__id/preferences/:domain',
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': GroupPreferenceKV,
            },
        }
    },
    'ProfilePreferenceKV': {
        'url': "/prefs/api/1/profiles/:parent__parent__id/preferences/:parent__domain/:key",
        'methods': ['GET', 'PUT', 'DELETE'],
    },
    'ProfilePreferenceDomain': {
        'url': "/prefs/api/1/profiles/:parent__id/preferences/:domain",
        'methods': ['GET', 'PUT', 'DELETE'],
        'collections': {
            'pairs': {
                'url': '/prefs/api/1/profiles/:parent__id/preferences/:domain',
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': ProfilePreferenceKV,
            },
        }
    },
    'UserPreferenceKV': {
        'url': "/prefs/api/1/users/:parent__parent__id/preferences/:parent__domain/:key",
        'methods': ['GET', 'PUT', 'DELETE'],
    },
    'UserPreferenceDomain': {
        'url': "/prefs/api/1/users/:parent__id/preferences/:domain",
        'methods': ['GET', 'PUT', 'DELETE'],
        'collections': {
            'pairs': {
                'url': '/prefs/api/1/users/:parent__id/preferences/:domain',
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': UserPreferenceKV,
            },
        }
    },
    'Install': {
        'url': "/ade/api/1/installs/:id",
        'methods': ['GET', 'PUT'],
        'fields': {
            'application': Appli,
            'profile': Profile,
        },
    },
    'System': {
        'url': "/ade/api/1/systems/:id",
        'methods': ['GET', 'PUT', 'DELETE'],
        'fields': {
            'submitter': User,
        },
        'collections': {
            'robots': {
                'resource_class': RobotModel,
            },
            'visibility': {
                'resource_class': Visibility,
            },
        },
    },
    'Visibility': {},
    'ProfileSystem': {
        'url': "/ade/api/1/profiles/:parent__id/system",
        'methods': ['GET', 'PUT', 'DELETE'],
    },
    'Profile': {
        'url': "/fleet/api/1/profiles/:id",
        'methods': ['GET', 'PUT', 'DELETE'],
        'fields': {
            'owner': User,
            'system': ProfileSystem,
        },
        'collections': {
            'applis': {
                'url': '/ade/api/1/profiles/:id/applis',
                'methods': ['GET'],
                'resource_methods': ['GET', 'POST', 'DELETE'],
                'resource_class': Appli,
            },
            'installs': {
                'url': '/ade/api/1/profiles/:id/installs',
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': Install,
            },
            'preferences': {
                'url': '/prefs/api/1/profiles/:id/preferences',
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': ProfilePreferenceDomain,
            },
            'admins': {
                'resource_class': User,
            },
            'viewers': {
                'resource_class': User,
            },
        }
    },
    'Group': {
        'url': "/fleet/api/1/groups/:id",
        'methods': ['GET', 'PUT', 'DELETE'],
        'fields': {
            'owner': User,
        },
        'collections': {
            'robots': {
                'url': '/fleet/api/1/groups/:id/robots',
                'methods': ['GET'],
                'resource_methods': ['GET', 'POST', 'DELETE'],
                'resource_class': Robot,
            },
            'profiles': {
                'url': '/fleet/api/1/groups/:id/profiles',
                'methods': ['GET'],
                'resource_methods': ['GET', 'POST', 'DELETE'],
                'resource_class': Profile,
            },
            'preferences': {
                'url': '/prefs/api/1/groups/:id/preferences',
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': GroupPreferenceDomain,
            },
            'admins': {
                'resource_class': User,
            },
            'viewers': {
                'resource_class': User,
            },
        }
    },
    'User': {
        'url': "/core/api/1/users/:id",
        'fields': {
            'userType': UserType
        },
        'methods': ['GET', 'PUT', 'DELETE'],
        'collections': {
            'applis': {
                'url': '/ade/api/1/users/:id/applis',
                'methods': ['GET'],
                'resource_methods': [],
                'resource_class': Appli,
            },
            'groups': {
                'url': '/ade/api/1/users/:id/groups',
                'methods': ['GET'],
                'resource_methods': [],
                'resource_class': Group,
            },
            'profiles': {
                'url': '/ade/api/1/users/:id/profiles',
                'methods': ['GET'],
                'resource_methods': [],
                'resource_class': Profile,
            },
            'robots': {
                'url': '/core/api/1/users/:id/robots',
                'methods': ['GET'],
                'resource_methods': [],
                'resource_class': Robot,
            },
            'preferences': {
                'url': '/prefs/api/1/users/:id/preferences',
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': UserPreferenceDomain,
            },
        },
    },
    'UserType': {
        'url': '/ade/api/1/typesusers',
        'methods': ['GET'],
        'resource_class': UserType
    },
    'RobotType': {
        'url': '/ade/api/1/typesrobots',
        'methods': ['GET'],
        'resource_class': RobotType
    },
    'Robot': {
        'url': "/core/api/1/robots/:id",
        'methods': ['GET', 'PUT', 'DELETE'],
        'fields': {
            'registered': User,
            'robot_type': RobotType,
        },
        'collections': {
            'applis': {
                'url': '/ade/api/1/robots/:id/applis',
                'methods': ['GET'],
                'resource_methods': [],
                'resource_class': Appli,
            },
            'groups': {
                'url': '/fleet/api/1/robots/:id/groups',
                'methods': ['GET'],
                'resource_methods': [],
                'resource_class': Group,
            },
            'preferences': {
                'url': '/prefs/api/1/robots/:id/preferences',
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': RobotPreferenceDomain,
            },
            'admins': {
                'resource_class': User,
            },
            'viewers': {
                'resource_class': User,
            },
            'appli_versions': {
                'url': "/ade/api/1/robots/:id/lastupdate",
                'methods': [],
                'resource_methods': [],
                'resource_class': AppliVersion,
                'class': AppliVersionsCollection,
            },
            'channels': {
                'url':"/ade/api/1/robots/:id/channels",
                'methods': ['GET'],
                'resource_methods': ['GET', 'DELETE'],
                'resource_class': Profile,
                'class': ChannelCollection,
            },
            'installs': {
                'url': "/ade/api/1/robots/:id/installs",
                'methods': ['GET'],
                'resource_methods': ['GET', 'DELETE'],
                'resource_class': Install,
                'class': InstallCollection,
            },
        },
    },
    'RobotModel': {
        'url': "/core/api/1/models/:id",
        'methods': ['GET'],
        'collections': {
            'versions': {
                'url': '/ade/api/1/models/:id/versions',
                'methods': ['GET'],
                'resource_methods': [],
                'resource_class': AppliVersion,
            },
        },
    },
    'Category': {
        'url': "/ade/api/1/categories/:id",
        'methods': ['GET'],
        'collections': {
            'versions': {
                'url': '/ade/api/1/categories/:id/versions',
                'methods': ['GET'],
                'resource_methods': [],
                'resource_class': AppliVersion,
            },
        },
    },
    'Language': {
        'url': "/ade/api/1/languages/:id",
        'methods': ['GET'],
        'collections': {
            'versions': {
                'url': '/ade/api/1/languages/:id/versions',
                'methods': ['GET'],
                'resource_methods': [],
                'resource_class': AppliVersion,
            },
        },
    },
    'Appli': {
        'url': "/ade/api/1/applis/:id",
        'methods': ['GET', 'PUT', 'DELETE'],
        'collections': {
            'versions': {
                'url': '/ade/api/1/applis/:id/versions',
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': AppliVersion,
            },
            'preferences': {
                'url': '/prefs/api/1/applis/:id/preferences',
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': AppliPreferenceDomain,
            },
        },
    },
    'AppliVersion': {
        'url': "/ade/api/1/applis/:parent__id/versions/:id",
        'methods': ['GET', 'PUT', 'DELETE'],
        'fields': {
            'category': Category,
        },
        'collections': {
            'properties': {
                'url': "/ade/api/1/applis/:parent__id/versions/:id/properties",
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': AppliVersionProperty,
            },
            'languages': {
                'resource_class': Language,
            },
        },
    },
    'AppliVersionProperty': {
        'url': "/ade/api/1/applis/:parent__parent__id/versions/:parent__id/properties/:id",
        'methods': ['GET', 'PUT', 'DELETE'],
        'fields': {
            'language': Language,
        },
    },
}
