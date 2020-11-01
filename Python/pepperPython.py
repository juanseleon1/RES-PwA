from naoqi import ALProxy
import socket
import threading
import json
import qi
import sys
import argparse

def handle_client(conn, addr):
    print("Lo logro")
    
    msg_length = conn.recv(HEADER)
    msg_length = msg_length.decode(FORMAT, 'ignore')
    msg_length = safe_str(msg_length)
    #if msg_length:
    y = safe_str(msg_length).split('{')[1]
    print("msg:")
    y = "{" + y
    #print(y)
    jsonObj = json.loads(y)
    msg_length = len(jsonObj)
    msg = conn.recv((msg_length)).decode(FORMAT, 'ignore')
    if jsonObj["methodName"] == "hablar":
        print(jsonObj["methodName"])
    
    if learn_face("Brayan"):
        hablar("hola Enrique, mi nombre es Pepper")
    else:
        hablar("Mal")
    print(get_face_list()[0])
    
    #activate_blinking(False)+++++++++++++++++++++++++++++
    #activate_life_signals(false)+++++++++++++++++++++++++
    #activate_life_signals_awareness(False)
    #set_engagement_type("FullyEngaged")
    #activate_hearing_movement(False)
    #activate_speak_movements(False)
    #define_conversation_mode("random")
    #activate_push_reflexes(False)
    #activate_breath_movement("Body", False)
    #activate_movement_detection(True)
    #activate_face_detection(False)
    #activate_colission_detection("arms", False)
    #activate_monitoring_charge_service(False)
    #print("Battery", get_battery())
    #print("Temperature", get_temperature())
    #print("Smile", get_emotion_state().get("expressions").get("excitement")[0]) #return the excitement value
    #print(login())

    #set_topic_focus("empatia")
    #say_under_topic_context((path,"empatia")
    #deactivate_conversational_topic("empatia")
    #unload_conversational_topic("empatia")
    #load_conversational_topic("empatia")
    #activate_conversational_topic("empatia")
    #desactivate_voice_recognition():
    #activate_voice_recognition(subscriber)
    #desactivate_voice_emotion_analysis()
    #activate_voice_emotion_analysis(Sadness)
    #pause_sound(idSound)
    #play_sound(idSound)
    #set_system_volume(50)
    #say_with_movement("Hola Enrique")
    #set_say_volume(75)
    #stop_all()
    #say("Hola mundo")
    #tablet_on()
    #wake_tablet()
    #suspend_tablet()
    #tablet_off()
    #show_video("https://www.youtube.com/watch?v=TmKh7lAwnBI&ab_channel=BadBunny")
    #quit_video()
    #pause_video()
    #resume_video()
    #preload_image("https://aliverobots.com/wp-content/uploads/2016/05/robot-pepper.jpg")
    #show_image("https://aliverobots.com/wp-content/uploads/2016/05/robot-pepper.jpg")
    #hide_image()
    #set_tablet_bright(50)
    #set_tablet_volume(50)


    
