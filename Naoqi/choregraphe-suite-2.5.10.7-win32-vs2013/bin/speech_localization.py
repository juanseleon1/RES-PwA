#!/usr/bin/python
import qi
import threading
import time
import sys
import math
import copy

@qi.nobind
def get_confidence(value):
    """Get the confidence from a sound localization data event

    Arguments:
        value -- Sound localization data
    Return:
        Sound localization confidence or 0.0
    """
    try:
        sound_position = value[1]
        return sound_position[2]
    except IndexError:
        return 0.0

@qi.nobind
def get_world_euler(best_sound_loc):
    """ Get Yaw and pitch in the global world representation:

    Arguments:
        best_sound_loc -- Sound localization event
    Return:
        new calculated vector [yaw, pitch]
    """
    world_yaw = best_sound_loc[2][5] + best_sound_loc[1][0]
    world_pitch = best_sound_loc[2][4] + best_sound_loc[1][1]
    return [world_yaw, world_pitch]

@qi.nobind
def get_time(value):
    """Get a generic time in second and stored on a float value, with the 2 integer values

    Arguments:
        value -- Time to convert: [time sec, time microsecond]
    Return:
        float time in second
    """
    return float(value[0]) + float(value[1])*0.000001

@qi.nobind
def get_clock():
    """Get a generic qi.clock now interface in generic python interface

    Return:
        float time in second
    """
    return qi.clockNow()/1.0e9

@qi.nobind
def angle_diff(first_angle, second_angle):
    """Calculate delta between 2 angles

    Arguments:
        first_angle -- First Angle to compare
        second_angle -- Second angle to compare
    Return:
        diff angle [-math.pi..math.pi[
    """
    difference = abs(second_angle - first_angle);
    while difference < -math.pi:
        difference += 2.0*math.pi;
    while difference >= math.pi:
        difference -= 2.0*math.pi;
    return difference;


@qi.nobind
class EventInterface():

    def __init__(self, session):
        # get AL Memory interface for access to sound loc event and word recognized event
        self.service_memory = session.service("ALMemory")
        self.service_memory_callback_list = []

    def connect_callback(self, event_name, callback_func):
        """Connect a callback to "ALMemory" service

        Arguments:
            self -- Current class handle
            event_name -- Event name to connect
            callback_func -- Callback that is connected on the event
        """
        subscriber = self.service_memory.subscriber(event_name)
        subscriber.signal.connect(callback_func)
        self.service_memory_callback_list.append(subscriber)

    def disconnect_callback(self):
        """disconnect all ALMemory events
        """
        del self.service_memory_callback_list[:]


