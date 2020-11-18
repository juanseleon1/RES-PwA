from naoqi import *
import socket
import threading
import json
import qi
import sys
import argparse

#---------------------------------------------Global Variables-----------------------------------------------
global alBroker
global activities_running
global robot
global emotionStateRobot

def timer_activities():
    
    for key, value in activities_running.items():
        if value == True:
            print (key, value)
            #create Json message
            #send the message to BESA
            send( -1, key, value)
            
    t = threading.Timer(10.0, timer_activities).start()

def handle_client(conn, addr):
    #while connected:
    msg_length = conn.recv(HEADER)
    msg_length = msg_length.decode(FORMAT, 'ignore')
    msg_length = safe_str(msg_length)
    #if msg_length:
    #print(msg_length)
    y = safe_str(msg_length).split('{')
    json_string = ""
    for val in range(1, len(y)):
        json_string = json_string + "{" + y[val]
    
    print(" msg:")
    #y = "{" + y
    print(json_string)
    #print(y)
    jsonObj = json.loads(json_string)
    #msg_length = len(jsonObj)
    #msg = conn.recv((msg_length)).decode(FORMAT, 'ignore')
    
    callFunction(jsonObj)
    
    #if learn_face("Brayan"):
    #hablar(jsonObj["methodName"],100)
    #hablar(jsonObj["methodName"],50)
    #else:
    #    hablar("Mal")
    #print(get_face_list()[0])
    #json_formatted_str = json.dumps(json_creator(-1, "emo", get_emotion_state()), indent=2)
    #print(json_formatted_str)
    #activate_blinking(True)+++++++++++++
    #activate_life_signals(True)+++++++++++++
    #activate_life_signals_awareness(True)+++++++++++++
    #set_engagement_type("FullyEngaged")+++++++++++++
    #activate_hearing_movement(True)+++++++++++++
    #activate_speak_movements(True)+++++++++++++
    #define_conversation_mode("random")+++++++++++++
    #activate_push_reflexes(False) IGNORADA POR AHORA, PARA PROBARLA HAY QUE DESACTIVAR LOS REFLEJOS DE SEGURIDAD PRIMERO
    #activate_breath_movement("Body", True)+++++++++++++
    #activate_movement_detection(False)+++++++++++++
    #activate_face_detection(True)+++++++++++++
    #activate_colission_detection("Arms", True)+++++++++++++
    #activate_monitoring_charge_service(True)+++++++++++++
    #print("Battery", get_battery())+++++++++++++
    #print("Temperature", get_temperature())+++++++++++++
    #print("Smile", get_emotion_state()) +++++++++++++
    #print(login()) -------------
    #search_free_zone(0.6, 0.5) +++++++++++++
    #get_free_zone(0.6, 0.5) +++++++++++++
    #get_robot_position(False) +++++++++++++
    #move(0.25,0.25,0.1)+++++++++++++
    #move_forward(0.2, 0.2, 0.1)+++++++++++++
    #move_to(2.0, 0.0)---------------------
    #move_to_position(2.0)
    #wake_up()++++++++++++++++++++++                                                               ########
    #suspend()++++++++++++++++++++++                                                               ########
    #activate_rasta(2.0f)
    #random_eyes(2.0f)
    #set_leds_intensity("LeftFaceLedsGreen", 0.5)++++++++++++++++++++++                              ########
    #change_led_color("AllLeds", 0, 0, 0, 0.5 )                             ########
    activate_stiffness(True)

    #pause_sound(idSound)
    #play_sound("D:\ASUS\Music\Proyectos de video\when-stars-and-salt-collide-coldplay-a-sky-full-of-stars-pianocello-cover-the-piano-guys.mp3")
    ############################alAudioPlayer.playWebStream("https://www.youtube.com/watch?v=3DZP00zvW74", 0.7, 0.0)
    #set_system_volume(20)#+++++++++++++++++++
    #say_with_movement("Hola Enrique")
    #set_say_volume(0.75)+++++++++++++++++++
    #stop_all()---------------------
    #say(jsonObj["methodName"],100, 1.1)++++++++++++++++++++++++++++++
    #tablet_on()#++++++++++++++++++++++++++++++
    #wake_tablet()++++++++++++++++++++++++++++++
    #suspend_tablet()++++++++++++++++++++++++++++++
    #tablet_off()
    #show_video("https://redirector.googlevideo.com/videoplayback?expire=1604472485&ei=RfqhX4T1GarQ8gSr4qmYDg&ip=104.152.209.78&id=o-AORe7I9sVz175SndJTjmbzo4R18jnu53gU_o1Jx3NfMS&itag=18&source=youtube&requiressl=yes&mh=Fr&mm=31%2C29&mn=sn-cx5o4aqj5-tt1e%2Csn-ab5szn7e&ms=au%2Crdu&mv=u&mvi=2&pl=26&vprv=1&mime=video%2Fmp4&gir=yes&clen=12461468&ratebypass=yes&dur=190.217&lmt=1580700039115886&mt=1604450593&fvip=2&c=WEB&txp=5531432&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl&lsig=AG3C_xAwRQIgTuadH3UvqXe9vgd7PxkCck_D7-SUu_wkoa-drKKjM10CIQCftFm5NAyngyV1h3LioLNUfgPvRih4KMoABwlurfbCAQ%3D%3D&sig=AOq0QJ8wRQIhANhJISV3QgaoKFkvVbKm1jN-5L1ADp6Ebvl6K1-nEbMTAiAt2alrXgiZxkv9OOo8lb0n9UQ_43yl0tv92_mhPh1Zsw==")
    #quit_video()
    #pause_video()++++++++++++++++++++++++++++++
    #resume_video()++++++++++++++++++++++++++++++
    #preload_image("https://firebasestorage.googleapis.com/v0/b/respwa.appspot.com/o/imagenes%2Fcagejpeg.jpeg?alt=media")++++++++++++++++++++++++++++++
    #show_image("https://firebasestorage.googleapis.com/v0/b/respwa.appspot.com/o/imagenes%2Fcagejpeg.jpeg?alt=media")++++++++++++++++++++++++++++++
    #hide_image()++++++++++++++++++++++++++++++
    #set_tablet_bright(20)#---------------------------
    #set_tablet_volume(20)#---------------------------

    #if jsonObj["methodName"] == "hablar":
        #   print(jsonObj["methodName"])
    
def message_manage(key):
        switch_accion = {
            #ActivityServices-------------------------------------------------------
            "RUNANIMATION": run_animation, #funcionando
            "GOTOPOSTURE": go_to_posture,  #No hace nada-------------------------
            "DETECTNEWFACE ": learn_face,  #funcionando
            "GETFACELIST": get_face_list,  #funcionando
            "ACTIVATE": activate_blinking, #Parece que funciona, no bota error
            "ACTIVATELIFESIGNALS": activate_life_signals, #
            "ACTIVATELIFESGINALSINT": activate_life_signals_awareness, #
            "DEFENGAGEMENTTYPE": set_engagement_type, #
            "ACTIVATEACTIVEHEARING": activate_hearing_movement,#
            "ACTIVATESPEAKMOVEMENTS": activate_speak_movements, #
            "DEFCONVERSATIONMODE": define_conversation_mode, #
            "ACTIVATEPUSHREFLEXES": activate_push_reflexes, #
            "ACIVATEBREATHMOV": activate_breath_movement, #
            "ACTIVATEMOVDETECTION": activate_movement_detection, #
            "ACTIVATEFACEDETEC": activate_face_detection, #
            "ACTIVATECOLISSIONDETECT": activate_colission_detection, #
            #EnergyServices-------------------------------------------------------
            "ACTIVATEMONITORINGCHARGESERV": activate_monitoring_charge_service, #
            "GETBATTERY": get_battery, #
            "GETTEMP": get_temperature,#
            #HumanServices-------------------------------------------------------
            "GETEMOTIONSTATE": get_emotion_state, #
            "LOGIN": login, #
            #LocationServices-------------------------------------------------------
            "SEARCHFREEZONE": search_free_zone, #
            "GETFREEZONES": get_free_zone, #
            "GETROBOTPOSITION": get_robot_position, #
            #MovementServices-------------------------------------------------------
            "MOVE": move, #
            "MOVEFORWARD": move_forward, #
            "MOVETO": move_to, #
            "MOVETOPOSITION":move_to_position, #
            #RobotStateServices-------------------------------------------------------
            "WAKEUP": wake_up, #
            "SUSPEND": suspend, #
            "SETREFRESHTIMESENSORS": set_refresh_time_sensors, #Hay que crear un modulo para probar
            "ACTIVATERASTA ": activate_rasta, #
            "RANDOMEYES": random_eyes,#
            "SETLEDSINTENSITY": set_leds_intensity,#
            "CHANGELEDCOLOR": change_led_color,#
            "ACTIVATESTIFFNESS": activate_stiffness,#
            #TabletServices-------------------------------------------------------
            "TABLETON": tablet_on,
            "WAKETABLET": wake_tablet,
            "SUSPENDTABLET": suspend_tablet,
            "TABLETOFF": tablet_off,
            "SHOWVIDEO": show_video,
            "QUITVIDEO": quit_video,
            "PAUSEVIDEO": pause_video,
            "RESUMEVIDEO": resume_video,
            "PRELOADIMG": preload_image,
            "SHOWIMG": show_image,
            "HIDEIMG": hide_image,
            "SETTABLETBRIGHT": set_tablet_bright,
            "SETTABLETVOL": set_tablet_volume,
            #VoiceServices-------------------------------------------------------
            "SAY": say,
            "STOPALL": stop_all,
            "SETSAYVOLUMN": set_say_volume,
            "SAYWITHMOVEMENT": say_with_movement,
            "SETSYSTEMVOLUME": set_system_volume,
            "PLAYSOUND": play_sound,
            "PAUSESOUND": pause_sound,
            "ACTIVATEVOICEEMOANAL": activate_voice_emotion_analysis,
            "DESACTIVVOICEEMOANAL": desactivate_voice_emotion_analysis,
            "ACTVOICERECOG": activate_voice_recognition,
            "DESACTVOICERECOG": desactivate_voice_recognition,
            "ACTIVATECONVTOPIC": activate_conversational_topic,
            "LOADCONVTOPIC": load_conversational_topic,
            "UNLOADCONVTOPIC": unload_conversational_topic,
            "DEACTCONVTOPIC": deactivate_conversational_topic,
            "SAYUNDERTOPICCONTEXT": say_under_topic_context,
            "SETTOPICFOCUS": set_topic_focus
        }
        return switch_accion.get(key)
        
