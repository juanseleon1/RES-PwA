#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use setCollisionProtectionEnabled Method"""

import qi
import argparse
import sys
import time
import motion


def main(session):
    ''' Example showing the effect of collision detection
        Nao bumps his chest with his left arm with collision detection enabled
        or disabled.
    '''
    # Get the service ALMotion.

    motion_service  = session.service("ALMotion")
    model_service  = session.service("ALRobotModel")

    # Get the robot configuration.
    if model_service.getRobotType() != "Nao" or not model_service.hasArms():
        print "This test is not available for your Robot"
        print "---------------------"
        exit(1)

    pChainName = "LArm"
    hasHands = model_service.hasHands()

    # Send robot to Pose Init.
    moveLArm(motion_service, hasHands, "Init")

    # Disable collision detection on LArm chain.
    pEnable = False
    success = motion_service.setCollisionProtectionEnabled(pChainName, pEnable)
    if (not success):
        print("Failed to disable collision protection")
    time.sleep(1.0)
    # Make NAO's arm move so that it bumps its torso.
    moveLArm(motion_service, hasHands, "Final")
    time.sleep(1.0)

    # Go back to pose init.
    moveLArm(motion_service, hasHands, "Init")
    # Enable collision detection on chainName.
    pEnable = True
    success = motion_service.setCollisionProtectionEnabled(pChainName, pEnable)
    if (not success):
        print("Failed to enable collision protection")

    # Make NAO's arm move and see that it does not bump on the torso.
    moveLArm(motion_service, hasHands, "Final")
    time.sleep(1.0)

    # Go back to pose init.
    moveLArm(motion_service, hasHands, "Init")


def moveLArm(motion_service, pHasHands, pPose):
    ''' Function to make NAO bump on his Torso with his left arm '''

    # Define the name of the chain controlled
    pChainName = "LArm"

    # Define the final position.
    if (pPose == "Init"):
        pTargetAngles = [
             80, # LShoulderPitch
             20, # LShoulderRoll
            -80, # LElbowYaw
            -60] # LElbowRoll
    elif (pPose == "Final"):
        pTargetAngles = [
             50, # LShoulderPitch
              6, # LShoulderRoll
              0, # LElbowYaw
           -150] # LElbowRoll
    else:
        print "ERROR: Your pose is unknown"
        print "---------------------"
        exit(1)

    # Set the target angles according to the robot version.
    if pHasHands:
        pTargetAngles += [0.0, 0.0]

    # Convert to radians.
    pTargetAngles = [x * motion.TO_RAD for x in pTargetAngles]

    # Set the fraction of max speed for the arm movement.
    pMaxSpeedFraction = 0.5

    # Set NAO in stiffness On.
    motion_service.setStiffnesses("LArm", 1.0)

    # Move the arm to the final position.
    motion_service.angleInterpolationWithSpeed(pChainName, pTargetAngles, pMaxSpeedFraction)


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