@qi.singleThreaded()
class SpeechLocalization(object):
    """Aggregator of the Sound localization event and the Word detected event

    The Module aggregates the signal of multiple sound localization events and a unique word.
    It generates an event when the word is recognized whith the best sound localization event found.
    """
    # Current service name:
    service_name = "ALSpeechLocalization"
    def __init__(self, session):
        """init of the Class

        Arguments:
            self -- Local class instance
            session -- Current open session to the naoqi-interface
        """
        super(SpeechLocalization, self).__init__()
        # keep a reference on the current session
        self.session = session
        # wait for needed services to be started:
        self.session.waitForService("ALMemory");
        self.session.waitForService("ALSoundLocalization");
        self.session.waitForService("ALSpeechRecognition");
        self.memory_interface = EventInterface(self.session)
        self.service_best_sound_loc = self.session.service("ALSoundLocalization")
        # create ouput signal event (naoqi-2)
        self.speechLocated = qi.Signal('((sf)((ii)(ffff)(ffffff)(ffffff)))', self.on_remote_connect)
        # configure internal parameters:
        self.param_separative_angle = math.pi/8
        self.param_coef_aggregation = 0.1
        self.param_time_latency = 2.0
        self.param_delay_event_sound_loc_before_asr = 0.8
        #configure local variables:
        self.best_sound_loc_reset = {"internal_confidence":0.0,
                                "value":[[0L, 0L], [0.0, 0.0, 0.0, 0.0], [0.0, 0.0, 0.0, 0.0, 0.0, 0.0], [0.0, 0.0, 0.0, 0.0, 0.0, 0.0]],
                                "main_angle":[0.0, 0.0, 0.0],
                                "time":0.0}
        self.best_sound_loc = copy.deepcopy(self.best_sound_loc_reset)
        self.best_sound_loc_old = copy.deepcopy(self.best_sound_loc_reset)

    @qi.nobind
    def on_remote_connect(self, status):
        """Callback on a user connect/disconnect on "self.speechLocated" signal.

        Arguments:
            self -- Current class handle
            status -- Status of the connection on the signal (True: first connection, False No more connection)
        """
        if status == True:
            # connect all needeed signals
            self.memory_interface.connect_callback("SpeechDetected", self.on_speech_detected)
            self.memory_interface.connect_callback("WordRecognized", self.on_word_recognized)
            self.memory_interface.connect_callback("ALSoundLocalization/SoundLocated", self.on_sound_localization)
            self.service_best_sound_loc.subscribe(self.service_name)
        else:
            # disconnect all unneeded signals
            self.service_best_sound_loc.unsubscribe(self.service_name)
            self.memory_interface.disconnect_callback()
            # reset internal variables
            self.best_sound_loc = copy.deepcopy(self.best_sound_loc_reset)
            self.best_sound_loc_old = copy.deepcopy(self.best_sound_loc_reset)

    @qi.nobind
    def similar_angle(self, value1, value2):
        """Check if two angles are in the same area (+/-self.param_separative_angle)

        Arguments:
            self -- Current class handle
            value1 -- First angle position
            value2 -- Second angle position
        Return:
            True If the 2 angles are similar, False otherwise
        """
        delta = angle_diff(value1[0], value2[0])
        if abs(delta) > self.param_separative_angle:
            return False
        delta = angle_diff(value1[1], value2[1])
        if abs(delta) > self.param_separative_angle:
            return False
        return True

    @qi.nobind
    def on_speech_detected(self, value):
        """Callback on the ASR detection of a speech start and stop

        Arguments:
            self -- Current class handle
            value -- 0/1 value of start stop speech detection
        """
        if value == 1:
            if self.best_sound_loc["internal_confidence"] != 0.0:
                currentTime = get_clock()
                if self.best_sound_loc["time"] < currentTime - self.param_delay_event_sound_loc_before_asr:
                    #disable last sound loc (too old)
                    self.best_sound_loc = copy.deepcopy(self.best_sound_loc_reset)
        else:
            self.best_sound_loc_old = self.best_sound_loc
            self.best_sound_loc = copy.deepcopy(self.best_sound_loc_reset)

    @qi.nobind
    def on_word_recognized(self, ASR_result):
        """Callback on the ASR detection of a word recognized.

        Arguments:
            self -- Current class handle
            ASR_result -- Result of the asr [word, confidence].
        """
        if self.best_sound_loc["internal_confidence"] == 0.0:
            if self.best_sound_loc_old["internal_confidence"] != 0.0:
                currentTime = get_clock()
                if self.best_sound_loc_old["time"] >= currentTime - self.param_delay_event_sound_loc_before_asr:
                    self.speechLocated(ASR_result, self.best_sound_loc_old["value"])
                    return
        self.speechLocated(ASR_result, self.best_sound_loc["value"])

    @qi.nobind
    def set_best_sound_loc_property(self, value):
        """set the value as the current sound localization reference

        Arguments:
            self -- Current class handle
            value -- Sound localization properties [[time(s), time(us)] [yaw, pitch, confidence, energie], transform Body, transform word]
        """
        self.best_sound_loc["internal_confidence"] = get_confidence(value)
        self.best_sound_loc["value"] = value
        self.best_sound_loc["main_angle"] = get_world_euler(value)
        self.best_sound_loc["time"] = get_time(value[0])

    @qi.nobind
    def on_sound_localization(self, value):
        """Callback on the sound localization

        Generate weighting of an internal confidence of the "best" sound localization found.

        Arguments:
            self -- Current class handle
            value -- Sound localization properties [[time(s), time(us)] [yaw, pitch, confidence, energie], transform Body, transform word]
        """
        delta = get_clock() - self.best_sound_loc["time"]
        # if no previous event:
        if self.best_sound_loc["internal_confidence"] == 0.0:
            self.set_best_sound_loc_property(value)
        # check if the angle is in the same area:
        elif self.similar_angle(self.best_sound_loc["main_angle"], get_world_euler(value)) == True:
            self.best_sound_loc["time"] = get_time(value[0])
            # check if the new value is better than the previous
            if get_confidence(value) > get_confidence(self.best_sound_loc["value"]):
                # keep it as the new reference:
                self.best_sound_loc["value"] = value
                if self.best_sound_loc["internal_confidence"] < get_confidence(value):
                    # set the new sound loc as internal confidence and add last weighted confidence
                    self.best_sound_loc["internal_confidence"] = get_confidence(value) + self.best_sound_loc["internal_confidence"]*self.param_coef_aggregation
                    return
            # Increase internal confidence with input weighted confidence
            self.best_sound_loc["internal_confidence"] = get_confidence(value)*self.param_coef_aggregation + self.best_sound_loc["internal_confidence"]
        #if internal confidence is lower than new value, just overwrite it ...
        elif self.best_sound_loc["internal_confidence"] <= get_confidence(value):
            self.set_best_sound_loc_property(value)
        #check timeout
        elif get_clock() - self.best_sound_loc["time"] > self.param_time_latency:
            self.set_best_sound_loc_property(value)
        else:
            # in this case reduce current internal confidence
            self.best_sound_loc["internal_confidence"] = self.best_sound_loc["internal_confidence"]*(1.0-self.param_coef_aggregation)

def add_service(session):
    """Add the service in the naoqi-session system

    Arguments:
        session -- Current open session to the naoqi-interface
    Return:
        [service, serviceId]
    """
    service = SpeechLocalization(session)
    id = session.registerService(SpeechLocalization.service_name, service)
    return [service, id]

def main():
    app = qi.Application()
    app.start()
    [speech_localization_service, speech_localization_id] = add_service(app.session)
    app.run()

if __name__ == "__main__":
    main()
