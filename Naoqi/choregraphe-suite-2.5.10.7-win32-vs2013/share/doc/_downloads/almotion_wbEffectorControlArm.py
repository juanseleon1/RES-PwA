#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Whole Body Motion - Left or Right Arm position control"""

import qi
import argparse
import sys
import motion
import time


def main(session, chainName):
    """
    Example of a whole body Left or Right Arm position control
    Warning: Needs a PoseInit before executing
            Whole body balancer must be inactivated at the end of the script
    This example is only compatible with NAO
    """
    # Get the service ALMotion.

    motion_service = session.service("ALMotion")

    # Wake up robot
    motion_service.wakeUp()

    # Send robot to Stand Init
    #postureProxy.goToPosture("StandInit", 0.5)

    frame     = motion.FRAME_ROBOT
    useSensor = False

    effectorInit = motion_service.getPosition(chainName, frame, useSensor)

    # Active LArm tracking
    isEnabled = True
    motion_service.wbEnableEffectorControl(chainName, isEnabled)

    # Example showing how to set position target for LArm
    # The 3 coordinates are absolute LArm position in FRAME_ROBOT
    # Position in meter in x, y and z axis.

    # X Axis LArm Position feasible movement = [ +0.00, +0.12] meter
    # Y Axis LArm Position feasible movement = [ -0.05, +0.10] meter
    # Y Axis RArm Position feasible movement = [ -0.10, +0.05] meter
    # Z Axis LArm Position feasible movement = [ -0.10, +0.10] meter

    coef = 1.0
    if chainName == "LArm":
        coef = +1.0
    elif chainName == "RArm":
        coef = -1.0

    targetCoordinateList = [
    [ +0.12, +0.00*coef, +0.00], # target 0
    [ +0.12, +0.00*coef, -0.10], # target 1
    [ +0.12, +0.05*coef, -0.10], # target 1
    [ +0.12, +0.05*coef, +0.10], # target 2
    [ +0.12, -0.10*coef, +0.10], # target 3
    [ +0.12, -0.10*coef, -0.10], # target 4
    [ +0.12, +0.00*coef, -0.10], # target 5
    [ +0.12, +0.00*coef, +0.00], # target 6
    [ +0.00, +0.00*coef, +0.00], # target 7
    ]


    # wbSetEffectorControl is a non blocking function
    # time.sleep allow head go to his target
    # The recommended minimum period between two successives set commands is
    # 0.2 s.
    for targetCoordinate in targetCoordinateList:
        targetCoordinate = [targetCoordinate[i] + effectorInit[i] for i in range(3)]
        motion_service.wbSetEffectorControl(chainName, targetCoordinate)
        time.sleep(4.0)

    # Deactivate Head tracking
    isEnabled    = False
    motion_service.wbEnableEffectorControl(chainName, isEnabled)

    # Go to rest position
    motion_service.rest()


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--ip", type=str, default="127.0.0.1",
                        help="Robot IP address. On robot or Local Naoqi: use '127.0.0.1'.")
    parser.add_argument("--port", type=int, default=9559,
                        help="Naoqi port number")
    parser.add_argument("--chain", type=str, default="LArm",
                        choices=["LArm", "RArm"], help="Chain name")

    args = parser.parse_args()
    session = qi.Session()
    try:
        session.connect("tcp://" + args.ip + ":" + str(args.port))
    except RuntimeError:
        print ("Can't connect to Naoqi at ip \"" + args.ip + "\" on port " + str(args.port) +".\n"
               "Please check your script arguments. Run with -h option for help.")
        sys.exit(1)
    main(session, args.chain)
