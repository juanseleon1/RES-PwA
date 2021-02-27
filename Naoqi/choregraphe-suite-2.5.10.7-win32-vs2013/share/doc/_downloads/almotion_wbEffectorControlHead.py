#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Whole Body Motion - Head orientation control"""

import qi
import argparse
import sys
import time
import math


def main(session):
    """
    Example of a whole body head orientation control.
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

    effectorName = "Head"

    # Active Head tracking
    isEnabled    = True
    motion_service.wbEnableEffectorControl(effectorName, isEnabled)

    # Example showing how to set orientation target for Head tracking
    # The 3 coordinates are absolute head orientation in NAO_SPACE
    # Rotation in RAD in x, y and z axis

    # X Axis Head Orientation feasible movement = [-20.0, +20.0] degree
    # Y Axis Head Orientation feasible movement = [-75.0, +70.0] degree
    # Z Axis Head Orientation feasible movement = [-30.0, +30.0] degree

    targetCoordinateList = [
    [+20.0,  00.0,  00.0], # target 0
    [-20.0,  00.0,  00.0], # target 1
    [ 00.0, +70.0,  00.0], # target 2
    [ 00.0, +70.0, +30.0], # target 3
    [ 00.0, +70.0, -30.0], # target 4
    [ 00.0, -75.0,  00.0], # target 5
    [ 00.0, -75.0, +30.0], # target 6
    [ 00.0, -75.0, -30.0], # target 7
    [ 00.0,  00.0,  00.0], # target 8
    ]

    # wbSetEffectorControl is a non blocking function
    # time.sleep allow head go to his target
    # The recommended minimum period between two successives set commands is
    # 0.2 s.
    for targetCoordinate in targetCoordinateList:
        targetCoordinate = [target*math.pi/180.0 for target in targetCoordinate]
        motion_service.wbSetEffectorControl(effectorName, targetCoordinate)
        time.sleep(3.0)

    # Deactivate Head tracking
    isEnabled = False
    motion_service.wbEnableEffectorControl(effectorName, isEnabled)

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
