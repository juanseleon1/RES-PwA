#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use onTouchDown Method"""

import qi
import argparse
import sys


def main(app):
    """
    This example uses the onTouchDown method.
    To Test ALTabletService, you need to run the script ON the robot.
    """
    # Get the service ALTabletService.

    try:
        session = app.session
        tabletService = session.service("ALTabletService")

        # Don't forget to disconnect the signal at the end
        signalID = 0

        # function called when the signal onTouchDown is triggered
        def callback(x, y):
            print "coordinate are x: ", x, " y: ", y
            if x > 640:
                # disconnect the signal
                tabletService.onTouchDown.disconnect(signalID)
                app.stop()

        # attach the callback function to onJSEvent signal
        signalID = tabletService.onTouchDown.connect(callback)
        app.run()
    except Exception, e:
        print "Error was: ", e


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--ip", type=str, default="127.0.0.1",
                        help="Robot IP address. On robot or Local Naoqi: use '127.0.0.1'.")
    parser.add_argument("--port", type=int, default=9559,
                        help="Naoqi port number")

    args = parser.parse_args()
    try:
        connection_url = "tcp://" + args.ip + ":" + str(args.port)
        app = qi.Application(["TabletModule", "--qi-url=" + connection_url])
        app.start()
    except RuntimeError:
        print ("Can't connect to Naoqi at ip \"" + args.ip + "\" on port " + str(args.port) +".\n"
               "Please check your script arguments. Run with -h option for help.")
        sys.exit(1)
    main(app)
