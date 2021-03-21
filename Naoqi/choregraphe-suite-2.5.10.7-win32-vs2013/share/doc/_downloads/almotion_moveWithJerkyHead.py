#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Walk - Small example to make Nao walk"""

import qi
import argparse
import sys
import time
import random


def main(session):
    """
    Walk: Small example to make Nao walk with jerky head.
    This example is only compatible with NAO.
    """
    # Get the services ALMotion & ALRobotPosture.

    motion_service = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # Wake up robot
    motion_service.wakeUp()

    # Send robot to Stand Init
    posture_service.goToPosture("StandInit", 0.5)

    # Initialize the move process.
    # Check the robot pose and take a right posture.
    # This is blocking called.
    motion_service.moveInit()

    testTime = 10.0 # seconds
    t  = 0.0
    dt = 0.2

    while t<testTime:
        # WALK
        X         = random.uniform(0.4, 1.0)
        Y         = random.uniform(-0.4, 0.4)
        Theta     = random.uniform(-0.4, 0.4)
        Frequency = random.uniform(0.5, 1.0)
        try:
            motion_service.moveToward(X, Y, Theta, [["Frequency", Frequency]])
        except Exception, errorMsg:
            print str(errorMsg)
            print "This example is not allowed on this robot."
            exit()

        # JERKY HEAD
        motion_service.setAngles("HeadYaw", random.uniform(-1.0, 1.0), 0.6)
        motion_service.setAngles("HeadPitch", random.uniform(-0.5, 0.5), 0.6)

        t = t + dt
        time.sleep(dt)

    # stop move on the next double support
    motion_service.stopMove()

    # Go to rest position
    motion_service.rest()


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
