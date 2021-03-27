#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use wbSetEffectorControl Method"""

import qi
import argparse
import sys
import time


def main(session):
    """
    This example uses the wbSetEffectorControl method.
    """
    # Get the services ALMotion & ALRobotPosture.

    motion_service  = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # Wake up robot
    motion_service.wakeUp()

    # Send robot to Pose Init
    posture_service.goToPosture("StandInit", 0.5)

    motion_service.wbEnable(True)

    # Example showing how to set orientation target for LArm tracking.
    effectorName = "LArm"

    motion_service.wbEnableEffectorControl(effectorName, True)
    time.sleep(2.0)
    targetCoordinate = [0.20, 0.12, 0.30]
    motion_service.wbSetEffectorControl(effectorName, targetCoordinate)

    time.sleep(4.0)
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