def ack_manage(key):
        switch_ack = {
            #ActivityServices-------------------------------------------------------
            "RUNANIMATION": True, #funcionando
            "DETECTNEWFACE ": True,  #funcionando
            "ACTIVATE": True, #Parece que funciona, no bota error
            "ACTIVATELIFESIGNALS": True, #
            "ACTIVATELIFESGINALSINT": True, #
            "ACTIVATEACTIVEHEARING": True,#
            "ACTIVATESPEAKMOVEMENTS": True, #
            "DEFCONVERSATIONMODE": True, #
            "ACTIVATEPUSHREFLEXES": True, #
            "ACIVATEBREATHMOV": True, #
            "ACTIVATEMOVDETECTION": True, #
            "ACTIVATEFACEDETEC": True, #
            "ACTIVATECOLISSIONDETECT": True, #
            #EnergyServices-------------------------------------------------------
            "ACTIVATEMONITORINGCHARGESERV": True, #
            #MovementServices-------------------------------------------------------
            "MOVE": True, #
            "MOVEFORWARD": True, #
            "MOVETO": True, #
            "MOVETOPOSITION":True, #
            #RobotStateServices-------------------------------------------------------
            "WAKEUP": True, #
            "SUSPEND": True, #
            "SETLEDSINTENSITY": True,#
            "CHANGELEDCOLOR": True,#
            "ACTIVATESTIFFNESS": True,#
            #TabletServices-------------------------------------------------------
            "TABLETON": True,
            "WAKETABLET": True,
            "SUSPENDTABLET": True,
            "TABLETOFF": True,
            #"SHOWVIDEO": show_video,
            #"QUITVIDEO": quit_video,
            #"PAUSEVIDEO": pause_video,
            #"RESUMEVIDEO": resume_video,
            #"PRELOADIMG": preload_image,
            "SHOWIMG": True,
            "HIDEIMG": True,
            "SETTABLETBRIGHT": True,
            "SETTABLETVOL": True,
            #VoiceServices-------------------------------------------------------
            "SAY": True,
            "STOPALL": True,
            "SETSAYVOLUMN": True,
            "SAYWITHMOVEMENT": True,
            "SETSYSTEMVOLUME": True,
            "PLAYSOUND": True,
            "PAUSESOUND": True,
            "ACTIVATEVOICEEMOANAL": True,
            "DESACTIVVOICEEMOANAL": True,
            "ACTVOICERECOG": True,
            "DESACTVOICERECOG": True,
            "ACTIVATECONVTOPIC": True,
            "LOADCONVTOPIC": True,
            "UNLOADCONVTOPIC": True,
            "DEACTCONVTOPIC": True,
            "SAYUNDERTOPICCONTEXT": True,
            "SETTOPICFOCUS": True
        }
        return switch_ack.get(key, False)

def responseTypeBESAFunction(function):

    switch_response = {
            #ActivityServices-------------------------------------------------------
            "RUNANIMATION": "act", #funcionando
            "GOTOPOSTURE": "act",  #No hace nada-------------------------
            "DETECTNEWFACE ": "int",  #funcionando
            "GETFACELIST": "int",  #funcionando
            "ACTIVATE": "act", #Parece que funciona, no bota error
            "ACTIVATELIFESIGNALS": "act", #
            "ACTIVATELIFESGINALSINT": "act", #
            "DEFENGAGEMENTTYPE": "int", #
            "ACTIVATEACTIVEHEARING": "act",#
            "ACTIVATESPEAKMOVEMENTS": "act", #
            "DEFCONVERSATIONMODE": "int", #
            "ACTIVATEPUSHREFLEXES": "act", #
            "ACIVATEBREATHMOV": "act", #
            "ACTIVATEMOVDETECTION": "act", #
            "ACTIVATEFACEDETEC": "act", #
            "ACTIVATECOLISSIONDETECT": "act", #
            #EnergyServices-------------------------------------------------------
            "ACTIVATEMONITORINGCHARGESERV": "act", #
            "GETBATTERY": "act", #
            "GETTEMP": "act",#
            #HumanServices-------------------------------------------------------
            "GETEMOTIONSTATE": "emo", #
            "LOGIN": "act", #
            #LocationServices-------------------------------------------------------
            "SEARCHFREEZONE": "act", #
            "GETFREEZONES": "act", #
            "GETROBOTPOSITION": "act", #
            #MovementServices-------------------------------------------------------
            "MOVE": "act", #
            "MOVEFORWARD": "act", #
            "MOVETO": "act", #
            "MOVETOPOSITION":"act", #
            #RobotStateServices-------------------------------------------------------
            "WAKEUP": "act", #
            "SUSPEND": "act", #
            "SETREFRESHTIMESENSORS": "act", #Hay que crear un modulo para probar
            "ACTIVATERASTA ": "act", #
            "RANDOMEYES": "act",#
            "SETLEDSINTENSITY": "act",#
            "CHANGELEDCOLOR": "act",#
            "ACTIVATESTIFFNESS": "act",#
            #TabletServices-------------------------------------------------------
            "TABLETON": "act",
            "WAKETABLET": "act",
            "SUSPENDTABLET": "act",
            "TABLETOFF": "act",
            "SHOWVIDEO": "act",
            "QUITVIDEO": "act",
            "PAUSEVIDEO": "act",
            "RESUMEVIDEO": "act",
            "PRELOADIMG": "act",
            "SHOWIMG": "act",
            "HIDEIMG": "act",
            "SETTABLETBRIGHT": "act",
            "SETTABLETVOL": "act",
            #VoiceServices-------------------------------------------------------
            "SAY": "act",
            "STOPALL": "act",
            "SETSAYVOLUMN": "act",
            "SAYWITHMOVEMENT": "act",
            "SETSYSTEMVOLUME": "act",
            "PLAYSOUND": "act",
            "PAUSESOUND": "act",
            "ACTIVATEVOICEEMOANAL": "act",
            "DESACTIVVOICEEMOANAL": "act",
            "ACTVOICERECOG": "act",
            "DESACTVOICERECOG": "act",
            "ACTIVATECONVTOPIC": "act",
            "LOADCONVTOPIC": "act",
            "UNLOADCONVTOPIC": "act",
            "DEACTCONVTOPIC": "act",
            "SAYUNDERTOPICCONTEXT": "act",
            "SETTOPICFOCUS": "act"
        }
    return switch_response.get(function)

def json_creator(id_response, responseType, params):
    json_string = {
        "id" : id_response ,
        "respType": responseType,
        "params":params       
    }
    return json.loads(json.dumps(json_string))

def callFunction(jsonObj):

    function = robot.getFunction(jsonObj["methodName"])
    params = jsonObj["params"]

    if params == None:
        function()
    elif function != None:
        function(params)
        
    if robot.getAck(jsonObj["methodName"]):
        ack_param = {}
        ack_param[jsonObj["methodName"]] = True
        send( jsonObj["id"], robot.getType(jsonObj["methodName"]), ack_param)

    response_type = robot.getType(jsonObj["methodName"])

    if response_type != None:
        robot_activity = messageManager(jsonObj["id"], response_type)
        activities_running[jsonObj["methodName"]] = robot_activity
    

