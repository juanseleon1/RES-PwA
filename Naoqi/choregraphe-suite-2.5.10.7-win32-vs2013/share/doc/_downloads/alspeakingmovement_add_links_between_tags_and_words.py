#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use ALSpeakingMovement and ALAnimatedSpeech Modules"""

import qi
import argparse
import sys


def main(session):
    """
    This example uses the ALSpeakingMovement and ALAnimatedSpeech modules.
    """
    # Get the services ALSpeakingMovement and ALAnimatedSpeech.

    speak_move_service = session.service("ALSpeakingMovement")
    anim_speech_service = session.service("ALAnimatedSpeech")

    # removes all the tagged words dynamically added by the method addTagsToWords()
    speak_move_service.resetTagsToWords()

    # here the word "testword" is not linked with with the tag "hello"
    anim_speech_service.say("I am saying the word testword !")

    # associate word "hey" with animation tag "hello"
    # associate word "yo" with animation tag "hello"
    # associate word "testword" with animation tag "hello"
    # assiciate word "everybody" with animation tag "everything"
    ttw = { "hello" : ["hey", "yo", "testword"],
            "everything" : ["everybody"] }
    speak_move_service.addTagsToWords(ttw)

    # here the word "testword" is linked with with the tag "hello"
    anim_speech_service.say("I am saying the word testword !")



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
