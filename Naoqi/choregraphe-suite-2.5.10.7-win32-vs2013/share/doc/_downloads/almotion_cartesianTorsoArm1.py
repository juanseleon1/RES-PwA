#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use transformInterpolations Method on Arm and Torso"""

import qi
import argparse
import sys
import almath
import motion


def main(session):
    """
    Use transformInterpolations Method on Arm and Torso
    """
    # Get the services ALMotion & ALRobotPosture.

    motion_service = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # Wake up robot
    motion_service.wakeUp()

    # Send robot to Stand Init
    posture_service.goToPosture("StandInit", 0.5)

    frame      = motion.FRAME_WORLD
    coef       = 0.5                   # motion speed
    times      = [coef, 2.0*coef, 3.0*coef, 4.0*coef]
    useSensorValues = False

    # Relative movement between current and desired positions
    dy         = +0.03                 # translation axis Y (meters)
    dz         = -0.03                 # translation axis Z (meters)
    dwx        = +8.0*almath.TO_RAD   # rotation axis X (radians)

    # Motion of Torso with _async process
    effector   = "Torso"

    path = []
    initTf = almath.Transform(motion_service.getTransform(effector, frame, useSensorValues))
    # point 1
    deltaTf  = almath.Transform(0.0, -dy, dz)*almath.Transform().fromRotX(-dwx)
    targetTf = initTf*deltaTf
    path.append(list(targetTf.toVector()))

    # point 2
    path.append(list(initTf.toVector()))

    # point 3
    deltaTf  = almath.Transform(0.0, dy, dz)*almath.Transform().fromRotX(dwx)
    targetTf = initTf*deltaTf
    path.append(list(targetTf.toVector()))

    # point 4
    path.append(list(initTf.toVector()))

    axisMask   = almath.AXIS_MASK_ALL  # control all the effector axes
    motion_service.transformInterpolations(effector, frame, path,
                                           axisMask, times, _async=True)

    # Motion of Arms with block process
    frame     = motion.FRAME_TORSO
    axisMask  = almath.AXIS_MASK_VEL  # control just the position
    times     = [1.0*coef, 2.0*coef]  # seconds

    # Motion of Right Arm during the first half of the Torso motion
    effector  = "RArm"

    path = []
    currentTf = motion_service.getTransform(effector, frame, useSensorValues)
    targetTf  = almath.Transform(currentTf)
    targetTf.r2_c4 -= 0.04 # y
    path.append(list(targetTf.toVector()))
    path.append(currentTf)

    motion_service.transformInterpolations(effector, frame, path, axisMask, times)

    # Motion of Left Arm during the last half of the Torso motion
    effector   = "LArm"

    path = []
    currentTf = motion_service.getTransform(effector, frame, useSensorValues)
    targetTf  = almath.Transform(currentTf)
    targetTf.r2_c4 += 0.04 # y
    path.append(list(targetTf.toVector()))
    path.append(currentTf)

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
