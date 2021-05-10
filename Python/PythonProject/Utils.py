# activities_running is a dictionary which save the activities running on the robot
import json
from datetime import datetime
from socket import socket, AF_INET, SOCK_STREAM

activities_running = {}
callbacks_running = {}

# responsesXTime is a dictionary with the responses and the time of each one, to make a restriction of the number of responses
# ed to BESA
responsesXTime = dict()


def json_creator(id_response, responseType, params):
    json_string = {
        "id": id_response,
        "respType": responseType,
        "params": params
    }
    # Check if response is an emotional element of the robot
    if isAnEmotionalAck(params):
        json_string["hasEmo"] = True
    return json.loads(json.dumps(json_string))


def send(id_response, responseType, params):
    HOST_LOCAL = '127.0.0.1'
    PORT = 7897
    FORMAT = 'utf-8'
    should_send_message = True
    key = params.keys().pop()
    if key in responsesXTime:
        print "key: ", key
        should_send_message = checkTimeMessageSended(key)
    else:
        responsesXTime[key] = datetime.now()

    if should_send_message:
        print "ENVIANDO"
        ADDR = (HOST_LOCAL, PORT)
        client = socket(AF_INET, SOCK_STREAM)
        client.connect(ADDR)
        msg_to_send = json.dumps(json_creator(id_response, responseType, params))
        print("send ", msg_to_send)

        client.send(msg_to_send + '\r\n')
        client.close()

def isAnEmotionalAck(params):
    encontrado = False
    emotionalAck = [
        "peopleDetected",
        "personStopsLookingAtRobot",
        "personMovedAway",
        "speechDetected"
    ]
    for i in emotionalAck:
        if params.__contains__(i):
            encontrado = True

    return encontrado

def checkTimeMessageSended(params):
    isCorrectToSend = True
    # print("PARAMS: " + str( responsesXTime.get( params ) ))
    if (responsesXTime.get(params).hour - datetime.now().hour) < 1:

        if (responsesXTime.get(params).minute - datetime.now().minute) < 2:

            if (abs(datetime.now().second - responsesXTime.get(params).second)) < 5:
                print("Change")
                isCorrectToSend = False

            if (abs(datetime.now().second - responsesXTime.get(params).second)) > 20:
                #print("Erase")
                isCorrectToSend = False
                deleteExpiredAction( params )

    return isCorrectToSend

def deleteExpiredAction( expiredAction ):
    if activities_running and (expiredAction in activities_running.keys()):
        activities_running.pop( expiredAction )
