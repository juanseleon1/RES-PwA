#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Whole Body Motion - Enable Balance Constraint"""

import qi
import argparse
import sys
import math


def main(session):
    """
    Example of a whole body Enable Balance Constraint.
    Warning: Needs a PoseInit before executing.
        Whole body balancer must be deactivated at the end of the script.
    This example is only compatible with NAO.
    """
    # Get the services ALMotion & ALRobotPosture.

    motion_service = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # Wake up robot
    motion_service.wakeUp()

    # Send robot to Stand Init
    posture_service.goToPosture("StandInit", 0.5)

    # Activate Whole Body Balancer
    isEnabled  = True
    motion_service.wbEnable(isEnabled)

    # Legs are constrained in a plane
    stateName  = "Fixed"
    supportLeg = "Legs"
    motion_service.wbFootState(stateName, supportLeg)

    # Constraint Balance Motion
    isEnable   = True
    supportLeg = "Legs"
    motion_service.wbEnableBalanceConstraint(isEnable, supportLeg)

    # KneePitch angleInterpolation
    # Without Whole Body balancer, foot will fall down
    names      = ["LKneePitch", "RKneePitch"]
    angleLists = [ [0.0, 40.0*math.pi/180.0], [0.0, 40.0*math.pi/180.0]]
    timeLists  = [ [5.0, 10.0], [5.0, 10.0]]
    isAbsolute = True
    try:
        motion_service.angleInterpolation(names, angleLists, timeLists, isAbsolute)
    except Exception, errorMsg:
        print str(errorMsg)
        print "This example is not allowed on this robot."
        exit()

    # Deactivate Whole Body Balancer
    isEnabled  = False
    motion_service.wbEnable(isEnabled)

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