def run_animation( params ):
    animation_name = params.get("TAGSDANCE")
    animation_factor = params.get("FACTOR")
    animation_name(animation_factor)
    
def getEmotionalReaction():
    #Returns:	The detected reaction.
    return alMood.getEmotionalReaction()

def getAttention():
    #"unengaged": attentionLevel < 0.6
    #"semiEngaged" : 0.6 <= attentionLevel < 0.9
    #"fullyEngaged": attentionLevel >= 0.9
    return alMood.attention()
    
# Choregraphe bezier export in Python.
def dance_macarena( factor = 1 ):
    # Choregraphe bezier export in Python.
    names = list()
    times = list()
    keys = list()

    names.append("HeadPitch")
    times.append([0, 1.16, 2.36, 4.76, 5.96, 8.36, 9.56, 11.96, 14.36, 16.36])
    keys.append([[-0.211185, [3, -0.0133333, 0], [3, 0.386667, 0]], [-0.211185, [3, -0.386667, 0], [3, 0.4, 0]], [-0.211185, [3, -0.4, 0], [3, 0.8, 0]], [0.123918, [3, -0.8, 0], [3, 0.4, 0]], [0.123918, [3, -0.4, 0], [3, 0.8, 0]], [0.445059, [3, -0.8, 0], [3, 0.4, 0]], [0.123918, [3, -0.4, 0], [3, 0.8, 0]], [0.123918, [3, -0.8, 0], [3, 0.8, 0]], [0.123918, [3, -0.8, 0], [3, 0.666667, 0]], [0.123918, [3, -0.666667, 0], [3, 0, 0]]])

    names.append("HeadYaw")
    times.append([0, 1.16, 2.36, 4.76, 5.96, 8.36, 9.56, 11.96, 13.16, 14.36, 16.36])
    keys.append([[-0.00698132, [3, -0.0133333, 0], [3, 0.386667, 0]], [0.219911, [3, -0.386667, 0], [3, 0.4, 0]], [-0.00698132, [3, -0.4, 0.064965], [3, 0.8, -0.12993]], [-0.364774, [3, -0.8, 0], [3, 0.4, 0]], [-0.0174533, [3, -0.4, -0.00523599], [3, 0.8, 0.010472]], [-0.00698132, [3, -0.8, 0], [3, 0.4, 0]], [-0.00698132, [3, -0.4, 0], [3, 0.8, 0]], [0.329867, [3, -0.8, 0], [3, 0.4, 0]], [-0.118682, [3, -0.4, 0], [3, 0.4, 0]], [0.127409, [3, -0.4, 0], [3, 0.666667, 0]], [-0.0314159, [3, -0.666667, 0], [3, 0, 0]]])

    names.append("HipPitch")
    times.append([0, 1.16, 8.36])
    keys.append([[-0.0357826, [3, -0.0133333, 0], [3, 0.386667, 0]], [-0.0474347, [3, -0.386667, 0], [3, 2.4, 0]], [0, [3, -2.4, 0], [3, 0, 0]]])

    names.append("HipRoll")
    times.append([0, 1.16, 8.36])
    keys.append([[-0.0041018, [3, -0.0133333, 0], [3, 0.386667, 0]], [-0.00654055, [3, -0.386667, 0], [3, 2.4, 0]], [-0.00523599, [3, -2.4, 0], [3, 0, 0]]])

    names.append("KneePitch")
    times.append([0, 1.16, 8.36])
    keys.append([[-0.0133719, [3, -0.0133333, 0], [3, 0.386667, 0]], [-0.0163339, [3, -0.386667, 0], [3, 2.4, 0]], [0, [3, -2.4, 0], [3, 0, 0]]])

    names.append("LElbowRoll")
    times.append([0, 1.16, 2.36, 4.76, 9.56, 13.16, 14.36, 16.36])
    keys.append([[-1.56207, [3, -0.0133333, 0], [3, 0.386667, 0]], [-0.00872665, [3, -0.386667, 0], [3, 0.4, 0]], [-0.00872665, [3, -0.4, 0], [3, 0.8, 0]], [-0.00872665, [3, -0.8, 0], [3, 1.6, 0]], [-0.00872665, [3, -1.6, 0], [3, 1.2, 0]], [-1.37357, [3, -1.2, 0.120428], [3, 0.4, -0.0401426]], [-1.41372, [3, -0.4, 0], [3, 0.666667, 0]], [-1.41372, [3, -0.666667, 0], [3, 0, 0]]])

    names.append("LElbowYaw")
    times.append([0, 1.16, 2.36, 3.56, 4.76, 9.56, 13.16, 14.36, 16.36])
    keys.append([[-0.118682, [3, -0.0133333, 0], [3, 0.386667, 0]], [-0.118682, [3, -0.386667, 0], [3, 0.4, 0]], [-0.118682, [3, -0.4, 0], [3, 0.4, 0]], [0.722566, [3, -0.4, 0], [3, 0.4, 0]], [0.722566, [3, -0.4, 0], [3, 1.6, 0]], [-1.80816, [3, -1.6, 0], [3, 1.2, 0]], [-0.197222, [3, -1.2, 0], [3, 0.4, 0]], [-0.830777, [3, -0.4, 0.0115192], [3, 0.666667, -0.0191987]], [-0.849975, [3, -0.666667, 0], [3, 0, 0]]])

    names.append("LHand")
    times.append([0, 1.16, 2.36, 3.56])
    keys.append([[0.02, [3, -0.0133333, 0], [3, 0.386667, 0]], [0.2, [3, -0.386667, 0], [3, 0.4, 0]], [0.2, [3, -0.4, 0], [3, 0.4, 0]], [0.87, [3, -0.4, 0], [3, 0, 0]]])

    names.append("LShoulderPitch")
    times.append([0, 1.16, 2.36, 3.56, 4.76, 9.56, 13.16, 14.36, 16.36])
    keys.append([[1.32994, [3, -0.0133333, 0], [3, 0.386667, 0]], [1.7558, [3, -0.386667, 0], [3, 0.4, 0]], [-1.22173, [3, -0.4, 0], [3, 0.4, 0]], [0.0837758, [3, -0.4, 0], [3, 0.4, 0]], [0.0837758, [3, -0.4, 0], [3, 1.6, 0]], [0.0837758, [3, -1.6, 0], [3, 1.2, 0]], [0.0837758, [3, -1.2, 0], [3, 0.4, 0]], [-0.289725, [3, -0.4, 0], [3, 0.666667, 0]], [1.69821, [3, -0.666667, 0], [3, 0, 0]]])

    names.append("LShoulderRoll")
    times.append([0, 1.16, 2.36, 3.56, 4.76, 9.56, 13.16, 14.36, 16.36])
    keys.append([[0.792379, [3, -0.0133333, 0], [3, 0.386667, 0]], [0.792379, [3, -0.386667, 0], [3, 0.4, 0]], [0.00872665, [3, -0.4, 0], [3, 0.4, 0]], [0.198968, [3, -0.4, 0], [3, 0.4, 0]], [0.198968, [3, -0.4, 0], [3, 1.6, 0]], [0.198968, [3, -1.6, 0], [3, 1.2, 0]], [0.0226893, [3, -1.2, 0], [3, 0.4, 0]], [0.525344, [3, -0.4, -0.00837757], [3, 0.666667, 0.0139626]], [0.539307, [3, -0.666667, 0], [3, 0, 0]]])

    names.append("LWristYaw")
    times.append([0, 1.16, 2.36, 4.76, 9.56, 13.16, 14.36])
    keys.append([[-0.708604, [3, -0.0133333, 0], [3, 0.386667, 0]], [-0.708604, [3, -0.386667, 0], [3, 0.4, 0]], [-0.708604, [3, -0.4, 0], [3, 0.8, 0]], [-0.708604, [3, -0.8, 0], [3, 1.6, 0]], [-0.708604, [3, -1.6, 0], [3, 1.2, 0]], [-0.301942, [3, -1.2, -0.0314158], [3, 0.4, 0.0104719]], [-0.29147, [3, -0.4, 0], [3, 0, 0]]])

    names.append("RElbowRoll")
    times.append([0, 1.16, 4.76, 5.96, 10.76, 11.96, 15.56, 17.56])
    keys.append([[1.56207, [3, -0.0133333, 0], [3, 0.386667, 0]], [1.56207, [3, -0.386667, 0], [3, 1.2, 0]], [0.00872665, [3, -1.2, 0], [3, 0.4, 0]], [0.00872665, [3, -0.4, 0], [3, 1.6, 0]], [0.00872665, [3, -1.6, 0], [3, 0.4, 0]], [1.41372, [3, -0.4, 0], [3, 1.2, 0]], [1.41372, [3, -1.2, 0], [3, 0.666667, 0]], [1.41372, [3, -0.666667, 0], [3, 0, 0]]])

    names.append("RElbowYaw")
    times.append([0, 1.16, 4.76, 5.96, 7.16, 10.76, 11.96, 14.36, 15.56, 17.56])
    keys.append([[0.118682, [3, -0.0133333, 0], [3, 0.386667, 0]], [0.118682, [3, -0.386667, 0], [3, 1.2, 0]], [0.118682, [3, -1.2, 0], [3, 0.4, 0]], [0.118682, [3, -0.4, 0], [3, 0.4, 0]], [-0.722566, [3, -0.4, 0], [3, 1.2, 0]], [1.80816, [3, -1.2, 0], [3, 0.4, 0]], [0.13439, [3, -0.4, 0], [3, 0.8, 0]], [0.150098, [3, -0.8, -0.015708], [3, 0.4, 0.00785399]], [0.849975, [3, -0.4, 0], [3, 0.666667, 0]], [0.849975, [3, -0.666667, 0], [3, 0, 0]]])

    names.append("RHand")
    times.append([0, 1.16, 7.16, 10.76])
    keys.append([[0.02, [3, -0.0133333, 0], [3, 0.386667, 0]], [0.2, [3, -0.386667, -0.0459032], [3, 2, 0.23743]], [0.87, [3, -2, 0], [3, 1.2, 0]], [0.87, [3, -1.2, 0], [3, 0, 0]]])

    names.append("RShoulderPitch")
    times.append([0, 1.16, 4.76, 5.96, 7.16, 10.76, 15.56, 17.56])
    keys.append([[1.32994, [3, -0.0133333, 0], [3, 0.386667, 0]], [1.32994, [3, -0.386667, 0], [3, 1.2, 0]], [0.127409, [3, -1.2, 0.605629], [3, 0.4, -0.201876]], [-1.09258, [3, -0.4, 0], [3, 0.4, 0]], [0.0837758, [3, -0.4, 0], [3, 1.2, 0]], [0.0837758, [3, -1.2, 0], [3, 1.6, 0]], [-0.300197, [3, -1.6, 0], [3, 0.666667, 0]], [1.69821, [3, -0.666667, 0], [3, 0, 0]]])

    names.append("RShoulderRoll")
    times.append([0, 1.16, 4.76, 5.96, 7.16, 10.76, 11.96, 15.56, 17.56])
    keys.append([[-0.792379, [3, -0.0133333, 0], [3, 0.386667, 0]], [-0.792379, [3, -0.386667, 0], [3, 1.2, 0]], [-0.792379, [3, -1.2, 0], [3, 0.4, 0]], [-0.0314159, [3, -0.4, -0.0226893], [3, 0.4, 0.0226893]], [-0.00872665, [3, -0.4, 0], [3, 1.2, 0]], [-0.200713, [3, -1.2, 0], [3, 0.4, 0]], [-0.00872665, [3, -0.4, 0], [3, 1.2, 0]], [-0.539307, [3, -1.2, 0], [3, 0.666667, 0]], [-0.539307, [3, -0.666667, 0], [3, 0, 0]]])

    names.append("RWristYaw")
    times.append([0, 1.16, 4.76, 5.96, 10.76, 15.56, 17.56])
    keys.append([[0.708604, [3, -0.0133333, 0], [3, 0.386667, 0]], [-0.708604, [3, -0.386667, 0], [3, 1.2, 0]], [0.708604, [3, -1.2, 0], [3, 0.4, 0]], [0.708604, [3, -0.4, 0], [3, 1.6, 0]], [0.708604, [3, -1.6, 0], [3, 1.6, 0]], [0.29147, [3, -1.6, 0], [3, 0.666667, 0]], [0.29147, [3, -0.666667, 0], [3, 0, 0]]])

    try:
      # uncomment the following line and modify the IP if you use this script outside Choregraphe.
      # motion = ALProxy("ALMotion", IP, 9559)
      #motion = ALProxy("ALMotion")
      #motion.angleInterpolationBezier(names, times, keys)
      map(lambda i: i * factor, times)
      play_animation(names, times, keys)
    except BaseException, err:
      print err
