#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use moveToward Method"""

import qi
import argparse
import sys
import time


def main(session):
    """
    This example uses the moveToward method.
    """
    # Get the services ALMotion & ALRobotPosture.

    motion_service  = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # Wake up robot
    motion_service.wakeUp()

    # Send robot to Pose Init
    posture_service.goToPosture("StandInit", 0.5)

    # Example showing the use of moveToward
    # The parameters are fractions of the maximums
    # Here we are asking for full speed forwards
    x     = 1.0
    y     = 0.0
    theta = 0.0
    frequency = 1.0
    motion_service.moveToward(x, y, theta, [["Frequency", frequency]])

    # If we don't send another command, he will move forever
    # Lets make him slow down(step length) and turn after 3 seconds
    time.sleep(3)
    x     = 0.5
    theta = 0.6
    motion_service.moveToward(x, y, theta, [["Frequency", frequency]])

    # Lets make him slow down(frequency) after 3 seconds
    time.sleep(3)
    frequency = 0.5
    motion_service.moveToward(x, y, theta, [["Frequency", frequency]])

    # Lets make him stop after 3 seconds
    time.sleep(3)
    motion_service.stopMove()
    # Note that at any time, you can use a moveTo command
    # to run a precise distance. The last command received,
    # of velocity or position always wins

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
