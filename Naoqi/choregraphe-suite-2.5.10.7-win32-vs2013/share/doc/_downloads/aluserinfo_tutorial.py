#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use UserInfo Module"""

import qi
import argparse
import sys


def main(session):
    """
    This example uses the module UserInfo. Please be in front of the robot
    AND in life mode when running it. It needs a focused user.
    """
    #Get proxy on the services ALUserInfo & ALUserSession.

    user_info_service = session.service("ALUserInfo")
    user_session_service = session.service("ALUserSession")

    # We suppose here that there is a focused user, but you can read and edit
    # any data of any existing user
    if(user_session_service.getFocusedUser() == -1):
        print "There is no focused user"
        exit()

    myDomain = "com.aldebaran.myApp"

    # Set data, get data, check data, remove data
    user_info_service.set(myDomain, "nickname", "superHuman")
    nickname = user_info_service.get(myDomain, "nickname")
    print nickname #Should print superHuman
    print user_info_service.has(myDomain, "nickname") # should print True
    user_info_service.remove(myDomain, "nickname")
    print user_info_service.has(myDomain, "nickname") # should print False

    # RemoveUser data
    user_info_service.set(myDomain, "lastScore", "43")
    lastScore = user_info_service.get(myDomain, "lastScore")
    print lastScore #Should print 43
    print user_info_service.has(myDomain, "lastScore") # should print True

    user_info_service.set(myDomain, "highScore", "62")
    highScore = user_info_service.get(myDomain, "highScore")
    print highScore #Should print 62
    print user_info_service.has(myDomain, "highScore") # should print True

    user_info_service.removeUser(myDomain)
    print user_info_service.has(myDomain, "lastScore") # should print False
    print user_info_service.has(myDomain, "highScore") # should print False

    # Check type
    user_info_service.set(myDomain, "meaningOfLife", 42)
    print user_info_service.getType(myDomain, "meaningOfLife") #should print int

    user_info_service.set(myDomain, "meaningOfLife", "Play games")
    print user_info_service.getType(myDomain, "meaningOfLife") #should print string


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
