#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Move To - Small example to make Nao Move To an Objective"""

import qi
import argparse
import sys


def main(session):
    """
    Move To - Small example to make Nao Move To an Objective.
    This example is only compatible with NAO.
    """
    # Get the services ALMotion & ALRobotPosture.

    motion_service = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # Wake up robot
    motion_service.wakeUp()

    # Send robot to Stand Init
    posture_service.goToPosture("StandInit", 0.5)

    x     = 0.2
    y     = 0.0
    theta = 0.0

    # This example show customization for the both foot
    # with all the possible gait parameters
    try:
        motion_service.moveTo(x, y, theta,
            [ ["MaxStepX", 0.02],         # step of 2 cm in front
              ["MaxStepY", 0.16],         # default value
              ["MaxStepTheta", 0.4],      # default value
              ["MaxStepFrequency", 0.0],  # low frequency
              ["StepHeight", 0.01],       # step height of 1 cm
              ["TorsoWx", 0.0],           # default value
              ["TorsoWy", 0.1] ])         # torso bend 0.1 rad in front
    except Exception, errorMsg:
        print str(errorMsg)
        print "This example is not allowed on this robot."
        exit()

    # This example show customization for the both foot
    # with just one gait parameter, in this case, the other
    # parameters are set to the default value
    try:
        motion_service.moveTo(x, y, theta, [ ["StepHeight", 0.04] ]) # step height of 4 cm
    except Exception, errorMsg:
        print str(errorMsg)
        print "This example is not allowed on this robot."
        exit()

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
