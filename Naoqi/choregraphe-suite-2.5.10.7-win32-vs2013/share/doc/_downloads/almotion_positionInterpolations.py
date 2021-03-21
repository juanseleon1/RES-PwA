#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use positionInterpolations Method"""

import qi
import argparse
import sys
import almath
import motion


def main(session):
    """
    This example uses the positionInterpolations method.
    """
    # Get the services ALMotion & ALRobotPosture.

    motion_service  = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # Wake up robot
    motion_service.wakeUp()

    # Send robot to Pose Init
    posture_service.goToPosture("StandInit", 0.5)

    # Example showing how to use positionInterpolations
    frame = motion.FRAME_ROBOT
    useSensorValues = False

    dx = 0.03 # translation axis X (meters)
    dy = 0.04 # translation axis Y (meters)

    # Motion of Arms with block process
    effectorList = []
    pathList     = []

    axisMaskList = [motion.AXIS_MASK_VEL, motion.AXIS_MASK_VEL]
    timeList     = [[1.0], [1.0]]         # seconds

    effectorList.append("LArm")
    currentPos = motion_service.getPosition("LArm", frame, useSensorValues)
    targetPos = almath.Position6D(currentPos)
    targetPos.y -= dy
    pathList.append(list(targetPos.toVector()))

    effectorList.append("RArm")
    currentPos = motion_service.getPosition("RArm", frame, useSensorValues)
    targetPos = almath.Position6D(currentPos)
    targetPos.y += dy
    pathList.append(list(targetPos.toVector()))

    motion_service.positionInterpolations(effectorList, frame, pathList,
                                 axisMaskList, timeList)

    # Motion of Arms and Torso with block process
    axisMaskList = [motion.AXIS_MASK_VEL,
                    motion.AXIS_MASK_VEL,
                    motion.AXIS_MASK_ALL]
    timeList    = [[4.0],
                    [4.0],
                    [1.0, 2.0, 3.0, 4.0]] # seconds

    effectorList = []
    pathList     = []

    effectorList.append("LArm")
    pathList.append([motion_service.getPosition("LArm", frame, useSensorValues)])

    effectorList.append("RArm")
    pathList.append([motion_service.getPosition("RArm", frame, useSensorValues)])

    effectorList.append("Torso")
    torsoList  = []
    currentPos = motion_service.getPosition("Torso", frame, useSensorValues)
    targetPos  = almath.Position6D(currentPos)
    targetPos.y += dy
    torsoList.append(list(targetPos.toVector()))

    targetPos = almath.Position6D(currentPos)
    targetPos.x -= dx
    torsoList.append(list(targetPos.toVector()))

    targetPos = almath.Position6D(currentPos)
    targetPos.y -= dy
    torsoList.append(list(targetPos.toVector()))

    targetPos = almath.Position6D(currentPos)
    torsoList.append(list(targetPos.toVector()))
    pathList.append(torsoList)

    motion_service.positionInterpolations(effectorList, frame, pathList,
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
