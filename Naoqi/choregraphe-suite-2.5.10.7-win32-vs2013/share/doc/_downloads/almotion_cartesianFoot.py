#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use transformInterpolations Method on Arm on Foot"""

import qi
import argparse
import sys
import almath
import motion


def main(session):
    """
    Use transformInterpolations Method on Foot.
    """
    # Get the services ALMotion & ALRobotPosture.

    motion_service = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # Wake up robot
    motion_service.wakeUp()

    # Send robot to Stand Init
    posture_service.goToPosture("StandInit", 0.5)

    frame      = motion.FRAME_WORLD
    axisMask   = almath.AXIS_MASK_ALL   # full control
    useSensorValues = False

    # Lower the Torso and move to the side
    effector = "Torso"
    initTf   = almath.Transform(
        motion_service.getTransform(effector, frame, useSensorValues))
    deltaTf  = almath.Transform(0.0, -0.06, -0.03) # x, y, z
    targetTf = initTf*deltaTf
    path     = list(targetTf.toVector())
    times    = 2.0 # seconds
    motion_service.transformInterpolations(effector, frame, path, axisMask, times)

    # LLeg motion
    effector = "LLeg"
    initTf = almath.Transform()

    try:
        initTf = almath.Transform(motion_service.getTransform(effector, frame, useSensorValues))
    except Exception, errorMsg:
        print str(errorMsg)
        print "This example is not allowed on this robot."
        exit()

    # rotation Z
    deltaTf  = almath.Transform(0.0, 0.04, 0.0)*almath.Transform().fromRotZ(45.0*almath.TO_RAD)
    targetTf = initTf*deltaTf
    path     = list(targetTf.toVector())
    times    = 2.0 # seconds

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
