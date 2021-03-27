#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Non ascii Characters"""

import qi
import argparse
import sys
import codecs


def say_from_file(tts_service, filename, encoding):
    with codecs.open(filename, encoding=encoding) as fp:
        contents = fp.read()
        # warning: print contents won't work
        to_say = contents.encode("utf-8")
    tts_service.say(to_say)


def main(session):
    """
    This example uses non ascii characters.
    """
    # Get the service ALTextToSpeech.

    tts_service = session.service("ALTextToSpeech")

    try :
        tts_service.setLanguage('French')
    except RuntimeError:
        print "No French pronunciation because French language is not installed. Pronunciation will be incorrect."
    say_from_file(tts_service, 'coffee_fr_utf-8.txt', 'utf-8')
    say_from_file(tts_service, 'coffee_fr_latin9.txt', 'latin9')

    tts_service.setLanguage('English')
    # the string "I like coffee" is encoded the exact same way in these three
    # encodings
    say_from_file(tts_service, 'coffee_en.txt', 'ascii')
    say_from_file(tts_service, 'coffee_en.txt', 'utf-8')
    say_from_file(tts_service, 'coffee_en.txt', 'latin9')


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

