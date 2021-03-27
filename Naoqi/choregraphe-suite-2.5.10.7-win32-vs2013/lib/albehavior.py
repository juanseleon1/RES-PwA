import os
import sys
import ctypes
import weakref
import logging
import inspect
import qi

import naoqi

def load_ialbehavior_deps():
    """ Helper to laod _ialbehavior.so deps on linux """
    deps = [
        "_inaoqi.so"
        "libalbehavior.so",
    ]
    this_dir = os.path.abspath(os.path.dirname(__file__))
    for dep in deps:
        full_path = os.path.join(this_dir, dep)
        try:
            ctypes.cdll.LoadLibrary(full_path)
        except Exception:
            pass

if sys.platform.startswith("linux"):
    load_ialbehavior_deps()

import ialbehavior

class ALBehavior(ialbehavior.behavior, naoqi.NaoQiModule):
  # class var in order not to build it each time
  _noNeedToBind = set(dir(ialbehavior.behavior))
  _noNeedToBind.add("getModule")
  _noNeedToBind.add("onLoad")
  _noNeedToBind.add("onUnload")
  # deprecated since 1.14 methods
  _noNeedToBind.add("log")
  _noNeedToBind.add("playTimeline")
  _noNeedToBind.add("stopTimeline")
  _noNeedToBind.add("exitBehavior")
  _noNeedToBind.add("gotoAndStop")
  _noNeedToBind.add("gotoAndPlay")
  _noNeedToBind.add("playTimelineParent")
  _noNeedToBind.add("stopTimelineParent")
  _noNeedToBind.add("exitBehaviorParent")
  _noNeedToBind.add("gotoAndPlayParent")
  _noNeedToBind.add("gotoAndStopParent")

  # but we want to bind setParameter to listen runtime changes
  _noNeedToBind.remove("setParameter")

  def __init__(self, param, autoBind, brokerRegister=False):
    ialbehavior.behavior.__init__(self, param)
    naoqi.NaoQiModule.__init__(self, param, logger=False)
    self.logger = logging.getLogger(param)
    self.behaviorloghandler = ALBehaviorLogHandler()
    self.logger.addHandler(self.behaviorloghandler)
    self.logger.setLevel(logging.DEBUG)
    self.resource = False
    self.BIND_PYTHON(self.getName(), "__onLoad__", 0)
    self.BIND_PYTHON(self.getName(), "__onUnload__", 0)
    #always set autobind to true for the compatibility layer.
    #sometime BIND_PYTHON do not specify the number of arguments
    #that cant be guessed because the class is not provided to the method.
    autoBind = True
    if(autoBind):
      behName = self.getName()
      userMethList = set(dir(self)) - self._noNeedToBind
      for methName in userMethList:
        function = getattr(self, methName)
        if callable(function) and type(function) == type(self.__init__):
          if (methName[0] != "_"):  # private method
            self.functionName(methName, behName, "")
            for param in function.func_code.co_varnames:
              if (param != "self"):
                self.addParam(param)
            self._bindWithParam(behName, methName, naoqi._getMethodParamCount(function)-1)
    if brokerRegister:
      self.registerToBroker()

  def setParameterInternal(self, a, b):
      """ internal method used to call the good override for setParameter
          which can be overrided in user class. legacy hell.
      """
      self.setParameter(a, b)

  def session(self):
    return ialbehavior.behavior.session(self)

  def __del__(self):
    naoqi.NaoQiModule.__del__(self)
    ialbehavior.behavior.__del__(self)
    self.logger.removeHandler(self.behaviorloghandler)
    self.behaviorloghandler.close()

  def __onLoad__(self):
    self._safeCallOfUserMethod("onLoad",None)

  def __onUnload__(self):
    if(self.resource):
        self.releaseResource()
    self._safeCallOfUserMethod("onUnload",None)

  def setParameter(self, parameterName, newValue):
    ialbehavior.behavior.setParameter(self, parameterName, newValue)

  def _safeCallOfUserMethod(self, functionName, functionArg):
    import traceback
    try:
      if(functionName in dir(self)):
        func = getattr(self, functionName)
        if(func.im_func.func_code.co_argcount == 2):
          func(functionArg)
        else:
          func()
      return True
    except BaseException, err:
      if("onError" in dir(self)):
        try:
          self.onError(self.getName() + ':' + str(err))
        except BaseException, err2:
          self.logger.error(traceback.format_exc())
          self._reportError(self.behaviorId, self.__class__.__name__, traceback.format_exc())
      else:
        self.logger.error(traceback.format_exc())
        self._reportError(self.behaviorId, self.__class__.__name__, traceback.format_exc())
    return False

  # Depreciate this!!! Same as self.logger.info(), but function is always "log"
  def log(self, p):
    self.logger.warning("self.log(msg) is deprecated, please use self.logger.info(msg) instead.")
    self.logger.info(p)

# define the log handler to be used by the logging module,
# but we force the category to be behavior.box
# *AND* we prefix the message with the module name
# look at errorInBox in choregraphe for explanation
class ALBehaviorLogHandler(logging.Handler):
  def __init__(self):
    logging.Handler.__init__(self)

  def emit(self, record):
    level_to_function = {
      logging.DEBUG: naoqi.allog.debug,
      logging.INFO: naoqi.allog.info,
      logging.WARNING: naoqi.allog.warning,
      logging.ERROR: naoqi.allog.error,
      logging.CRITICAL: naoqi.allog.fatal,
    }
    function = level_to_function.get(record.levelno, naoqi.allog.debug)
    function(record.name + ": " + record.getMessage(),
             "behavior.box",
             "",   # record.filename in this case is simply '<string>'
             record.funcName,
             record.lineno)

