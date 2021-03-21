import os
from xml.etree import ElementTree
import logging
logger = logging.getLogger(__name__)

from arcsdk import config

class Environment(object):

    def __init__(self, env=None):
        self._env = None
        if env is not None:
            self.env = env

    @property
    def env(self):
        return self._env if self._env is not None else get_default()

    @env.setter
    def env(self, value):
        if not value in ALL:
            raise RuntimeError("Unknown environment '%s'" % value)
        self._env = value

    def cloud_url(self):
        return ALL[self.env]['cloud']

    def auth_url(self):
        return ALL[self.env]['as']


ALL = {
    "int": {
        "cloud": "https://int-cloud.aldebaran.com",
        "as": "https://int-as.aldebaran-robotics.com/as/token.oauth2"
    },
    "ppd": {
        "cloud": "https://ppd-cloud.aldebaran.com",
        "as": "https://ppd-as.aldebaran-robotics.com/as/token.oauth2"
    },
    "prod": {
        "cloud": "https://cloud.aldebaran-robotics.com",
        "as": "https://as.aldebaran-robotics.com/as/token.oauth2"
    },
    "qa": {
        "cloud": "https://qa-cloud.aldebaran.com",
        "as": "https://qa-as.aldebaran-robotics.com/as/token.oauth2"
    },

    "prod-cn" : {
        "cloud": "https://cloud-cn.aldebaran.com",
        "as": "https://as-cn.aldebaran-robotics.com/as/token.oauth2"
    },
    "prod-cn-1" : {
        "cloud": "https://cloud-cn-1.aldebaran.com",
        "as": "https://as-cn-1.aldebaran-robotics.com/as/token.oauth2"
    },
    "prod-cn-2" : {
        "cloud": "https://cloud-cn-2.aldebaran.com",
        "as": "https://as-cn-2.aldebaran-robotics.com/as/token.oauth2"
    },
    "prod-cn-3" : {
        "cloud": "https://cloud-cn-3.aldebaran.com",
        "as": "https://as-cn-3.aldebaran-robotics.com/as/token.oauth2"
    },
    "ppd-cn" : {
        "cloud": "https://ppd-cloud-cn.aldebaran.com",
        "as": "https://ppd-as-cn.aldebaran-robotics.com/as/token.oauth2"
    },
    "qa-cn" : {
        "cloud": "https://qa-cloud-cn.aldebaran.com",
        "as": "https://qa-as-cn.aldebaran-robotics.com/as/token.oauth2"
    },
    "int-cn" : {
        "cloud": "https://int-cloud-cn.aldebaran.com",
        "as": "https://int-as-cn.aldebaran-robotics.com/as/token.oauth2"
    },

    "prod-jp" : {
        "cloud": "https://cloud-jp.aldebaran.com",
        "as": "https://as-jp.aldebaran-robotics.com/as/token.oauth2"
    },
    "prod-jp-1" : {
        "cloud": "https://cloud-jp-1.aldebaran.com",
        "as": "https://as-jp-1.aldebaran-robotics.com/as/token.oauth2"
    },
    "prod-jp-2" : {
        "cloud": "https://cloud-jp-2.aldebaran.com",
        "as": "https://as-jp-2.aldebaran-robotics.com/as/token.oauth2"
    },
    "prod-jp-3" : {
        "cloud": "https://cloud-jp-3.aldebaran.com",
        "as": "https://as-jp-3.aldebaran-robotics.com/as/token.oauth2"
    },
    "ppd-jp" : {
        "cloud": "https://ppd-cloud-jp.aldebaran.com",
        "as": "https://ppd-as-jp.aldebaran-robotics.com/as/token.oauth2"
    },
    "qa-jp" : {
        "cloud": "https://qa-cloud-jp.aldebaran.com",
        "as": "https://qa-as-jp.aldebaran-robotics.com/as/token.oauth2"
    },
    "int-jp" : {
        "cloud": "https://int-cloud-jp.aldebaran.com",
        "as": "https://int-as-jp.aldebaran-robotics.com/as/token.oauth2"
    },
    "prod-eu" : {
        "cloud": "https://cloud-eu.aldebaran.com",
        "as": "https://as-eu.aldebaran-robotics.com/as/token.oauth2"
    },
    "prod-eu-1" : {
        "cloud": "https://cloud-eu-1.aldebaran.com",
        "as": "https://as-eu-1.aldebaran-robotics.com/as/token.oauth2"
    },
    "prod-eu-2" : {
        "cloud": "https://cloud-eu-2.aldebaran.com",
        "as": "https://as-eu-2.aldebaran-robotics.com/as/token.oauth2"
    },
    "prod-eu-3" : {
        "cloud": "https://cloud-eu-3.aldebaran.com",
        "as": "https://as-eu-3.aldebaran-robotics.com/as/token.oauth2"
    },
    "ppd-eu" : {
        "cloud": "https://ppd-cloud-eu.aldebaran.com",
        "as": "https://ppd-as-eu.aldebaran-robotics.com/as/token.oauth2"
    },
    "qa-eu" : {
        "cloud": "https://qa-cloud-eu.aldebaran.com",
        "as": "https://qa-as-eu.aldebaran-robotics.com/as/token.oauth2"
    },
    "int-eu" : {
        "cloud": "https://int-cloud-eu.aldebaran.com",
        "as": "https://int-as-eu.aldebaran-robotics.com/as/token.oauth2"
    },
    "prod-us" : {
        "cloud": "https://cloud-us.aldebaran.com",
        "as": "https://as-us.aldebaran-robotics.com/as/token.oauth2"
    },
    "prod-us-1" : {
        "cloud": "https://cloud-us-1.aldebaran.com",
        "as": "https://as-us-1.aldebaran-robotics.com/as/token.oauth2"
    },
    "prod-us-2" : {
        "cloud": "https://cloud-us-2.aldebaran.com",
        "as": "https://as-us-2.aldebaran-robotics.com/as/token.oauth2"
    },
    "prod-us-3" : {
        "cloud": "https://cloud-us-3.aldebaran.com",
        "as": "https://as-us-3.aldebaran-robotics.com/as/token.oauth2"
    },
    "ppd-us" : {
        "cloud": "https://ppd-cloud-us.aldebaran.com",
        "as": "https://ppd-as-us.aldebaran-robotics.com/as/token.oauth2"
    },
    "qa-us" : {
        "cloud": "https://qa-cloud-us.aldebaran.com",
        "as": "https://qa-as-us.aldebaran-robotics.com/as/token.oauth2"
    },
    "int-us" : {
        "cloud": "https://int-cloud-us.aldebaran.com",
        "as": "https://int-as-us.aldebaran-robotics.com/as/token.oauth2"
    },
}

