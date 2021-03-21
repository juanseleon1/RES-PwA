#!/usr/bin/env python
#-*- coding: utf-8 -*-
''' Reports Collisions in the World Representation '''

import math
import qi
import random
import signal
import sys
import threading


g_useALMath = True
try:
    import almath
except:
    qi.error("ALCollisionsRepresentation", "import almath failed")
    g_useALMath = False

from naoqi import ALProxy

class ALCollisionsRepresentation:

    def _namingPatch(self, name):
        if(name == "Torso"):
            return "Robot_Torso"
        else:
            return name


    def _toBeDisplayed(self, motionProxy, pName):
        robotConfig = motionProxy.getRobotConfig()
        if (pName == "HeadPitchLaserCollision" and robotConfig[1][3] == False):
            # If the robot has no laser, do not display it.
            return False
        elif (("LFoot" in pName) or
              ("RFoot" in pName)):
            # Do not display feet.
            return False
        else:
            return True


    def _cylinderPosAngles(self, pName, point1, point2):
        if not g_useALMath:
            return [0.0, 0.0, 0.0]
        vect = almath.Position3D(\
            point2[0]-point1[0],\
            point2[1]-point1[1],\
            point2[2]-point1[2])
        tf = almath.orthogonalSpace(vect)*almath.Transform.fromRotX(90.0*almath.TO_RAD)
        pos6D = almath.position6DFromTransform(tf)
        return [pos6D.wx, pos6D.wy, pos6D.wz]


    def _createShapeSphere(self, collision):
        ret = {}
        pName = collision[0]
        parentJoint = collision[1]

        keyName     = "CollisionSphere" + pName
        radius      = collision[4]
        position    = [collision[5][0], collision[5][1], collision[5][2]]
        x           = position[0]
        y           = position[1]
        z           = position[2]

        ret[keyName] = [pName,
          parentJoint,
          [x, y, z, 0.0, 0.0, 0.0], # Position6D
          "sphere",
          [2.0*radius, 2.0*radius, 2.0*radius], # size (x, y, z)
          [0.9411 , 0.2470, 0.1255]] # (r, g, b)
        return ret


    def _createShapePlan(self, collision):
        ret = {}
        pName       = collision[0]
        parentJoint = collision[1]
        keyName     = "CollisionPlan" + pName
        ret[keyName] = [pName,
            parentJoint,
            [0.0, 0.0, -0.7, 0.0, 0.0, 0.0], # Position6D
            "sphere",
            [0.5, 0.5, 0.01], # size (x, y, z)
            [0.3, 0.7, 0.7]] # (r, g, b)
        return ret


    def _createShapePill(self, collision):
        ret = {}
        pName       = collision[0]
        parentJoint = collision[1]
        radius      = 1.0*collision[4]

        # first sphere
        keyName     = "CollisionPill" + pName + 'a'
        x = collision[5][0]
        y = collision[5][1]
        z = collision[5][2]
        ptA = [x, y, z]

        ret[keyName] = [pName,
            parentJoint,
            [x, y, z, 0.0, 0.0, 0.0], # Position6D
            "sphere",
            [2.0*radius, 2.0*radius, 2.0*radius], # size (x, y, z)
            [0.9411 , 0.2470, 0.1255]] # (r, g, b)

        # second sphere
        keyName  = "CollisionPill" + pName + 'b'
        x = collision[6][0]
        y = collision[6][1]
        z = collision[6][2]
        ptB = [x, y, z]

        ret[keyName] =  [pName,
            parentJoint,
            [x, y, z, 0.0, 0.0, 0.0], # Position6D
            "sphere",
            [2.0*radius, 2.0*radius, 2.0*radius], # size (x, y, z)
            [0.9411 , 0.2470, 0.1255]] # (r, g, b)

        keyName  = "CollisionPill" + pName + 'c'
        x        = 0.5*(collision[5][0]+collision[6][0])
        y        = 0.5*(collision[5][1]+collision[6][1])
        z        = 0.5*(collision[5][2]+collision[6][2])
        distance = math.sqrt(
            (collision[6][0]-collision[5][0])*(collision[6][0]-collision[5][0])+
            (collision[6][1]-collision[5][1])*(collision[6][1]-collision[5][1])+
            (collision[6][2]-collision[5][2])*(collision[6][2]-collision[5][2]))

        ret[keyName] = [pName,
            parentJoint,
            [x, y, z] + self._cylinderPosAngles(collision[0], ptA, ptB), # Position6D
            "cylinder",
            [2.0*radius, distance, 2.0*radius], # was 5.0*2.0*radius size (x, y, z)
            #[5.0*2.0*radius, 5.0*2.0*radius, distance], # size (x, y, z)
            [0.9411 , 0.2470, 0.1255]]  # (r, g, b)
        return ret


    def _createShapeTab(self, collision):
        ret = {}
        pName       = collision[0]
        parentJoint = collision[1]
        radius      = collision[4]

        # sphere 1
        keyName     = "CollisionTab" + pName + 'a'
        x        = collision[5][0]
        y        = collision[5][1]
        z        = collision[5][2]
        ret[keyName] = [pName,
            parentJoint,
            [x, y, z, 0.0, 0.0, 0.0], # Position6D
            "sphere",
            [2.0*radius, 2.0*radius, 2.0*radius], # size (x, y, z)
            [0 , 1, 1]] # (r, g, b)

        # sphere 2
        keyName  = "CollisionTab" + pName + 'b'
        x        = collision[6][0]
        y        = collision[6][1]
        z        = collision[6][2]
        ret[keyName] = [pName,
            parentJoint,
            [x, y, z, 0.0, 0.0, 0.0], # Position6D
            "sphere",
            [2.0*radius, 2.0*radius, 2.0*radius], # size (x, y, z)
            [0 , 1, 1]] # (r, g, b)

        # sphere 3
        keyName     = "CollisionTab" + pName + 'c'
        x        = collision[7][0]
        y        = collision[7][1]
        z        = collision[7][2]
        ret[keyName]  = [pName,
            parentJoint,
            [x, y, z, 0.0, 0.0, 0.0], # Position6D
            "sphere",
            [2.0*radius, 2.0*radius, 2.0*radius], # size (x, y, z)
            [0 , 1, 1]] # (r, g, b)

        # sphere 4
        keyName  = "CollisionTab" + pName + 'd'
        x        = collision[8][0]
        y        = collision[8][1]
        z        = collision[8][2]
        ret[keyName] = [pName,
            parentJoint,
            [x, y, z, 0.0, 0.0, 0.0], # Position6D
            "sphere",
            [2.0*radius, 2.0*radius, 2.0*radius], # size (x, y, z)
            [0 , 1, 1]] # (r, g, b)
        return ret;

    def _createShapes(self,
        motionProxy,
        collisionList):

        for collision in collisionList:
            collision[1] = self._namingPatch(collision[1])

            pName = collision[0]
            # collision type
            # 0: COLLISION_TYPE_STATIC
            # 1: COLLISION_TYPE_DYNAMIC
            # 2: COLLISION_TYPE_VIRTUALCONTACT
            # 3: COLLISION_TYPE_SUPPORTPOLYGON
            collisionType = collision[2]
            shape         = collision[3]

            # do not care of collisionType
            if (collisionType == 2) or (collisionType == 3):
                continue

            keyName = ""
            if (self._toBeDisplayed(motionProxy, collision[0])):
                ## Sphere
                if shape == 0:
                    self.shapesDict[pName] = self._createShapeSphere(collision)

                ## Plan
                elif shape == 1:
                    self.shapesDict[pName] = self._createShapePlan(collision)

                ## Pill
                elif shape == 2:
                    self.shapesDict[pName] = self._createShapePill(collision)

                ## Tab
                elif shape == 3:
                    self.shapesDict[pName] = self._createShapeTab(collision)

                ## Other
                else:
                    print "Unknown case"
            else:
                if shape >= 0 and shape <= 3:
                  self.shapesDict[pName] = {}
                else:
                    print "Unknow case -- noShape: ", shape


    def displayAllShapes(self):
        ''' Displays all collisions bounding box shapes'''
        for pName, shapesArray in self.shapesDict.iteritems():
            for name, shape in shapesArray.iteritems():
                self.storeObjectInWorldRepresentation(name, shape[1], self.k_objectCategory, shape[3], shape[2], shape[4], shape[5])

    def _displayShape(self, nameToDisplay):
        for pName, shapesArray in self.shapesDict.iteritems():
            if(pName == nameToDisplay):
                for name, shape in shapesArray.iteritems():
                    shape[5] = [1 , 0, 0]
                    self.storeObjectInWorldRepresentation(name, shape[1], self.k_objectCategory, shape[3], shape[2], shape[4], shape[5])
                break

    def _removeShape(self, nameToRemove):
        for pName, shapesArray in self.shapesDict.iteritems():
            if(pName == nameToRemove):
                for name, shape in shapesArray.iteritems():
                    self.removeObjectFromWorldRepresentation(name);
                break

    def removeAllShapes(self):
        ''' Removes all collisions bounding box from the World Representation.
            Deletes and recreates the collision objects category.'''
        try:
          if(self.worldRepresentation.objectCategoryExists(self.k_objectCategory)):
              self.worldRepresentation.removeObjectCategory(self.k_objectCategory)
          self.worldRepresentation.createObjectCategory(self.k_objectCategory, True)
        except Exception as e:
          qi.warning("ALCollisionsRepresentation",
              "could not remove/recreate category '%s': %s"
              % (self.k_objectCategory, e))
          pass

    def _initCollisionShapes(self):
        try:
            collisionList = self.motionProxy._getCollisionShapes(self.mode)
            if type(collisionList).__name__ == "NoneType" or len(collisionList)==0:
                collisionList = []

            self._createShapes(
                self.motionProxy,
                collisionList)

        except KeyboardInterrupt:
            print "Interrupted by user, stopping..."
        except RuntimeError, e:
            qi.warning("ALCollisionsRepresentation",
                "There was an exception: %s" % (e))

    def removeObjectFromWorldRepresentation(self, objectName):
        ''' Removes generically an object from the WorldRepresentation.
            param: objectName, the name of the object to remove'''
        try:
            self.worldRepresentation.clearObject(objectName)
        except Exception as e:
            qi.warning("ALCollisionsRepresentation",
                "worldRepresentation.clearObject(%s) error code: %s"
                    % (objectName, e))


    def storeObjectInWorldRepresentation(self, objectName, parentName, categoryName, model, position, size, color):
        ''' Adds generically an object to the WorldRepresentation.
        param: objectName, the name of the object to remove
               parentName, the robot or object part to be considered as parent/move along with
               categoryName, category of the WorldRepresentation object to create
               model, the 3D model to represent the object with ("sphere", "cylinder" for instance)
               position, a 6D array to represent the object position
               size, a 3D scaleX/Y/Z array
               color, an 3 elements array with RGB float values
        '''
        attributes = [["model", model],
        ["scaleX", size[0]],
        ["scaleY", size[1]],
        ["scaleZ", size[2]],
        ["colorR", float(color[0])],
        ["colorG", float(color[1])],
        ["colorB", float(color[2])]]
        try:
            ret = self.worldRepresentation.storeObject(objectName, parentName, position, categoryName, [])
            if(ret):
                qi.warning("ALCollisionsRepresentation",
                "worldRepresentation.storeObject(%s, %s, %s, %s, %s) error code: %s"
                    % (objectName, parentName, position, categoryName, attributes, ret))
        except Exception as e:
            qi.warning("ALCollisionsRepresentation",
                "worldRepresentation.storeObject(%s, %s, %s, %s, %s) error: %s"
                    % (objectName, parentName, position, categoryName, attributes, e))
            return

        try:
            ret = self.worldRepresentation.updateAttribute(
                            objectName,
                            "Display",
                            "1",
                            attributes)
            if(ret):
                qi.warning("ALCollisionsRepresentation", "wr.updateAttribute(%s, %s, %s, %s) error code: %d"
                        %(objectName, "Display", "1", attributes, ret))
        except Exception as e:
            qi.warning("wr.updateAttribute(%s, %s, %s, %s) error: %s"
                %(objectName, "Display", "1", attributes, e))

    def _pollCollisionsInfo(self):
        try:
            self.detectedCollisionPairs = self.motionProxy._getDetectedCollisions(0.001)
            if(self.detectedCollisionPairs is None):
                self.detectedCollisionPairs = [];

            toRemoveArray = []
            for collisionName, dummy in self.displayedCollisionNames.iteritems():
                found = False
                for collisionPair in self.detectedCollisionPairs:
                    if(collisionPair[0] == collisionName or collisionPair[1] == collisionName):
                        found = True
                if(not found):
                    self._removeShape(collisionName)
                    toRemoveArray.append(collisionName)

            for toRemove in toRemoveArray:
                del self.displayedCollisionNames[toRemove]

            if(len(self.detectedCollisionPairs) == 0):
              return

            #qi.verbose("ALCollisionsRepresentation", "Detected " + str(self.detectedCollisionPairs))

            for collisionPair in self.detectedCollisionPairs:
                if(not self.displayedCollisionNames.has_key(collisionPair[0])):
                    self.displayedCollisionNames[collisionPair[0]] = True
                    self._displayShape(collisionPair[0])
                if(not self.displayedCollisionNames.has_key(collisionPair[1])):
                    self.displayedCollisionNames[collisionPair[1]] = True
                    self._displayShape(collisionPair[1])

        except KeyboardInterrupt:
            print "Interrupted by user, stopping..."
        except RuntimeError, e:
            qi.warning("ALCollisionsRepresentation", "There was an exception: " + str(e))

    k_objectCategory = "CollisionObjects"
    shapesDict = {}
    displayedCollisionNames = {}
    detectedCollisionPairs = []
    motionProxy = None
    worldRepresentation = None
    collisionsTask = None
    mode = None
    lock = None
    cleanUpDone = False

    def __init__(self, session, mode):
        self.lock = threading.Lock()
        if(not session):
          return
        self.motionProxy = session.service("ALMotion")
        self.worldRepresentation = session.service("ALWorldRepresentation")
        self.mode = mode

        self.removeAllShapes()
        self._initCollisionShapes()

        #Debug
        #self.displayAllShapes()

        self.collisionsTask = qi.PeriodicTask()
        self.collisionsTask.setCallback(self._pollCollisionsInfo)
        self.collisionsTask.setUsPeriod(200*1000) # every 0.2 seconds
        self.collisionsTask.start(True)

    def stopService(self):
        self.__del__()

    def __del__(self):
        self.lock.acquire()
        if(self.cleanUpDone):
            return
        self.cleanUpDone = True
        self.lock.release()
        self.collisionsTask.stop()
        del self.collisionsTask
        self.removeAllShapes()
        del self.worldRepresentation

g_service = None

def signal_handler(signal, frame):
    g_service.stopService()
    del g_service
    sys.exit(0)

def main():
    global g_service
    app = qi.ApplicationSession(sys.argv)
    app.start()
    g_service = ALCollisionsRepresentation(app.session, "static")
    app.session.registerService("ALCollisionsRepresentation", g_service)
    app.run()
    g_service.stopService()
    del g_service

if __name__ == "__main__":
    main()
