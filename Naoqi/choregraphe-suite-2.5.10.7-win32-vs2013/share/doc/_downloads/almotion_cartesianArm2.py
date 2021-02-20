#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use transformInterpolations Method on Arm"""

import qi
import argparse
import sys
import motion
import almath


def main(session):
    """
    Use transformInterpolations Method on Arm
    """
    # Get the services ALMotion & ALRobotPosture.

    motion_service = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # Wake up robot
    motion_service.wakeUp()

    # Send robot to Stand Init
    posture_service.goToPosture("StandInit", 0.5)

    effector   = "LArm"
    frame      = motion.FRAME_TORSO
    axisMask   = almath.AXIS_MASK_VEL    # just control position
    useSensorValues = False

    path = []
    currentTf = motion_service.getTransform(effector, frame, useSensorValues)
    # point 1
    targetTf  = almath.Transform(currentTf)
    targetTf.r2_c4 -= 0.05 # y
    path.append(list(targetTf.toVector()))

    # point 2
    targetTf  = almath.Transform(currentTf)
    targetTf.r3_c4 += 0.04 # z
    path.append(list(targetTf.toVector()))

    # point 3
    targetTf  = almath.Transform(currentTf)
    targetTf.r2_c4 += 0.04 # y
    path.append(list(targetTf.toVector()))

    # point 4
    targetTf  = almath.Transform(currentTf)
    targetTf.r3_c4 -= 0.02 # z
    path.append(list(targetTf.toVector()))

    # point 5
    targetTf  = almath.Transform(currentTf)
    targetTf.r2_c4 -= 0.05 # y
    path.append(list(targetTf.toVector()))

    # point 6
    path.append(currentTf)

    times = [0.5, 1.0, 2.0, 3.0, 4.0, 4.5] # seconds

    motion_service.transformInterpolations(effector, frame, path, axisMask, times)

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
