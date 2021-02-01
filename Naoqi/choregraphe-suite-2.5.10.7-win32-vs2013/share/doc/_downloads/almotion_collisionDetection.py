#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

''' Example :Collision detection - Arm Collision Detection '''

import qi
import argparse
import sys
import almath
import time


def moveArm(motion_service, target, has_hands, chain_name):
    ''' Function to make NAO bump on his Torso or Head with his arm '''

    # Set the fraction of max speed for the arm movement.
    pMaxSpeedFraction = 0.5

    # Define the final position.
    if target == "Torso":
        shoulderPitchAngle = 50
    elif target == "Head":
        shoulderPitchAngle = -50
    else:
        print "ERROR: target is unknown"
        print "Must be Torso or Head"
        print "---------------------"
        exit(1)

    ShoulderRollAngle  = 6
    ElbowYawAngle      = 0
    ElbowRollAngle     = -150

    if chain_name == "LArm":
        targetAngles = [shoulderPitchAngle, +ShoulderRollAngle,
            +ElbowYawAngle, +ElbowRollAngle]
    elif chain_name == "RArm":
        targetAngles = [shoulderPitchAngle, -ShoulderRollAngle,
            -ElbowYawAngle, -ElbowRollAngle]
    else:
        print "ERROR: chainName is unknown"
        print "Must be LArm or RArm"
        print "---------------------"
        exit(1)

    # Set the target angles according to the robot version.
    if has_hands:
        targetAngles += [0.0, 0.0]

    # Convert to radians.
    targetAngles = [x * almath.TO_RAD for x in targetAngles]

    # Move the arm to the final position.
    motion_service.angleInterpolationWithSpeed(
        chain_name, targetAngles, pMaxSpeedFraction)


def main(session, chain_name):
    """
    Collision detection : arm collision detection
    """
    # Get the services ALMotion, ALRobotModel & ALRobotPosture.

    motion_service = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")
    model_service = session.service("ALRobotModel")

    if model_service.getRobotType() != "Nao" or not model_service.hasArms():
        print "This test is not available for your Robot"
        print "---------------------"
        exit(1)

    # Wake up robot
    motion_service.wakeUp()

    # Send robot to Stand Init
    posture_service.goToPosture("StandInit", 0.5)

    has_hands = model_service.hasHands()

    ###############################
    # Arm motion bumping on torso #
    ###############################

    # Disable collision detection on chainName.
    is_enable = False
    success = motion_service.setCollisionProtectionEnabled(chain_name, is_enable)
    if (not success):
        print("Failed to disable collision protection")
    time.sleep(1.0)

    # Make NAO's arm move so that it bumps its torso.
    target = "Torso"
    moveArm(motion_service, target, has_hands, chain_name)
    time.sleep(1.0)

    # Go back to pose init.
    posture_service.goToPosture("StandInit", 1.0)

    # Enable collision detection on chainName.
    is_enable = True
    success = motion_service.setCollisionProtectionEnabled(chain_name, is_enable)
    if (not success):
        print("Failed to enable collision protection")
    time.sleep(1.0)

    # Make NAO's arm move and see that it does not bump on the torso.
    target = "Torso"
    moveArm(motion_service, target, has_hands, chain_name)

    ##############################
    # Arm motion bumping on head #
    ##############################

    time.sleep(1.0)
    # Go back to pose init.
    posture_service.goToPosture("StandInit", 1.0)
    # Disable collision detection on chainName.
    is_enable = False
    success = motion_service.setCollisionProtectionEnabled(chain_name, is_enable)
    if (not success):
        print("Failed to disable collision protection")
    time.sleep(1.0)
    # Make NAO's arm move so that it bumps its head.
    target = "Head"
    moveArm(motion_service, target, has_hands, chain_name)

    time.sleep(1.0)
    # Go back to pose init.
    posture_service.goToPosture("StandInit", 1.0)
    # Enable collision detection on chainName.
    is_enable = True
    success = motion_service.setCollisionProtectionEnabled(chain_name, is_enable)
    if (not success):
        print("Failed to enable collision protection")
    # Make NAO's arm move and see that it does not bump on the head.
    target = "Head"
    moveArm(motion_service, target, has_hands, chain_name)

    time.sleep(1.0)
    # Go back to pose init.
    posture_service.goToPosture("StandInit", 1.0)

    # Go to rest position
    motion_service.rest()


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--ip", type=str, default="127.0.0.1",
                        help="Robot IP address. On robot or Local Naoqi: use '127.0.0.1'.")
    parser.add_argument("--port", type=int, default=9559,
                        help="Naoqi port number")
    parser.add_argument("--chain", type=str, default="LArm",
                        choices=["LArm", "RArm"], help="Chain name")

    args = parser.parse_args()
    session = qi.Session()
    try:
        session.connect("tcp://" + args.ip + ":" + str(args.port))
    except RuntimeError:
        print ("Can't connect to Naoqi at ip \"" + args.ip + "\" on port " + str(args.port) +".\n"
               "Please check your script arguments. Run with -h option for help.")
        sys.exit(1)
    main(session, args.chain)