#Prueba!!!!!!!!!
    

def play_animation(animation_names, animation_times, animation_keys):
    try:
      # uncomment the following line and modify the IP if you use this script outside Choregraphe.
      # motion = ALProxy("ALMotion", IP, 9559)
      alMotion.angleInterpolationBezier(animation_names, animation_times, animation_keys)
    except BaseException, err:
      print err
      
#def run_animation( animation_path, animation_tag):
#    alAnimationPlayer.runTag(animation_path, animation_tag)

def go_to_posture( params ):
    posture = params.get("postura")
    speed = params("velocidad")
    alRobotPosture.goToPosture(posture, speed)

#Learns a new face and adds it in the database under the specified name.
def learn_face( params ):
    person_name = params.get("DETECTPWA")
    #Returns: true if the operation succeeded
    return alFaceDetection.learnFace(person_name)

#Gets a list containing the name of each learned face. The size of this list is always
#equal to the number of faces in the data base.
def get_face_list():
    return alFaceDetection.getLearnedFacesList()

#Enables or disables the autonomous blinking.
def activate_blinking(params):
    enabled = bool(params.get("ACTIVATE"))
    alAutonomousBlinking.setEnabled(enabled)

#Enables or disables the background movements.
def activate_life_signals(params):
    enabled = bool(params.get("ACTIVATELIFESIGNALS"))
    alBackgroundMovement.setEnabled(enabled)

#Enables or disables basic awareness.
def activate_life_signals_awareness(params):
    enabled = bool(params.get("ACTIVATELIFESIGNALSINT"))
    alBasicAwareness.setEnabled(enabled)

#Sets the engagement mode.
#https://developer.softbankrobotics.com/pepper-naoqi-25/naoqi-developer-guide/naoqi-apis/naoqi-interaction-engines/albasicawareness#albasicawareness-engagement-modes
def set_engagement_type(params):
    modeName = params.get("DEFENGAGEMENTTYPE")
    alBasicAwareness.setEngagementMode ( modeName )

#Enables or disables the listening movements.
def activate_hearing_movement(params):
    enabled = bool(params.get("ACTIVATEACTIVEHEARING"))
    alListeningMovement.setEnabled(enabled)

#Enables or disables the speaking movements.
def activate_speak_movements(params):
    enabled = bool(params.get("ACTIVATESPEAKMOVEMENTS"))
    alSpeakingMovementProxy.setEnabled(enabled)

#Sets the current speaking movement mode.  Random - Contextual 
def define_conversation_mode(mode):
    alSpeakingMovementProxy.setMode(mode)

#Enable/disable the push-recovery reflex of the robot, but only if allowed by the owner. If not allowed, an exception is thrown.
def activate_push_reflexes(params):
    enabled = bool(params.get("ACTIVATEPUSHREFLEXES"))
    alMotionProxy.setPushRecoveryEnabled(enabled)
    
 #Starts or stops breathing animation on a chain.   
def activate_breath_movement(params):
    extremity_to_enabled = "Body" 
    enabled = bool(params.get("ACTIVATEBREATHMOV"))
    alMotionProxy.setBreathEnabled(extremity_to_enabled, enabled)

#Enables or disables the movement detection to detect people. This can make the overall process slower if enabled
def activate_movement_detection(params):
    enabled = bool(params.get("ACTIVATEMOVDETECTION"))
    alPeoplePerception.setMovementDetectionEnabled(enabled)
    
#Enables/disables the face recognition process. The remaining face detection process will be faster if face recognition is disabled. Face recognition is enabled by default.
def activate_face_detection(params):
    enabled = bool(params.get("ACTIVATELIFESIGNALSINT"))
    alFaceDetection.setRecognitionEnabled(enabled)

#Enable/Disable Anti-collision protection of the arms of the robot.
def activate_colission_detection(params):
    chainName = "Arms"
    enabled = bool(params.get("ACTIVATECOLISSIONDETECT"))
    alMotionProxy.setCollisionProtectionEnabled(chainName,enabled)

#Enables or disables power monitoring.
def activate_monitoring_charge_service(params):
    enabled = bool(params.get("ACTIVATEMONITORINGCHARGESERV")) 
    alBatteryProxy.enablePowerMonitoring(enabled)

#Get battery charge.
def get_battery():
    return  alBatteryProxy.getBatteryCharge()

#Return the actual state of the temperature diagnosis. Only the highest level of failure is returned.
def get_temperature():
    return alBodyTemperatureProxy.getTemperatureDiagnosis()

