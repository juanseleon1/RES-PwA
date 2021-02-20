#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use angleInterpolationWithSpeed Method"""

import qi
import argparse
import sys
import time


def main(session):
    """
    This example uses the angleInterpolationWithSpeed method.
    """
    # Get the services ALMotion & ALRobotPosture.

    motion_service  = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # Wake up robot
    motion_service.wakeUp()

    # Send robot to Stand Init
    posture_service.goToPosture("StandInit", 0.5)

    # Example showing multiple trajectories
    # Interpolate the head yaw to 1.0 radian and back to zero in 2.0 seconds
    # while interpolating HeadPitch up and down over a longer period.
    names  = ["HeadYaw","HeadPitch"]
    # Each joint can have lists of different lengths, but the number of
    # angles and the number of times must be the same for each joint.
    # Here, the second joint ("HeadPitch") has three angles, and
    # three corresponding times.
    angleLists  = [[1.0, 0.0], [-0.5, 0.5, 0.0]]
    timeLists   = [[1.0, 2.0], [ 1.0, 2.0, 3.0]]
    isAbsolute  = True
    motion_service.angleInterpolation(names, angleLists, timeLists, isAbsolute)

    time.sleep(1.0)

    # Example showing a single target for one joint
    names             = "HeadYaw"
    targetAngles      = 1.0
    maxSpeedFraction  = 0.2 # Using 20% of maximum joint speed
    motion_service.angleInterpolationWithSpeed(names, targetAngles, maxSpeedFraction)

    time.sleep(1.0)

    # Example showing multiple joints
    # Instead of listing each joint, you can use a chain name, which will
    # be expanded to contain all the joints in the chain. In this case,
    # "Head" will be interpreted as ["HeadYaw", "HeadPitch"]
    names  = "Head"
    # We still need to specify the correct number of target angles
    targetAngles     = [0.5, 0.25]
    maxSpeedFraction = 0.2 # Using 20% of maximum joint speed
    motion_service.angleInterpolationWithSpeed(names, targetAngles, maxSpeedFraction)

    # Example showing body zero position
    # Instead of listing each joint, you can use a the name "Body"
    names  = "Body"
    # We still need to specify the correct number of target angles, so
    # we need to find the number of joints that this Nao has.
    # Here we are using the getBodyNames method, which tells us all
    # the names of the joints in the alias "Body".
    # We could have used this list for the "names" parameter.
    numJoints = len(motion_service.getBodyNames("Body"))
    # Make a list of the correct length. All angles are zero.
    targetAngles  = [0.0]*numJoints
    # Using 10% of maximum joint speed
    maxSpeedFraction  = 0.1
    motion_service.angleInterpolationWithSpeed(names, targetAngles, maxSpeedFraction)

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
