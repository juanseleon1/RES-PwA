#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use notifications Method"""

import qi
import argparse
import sys


def main(session):
    """
    This example uses the notifications method.
    """
    # Get the service ALNotificationManager.

    notif_mng_service = session.service("ALNotificationManager")

    # Get the notifications.
    notifications = notif_mng_service.notifications()
    for notification in notifications:
        notifDict = dict(notification)
        print "Notification ID: " + str(notifDict["id"])
        print "\tMessage: " + notifDict["message"]
        print "\tSeverity: " + notifDict["severity"]
        print "\tRemove On Read: " + str(notifDict["removeOnRead"])
        print "-----------\n"


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
