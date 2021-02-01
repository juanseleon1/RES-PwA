
import logging
logger = logging.getLogger(__name__)

from arcsdk.client import RequestManager
from arcsdk import config
from arcsdk import environments
from arcsdk import auth
from arcsdk.environments import Environment, CloudUrl, AuthUrl, cloud_url, auth_url


def _build(serviceName,
           description,
           hostUrl=None,
           credentials=None,
           enableLogs=False,
           enableRetryOn500=False):
    if serviceName not in description:
        raise RuntimeError('API for service %s not found' % serviceName)
    client = httpclient(hostUrl=hostUrl, credentials=credentials,
                        enableLogs=enableLogs,
                        enableRetryOn500=enableRetryOn500)
    api_description = description[serviceName]['description']
    root_cls = description[serviceName]['rootClass']
    return root_cls({}, client, api_description, None)


def httpclient(hostUrl=None,
               credentials=None,
               enableLogs=False,
               enableRetryOn500=False,
               jsonResponse=True):
    cli = RequestManager(cloud_url=hostUrl,
                         enable_logs=enableLogs,
                         enable_retry_on_500=enableRetryOn500,
                         json_response=jsonResponse)
    if credentials is not None:
        cli.set_credentials(credentials)
    return cli


def build(serviceName,
          hostUrl=None,
          credentials=None,
          enableLogs=False,
          enableRetryOn500=False):
    from arcsdk.api import description
    return _build(serviceName, description, hostUrl, credentials,
                  enableLogs=enableLogs, enableRetryOn500=enableRetryOn500)


def robothttpclient(hostUrl=None,
                    enableLogs=False,
                    enableRetryOn500=False,
                    jsonResponse=True):
    from arcsdk.auth import RobotOAuth2Credentials
    return httpclient(hostUrl=hostUrl,
                      enableLogs=enableLogs,
                      credentials=RobotOAuth2Credentials(),
                      enableRetryOn500=enableRetryOn500,
                      jsonResponse=jsonResponse)


def robotbuild(serviceName,
               hostUrl=None,
               enableLogs=False,
               enableRetryOn500=False):
    if hostUrl is None:
        hostUrl = Environments.get_default().cloud_url()
    from arcsdk.auth import RobotOAuth2Credentials
    from arcsdk.robotapi.api import description
    return _build(serviceName, description, hostUrl=hostUrl,
                  enableLogs=enableLogs,
                  credentials=RobotOAuth2Credentials(),
                  enableRetryOn500=enableRetryOn500)


# Deprecated
def api(client=None):
    logger.warn("Using api() is deprecated. Please use build() instead")
    from arcsdk.helpers import merge
    from arcsdk.ade import api as ade_api
    from arcsdk.rhm import api as rhm_api
    api_description = reduce(merge, [ade_api.description, rhm_api.description_compat()])
    if client is None:
        client = httpclient()
    from arcsdk.ade.objects import Aldebaran
    return Aldebaran({}, client, api_description, None)
