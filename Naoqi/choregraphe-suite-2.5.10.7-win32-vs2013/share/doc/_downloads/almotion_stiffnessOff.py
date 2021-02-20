#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example : Stiffness Off - remove stiffness of all joints and actuators"""

import qi
import argparse
import sys


def main(session):
    """
    Stiffness Off - remove stiffness of all joints and actuators.
    This example is only compatible with NAO.
    """
    # Get the service ALMotion.

    motion_service = session.service("ALMotion")

    # We use the "Body" name to signify the collection of all joints
    names = "Body"
    stiffnessLists = 0.0
    timeLists = 1.0
    motion_service.stiffnessInterpolation(names, stiffnessLists, timeLists)

    # print motion state
    print motion_service.getSummary()


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
