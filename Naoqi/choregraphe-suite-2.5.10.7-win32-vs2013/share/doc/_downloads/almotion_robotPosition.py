#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Robot Position - Small example"""

import qi
import argparse
import sys
import time
import math
import almath
try:
    import pylab as pyl
    PLOT_ALLOW = True
except ImportError:
    print "Matplotlib not found. this example will not plot data"
    PLOT_ALLOW = False


def main(session):
    """
    Robot Position - Small example to know how to deal
        with robotPosition and getFootSteps
    """
    # Get the services ALMotion & ALRobotPosture.

    motion_service = session.service("ALMotion")
    posture_service = session.service("ALRobotPosture")

    # Wake up robot
    motion_service.wakeUp()

    # Send robot to Stand Init
    posture_service.goToPosture("StandInit", 0.5)

    # Initialize the move
    motion_service.moveInit()

    # end init, begin experiment

    # First call of move API
    # Use _async=True argument to not be blocking here.
    motion_service.moveTo(0.3, 0.0, 0.5, _async=True)

    # wait that the move process start running
    time.sleep(0.1)

    # get robotPosition and nextRobotPosition
    useSensors = False
    robotPosition     = almath.Pose2D(motion_service.getRobotPosition(useSensors))
    nextRobotPosition = almath.Pose2D(motion_service.getNextRobotPosition())

    # get the first foot steps vector
    # (footPosition, unChangeable and changeable steps)

    footSteps1 = []
    try:
        footSteps1 = motion_service.getFootSteps()
    except Exception, errorMsg:
        print str(errorMsg)
        PLOT_ALLOW = False

    # Second call of move API
    motion_service.moveTo(0.3, 0.0, -0.5, _async=True)

    # get the second foot steps vector
    footSteps2 = []
    try:
        footSteps2 = motion_service.getFootSteps()
    except Exception, errorMsg:
        print str(errorMsg)
        PLOT_ALLOW = False

    # end experiment, begin compute

    # here we wait until the move process is over
    motion_service.waitUntilMoveIsFinished()
    # then we get the final robot position
    robotPositionFinal = almath.Pose2D(motion_service.getRobotPosition(False))

    # compute robot Move with the second call of move API
    # so between nextRobotPosition and robotPositionFinal
    robotMove = almath.pose2DInverse(nextRobotPosition)*robotPositionFinal
    print "Robot Move:", robotMove

    # Go to rest position
    motion_service.rest()

    # end compute, begin plot

    if PLOT_ALLOW:
        #################
        # Plot the data #
        #################
        pyl.figure()
        printRobotPosition(robotPosition, 'black')
        printRobotPosition(nextRobotPosition, 'blue')
        printFootSteps(footSteps1, 'green', 'red')

        pyl.figure()
        printRobotPosition(robotPosition, 'black')
        printRobotPosition(nextRobotPosition, 'blue')
        printFootSteps(footSteps2, 'blue', 'orange')

        pyl.show()

        # end plot

def printRobotPosition(pos, color):
    """ Function for plotting a robot position
        :param pos: an almath Pose2D
        :param color: the color of the robot
    """

    robotWidth = 0.01
    pyl.plot(pos.x, pos.y, color=color, marker='o', markersize=10)
    pyl.plot([pos.x, pos.x + robotWidth*math.cos(pos.theta)],
             [pos.y, pos.y + robotWidth*math.sin(pos.theta)],
             color=color,
             linewidth = 4)


def printFootSteps(footSteps, colorLeft, colorRight):
    """ Function for plotting the result of a getFootSteps
        :param footSteps: the result of a getFootSteps API call
        :param colorLeft: the color for left foot steps
        :param colorRight: the color for right foot steps
    """

    if len(footSteps[0]) == 2:
        posLeft  = footSteps[0][0]
        posRight = footSteps[0][1]

        if posLeft != posRight:
            leftPose2D = almath.Pose2D(posLeft[0], posLeft[1], posLeft[2])
            printLeftFootStep(leftPose2D, colorLeft, 3)
            rightPose2D = almath.Pose2D(posRight[0], posRight[1], posRight[2])
            printRightFootStep(rightPose2D, colorRight, 3)

    if len(footSteps[1]) >= 1:
        for i in range(len(footSteps[1])):
            name = footSteps[1][i][0]
            pos = footSteps[1][i][2]
            tmpPose2D = almath.Pose2D(pos[0], pos[1], pos[2])

            if name == 'LLeg':
                leftPose2D = rightPose2D * tmpPose2D
                printLeftFootStep(leftPose2D, colorLeft, 3)
            else:
                rightPose2D = leftPose2D * tmpPose2D
                printRightFootStep(rightPose2D, colorRight, 3)

    if len(footSteps[2]) >= 1:
        for i in range(len(footSteps[2])):
            name = footSteps[2][i][0]
            pos = footSteps[2][i][2]
            tmpPose2D = almath.Pose2D(pos[0], pos[1], pos[2])

            if name == 'LLeg':
                leftPose2D = rightPose2D * tmpPose2D
                printLeftFootStep(leftPose2D, colorLeft, 1)
            else:
                rightPose2D = leftPose2D * tmpPose2D
                printRightFootStep(rightPose2D, colorRight, 1)

    pyl.axis('equal')


def printLeftFootStep(footPose, color, size):
    """ Function for plotting a LEFT foot step
       :param footPose: an almath Pose2D
       :param color: the color for the foot step
       :param size: the size of the line
    """

    lFootBoxFL = footPose * almath.Pose2D( 0.110,  0.050, 0.0)
    lFootBoxFR = footPose * almath.Pose2D( 0.110, -0.038, 0.0)
    lFootBoxRR = footPose * almath.Pose2D(-0.047, -0.038, 0.0)
    lFootBoxRL = footPose * almath.Pose2D(-0.047,  0.050, 0.0)

    pyl.plot(footPose.x, footPose.y, color=color, marker='o', markersize=size*2)
    pyl.plot( [lFootBoxFL.x, lFootBoxFR.x, lFootBoxRR.x, lFootBoxRL.x, lFootBoxFL.x],
              [lFootBoxFL.y, lFootBoxFR.y, lFootBoxRR.y, lFootBoxRL.y, lFootBoxFL.y],
              color = color,
              linewidth = size)


def printRightFootStep(footPose, color, size):
    """ Function for plotting a RIGHT foot step
        :param footPose: an almath Pose2D
        :param color: the color for the foot step
        :param size: the size of the line
    """

    rFootBoxFL = footPose * almath.Pose2D( 0.110,  0.038, 0.0)
    rFootBoxFR = footPose * almath.Pose2D( 0.110, -0.050, 0.0)
    rFootBoxRR = footPose * almath.Pose2D(-0.047, -0.050, 0.0)
    rFootBoxRL = footPose * almath.Pose2D(-0.047,  0.038, 0.0)

    pyl.plot(footPose.x, footPose.y, color=color, marker='o', markersize=size*2)
    pyl.plot( [rFootBoxFL.x, rFootBoxFR.x, rFootBoxRR.x, rFootBoxRL.x, rFootBoxFL.x],
              [rFootBoxFL.y, rFootBoxFR.y, rFootBoxRR.y, rFootBoxRL.y, rFootBoxFL.y],
              color = color,
              linewidth = size)


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
