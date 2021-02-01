#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Whole Body Motion - Foot State"""

import qi
import argparse
import sys
import math


def main(session):
    """
    Example of a whole body FootState.
    Warning: Needs a PoseInit before executing.
        Whole body balancer must be inactivated at the end of the script.
    This example is only compatible with NAO.
    """
    # Get the services ALMotion & ALRobotPosture.

    motion_service = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # Wake up robot
    motion_service.wakeUp()

    # Send robot to Stand Init
    posture_service.goToPosture("StandInit", 0.5)

    # Activate Whole Body Balancer.
    isEnabled  = True
    motion_service.wbEnable(isEnabled)

    # Legs are constrained in a plane
    stateName  = "Plane"
    supportLeg = "Legs"
    motion_service.wbFootState(stateName, supportLeg)

    # HipYawPitch angleInterpolation
    # Without Whole Body balancer, foot will not be keeped plane.
    names      = "LHipYawPitch"
    angleLists = [-45.0, 10.0, 0.0]
    timeLists  = [3.0, 6.0, 9.0]
    isAbsolute = True
    angleLists = [angle*math.pi/180.0 for angle in angleLists]
    try:
        motion_service.angleInterpolation(names, angleLists, timeLists, isAbsolute)
    except Exception, errorMsg:
        print str(errorMsg)
        print "This example is not allowed on this robot."
        exit()

    # Deactivate Whole Body Balancer.
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