#Gets the emotional state of the current focused user through a PersonState struct.
def get_emotion_state():
    return alMood.currentPersonState()

#                        NI PINSHI IDEA DE COMO DEJAR EL LOGIN

#Se verifica el login, es decir, se revisa que alguno de los usuarios con sesion activa coincida con
#el que esta interactuando con el robot
def login():

    for i in alUserSession.getOpenUserSessions():
        if i == alUserSession.getFocusedUser():
            return True
            
    return False



#Looks for a free circular zone of a specified radius not farer than a specified displacement.
def search_free_zone( params ):
    radius = params.get("RADIO")
    displacement = params.get("DISTANCIAMAX")
    return alNavigationProxy.findFreeZone(radius,displacement)

#Looks for a free circular zone of a specified radius not farer than a specified displacement
def get_free_zone(radius, displacement):
    return alNavigationProxy.getFreeZone(radius, displacement)

#Gets the coordinates x, y, theta of the pose2D of the robot
def get_robot_position(params):
    enabled = params.get("GETROBOTPOSITION")
    return alLocalizationProxy.getRobotPosition(enabled)

#Makes the robot move at the given velocity, expressed in FRAME_ROBOT
#Z is rotation
def move(x, y, z):
    alMotion.move(x,y,z)

#Makes the robot move to the given pose in the ground plane, relative to FRAME_ROBOT
def move_forward(x,y,speed):
    alMotion.moveTo(x,y,speed)

#Makes the robot navigate to a relative metrical target pose2D expressed in FRAME_ROBOT.
def move_to(params):
    x = params.get("MOVETOX")
    y = params.get("MOVETOY")
    alNavigationProxy.navigateTo(x,y)

#Go to the given position trying to perform a visual close loop with the image contained in current panorama at theta.
def move_to_position(position):
    alLocalizationProxy.goToPosition(position)

# The robot wakes up
def wake_up():
    alMotionProxy.wakeUp()

#The robot rests: goes to a relaxed and safe position and sets Motor off.
def suspend():
    alMotionProxy.rest()

#Updates the period if relevant.
def set_refresh_time_sensors(sensor, time):
    alSensorsProxy.updatePeriod(sensor,time)

#Launch a green/yellow/red rasta animation on all body.
def  activate_rasta(duration):
    alLedsProxy.rasta(duration)

#Launch a random animation in eyes
def random_eyes(duration):
    alLedsProxy.randomEyes(duration)

#Sets the intensity of a LED or Group of LEDs.
def set_leds_intensity(sensor, intensity):
    alLedsProxy.setIntensity(sensor, intensity)

#Sets the color of an RGB led using  color code.
def change_led_color(sensor, red_color, green_color, blue_color, duration):
    alLedsProxy.fadeRGB(sensor, red_color, green_color, blue_color, duration)

#Enable or Disable the smart stiffness reflex for all the joints (True by default).
#The update takes one motion cycle.
def  activate_stiffness(params):
    return alMotion.setSmartStiffnessEnabled(params)

def change_emotion_expression(params):
    print ("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEENNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRREEEEEEEEEEEEEEEEEEEEEEEEEEEE")
    emotionStateRobot.setToneSpeech(params.get("tonoHabla"))
    emotionStateRobot.setLedR(params.get("R"))
    emotionStateRobot.setLedG(params.get("G"))
    emotionStateRobot.setLedB(params.get("B"))
    emotionStateRobot.setLedIntensity(params.get("ledIntens"))
    emotionStateRobot.setFactorVelocity(params.get("velocidad"))
    emotionStateRobot.setVelocitySpeech(params.get("velHabla"))
    print("R: ", emotionStateRobot.getLedR(), "G: ", emotionStateRobot.getLedG(), "B: ", emotionStateRobot.getLedB() )
    change_led_color("AllLeds", emotionStateRobot.getLedR(), emotionStateRobot.getLedG(), emotionStateRobot.getLedB(), 1.0)
    set_leds_intensity("AllLeds", emotionStateRobot.getLedIntensity())
    



#Turn on/off the tablet screen.
def tablet_on():
    alTabletService.turnScreenOn(True)

#Wake the tablet (from standby mode).
def wake_tablet():
    alTabletService.wakeUp()
    
#Put the tablet in sleep mode (standby mode).
def suspend_tablet():
    alTabletService.goToSleep()

#Turn on/off the tablet screen.
def tablet_off():
    alTabletService.turnScreenOn(False)

#Open a video player on tablet and play video from given url.
def show_video(params):
    alTabletService.playVideo(params.get("SHOWVIDEO"))

#Close the video player.
def quit_video():
    alTabletService.stopVideo()

#Pause the video playing but do not close the video player.
def pause_video():
    alTabletService.pauseVideo()

#Resume the video paused by ALTabletService::pauseVideo .
def  resume_video():
     alTabletService.resumeVideo()

# Load the image to show to de user
def preload_image(url):
    alTabletService.preLoadImage(url)

#Shows the image in the tablet for the user
def show_image(params):
    alTabletService.showImage(params.get(""))

#Hide image currently displayed.
def hide_image():
    alTabletService.hideImage()

#Set tablet brightness.
def set_tablet_bright(params):
    brightness = params.get("SETTABLETBRIGHT")
    alTabletService.setBrightness(brightness)

#Configure the media volume of the tablet.
def set_tablet_volume(volume):
    alTabletService.setVolume(volume)

#Says the specified string of characters.
def say(params, speed=None, pitch=None):
    if speed == None:
        speed = emotionStateRobot.getVelocitySpeech()
    if pitch == None:
        pitch = emotionStateRobot.getToneSpeech()
    alTexToSpeech.setParameter("speed", speed)
    alTexToSpeech.setParameter("pitchShift", pitch)
    alTexToSpeech.say(params.get("SAY"))

 #   This method stops the current and all the pending tasks immediately.
def stop_all(params):
    alTexToSpeech.stopAll(params.get("STOPALL"))


#Sets the current gain applied to the signal synthesized by the text to speech engine.
def set_say_volume(params):
    volume = params.get("SETSAYVOLUMEN")/100
    alTexToSpeech.setVolume(volume)

#Say the annotated text given in parameter and animate it with animations inserted in the text.
def say_with_movement(text):
    alAnimatedSpeech.say(text)

#Sets the overall output volume of the system.
def set_system_volume(volume):
    alAudioDevice.setOutputVolume(volume)

#Starts the playback of the specified file. 
def play_sound(sound):
    alAudioPlayer.playFile(sound)

#Pause the playback of the specified task.
def pause_sound(idSound):
    alAudioPlayer.pause(idSound)

#Subscribes to ALVoiceEmotionAnalysis .
def activate_voice_emotion_analysis(params):
    subscriberName = params.get("ACTIVATEVOICEEMOANAL")
    alVoiceEmotionAnalysis.subscribe(subscriberName) 

#Unsubscribes to ALVoiceEmotionAnalysis .
def desactivate_voice_emotion_analysis():
    alVoiceEmotionAnalysis.unsubscribe(sensorsModule)

#Subscribes to ALSpeechRecognition 
def activate_voice_recognition(params):
    subscriber = params.get("ACTVOICERECOG")
    alSpeechRecognition.subscribe(subscriber)

#Unsubscribes to ALSpeechRecognition
def desactivate_voice_recognition():
    alSpeechRecognition.unsubscribe(sensorsModule)

#Adds the specified topic to the list of the topics that are currently used by the dialog engine to parse the human's inputs.
def activate_conversational_topic(topicName):
    alDialogProxy.activateTopic(topicName)

#Loads the topic, exports and compiles the corresponding context files so that they are ready to be used by the speech recognition engine 
def load_conversational_topic(topicName):
     alDialogProxy.loadTopic(path)

#Unloads the specified topic and frees the associated memory.
def unload_conversational_topic(topicName):
     alDialogProxy.unloadTopic(topicName)

#Removes the specified topic from list of the topics that are currently used by the dialog engine to parse the human's inputs.
def deactivate_conversational_topic(topicName):
     alDialogProxy.deactivateTopic(topicName)

#Says a tagged sentence from a topic.
def say_under_topic_context(topic, tag):
    alDialogProxy.say(topic,tag)

#If multiple topics can be active at the same time, only one of them is used to generate proposals.
def set_topic_focus(topicName):
    alDialogProxy.setFocus(topicName)




    
def hablar(text_to_speech, speed=None, pitch=None):
    if speed == None:
        speed = emotionStateRobot.getVelocitySpeech()
    if pitch == None:
        pitch = emotionStateRobot.getToneSpeech()

    alTexToSpeech.setParameter("speed", speed)
    alTexToSpeech.setParameter("pitchShift", pitch)
    alTexToSpeech.say(text_to_speech)
    
def safe_str(obj):
    try: return str(obj)
    except UnicodeEncodeError:
        return obj.encode(FORMAT, 'ignore').decode(FORMAT)
    return ""

