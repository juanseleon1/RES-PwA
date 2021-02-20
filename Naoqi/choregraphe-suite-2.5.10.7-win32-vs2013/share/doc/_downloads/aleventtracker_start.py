#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use Tracking Module to Track an Object"""

import qi
import argparse
import sys
import time


def main(session):
    """
    This example shows how to use ALTracker to track an object with trackEvent api.
    This example is only a subscriber. You need to create another script to raise the tracked event.
    Your events should follow this structure :
        EventNameInfo {
          TargetPositionInFrameWorld,
          TimeStamp,
          EffectorId,
          HeadThreshold (optional)
          }
    All details are available in ALTracker API Documentation.
    """
    # Get the services ALTracker, ALMotion and ALRobotPosture.

    motion_service = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")
    tracker_service = session.service("ALTracker")


    # First, wake up.
    motion_service.wakeUp()

    fractionMaxSpeed = 0.8

    # Go to posture stand
    posture_service.goToPosture("StandInit", fractionMaxSpeed)

    # Set target to track.
    eventName = "ALTracker/BlobDetected"

    # set mode
    mode = "Move"
    tracker_service.setMode(mode)

    # Set the robot relative position to target
    # The robot stays a 50 centimeters of target with 10 cm precision
    tracker_service.setRelativePosition([-0.5, 0.0, 0.0, 0.1, 0.1, 0.3])

    # Then, start tracker.
    tracker_service.trackEvent(eventName)

    print "ALTracker successfully started."
    print "Use Ctrl+c to stop this script."

    try:
        while True:
            time.sleep(1)
    except KeyboardInterrupt:
        print
        print "Interrupted by user"
        print "Stopping..."

    # Stop tracker, go to posture Sit.
    tracker_service.stopTracker()
    tracker_service.unregisterAllTargets()
    posture_service.goToPosture("Sit", fractionMaxSpeed)
    motion_service.rest()

    print "ALTracker stopped."


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
