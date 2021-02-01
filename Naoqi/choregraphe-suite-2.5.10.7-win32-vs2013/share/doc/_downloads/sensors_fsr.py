#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use getData Method to Use FSR Sensors"""

import qi
import argparse
import sys
import time


def main(session):
    """
    This example uses the getData method to use FSR sensors.
    """
    # Get the service ALMemory.

    memory_service = session.service("ALMemory")

    footContact = memory_service.getData("footContact")

    while footContact:
        footContact = memory_service.getData("footContact")
        leftFoot    = memory_service.getData("leftFootTotalWeight")
        rightFoot   = memory_service.getData("rightFootTotalWeight")
        print ("Total weight on left foot: %.2f kg, on right foot: %.2f kg" % (leftFoot, rightFoot))
        time.sleep(1.0)

    print("Foot contact lost")


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
