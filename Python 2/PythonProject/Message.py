# ----------------------------------------------------------------------------Message class---------------------------------------------------------------------------------------------
"""--------------------------------------------------------------------------Message class---------------------------------------------------------------------------------------------"""


# ----------------------------------------------------------------------------Message class---------------------------------------------------------------------------------------------
class messageManager:
    def __init__(self, idResponse=-1, response_type=None):
        self.__idResponse = idResponse
        self.__response_type = response_type
        self.__params = None

    def getIdResponse(self):
        return self.__idResponse

    def getResponseType(self):
        return self.__response_type

    def getParams(self):
        return self.__params

    def setIdResponse(self, idResponse):
        self.__idResponse = idResponse

    def setResponseType(self, response_type):
        self.__response_type = response_type

    def setParams(self, params):
        self.__params = params