#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use getCurrentPosition Method"""

import qi
import argparse
import sys
import time


def main(session):
    """
    This example uses the getCurrentPosition method.
    """
    # Get the service ALAudioPlayer.

    audio_player_service = session.service("ALAudioPlayer")

    #plays a file and get the current position 5 seconds later
    fileId = audio_player_service.loadFile("/usr/share/naoqi/wav/random.wav")
    audio_player_service.play(fileId, _async=True)

    time.sleep(3)

    #currentPos should be near 3 secs
    currentPos = audio_player_service.getCurrentPosition(fileId)
    print "The current position in file is: ", currentPos


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
