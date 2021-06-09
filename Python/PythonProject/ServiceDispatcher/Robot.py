import threading
import time

from EventHandler import PepperModuleV2
from ServiceDispatcher.Animation import Animation
from Utils.Emotion import Emotion
from Utils.Topics import *
from Utils.Utils import activities_running, send


# ----------------------------------------------------------------------------Robot class---------------------------------------------------------------------------------------------
class Robot:
    def __init__(self, app, session):
        self.app = app
        self.current_emomap = None
        print "INICIA ROBOT CARGADO Y LISTO"
        self.session = session
        self.aLAutonomousLife = session.service("ALAutonomousLife")
        self.aLAutonomousBlinking = session.service("ALAutonomousBlinking")
        self.aLAutonomousBlinking.setEnabled(False)
        self.aLAutonomousLife.setState("interactive")
        self.alProxy = session.service("ALMemory")
        self.alTabletService = self.session.service("ALTabletService")
        self.alLedsProxy = session.service("ALLeds")
        self.alTexToSpeech = session.service("ALTextToSpeech")
        self.alMood = session.service("ALMood")
        self.alAnimationPlayer = session.service("ALAnimationPlayer")
        self.alMotion = session.service("ALMotion")
        self.alTabletService = self.session.service("ALTabletService")
        self.alRobotPosture = session.service("ALRobotPosture")
        self.alAutonomousBlinking = session.service("ALAutonomousBlinking")
        self.alBackgroundMovement = session.service("ALBackgroundMovement")
        self.alBackgroundMovement.setEnabled(False)
        self.alListeningMovement = session.service("ALListeningMovement")
        self.alSpeakingMovementProxy = session.service("ALSpeakingMovement")
        self.alMotionProxy = session.service("ALMotion")
        self.alBatteryProxy = session.service("ALBattery")
        self.alBodyTemperatureProxy = session.service("ALBodyTemperature")
        self.alUserSession = session.service("ALUserSession")
        self.alNavigationProxy = session.service("ALNavigation")
        self.alLocalizationProxy = session.service("ALLocalization")
        self.alSensorsProxy = session.service("ALSensors")
        self.alAnimatedSpeech = session.service("ALAnimatedSpeech")
        self.alAudioDevice = session.service("ALAudioDevice")
        self.alAudioPlayer = session.service("ALAudioPlayer")
        print "MEDIO ROBOT CARGADO Y LISTO"
        self.alVoiceEmotionAnalysis = session.service("ALVoiceEmotionAnalysis")
        self.alSpeechRecognition = session.service("ALSpeechRecognition")
        self.specchRecog = session.service("ALSpeechRecognition")
        self.specchRecog.pause(True)
        self.alTabletService.hideImage()
        self.alFaceDetection = session.service("ALFaceDetection")
        self.alFaceDetection.setTrackingEnabled(True)
        self.alFaceDetection.setRecognitionEnabled(False)
        self.alBasicAwareness = session.service("ALBasicAwareness")
        self.emotionStateRobot = Emotion()
        self.alBasicAwareness.setEngagementMode("FullyEngaged")
        self.alBasicAwareness.setTrackingMode("BodyRotation")
        self.alBasicAwareness.setStimulusDetectionEnabled("People", False)
        self.alBasicAwareness.setStimulusDetectionEnabled("Sound", False)
        self.alBasicAwareness.setStimulusDetectionEnabled("Movement", False)
        self.alBasicAwareness.setStimulusDetectionEnabled("NavigationMotion", False)
        self.alBasicAwareness.startAwareness()
        self.alBasicAwareness.setEnabled(True)
        self.alPeoplePerception = session.service("ALPeoplePerception")
        self.alPeoplePerception.setMovementDetectionEnabled(False)
        # self.alTabletService = None;
        self.joy = 0.0
        self.sorrow = 0.0
        self.attention = 0.0
        self.ease = 0.0
        self.topicMap = {}
        self.prof_emotions = dict()
        self.sensorsModule = None
        self.animation = Animation(self.session)
        # print "GUI LISTA"
        self.topicContentMap = {"basicoTopic": topic_content_1,
                                "alegreTopic": topico_alegre,
                                "sadTopic": topico_triste,
                                "iraTopic": topico_ira,
                                "ayudaTopic": topico_ayuda,
                                "normalTopic": topico_normal,
                                "retroCuentoTopic": topico_retroCuento,
                                "retroCancionTopic": topico_retroCancion,
                                "saludarTopic": topico_saludable,
                                "soundTopic": topico_sonido,
                                "blankaTopic": topico_blank
                                }
        self.path = {
            "VSAD": "boston_animation_library/Stand/cry",
            "VHAPPY": "animations/Stand/Emotions/Positive/Happy_1",
            "SILBAR": "animations/Stand/Waiting/CallSomeone_1"
        }

        self.alDialogProxy = session.service("ALDialog")
        print "AWITA A MIL", self.alDialogProxy.getAllLoadedTopics()
        # Clean Topics
        self.alDialogProxy.stopTopics(self.alDialogProxy.getAllLoadedTopics())
        self.alDialogProxy.setLanguage("Spanish")
        # self.alDialogProxy.stopTopics(self.alDialogProxy.getAllLoadedTopics())
        print "Medio PAPITAS A MIL", self.alDialogProxy.getAllLoadedTopics()
        self.alDialogProxy.setConfidenceThreshold("BNF", 0.3, "Spanish")
        if len(self.alDialogProxy.getAllLoadedTopics()) < 3:
            print "Iniciando Topics"
            self.init_topics()

        print "PAPITAS A MIL", self.alDialogProxy.getAllLoadedTopics()
        print "MILTON", self.alDialogProxy.getActivatedTopics()

        # print "ROBOT CARGADO Y LISTO"
        # time.sleep(10)
        self.alTexToSpeech.say("Estoy preparado")
        time.sleep(5)
        print "ROBOT CARGADO Y LISTO"

        # The list have the function on the first place, if the activity most return an ack on the second, type on the third and callback response the fourth
        self.__modules = {
            # ActivityServices-------------------------------------------------------
            "RUNANIMATION": [self.run_animation, True, "act", True],  # funcionando
            "PLAYANIMATION": [self.run_animation_play, True, "act", False],  # funcionando
            "GOTOPOSTURE": [self.go_to_posture, False, "rob", False],  # No hace nada-------------------------
            "DETECTNEWFACE ": [self.learn_face, True, "int", False],  # funcionando
            "GETFACELIST": [self.get_face_list, False, "int", False],  # funcionando
            "ACTIVATE": [self.activate_blinking, True, "rob", False],  # Parece que funciona, no bota error
            "ACTIVATELIFESIGNALS": [self.activate_life_signals, True, "rob", False],  #
            "ACTIVATELIFESGINALSINT": [self.activate_life_signals_awareness, True, "rob", False],  #
            "DEFENGAGEMENTTYPE": [self.set_engagement_type, False, "int", False],  #
            "ACTIVATEACTIVEHEARING": [self.activate_hearing_movement, True, "rob", False],  #
            "ACTIVATESPEAKMOVEMENTS": [self.activate_speak_movements, True, "rob", False],  #
            "DEFCONVERSATIONMODE": [self.define_conversation_mode, True, "int", False],  #
            "ACTIVATEPUSHREFLEXES": [self.activate_push_reflexes, True, "rob", False],  #
            "ACIVATEBREATHMOV": [self.activate_breath_movement, True, "rob", False],  #
            "ACTIVATEMOVDETECTION": [self.activate_movement_detection, True, "rob", False],  #
            "ACTIVATEFACEDETEC": [self.activate_face_detection, True, "rob", False],  #
            "ACTIVATECOLISSIONDETECT": [self.activate_colission_detection, True, "rob", False],  #
            # EnergyServices-------------------------------------------------------
            "ACTIVATEMONITORINGCHARGESERV": [self.activate_monitoring_charge_service, True, "rob", False],  #
            "GETBATTERY": [self.get_battery, False, "rob", False],  #
            "GETTEMP": [self.get_temperature, False, "rob", False],  #
            # HumanServices-------------------------------------------------------
            "GETEMOTIONSTATE": [self.get_emotion_state, False, "emo", False],  #
            "LOGIN": [self.login, False, "act", False],  #
            # LocationServices-------------------------------------------------------
            "SEARCHFREEZONE": [self.search_free_zone, False, "act", False],  #
            "GETFREEZONES": [self.get_free_zone, False, "act", False],  #
            "GETROBOTPOSITION": [self.get_robot_position, False, "act", False],  #
            # MovementServices-------------------------------------------------------
            "MOVE": [self.move, True, "act", True],  ##################################################################
            "MOVEFORWARD": [self.move_forward, True, "act", True],  #
            "MOVETO": [self.move_to, True, "act", True],  #
            "MOVETOPOSITION": [self.move_to_position, True, "act", True],  #
            "INITIALCONF": [self.initial_conf, False, "rob", True],
            # RobotStateServices-------------------------------------------------------
            "WAKEUP": [self.wake_up, True, "act", False],  #
            "SUSPEND": [self.suspend, True, "act", False],  #
            "SETREFRESHTIMESENSORS": [self.set_refresh_time_sensors, False, "act", False],
            # Hay que crear un modulo para probar
            "ACTIVATERASTA ": [self.activate_rasta, False, "act", False],  #
            "RANDOMEYES": [self.random_eyes, False, "act", False],  #
            "SETLEDSINTENSITY": [self.set_leds_intensity, True, "act", False],  #
            "CHANGELEDCOLOR": [self.change_led_color, True, "act", False],  #
            "ACTIVATESTIFFNESS": [self.activate_stiffness, True, "act", False],  #
            "ROBOTEMOTION": [self.change_emotion_expression, False, "rob", False],
            # TabletServices-------------------------------------------------------
            "TABLETON": [self.tablet_on, True, "act", False],
            "WAKETABLET": [self.wake_tablet, True, "act", False],
            "SUSPENDTABLET": [self.suspend_tablet, True, "act", False],
            "TABLETOFF": [self.tablet_off, True, "act", False],
            "SHOWVIDEO": [self.show_video, False, "act", True],
            "QUITVIDEO": [self.quit_video, False, "act", False],
            "PAUSEVIDEO": [self.pause_video, False, "act", False],
            "RESUMEVIDEO": [self.resume_video, False, "act", False],
            "PRELOADIMG": [self.preload_image, False, "act", False],
            "SHOWIMG": [self.show_image, True, "act", True],
            "HIDEIMG": [self.hide_image, True, "act", False],
            "SETTABLETBRIGHT": [self.set_tablet_bright, True, "act", False],
            "SETTABLETVOL": [self.set_tablet_volume, True, "act", False],
            # VoiceServices-------------------------------------------------------
            "SAY": [self.say, True, "act", True],
            "STOPALL": [self.stop_all, True, "act", False],
            "SETSAYVOLUMN": [self.set_say_volume, True, "act", False],
            "SAYWITHMOVEMENT": [self.say_with_movement, True, "act", True],
            # -------------------------Callback realizado
            "SETSYSTEMVOLUME": [self.set_system_volume, True, "act", False],
            "PLAYSOUND": [self.play_sound, True, "act", True],
            "PAUSESOUND": [self.pause_sound, True, "act", False],
            "ACTIVATEVOICEEMOANAL": [self.activate_voice_emotion_analysis, True, "act", False],
            "DESACTIVVOICEEMOANAL": [self.desactivate_voice_emotion_analysis, True, "act", False],
            "ACTVOICERECOG": [self.activate_voice_recognition, True, "act", False],
            "DESACTVOICERECOG": [self.desactivate_voice_recognition, True, "act", False],
            "ACTIVATECONVTOPIC": [self.activate_conversational_topic, True, "rob", False],
            "DEACTCONVTOPIC": [self.deactivate_topics, True, "rob", False],
            "LOADCONVTOPIC": [self.load_conversational_topic, True, "act", False],
            "UNLOADCONVTOPIC": [self.unload_conversational_topic, True, "act", False],
            "SAYUNDERTOPICCONTEXT": [self.say_under_topic_context, True, "act", False],
            "SETTOPICFOCUS": [self.set_topic_focus, True, "act", False],
            "FORCEOUT": [self.force_out, True, "int", False],
            "KILLALL": [self.kill_all, True, "int", False],

        }

        # Declare the modules --------------------------------------------------------------------------------

    def getFunction(self, fun):
        return self.__modules.get(fun)[0]

    def getAck(self, fun):
        return self.__modules.get(fun)[1]

    def getType(self, fun):
        return self.__modules.get(fun)[2]

    def mustBeResponse(self, fun):
        return self.__modules.get(fun)[3]

    def run_animation(self, params):
        # Get the function
        animation_name = params.get("TAGSDANCE")
        # Get the params of the function
        # Invoke the function
        # animation_name(animation_factor)

        try:
            print animation_name
            animation_function = self.animation.getAnimation(animation_name)
            names, times, keys = animation_function()
            # times = self.change_speed(animation_factor, times)
            self.play_animation(names, times, keys)
        except BaseException, err:
            print err

    def getEmotionalReaction(self):
        # Returns:	The detected reaction.
        return self.alMood.getEmotionalReaction()

    def getAttention(self):
        # "unengaged": attentionLevel < 0.6
        # "semiEngaged" : 0.6 <= attentionLevel < 0.9
        # "fullyEngaged": attentionLevel >= 0.9
        return self.alMood.attention()

    def play_animation(self, animation_names, animation_times, animation_keys):
        try:
            # uncomment the following line and modify the IP if you use this script outside Choregraphe.
            # motion = ALProxy("ALMotion", IP, 9559)
            self.alMotion.angleInterpolation(animation_names, animation_keys,
                                             self.change_speed(self.emotionStateRobot.getFactorVelocity(),
                                                               animation_times), True)

        except BaseException, err:
            print err

    def run_animation_play(self, params):
        path = self.path[params.get("EMOTION")]
        self.alAnimationPlayer.run(path, _async=True)

    # Animations
    def change_speed(self, factor, times):
        timesfinal = list()
        for i in times:
            times2 = list()
            for j in i:
                times2.append(j * factor)
            timesfinal.append(times2)
        return timesfinal

    # def dance( self, animation, factor = 1 ):
    #     try:
    #         # uncomment the following line and modify the IP if you use this script outside Choregraphe.
    #         # motion = ALProxy("ALMotion", IP, 9559)
    #         # motion = ALProxy("ALMotion")
    #         # motion.angleInterpolation(names, keys, times, True)
    #         animationFunction = Dance.getAnimation( animation )
    #         names, times, keys = animationFunction()
    #         times = self.change_speed(factor, times)
    #         self.play_animation(names, times, keys)
    #     except BaseException, err:
    #         print err

    # Prueba!!!!!!!!!

    def go_to_posture(self, params):
        posture = params.get("postura")
        speed = params("velocidad")
        self.alRobotPosture.goToPosture(posture, speed)

    # Learns a new face and adds it in the database under the specified name.
    def learn_face(self, params):
        person_name = params.get("DETECTPWA")
        # Returns: true if the operation succeeded
        return self.alFaceDetection.learnFace(person_name)

    # Gets a list containing the name of each learned face. The size of this list is always
    # equal to the number of faces in the data base.
    def get_face_list(self):
        return self.alFaceDetection.getLearnedFacesList()

    # Enables or disables the autonomous blinking.
    def activate_blinking(self, params):
        enabled = params.get("ACTIVATE")
        self.alAutonomousBlinking.setEnabled(enabled)

    # Enables or disables the background movements.
    def activate_life_signals(self, params):
        enabled = params.get("ACTIVATELIFESIGNALS")
        self.alBackgroundMovement.setEnabled(enabled)

    # Enables or disables basic awareness.
    def activate_life_signals_awareness(self, params):
        enabled = params.get("ACTIVATELIFESIGNALSINT")
        self.alBasicAwareness.setEnabled(enabled)

    # Sets the engagement mode.
    # https://developer.softbankrobotics.com/pepper-naoqi-25/naoqi-developer-guide/naoqi-apis/naoqi-interaction-engines/albasicawareness#albasicawareness-engagement-modes
    def set_engagement_type(self, params):
        modeName = params.get("DEFENGAGEMENTTYPE")
        self.alBasicAwareness.setEngagementMode(modeName)

    # Enables or disables the listening movements.
    def activate_hearing_movement(self, params):
        enabled = params.get("ACTIVATEACTIVEHEARING")
        self.alListeningMovement.setEnabled(enabled)

    # Enables or disables the speaking movements.
    def activate_speak_movements(self, params):
        enabled = params.get("ACTIVATESPEAKMOVEMENTS")
        self.alSpeakingMovementProxy.setEnabled(enabled)

    # Sets the current speaking movement mode.  Random - Contextual
    def define_conversation_mode(self, mode):
        self.alSpeakingMovementProxy.setMode(mode)

    # Enable/disable the push-recovery reflex of the robot, but only if allowed by the owner. If not allowed, an exception is thrown.
    def activate_push_reflexes(self, params):
        enabled = params.get("ACTIVATEPUSHREFLEXES")
        self.alMotionProxy.setPushRecoveryEnabled(enabled)

    # Starts or stops breathing animation on a chain.
    def activate_breath_movement(self, params):
        extremity_to_enabled = "Body"
        enabled = params.get("ACTIVATEBREATHMOV")
        self.alMotionProxy.setBreathEnabled(extremity_to_enabled, enabled)

    # Enables or disables the movement detection to detect people. This can make the overall process slower if enabled
    def activate_movement_detection(self, params):
        enabled = params.get("ACTIVATEMOVDETECTION")
        self.alPeoplePerception.setMovementDetectionEnabled(enabled)

    # Enables/disables the face recognition process. The remaining face detection process will be faster if face recognition is disabled. Face recognition is enabled by default.
    def activate_face_detection(self, params):
        enabled = params.get("ACTIVATELIFESIGNALSINT")
        self.alFaceDetection.setRecognitionEnabled(True)

    # Enable/Disable Anti-collision protection of the arms of the robot.
    def activate_colission_detection(self, params):
        chainName = "Arms"
        enabled = params.get("ACTIVATECOLISSIONDETECT")
        self.alMotionProxy.setCollisionProtectionEnabled(chainName, enabled)

    # Enables or disables power monitoring.
    def activate_monitoring_charge_service(self, params):
        enabled = params.get("ACTIVATEMONITORINGCHARGESERV")
        self.alBatteryProxy.enablePowerMonitoring(enabled)

    # Get battery charge.
    def get_battery(self):
        return self.alBatteryProxy.getBatteryCharge()

    # Return the actual state of the temperature diagnosis. Only the highest level of failure is returned.
    def get_temperature(self):
        return self.alBodyTemperatureProxy.getTemperatureDiagnosis()

    # Gets the emotional state of the current focused user through a PersonState struct.
    def get_emotion_state(self):
        expressions = {
            "calm": {"value": 0.0, "confidence": 0.0},
            "anger": {"value": 0.0, "confidence": 0.0},
            "joy": {"value": self.joy, "confidence": 1.0},
            "sorrow": {"value": self.sorrow, "confidence": 1.0},
            "laughter": {"value": 0.0, "confidence": 0.0},
            "excitement": {"value": 0.0, "confidence": 0.0},
            "surprise": {"value": 0.0, "confidence": 0.0}
        }
        PersonData = {
            "valence": {"value": 0.0, "confidence": 0.0},
            "attention": {"value": self.attention, "confidence": 1.0},
            "bodyLanguageState":
                {
                    "ease": {"level": self.ease, "confidence": 1.0}
                },
            "smile": {"value": 0.0, "confidence": 0.0},
            "expressions": expressions
        }
        print PersonData
        return PersonData
        # return self.alMood.currentPersonState()

    #                        NI PINSHI IDEA DE COMO DEJAR EL LOGIN

    # Se verifica el login, es decir, se revisa que alguno de los usuarios con sesion activa coincida con
    # el que esta interactuando con el robot
    def login(self):
        for i in self.alUserSession.getOpenUserSessions():
            if i == self.alUserSession.getFocusedUser():
                return True

        return False

    # Looks for a free circular zone of a specified radius not farer than a specified displacement.
    def search_free_zone(self, params):
        radius = params.get("RADIO")
        displacement = params.get("DISTANCIAMAX")
        return self.alNavigationProxy.findFreeZone(radius, displacement)

    # Looks for a free circular zone of a specified radius not farer than a specified displacement
    def get_free_zone(self, radius, displacement):
        return self.alNavigationProxy.getFreeZone(radius, displacement)

    # Gets the coordinates x, y, theta of the pose2D of the robot
    def get_robot_position(self, params):
        enabled = params.get("GETROBOTPOSITION")
        return self.alLocalizationProxy.getRobotPosition(enabled)

    # Makes the robot move at the given velocity, expressed in FRAME_ROBOT
    # Z is rotation
    def move(self, x, y, z):
        self.alMotion.move(x, y, z)

    # Makes the robot move to the given pose in the ground plane, relative to FRAME_ROBOT
    def move_forward(self, x, y, speed):
        self.alMotion.moveInit()
        self.alMotion.moveTo(x, y, speed)

    # Makes the robot navigate to a relative metrical target pose2D expressed in FRAME_ROBOT.
    def move_to(self, params):
        self.alMotion.moveInit()
        x = params.get("MOVETOX")
        y = params.get("MOVETOY")
        self.alNavigationProxy.navigateTo(x, y)

    # Go to the given position trying to perform a visual close loop with the image contained in current panorama at theta.
    def move_to_position(self, position):
        self.alLocalizationProxy.goToPosition(position)

    def initial_conf(self, prof_emotions):
        self.prof_emotions = prof_emotions["INITIALCONF"]
        print("VER IDENT ", self.prof_emotions)
        if len(self.prof_emotions) == 5:
            try:
                self.init_timers()
                self.sensorsModule = PepperModuleV2.pepperModuleV2(self.session)
            except Exception, e:
                print "Main Error"
                print e
                exit(1)

    def request_posture_change(self, params):
        actions = self.current_emomap[params.get("ACTION")]
        names = list()
        times = list()
        keys = list()
        for name, action in actions.items():
            names.append(name)
            keys.append(action["key"])
            times.append(action["time"])

        self.play_animation(names, times, keys)

    # The robot wakes up
    def wake_up(self):
        self.alMotionProxy.wakeUp()

    # The robot rests: goes to a relaxed and safe position and sets Motor off.
    def suspend(self):
        self.alMotionProxy.rest()

    # Updates the period if relevant.
    def set_refresh_time_sensors(self, sensor, time):
        self.alSensorsProxy.updatePeriod(sensor, time)

    # Launch a green/yellow/red rasta animation on all body.
    def activate_rasta(self, duration):
        self.alLedsProxy.rasta(duration)

    # Launch a random animation in eyes
    def random_eyes(self, duration):
        self.alLedsProxy.randomEyes(duration)

    # Sets the intensity of a LED or Group of LEDs.
    def set_leds_intensity(self, sensor, intensity):
        self.alLedsProxy.setIntensity(sensor, intensity / 100)

    # Sets the color of an RGB led using  color code.
    def change_led_color(self, color, rotationDuration):
        # color is an hexa number
        self.alLedsProxy.fadeRGB("AllLeds", int(color), 0)

    # Enable or Disable the smart stiffness reflex for all the joints (True by default).
    # The update takes one motion cycle.
    def activate_stiffness(self, params):
        return self.alMotion.setSmartStiffnessEnabled(params.get("ACTIVATESTIFFNESS"))

    def change_emotion_expression(self, params):
        self.emotionStateRobot.setToneSpeech(params.get("tonoHabla"))
        self.emotionStateRobot.setLedColor(params.get("COLOR"))
        self.emotionStateRobot.setRotationEyesColor(params.get("DURATION"))
        self.emotionStateRobot.setLedIntensity(params.get("ledIntens"))
        self.emotionStateRobot.setFactorVelocity(params.get("velocidad"))
        self.emotionStateRobot.setVelocitySpeech(params.get("velHabla"))
        if "EmotionalTag" in params:
            # print "TIENE EMOTAG", params
            self.current_emomap = self.prof_emotions[params.get("EmotionalTag")]
            self.show_image({"SHOWIMG": self.current_emomap["image"]})
            emomapParams = {"ACTION": "POSTURA"}
            self.request_posture_change(emomapParams)
        self.change_led_color(self.emotionStateRobot.getLedColor(), self.emotionStateRobot.getRotationEyesColor())
        # self.set_leds_intensity("AllLeds", self.emotionStateRobot.getLedIntensity())

    # Turn on/off the tablet screen.
    def tablet_on(self):
        self.alTabletService.turnScreenOn(True)

    # Wake the tablet (from standby mode).
    def wake_tablet(self):
        self.alTabletService.wakeUp()

    # Put the tablet in sleep mode (standby mode).
    def suspend_tablet(self):
        self.alTabletService.goToSleep()

    # Turn on/off the tablet screen.
    def tablet_off(self):
        self.alTabletService.turnScreenOn(False)

    # Open a video player on tablet and play video from given url.
    def show_video(self, params):
        print "CRACK", params.get("SHOWVIDEO")
        value = params.get("SHOWVIDEO")
        self.alTabletService.playVideo("http://10.195.22.103:49152/content/media/object_id/68/res_id/0")

    # Close the video player.
    def quit_video(self):
        if activities_running.has_key("SHOWVIDEO"):
            activities_running.pop("SHOWVIDEO")
        self.alTabletService.stopVideo()

    # Pause the video playing but do not close the video player.
    def pause_video(self):
        self.alTabletService.pauseVideo()

    # Resume the video paused by ALTabletService::pauseVideo .
    def resume_video(self):
        self.alTabletService.resumeVideo()

    # Load the image to show to de user
    def preload_image(self, url):
        self.alTabletService.preLoadImage(url)

    # Shows the image in the tablet for the user
    def show_image(self, params):
        self.alTabletService.showImage(params.get("SHOWIMG"))

    # Hide image currently displayed.
    def hide_image(self, params):
        if activities_running.has_key("SHOWIMG"):
            activities_running.pop("SHOWIMG")
        self.alTabletService.hideImage()

    # Set tablet brightness.
    def set_tablet_bright(self, params):
        brightness = params.get("SETTABLETBRIGHT")
        self.alTabletService.setBrightness(brightness)

    # Configure the media volume of the tablet.
    def set_tablet_volume(self, volume):
        self.alTabletService.setVolume(volume)

    # Says the specified string of characters.
    def say(self, params, speed=None, pitch=None):
        if speed is None:
            speed = self.emotionStateRobot.getVelocitySpeech()
        if pitch is None:
            pitch = self.emotionStateRobot.getToneSpeech()
        self.alTexToSpeech.setParameter("speed", speed)
        self.alTexToSpeech.setParameter("pitchShift", pitch)
        self.alTexToSpeech.say(params.get("SAY"))

    #   This method stops the current and all the pending tasks immediately.
    def stop_all(self, params):
        self.alTexToSpeech.stopAll(params.get("STOPALL"))

    # Sets the current gain applied to the signal synthesized by the text to speech engine.
    def set_say_volume(self, params):
        volume = params.get("SETSAYVOLUMEN") / 100
        self.alTexToSpeech.setVolume(volume)

    # Say the annotated text given in parameter and animate it with animations inserted in the text.
    def say_with_movement(self, params):
        text = params.get("SAY")
        configuration = {"bodyLanguageMode": "random"}
        self.alAnimatedSpeech.say(str(text), configuration)

    # Sets the overall output volume of the system.
    def set_system_volume(self, volume):
        self.alAudioDevice.setOutputVolume(volume)

    # Starts the playback of the specified file.
    def play_sound(self, sound):
        self.alAudioPlayer.playFile(sound)

    # Pause the playback of the specified task.
    def pause_sound(self, idSound):
        if activities_running.has_key("PLAYSOUND"):
            activities_running.pop("PLAYSOUND")
        self.alAudioPlayer.pause(idSound)

    # Subscribes to ALVoiceEmotionAnalysis .
    def activate_voice_emotion_analysis(self, params):
        subscriberName = params.get("ACTIVATEVOICEEMOANAL")
        self.alVoiceEmotionAnalysis.subscribe(subscriberName)

    # Unsubscribes to ALVoiceEmotionAnalysis .
    def desactivate_voice_emotion_analysis(self):
        self.alVoiceEmotionAnalysis.unsubscribe(self.sensorsModule)

    # Subscribes to ALSpeechRecognition
    def activate_voice_recognition(self, params):
        subscriber = params.get("ACTVOICERECOG")
        self.alSpeechRecognition.subscribe(self.sensorsModule)

    # Unsubscribes to ALSpeechRecognition
    def desactivate_voice_recognition(self):
        self.alSpeechRecognition.unsubscribe(self.sensorsModule)

    ## Loading the topics directly as text strings
    def load_topic_content(self, topicName):
        self.alDialogProxy.loadTopicContent(topicName)

    def init_topics(self):
        """
        for topicName in self.topicContentMap:
            tContent = self.topicContentMap.get( topicName )
            tContent= tContent.decode('utf-8')
            # topic = self.alDialogProxy.loadTopicContent(tContent)
            topic = self.alDialogProxy.loadTopic(tContent.decode('utf-8'))
            self.topicMap[topicName] = topic
            """
        self.alDialogProxy.runTopics(topic_list)
        self.deactivate_topics(self.alDialogProxy.getActivatedTopics())

        # self.deactivate_topics(self.alDialogProxy.getActivatedTopics())
        # self.alDialogProxy.activateTopic("basicoTopic")
        # time.sleep(25.4)
        # print "Cambio"
        # self.deactivate_topics(self.alDialogProxy.getActivatedTopics())
        # self.alDialogProxy.activateTopic("alegreTopic")

    # def

    def deactivate_topics(self, topicsList):
        for topic in self.alDialogProxy.getActivatedTopics():
            self.desactivate_conversational_topic(topic)
            time.sleep(5)

    # def
    def load_conversational_topic(self, params):
        topicName = params.get("name")
        self.alDialogProxy.activateTopic(topicName)

    # Unloads the specified topic and frees the associated memory.

    def unload_conversational_topic(self, params):
        topic_name = params.get("name")
        self.alDialogProxy.deactivateTopic(topic_name)

    # Says a tagged sentence from a topic.
    def say_under_topic_context(self, params):
        topic_path = self.topicContentMap[params.get("TOPIC")]
        self.alDialogProxy.say(topic_path, params.get("TAGS"))

    # If multiple topics can be active at the same time, only one of them is used to generate proposals.
    def set_topic_focus(self, topicName):
        self.alDialogProxy.setFocus(topicName)

    def set_language(self, language):
        self.alDialogProxy.setLanguage(language)

    def subscribe_topic(self, topicName):
        self.alDialogProxy.subscribe(topicName)

    def unsubscibe_topic(self, topicName):
        self.alDialogProxy.unsubscribe(topicName)

    def hablar(self, text_to_speech, speed=None, pitch=None):
        if speed is None:
            speed = self.emotionStateRobot.getVelocitySpeech()
        if pitch is None:
            pitch = self.emotionStateRobot.getToneSpeech()

        self.alTexToSpeech.setParameter("speed", speed)
        self.alTexToSpeech.setParameter("pitchShift", pitch)
        self.alTexToSpeech.say(text_to_speech)

    def activate_conversational_topic(self, params):
        print "CRACK: ", params
        topicName = params.get("TOPICNAME")
        if topicName not in self.alDialogProxy.getActivatedTopics():
            self.alDialogProxy.activateTopic(topicName)

    def desactivate_conversational_topic(self, topic_name):

        if topic_name in self.alDialogProxy.getActivatedTopics():
            for topic in self.alDialogProxy.getActivatedTopics():
                if topic == topic_name:
                    # print "ENTRA"
                    self.alDialogProxy.deactivateTopic(topic)
                    break
        elif topic_name == "allTopics":
            self.alDialogProxy.stopTopics(self.alDialogProxy.getAllLoadedTopics())
        time.sleep(5)
        print "topicos activos", self.alDialogProxy.getActivatedTopics()

    # def

    def desactivate_conversational_topic_json(self, params):
        topicName = params.get("TOPICNAME")
        print "TOPICNAME", topicName
        self.desactivate_conversational_topic(str(topicName))

    # def

    def registrar_cuidador(params):
        pass

    def historial(params):
        pass

    def seleccionar_actividades(params):
        pass

    def cambiar_actividad(params):
        pass

    def conversacion_empatica(self, params):
        estado_emocional = params.get("estado_emocional")
        switch_emocion = {
            "triste": "conversacion_triste",
            "alegre": "conversacion_alegre",
            "ira": "conversacion_ira"
        }
        func = switch_emocion.get(params)
        func()

    def conversacion_triste(self):
        frase_inicial = "Se que estas triste,"  # realizar una pausa! IMPORTANTE!!!!!!!!!!!!!!
        switch_conversacion = {
            1: "pero sabes? la tristeza nos puede ayudar a hacer una introspeccion y esto nos permite estar mejor.",
            2: "Aveces la tristeza nos puede hacer sentir mal, te cuento que como el resto de emociones tiene una funcion que nos permite volver mejores",
            3: "Sabias que usualmente necesitamos la ayuda de otras personas cuando estamos tristes?",
            4: "y te sientes mal, pero esta emocion te permite sanar heridas"
        }
        frase_principal = "Te gustaria escuchar una cancion?"

    def conversacion_alegre(self):
        frase_inicial = "Me gusta verte asi,"
        switch_conversacion = {
            1: "La alegria te genera una sonrisa hermosa",
            2: "Las personas se desempenian mucho mejor cuando estan alegres",
            3: "La alegria es parte de la vida y es vital para disfrutar nuestra estadia",
            4: "luces genial, esa alegria que reflejas te hace ver mejor"
        }
        frase_principal = "Escuchamos una cancion?"

    def conversacion_ira(self, genero):
        frase_inicial = "Veo que estas enfadad"

        if genero == 'M':
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

    def timer_currentState(self):
        json_params = {}
        # The value should be True
        json_params["PersonData"] = self.get_emotion_state()
        print "PersonData", json_params
        send(-1, "emo", json_params)
        threading.Timer(10.0, self.timer_currentState).start()

    def timer_Battery(self):
        json_params = {"batteryPerc": self.alBatteryProxy.getBatteryCharge()}
        send(-1, "rob", json_params)
        threading.Timer(10.0, self.timer_Battery).start()

    def init_timers(self):
        self.timer_Battery()
        self.timer_currentState()
        self.timer_request_finish_anim()

    def timer_request_finish_anim(self):
        dict = self.alMotion.getTaskList()
        json_params = {"finishAnim": "angleInterpolation" in dict or "angleInterpolationBezier" in dict}
        send(-1, "act", json_params)
        threading.Timer(9.0, self.timer_request_finish_anim).start()

    def force_out(self, params):
        self.alDialogProxy.forceOutput()

    def kill_all(self, params):
        self.alMotion.killAll()
