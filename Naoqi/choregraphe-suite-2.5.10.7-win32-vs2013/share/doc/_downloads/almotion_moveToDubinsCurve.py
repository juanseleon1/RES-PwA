#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Walk To - Small example to make Nao Walk follow a Dubin Curve"""

import qi
import argparse
import sys
import almath


def main(session):
    """
    Walk To: Small example to make Nao Walk follow a Dubins Curve.
    """
    # Get the services ALMotion & ALRobotPosture.

    motion_service = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # Wake up robot
    motion_service.wakeUp()

    # Send robot to Stand Init
    posture_service.goToPosture("StandInit", 0.5)

    # first we defined the goal
    goal = almath.Pose2D(0.0, -0.4, 0.0)

    # We get the dubins solution (control points) by
    # calling an almath function

    circleRadius = 0.04
    # Warning : the circle use by dubins curve
    #           have to be 4*CircleRadius < norm(goal)
    dubinsSolutionAbsolute = almath.getDubinsSolutions(goal, circleRadius)

    # moveTo With control Points use relative commands but
    # getDubinsSolution return absolute position
    # So, we compute dubinsSolution in relative way
    dubinsSolutionRelative = []
    dubinsSolutionRelative.append(dubinsSolutionAbsolute[0])
    for i in range(len(dubinsSolutionAbsolute)-1):
        dubinsSolutionRelative.append(
                dubinsSolutionAbsolute[i].inverse() *
                dubinsSolutionAbsolute[i+1])

    # create a vector of moveTo with dubins Control Points
    moveToTargets = []
    for i in range(len(dubinsSolutionRelative)):
        moveToTargets.append(
            [dubinsSolutionRelative[i].x,
             dubinsSolutionRelative[i].y,
             dubinsSolutionRelative[i].theta] )

    # Initialized the Move process and be sure the robot is ready to move
    # without this call, the first getRobotPosition() will not refer to the position
    # of the robot before the move process
    motion_service.moveInit()

    # get robot position before move
    robotPositionBeforeCommand  = almath.Pose2D(motion_service.getRobotPosition(False))

    motion_service.moveTo( moveToTargets )

    # With MoveTo control Points, it's also possible to customize the gait parameters
    # motionProxy.moveTo(moveToTargets,
    #                    [["StepHeight", 0.001],
    #                     ["MaxStepX", 0.06],
    #                     ["MaxStepFrequency", 1.0]])

    # get robot position after move
    robotPositionAfterCommand = almath.Pose2D(motion_service.getRobotPosition(False))

    # compute and print the robot motion
    robotMoveCommand = almath.pose2DInverse(robotPositionBeforeCommand)*robotPositionAfterCommand
    print "The Robot Move Command: ", robotMoveCommand

    # Go to rest position
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
