#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use killTask Method"""

import qi
import argparse
import sys
import time
import almath


def main(session):
    """
    This example uses the killTask method.
    """
    # Get the service ALMotion.

    motion_service  = session.service("ALMotion")

    motion_service.setStiffnesses("Head", 1.0)

    # This function is useful to kill motion Task
    # To see the current motionTask please use getTaskList() function

    motion_service.angleInterpolation('HeadYaw', 90*almath.TO_RAD, 10, True, _async=True)
    time.sleep(3)
    taskList = motion_service.getTaskList()
    uiMotion = taskList[0][1]
    motion_service.killTask(uiMotion)

    motion_service.setStiffnesses("Head", 0.0)


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
