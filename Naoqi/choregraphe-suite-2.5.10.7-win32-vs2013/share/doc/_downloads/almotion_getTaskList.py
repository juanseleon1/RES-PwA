#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use getTaskList Method"""

import qi
import argparse
import sys
import time


def main(session):
    """
    This example uses the getTaskList method.
    """
    # Get the service ALMotion.

    motion_service  = session.service("ALMotion")

    motion_service.setStiffnesses("Head", 1.0)

    # Example showing how to get the current task list
    # We will create a task first, so that we see a result
    names      = "HeadYaw"
    angleLists = 1.0
    timeList   = 3.0
    isAbsolute = True
    motion_service.angleInterpolation(names, angleLists, timeList, isAbsolute, _async=True)
    time.sleep(0.1)
    print 'Tasklist: ', motion_service.getTaskList()

    time.sleep(2.0)

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
