import qi.logging
logger = qi.logging.Logger("arcsdk.robot")


def _get_conf(qiSession):
    try:
        oldprefs = qiSession.service('ALPreferences')
        conf = oldprefs.readPrefFile("ALTelepathe", False)
        conf = {v[0]: v[2] for v in conf}
        return conf
    except Exception:
        import traceback
        logger.error(traceback.format_exc())
    return {}


def get_api_token(qiSession):
    """ Return the apitoken found in ~nao/.config/naoqi/ALTelepathe.xml """
    conf = _get_conf(qiSession)
    apitoken = conf.get("apitoken", None)
    return apitoken


def get_headid(qiSession):
    """ Return the robot FullHeadId """
    try:
        alm = qiSession.service('ALMemory')
        return alm.getData('RobotConfig/Head/FullHeadId')
    except Exception:
        import traceback
        logger.error(traceback.format_exc())
    return None


def get_buildid():
    """ Get Robot Running System Version """
    try:
        with open("/etc/lsb-release") as lsb_file:
            for lsb_line in lsb_file:
                lsb_parts = lsb_line.strip().split("=", 1)
                if lsb_parts[0] == "DISTRIB_BUILDID":
                    build_id = lsb_parts[1].replace("\"", "").strip()
        return build_id
    except Exception:
        import traceback
        logger.error(traceback.format_exc())
    return None


def get_qi_session():
    from naoqi import ALProxy
    try:
        return ALProxy("ALMemory", "127.0.0.1", 9559).session()
    except Exception:
        import traceback
        logger.error(traceback.format_exc())
    return None
