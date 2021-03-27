#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use getData Method to Get Inertial Values"""

import qi
import argparse
import sys


def main(session):
    """
    This example uses the getData method to get Inertial Values.
    """
    # Get the service ALMemory.

    memory_service = session.service("ALMemory")

    # Get the Gyrometers Values
    GyrX = memory_service.getData("Device/SubDeviceList/InertialSensor/GyrX/Sensor/Value")
    GyrY = memory_service.getData("Device/SubDeviceList/InertialSensor/GyrY/Sensor/Value")
    print ("Gyrometers value X: %.3f, Y: %.3f" % (GyrX, GyrY))

    # Get the Accelerometers Values
    AccX = memory_service.getData("Device/SubDeviceList/InertialSensor/AccX/Sensor/Value")
    AccY = memory_service.getData("Device/SubDeviceList/InertialSensor/AccY/Sensor/Value")
    AccZ = memory_service.getData("Device/SubDeviceList/InertialSensor/AccZ/Sensor/Value")
    print ("Accelerometers value X: %.3f, Y: %.3f, Z: %.3f" % (AccX, AccY,AccZ))

    # Get the Compute Torso Angle in radian
    TorsoAngleX = memory_service.getData("Device/SubDeviceList/InertialSensor/AngleX/Sensor/Value")
    TorsoAngleY = memory_service.getData("Device/SubDeviceList/InertialSensor/AngleY/Sensor/Value")
    print ("Torso Angles [radian] X: %.3f, Y: %.3f" % (TorsoAngleX, TorsoAngleY))


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

