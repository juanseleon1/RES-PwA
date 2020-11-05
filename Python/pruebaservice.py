from naoqi import ALProxy
tts = ALProxy("ALTextToSpeech", "10.195.22.24", 9559)
tts.say("Hello, world!")