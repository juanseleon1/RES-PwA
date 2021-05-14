#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Show how to use almath with python and send the results to
    the robot by using a proxy to ALMotion"""

import qi
import argparse
import sys
import time
import almath


def main(session):
    """
    Show how to use almath with python and send the results to
    the robot by using a proxy to ALMotion.
    """
    # Get the services AlMotion and ALRobotPosture.

    motion_service = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # WakeUp
    motion_service.wakeUp()

    # Stand up.
    posture_service.goToPosture("StandInit", 0.3)

    chainName = "RArm"
    frame = 1 # FRAME_WORLD
    useSensors = True

    ##############################################
    # Retrieve a transform matrix using ALMotion #
    ##############################################

    # Retrieve current transform from ALMotion.
    # Convert it to a transform matrix for ALMath.
    origTransform = almath.Transform(
        motion_service.getTransform(chainName, frame, useSensors))

    # Visualize the transform using overriden print from ALMath
    print "Original transform"
    print origTransform

    ##########################################################
    # Use almath to do some computations on transform matrix #
    ##########################################################

    # Compute a transform corresponding to the desired move
    # (here, move the chain for 5cm along the Z axis and the X axis).
    moveTransform = almath.Transform.fromPosition(0.05, 0.0, 0.05)

    # Compute the corresponding target transform.
    targetTransform = moveTransform * origTransform

    # Visualize it.
    print "Target transform"
    print targetTransform

    ##############################################
    # Send a transform to the robot via ALMotion #
    ##############################################

    # Convert it to a tuple.
    targetTransformList = list(targetTransform.toVector())

    # Send the target transform to NAO through ALMotion.
    fractionOfMaxSpeed = 0.5
    axisMask = almath.AXIS_MASK_VEL # Translation X, Y, Z
    motion_service.setTransforms(
        chainName,
        frame,
        targetTransformList,
        fractionOfMaxSpeed,
        axisMask)

    time.sleep(2.0)
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
