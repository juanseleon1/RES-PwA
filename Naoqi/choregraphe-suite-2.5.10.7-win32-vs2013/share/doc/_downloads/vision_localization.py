#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Demonstrates a way to localize the robot with ALLandMarkDetection"""

import qi
import time
import sys
import argparse
import math
import almath


class LandmarkDetector(object):
    """
    We first instantiate a proxy to the ALLandMarkDetection module
    Note that this module should be loaded on the robot's naoqi.
    The module output its results in ALMemory in a variable
    called "LandmarkDetected".
    We then read this ALMemory value and check whether we get
    interesting things.
    After that we get the related position of the landmark compared to robot.
    """

    def __init__(self, app):
        """
        Initialisation of qi framework and event detection.
        """
        super(LandmarkDetector, self).__init__()
        app.start()
        session = app.session
        # Get the service ALMemory.
        self.memory = session.service("ALMemory")
        # Connect the event callback.
        self.subscriber = self.memory.subscriber("LandmarkDetected")
        self.subscriber.signal.connect(self.on_landmark_detected)
        # Get the services ALTextToSpeech, ALLandMarkDetection and ALMotion.
        self.tts = session.service("ALTextToSpeech")
        self.landmark_detection = session.service("ALLandMarkDetection")
        self.motion_service = session.service("ALMotion")
        self.landmark_detection.subscribe("LandmarkDetector", 500, 0.0 )
        self.got_landmark = False
        # Set here the size of the landmark in meters.
        self.landmarkTheoreticalSize = 0.06 #in meters
        # Set here the current camera ("CameraTop" or "CameraBottom").
        self.currentCamera = "CameraTop"

    def on_landmark_detected(self, markData):
        """
        Callback for event LandmarkDetected.
        """
        if markData == []:  # empty value when the landmark disappears
            self.got_landmark = False
        elif not self.got_landmark:  # only speak the first time a landmark appears
            self.got_landmark = True
            print "I saw a landmark! "
            self.tts.say("I saw a landmark! ")

            # Retrieve landmark center position in radians.
            wzCamera = markData[1][0][0][1]
            wyCamera = markData[1][0][0][2]

            # Retrieve landmark angular size in radians.
            angularSize = markData[1][0][0][3]

            # Compute distance to landmark.
            distanceFromCameraToLandmark = self.landmarkTheoreticalSize / ( 2 * math.tan( angularSize / 2))

            # Get current camera position in NAO space.
            transform = self.motion_service.getTransform(self.currentCamera, 2, True)
            transformList = almath.vectorFloat(transform)
            robotToCamera = almath.Transform(transformList)

            # Compute the rotation to point towards the landmark.
            cameraToLandmarkRotationTransform = almath.Transform_from3DRotation(0, wyCamera, wzCamera)

            # Compute the translation to reach the landmark.
            cameraToLandmarkTranslationTransform = almath.Transform(distanceFromCameraToLandmark, 0, 0)

            # Combine all transformations to get the landmark position in NAO space.
            robotToLandmark = robotToCamera * cameraToLandmarkRotationTransform *cameraToLandmarkTranslationTransform

            print "x " + str(robotToLandmark.r1_c4) + " (in meters)"
            print "y " + str(robotToLandmark.r2_c4) + " (in meters)"
            print "z " + str(robotToLandmark.r3_c4) + " (in meters)"

    def run(self):
        """
        Loop on, wait for events until manual interruption.
        """
        print "Starting LandmarkDetector"
        try:
            while True:
                time.sleep(1)
        except KeyboardInterrupt:
            print "Interrupted by user, stopping LandmarkDetector"
            self.landmark_detection.unsubscribe("LandmarkDetector")
            #stop
            sys.exit(0)


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--ip", type=str, default="127.0.0.1",
                        help="Robot IP address. On robot or Local Naoqi: use '127.0.0.1'.")
    parser.add_argument("--port", type=int, default=9559,
                        help="Naoqi port number")

    args = parser.parse_args()
    try:
        # Initialize qi framework.
        connection_url = "tcp://" + args.ip + ":" + str(args.port)
        app = qi.Application(["LandmarkDetector", "--qi-url=" + connection_url])
    except RuntimeError:
        print ("Can't connect to Naoqi at ip \"" + args.ip + "\" on port " + str(args.port) +".\n"
               "Please check your script arguments. Run with -h option for help.")
        sys.exit(1)
    landmark_detector = LandmarkDetector(app)
    landmark_detector.run()
