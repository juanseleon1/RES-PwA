#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use getFootSteps Method"""

import qi
import argparse
import sys
import time


def main(session):
    """
    This example uses the getFootSteps method.
    """
    # Get the services ALMotion & ALRobotPosture.

    motion_service  = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # Wake up robot
    motion_service.wakeUp()

    # Send NAO to Pose Init
    posture_service.goToPosture("StandInit", 0.5)

    #####################################
    # A small example using getFootSteps
    #####################################

    # First call of move API
    # Use _async=True argument to not be blocking here.
    motion_service.moveTo(0.3, 0.0, 0.5, _async=True)

    # wait that the move process start running
    time.sleep(1.0)

    # get the foot steps vector
    footSteps = motion_service.getFootSteps()

    # print the result
    leftFootWorldPosition = footSteps[0][0]
    print "leftFootWorldPosition:"
    print leftFootWorldPosition
    print ""

    rightFootWorldPosition = footSteps[0][1]
    print "rightFootWorldPosition:"
    print rightFootWorldPosition
    print ""

    footStepsUnchangeable = footSteps[1]
    print "Unchangeable:"
    print footStepsUnchangeable
    print ""

    footStepsChangeable   = footSteps[2]
    print "Changeable:"
    print footStepsChangeable
    print ""

    motion_service.waitUntilMoveIsFinished()

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
