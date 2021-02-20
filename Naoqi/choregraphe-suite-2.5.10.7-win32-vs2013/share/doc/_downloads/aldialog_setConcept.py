import qi
import argparse
import sys


def main(session):
    """
    this example shows the setConcept method from the ALDialog API
    """
    ALDialog = session.service("ALDialog")
    ALDialog.setLanguage("English")
    topicName = ALDialog.loadTopic("/home/nao/aldialog_test_topic_file.top")
    ALDialog.activateTopic(topicName)
    ALDialog.subscribe("my_setConcept_test")
    ALDialog.setConcept("things_you_can_ride", "English", ["bike", "dragon", "unicorn", "double-decker bus"])
    try:
        ALDialog.forceInput("I want to ride a double-decker bus")
        ALDialog.forceInput("I want to ride a dragon")
    finally:
        ALDialog.unsubscribe("my_setConcept_test")
        ALDialog.clearConcepts()
        ALDialog.deactivateTopic(topicName)
        ALDialog.unloadTopic(topicName)


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