def message_manage(key, msg):
        switch_accion = {
            #ActivityServices-------------------------------------------------------
            "RUNANIMATION": "run_animation", #funcionando
            "GOTOPOSTURE": "go_to_posture",  #No hace nada-------------------------
            "DETECTNEWFACE ": "learn_face",  #funcionando
            "GETFACELIST": "get_face_list",  #funcionando
            "ACTIVATE": "activate_blinking", #
            "ACTIVATELIFESIGNALS": "activate_life_signals", #
            "ACTIVATELIFESGINALSINT": "activate_life_signals_awareness", #
            "DEFENGAGEMENTTYPE": "set_engagement_type", #
            "ACTIVATEACTIVEHEARING": "activate_hearing_movement",#
            "ACTIVATESPEAKMOVEMENTS":"activate_speak_movements", #
            "DEFCONVERSATIONMODE":"define_conversation_mode", #
            "ACTIVATEPUSHREFLEXES":"activate_push_reflexes", #
            "ACIVATEBREATHMOV":"activate_breath_movement", #
            "ACTIVATEMOVDETECTION":"activate_movement_detection", #
            "ACTIVATEFACEDETEC":"activate_face_detection", #
            "ACTIVATECOLISSIONDETECT":"activate_colission_detection", #
            #EnergyServices-------------------------------------------------------
            "ACTIVATEMONITORINGCHARGESERV":"activate_monitoring_charge_service", #
            "GETBATTERY":"get_battery", #
            "GETTEMP":"get_temperature",#
            #HumanServices-------------------------------------------------------
            "GETEMOTIONSTATE":"get_emotion_state", #
            "LOGIN":"login", #
            #LocationServices-------------------------------------------------------
            "SEARCHFREEZONE":"search_free_zone",
            "GETFREEZONES":"get_free_zones",
            "GETROBOTPOSITION":"get_robot_position",
            #MovementServices-------------------------------------------------------
            "MOVE":"move",
            "MOVEFORWARD":"move_forward",
            "MOVETO":"move_to",
            "MOVETOPOSITION":"move_to_position",
            #RobotStateServices-------------------------------------------------------
            "WAKEUP":"wake_up",
            "SUSPEND":"suspend",
            "SETREFRESHTIMESENSORS":"set_refresh_time_sensors",
            "ACTIVATERASTA ":"activate_rasta",
            "RANDOMEYES":"random_eyes",
            "SETLEDSINTENSITY":"set_leds_intensity",
            "CHANGELEDCOLOR":"change_led_color",
            "ACTIVATESTIFFNESS":"activate_stiffness",
            #TabletServices-------------------------------------------------------
            "TABLETON":"tablet_on",
            "WAKETABLET":"wake_tablet",
            "SUSPENDTABLET":"suspend_tablet",
            "TABLETOFF":"tablet_off",
            "SHOWVIDEO":"show_video",
            "QUITVIDEO":"quit_video",
            "PAUSEVIDEO":"pause_video",
            "RESUMEVIDEO":"resume_video",
            "PRELOADIMG":"preload_image",
            "SHOWIMG":"show_image",
            "HIDEIMG":"hide_image",
            "SETTABLETBRIGHT":"set_tablet_bright",
            "SETTABLETVOL":"set_tablet_volume",
            #VoiceServices-------------------------------------------------------
            "SAY":"say",
            "STOPALL":"stop_all",
            "SETSAYVOLUMN":"set_say_volume",
            "SAYWITHMOVEMENT":"say_with_movement",
            "SETSYSTEMVOLUME":"set_system_volume",
            "PLAYSOUND":"play_sound",
            "PAUSESOUND":"pause_sound",
            "ACTIVATEVOICEEMOANAL":"activate_voice_emotion_analysis",
            "DESACTIVVOICEEMOANAL":"desactivate_voice_emotion_analysis",
            "ACTVOICERECOG":"activate_voice_recognition",
            "DESACTVOICERECOG":"desactivate_voice_recognition",
            "ACTIVATECONVTOPIC":"activate_conversational_topic",
            "LOADCONVTOPIC":"load_conversational_topic",
            "UNLOADCONVTOPIC":"unload_conversational_topic",
            "DEACTCONVTOPIC":"deactivate_conversational_topic",
            "SAYUNDERTOPICCONTEXT":"say_under_topic_context",
            "SETTOPICFOCUS":"set_topic_focus"
        }
        func = switch_accion.get(key)
        func(msg)

def BEstadoEmocionalPwA(info_human_state):
    if info_human_state == "attention":
        val_attention = getAttention()
    else:
        val_attention = getAttention()
    return val_attention

def getEmotionalReaction():
    #Returns:	The detected reaction.
    return alMood.getEmotionalReaction()