_default = None
_CONST_DEFAULT = "prod"

def set_default(env):
    global _default
    if config.I_AM_A_ROBOT:
        logger.warning("Setting environment overidde global environment"
                       " set by ALCloud.setEnvironment")
    if not env in ALL:
        raise RuntimeError("Unknown environment '%s'" % env)
    _default = env

def get_default():
    global _default
    if _default is None:
        if config.I_AM_A_ROBOT:
            return _parse_environment_on_robot()
        return _CONST_DEFAULT
    return _default

def reset_default():
    global _default
    _default = None

def add_custom(name, cloud_url, auth_url):
    global ALL
    ALL[name] = {
        "cloud": cloud_url,
        "as": auth_url
    }

_parsed_env = None

def _parse_environment_on_robot():
    global _parsed_env
    if _parsed_env is None:
        _parsed_env = _CONST_DEFAULT
        import qi
        alcloudprefs = qi.path.findConf("naoqi", "ALCloud.xml")
        pref = "{http://www.aldebaran-robotics.com/ns/ALPreference}Preference"
        env = 'prod'
        if os.path.isfile(alcloudprefs):
            try:
                _parsed_env = ElementTree.parse(alcloudprefs).find(pref).attrib['value']
            except:
                pass
    return _parsed_env


class CloudUrl(object):
    '''Use this class to globally set the base part of your urls (ex:
    https://cloud.aldebaran.com), then to only specify the path part
    in your code (ex: /ade/api/1/auth)

    # Choose to use the default environment
    # That you can later choose to change with
    #        set_default_environment("ppd")
    # or in case you are on a robot:
    #        ALCloud.setEnvironment("ppd") # + naoqi reboot
    url = CloudUrl()
    requests.get(url("/ade/api/1/auth"))

    # or use a specific environment in case you need to use several
    # environment at the same time
    url = CloudUrl(Environment("ppd"))
    requests.get(url("/ade/api/1/auth"))

    '''
    def __init__(self, environment=None):
        if environment is None:
            environment = Environment()
        self.environment = environment

    def __call__(self, url):
        return '%s%s' % (self.environment.cloud_url(), url)


class CloudUrlFromUrl(object):
    def __init__(self, url):
        self.url = url

    def __call__(self, url):
        return '%s%s' % (self.url, url)

cloud_url = CloudUrl()


class AuthUrl(object):

    def __init__(self, environment=None):
        if environment is None:
            environment = Environment()
        self.environment = environment

    def __call__(self):
        return self.environment.auth_url()


auth_url = AuthUrl()
