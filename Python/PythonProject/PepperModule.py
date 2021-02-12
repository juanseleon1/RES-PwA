from naoqi import *
import socket
import threading
import json
import qi
import sys
import argparse
import datetime

# ----------------------------------------------------------------------------MODULE---------------------------------------------------------------------------------------------
from main import activities_running, send

"""--------------------------------------------------------------------------MODULE---------------------------------------------------------------------------------------------"""


# ----------------------------------------------------------------------------MODULE---------------------------------------------------------------------------------------------
# create python module
class PepperModule(ALModule):
    """python class myModule test auto documentation: comment needed to create a new python module"""
    """python class myModule test auto documentation: comment needed to create a new python module"""

    def pythondatachanged(self, key, value, message):
        """callback when data change"""
        """HOST_LOCAL = '127.0.0.1'
        PORT = 7897
        FORMAT = 'utf-8'
        ADDR = (HOST_LOCAL, PORT)
        client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        client.connect(ADDR)
        msg_to_send = json.dumbs(json_creator(-1, "ROB", True))
        client.send(msg_to_send)
        client.close() 
        """
        # print("send ", msg_to_send)
        # json_creator(-1, responseTypeBESAFunction(key), getParams(key, value))

        # send(msg_to_send)
        print "datachanged:", key, " value:", value, " message:", message

    # Raised when an animated speech is done.
    def endOfAnimatedSpeech(self, key, value, message):
        if activities_running.has_key("SAYWITHMOVEMENT"):
            activities_running.pop("SAYWITHMOVEMENT")
        json_params = {}
        json_params["endOfAnimatedSpeech"] = True
        send(-1, "int", json_params)

    # Raised when the person tracked can no longer be found for some time.
    def humanLost(self, key, value, message):
        json_params = {}
        json_params["humanLost"] = True
        send(-1, "int", json_params)

    # Raised when the robot begins to track a person, when the tracked person is lost, or when the tracked person's ID is
    def humanTracked(self, key, value, message):
        json_params = {}
        json_params["humanTracked"] = value
        send(-1, "int", json_params)

    def stimulusDetected(self, key, value, message):
        json_params = {}
        json_params["stimulusDetected"] = value
        send(-1, "int", json_params)

    def batteryLow(self, key, value, message):
        json_params = {}
        # Supposedly the value always is True
        json_params["batteryLow"] = value
        send(-1, "rob", json_params)

    def goToFailed(self, key, value, message):
        json_params = {}
        # The value should be True
        json_params["goToFailed"] = value
        send(-1, "rob", json_params)

    def goToSuccess(self, key, value, message):
        json_params = {}
        # The value should be True
        json_params["goToSuccess"] = value
        send(-1, "rob", json_params)

    def goToLost(self, key, value, message):
        json_params = {}
        # The value should be True
        json_params["goToLost"] = value
        send(-1, "rob", json_params)

    def localizeSuccess(self, key, value, message):
        json_params = {}
        # The value should be True
        json_params["localizeSuccess"] = value
        send(-1, "rob", json_params)

    def localizeLost(self, key, value, message):
        json_params = {}
        # The value should be True
        json_params["localizeLost"] = value
        send(-1, "rob", json_params)

    def localizeDirectionLost(self, key, value, message):
        json_params = {}
        # The value should be True
        json_params["localizeDirectionLost"] = value
        send(-1, "rob", json_params)

    def localizeDirectionSuccess(self, key, value, message):
        json_params = {}
        # The value should be True
        json_params["localizeDirectionSuccess"] = value
        send(-1, "rob", json_params)

    def chainVelocityClipped(self, key, value, message):
        json_params = {}
        # The value should be True
        json_params["chainVelocityClipped"] = value
        send(-1, "rob", json_params)

    def moveFailed(self, key, value, message):
        json_params = {}
        # The value should be True
        if activities_running.has_key("MOVE"):
            activities_running.pop("MOVE")

        json_params["moveFailed"] = value
        send(-1, "int", json_params)

    def robotIsWakeUp(self, key, value, message):
        json_params = {}
        # The value should be True
        json_params["robotIsWakeUp"] = value
        send(-1, "int", json_params)

    def wakeUpFinished(self, key, value, message):
        json_params = {}
        # The value should be True
        json_params["wakeUpFinished"] = value
        send(-1, "int", json_params)

    def restFinished(self, key, value, message):
        json_params = {}
        # The value should be True
        json_params["restFinished"] = value
        send(-1, "int", json_params)

    def disabledDevicesChanged(self, key, value, message):
        json_params = {}
        # The value is a map with the devices of the robot whose are able/disabled
        json_params["disabledDevicesChanged"] = value
        send(-1, "int", json_params)

    def disabledFeaturesChanged(self, key, value, message):
        json_params = {}
        # The value is a map with the features of the robot whose are able/disabled
        json_params["disabledFeaturesChanged"] = value
        send(-1, "int", json_params)

    def connectedToChargingStation(self, key, message):
        json_params = {}
        json_params["connectedToChargingStation"] = True
        send(-1, "rob", json_params)

    def moveFailedRecharging(self, key, message):
        json_params = {}
        if activities_running.has_key("MOVE"):
            activities_running.pop("MOVE")
        json_params["moveFailedRecharging"] = True
        send(-1, "int", json_params)

    def leaveFailed(self, key, message):
        json_params = {}
        json_params["leaveFailed"] = True
        send(-1, "int", json_params)

    def wordRecognized(self, key, value, message):
        json_params = {}
        # The value is a map with the word recognized
        json_params["wordRecognized"] = value
        send(-1, "int", json_params)

    def speechDetected(self, key, value, message):
        json_params = {}
        # The value should be True
        json_params["speechDetected"] = value
        send(-1, "int", json_params)

    def tabletMessage(self, key, message):
        json_params = {}
        json_params["tabletMessage"] = True
        send(-1, "int", json_params)

    def tabletError(self, key, message):
        json_params = {}
        json_params["tabletError"] = True
        send(-1, "int", json_params)

    def onInputText(self, key, message):
        json_params = {}
        # The value should be True
        json_params["onInputText"] = True
        send(-1, "int", json_params)

    def gesture(self, key, value, message):
        json_params = {}
        # The value is the name of the gesture
        json_params["gesture"] = value
        send(-1, "int", json_params)

    def speechTextDone(self, key, value, message):
        json_params = {}
        # The value should be True
        if activities_running.has_key("SAY"):
            activities_running.pop("SAY")
        json_params["speechTextDone"] = value
        send(-1, "int", json_params)

    def speechTextInterrupted(self, key, value, message):
        json_params = {}
        if activities_running.has_key("SAY"):
            activities_running.pop("SAY")
        # The value should be True
        json_params["speechTextInterrupted"] = value
        send(-1, "int", json_params)

    def voiceEmotionRecognized(self, key, value, message):
        json_params = {}
        # The value is the emotion detected
        json_params["voiceEmotionRecognized"] = value
        send(-1, "int", json_params)

    def autonomousCompletedActivity(self, key, value, message):
        json_params = {}
        # The value is the activity name
        json_params["autonomousCompletedActivity"] = value
        send(-1, "int", json_params)

    def hotDeviceDetected(self, key, value, message):
        json_params = {}
        # The value is a list of the devices with high temperature
        json_params["hotDeviceDetected"] = value
        send(-1, "int", json_params)

    def dialogLastInput(self, key, value, message):
        json_params = {}
        # The value is the last human input
        json_params["dialogLastInput"] = value
        send(-1, "int", json_params)

    def dialogIsStarted(self, key, value, message):
        json_params = {}
        # The value is "1" for start, "0" for stop.
        json_params["dialogIsStarted"] = value
        send(-1, "int", json_params)

    def dialogCurrentString(self, key, value, message):
        json_params = {}
        # The value is currently processed human input.
        json_params["dialogCurrentString"] = value
        send(-1, "int", json_params)

    def personMovedAway(self, key, value, message):
        json_params = {}
        # The value is the ID of the person
        json_params["personMovedAway"] = value
        send(-1, "int", json_params)

    def personApproached(self, key, value, message):
        json_params = {}
        # The value is the ID of the person
        json_params["personApproached"] = value
        send(-1, "int", json_params)

    def personSmiling(self, key, value, message):
        json_params = {}
        # The value is the ID of the person
        json_params["personSmiling"] = value
        send(-1, "int", json_params)

    def faceDetected(self, key, value, message):
        json_params = {}
        # The value is an ALvalue which contains the info of the face, but we aren't going to send that
        json_params["faceDetected"] = True
        send(-1, "int", json_params)

    def peopleLookingAtRobot(self, key, value, message):
        json_params = {}
        # The value is a list of IDs with people whose are looking the robot
        json_params["peopleLookingAtRobot"] = value
        send(-1, "int", json_params)

    def personStopsLookingAtRobot(self, key, value, message):
        json_params = {}
        # The value is the person ID
        json_params["personStopsLookingAtRobot"] = value
        send(-1, "int", json_params)

    def distanceOfTrackedHuman(self, key, value, message):
        json_params = {}
        # The value is the distance in meters to the tracked human. -1.0 if no one is tracked.
        json_params["distanceOfTrackedHuman"] = value
        # send(-1, "int", json_params)

    def obstacleDetected(self, key, value, message):
        json_params = {}
        # The value is an array formatted as [x, y], representing the position of the detected obstacle
        json_params["position"] = value
        send(-1, "int", json_params)

    def peopleDetected(self, key, value, message):
        json_params = {}
        # The value has the information of the detected people and is organizated as follow MovementInfo =
        # [
        # [TimeStamp_Seconds, TimeStamp_Microseconds],
        # [PersonData_1, PersonData_2, ... PersonData_n],
        # CameraPose_InTorsoFrame,
        # CameraPose_InRobotFrame,
        # Camera_Id
        # ]
        json_params["peopleDetected"] = value
        send(-1, "int", json_params)

    def preferenceAdded(self, key, value, message):
        json_params = {}
        # The value is the id - An array of two values identifying the preference:
        # id[0] - The preference domain.
        # id[1] - The preference name.
        json_params["preferenceAdded"] = value
        send(-1, "int", json_params)

    def preferenceChanged(self, key, value, message):
        json_params = {}
        # The value is the id - An array of two values identifying the preference:
        # id[0] - The preference domain.
        # id[1] - The preference name.
        json_params["preferenceChanged"] = value
        send(-1, "int", json_params)

    def wavingDetection(self, key, value, message):
        json_params = {}
        # The value contains the info of the waving (pixels and some info more), we won't send that
        json_params["wavingDetection"] = True
        send(-1, "int", json_params)

    def personWaving(self, key, value, message):
        json_params = {}
        # The value is the person ID
        json_params["personWaving"] = value
        send(-1, "int", json_params)

