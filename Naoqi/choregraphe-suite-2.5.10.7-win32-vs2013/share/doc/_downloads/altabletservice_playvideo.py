#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use playVideo Method"""

import qi
import argparse
import sys
import time


def main(session):
    """
    This example uses the playVideo method.
    To Test ALTabletService, you need to run the script ON the robot.
    """
    # Get the service ALTabletService.

    try:
        tabletService = session.service("ALTabletService")

        # Ensure that the tablet wifi is enable
        tabletService.enableWifi()

        # Play a video from the web and display the player
        # If you want to play a local video, the ip of the robot from the tablet is 198.18.0.1
        # Put the video in the HTML folder of your behavior
        # "http://198.18.0.1/apps/my_behavior/my_video.mp4"
        tabletService.playVideo("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4")

        time.sleep(3)

        # Display the time elapse / the total time of the video
        print tabletService.getVideoPosition(), " / ", tabletService.getVideoLength()

        # Pause the video
        tabletService.pauseVideo()

        time.sleep(3)

        # resume the video
        tabletService.resumeVideo()

        time.sleep(3)

        # stop the video and hide the player
        tabletService.stopVideo()
    except Exception, e:
        print "Error was: ", e


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
