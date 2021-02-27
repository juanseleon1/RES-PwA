#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Task management - the second motion is not postponed"""

import qi
import argparse
import sys
import math
import time


def main(session):
    """
    Task management - the second motion is not postponed
    """
    # Get the service ALMotion.

    motion_service = session.service("ALMotion")

    # Wake up robot
    motion_service.wakeUp()

    # go to an init head pose.
    names  = ["HeadYaw", "HeadPitch"]
    angles = [0., 0.]
    times  = [1.0, 1.0]
    isAbsolute = True
    motion_service.angleInterpolation(names, angles, times, isAbsolute)

    # move slowly the head to look in the left direction
    names  = "HeadYaw"
    angles = math.pi/2
    fractionMaxSpeed = .1
    motion_service.setAngles(names, angles, fractionMaxSpeed)

    time.sleep(1.)

    # while the previous motion is still running, update the angle
    angles  = -math.pi/6
    fractionMaxSpeed  = 1.
    motion_service.setAngles(names, angles, fractionMaxSpeed)

    time.sleep(2.0)
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