def registrar_cuidador(params):
    pass

def historial(params):
    pass
def seleccionar_actividades(params):
    pass
def cambiar_actividad(params):
    pass
def conversacion_empatica(params):
    estado_emocional = params.get("estado_emocional")
    switch_emocion = {
        "triste": "conversacion_triste",
        "alegre": "conversacion_alegre",
        "ira": "conversacion_ira"
    }
    func = switch_emocion.get(params)
    func()

def conversacion_triste():
    frase_inicial = "Se que estas triste," #realizar una pausa! IMPORTANTE!!!!!!!!!!!!!!
    switch_conversacion = {
        1: "pero sabes? la tristeza nos puede ayudar a hacer una introspeccion y esto nos permite estar mejor.",
        2: "Aveces la tristeza nos puede hacer sentir mal, te cuento que como el resto de emociones tiene una funcion que nos permite volver mejores",
        3: "Sabias que usualmente necesitamos la ayuda de otras personas cuando estamos tristes?",
        4: "y te sientes mal, pero esta emocion te permite sanar heridas"
    }
    frase_principal = "Te gustaria escuchar una cancion?"

def conversacion_alegre():
    frase_inicial = "Me gusta verte asi,"
    switch_conversacion = {
        1: "La alegria te genera una sonrisa hermosa",
        2: "Las personas se desempenian mucho mejor cuando estan alegres",
        3: "La alegria es parte de la vida y es vital para disfrutar nuestra estadia",
        4: "luces genial, esa alegria que reflejas te hace ver mejor"
    }
    frase_principal = "Escuchamos una cancion?"

def conversacion_ira(genero):
    frase_inicial = "Veo que estas enfadad"

    if genero =='M':
        frase_inicial = frase_inicial + "o"
    else:
        frase_inicial = frase_inicial + "a"
    
    frase_inicial = frase_inicial + ","

    switch_conversacion = {
        1: "es normal sentir ira, pero no debes dejarla salir de control porque no te permite tomar buenas decisiones",
        2: "aveces la ira te puede hacer sentir mal, sin embargo debes tomar las cosas con serenidad",
        3: "pero sabes? las cosas tienen la importancia que tu les das",
        4: "la ira te indispone y dificulta que pienses con claridad"
    }
    frase_principal = "si quieres podemos escuchar una cancion para relajarnos?"

def send( id_response, responseType, params):
    HOST_LOCAL = '127.0.0.1'
    PORT = 7897
    FORMAT = 'utf-8'
    ADDR = (HOST_LOCAL, PORT)
    client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client.connect(ADDR)
    msg_to_send = json.dumps(json_creator(id_response, responseType, params))
    print("send ", msg_to_send)
    
    client.send(msg_to_send + '\r\n')
    client.close()   


#----------------------------------------------------------------------------Robot class---------------------------------------------------------------------------------------------    
"""--------------------------------------------------------------------------Robot class---------------------------------------------------------------------------------------------"""
#----------------------------------------------------------------------------Robot class---------------------------------------------------------------------------------------------
class Robot:
    def __init__(self):
        #The list have the function on the first place, if the activity most return an ack on the second, type on the third and callback response the fourth
        self.__modules = {
            #ActivityServices-------------------------------------------------------
            "RUNANIMATION": [run_animation, True, "act", True], #funcionando
            "GOTOPOSTURE": [go_to_posture, False, "rob", False],  #No hace nada-------------------------
            "DETECTNEWFACE ": [learn_face, True, "int", False],  #funcionando
            "GETFACELIST": [get_face_list, False, "int", False],  #funcionando
            "ACTIVATE": [activate_blinking, True, "rob", False], #Parece que funciona, no bota error
            "ACTIVATELIFESIGNALS": [activate_life_signals, True, "rob", False], #
            "ACTIVATELIFESGINALSINT": [activate_life_signals_awareness, True, "rob", False], #
            "DEFENGAGEMENTTYPE": [set_engagement_type, False, "int", False], #
            "ACTIVATEACTIVEHEARING": [activate_hearing_movement, True, "rob", False],#
            "ACTIVATESPEAKMOVEMENTS": [activate_speak_movements, True, "rob", False], #
            "DEFCONVERSATIONMODE": [define_conversation_mode, True, "int", False], #
            "ACTIVATEPUSHREFLEXES": [activate_push_reflexes, True, "rob", False], #
            "ACIVATEBREATHMOV": [activate_breath_movement, True, "rob", False], #
            "ACTIVATEMOVDETECTION": [activate_movement_detection, True, "rob", False], #
            "ACTIVATEFACEDETEC": [activate_face_detection, True, "rob", False], #
            "ACTIVATECOLISSIONDETECT": [activate_colission_detection, True, "rob", False], #
            #EnergyServices-------------------------------------------------------
            "ACTIVATEMONITORINGCHARGESERV": [activate_monitoring_charge_service, True, "rob", False], #
            "GETBATTERY": [get_battery, False, "rob", False], #
            "GETTEMP": [get_temperature, False, "rob", False],#
            #HumanServices-------------------------------------------------------
            "GETEMOTIONSTATE": [get_emotion_state, False, "emo", False], #
            "LOGIN": [login, False, "act", False], #
            #LocationServices-------------------------------------------------------
            "SEARCHFREEZONE": [search_free_zone, False, "act", False], #
            "GETFREEZONES": [get_free_zone, False, "act", False], #
            "GETROBOTPOSITION": [get_robot_position, False, "act", False], #
            #MovementServices-------------------------------------------------------
            "MOVE": [move, True, "act", True], #
            "MOVEFORWARD": [move_forward, True, "act", True], #
            "MOVETO": [move_to, True, "act", True], #
            "MOVETOPOSITION": [move_to_position, True, "act", True], #
            #RobotStateServices-------------------------------------------------------
            "WAKEUP": [wake_up, True, "act", False], #
            "SUSPEND": [suspend, True, "act", False], #
            "SETREFRESHTIMESENSORS": [set_refresh_time_sensors, False, "act", False], #Hay que crear un modulo para probar
            "ACTIVATERASTA ": [activate_rasta, False, "act", False], #
            "RANDOMEYES": [random_eyes, False, "act", False],#
            "SETLEDSINTENSITY": [set_leds_intensity, True, "act", False],#
            "CHANGELEDCOLOR": [change_led_color, True, "act", False],#
            "ACTIVATESTIFFNESS": [activate_stiffness, True, "act", False],#
            "ROBOTEMOTION": [change_emotion_expression, False, "emo", False],
            #TabletServices-------------------------------------------------------
            "TABLETON": [tablet_on, True, "act", False],
            "WAKETABLET": [wake_tablet, True, "act", False],
            "SUSPENDTABLET": [suspend_tablet, True, "act", False],
            "TABLETOFF": [tablet_off, True, "act", False],
            "SHOWVIDEO": [show_video, False, "act", True],
            "QUITVIDEO": [quit_video, False, "act", False],
            "PAUSEVIDEO": [pause_video, False, "act", False],
            "RESUMEVIDEO": [resume_video, False, "act", False],
            "PRELOADIMG": [preload_image, False, "act", False],
            "SHOWIMG": [show_image, True, "act", True],
            "HIDEIMG": [hide_image, True, "act", False],
            "SETTABLETBRIGHT": [set_tablet_bright, True, "act", False],
            "SETTABLETVOL": [set_tablet_volume, True, "act", False],
            #VoiceServices-------------------------------------------------------
            "SAY": [say, True, "act", True],
            "STOPALL": [stop_all, True, "act", False],
            "SETSAYVOLUMN": [set_say_volume, True, "act", False],
            "SAYWITHMOVEMENT": [say_with_movement, True, "act", True],
            "SETSYSTEMVOLUME": [set_system_volume, True, "act", False],
            "PLAYSOUND": [play_sound, True, "act", True],
            "PAUSESOUND": [pause_sound, True, "act", False],
            "ACTIVATEVOICEEMOANAL": [activate_voice_emotion_analysis, True, "act", False],
            "DESACTIVVOICEEMOANAL": [desactivate_voice_emotion_analysis, True, "act", False],
            "ACTVOICERECOG": [activate_voice_recognition, True, "act", False],
            "DESACTVOICERECOG": [desactivate_voice_recognition, True, "act", False],
            "ACTIVATECONVTOPIC": [activate_conversational_topic, True, "act", False],
            "LOADCONVTOPIC": [load_conversational_topic, True, "act", False],
            "UNLOADCONVTOPIC": [unload_conversational_topic, True, "act", False],
            "DEACTCONVTOPIC": [deactivate_conversational_topic, True, "act", False],
            "SAYUNDERTOPICCONTEXT": [say_under_topic_context, True, "act", True],
            "SETTOPICFOCUS": [set_topic_focus, True, "act", False]
        }
    def getFunction(self, fun):
        return self.__modules.get(fun)[0]

    def getAck(self, fun):
        return self.__modules.get(fun)[1]
    
    def getType(self, fun):
        return self.__modules.get(fun)[2]

    def isResponseSaved(self, fun):
        return self.__modules.get(fun)[3]

