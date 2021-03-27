#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use setFootStepsWithSpeed Method"""

import qi
import argparse
import sys
import time


def main(session):
    """
    This example uses the setFootStepsWithSpeed method.
    """
    # Get the services ALMotion & ALRobotPosture.

    motion_service  = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # Wake up robot
    motion_service.wakeUp()

    # Send robot to Pose Init
    posture_service.goToPosture("StandInit", 0.5)

    # A small step forwards and anti-clockwise with the left foot
    legName = ["LLeg"]
    X       = 0.04
    Y       = 0.1
    Theta   = 0.3
    footSteps = [[X, Y, Theta]]
    fractionMaxSpeed = [1.0]
    clearExisting = False
    motion_service.setFootStepsWithSpeed(legName, footSteps, fractionMaxSpeed, clearExisting)

    time.sleep(0.5)

    # A small step forwards and anti-clockwise with the left foot
    legName = ["LLeg", "RLeg"]
    X       = 0.04
    Y       = 0.1
    Theta   = 0.3
    footSteps = [[X, Y, Theta], [X, -Y, Theta]]
    fractionMaxSpeed = [1.0, 1.0]
    clearExisting = False
    motion_service.setFootStepsWithSpeed(legName, footSteps, fractionMaxSpeed, clearExisting)

    motion_service.waitUntilMoveIsFinished()

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
