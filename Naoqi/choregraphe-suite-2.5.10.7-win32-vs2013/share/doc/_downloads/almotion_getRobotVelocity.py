#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use getRobotVelocity Method"""

import qi
import argparse
import sys


def main(session):
    """
    This example uses the getRobotVelocity method.
    """
    # Get the service ALMotion.

    motion_service  = session.service("ALMotion")

    # Example showing how to get a simplified absolute robot velocity in FRAME_ROBOT.
    result = motion_service.getRobotVelocity()
    print "Robot Velocity: ", result


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
