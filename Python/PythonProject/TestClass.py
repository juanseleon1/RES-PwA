#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use onTouchDown Method"""

import qi
import argparse
import sys
import time


def main(app):
    """
    This example uses the onTouchDown method.
    To Test ALTabletService, you need to run the script ON the robot.
    """
    # Get the service ALTabletService.

    try:
        say = app.session.service("ALTextToSpeech")
        leds = app.session.service("ALLeds")
        print "HOLA"
        say.say("HOLA")
        leds.fadeRGB("AllLeds",0x00ffff7a,0.2)
        app.run()
    except Exception, e:
        print "Error was: ", e


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--ip", type=str, default="10.195.22.105",
                        help="Robot IP address. On robot or Local Naoqi: use '127.0.0.1'.")
    parser.add_argument("--port", type=int, default=9559,
                        help="Naoqi port number")

    args = parser.parse_args()
    try:
        connection_url = "tcp://" + args.ip + ":" + str(args.port)
        app = qi.Application(["TabletModule", "--qi-url=" + connection_url])
        app.start()
    except RuntimeError:
        print ("Can't connect to Naoqi at ip \"" + args.ip + "\" on port " + str(args.port) +".\n"
               "Please check your script arguments. Run with -h option for help.")
        sys.exit(1)
    main(app)