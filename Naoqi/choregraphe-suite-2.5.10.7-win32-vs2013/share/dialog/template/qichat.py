import sys
from naoqi import ALProxy


class MethodMissingMixinQichat(object):
    """ A Mixin' to implement the 'method_missing' Ruby-like protocol. """
    def __getattribute__(self, attr):
        try:
            return object.__getattribute__(self, attr)
        except:
            class MethodMissing(object):
                def __init__(self, wrapped, method):
                    self.__wrapped__ = wrapped
                    self.__method__ = method
                def __call__(self, *args, **kwargs):
                    return self.__wrapped__.method_missing(self.__method__, *args, **kwargs)

            p = ALProxy("ALMemory")
            return p.getData(attr)

    def method_missing(self, *args, **kwargs):
        """ This method should be overridden in the derived class. """
        print "Method missing"


class User(MethodMissingMixinQichat):
            def __assign__(self, value):
                print "affectation"
                
            def __setattr__(self,  name, value):
                p = ALProxy("ALMemory")
                p.raiseEvent(name,value)

class UserManager():
    def __init__(self):
        self.user = User()

class QiChat(MethodMissingMixinQichat,UserManager):
    
    def __setattr2__(self,  name, value):
        p = ALProxy("ALMemory")
        p.raiseEvent(name,value)

    def __init__(self):
        UserManager.__init__(self)
        setattr(self.__class__, '__setattr__', self.__setattr2__)
                
    def __assign__(self, value):
        print "affectation"

    
        
qichat = QiChat()

