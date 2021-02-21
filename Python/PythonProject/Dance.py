
class Dance:
    def __init__(self, session, HOST):
        self.__modules = {
            # ActivityServices-------------------------------------------------------
            "MACARENA": self.dance_macarena,
            "LAMBADA": self.dance_lambada
        }

    def getAnimation(self, animation):
        return self.__modules.get( animation )

    def dance_lambada(self, factor=1):
        pass


    def dance_macarena(self, factor=1):
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([0, 1.16, 2.36, 4.76, 5.96, 8.36, 9.56, 11.96, 14.36, 16.36])
        keys.append(
            [-0.211185, -0.211185, -0.211185, 0.123918, 0.123918, 0.445059, 0.123918, 0.123918, 0.123918, 0.123918])

        names.append("HeadYaw")
        times.append([0, 1.16, 2.36, 4.76, 5.96, 8.36, 9.56, 11.96, 13.16, 14.36, 16.36])
        keys.append(
            [-0.00698132, 0.219911, -0.00698132, -0.364774, -0.0174533, -0.00698132, -0.00698132, 0.329867, -0.118682,
             0.127409, -0.0314159])

        names.append("HipPitch")
        times.append([0, 1.16, 8.36])
        keys.append([-0.0357826, -0.0474347, 0])

        names.append("HipRoll")
        times.append([0, 1.16, 8.36])
        keys.append([-0.0041018, -0.00654055, -0.00523599])

        names.append("KneePitch")
        times.append([0, 1.16, 8.36])
        keys.append([-0.0133719, -0.0163339, 0])

        names.append("LElbowRoll")
        times.append([0, 1.16, 2.36, 4.76, 9.56, 13.16, 14.36, 16.36])
        keys.append([-1.56207, -0.00872665, -0.00872665, -0.00872665, -0.00872665, -1.37357, -1.41372, -1.41372])

        names.append("LElbowYaw")
        times.append([0, 1.16, 2.36, 3.56, 4.76, 9.56, 13.16, 14.36, 16.36])
        keys.append([-0.118682, -0.118682, -0.118682, 0.722566, 0.722566, -1.80816, -0.197222, -0.830777, -0.849975])

        names.append("LHand")
        times.append([0, 1.16, 2.36, 3.56])
        keys.append([0.02, 0.2, 0.2, 0.87])

        names.append("LShoulderPitch")
        times.append([0, 1.16, 2.36, 3.56, 4.76, 9.56, 13.16, 14.36, 16.36])
        keys.append([1.32994, 1.7558, -1.22173, 0.0837758, 0.0837758, 0.0837758, 0.0837758, -0.289725, 1.69821])

        names.append("LShoulderRoll")
        times.append([0, 1.16, 2.36, 3.56, 4.76, 9.56, 13.16, 14.36, 16.36])
        keys.append([0.792379, 0.792379, 0.00872665, 0.198968, 0.198968, 0.198968, 0.0226893, 0.525344, 0.539307])

        names.append("LWristYaw")
        times.append([0, 1.16, 2.36, 4.76, 9.56, 13.16, 14.36])
        keys.append([-0.708604, -0.708604, -0.708604, -0.708604, -0.708604, -0.301942, -0.29147])

        names.append("RElbowRoll")
        times.append([0, 1.16, 4.76, 5.96, 10.76, 11.96, 15.56, 17.56])
        keys.append([1.56207, 1.56207, 0.00872665, 0.00872665, 0.00872665, 1.41372, 1.41372, 1.41372])

        names.append("RElbowYaw")
        times.append([0, 1.16, 4.76, 5.96, 7.16, 10.76, 11.96, 14.36, 15.56, 17.56])
        keys.append([0.118682, 0.118682, 0.118682, 0.118682, -0.722566, 1.80816, 0.13439, 0.150098, 0.849975, 0.849975])

        names.append("RHand")
        times.append([0, 1.16, 7.16, 10.76])
        keys.append([0.02, 0.2, 0.87, 0.87])

        names.append("RShoulderPitch")
        times.append([0, 1.16, 4.76, 5.96, 7.16, 10.76, 15.56, 17.56])
        keys.append([1.32994, 1.32994, 0.127409, -1.09258, 0.0837758, 0.0837758, -0.300197, 1.69821])

        names.append("RShoulderRoll")
        times.append([0, 1.16, 4.76, 5.96, 7.16, 10.76, 11.96, 15.56, 17.56])
        keys.append(
            [-0.792379, -0.792379, -0.792379, -0.0314159, -0.00872665, -0.200713, -0.00872665, -0.539307, -0.539307])

        names.append("RWristYaw")
        times.append([0, 1.16, 4.76, 5.96, 10.76, 15.56, 17.56])
        keys.append([0.708604, -0.708604, 0.708604, 0.708604, 0.708604, 0.29147, 0.29147])

        return names, times, keys
