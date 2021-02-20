from arcsdk.rhm.objects import (
    User, Robot, Log, Probe, RobotPart, LogCollection,
    TimeSerieCollection, Status, Profile, ProfileCollection, TimeSerie,
    RobotCollection, ProfileData, Geoloc, RobotStatus
)


description = {
    'Aldebaran': {
        'fields': {
            'status': Status,
        },
        'collections': {
            'robots': {
                'url': '/rhm/api/1/fleetstatus',
                'methods': ['GET'],
                'resource_methods': ['GET'],
                'resource_url': '/rhm/api/1/robots/%s',
                'resource_class': Robot,
                'class': RobotCollection,
            },
            'probes': {
                'url': '/rhm/api/1/probes',
                'methods': ['GET', 'POST'],
                'resource_methods': ['GET'],
                'resource_class': Probe,
            },
            'batch_profiles': {
                'url': '/rhm/api/1/batch/profiles',
                'methods': ['GET'],
                'resource_class': Profile,
                'class': ProfileCollection,
            },
        }
    },
    'Robot': {
        'url': "/rhm/api/1/robots/:id",
        'methods': ['GET'],
        'fields': {
            'owner': User,
            'geoloc': Geoloc,
            'detailed_status': RobotStatus,
        },
        'collections': {
            'logs': {
                'url': "/rhm/api/1/robots/:id/logs",
                'methods': ['POST'],
                'resource_methods': [],
                'resource_class': Log,
                'class': LogCollection,
            },
            'parts': {
                'url': "/rhm/api/1/robots/:id/parts",
                'methods': ['GET'],
                'resource_methods': [],
                'resource_class': RobotPart,
            },
            'probes': {
                'url': '/rhm/api/1/robots/:id/probes',
                'methods': ['GET', 'POST'],
                'resource_class': Probe,
            }
        },
    },
    'User': {},
    'Log': {},
    'RobotPart': {},
    'Probe': {
        'url': "/rhm/api/1/probes/:name",
        'methods': ['GET', 'PUT', 'DELETE'],
        'collections': {
            'timeseries': {
                'methods': ['GET'],
                'url': "/rhm/api/1/probes/:name",
                'class': TimeSerieCollection,
                'resource_class': TimeSerie,
            }
        },
    },
    'Status': {
        'url': '/rhm/api/1/status',
        'methods': ['GET'],
    },
    'Profile': {
        'url': '/rhm/api/1/batch/profiles/:profile_id',
        'methods': ['PUT'],
        'fields': {
            'data': ProfileData,
        },
    },
    'TimeSerie': {
    },
    'ProfileData': {
        'url': '/rhm/api/1/batch/profiles/:parent__profile_id/data',
        'methods': ['GET'],
        'fields': {
            'profile': Profile,
        }
    },
    'Geoloc': {
        'url': '/rhm/api/1/robots/:parent__id/geoloc',
        'methods': ['PUT', 'DELETE'],
    },
    'RobotStatus': {
        'url': '/rhm/api/1/robots/:parent__id/status',
        'methods': ['GET', 'PUT'],
    }
}


_description_compat = None


# Deprecated api: keep compatibility
def description_compat():
    global _description_compat
    if _description_compat is None:
        import copy
        d = copy.deepcopy(description)
        d['RHMRobot'] = d['Robot']
        del d['Robot']
        d['Aldebaran']['collections']['fleetstatus'] = \
            d['Aldebaran']['collections'].pop('robots')
        RHMRobot = d['Aldebaran']['collections']['fleetstatus']['resource_class']
        RHMRobot = type('RHMRobot', (RHMRobot,), {})
        d['Aldebaran']['collections']['fleetstatus']['resource_class'] = RHMRobot
        d['RhmProfile'] = d.pop('Profile')
        RHMProfile = \
            d['Aldebaran']['collections']['batch_profiles']['resource_class']
        RHMProfile = type('RHMProfile', (RHMProfile,), {})
        _description_compat = d

    return _description_compat
