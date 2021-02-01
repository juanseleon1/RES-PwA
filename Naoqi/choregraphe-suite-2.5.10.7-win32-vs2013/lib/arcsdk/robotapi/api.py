from arcsdk.ade import objects as ade_objects
from arcsdk.rhm import objects as rhm_objects

core_description = {
    'Aldebaran': {
        'fields': {
            'robot_update': ade_objects.RobotUpdate,
            'robot': ade_objects.Robot,
        },
        'collections': {
            'robot_associations': {
                'url': '/ade/api/1/robot/assign',
                'methods': ['POST'],
            },
        },
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
                'resource_class': ade_objects.RobotPreferenceKV,
            },
        }
    },
    'RobotUpdate': {
        'url': '/ade/api/1/robot/update',
        'methods': ['GET'],
        'collections': {
            'packages': {
                'resource_class': ade_objects.RobotPackage,
            },
        },
    },
    'RobotPackage': {},
    'User': {
    },
    'RobotType': {},
    'Robot': {
        'url': "/ade/api/1/robot/auth",
        'methods': ['GET', 'PUT', 'DELETE'],
        'fields': {
            'registered': ade_objects.User,
            'robot_type': ade_objects.RobotType,
        },
        'collections': {
            'preferences': {
                'url': '/prefs/api/1/robots/:id/preferences',
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': ade_objects.RobotPreferenceDomain,
            },

            'admins': {
                'resource_class': ade_objects.User,
            },
            'viewers': {
                'resource_class': ade_objects.User,
            },
        },
    },
}

rhm_description = {
    'Aldebaran': {
        'fields': {
            'passivediag_errors': rhm_objects.PassivediagErrors
        },
        'collections': {
            'passivediags': {
#                'url':'/rhm/api/1/robots/{rid}/passivediags/id,
#                'methods': ['POST'],
                'resource_class': rhm_objects.Passivediag,
                'class': rhm_objects.PassivediagCollection,
            }
        },
        'Passivediag': {},
    }
}


description = {
    'core': {
        'description': core_description,
        'rootClass': ade_objects.Aldebaran,
    },
    'ade': {
        'description': core_description,
        'rootClass': ade_objects.Aldebaran,
    },
    'fleet': {
        'description': core_description,
        'rootClass': ade_objects.Aldebaran,
    },
    'prefs': {
        'description': core_description,
        'rootClass': ade_objects.Aldebaran,
    },
    'rhm': {
        'description': rhm_description,
        'rootClass': rhm_objects.Aldebaran,
    },
}
