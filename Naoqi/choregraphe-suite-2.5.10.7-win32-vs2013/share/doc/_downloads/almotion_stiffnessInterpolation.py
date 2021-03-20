#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use stiffnessInterpolation Method"""

import qi
import argparse
import sys
import time


def main(session):
    """
    This example uses the stiffnessInterpolation method.
    """
    # Get the service ALMotion.

    motion_service  = session.service("ALMotion")
    motion_service.wakeUp()

    # Example showing how to interpolate to minimum stiffness in 1 second
    names  = 'LArm'
    stiffnessLists  = 0.0
    timeLists  = 1.0
    motion_service.stiffnessInterpolation(names, stiffnessLists, timeLists)

    time.sleep(1.0)

    # Example showing a stiffness trajectory for a single joint
    names  = ['LWristYaw']
    stiffnessLists  = [0.25, 0.5, 1.0, 0.0]
    timeLists  = [1.0, 2.0, 3.0, 4.0]
    motion_service.stiffnessInterpolation(names, stiffnessLists, timeLists)

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
