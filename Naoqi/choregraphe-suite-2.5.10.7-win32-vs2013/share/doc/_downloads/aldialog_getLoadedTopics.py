import qi
import argparse
import sys


def main(session):
    """
    this example shows the getLoadedTopics method from the ALDialog API
    """
    ALDialog = session.service("ALDialog")

    topic_name_eng_1 = ALDialog.loadTopic("/home/nao/aldialog_test_topic_file.top")
    print "\nLoaded an English topic:"
    print "French loaded topics:", ALDialog.getLoadedTopics("French")
    print "English loaded topics:", ALDialog.getLoadedTopics("English")
    print "All loaded topics:", ALDialog.getAllLoadedTopics()

    topic_name_eng_2 = ALDialog.loadTopic("/home/nao/aldialog_test_topic_file_2.top")
    print "\nLoaded another English topic:"
    print "English loaded topics:", ALDialog.getLoadedTopics("English")
    print "All loaded topics:", ALDialog.getAllLoadedTopics()

    topic_name_frf_1= ALDialog.loadTopic("/home/nao/aldialog_test_topic_file_frf.top")
    print "\nLoaded a French topic:"
    print "French loaded topics:", ALDialog.getLoadedTopics("French")
    print "All loaded topics:", ALDialog.getAllLoadedTopics()

    ALDialog.unloadTopic("mytopic")  # notice that this call unloads both the French anf English "mytopic"
    print "\nUnloaded 'mytopic':"
    print "French loaded topics:", ALDialog.getLoadedTopics("French")
    print "English loaded topics:", ALDialog.getLoadedTopics("English")
    print "All loaded topics:", ALDialog.getAllLoadedTopics()

    ALDialog.unloadTopic("mytopic_2")
    print "\nUnloaded 'mytopic_2':"
    print "All loaded topics:", ALDialog.getAllLoadedTopics()


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
