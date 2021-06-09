# ----------------------------------------------------------------------------MODULE---------------------------------------------------------------------------------------------
from Utils.Utils import activities_running, send
import re


# ----------------------------------------------------------------------------MODULE---------------------------------------------------------------------------------------------
# create python module


class pepperModuleV2(object):
    """python class myModule test auto documentation: comment needed to create a new python module"""
    """python class myModule test auto documentation: comment needed to create a new python module"""

    def __init__(self, session):
        super(pepperModuleV2, self).__init__()
        self.session = session

        self.alProxy = session.service("ALMemory")
        self.alDialogProxy = session.service("ALDialog")

        self.topicInputSub = self.alProxy.subscriber("Dialog/LastInput")
        self.topicInputSub.signal.connect(self.getDialogInput)

        self.topicActivate = self.alProxy.subscriber("Dialog/ActivateTopic")
        self.topicActivate.signal.connect(self.activateTopic)

        self.topicDeact = self.alProxy.subscriber("Dialog/DeactivateTopic")
        self.topicDeact.signal.connect(self.deactivateTopic)

        self.dialogIsStartedS = self.alProxy.subscriber("Dialog/IsStarted")
        self.dialogIsStartedS.signal.connect(self.dialogIsStarted)

        self.dialogCurrentStringS = self.alProxy.subscriber("Dialog/CurrentString")
        self.dialogCurrentStringS.signal.connect(self.dialogCurrentString)

        #   self.alDialogProxy.subscribe("pepperModuleV2")

        self.sayDoneSub = self.alProxy.subscriber("ALTextToSpeech/TextDone")
        self.sayDoneSub.signal.connect(self.speechTextDone)

        self.endAnimSpeech = self.alProxy.subscriber("ALAnimatedSpeech/EndOfAnimatedSpeech")
        self.endAnimSpeech.signal.connect(self.endOfAnimatedSpeech)

        self.noHuman = self.alProxy.subscriber("ALBasicAwareness/HumanLost")
        self.noHuman.signal.connect(self.humanLost)

        self.trackedPerson = self.alProxy.subscriber("ALBasicAwareness/HumanTracked")
        self.trackedPerson.signal.connect(self.humanTracked)

        self.lowBat = self.alProxy.subscriber("ALBattery/BatteryLow")
        self.lowBat.signal.connect(self.batteryLow)

        self.stimuDetected = self.alProxy.subscriber("ALBasicAwareness/StimulusDetected")
        self.stimuDetected.signal.connect(self.stimulusDetected)

        self.fallaGoTo = self.alProxy.subscriber("ALLocalization/GoToFailed")
        self.fallaGoTo.signal.connect(self.goToFailed)

        self.bienGoTo = self.alProxy.subscriber("ALLocalization/GoToSuccess")
        self.bienGoTo.signal.connect(self.goToSuccess)

        self.goToLostS = self.alProxy.subscriber("ALLocalization/GoToLost")
        self.goToLostS.signal.connect(self.goToLost)

        self.localizeSuccessS = self.alProxy.subscriber("ALLocalization/LocalizeSuccess")
        self.localizeSuccessS.signal.connect(self.localizeSuccess)

        self.localizeLostS = self.alProxy.subscriber("ALLocalization/LocalizeLost")
        self.localizeLostS.signal.connect(self.localizeLost)

        self.localizeDirectionLostS = self.alProxy.subscriber("ALLocalization/LocalizeDirectionLost")
        self.localizeDirectionLostS.signal.connect(self.localizeDirectionLost)

        self.localizeDirectionSuccessS = self.alProxy.subscriber("ALLocalization/LocalizeDirectionSuccess")
        self.localizeDirectionSuccessS.signal.connect(self.localizeDirectionSuccess)

        self.chainVelocityClippedS = self.alProxy.subscriber("ALMotion/Safety/ChainVelocityClipped")
        self.chainVelocityClippedS.signal.connect(self.chainVelocityClipped)

        self.moveFailedS = self.alProxy.subscriber("ALMotion/MoveFailed")
        self.moveFailedS.signal.connect(self.moveFailed)

        self.robotIsWakeUpS = self.alProxy.subscriber("robotIsWakeUp")
        self.robotIsWakeUpS.signal.connect(self.robotIsWakeUp)

        self.wakeUpFinishedS = self.alProxy.subscriber("ALMotion/Stiffness/wakeUpFinished")
        self.wakeUpFinishedS.signal.connect(self.wakeUpFinished)

        self.restFinishedS = self.alProxy.subscriber("ALMotion/Stiffness/restFinished")
        self.restFinishedS.signal.connect(self.restFinished)

        self.disabledDevicesChangedS = self.alProxy.subscriber("ALMotion/Protection/DisabledDevicesChanged")
        self.disabledDevicesChangedS.signal.connect(self.disabledDevicesChanged)

        # self.disabledFeaturesChangedS = self.alProxy.subscriber("ALMotion/Protection/DisabledFeaturesChanged")
        # self.disabledFeaturesChangedS.signal.connect(self.disabledFeaturesChanged)

        self.connectedToChargingStationS = self.alProxy.subscriber("ALRecharge/ConnectedToChargingStation")
        self.connectedToChargingStationS.signal.connect(self.connectedToChargingStation)

        self.moveFailedRechargingS = self.alProxy.subscriber("ALRecharge/MoveFailed")
        self.moveFailedRechargingS.signal.connect(self.moveFailedRecharging)

        self.leaveFailedS = self.alProxy.subscriber("ALRecharge/LeaveFailed")
        self.leaveFailedS.signal.connect(self.leaveFailed)

        self.wordRecognizedS = self.alProxy.subscriber("WordRecognized")
        self.wordRecognizedS.signal.connect(self.wordRecognized)

        self.speechDetectedS = self.alProxy.subscriber("SpeechDetected")
        self.speechDetectedS.signal.connect(self.speechDetected)

        self.tabletErrorS = self.alProxy.subscriber("ALTabletService/error")
        self.tabletErrorS.signal.connect(self.tabletError)

        # self.tabletMessageS = self.alProxy.subscriber("ALTabletService/message")
        # self.tabletMessageS.signal.connect(self.tabletMessage)

        tabletService = session.service("ALTabletService")
        tabletService.videoFinished.connect(self.tabletVideoFinished)

        # self.onInputTextS = self.alProxy.subscriber("ALTabletService/onInputText")
        # self.onInputTextS.signal.connect(self.onInputText)

        # self.gestureS = self.alProxy.subscriber("ALTactileGesture/Gesture")
        # self.gestureS.signal.connect(self.gesture)

        self.speechTextInterruptedS = self.alProxy.subscriber("ALTextToSpeech/TextInterrupted")
        self.speechTextInterruptedS.signal.connect(self.speechTextInterrupted)

        self.voiceEmotionRecognizedS = self.alProxy.subscriber("ALVoiceEmotionAnalysis/EmotionRecognized")
        self.voiceEmotionRecognizedS.signal.connect(self.voiceEmotionRecognized)

        self.hotDeviceDetectedS = self.alProxy.subscriber("HotDeviceDetected")
        self.hotDeviceDetectedS.signal.connect(self.hotDeviceDetected)

        self.personMovedAwayS = self.alProxy.subscriber("EngagementZones/PersonMovedAway")
        self.personMovedAwayS.signal.connect(self.personMovedAway)

        self.personApproachedS = self.alProxy.subscriber("EngagementZones/PersonApproached")
        self.personApproachedS.signal.connect(self.personApproached)

        self.personSmilingS = self.alProxy.subscriber("FaceCharacteristics/PersonSmiling")
        self.personSmilingS.signal.connect(self.personSmiling)

        self.faceDetectedS = self.alProxy.subscriber("FaceDetected")
        self.faceDetectedS.signal.connect(self.faceDetected)

        self.peopleLookingAtRobotS = self.alProxy.subscriber("GazeAnalysis/PeopleLookingAtRobot")
        self.peopleLookingAtRobotS.signal.connect(self.peopleLookingAtRobot)

        self.personStopsLookingAtRobotS = self.alProxy.subscriber("GazeAnalysis/PersonStopsLookingAtRobot")
        self.personStopsLookingAtRobotS.signal.connect(self.personStopsLookingAtRobot)

        self.distanceOfTrackedHumanS = self.alProxy.subscriber("Launchpad/DistanceOfTrackedHuman")
        self.distanceOfTrackedHumanS.signal.connect(self.distanceOfTrackedHuman)

        self.obstacleDetectedS = self.alProxy.subscriber("Navigation/AvoidanceNavigator/ObstacleDetected")
        self.obstacleDetectedS.signal.connect(self.obstacleDetected)

        self.peopleDetectedS = self.alProxy.subscriber("PeoplePerception/PeopleDetected")
        self.peopleDetectedS.signal.connect(self.peopleDetected)

        self.wavingDetectionS = self.alProxy.subscriber("WavingDetection/Waving")
        self.wavingDetectionS.signal.connect(self.personWaving)

        self.personWavingS = self.alProxy.subscriber("WavingDetection/PersonWaving")
        self.personWavingS.signal.connect(self.personWaving)
        self.retroalimentacionCompleta = ""
    

    def activateTopic(self, event_name):
        json_params = {}
        json_params["ActivateTopic"] = event_name
        print "activateTopic", json_params
        send(-1, "int", json_params, False)

    def deactivateTopic(self, event_name):
        json_params = {}
        json_params["DeactivateTopic"] = event_name
        print "DeactivateTopic", json_params
        send(-1, "int", json_params, False)



    # Raised when an animated speech is done.
    def endOfAnimatedSpeech(self, value):
        if activities_running.has_key("SAYWITHMOVEMENT"):
            activities_running.pop("SAYWITHMOVEMENT")

        json_params = {}
        json_params["endOfAnimatedSpeech"] = True
        send(-1, "int", json_params, False)

    # Raised when the person tracked can no longer be found for some time.
    def humanLost(self, value):
        json_params = {}
        json_params["humanLost"] = True
        send(-1, "int", json_params)

    # Raised when the robot begins to track a person, when the tracked person is lost, or when the tracked person's ID is
    def humanTracked(self, value):
        json_params = {}
        # The value is The ID of the currently tracked person. If no person is tracked, the value is -1.
        json_params["humanTracked"] = value
        send(-1, "int", json_params)

    def stimulusDetected(self, value):
        json_params = {}
        #  The value is the name of the stimulus detected.
        json_params["stimulusDetected"] = value
        send(-1, "int", json_params, False)

    def batteryLow(self, value):
        json_params = {}
        # Supposedly the value always is True
        json_params["batteryLow"] = value
        send(-1, "rob", json_params, False)

    def goToFailed(self, value):
        json_params = {}
        # The value should be True
        json_params["goToFailed"] = value
        send(-1, "rob", json_params, False)

    def goToSuccess(self, value):
        json_params = {}
        # The value should be True
        json_params["goToSuccess"] = value
        send(-1, "rob", json_params, False)

    def goToLost(self, value):
        json_params = {}
        # The value should be True
        json_params["goToLost"] = value
        send(-1, "rob", json_params, False)

    def localizeSuccess(self, value):
        json_params = {}
        # The value should be True
        json_params["localizeSuccess"] = value
        send(-1, "rob", json_params, False)

    def localizeLost(self, value):
        json_params = {}
        # The value should be True
        json_params["localizeLost"] = value
        send(-1, "rob", json_params, False)

    def localizeDirectionLost(self, value):
        json_params = {}
        # The value should be True
        json_params["localizeDirectionLost"] = value
        send(-1, "rob", json_params, False)

    def localizeDirectionSuccess(self, value):
        json_params = {}
        # The value should be True
        json_params["localizeDirectionSuccess"] = value
        send(-1, "rob", json_params, False)

    def chainVelocityClipped(self, value):
        json_params = {}
        # The value should be True
        json_params["chainVelocityClipped"] = value
        send(-1, "rob", json_params, False)

    def moveFailed(self, value):
        json_params = {}
        # The value should be True
        if activities_running.has_key("MOVE"):
            activities_running.pop("MOVE")

        json_params["moveFailed"] = value
        send(-1, "int", json_params, False)

    def robotIsWakeUp(self, value):
        json_params = {}
        # The value should be True
        json_params["robotIsWakeUp"] = value
        send(-1, "int", json_params, False)

    def wakeUpFinished(self, value):
        json_params = {}
        # The value should be True
        json_params["wakeUpFinished"] = value
        send(-1, "int", json_params, False)

    def restFinished(self, value):
        json_params = {}
        # The value should be True
        json_params["restFinished"] = value
        send(-1, "int", json_params, False)

    def disabledDevicesChanged(self, value):
        json_params = {}
        # The value is a map with the devices of the robot whose are able/disabled
        json_params["disabledDevicesChanged"] = value
        send(-1, "int", json_params, False)

    # def disabledFeaturesChanged(self, value):
    #     json_params = {}
    #     # The value is a map with the features of the robot whose are able/disabled
    #     json_params["disabledFeaturesChanged"] = value
    #     send(-1, "int", json_params, False)

    def connectedToChargingStation(self, key, message):
        json_params = {}
        json_params["connectedToChargingStation"] = True
        send(-1, "rob", json_params, False)

    def moveFailedRecharging(self, key, message):
        json_params = {}
        if activities_running.has_key("MOVE"):
            activities_running.pop("MOVE")
        json_params["moveFailedRecharging"] = True
        send(-1, "int", json_params, False)

    def leaveFailed(self, key, message):
        json_params = {}
        json_params["leaveFailed"] = True
        send(-1, "int", json_params, False)

    def wordRecognized(self, value):
        json_params = {}
        # The value is a map with the word recognized
        json_params["wordRecognized"] = value
        send(-1, "int", json_params, False)

    def speechDetected(self, value):
        json_params = {}
        # The value should be True
        json_params["speechDetected"] = value
        send(-1, "int", json_params, False)

    # def tabletMessage(self, key, message):
    #     json_params = {}
    #     json_params["tabletMessage"] = True
    #     send(-1, "int", json_params, False)
    def tabletVideoFinished(self):
        json_params = {}
        # The value should be True
        json_params["endVideo"] = True
        print "EntraTabletVideoFinished"
        send(-1, "int", json_params, False)

    def onWifiStatusChange(self, wifi_status):
        json_params = {}
        # WiFi status among IDLE, SCANNING, DISCONNECTED, CONNECTED
        json_params["wifi_status"] = wifi_status
        print "EntraWifiChangeFinished"
        send(-1, "rob", json_params, False)

    def tabletError(self, key, message):
        json_params = {}
        json_params["tabletError"] = True
        send(-1, "int", json_params, False)

    # def onInputText(self, key, message):
    #     json_params = {}
    #     # The value should be True
    #     json_params["onInputText"] = True
    #     send(-1, "int", json_params, False)

    # def gesture(self, value):
    #     json_params = {}
    #     # The value is the name of the gesture
    #     json_params["gesture"] = value
    #     send(-1, "int", json_params, False)

    def speechTextDone(self, value):
        json_params = {}
        # The value should be True
        if activities_running.has_key("SAY"):
            activities_running.pop("SAY")
        print "MAFE ES FEA", bool(value), value
        json_params["speechTextDone"] = value
        send(-1, "int", json_params, False)

    def speechTextInterrupted(self, value):
        json_params = {}
        if activities_running.has_key("SAY"):
            activities_running.pop("SAY")
        # The value should be True
        json_params["speechTextInterrupted"] = value
        send(-1, "int", json_params, False)

    def voiceEmotionRecognized(self, value):
        json_params = {}
        # The value is the emotion detected
        json_params["voiceEmotionRecognized"] = value
        send(-1, "emo", json_params, False)

    # def autonomousCompletedActivity(self, value):
    #     json_params = {}
    #     # The value is the activity name
    #     json_params["autonomousCompletedActivity"] = value
    #     send(-1, "int", json_params, False)

    def hotDeviceDetected(self, value):
        json_params = {}
        # The value is a list of the devices with high temperature
        json_params["hotDeviceDetected"] = True
        send(-1, "int", json_params, False)

    def dialogLastInput(self, value):
        json_params = {}
        # The value is the last human input
        json_params["dialogLastInput"] = value
        print "DIALOGINPUT", value
        send(-1, "int", json_params, False)

    def dialogIsStarted(self, value):
        json_params = {}
        # The value is "1" for start, "0" for stop.
        json_params["dialogIsStarted"] = value
        send(-1, "int", json_params, False)

    def dialogCurrentString(self, value):
        json_params = {}
        # The value is currently processed human input.
        json_params["dialogCurrentString"] = value
        send(-1, "int", json_params, False)

    def personMovedAway(self, value):
        json_params = {}
        # The value is the ID of the person
        json_params["personMovedAway"] = True
        send(-1, "int", json_params)

    def personApproached(self, value):
        json_params = {}
        # The value is the ID of the person
        json_params["personApproached"] = True
        send(-1, "int", json_params)

    def personSmiling(self, value):
        json_params = {}
        # The value is the ID of the person
        json_params["personSmiling"] = True
        send(-1, "int", json_params)

    def faceDetected(self, value):
        json_params = {}
        # The value is an ALvalue which contains the info of the face, but we aren't going to send that
        json_params["faceDetected"] = True
        send(-1, "int", json_params, False)

    def peopleLookingAtRobot(self, value):
        json_params = {}
        # The value is a list of IDs with people whose are looking the robot
        json_params["peopleLookingAtRobot"] = True
        send(-1, "int", json_params, False)

    def personStopsLookingAtRobot(self, value):
        json_params = {}
        # The value is the person ID
        json_params["personStopsLookingAtRobot"] = True
        send(-1, "int", json_params)

    def distanceOfTrackedHuman(self, value):
        json_params = {}
        # The value is the distance in meters to the tracked human. -1.0 if no one is tracked.
        if (value is not -1):

            json_params["distanceOfTrackedHuman"] = value
            send(-1, "int", json_params)

    def obstacleDetected(self, value):
        json_params = {}
        # The value is an array formatted as [x, y], representing the position of the detected obstacle
        json_params["position"] = value
        send(-1, "int", json_params, False)

    def peopleDetected(self, value):
        json_params = {}
        # The value has the information of the detected people and is organizated as follow MovementInfo =
        # [
        # [TimeStamp_Seconds, TimeStamp_Microseconds],
        # [PersonData_1, PersonData_2, ... PersonData_n],
        # CameraPose_InTorsoFrame,
        # CameraPose_InRobotFrame,
        # Camera_Id
        # ]
        json_params["peopleDetected"] = True
        send(-1, "int", json_params)

    # def preferenceAdded(self, value):
    #     json_params = {}
    #     # The value is the id - An array of two values identifying the preference:
    #     # id[0] - The preference domain.
    #     # id[1] - The preference name.
    #     json_params["preferenceAdded"] = value
    #     send(-1, "int", json_params, False)

    # def preferenceChanged(self, value):
    #     json_params = {}
    #     # The value is the id - An array of two values identifying the preference:
    #     # id[0] - The preference domain.
    #     # id[1] - The preference name.
    #     json_params["preferenceChanged"] = value
    #     send(-1, "int", json_params, False)

    # def wavingDetection(self, value):
    #     json_params = {}
    #     # The value contains the info of the waving (pixels and some info more), we won't send that
    #     json_params["wavingDetection"] = True
    #     send(-1, "int", json_params, False)

    def personWaving(self, value):
        json_params = {}
        # The value is the person ID
        json_params["personWaving"] = True
        send(-1, "int", json_params, False)

    def sendValue(self, resultValue, sirve=True):

        print "enviar", resultValue
        if sirve:
            if "ayuda" in resultValue:
                json_params = {"ayudaValue": resultValue}
            else:
                json_params = {"DialogInput": resultValue}
        else:
            json_params = {"retroValue": resultValue}
        print "DialogInput", json_params
        send(-1, "int", json_params, False)

    def preferenceInput(self, completeSentence):
        resultValue = ""
        word = completeSentence
        if(len(word)>0):
            if ((len(re.findall(r'mu\w+', word[0])) == 1 or len(re.findall(r'sub\w+', word[0])) != 0) and (
                len(re.findall(r'brill\w+', word[1])) != 0)):
                resultValue = 'decrease brigthness'
            if (len(re.findall(r'baj\w+', word[0])) == 1 and len(re.findall(r'brill\w+', word[1])) != 0):
                resultValue = 'increase brigthness'
            if ((len(re.findall(r'baj\w+', word[0])) == 1 or len(re.findall(r'dur\w+', word[0])) != 0) and (
                len(re.findall(r'vol\w+', word[1])) != 0 or len(re.findall(r'dur\w+', word[1])) != 0)):
                resultValue = 'decrease volume'
            if ((len(re.findall(r'sub\w+', word[0])) == 1 or len(re.findall(r'nada\w+', word[0])) != 0) and (
                len(re.findall(r'vol\w+', word[1])) != 0 or len(re.findall(r'hab\w+', word[1])) != 0)):
                resultValue = 'decrease volume'
        if (resultValue == ""):
            return (False, "")
        return (True, resultValue)

    def retroalimentacionFilter(self, value):

        if value == 'uno' or value == 'dos' or value == 'tres' or value == 'cuatro' or  value == 'cinco':
            self.retroalimentacionCompleta += " " + value

        # EL 8 VARIA SEGuN LA CANTIDAD DE PREGUNTAS DE RETROALIMENTACION
        if (len(self.retroalimentacionCompleta.split()) == 3):

            return (True, self.retroalimentacionCompleta)
        else:
            return (False, "")

    def happyFilter(self, value):
        aux = ''
        if (len(value) == 2):
            aux = value[1]
        elif (len(value) == 3):
            aux = value[2]
        resulset = ''

        if len(re.findall(r'feli\w+', aux)) == 1 or \
                len(re.findall(r'alegr\w+', aux)) == 1 or \
                len(re.findall(r'emocion\w+', aux)) == 1 or \
                len(re.findall(r'feli\w+', aux)) == 1 or \
                len(re.findall(r'animad\w+', aux)) == 1 or \
                len(re.findall(r'chever\w+', aux)) == 1:
            resulset = value
        if (resulset != ''):
            return (True, resulset)
        else:
            return (False, "")

    def normalFilter(self, value):
        aux = ''
        if (len(value) == 2):
            aux = value[1]
        elif (len(value) == 3):
            aux = value[2]
        resulset = ''

        if len(re.findall(r'bien\w+', aux)) == 1 or \
                len(re.findall(r'norma\w+', aux)) == 1 or \
                len(re.findall(r'agrada\w+', aux)) == 1 or \
                len(re.findall(r'buen\w+', aux)) == 1:
            resulset = value
        if (resulset != ''):
            return (True, resulset)
        else:
            return (False, "")

    def sadFilter(self, value):
        resulset = ''
        aux = ''
        if (len(value) == 2):
            aux = value[1]
        elif (len(value) == 3):
            aux = value[2]
        if len(re.findall(r'aburrid\w+', aux)) == 1 or \
                len(re.findall(r'cansad\w+', aux)) == 1 or \
                len(re.findall(r'fastidia\w+', aux)) == 1 or \
                len(re.findall(r'mamad\w+', aux)) == 1 or \
                len(re.findall(r'mal\w+', aux)) == 1 or \
                len(re.findall(r'terribl\w+', aux)) == 1 or \
                len(re.findall(r'horrib\w+', aux)) == 1 or \
                len(re.findall(r'trist\w+', aux)) == 1 or \
                len(re.findall(r'adolid\w+', aux)) == 1 or \
                len(re.findall(r'melancol\w+', aux)) == 1:
            resulset = value

        if (resulset != ''):
            return (True, resulset)
        else:
            return (False, "")

    def angryFilter(self, value):
        resulset = ''
        aux = ''
        if (len(value) == 2):
            aux = value[1]
        elif (len(value) == 3):
            aux = value[2]
        elif (len(value) == 4):
            aux = value[2]

        if len(re.findall(r'cansad\w+', aux)) == 1 or \
                len(re.findall(r'molest\w+', aux)) == 1 or \
                len(re.findall(r'enojad\w+', aux)) == 1 or \
                len(re.findall(r'jodido\w+', aux)) == 1 or \
                len(re.findall(r'fasti\w+', aux)) == 1 or \
                len(re.findall(r'piedra\w+', aux)) == 1 or \
                len(re.findall(r'rabi\w+', aux)) == 1 or \
                len(re.findall(r'ira\w+', aux)) == 1 or \
                len(re.findall(r'furi\w+', aux)) == 1 or \
                len(re.findall(r'coler\w+', aux)) == 1 or \
                len(re.findall(r'vayase\w+', aux)) == 1 or \
                len(re.findall(r'vete\w+', aux)) == 1:
            resulset = value
        if (resulset != ''):
            return (True, resulset)
        else:
            return (False, "")

    def emotionalFilter(self, value):
        aux = ''

        happyFilter = self.happyFilter(value)
        normalFilter = self.normalFilter(value)
        sadFilter = self.sadFilter(value)
        angryFilter = self.angryFilter(value)

        if happyFilter[0]:
            return (True, "happy " + happyFilter[1])
        # Regex Normal '''
        elif normalFilter[0]:
            return (True, "normal " + normalFilter[1])
        # '''Regex sad '''
        elif sadFilter[0]:
            return (True, "sad " + sadFilter[1])
        # '''Regex angry '''
        elif angryFilter[0]:
            return (True, "angry " + angryFilter[1])
        else:
            return (False, "")

    def getDialogInput(self, value):
        # The value is the last human input
        # The function has been made for the comprehension of the human. In this case, is used to detect orders of adjust volume of pepper and bright of the tablet

        # Are all the filters possible in the code - they were simplyfied to get a better knowledge of
        # Prefences - Retroalimentation - emotion
        if value != "":
            preference = self.preferenceInput(value)
            retroAlimentacion = self.retroalimentacionFilter(value)
            emotion = self.emotionalFilter(value)
            print ("VALUE-AFTER: ", preference, retroAlimentacion, emotion )

            resultValue = preference[1] + retroAlimentacion[1] + emotion[1]
            print "Result Value ", resultValue
            if preference[0] or retroAlimentacion[0] or emotion[0]:
                self.sendValue(resultValue, False)
            else:
                self.sendValue(value)
