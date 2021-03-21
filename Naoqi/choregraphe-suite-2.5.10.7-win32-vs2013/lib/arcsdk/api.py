from arcsdk.ade import objects as ade_objects
from arcsdk.ade import api as ade_api
from arcsdk.rhm import objects as rhm_objects
from arcsdk.rhm import api as rhm_api

description = {
    'core': {
        'description': ade_api.description,
        'rootClass': ade_objects.Aldebaran,
    },
    'ade': {
        'description': ade_api.description,
        'rootClass': ade_objects.Aldebaran,
    },
    'fleet': {
        'description': ade_api.description,
        'rootClass': ade_objects.Aldebaran,
    },
    'prefs': {
        'description': ade_api.description,
        'rootClass': ade_objects.Aldebaran,
    },
    'rhm': {
        'description': rhm_api.description,
        'rootClass': rhm_objects.Aldebaran,
    },
}