def getAttention():
    #ï¿½unengagedï¿½: attentionLevel < 0.6
    #ï¿½semiEngagedï¿½ : 0.6 <= attentionLevel < 0.9
    #ï¿½fullyEngagedï¿½: attentionLevel >= 0.9
    return alMood.attention()
    
# Choregraphe bezier export in Python.
def dance_macarena():
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
      motion = ALProxy("ALMotion")
      motion.angleInterpolationBezier(names, times, keys)
    except BaseException, err:
      print err
#Prueba!!!!!!!!!
    run_animation(names, times, keys)


def run_animation(animation_names, animation_times, animation_keys):
    try:
      # uncomment the following line and modify the IP if you use this script outside Choregraphe.
      # motion = ALProxy("ALMotion", IP, 9559)
      alMotion.angleInterpolationBezier(animation_names, animation_times, animation_keys)
    except BaseException, err:
      print err
      
#def run_animation( animation_path, animation_tag):
#    alAnimationPlayer.runTag(animation_path, animation_tag)

def go_to_posture( posture, speed):
    alRobotPosture.goToPosture(posture, speed)

#Learns a new face and adds it in the database under the specified name.
def learn_face(person_name):
    #Returns: true if the operation succeeded
    return alFaceDetection.learnFace(person_name)

#Gets a list containing the name of each learned face. The size of this list is always
#equal to the number of faces in the data base.
def get_face_list():
    return alFaceDetection.getLearnedFacesList()

#Enables or disables the autonomous blinking.
def activate_blinking(enabled):
    alAutonomousBlinking.setEnabled(enabled)

#Enables or disables the background movements.
def activate_life_signals(enabled):
    alBackgroundMovement.setEnabled(enabled)

#Enables or disables basic awareness.
def activate_life_signals_awareness(enabled):
    alBasicAwareness.setEnabled(enabled)

#Sets the engagement mode.
#https://developer.softbankrobotics.com/pepper-naoqi-25/naoqi-developer-guide/naoqi-apis/naoqi-interaction-engines/albasicawareness#albasicawareness-engagement-modes
def set_engagement_type(modeName):
    alBasicAwareness.setEngagementMode ( modeName )

#Enables or disables the listening movements.
def activate_hearing_movement(enabled):
    alListeningMovement.setEnabled(enabled)

#Enables or disables the speaking movements.
def activate_speak_movements(enabled):
    alSpeakingMovementProxy.setEnabled(enabled)

#Sets the current speaking movement mode.  Random - Contextual 
def define_conversation_mode(mode):
    alSpeakingMovementProxy.setMode(mode)

#Enable/disable the push-recovery reflex of the robot, but only if allowed by the owner. If not allowed, an exception is thrown.
def activate_push_reflexes(enabled):
    alMotionProxy.setPushRecoveryEnabled(enabled)
    
 #Starts or stops breathing animation on a chain.   
def activate_breath_movement(extremity_to_enabled, enabled):
    alMotionProxy.setBreathEnabled(extremity_to_enabled, enabled)

#Enables or disables the movement detection to detect people. This can make the overall process slower if enabled
def activate_movement_detection(enabled):
    alPeoplePerception.setMovementDetectionEnabled(enabled)
    
#Enables/disables the face recognition process. The remaining face detection process will be faster if face recognition is disabled. Face recognition is enabled by default.
def activate_face_detection(enabled):
    alFaceDetection.setRecognitionEnabled(enabled)

#Enable/Disable Anti-collision protection of the arms of the robot.
def activate_colission_detection(chainName,enabled):
    alMotionProxy.setCollisionProtectionEnabled(chainName,enabled)

#Enables or disables power monitoring.
def activate_monitoring_charge_service(enabled):
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
#Se verifica el login, es decir, se revisa que alguno de los usuarios con sesión activa coincida con
#el que está interactuando con el robot
def login():

    for i in alUserSession.getOpenUserSessions():
        if i == alUserSession.getFocusedUser():
            return True
            
    return False



