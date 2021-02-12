from naoqi import *
import socket
import threading
import json
import qi
import sys
import argparse
import datetime
from Emotion import Emotion
from Message import messageManager
import PepperModule
from Robot import Robot

#--------------------------------------------------Functions-----------------------------------------------------------------------
def send(id_response, responseType, params):
    HOST_LOCAL = '127.0.0.1'
    PORT = 7897
    FORMAT = 'utf-8'
    should_send_message = True
    if responsesXTime.has_key(params.keys()):
        should_send_message = checkTimeMessageSended(params.keys())
    else:
        responsesXTime[params.keys()] = {datetime.datetime.now()}

    if should_send_message:
        ADDR = (HOST_LOCAL, PORT)
        client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        client.connect(ADDR)
        msg_to_send = json.dumps(json_creator(id_response, responseType, params))
        print("send ", msg_to_send)

        client.send(msg_to_send + '\r\n')
        client.close()


def checkTimeMessageSended(params):
    isCorrectToSend = True
    if (responsesXTime.get(params).hour - datetime.datetime.now().hour) == 0:

        if (responsesXTime.get(params).minute - datetime.datetime.now().minute) == 0:

            if (datetime.datetime.now().second - responsesXTime.get(params).second) < 2:
                isCorrectToSend = False

        if (responsesXTime.get(params).minute - datetime.datetime.now().minute) == -1:

            if (datetime.datetime.now().second - responsesXTime.get(params).second) < 2:
                isCorrectToSend = False

    return isCorrectToSend

def timer_activities():
    for key, value in activities_running.items():
        # print (key, value)
        # create Json message
        # send the message to BESA
        send(value.getIdResponse(), value.getResponseType(), value.getParams())

    t = threading.Timer(10.0, timer_activities).start()

def safe_str(obj):
    try: return str(obj)
    except UnicodeEncodeError:
        return obj.encode(FORMAT, 'ignore').decode(FORMAT)
    return ""

def handle_client_init(conn, addr):
    print "Entre nigels"
    alFaceDetection.forgetPerson("Brayan")
    alFaceDetection.setRecognitionEnabled(True)
    alFaceDetection.learnFace("Brayan")


def handle_client(self, conn, addr):
    # while connected:
    msg_length = conn.recv(HEADER)
    msg_length = msg_length.decode(FORMAT, 'ignore')
    msg_length = safe_str(msg_length)
    # if msg_length:
    # print(msg_length)
    y = safe_str(msg_length).split('{')
    json_string = ""
    for val in range(1, len(y)):
        json_string = json_string + "{" + y[val]

    print(" msg:")
    # y = "{" + y
    print(json_string)
    # print(y)
    jsonObj = json.loads(json_string)
    # msg_length = len(jsonObj)
    # msg = conn.recv((msg_length)).decode(FORMAT, 'ignore')

    callFunction(jsonObj)
def json_creator(id_response, responseType, params):
    json_string = {
        "id": id_response,
        "respType": responseType,
        "params": params
    }
    return json.loads(json.dumps(json_string))


def callFunction(jsonObj):
    function = robot.getFunction(jsonObj["methodName"])
    params = jsonObj["params"]

    if params == None:
        return_value = function()
    else:
        return_value = function(params)

    if robot.getAck(jsonObj["methodName"]):
        ack_param = {}
        if return_value != None:
            ack_param[jsonObj["methodName"]] = return_value
        else:
            ack_param[jsonObj["methodName"]] = True

        send(jsonObj["id"], robot.getType(jsonObj["methodName"]), ack_param)

    response = robot.mustBeResponse(jsonObj["methodName"])

    if response == True:
        response_type = robot.getType(jsonObj["methodName"])
        robot_activity = messageManager(jsonObj["id"], response_type)
        activity_params = {}
        activity_params[jsonObj["methodName"]] = True
        robot_activity.setParams(activity_params)
        activities_running[jsonObj["methodName"]] = robot_activity




# ----------------------------------------------------------------------------MAIN---------------------------------------------------------------------------------------------
"""--------------------------------------------------------------------------MAIN---------------------------------------------------------------------------------------------"""
# ----------------------------------------------------------------------------MAIN---------------------------------------------------------------------------------------------

