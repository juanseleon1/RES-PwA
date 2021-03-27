import qi
import argparse
import sys


def main(session):
    """
    this example shows the addBlockingEvent method from the ALDialog API
    """
    ALDialog = session.service("ALDialog")
    ALDialog.setLanguage("English")
    topic_name = ALDialog.loadTopic("/home/nao/aldialog_test_topic_file.top")
    ALDialog.activateTopic(topic_name)
    ALDialog.subscribe("my_deactivateTag_test")

    raw_input("\nThe robot is going to start counting from 0 to 5 now. "
              "Try touching its head's tactile sensors while it's speaking. "
              "It will not interrupt its speech. Press Enter to start:")
    ALDialog.forceInput("start counting")

    raw_input("\nThe robot is going to start counting from 0 to 5 now. "
              "Try touching its head's tactile sensors while it's speaking. "
              "Touching the head is now declared as a blocking event, the "
              "robot will react to your touch immediately. Press Enter to start:")
    ALDialog.addBlockingEvent("MiddleTactilTouched")
    ALDialog.addBlockingEvent("FrontTactilTouched")
    ALDialog.addBlockingEvent("RearTactilTouched")
    ALDialog.forceInput("start counting")

    raw_input("\nThe robot is going to start counting from 0 to 5 now. "
              "Try touching its head's tactile sensors while it's speaking. "
              "The default behavior is restored now, the robot won't interrupt its speech.")
    ALDialog.removeBlockingEvent("MiddleTactilTouched")
    ALDialog.removeBlockingEvent("FrontTactilTouched")
    ALDialog.removeBlockingEvent("RearTactilTouched")
    ALDialog.forceInput("start counting")

    ALDialog.unsubscribe("my_deactivateTag_test")
    ALDialog.deactivateTopic(topic_name)
    ALDialog.unloadTopic(topic_name)


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--ip", type=str, default="127.0.0.1",
                        help="Robot's IP address. If on a robot or a local Naoqi - use '127.0.0.1' (this is the default value).")
    parser.add_argument("--port", type=int, default=9559,
                        help="port number, the default value is OK in most cases")

    args = parser.parse_args()
    session = qi.Session()
    try:
        session.connect("tcp://{}:{}".format(args.ip, args.port))
    except RuntimeError:
        print ("Can't connect to Naoqi at IP {} (port {}).\nPlease check your script's arguments."
               " Run with -h option for help.".format(args.ip, args.port))
        sys.exit(1)
    main(session)

