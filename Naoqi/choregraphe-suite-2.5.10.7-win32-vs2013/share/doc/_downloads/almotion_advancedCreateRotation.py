#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use setTransforms Method"""

import qi
import argparse
import sys
import almath
import motion


def main(session):
    """
    This example uses the setTransforms method to create a rotation.
    """
    # Get the services ALMotion & ALRobotPosture.

    motion_service  = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # Wake up robot
    motion_service.wakeUp()

    # Send NAO to Pose Init
    posture_service.goToPosture("StandInit", 0.5)

    # Get transform of Left Arm in Torso frame
    chainName = "LArm"
    frame     = motion.FRAME_TORSO
    useSensor = False
    tf = almath.Transform(motion_service.getTransform(chainName, frame, useSensor))

    # Compute desired transform: rotation of -20 degrees around the Z axis
    tfEnd = almath.Transform.fromRotZ(-20.0*almath.TO_RAD)*tf
    tfEnd.r1_c4 = tf.r1_c4
    tfEnd.r2_c4 = tf.r2_c4
    tfEnd.r3_c4 = tf.r3_c4

    # Set the desired target
    axisMask = 63 # rotation
    fractionMaxSpeed = 0.1
    transform = [val for val in tfEnd.toVector()]
    motion_service.setTransforms(chainName, frame, transform, fractionMaxSpeed, axisMask)

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
