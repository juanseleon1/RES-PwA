#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use getData Method to Use FSR Sensors"""

import qi
import argparse
import sys


def main(session):
    """
    This example uses the getData method to use FSR sensors.
    """
    # Get the service ALMemory.

    memory_service = session.service("ALMemory")

    # Get The Left Foot Force Sensor Values
    LFsrFL = memory_service.getData("Device/SubDeviceList/LFoot/FSR/FrontLeft/Sensor/Value")
    LFsrFR = memory_service.getData("Device/SubDeviceList/LFoot/FSR/FrontRight/Sensor/Value")
    LFsrBL = memory_service.getData("Device/SubDeviceList/LFoot/FSR/RearLeft/Sensor/Value")
    LFsrBR = memory_service.getData("Device/SubDeviceList/LFoot/FSR/RearRight/Sensor/Value")

    print( "Left FSR [Kg] : %.2f %.2f %.2f %.2f" %  (LFsrFL, LFsrFR, LFsrBL, LFsrBR) )

    # Get The Right Foot Force Sensor Values
    RFsrFL = memory_service.getData("Device/SubDeviceList/RFoot/FSR/FrontLeft/Sensor/Value")
    RFsrFR = memory_service.getData("Device/SubDeviceList/RFoot/FSR/FrontRight/Sensor/Value")
    RFsrBL = memory_service.getData("Device/SubDeviceList/RFoot/FSR/RearLeft/Sensor/Value")
    RFsrBR = memory_service.getData("Device/SubDeviceList/RFoot/FSR/RearRight/Sensor/Value")

    print( "Right FSR [Kg] : %.2f %.2f %.2f %.2f" %  (RFsrFL, RFsrFR, RFsrBL, RFsrBR) )


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
