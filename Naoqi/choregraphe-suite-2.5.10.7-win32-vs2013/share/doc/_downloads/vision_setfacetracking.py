#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Modify Face Tracking policy on the robot."""

import qi
import argparse
import sys


def main(session):
    """
    When tracking is activated, faces looking sideways, or located further away
    should be tracked for a longer period.
    Launch Monitor, Camera-Viewer, activate face detection, and see if it works better.
    """

    tracking_enabled = True

    # Get the service ALFaceDetection.

    face_service = session.service("ALFaceDetection")

    print "Will set tracking to '%s' on the robot ..." % tracking_enabled

    # Enable or disable tracking.
    face_service.enableTracking(tracking_enabled)

    # Just to make sure correct option is set.
    print "Is tracking now enabled on the robot?", face_service.isTrackingEnabled()


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
