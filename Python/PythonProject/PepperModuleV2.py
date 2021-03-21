# ----------------------------------------------------------------------------MODULE---------------------------------------------------------------------------------------------
from Utils import activities_running, send


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
        self.topicInputSub.signal.connect(self.activateTopic)

        self.dialogIsStartedS = self.alProxy.subscriber("Dialog/IsStarted")
        self.dialogIsStartedS.signal.connect(self.dialogIsStarted)

        self.dialogCurrentStringS = self.alProxy.subscriber("Dialog/CurrentString")
        self.dialogCurrentStringS.signal.connect(self.dialogCurrentString)

        self.alDialogProxy.subscribe("pepperModuleV2")

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


        print("ENTRE AL MODULO")

    def activateTopic(self, value):
        json_params = {}
        json_params["ActivateTopic"] = True
        send(-1, "rob", json_params)
    # def

    # Raised when an animated speech is done.
    def endOfAnimatedSpeech(self, value):
        if activities_running.has_key("SAYWITHMOVEMENT"):
            activities_running.pop("SAYWITHMOVEMENT")
        json_params = {}
        json_params["endOfAnimatedSpeech"] = True
        send(-1, "int", json_params)

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
        send(-1, "int", json_params)

    def batteryLow(self, value):
        json_params = {}
        # Supposedly the value always is True
        json_params["batteryLow"] = value
        send(-1, "rob", json_params)

    def goToFailed(self, value):
        json_params = {}
        # The value should be True
        json_params["goToFailed"] = value
        send(-1, "rob", json_params)

    def goToSuccess(self, value):
        json_params = {}
        # The value should be True
        json_params["goToSuccess"] = value
        send(-1, "rob", json_params)

    def goToLost(self, value):
        json_params = {}
        # The value should be True
        json_params["goToLost"] = value
        send(-1, "rob", json_params)

    def localizeSuccess(self, value):
        json_params = {}
        # The value should be True
        json_params["localizeSuccess"] = value
        send(-1, "rob", json_params)

    def localizeLost(self, value):
        json_params = {}
        # The value should be True
        json_params["localizeLost"] = value
        send(-1, "rob", json_params)

    def localizeDirectionLost(self, value):
        json_params = {}
        # The value should be True
        json_params["localizeDirectionLost"] = value
        send(-1, "rob", json_params)

    def localizeDirectionSuccess(self, value):
        json_params = {}
        # The value should be True
        json_params["localizeDirectionSuccess"] = value
        send(-1, "rob", json_params)

    def chainVelocityClipped(self, value):
        json_params = {}
        # The value should be True
        json_params["chainVelocityClipped"] = value
        send(-1, "rob", json_params)

    def moveFailed(self, value):
        json_params = {}
        # The value should be True
        if activities_running.has_key("MOVE"):
            activities_running.pop("MOVE")

        json_params["moveFailed"] = value
        send(-1, "int", json_params)

    def robotIsWakeUp(self, value):
        json_params = {}
        # The value should be True
        json_params["robotIsWakeUp"] = value
        send(-1, "int", json_params)

    def wakeUpFinished(self, value):
        json_params = {}
        # The value should be True
        json_params["wakeUpFinished"] = value
        send(-1, "int", json_params)

    def restFinished(self, value):
        json_params = {}
        # The value should be True
        json_params["restFinished"] = value
        send(-1, "int", json_params)

    def disabledDevicesChanged(self, value):
        json_params = {}
        # The value is a map with the devices of the robot whose are able/disabled
        json_params["disabledDevicesChanged"] = value
        send(-1, "int", json_params)

    # def disabledFeaturesChanged(self, value):
    #     json_params = {}
    #     # The value is a map with the features of the robot whose are able/disabled
    #     json_params["disabledFeaturesChanged"] = value
    #     send(-1, "int", json_params)

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

    def wordRecognized(self, value):
        json_params = {}
        # The value is a map with the word recognized
        json_params["wordRecognized"] = value
        send(-1, "int", json_params)

    def speechDetected(self, value):
        json_params = {}
        # The value should be True
        json_params["speechDetected"] = value
        send(-1, "int", json_params)

    # def tabletMessage(self, key, message):
    #     json_params = {}
    #     json_params["tabletMessage"] = True
    #     send(-1, "int", json_params)

    def tabletError(self, key, message):
        json_params = {}
        json_params["tabletError"] = True
        send(-1, "int", json_params)

    # def onInputText(self, key, message):
    #     json_params = {}
    #     # The value should be True
    #     json_params["onInputText"] = True
    #     send(-1, "int", json_params)

    # def gesture(self, value):
    #     json_params = {}
    #     # The value is the name of the gesture
    #     json_params["gesture"] = value
    #     send(-1, "int", json_params)

    def speechTextDone(self, value):
        json_params = {}
        # The value should be True
        if activities_running.has_key("SAY"):
            activities_running.pop("SAY")
        json_params["speechTextDone"] = value
        send(-1, "int", json_params)

    def speechTextInterrupted(self, value):
        json_params = {}
        if activities_running.has_key("SAY"):
            activities_running.pop("SAY")
        # The value should be True
        json_params["speechTextInterrupted"] = value
        send(-1, "int", json_params)

    def voiceEmotionRecognized(self, value):
        json_params = {}
        # The value is the emotion detected
        json_params["voiceEmotionRecognized"] = value
        send(-1, "emo", json_params)

    # def autonomousCompletedActivity(self, value):
    #     json_params = {}
    #     # The value is the activity name
    #     json_params["autonomousCompletedActivity"] = value
    #     send(-1, "int", json_params)

    def hotDeviceDetected(self, value):
        json_params = {}
        # The value is a list of the devices with high temperature
        json_params["hotDeviceDetected"] = True
        send(-1, "int", json_params)

    def dialogLastInput(self, value):
        json_params = {}
        # The value is the last human input
        json_params["dialogLastInput"] = value
        send(-1, "int", json_params)

    def dialogIsStarted(self, value):
        json_params = {}
        # The value is "1" for start, "0" for stop.
        json_params["dialogIsStarted"] = value
        send(-1, "int", json_params)

    def dialogCurrentString(self, value):
        json_params = {}
        # The value is currently processed human input.
        json_params["dialogCurrentString"] = value
        send(-1, "int", json_params)

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
        #send(-1, "int", json_params)

    def peopleLookingAtRobot(self, value):
        json_params = {}
        # The value is a list of IDs with people whose are looking the robot
        json_params["peopleLookingAtRobot"] = True
        send(-1, "int", json_params)

    def personStopsLookingAtRobot(self, value):
        json_params = {}
        # The value is the person ID
        json_params["personStopsLookingAtRobot"] = True
        send(-1, "int", json_params)

    def distanceOfTrackedHuman(self, value):
        json_params = {}
        # The value is the distance in meters to the tracked human. -1.0 if no one is tracked.
        if ( value is not -1):
            json_params["distanceOfTrackedHuman"] = value
            send(-1, "int", json_params)

    def obstacleDetected(self, value):
        json_params = {}
        # The value is an array formatted as [x, y], representing the position of the detected obstacle
        json_params["position"] = value
        send(-1, "int", json_params)

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
        json_params["peopleDetected"] = value
        #send(-1, "int", json_params)

    # def preferenceAdded(self, value):
    #     json_params = {}
    #     # The value is the id - An array of two values identifying the preference:
    #     # id[0] - The preference domain.
    #     # id[1] - The preference name.
    #     json_params["preferenceAdded"] = value
    #     send(-1, "int", json_params)

    # def preferenceChanged(self, value):
    #     json_params = {}
    #     # The value is the id - An array of two values identifying the preference:
    #     # id[0] - The preference domain.
    #     # id[1] - The preference name.
    #     json_params["preferenceChanged"] = value
    #     send(-1, "int", json_params)

    # def wavingDetection(self, value):
    #     json_params = {}
    #     # The value contains the info of the waving (pixels and some info more), we won't send that
    #     json_params["wavingDetection"] = True
    #     send(-1, "int", json_params)

    def personWaving(self, value):
        json_params = {}
        # The value is the person ID
        json_params["personWaving"] = True
        send(-1, "int", json_params)

    def getDialogInput(self, value):
        # The value is the last human input
        if value:
            print "enviar", value
            json_params = {"DialogInput": value}
            send(-1, "int", json_params)

