#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use say Method"""

import qi
import argparse
import sys


def main(session):
    """
    This example uses the say method to make the robot speak.
    If French is installed, it will use the French pronunciation for "voiture"
    Else, it will try to say it in English.
    """
    # Get the service ALTextToSpeech.

    tts = session.service("ALTextToSpeech")

    #Sets the language to English
    tts.setLanguage("English")

    tts.say("Let me teach you some French words.")
    tts.say("In French, we say")
    try :
        tts.say("voiture", "French")
    except RuntimeError:
        print "French language is not installed, please install it to have a French pronunciation."
        tts.say("voiture", "English")
    tts.say("for car")


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--ip", type=str, default="127.0.0.1",
                        help="Robot IP address. On robot or Local Naoqi: use '127.0.0.1'.")
    parser.add_argument("--port", type=int, default=9559,
                        help="Naoqi port number")

    args = parser.parse_args()
    session = qi.Session()
    try:
        session.connect("tcp://" + args.ip + ":" + str(args.port))
    except RuntimeError:
        print ("Can't connect to Naoqi at ip \"" + args.ip + "\" on port " + str(args.port) +".\n"
               "Please check your script arguments. Run with -h option for help.")
        sys.exit(1)
    main(session)
