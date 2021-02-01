#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use transformInterpolations Method on Arm and Torso"""

import qi
import argparse
import sys
import motion
import almath


def main(session):
    """
    Use transformInterpolations Method on Arm and Torso.
    """
    # Get the services ALMotion & ALRobotPosture.

    motion_service = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # Wake up robot
    motion_service.wakeUp()

    # Send robot to Stand Init
    posture_service.goToPosture("StandInit", 0.5)

    frame      = motion.FRAME_ROBOT
    useSensorValues = False

    effectorList = ["LArm", "RArm"]

    # Motion of Arms with block process
    axisMaskList = [almath.AXIS_MASK_VEL, almath.AXIS_MASK_VEL]

    timesList    = [[1.0], [1.0]] # seconds

    # LArm path
    pathLArm = []
    targetTf  = almath.Transform(motion_service.getTransform("LArm", frame, useSensorValues))
    targetTf.r2_c4 -= 0.04 # y
    pathLArm.append(list(targetTf.toVector()))

    # RArm path
    pathRArm = []
    targetTf  = almath.Transform(motion_service.getTransform("RArm", frame, useSensorValues))
    targetTf.r2_c4 += 0.04 # y
    pathRArm.append(list(targetTf.toVector()))

    pathList = []
    pathList.append(pathLArm)
    pathList.append(pathRArm)

    motion_service.transformInterpolations(effectorList, frame, pathList,
                                       axisMaskList, timesList)

    effectorList = ["LArm", "RArm", "Torso"]

    # Motion of Arms and Torso with block process
    axisMaskList = [almath.AXIS_MASK_VEL,
                    almath.AXIS_MASK_VEL,
                    almath.AXIS_MASK_ALL]

    timesList    = [[4.0],                  # LArm  in seconds
                    [4.0],                  # RArm  in seconds
                    [1.0, 2.0, 3.0, 4.0]]   # Torso in seconds

    # LArm path
    pathLArm = []
    pathLArm.append(motion_service.getTransform("LArm", frame, useSensorValues))

    # RArm path
    pathRArm = []
    pathRArm.append(motion_service.getTransform("RArm", frame, useSensorValues))

    # Torso path
    pathTorso = []
    currentTf = motion_service.getTransform("Torso", frame, useSensorValues)

    # 1
    targetTf  = almath.Transform(currentTf)
    targetTf.r2_c4 += 0.04 # y
    pathTorso.append(list(targetTf.toVector()))

    # 2
    targetTf  = almath.Transform(currentTf)
    targetTf.r1_c4 -= 0.03 # x
    pathTorso.append(list(targetTf.toVector()))

    # 3
    targetTf  = almath.Transform(currentTf)
    targetTf.r2_c4 -= 0.04 # y
    pathTorso.append(list(targetTf.toVector()))

    # 4
    pathTorso.append(currentTf)

    pathList = []
    pathList.append(pathLArm)
    pathList.append(pathRArm)
    pathList.append(pathTorso)

    motion_service.transformInterpolations(effectorList, frame, pathList,
                                       axisMaskList, timesList)

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
