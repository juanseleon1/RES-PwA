#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: A Simple class to get & read BarcodeDetection Events"""

import qi
import argparse
import sys
import time


class BarcodeReader(object):
    """
    A simple class to react to barcode detection events.
    """
    def __init__(self, app):
        super(BarcodeReader, self).__init__()
        # start application and get session
        app.start()
        session = app.session
        # Get the services ALBarcodeReader and ALMemory.
        self.memory_service = session.service("ALMemory")
        self.barcode_service = session.service("ALBarcodeReader")
        self.subscriber = self.memory_service.subscriber("BarcodeReader/BarcodeDetected")
        self.subscriber.signal.connect(self.on_barcode_detected)
        self.barcode_service.subscribe("test_barcode")

    def on_barcode_detected(self, value):
        """
        Callback for event BarcodeReader/BarcodeDetected
        """
        print "I saw a barcode"
        print "The event data are: " +str(value)

    def run(self):
        """
        Loop on, wait for events until manual interruption.
        """
        print "Starting BarcodeReader"
        try:
            while True:
                time.sleep(1)
        except KeyboardInterrupt:
            print "Interrupted by user, stopping BarcodeReader"
            self.barcode_service.unsubscribe("test_barcode")
            # Stop
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
        app = qi.Application(["BarcodeReader", "--qi-url=" + connection_url])
    except RuntimeError:
        print ("Can't connect to Naoqi at ip \"" + args.ip + "\" on port " + str(args.port) +".\n"
               "Please check your script arguments. Run with -h option for help.")
        sys.exit(1)
    barcode_reader = BarcodeReader(app)
    barcode_reader.run()
