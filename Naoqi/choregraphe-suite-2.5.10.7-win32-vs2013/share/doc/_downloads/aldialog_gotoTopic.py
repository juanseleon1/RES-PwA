import qi
import argparse
import sys


def main(session):
    """
    this example shows the gotoTopic method from the ALDialog API
    """
    ALDialog = session.service("ALDialog")
    ALDialog.setLanguage("English")
    topicContent = ("topic: ~mytopic()\n"
                    "language: enu\n"
                    "proposal: hello\n"
                    "u1:(hi) nice to meet you\n")
    topicName = ALDialog.loadTopicContent(topicContent)
    ALDialog.activateTopic(topicName)
    ALDialog.subscribe("my_gotoTopic_test")
    ALDialog.gotoTopic("mytopic")  # "mytopic" is the value of topicName
    ALDialog.forceInput("hi")
    ALDialog.unsubscribe("my_gotoTopic_test")
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

