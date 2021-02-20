# -*- coding: utf-8
import logging
logger = logging.getLogger(__name__)


I_AM_A_ROBOT = None
try:
    import platform
    I_AM_A_ROBOT = "aldebaran" in platform.platform()
except:
    I_AM_A_ROBOT = False
