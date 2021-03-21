#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use getData Method to Use Sonars Sensors"""

import qi
import argparse
import sys


def main(session):
    """
    This example uses the getData method to use sonars sensors.
    """
    # Get the services ALMemory and ALSonar.

    memory_service = session.service("ALMemory")
    sonar_service = session.service("ALSonar")

    # Subscribe to sonars, this will launch sonars (at hardware level)
    # and start data acquisition.
    sonar_service.subscribe("myApplication")

    # Now you can retrieve sonar data from ALMemory.
    # Get sonar left first echo (distance in meters to the first obstacle).
    memory_service.getData("Device/SubDeviceList/US/Left/Sensor/Value")

    # Same thing for right.
    memory_service.getData("Device/SubDeviceList/US/Right/Sensor/Value")

    # Unsubscribe from sonars, this will stop sonars (at hardware level)
    sonar_service.unsubscribe("myApplication")

    # Please read Sonar ALMemory keys section
    # if you want to know the other values you can get.


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