#Looks for a free circular zone of a specified radius not farer than a specified displacement.
def search_free_zone(radius, displacement):
    return alNavigationProxy.searchFreeZone(radius,displacement)

#Looks for a free circular zone of a specified radius not farer than a specified displacement
def get_free_zones(radius, displacement):
    return alNavigationProxy.findFreeZone(radius, displacement)

""" No se si exista problema por utilizar ALLocalizationProxy o simplemente ALLocalization"""
#Gets the coordinates x, y, theta of the pose2D of the robot
def get_robot_position(enabled):
    return alLocalizationProxy.getRobotPosition(enabled)

#Makes the robot move at the given velocity, expressed in FRAME_ROBOT
def move(x,y,speed):
    alMotion.move(x,y,speed)

#Makes the robot move to the given pose in the ground plane, relative to FRAME_ROBOT
def move_forward(x,y,speed):
    alMotion.moveTo(x,y,speed)

#Makes the robot navigate to a relative metrical target pose2D expressed in FRAME_ROBOT.
def move_to(x,y):
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
"""
#Sets the color of an RGB led using  color code.
def change_led_color(sensor, color):
    alLedsProxy.fadeRGB(sensor, color)


def  activate_stiffness(enabled):
            ALGUNACOSA.SetSmartStiffnessEnabled


"""
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
def show_video(url):
    alTabletService.playVideo(url)

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
    pass

#Shows the image in the tablet for the user
def show_image(url):
    alTabletService.showImage(url)
    pass

#Hide image currently displayed.
def hide_image():
    alTabletService.hideImage()

#Set tablet brightness.
def set_tablet_bright(brightness):
    alTabletService.setBrightness(brightness)

#Configure the media volume of the tablet.
def set_tablet_volume(volume):
    alTabletService.setVolume(volume)

#Says the specified string of characters.
def say(text):
    alTexToSpeech.say(text)

 #   This method stops the current and all the pending tasks immediately.
def stop_all():
    alTexToSpeech.stopAll()


#Sets the current gain applied to the signal synthesized by the text to speech engine.
def set_say_volume(volume):
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
def activate_voice_emotion_analysis(subscriberName):
    alVoiceEmotionAnalysis.subscribe(subscriberName) 

#Unsubscribes to ALVoiceEmotionAnalysis .
def desactivate_voice_emotion_analysis():
    alVoiceEmotionAnalysis.unsubscribe(subscriberName)

#Subscribes to ALSpeechRecognition 
def activate_voice_recognition(subscriber):
    alSpeechRecognition.subscribe(subscriber)

#Unsubscribes to ALSpeechRecognition
def desactivate_voice_recognition():
    alSpeechRecognition.unsubscribe(subscriber)

#Adds the specified topic to the list of the topics that are currently used by the dialog engine to parse the humanï¿½s inputs.
def activate_conversational_topic(topicName):
    alDialogProxy.activateTopic(topicName)

#Loads the topic, exports and compiles the corresponding context files so that they are ready to be used by the speech recognition engine 
def load_conversational_topic(topicName):
     alDialogProxy.loadTopic(path)

#Unloads the specified topic and frees the associated memory.
def unload_conversational_topic(topicName):
     alDialogProxy.unloadTopic(topicName)

#Removes the specified topic from list of the topics that are currently used by the dialog engine to parse the humanï¿½s inputs.
def deactivate_conversational_topic(topicName):
     alDialogProxy.deactivateTopic(topicName)

#Says a tagged sentence from a topic.
def say_under_topic_context(topic, tag):
    alDialogProxy.say(topic,tag)

#If multiple topics can be active at the same time, only one of them is used to generate proposals.
def set_topic_focus(topicName):
    alDialogProxy.setFocus(topicName)




    
