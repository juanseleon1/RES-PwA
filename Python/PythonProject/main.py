import socket
import threading
import json
import qi
import Utils
import sys
import argparse
from Utils.Message import messageManager
from ServiceDispatcher.Robot import Robot
from Utils.Utils import activities_running, send, callbacks_running


# ------------------------------------- -------------Functions-----------------------------------------------------------------------
def timer_callbacks():
    # Execute all callback functions to know if all callback-activities are finished
    for key, function in callbacks_running.items():
        function()

    threading.Timer(1.0, timer_callbacks).start()


def timer_activities():
    for key, value in activities_running.items():
        send(value.getIdResponse(), value.getResponseType(), value.getParams())

    threading.Timer(10.0, timer_activities).start()


def safe_str(obj):
    try:
        return str(obj)
    except UnicodeEncodeError:
        return obj.encode(FORMAT, 'ignore').decode(FORMAT)


def receive_request(robot):
    while 1:
        global conn
        conn, addr = server.accept()
        thread = threading.Thread(target=handle_client)
        thread.start()


def handle_client():
    # print(" ID: ", threading.currentThread().getName())
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
    # y = "{" + y
    # print("Pepperoni: "+json_string)
    # print(y)
    jsonObj = json.loads(json_string)
    # msg_length = len(jsonObj)
    # msg = conn.recv((msg_length)).decode(FORMAT, 'ignore')

    callFunction(jsonObj)


def my_callback(choice, robot_1):
    if choice == "Aumentar Estado Emocional":

        if robot_1.joy < 1:
            robot_1.joy += 0.1
        if robot_1.sorrow > -1:
            robot_1.sorrow -= 0.1
    elif choice == "Bajar Estado Emocional":
        if robot_1.joy > -1:
            robot_1.joy -= 0.1
        if robot_1.sorrow < 1:
            robot_1.sorrow += 0.1
    elif choice == "Aumentar Relajacion":
        if robot_1.ease < 1:
            robot_1.ease += 0.1
    elif choice == "Bajar Relajacion":
        if robot_1.ease > -1:
            robot_1.ease -= 0.1
    elif choice == "Aumentar Atencion":
        if robot_1.attention < 1:
            robot_1.attention += 0.1
    elif choice == "Bajar Atencion":
        if robot_1.attention > -1:
            robot_1.attention -= 0.1


def callFunction(jsonObj):
    function = robot.getFunction(jsonObj["methodName"])
    params = jsonObj["params"]

    if params is None:
        return_value = function()
    else:
        return_value = function(params)

    if robot.getAck(jsonObj["methodName"]):
        ack_param = {}
        if return_value is not None:
            ack_param[jsonObj["methodName"]] = return_value
        else:
            ack_param[jsonObj["methodName"]] = True

        send(jsonObj["id"], robot.getType(jsonObj["methodName"]), ack_param)

    response = robot.mustBeResponse(jsonObj["methodName"])

    if response is True:
        response_type = robot.getType(jsonObj["methodName"])
        robot_activity = messageManager(jsonObj["id"], response_type)
        activity_params = {jsonObj["methodName"]: True}
        robot_activity.setParams(activity_params)
        activities_running[jsonObj["methodName"]] = robot_activity


# ----------------------------------------------------------------------------MAIN---------------------------------------------------------------------------------------------
"""---------------------------------------------------------------------------MAIN---------------------------------------------------------------------------------------------"""
# ----------------------------------------------------------------------------MAIN---------------------------------------------------------------------------------------------
print("Server starting...pop")
HOST = '10.195.22.105'  # socket.gethostbyname(socket.gethostname()) # Standard loopback interface             address (localhost)
HOST_LOCAL = '127.0.0.1'
print("Server starting on", HOST_LOCAL)
PORT = 7896  # Port to listen on (non-privileged ports are > 1023)
print("Server starting...pop0000000000000000")
ADDR = (HOST_LOCAL, PORT)
server = None
HEADER = 10000
FORMAT = 'utf-8'

# send( "id", "ROB", True)
# ------------------------------------------------------------------------------------------------
parser = argparse.ArgumentParser()
parser.add_argument("--ip", type=str, default=HOST, help="Robot IP address. On robot or Local Naoqi: use '127.0.0.1'.")
parser.add_argument("--port", type=int, default=9559, help="Naoqi port number")
args = parser.parse_args()

try:
    connection_url = "tcp://" + args.ip + ":" + str(args.port)
    app = qi.Application(["ResPwa", "--qi-url=" + connection_url])
    app.start()
    session = app.session
except RuntimeError:
    print ("Can't connect to Naoqi at ip \"" + args.ip + "\" on port " + str(args.port) + ".\n"
                                                                                          "Please check your script arguments. Run with -h option for help.")
    sys.exit(1)
print("Server starting...pop11111111111111111112")
# ----------------------------------------------------------------------------------------------------

# Declare the Naoqi variables --------------------------------------------------------------------

# ----------------------------------------------------------------------------------------------------
# ----------------------------------------------------------------------------------------------------

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
"""----------------------------------------------TIMER---------------------------------------------------------"""
# define Timer to inform BESA
t = threading.Timer(10.0, timer_activities)
t.start()
""" Robot class declaration"""
robot = Robot(app, session)
kthread = Utils.KeyboardThread(input_cbk=my_callback, robot=robot)
receive_request(robot)
