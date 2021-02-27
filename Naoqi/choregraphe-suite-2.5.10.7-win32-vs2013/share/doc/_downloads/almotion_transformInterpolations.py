#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use transphormInterpolation Method"""

import qi
import argparse
import sys
import almath
import motion


def main(session):
    """
    This example uses the transphormInterpolation method.
    """
    # Get the services ALMotion & ALRobotPosture.

    motion_service  = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # Wake up robot
    motion_service.wakeUp()

    # Send robot to Pose Init
    posture_service.goToPosture("StandInit", 0.5)

    # Example showing how to use transformInterpolations
    frame = motion.FRAME_ROBOT
    useSensorValues = False

    # Motion of Arms with block process
    effectorList = ["LArm", "RArm"]
    axisMaskList = [motion.AXIS_MASK_VEL, motion.AXIS_MASK_VEL]
    timeList     = [[1.0], [1.0]]         # seconds

    dy = 0.04
    pathList = []
    targetLArmTf = almath.Transform(motion_service.getTransform("LArm", frame, useSensorValues))
    targetLArmTf.r2_c4 -= dy
    pathList.append(list(targetLArmTf.toVector()))

    targetRArmTf = almath.Transform(motion_service.getTransform("RArm", frame, useSensorValues))
    targetRArmTf.r2_c4 += dy
    pathList.append(list(targetRArmTf.toVector()))

    motion_service.transformInterpolations(effectorList, frame, pathList,
                                 axisMaskList, timeList)

    # Motion of Arms and Torso with block process
    effectorList = ["LArm", "RArm", "Torso"]
    axisMaskList = [motion.AXIS_MASK_VEL,
                    motion.AXIS_MASK_VEL,
                    motion.AXIS_MASK_ALL]
    timeList     = [[4.0],
                    [4.0],
                    [1.0, 2.0, 3.0, 4.0]] # seconds

    dx = 0.03 # translation axis X (meters)
    dy = 0.05 # translation axis Y (meters)

    pathList = []

    targetLArmTf = almath.Transform(motion_service.getTransform("LArm", frame, useSensorValues))
    pathList.append(list(targetLArmTf.toVector()))

    targetRArmTf = almath.Transform(motion_service.getTransform("RArm", frame, useSensorValues))
    pathList.append(list(targetRArmTf.toVector()))

    pathTorsoList = []
    # point 1
    initTorsoTf = almath.Transform(motion_service.getTransform("Torso", frame, useSensorValues))
    targetTorsoTf = initTorsoTf
    targetTorsoTf.r2_c4 += dy
    pathTorsoList.append(list(targetTorsoTf.toVector()))

    # point 2
    initTorsoTf = almath.Transform(motion_service.getTransform("Torso", frame, useSensorValues))
    targetTorsoTf = initTorsoTf
    targetTorsoTf.r1_c4 += -dx
    pathTorsoList.append(list(targetTorsoTf.toVector()))

    # point 3
    initTorsoTf = almath.Transform(motion_service.getTransform("Torso", frame, useSensorValues))
    targetTorsoTf = initTorsoTf
    targetTorsoTf.r2_c4 += -dy
    pathTorsoList.append(list(targetTorsoTf.toVector()))

    # point 4
    initTorsoTf = almath.Transform(motion_service.getTransform("Torso", frame, useSensorValues))
    pathTorsoList.append(list(initTorsoTf.toVector()))

    pathList.append(pathTorsoList)

    motion_service.transformInterpolations(effectorList, frame, pathList,
                                  axisMaskList, timeList)

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
