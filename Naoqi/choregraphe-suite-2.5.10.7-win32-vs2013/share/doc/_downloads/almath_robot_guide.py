#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example : Show how to guide NAO by the hand"""

import qi
import argparse
import sys
import almath
import time
import math
import almath_foot_clip

armName     = "LArm"
lFootOffset = almath.Pose2D(0.0, 0.09, 0.0)
rFootOffset = almath.Pose2D(0.0, -0.09, 0.0)
stepSpeed   = 1.0
stepLength  = 0.05

def initRobotPosition(motion_service, posture_service):
    """
    Inits NAO's position and stiffnesses to make the guiding possible.
    """

    motion_service.wakeUp()
    posture_service.goToPosture("StandInit", 0.3)
    motion_service.moveInit()
    time.sleep(1.0)
    # Make left arm loose.
    motion_service.setAngles("LWristYaw", 0.0, 1.0)
    motion_service.setAngles("Head", [0.44, -0.44], 0.5)
    motion_service.setStiffnesses(armName, 0.0)
    motion_service.setStiffnesses("LWristYaw", 0.2)

    # Disable arm moves while walking on left arm.
    motion_service.setMoveArmsEnabled(False, True)
    time.sleep(1.0)


def interpretJointsPose(motion_service, memory_service):
    """
    Translates the current left arm pose into a target position
    for NAO's foot.
    """

    # Retrieve current arm position.
    armPose = motion_service.getAngles(armName, True)

    targetX     = 0.0
    targetY     = 0.0
    targetTheta = 0.0
    gaitConfig = motion_service.getMoveConfig("Default")

    # Filter Shoulder Pitch.
    if (armPose[0] > - 0.9 and armPose[0] < -0.20):
        targetX = stepLength
    elif (armPose[0] > -2.5 and armPose[0] < -1.5):
        targetX = - stepLength - 0.02

    # Filter Wrist Yaw.
    if armPose[4] > 0.2:
        targetTheta = gaitConfig[2][1]
    elif armPose[4] < -0.2:
        targetTheta = - gaitConfig[2][1]

    # Return corresponding pose.
    return almath.Pose2D(targetX, targetY, targetTheta)


def moveToTargetPose(targetPose, motion_service, isLeftSupport):
    """Move to the desired target with the current foot."""

    name = ""
    targetTf = almath.transformFromPose2D(targetPose)

    # Compute foot position with the offset in NAOSpace.
    if isLeftSupport:
        footTargetTf = targetTf * almath.transformFromPose2D(rFootOffset)
        footTargetPose = almath.pose2DFromTransform(footTargetTf)
        name = ["RLeg"]
    else:
        footTargetTf = targetTf * almath.transformFromPose2D(lFootOffset)
        footTargetPose = almath.pose2DFromTransform(footTargetTf)
        name = ["LLeg"]

    # Clip the footstep to avoid collisions and too wide steps.
    almath_foot_clip.clipFootStep(footTargetPose, isLeftSupport)

    step = [[footTargetPose.x, footTargetPose.y, footTargetPose.theta]]
    speed = [stepSpeed]

    # Send the footstep to NAO.
    motion_service.setFootStepsWithSpeed(name, step, speed, False)

    # Change current foot.
    isLeftSupport = not isLeftSupport


def main(session):
    """
    This example shows how to guide NAO by the hand, while computing his
    moves with only footsteps, using ALMath library. Footstep clipping is
    described in almath_foot_clip.py.
    """
    # Get the services ALMemory, ALMotion, ALRobotPosture and ALLeds.

    memory_service = session.service("ALMemory")
    motion_service = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")
    leds_service = session.service("ALLeds")

    motion_service.setExternalCollisionProtectionEnabled("Move", False)

    # Init robot position.
    initRobotPosition(motion_service, posture_service)

    # Wait for the user to press the front tactile sensor.
    print "Please press head front tactile sensor to start."
    while not memory_service.getData("FrontTactilTouched"):
        pass

    print "To guide the robot use the robot left arm."
    print "Move LShoulderPitch to set x, y target and move LWristYaw to set wz target."
    print "When the robot eyes are green, the robot is ready to move."

    print "Starting..."
    print "Please press head rear tactile sensor to stop."

    # Start by moving left foot.
    isLeftSupport = False
    isMoving = False
    leds_service.fadeRGB("FaceLeds", 255, 0.1)

    while not memory_service.getData("RearTactilTouched"):
        targetPose = interpretJointsPose(motion_service, memory_service)
        # Filter the pose to avoid too small steps.
        if (math.fabs(targetPose.x) > 0.01) or \
           (math.fabs(targetPose.y) > 0.01) or \
           (math.fabs(targetPose.theta) > 0.08):

            moveToTargetPose(targetPose, motion_service, isLeftSupport)
            isLeftSupport = not isLeftSupport
            isMoving = True
            # Set LEDs to green.
            leds_service.fadeRGB("FaceLeds", 256 * 255, 0.1)

        elif isMoving:
            # Stop the robot.
            motion_service.stopMove()
            isMoving = False
            # Set LEDs to blue.
            leds_service.fadeRGB("FaceLeds", 255, 0.1)

    print "Stopping..."
    # Set LEDs to white.
    leds_service.fadeRGB("FaceLeds", 256 * 256 * 255 + 256 * 255 + 255, 0.1)

    # Crouch.
    motion_service.rest()


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
