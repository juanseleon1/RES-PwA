

class AldebaranException(Exception):
    """ Base exception for all Aldebaran API Exceptions. """

    def __init__(self, status, message, data):
        super(AldebaranException, self).__init__()
        self.status = status
        self.message = message
        self.data = data

    def __repr__(self):
        return self.__str__()

    def __str__(self):
        return '%s: %s (%s)' % (self.status, self.message, self.data)
