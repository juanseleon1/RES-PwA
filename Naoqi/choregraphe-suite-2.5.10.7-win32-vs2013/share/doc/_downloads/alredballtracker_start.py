#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use Tracking Module to Track a Red Ball"""

import qi
import argparse
import sys
import time


def main(session, ballSize):
    """
    This example shows how to use ALTracker with red ball.
    """
    # Get the services ALTracker, ALMotion and ALRobotPosture.

    motion_service = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")
    tracker_service = session.service("ALTracker")

    # First, wake up.
    motion_service.wakeUp()

    fractionMaxSpeed = 0.8
    # Go to posture stand
    posture_service.goToPosture("StandInit", fractionMaxSpeed)

    # Add target to track.
    targetName = "RedBall"
    diameterOfBall = ballSize
    tracker_service.registerTarget(targetName, diameterOfBall)

    # set mode
    mode = "Move"
    tracker_service.setMode(mode)

    # Then, start tracker.
    tracker_service.track(targetName)

    print "ALTracker successfully started, now show a red ball to robot!"
    print "Use Ctrl+c to stop this script."

    try:
        while True:
            time.sleep(1)
    except KeyboardInterrupt:
        print
        print "Interrupted by user"
        print "Stopping..."

    # Stop tracker, go to posture Sit.
    tracker_service.stopTracker()
    tracker_service.unregisterAllTargets()
    posture_service.goToPosture("Sit", fractionMaxSpeed)
    motion_service.rest()

    print "ALTracker stopped."


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--ip", type=str, default="127.0.0.1",
                        help="Robot IP address. On robot or Local Naoqi: use '127.0.0.1'.")
    parser.add_argument("--port", type=int, default=9559,
                        help="Naoqi port number")
    parser.add_argument("--ballsize", type=float, default=0.06,
                        help="Diameter of ball.")

    args = parser.parse_args()
    session = qi.Session()
    try:
        session.connect("tcp://" + args.ip + ":" + str(args.port))
    except RuntimeError:
        print ("Can't connect to Naoqi at ip \"" + args.ip + "\" on port " + str(args.port) +".\n"
               "Please check your script arguments. Run with -h option for help.")
        sys.exit(1)
    main(session, args.ballsize)