#----------------------------------------------------------------------------Emotion class---------------------------------------------------------------------------------------------    
"""--------------------------------------------------------------------------Emotion class---------------------------------------------------------------------------------------------"""
#----------------------------------------------------------------------------Emotion class---------------------------------------------------------------------------------------------

class Emotion:
    def __init__(self):
        self.__toneSpeech = 1.1
        self.__ledR = 1
        self.__ledG = 1
        self.__ledB = 1
        self.__ledIntensity = 1
        self.__factorVelocity = 0.0
        self.__velocitySpeech = 100

    #Getters

    def getToneSpeech(self):
        return self.__toneSpeech

    def getLedR(self):
        return self.__ledR

    def getLedG(self):
        return self.__ledG   

    def getLedB(self):
        return self.__ledB  

    def getLedIntensity(self):
        return self.__ledIntensity 

    def getFactorVelocity(self):
        return self.__factorVelocity 

    def getVelocitySpeech(self):
        return self.__velocitySpeech 

    #Setters

    def setToneSpeech(self, tone):
        self.__toneSpeech = tone

    def setLedR(self, ledR):
        self.__ledR = ledR

    def setLedG(self, ledG):
        self.__ledG = ledG   

    def setLedB(self, ledB):
        self.__ledB = ledB 

    def setLedIntensity(self, ledIntensity ):
        self.__ledIntensity = ledIntensity  

    def setFactorVelocity(self, factorVelocity):
        self.__factorVelocity = factorVelocity

    def setVelocitySpeech(self, velocitySpeech ):
        self.__velocitySpeech = velocitySpeech
    
#----------------------------------------------------------------------------MODULE---------------------------------------------------------------------------------------------    
"""--------------------------------------------------------------------------MODULE---------------------------------------------------------------------------------------------"""
#----------------------------------------------------------------------------MODULE---------------------------------------------------------------------------------------------
# create python module
class pepperModule(ALModule):
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
    #print("send ", msg_to_send)
    #json_creator(-1, responseTypeBESAFunction(key), getParams(key, value))
    
    #send(msg_to_send)
    print "datachanged:", key, " value:", value, " message:", message
    

    
    #Envio de informacion a BESA despues de recibir un evento+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

#----------------------------------------------------------------------------Message class---------------------------------------------------------------------------------------------    
"""--------------------------------------------------------------------------Message class---------------------------------------------------------------------------------------------"""
#----------------------------------------------------------------------------Message class---------------------------------------------------------------------------------------------
class messageManager:
    def __init__(self, idResponse = -1, response_type = None):
        self.__idResponse = idResponse
        self.__response_type = response_type
        self.__params = None

    def getIdResponse(self):
        return self.__idResponse

    def getResponseType(self):
        return self.__response_type
    
    def getParams(self):
        return self.__params

    def setIdResponse(self, idResponse):
        self.__idResponse = idResponse

    def setResponseType(self, response_type):
        self.__response_type = response_type
    
    def setParams(self, params):
        self.__params = params
    


#----------------------------------------------------------------------------MAIN---------------------------------------------------------------------------------------------    
"""--------------------------------------------------------------------------MAIN---------------------------------------------------------------------------------------------"""
#----------------------------------------------------------------------------MAIN---------------------------------------------------------------------------------------------
print("Server starting...pop")
HOST = '10.195.22.24' #socket.gethostbyname(socket.gethostname()) # Standard loopback interface             address (localhost)
HOST_LOCAL = '127.0.0.1'
print("Server starting on", HOST_LOCAL)
PORT = 7896    # Port to listen on (non-privileged ports are > 1023)
print("Server starting...pop0000000000000000")
ADDR = (HOST_LOCAL, PORT)
server = None
HEADER = 1024
FORMAT = 'utf-8'
print("Server starting...pop1111111111111111111")
#send( "id", "ROB", True)
#------------------------------------------------------------------------------------------------
parser = argparse.ArgumentParser()
parser.add_argument("--ip", type=str, default=HOST, help="Robot IP address. On robot or Local Naoqi: use '127.0.0.1'.")
parser.add_argument("--port", type=int, default=9559, help="Naoqi port number")
args = parser.parse_args()
session = qi.Session()
try:
    session.connect("tcp://" + args.ip + ":" + str(args.port))
except RuntimeError:
    print ("Can't connect to Naoqi at ip \"" + args.ip + "\" on port " + str(args.port) +".\n"
               "Please check your script arguments. Run with -h option for help.")
    sys.exit(1)

#----------------------------------------------------------------------------------------------------

#Declare the Naoqi variables --------------------------------------------------------------------
alBroker = ALBroker("myBroker", "0.0.0.0", 7896, HOST, 9559 )
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
alFaceDetection = session.service("ALFaceDetection")
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
alVoiceEmotionAnalysis  = session.service("ALVoiceEmotionAnalysis")
alSpeechRecognition  = session.service("ALSpeechRecognition")
alDialogProxy = session.service("ALDialog")

