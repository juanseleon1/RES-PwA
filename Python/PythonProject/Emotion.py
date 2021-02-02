
#----------------------------------------------------------------------------Emotion class---------------------------------------------------------------------------------------------
"""--------------------------------------------------------------------------Emotion class---------------------------------------------------------------------------------------------"""
#----------------------------------------------------------------------------Emotion class---------------------------------------------------------------------------------------------

class Emotion:
    def __init__(self):
        self.__toneSpeech = 1.1
        self.__ledR = 1
        self.__ledG = 1
        self.__ledB = 1
        self.__ledIntensity = 1
        self.__factorVelocity = 0.0
        self.__velocitySpeech = 100

    #Getters

    def getToneSpeech(self):
        return self.__toneSpeech

    def getLedR(self):
        return self.__ledR

    def getLedG(self):
        return self.__ledG

    def getLedB(self):
        return self.__ledB

    def getLedIntensity(self):
        return self.__ledIntensity

    def getFactorVelocity(self):
        return self.__factorVelocity

    def getVelocitySpeech(self):
        return self.__velocitySpeech

    #Setters

    def setToneSpeech(self, tone):
        self.__toneSpeech = tone

    def setLedR(self, ledR):
        self.__ledR = ledR

    def setLedG(self, ledG):
        self.__ledG = ledG

    def setLedB(self, ledB):
        self.__ledB = ledB

    def setLedIntensity(self, ledIntensity ):
        self.__ledIntensity = ledIntensity

    def setFactorVelocity(self, factorVelocity):
        self.__factorVelocity = factorVelocity

    def setVelocitySpeech(self, velocitySpeech ):
        self.__velocitySpeech = velocitySpeech