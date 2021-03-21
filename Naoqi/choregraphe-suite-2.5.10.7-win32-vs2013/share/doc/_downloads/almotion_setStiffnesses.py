#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use setStiffnesses Method"""

import qi
import argparse
import sys


def main(session):
    """
    This example uses the setStiffnesses method.
    """
    # Get the service ALMotion.

    motion_service  = session.service("ALMotion")

    # Example showing how to set the stiffness to 1.0.
    # Beware, doing this could be dangerous, it is safer to use the
    #   stiffnessInterpolation method which takes a duration parameter.
    names  = 'Head'
    # If only one parameter is received, this is applied to all joints
    stiffnesses  = 1.0
    motion_service.setStiffnesses(names, stiffnesses)


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
