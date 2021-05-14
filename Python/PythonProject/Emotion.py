
#----------------------------------------------------------------------------Emotion class---------------------------------------------------------------------------------------------
"""--------------------------------------------------------------------------Emotion class---------------------------------------------------------------------------------------------"""
#----------------------------------------------------------------------------Emotion class---------------------------------------------------------------------------------------------

class Emotion:
    def __init__(self):
        self.__toneSpeech = 1.1
        self.__ledColor = 0xDAA2F8
        self.__ledIntensity = 1
        self.__factorVelocity = 0.0
        self.__velocitySpeech = 100
        self.__rotationEyesColor = 1
        self.__durationEyesColor = 14
    #Getters

    def getDurationEyesColor(self):
        return self.__durationEyesColor

    def getRotationEyesColor(self):
        return self.__rotationEyesColor

    def getToneSpeech(self):
        return self.__toneSpeech

    def getLedColor(self):
        return self.__ledColor

    def getLedIntensity(self):
        return self.__ledIntensity

    def getFactorVelocity(self):
        return self.__factorVelocity

    def getVelocitySpeech(self):
        return self.__velocitySpeech

    #Setters

    def setToneSpeech(self, tone):
        self.__toneSpeech = tone

    def setLedColor(self, ledColor):
        self.__ledColor = ledColor

    def setLedIntensity(self, ledIntensity ):
        self.__ledIntensity = ledIntensity

    def setFactorVelocity(self, factorVelocity):
        self.__factorVelocity = factorVelocity

    def setVelocitySpeech(self, velocitySpeech ):
        self.__velocitySpeech = velocitySpeech

    def setRotationEyesColor(self, rotationEyesColor ):
        self.__rotationEyesColor = rotationEyesColor