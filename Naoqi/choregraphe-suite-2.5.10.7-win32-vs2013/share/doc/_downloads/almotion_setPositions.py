#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use setPositions Method"""

import qi
import argparse
import sys
import time
import motion


def main(session):
    """
    This example uses the setPositions method.
    """
    # Get the services ALMotion & ALRobotPosture.

    motion_service  = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # Wake up robot
    motion_service.wakeUp()

    # Send robot to Pose Init
    posture_service.goToPosture("StandInit", 0.5)

    # Example showing how to set LArm Position, using a fraction of max speed
    chainName = "LArm"
    frame     = motion.FRAME_TORSO
    useSensor = False

    # Get the current position of the chainName in the same frame
    current = motion_service.getPosition(chainName, frame, useSensor)

    target = [
        current[0] + 0.05,
        current[1] + 0.05,
        current[2] + 0.05,
        current[3] + 0.0,
        current[4] + 0.0,
        current[5] + 0.0]

    fractionMaxSpeed = 0.5
    axisMask         = 7 # just control position

    motion_service.setPositions(chainName, frame, target, fractionMaxSpeed, axisMask)

    time.sleep(1.0)

    # Example showing how to set Torso Position, using a fraction of max speed
    chainName        = "Torso"
    frame            = motion.FRAME_ROBOT
    position         = [0.0, 0.0, 0.25, 0.0, 0.0, 0.0] # Absolute Position
    fractionMaxSpeed = 0.2
    axisMask         = 63
    motion_service.setPositions(chainName, frame, position, fractionMaxSpeed, axisMask)

    time.sleep(4.0)

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