def hablar(texto_hablar):
    alTexToSpeech.say(texto_hablar)
    
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
    frase_inicial = "Sï¿½ que estï¿½s triste," #realizar una pausa! IMPORTANTE!!!!!!!!!!!!!!
    switch_conversacion = {
        1: "pero sabes? la tristeza nos puede ayudar a hacer una introspeccion y esto nos permite estar mejor.",
        2: "Aveces la tristeza nos puede hacer sentir mal, te cuento que como el resto de emociones tiene una funciï¿½n que nos permite volver mejores",
        3: "ï¿½Sabï¿½as que usualmente necesitamos la ayuda de otras personas cuando estamos tristes?",
        4: "y te sientes mal, pero esta emociï¿½n te permite sanar heridas"
    }
    frase_principal = "ï¿½Te gustarï¿½a escuchar una canciï¿½n?"

def conversacion_alegre():
    frase_inicial = "Me gusta verte asï¿½,"
    switch_conversacion = {
        1: "La alegrï¿½a te genera una sonrisa hermosa",
        2: "Las personas se desempeï¿½an mucho mejor cuando estï¿½n alegres",
        3: "La alegrï¿½a es parte de la vida y es vital para disfrutar nuestra estadia",
        4: "luces genial, esa alegrï¿½a que reflejas te hace ver mejor"
    }
    frase_principal = "ï¿½Escuchamos una canciï¿½n?"

def conversacion_ira(genero):
    frase_inicial = "Veo que estï¿½s enfadad"

    if genero =='M':
        frase_inicial = frase_inicial + "o"
    else:
        frase_inicial = frase_inicial + "a"
    
    frase_inicial = frase_inicial + ","

    switch_conversacion = {
        1: "es normal sentir ira, pero no debes dejarla salir de control porque no te permite tomar buenas decisiones",
        2: "aveces la ira te puede hacer sentir mal, sin embargo debes tomar las cosas con serenidad",
        3: "pero sabes? las cosas tienen la importancia que tï¿½ les das",
        4: "la ira te indispone y dificulta que pienses con claridad"
    }
    frase_principal = "ï¿½si quieres podemos escuchar una canciï¿½n para relajarnos?"

def presentarse(params):
    pass
def ayuda(params):
    pass
def consultar_actividades(params):
    pass
def saludar(params):
    pass
def despedirse(params):
    pass
def reinicio_actividad(params):
    pass
def cancelar_actividad(params):
    pass
def cancelar_interaccion(params):
    pass
def bailar(params):
    pass
def karaoke(params):
    pass
def bateria(params):
    pass
def cancion(params):
    pass
def falta_informacion(params):
    pass
def notificacion(params):
    pass
def cambiar_dificultad(params):
    pass
def seleccionar_cuento(params):
    pass
def animar(params):
    pass
def mantener_atencion(params):
    pass
def reanudar_actividad(params):
    pass
def reporte(params):
    pass
def pausa_interaccion(params):
    pass
def musicoterapia(params):
    pass
def modificar_historia(params):
    pass


#----------------------------------------------------------------------------------------------------

print("Server starting...pop")
HOST = '192.168.2.7' #socket.gethostbyname(socket.gethostname()) # Standard loopback interface             address (localhost)
HOST_LOCAL = '127.0.0.1'
print("Server starting on", HOST)
PORT = 7895    # Port to listen on (non-privileged ports are > 1023)
print("Server starting...pop0000000000000000")
ADDR = (HOST_LOCAL, PORT)
server = None
HEADER = 1024
FORMAT = 'utf-8'
DISCONNECT_MESSAGE = "!DISCONNECT"
print("Server starting...pop1111111111111111111")

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

#ALProxy(" ALBasicAwarenessProxy", HOST, 9559)
#ALProxy(" ALBasicAwarenessProxy", HOST, 9559)
#ALProxy(" ALBasicAwarenessProxy", HOST, 9559)
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
conn, addr = server.accept()
thread = threading.Thread(target=handle_client, args=(conn, addr))
thread.start()
