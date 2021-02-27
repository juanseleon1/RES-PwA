import qi
import argparse
import sys

"""Example: ALTactileGesture Example Application"""


class ReactToTactileGesture():
    def __init__(self, app):
        """
        ALTactileGesture example application.
        """
        app.start()
        session = app.session

        # Connect services
        self.tts = session.service('ALTextToSpeech')
        self.tg = session.service('ALTactileGesture')

        # Connect tactile gesture handler to onGesture signal
        self.s1 = self.tg.onGesture.connect(self.tactile_gesture_handler)

        # Connect tactile gesture release handler to onRelease signal
        self.s2 = self.tg.onRelease.connect(self.tactile_gesture_release_handler)

        # Using getGesture(), get the name of the gesture we're labeling "DoubleTap"
        doubleTap = ['000', '111', '000', '111', '000']
        self.DoubleTap = self.tg.getGesture(doubleTap)

        # Create new gestures
        self.add_gestures()

        # Boolean 'lock' useful for responding to 'hold' gestures in a more controlled manner
        self.gesture_hold_lock = False

        print 'ALTactileGesture Example Application :'
        print 'Please touch the robot head sensors, for example the front one.'

    def add_gestures(self):
        """
        Add a couple new gestures into ALTactileGesture.
        """

        # Create a new gesture "TripleTap"
        try:
            self.TripleTap = None
            tripleTap = ['000', '111', '000', '111', '000', '111', '000']
            self.TripleTap = self.tg.createGesture(tripleTap)
        except RuntimeError as e:
            print e

        # Using getSequence(), create "QuadrupleTap" by building off of "TripleTap"
        try:
            tt_seq = self.tg.getSequence(self.TripleTap)
            if tt_seq:
                tt_seq.extend(['111', '000'])
                self.QuadrupleTap = self.tg.createGesture(tt_seq)
        except RuntimeError as e:
            print e

    def tactile_gesture_handler(self, value):
        """
        Given a tactile gesture, say the gesture if we're listening for it.
        """
        # A default gesture...
        if value == 'SingleFront':
            self.tts.say(value)

        # Another default gesture, via getGesture() ...
        if value == self.DoubleTap:
            self.tts.say('DoubleTap')

        # New gesture
        if value == self.TripleTap:
            self.tts.say('TripleTap')

        # New gesture, using getSequence() to build from ...
        if value == self.QuadrupleTap:
            self.tts.say('QuadrupleTap')

        # Hold gesture, repeats every 'hold period'
        if value == 'SingleFrontHold':
            self.tts.say(value)

        # Hold gesture, only responds to first firing
        if not self.gesture_hold_lock and value == 'SingleRearHold':
            self.gesture_hold_lock = True
            self.tts.say(value)

    def tactile_gesture_release_handler(self):
        """
        Enables 'locking out' of multiple 'hold gesture' signal responses
        """
        self.gesture_hold_lock = False

    def clean_up(self):
        """
        Disconnect tactile gesture handler from signal
        """
        self.tg.onGesture.disconnect(self.s1)
        self.tg.onRelease.disconnect(self.s2)


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--ip", type=str, default="127.0.0.1",
                        help="Robot IP address. On robot or Local Naoqi: use '127.0.0.1'.")
    parser.add_argument("--port", type=int, default=9559,
                        help="Naoqi port number")

    args = parser.parse_args()
    try:
        # Initialize qi framework.
        connection_url = "tcp://" + args.ip + ":" + str(args.port)
        app = qi.Application(["ReactToTouch", "--qi-url=" + connection_url])
    except RuntimeError:
        print ("Can't connect to Naoqi at ip \"" + args.ip + "\" on port " + str(args.port) +".\n"
               "Please check your script arguments. Run with -h option for help.")
        sys.exit(1)
    react_to_tactile_gesture = ReactToTactileGesture(app)
    app.run() # will exit when the connection is over.
    react_to_tactile_gesture.clean_up()
