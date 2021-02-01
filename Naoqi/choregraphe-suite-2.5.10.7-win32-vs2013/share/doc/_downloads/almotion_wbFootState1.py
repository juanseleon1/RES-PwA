#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use wbFootState Method"""

import qi
import argparse
import sys
import time


def main(session):
    """
    This example uses the wbFootState method.
    """
    # Get the services ALMotion & ALRobotPosture.

    motion_service  = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # Wake up robot
    motion_service.wakeUp()

    # Send robot to Pose Init
    posture_service.goToPosture("StandInit", 0.5)

    motion_service.wbEnable(True)

    # Example showing how to fix the feet.
    print "Feet fixed."
    stateName = "Fixed"
    supportLeg = "Legs"
    motion_service.wbFootState(stateName, supportLeg)

    # Example showing how to fix the left leg and constrained in a plane the right leg.
    print "Left leg fixed, right leg in a plane."
    motion_service.wbFootState("Fixed", "LLeg")
    motion_service.wbFootState("Plane", "RLeg")

    # Example showing how to fix the left leg and keep free the right leg.
    print "Left leg fixed, right leg free"
    motion_service.wbFootState("Fixed", "LLeg")
    motion_service.wbFootState("Free", "RLeg")

    time.sleep(2.0)
    motion_service.wbEnable(False)

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
