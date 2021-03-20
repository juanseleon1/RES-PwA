import pprint


def match_url_and_args(args, urls):
    argfilt = []
    for arg in args:
        if arg is None:
            break
        argfilt.append(arg)
    argnum = len(argfilt)
    if not argnum:
        url = urls[0]
    else:
        url = urls[argnum] % tuple(argfilt)
    return url


def object_list_from_json(cls, as_json, api):
    res = []
    for j in as_json:
        res.append(cls(j, api))
    return res


def safeint(s):
    try:
        return int(s)
    except ValueError:
        return s


def mypprint(ob):
    print "%s(%s)" % (
        ob.__class__.__name__,
        pprint.pformat({k: v for k, v in vars(ob).items()
                        if not k.startswith("_") == 1}),
    )


def merge(a, b, path=None):
    "merges dict b into a"
    if path is None: path = []
    for key in b:
        if key in a:
            if isinstance(a[key], dict) and isinstance(b[key], dict):
                merge(a[key], b[key], path + [str(key)])
            elif a[key] == b[key]:
                pass  # same leaf value
            else:
                raise Exception('Conflict at %s' % '.'.join(path + [str(key)]))
        else:
            a[key] = b[key]
    return a


import argparse
import json
import os
import arcsdk

def _example_set_verbose(level):
  if level >= 1:
    import logging
    logging.basicConfig(level=logging.DEBUG, format='[%(levelname)s] %(message)s')
  if level >= 2:
    import httplib
    import urllib
    httplib.HTTPConnection.debuglevel = 1

def load_credentials(auth_file_path):
    with open(auth_file_path) as auth_file:
        auth = json.load(auth_file)
        user_oauth = auth.get('user_oauth', None)
        app_oauth = auth.get('app_oauth', None)

        auth_file_dir = os.path.dirname(auth_file_path)
        if user_oauth is not None:
            from arcsdk.auth import UserOAuth2Credentials
            return UserOAuth2Credentials(
                None,
                user_oauth.get('client_id', None),

                os.path.join(auth_file_dir, user_oauth.get('cert_file', None)),
                os.path.join(auth_file_dir, user_oauth.get('key_file', None)),
                user_oauth.get('username', None),
                user_oauth.get('password', None),
           )

        elif app_oauth is not None:
            from arcsdk.auth import AppOAuth2Credentials
            return AppOAuth2Credentials(
                None,
                app_oauth.get('client_id', None),
                os.path.join(auth_file_dir, app_oauth.get('cert_file', None)),
            )

    return None


def example_init(argv, serviceName,  doc, filename, parents=[], use_auth=True):
  """A common initialization routine for examples.

  Many of the sample applications do the same initialization, which
  has now been consolidated into this function. This function uses
  common idioms found in almost all the samples, the credentials are
  stored in a file named auth.json in the same directory as the
  application main file.

  Args:
    argv: list of string, the command-line parameters of the application.
    doc: string, description of the application. Usually set to __doc__.
    file: string, filename of the application. Usually set to __file__.
    parents: list of argparse.ArgumentParser, additional command-line flags.
    scope: string, The OAuth scope used.

  Returns:
    A tuple of (ald, flags), where ald is the Aldebaran SDK object and flags
    is the parsed command-line flags.
"""

  parent_parsers = []
  parent_parsers.extend(parents)
  parser = argparse.ArgumentParser(
      description=doc,
      formatter_class=argparse.RawDescriptionHelpFormatter,
      parents=parent_parsers)
  parser.add_argument('--verbose', '-v',
                      action='count', default=0,
                      help="Make the operation more talkative. "
                      "Multiple -v options increase the verbosity. "
                      "The maximum is 2.")
  flags = parser.parse_args(argv[1:])

  _example_set_verbose(flags.verbose)

  ald = arcsdk.build(serviceName)

  if use_auth:
      auth_file_path = os.path.join(os.path.dirname(filename), 'auth.json')
      ald.client.set_credentials(load_credentials(auth_file_path))

  return ald, flags