print("Server starting...pop")
HOST = '10.195.22.168'  # socket.gethostbyname(socket.gethostname()) # Standard loopback interface             address (localhost)
HOST_LOCAL = '127.0.0.1'
print("Server starting on", HOST_LOCAL)
PORT = 7896  # Port to listen on (non-privileged ports are > 1023)
print("Server starting...pop0000000000000000")
ADDR = (HOST_LOCAL, PORT)
server = None
HEADER = 1024
FORMAT = 'utf-8'
print("Server starting...pop1111111111111111111")
# send( "id", "ROB", True)
# ------------------------------------------------------------------------------------------------
parser = argparse.ArgumentParser()
parser.add_argument("--ip", type=str, default=HOST, help="Robot IP address. On robot or Local Naoqi: use '127.0.0.1'.")
parser.add_argument("--port", type=int, default=9559, help="Naoqi port number")
args = parser.parse_args()
session = qi.Session()
try:
    session.connect("tcp://" + args.ip + ":" + str(args.port))
except RuntimeError:
    print ("Can't connect to Naoqi at ip \"" + args.ip + "\" on port " + str(args.port) + ".\n"
                                                                                          "Please check your script arguments. Run with -h option for help.")
    sys.exit(1)

# ----------------------------------------------------------------------------------------------------

# Declare the Naoqi variables --------------------------------------------------------------------
alBroker = ALBroker("myBroker", "0.0.0.0", 7896, HOST, 9559)
alProxy = ALProxy("ALMemory")
alMood = session.service("ALMood")
alTexToSpeech = session.service("ALTextToSpeech")
alAnimationPlayer = session.service("ALAnimationPlayer")
alMotion = session.service("ALMotion")
alRobotPosture = session.service("ALRobotPosture")
alFaceDetection = session.service("ALFaceDetection")
alAutonomousBlinking = session.service("ALAutonomousBlinking")
alBackgroundMovement = session.service("ALBackgroundMovement")
alBasicAwareness = session.service("ALBasicAwareness")
alListeningMovement = session.service("ALListeningMovement")
alSpeakingMovementProxy = session.service("ALSpeakingMovement")
alMotionProxy = session.service("ALMotion")
alPeoplePerception = session.service("ALPeoplePerception")
alBatteryProxy = session.service("ALBattery")
alBodyTemperatureProxy = session.service("ALBodyTemperature")
alUserSession = session.service("ALUserSession")
alNavigationProxy = session.service("ALNavigation")
alLocalizationProxy = session.service("ALLocalization")
alSensorsProxy = session.service("ALSensors")
alLedsProxy = session.service("ALLeds")
alTabletService = session.service("ALTabletService")
alAnimatedSpeech = session.service("ALAnimatedSpeech")
alAudioDevice = session.service("ALAudioDevice")
alAudioPlayer = session.service("ALAudioPlayer")
alVoiceEmotionAnalysis = session.service("ALVoiceEmotionAnalysis")
alSpeechRecognition = session.service("ALSpeechRecognition")
alDialogProxy = session.service("ALDialog")

