import qi
import argparse
import sys


def main(session):
    """
    this example shows the setDelay method from the ALDialog API
    """
    ALDialog = session.service("ALDialog")
    ALDialog.setLanguage("English")
    topic_name = ALDialog.loadTopic("/home/nao/aldialog_test_topic_file.top")
    ALDialog.activateTopic(topic_name)
    ALDialog.subscribe("my_setDelay_test")

    raw_input("\nThe robot is going to start counting from 0 to 5. "
              "Try touching its head's tactile sensors while it's speaking. "
              "The timeout is set to default (2 seconds). Press Enter to start:")
    ALDialog.forceInput("start counting")

    raw_input("\nThe robot is going to start counting from 0 to 5. "
              "Try touching its head's tactile sensors while it's speaking. "
              "The timeout is set to infinite now. Even if you touch the head when the "
              "robot only starts counting, it will still react after having stopped speaking. Press Enter to start:")
    ALDialog.setDelay("MiddleTactilTouched", -1)
    ALDialog.setDelay("FrontTactilTouched", -1)
    ALDialog.setDelay("RearTactilTouched", -1)
    ALDialog.forceInput("start counting")

    raw_input("\nThe robot is going to start counting from 0 to 5. "
              "Try touching its head's tactile sensors while it's speaking. "
              "The timeout is set back to default (2 seconds). Press Enter to start:")
    ALDialog.setDelay("MiddleTactilTouched", 2000)
    ALDialog.setDelay("FrontTactilTouched", 2000)
    ALDialog.setDelay("RearTactilTouched", 2000)
    ALDialog.forceInput("start counting")

    ALDialog.unsubscribe("my_setDelay_test")
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

