# -*- encoding: UTF-8 -*-

''' ALRecharge: Makes the robot move onto his charging station. '''

import argparse
import qi

def main(ip, port = 9559):
    s = qi.Session()
    s.connect("tcp://"+str(ip)+":"+str(port))
    # Get proxies
    recharge = s.service("ALRecharge")
    motion = s.service("ALMotion")

    # Wake the robot up.
    motion.wakeUp()

    # Make the robot go to his charging station
    success = recharge.goToStation()
    if success != 0: # The charging station has not been found.
        print "Station is not found."
        return
    print "Robot successfully docked."


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--ip", type=str, default="127.0.0.1", help="Robot IP address")
    parser.add_argument("--port", type=int, default=9559, help="Robot port number")

    args = parser.parse_args()
    main(args.ip, args.port)
