#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use executeJS Method"""

import qi
import argparse
import sys
import time


def main(session):
    """
    This example uses the executeJS method.
    To Test ALTabletService, you need to run the script ON the robot.
    """
    # Get the service ALTabletService.
    tabletService = session.service("ALTabletService")

    try:
        # Display a local web page located in boot-config/html folder
        # The ip of the robot from the tablet is 198.18.0.1
        tabletService.showWebview("http://198.18.0.1/apps/boot-config/preloading_dialog.html")

        time.sleep(3)

        # Javascript script for displaying a prompt
        # ALTabletBinding is a javascript binding inject in the web page displayed on the tablet
        script = """
            var name = prompt("Please enter your name", "Harry Pepper");
            ALTabletBinding.raiseEvent(name)
        """

        # Don't forget to disconnect the signal at the end
        signalID = 0

        # function called when the signal onJSEvent is triggered
        # by the javascript function ALTabletBinding.raiseEvent(name)
        def callback(event):
            print "your name is:", event
            promise.setValue(True)

        promise = qi.Promise()

        # attach the callback function to onJSEvent signal
        signalID = tabletService.onJSEvent.connect(callback)

        # inject and execute the javascript in the current web page displayed
        tabletService.executeJS(script)

        try:
            promise.future().hasValue(30000)
        except RuntimeError:
            raise RuntimeError('Timeout: no signal triggered')

    except Exception, e:
        print "Error was:", e

    # Hide the web view
    tabletService.hideWebview()
    # disconnect the signal
    tabletService.onJSEvent.disconnect(signalID)


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