#----------------------------------------------------------------------------------------------------
#----------------------------------------------------------------------------------------------------
#Declare the modules --------------------------------------------------------------------------------
try:

    sensorsModule = pepperModule("sensorsModule")
    #Raised when an animated speech is done.
    alProxy.subscribeToEvent("ALAnimatedSpeech/EndOfAnimatedSpeech","sensorsModule", "pythondatachanged") 
    #Raised when the person tracked can no longer be found for some time.
    alProxy.subscribeToEvent("ALBasicAwareness/HumanLost","sensorsModule", "pythondatachanged")      #DEBE TENER DETECTADA UNA CARA PARA FUNCIONAR

    #Raised when the robot begins to track a person, when the tracked person is lost, or when the tracked person's ID is|
    alProxy.subscribeToEvent("ALBasicAwareness/HumanTracked","sensorsModule", "pythondatachanged")

    #Raised when a stimulus is detected.
    #types of stimulus: http://doc.aldebaran.com/2-5/naoqi/interaction/autonomousabilities/albasicawareness.html#albasicawareness-stimuli-types
    alProxy.subscribeToEvent("ALBasicAwareness/StimulusDetected","sensorsModule", "pythondatachanged")

    #Raised when the battery level is low and will soon need charging.
    alProxy.subscribeToEvent("ALBattery/BatteryLow","sensorsModule", "pythondatachanged")      #DEBE TENER LA BATERiA BAJA PARA FUNCIONAR

    #Raised when the robot could not reach its destination, either because it was lost or because it was interrupted by an obstacle.
    alProxy.subscribeToEvent("ALLocalization/GoToFailed","sensorsModule", "pythondatachanged")   #NO MUESTRA NADA - 

    #Raised when the robot has successfully reached its destination.
    alProxy.subscribeToEvent("ALLocalization/GoToSuccess","sensorsModule", "pythondatachanged")

    #Raised when the robot gets lost while trying to go to its destination.
    alProxy.subscribeToEvent("ALLocalization/GoToLost","sensorsModule", "pythondatachanged")

    #Raised when the localization is successful.
    alProxy.subscribeToEvent("ALLocalization/LocalizeSuccess","sensorsModule", "pythondatachanged")

    #Raised when the localization fails and the robot is lost.
    alProxy.subscribeToEvent("ALLocalization/LocalizeLost","sensorsModule", "pythondatachanged")

    #Raised when the orientation of the robot has NOT been successfully retrieved.
    alProxy.subscribeToEvent("ALLocalization/LocalizeDirectionLost","sensorsModule", "pythondatachanged")

    #Raised when the orientation of the robot has been successfully retrieved.
    alProxy.subscribeToEvent("ALLocalization/LocalizeDirectionSuccess","sensorsModule", "pythondatachanged")

    #Raised when a chain velocity is clipped because an obstacle is too close.
    alProxy.subscribeToEvent("ALMotion/Safety/ChainVelocityClipped","sensorsModule", "pythondatachanged")

    #Raised when a move command fails.
    alProxy.subscribeToEvent("ALMotion/MoveFailed","sensorsModule", "pythondatachanged")

    #Raised when the awake status of the robot changes.
    alProxy.subscribeToEvent("robotIsWakeUp","sensorsModule", "pythondatachanged")

    #Raised at ALMotionProxy::wakeUp finish.
    ###   alProxy.subscribeToEvent("ALMotion/Stiffness/wakeUpFinished","sensorsModule", "pythondatachanged")
    #Raised at ALMotionProxy::rest finish.
    alProxy.subscribeToEvent("ALMotion/Stiffness/restFinished","sensorsModule", "pythondatachanged")

    #Raised when devices availability changed. When a device is not available the stiffness and movement on this device are prohibited.
    alProxy.subscribeToEvent("ALMotion/Protection/DisabledDevicesChanged","sensorsModule", "pythondatachanged")

    #Raised when features (Move, Stiffness...) availability changed.
    alProxy.subscribeToEvent("ALMotion/Protection/DisabledFeaturesChanged","sensorsModule", "pythondatachanged")

    #Raised when a chain velocity is clipped because an obstacle is too close.
    alProxy.subscribeToEvent("ALMotion/Safety/ChainVelocityClipped","sensorsModule", "pythondatachanged")

    #Raised when a move command fails.
    alProxy.subscribeToEvent("ALMotion/MoveFailed","sensorsModule", "pythondatachanged")

    #Raised when Pepper is correctly docked onto the charging station.
    alProxy.subscribeToEvent("ALRecharge/ConnectedToChargingStation","sensorsModule", "pythondatachanged")

    #Raised when Pepper interrupts his operation because a safety rule prevents the usage of ALMotion module.
    alProxy.subscribeToEvent("ALRecharge/MoveFailed","sensorsModule", "pythondatachanged")

    #Raised when Pepper failed to leave his charging station due to an obstacle in the way.
    alProxy.subscribeToEvent("ALRecharge/LeaveFailed","sensorsModule", "pythondatachanged")

    #Raised when one of the specified words set with ALSpeechRecognitionProxy::setVocabulary has been recognized. When no word is currently recognized, this value is reinitialized.
    alProxy.subscribeToEvent("WordRecognized","sensorsModule", "pythondatachanged")

    #Raised when the automatic speech recognition engine has detected a voice activity.
    alProxy.subscribeToEvent("SpeechDetected","sensorsModule", "pythondatachanged")

    #Raised when an error occurs.
    alProxy.subscribeToEvent("ALTabletService/error","sensorsModule", "pythondatachanged")

    #Raised when message occurs.
    alProxy.subscribeToEvent("ALTabletService/message","sensorsModule", "pythondatachanged")

    #Raised when text input occurs.
    alProxy.subscribeToEvent("ALTabletService/onInputText","sensorsModule", "pythondatachanged")

    #Raised when a valid tactile gesture has been detected
    alProxy.subscribeToEvent("ALTactileGesture/Gesture","sensorsModule", "pythondatachanged")

    #Raised when the current sentence synthesis is done.
    alProxy.subscribeToEvent("ALTextToSpeech/TextDone","sensorsModule", "pythondatachanged")

    #Raised when the current sentence synthesis is interrupted, for example by ALTextToSpeechProxy::stopAll.
    alProxy.subscribeToEvent("ALTextToSpeech/TextInterrupted","sensorsModule", "pythondatachanged")
    #Raised when an utterance has been analyzed.
    alProxy.subscribeToEvent("ALVoiceEmotionAnalysis/EmotionRecognized","sensorsModule", "pythondatachanged")
    #Raised whenever an activity completes its execution and exits.
    alProxy.subscribeToEvent("AutonomousLife/CompletedActivity","sensorsModule", "pythondatachanged")
    """
    #Raised when the robot touch status changed.
    alProxy.subscribeToEvent("TouchChanged","sensorsModule", "pythondatachanged")
    """
    #Raised when at least one device (joint, actuator, sensor) has a high temperature.
    alProxy.subscribeToEvent("HotDeviceDetected","sensorsModule", "pythondatachanged")
    #Raised each time the robot catches a human input. Contains the last human input.
    alProxy.subscribeToEvent("Dialog/LastInput","sensorsModule", "pythondatachanged")
    #Raised when the dialog engine starts or stops. The value is "1" for start, "0" for stop.
    alProxy.subscribeToEvent("Dialog/IsStarted","sensorsModule", "pythondatachanged")
    #Currently processed human input.
    alProxy.subscribeToEvent("Dialog/CurrentString","sensorsModule", "pythondatachanged")
    #Raised when a person just moved away from the robot (i.e. moved to a further engagement zone).
    alProxy.subscribeToEvent("EngagementZones/PersonMovedAway","sensorsModule", "pythondatachanged")
    #Raised when a person just approached the robot (i.e. moved to a closer engagement zone).
    alProxy.subscribeToEvent("EngagementZones/PersonApproached","sensorsModule", "pythondatachanged")
    #Raised when a person has a smile value above the current threshold (default = 0.7).
    alProxy.subscribeToEvent("FaceCharacteristics/PersonSmiling","sensorsModule", "pythondatachanged")
    #Raised when one or several faces are currently being detected.
    alProxy.subscribeToEvent("FaceDetected","sensorsModule", "pythondatachanged")
    #Raised each time the list of people looking at the robot changes.
    alProxy.subscribeToEvent("GazeAnalysis/PeopleLookingAtRobot","sensorsModule", "pythondatachanged")
    #Raised when someone turns his head away from the robot.
    alProxy.subscribeToEvent("GazeAnalysis/PersonStopsLookingAtRobot","sensorsModule", "pythondatachanged")
    #The distance in meters to the tracked human. -1.0 if no one is tracked.
    alProxy.subscribeToEvent("Launchpad/DistanceOfTrackedHuman","sensorsModule", "pythondatachanged")
    #Raised when an obstacle is detected in the close area.
    alProxy.subscribeToEvent("Navigation/AvoidanceNavigator/ObstacleDetected","sensorsModule", "pythondatachanged")
    #Raised whenever at least one person is visible by the robot. Contains information about the detected people, it is used by ALTracker to track people.
    alProxy.subscribeToEvent("PeoplePerception/PeopleDetected","sensorsModule", "pythondatachanged")
    #Raised when a new preference is added to the system.
    alProxy.subscribeToEvent("preferenceAdded","sensorsModule", "pythondatachanged")
    #Raised when the value of a preference has been updated.
    alProxy.subscribeToEvent("preferenceChanged","sensorsModule", "pythondatachanged")
    #Raised when something just waved at the robot.
    alProxy.subscribeToEvent("WavingDetection/Waving","sensorsModule", "pythondatachanged")
    #Raised when someone just waved at the robot.
    alProxy.subscribeToEvent("WavingDetection/PersonWaving","sensorsModule", "pythondatachanged")
    #
except Exception,e:
  print "error"
  print e
  alBroker.shutdown()
  exit(1)
#----------------------------------------------------------------------------------------------------
server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

print("Server starting...pop22222222222222222222222")
ADDR = (HOST_LOCAL, PORT)
print("Server starting...pop3333333333333333333333333")
server.bind(ADDR)
print("Server starting...pop4444444444444444444444444444")
server.listen(5)
print("[STARTING] server is listening on", HOST_LOCAL)

#activities_running is a dictionary which save the activities running on the robot
activities_running = {"battery": True}

"""----------------------------------------------TIMER---------------------------------------------------------"""
#define Timer to inform BESA
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

"""

#TOPICOS
alDialogProxy.setLanguage("Spanish")
topic_content_1 = ('topic: ~retroalimentation()\n'
                       'language: spe \n'
                       'concept:(retroalimentacion) [Excelente Bien Regular Mal Pesimo]\n'
                       'u: (Quiero terminar) Esta bien, Nos puedes dar una retroalimentacion, por favor?\n'
                       'u: (si) Gracias! Califica como te parecio la actividad: Excelente, bien, regular, mal o pesimo\n'
                       'u: (no) De acuerdo. Gracias por participar conmigo\n'
                       'u: ([Excelente Bien]) Gracias por tu calificacion! Esperamos que te siga gustando!\n'
                       'u: (Regular) Gracias por tu calificacion! Esperamos mejorar para la proxima\n'
                       'u: ([Mal Pesimo]) Gracias por tu calificacion! Que lastima que no te gustara, lo mejoraremos la proxima vez\n')

topic_content_2 = ('topic: ~Activity_management()\n'
                       'language: spe\n'
                       'concept:(emocion)[cansado aburrido mamado ] \n'
                       'u:(No quiero jugar mas) Esta bien! Vamos a quitar la actividad.\n'
                       'u:(Estoy_~emocion) Esta bien! Quieres cambiar la actividad?.\n'
                       'u:(si {por favor}) Bueno, cambiemos de actividad.\n'
                       'u:(Que te dices mi pez) En la buena pirobo norrea.\n')

    # Loading the topics directly as text strings
topic_name_1 = load_conversational_topic(topic_content_1)
topic_name_2 = load_conversational_topic(topic_content_1)

    # Activating the loaded topics
activate_conversational_topic(topic_name_1)
activate_conversational_topic(topic_name_2)
    #Subscribe to use the topics previously activated
        
"""




