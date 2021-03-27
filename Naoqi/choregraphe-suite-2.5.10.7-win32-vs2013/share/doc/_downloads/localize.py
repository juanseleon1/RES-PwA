#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use localization methods"""

import qi
import argparse
import sys


def main(session, exploration_file):
    """
    This example uses localization methods.
    """
    # Get the services ALNavigation and ALMotion.
    navigation_service = session.service("ALNavigation")
    motion_service = session.service("ALMotion")

    # Wake up robot
    motion_service.wakeUp()

    # Load a previously saved exploration
    navigation_service.loadExploration(exploration_file)

    # Relocalize the robot and start the localization process.
    guess = [0., 0.] # assuming the robot is not far from the place where
                     # he started the loaded exploration.
    navigation_service.relocalizeInMap(guess)
    navigation_service.startLocalization()

    # Navigate to another place in the map
    navigation_service.navigateToInMap([2., 0., 0.])

    # Check where the robot arrived
    print "I reached: " + str(navigation_service.getRobotPositionInMap()[0])

    # Stop localization
    navigation_service.stopLocalization()

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--ip", type=str, default="127.0.0.1",
                        help="Robot IP address. On robot or Local Naoqi: use '127.0.0.1'.")
    parser.add_argument("--port", type=int, default=9559,
                        help="Naoqi port number")
    parser.add_argument("--explo", type=str, help="Path to .explo file.")

    args = parser.parse_args()
    session = qi.Session()
    try:
        session.connect("tcp://" + args.ip + ":" + str(args.port))
    except RuntimeError:
        print ("Can't connect to Naoqi at ip \"" + args.ip + "\" on port " + str(args.port) +".\n"
               "Please check your script arguments. Run with -h option for help.")
        sys.exit(1)
    main(session, args.explo)