# ----------------------------------------------------------------------------------------------------
# ----------------------------------------------------------------------------------------------------
# Declare the modules --------------------------------------------------------------------------------
try:

    sensorsModule = PepperModule("sensorsModule")
    # Raised when an animated speech is done.
    alProxy.subscribeToEvent("ALAnimatedSpeech/EndOfAnimatedSpeech", "sensorsModule", "endOfAnimatedSpeech")
    # Raised when the person tracked can no longer be found for some time.
    alProxy.subscribeToEvent("ALBasicAwareness/HumanLost", "sensorsModule",
                             "humanLost")  # DEBE TENER DETECTADA UNA CARA PARA FUNCIONAR

    # Raised when the robot begins to track a person, when the tracked person is lost, or when the tracked person's ID is|
    alProxy.subscribeToEvent("ALBasicAwareness/HumanTracked", "sensorsModule", "humanTracked")

    # Raised when a stimulus is detected.
    # types of stimulus: http://doc.aldebaran.com/2-5/naoqi/interaction/autonomousabilities/albasicawareness.html#albasicawareness-stimuli-types
    alProxy.subscribeToEvent("ALBasicAwareness/StimulusDetected", "sensorsModule", "stimulusDetected")

    # Raised when the battery level is low and will soon need charging.
    alProxy.subscribeToEvent("ALBattery/BatteryLow", "sensorsModule",
                             "batteryLow")  # DEBE TENER LA BATERiA BAJA PARA FUNCIONAR

    # Raised when the robot could not reach its destination, either because it was lost or because it was interrupted by an obstacle.
    alProxy.subscribeToEvent("ALLocalization/GoToFailed", "sensorsModule", "goToFailed")  # NO MUESTRA NADA -

    # Raised when the robot has successfully reached its destination.
    alProxy.subscribeToEvent("ALLocalization/GoToSuccess", "sensorsModule", "goToSuccess")

    # Raised when the robot gets lost while trying to go to its destination.
    alProxy.subscribeToEvent("ALLocalization/GoToLost", "sensorsModule", "goToLost")

    # Raised when the localization is successful.
    alProxy.subscribeToEvent("ALLocalization/LocalizeSuccess", "sensorsModule", "localizeSuccess")

    # Raised when the localization fails and the robot is lost.
    alProxy.subscribeToEvent("ALLocalization/LocalizeLost", "sensorsModule", "localizeLost")

    # Raised when the orientation of the robot has NOT been successfully retrieved.
    alProxy.subscribeToEvent("ALLocalization/LocalizeDirectionLost", "sensorsModule", "localizeDirectionLost")

    # Raised when the orientation of the robot has been successfully retrieved.
    alProxy.subscribeToEvent("ALLocalization/LocalizeDirectionSuccess", "sensorsModule", "localizeDirectionSuccess")

    # Raised when a chain velocity is clipped because an obstacle is too close.
    alProxy.subscribeToEvent("ALMotion/Safety/ChainVelocityClipped", "sensorsModule", "chainVelocityClipped")

    # Raised when a move command fails.
    alProxy.subscribeToEvent("ALMotion/MoveFailed", "sensorsModule", "moveFailed")

    # Raised when the awake status of the robot changes.
    alProxy.subscribeToEvent("robotIsWakeUp", "sensorsModule", "robotIsWakeUp")

    # Raised at ALMotionProxy::wakeUp finish.
    alProxy.subscribeToEvent("ALMotion/Stiffness/wakeUpFinished", "sensorsModule", "wakeUpFinished")
    # Raised at ALMotionProxy::rest finish.
    alProxy.subscribeToEvent("ALMotion/Stiffness/restFinished", "sensorsModule", "restFinished")

    # Raised when devices availability changed. When a device is not available the stiffness and movement on this device are prohibited.
    alProxy.subscribeToEvent("ALMotion/Protection/DisabledDevicesChanged", "sensorsModule", "disabledDevicesChanged")

    # Raised when features (Move, Stiffness...) availability changed.
    alProxy.subscribeToEvent("ALMotion/Protection/DisabledFeaturesChanged", "sensorsModule", "disabledFeaturesChanged")

    # Raised when Pepper is correctly docked onto the charging station.
    alProxy.subscribeToEvent("ALRecharge/ConnectedToChargingStation", "sensorsModule", "connectedToChargingStation")

    # Raised when Pepper interrupts his operation because a safety rule prevents the usage of ALMotion module.
    alProxy.subscribeToEvent("ALRecharge/MoveFailed", "sensorsModule", "moveFailedRecharging")

    # Raised when Pepper failed to leave his charging station due to an obstacle in the way.
    alProxy.subscribeToEvent("ALRecharge/LeaveFailed", "sensorsModule", "leaveFailed")

    # Raised when one of the specified words set with ALSpeechRecognitionProxy::setVocabulary has been recognized. When no word is currently recognized, this value is reinitialized.
    alProxy.subscribeToEvent("WordRecognized", "sensorsModule", "wordRecognized")

    # Raised when the automatic speech recognition engine has detected a voice activity.
    alProxy.subscribeToEvent("SpeechDetected", "sensorsModule", "speechDetected")

    # Raised when an error occurs.
    alProxy.subscribeToEvent("ALTabletService/error", "sensorsModule", "tabletError")

    # Raised when message occurs.
    alProxy.subscribeToEvent("ALTabletService/message", "sensorsModule", "tabletMessage")

    # Raised when text input occurs.
    alProxy.subscribeToEvent("ALTabletService/onInputText", "sensorsModule", "onInputText")

    # Raised when a valid tactile gesture has been detected
    alProxy.subscribeToEvent("ALTactileGesture/Gesture", "sensorsModule", "gesture")

    # Raised when the current sentence synthesis is done.
    alProxy.subscribeToEvent("ALTextToSpeech/TextDone", "sensorsModule", "speechTextDone")

    # Raised when the current sentence synthesis is interrupted, for example by ALTextToSpeechProxy::stopAll.
    alProxy.subscribeToEvent("ALTextToSpeech/TextInterrupted", "sensorsModule", "speechTextInterrupted")
    # Raised when an utterance has been analyzed.
    alProxy.subscribeToEvent("ALVoiceEmotionAnalysis/EmotionRecognized", "sensorsModule", "voiceEmotionRecognized")
    # Raised whenever an activity completes its execution and exits.
    alProxy.subscribeToEvent("AutonomousLife/CompletedActivity", "sensorsModule", "autonomousCompletedActivity")

    # Revisar esto para ver si se va a colocar!!!!!!!!!!!!!!!!!!!!!!!!

    # Raised when the robot touch status changed.
    alProxy.subscribeToEvent("TouchChanged", "sensorsModule", "pythondatachanged")  ###########################

    # Raised when at least one device (joint, actuator, sensor) has a high temperature.
    alProxy.subscribeToEvent("HotDeviceDetected", "sensorsModule", "hotDeviceDetected")
    # Raised each time the robot catches a human input. Contains the last human input.
    alProxy.subscribeToEvent("Dialog/LastInput", "sensorsModule", "dialogLastInput")
    # Raised when the dialog engine starts or stops. The value is "1" for start, "0" for stop.
    alProxy.subscribeToEvent("Dialog/IsStarted", "sensorsModule", "dialogIsStarted")
    # Currently processed human input.
    alProxy.subscribeToEvent("Dialog/CurrentString", "sensorsModule", "dialogCurrentString")
    # Raised when a person just moved away from the robot (i.e. moved to a further engagement zone).
    alProxy.subscribeToEvent("EngagementZones/PersonMovedAway", "sensorsModule", "personMovedAway")
    # Raised when a person just approached the robot (i.e. moved to a closer engagement zone).
    alProxy.subscribeToEvent("EngagementZones/PersonApproached", "sensorsModule", "personApproached")
    # Raised when a person has a smile value above the current threshold (default = 0.7).
    alProxy.subscribeToEvent("FaceCharacteristics/PersonSmiling", "sensorsModule", "personSmiling")
    # #Raised when one or several faces are currently being detected.
    alProxy.subscribeToEvent("FaceDetected", "sensorsModule", "faceDetected")
    # Raised each time the list of people looking at the robot changes.
    alProxy.subscribeToEvent("GazeAnalysis/PeopleLookingAtRobot", "sensorsModule", "peopleLookingAtRobot")
    # Raised when someone turns his head away from the robot.
    alProxy.subscribeToEvent("GazeAnalysis/PersonStopsLookingAtRobot", "sensorsModule", "personStopsLookingAtRobot")
    # The distance in meters to the tracked human. -1.0 if no one is tracked.
    alProxy.subscribeToEvent("Launchpad/DistanceOfTrackedHuman", "sensorsModule", "distanceOfTrackedHuman")
    # Raised when an obstacle is detected in the close area.
    alProxy.subscribeToEvent("Navigation/AvoidanceNavigator/ObstacleDetected", "sensorsModule", "obstacleDetected")
    # Raised whenever at least one person is visible by the robot. Contains information about the detected people, it is used by ALTracker to track people.
    alProxy.subscribeToEvent("PeoplePerception/PeopleDetected", "sensorsModule", "peopleDetected")
    # Raised when a new preference is added to the system.
    alProxy.subscribeToEvent("preferenceAdded", "sensorsModule", "preferenceAdded")
    # Raised when the value of a preference has been updated.
    alProxy.subscribeToEvent("preferenceChanged", "sensorsModule", "preferenceChanged")
    # Raised when something just waved at the robot.
    alProxy.subscribeToEvent("WavingDetection/Waving", "sensorsModule", "wavingDetection")
    # Raised when someone just waved at the robot.
    alProxy.subscribeToEvent("WavingDetection/PersonWaving", "sensorsModule", "personWaving")
    #
except Exception, e:
    print "Main Error"
    print e
    alBroker.shutdown()
    exit(1)
# ----------------------------------------------------------------------------------------------------
server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

print("Server starting...pop22222222222222222222222")
ADDR = (HOST_LOCAL, PORT)
print("Server starting...pop3333333333333333333333333")
server.bind(ADDR)
print("Server starting...pop4444444444444444444444444444")
server.listen(5)
print("[STARTING] server is listening on", HOST_LOCAL)

# activities_running is a dictionary which save the activities running on the robot
activities_running = {}

# responsesXTime is a dictionary with the responses and the time of each one, to make a restriction of the number of responses sended to BESA
responsesXTime = {}

"""----------------------------------------------TIMER---------------------------------------------------------"""
# define Timer to inform BESA
t = threading.Timer(10.0, timer_activities)
t.start()

""" Robot class declaration"""
robot = Robot()

''' Emotion class declaration '''
emotionStateRobot = Emotion()

while 1:
    conn, addr = server.accept()
    thread = threading.Thread(target=handle_client, args=(conn, addr))
    thread.start()