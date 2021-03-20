import qi
import argparse
import sys


def main(session):
    """
    this example shows the deactivateTag method from the ALDialog API
    """
    ALDialog = session.service("ALDialog")
    ALDialog.setLanguage("English")
    topic_name = ALDialog.loadTopic("/home/nao/aldialog_test_topic_file.top")
    ALDialog.activateTopic(topic_name)
    ALDialog.subscribe("my_deactivateTag_test")

    tags = ["mytag_{}".format(i) for i in range (6)]  # mytag_0, mytag_1, (...), until mytag_5

    # below, activating all tags in case of a previous deactivation, normally active by default
    for tag in tags:
       ALDialog.activateTag(tag, "mytopic")  # you can use the "topic_name" variable here as well

    # playing each case with activated and then deactivated tag
    ALDialog.forceInput("single-tagged rule")  # standard response (mytag_0 active)
    ALDialog.deactivateTag("mytag_0", "mytopic")
    ALDialog.forceInput("single-tagged rule")  # "Dialog/NotUnderstood" is caught (mytag_0 blocked, rule not matched)

    ALDialog.forceInput("single-tagged output")  # standard response (mytag_1 active)
    ALDialog.deactivateTag("mytag_1", "mytopic")
    ALDialog.forceInput("single-tagged output")  # no response to this (mytag_1 blocked)

    ALDialog.forceInput("double-tagged rule")  # standard response (mytag_2 and mytag_3 active)
    ALDialog.deactivateTag("mytag_3", "mytopic")
    ALDialog.forceInput("double-tagged rule")  # "Dialog/NotUnderstood" is caught (mytag_2 active BUT mytag_3 blocked, rule not matched)

    ALDialog.forceInput("double-tagged output")  # standard response (mytag_4 and mytag_5 active)
    ALDialog.deactivateTag("mytag_5", "mytopic")
    ALDialog.forceInput("double-tagged output")  # no response to this (mytag_4 active BUT mytag_5 blocked)

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

