#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use getRobotPosition Method"""

import qi
import argparse
import sys
import almath


def main(session):
    """
    This example uses the getRobotPosition method.
    """
    # Get the services ALMotion & ALRobotPosture.

    motion_service  = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # Wake up robot
    motion_service.wakeUp()

    # Send robot to Pose Init
    posture_service.goToPosture("StandInit", 0.5)

    # Example showing how to get a simplified robot position in world.
    useSensorValues = False
    result = motion_service.getRobotPosition(useSensorValues)
    print "Robot Position", result

    # Example showing how to use this information to know the robot's diplacement.
    useSensorValues = False
    initRobotPosition = almath.Pose2D(motion_service.getRobotPosition(useSensorValues))

    # Make the robot move
    motion_service.moveTo(0.1, 0.0, 0.2)

    endRobotPosition = almath.Pose2D(motion_service.getRobotPosition(useSensorValues))

    # Compute robot's' displacement
    robotMove = almath.pose2DInverse(initRobotPosition)*endRobotPosition
    print "Robot Move:", robotMove

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
