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

    # Make the robot look for his charging station
    found = recharge.lookForStation()
    if found[0] != 0: # The charging station has not been found.
        print "Station is not found."
        return

    # Move in front of charging station using ALTracker
    correct = recharge.moveInFrontOfStation()
    if correct != 0: # The robot has not been able to move close to the charging station.
        print "Unable to move in front of the station."
        return

    # Start docking motion
    docked = recharge.dockOnStation()
    if docked != 0: # The docking failed.
        print "Unable to dock on the charging station."
        return
    print "Robot successfully docked."


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--ip", type=str, default="127.0.0.1", help="Robot IP address")
    parser.add_argument("--port", type=int, default=9559, help="Robot port number")

    args = parser.parse_args()
    main(args.ip, args.port)
