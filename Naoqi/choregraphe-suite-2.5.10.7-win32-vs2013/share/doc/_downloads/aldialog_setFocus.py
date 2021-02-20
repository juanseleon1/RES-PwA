import qi
import argparse
import sys


def main(session):
    """
    this example shows the setFocus method from the ALDialog API
    """
    ALDialog = session.service("ALDialog")
    ALDialog.setLanguage("English")
    topic_name = ALDialog.loadTopic("/home/nao/aldialog_test_topic_file.top")
    topic_name_2 = ALDialog.loadTopic("/home/nao/aldialog_test_topic_file_2.top")
    ALDialog.activateTopic(topic_name)
    ALDialog.activateTopic(topic_name_2)
    ALDialog.subscribe("my_setFocus_test")

    ALDialog.setFocus("mytopic")
    ALDialog.forceOutput()
    ALDialog.forceOutput()
    ALDialog.forceOutput()  # nothing more to say from "mytopic"
    ALDialog.setFocus("mytopic_2")
    ALDialog.forceOutput()
    ALDialog.forceOutput()
    ALDialog.forceOutput()  # nothing more to say from "mytopic_2"

    ALDialog.unsubscribe("my_setFocus_test")
    ALDialog.deactivateTopic(topic_name)
    ALDialog.deactivateTopic(topic_name_2)
    ALDialog.unloadTopic(topic_name)
    ALDialog.unloadTopic(topic_name_2)


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

