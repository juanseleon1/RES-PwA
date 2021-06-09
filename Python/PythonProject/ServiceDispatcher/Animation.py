
class Animation:
    def __init__(self, session):
        self.__modules = {
            # ActivityServices-------------------------------------------------------
            "MACARENA": self.dance_macarena,
            "LAMBADA": self.dance_lambada,
            "WALK": self.walk_animation,
            "CELEBRATE": self.celebrate_animation,
            "COMPLETECONV": self.complete_conversation,
            "HAPPINESS": self.happiness_animation,
            "FRAGCONVRIGHT": self.frag_conv_right,
            "FRAGCONVRIGHTOUTFOCUS": self.frag_conv_left_without_focus,
            "FRAGCONVLEFT": self.frag_conv_right,
            "FRAGCONVLEFTOUTFOCUS": self.frag_conv_left_without_focus,
            "QUESTION": self.question_animation,
            "MYSELF": self.myself_animation,
            "EMOTIONRISEARMS": self.emotion_rise_arms,
            "BLOW": self.blow_animation,
            "HOWL": self.howl_animation,
            "YAWN": self.yawn_animation,
            "EAT": self. eat_animation,
            "EXPLORE": self.explore_animation,
            "SHOWWARDROBE": self.show_wardrobe_animation,
            "BIGEYES": self.big_eyes_animation,
            "BUILD": self.build_animation,
            "TOCTOC": self.toctoc_animation,
            "LION": self.lion_animation,
            "PENGUIN": self.penguin_animation,
            "FEROCIOUS": self.ferocious_animation,
            "HAPPINESSDANCE": self.happiness_dance_animation,
            "SAD": self.sad_animation
        }

    def getAnimation(self, animation):
        return self.__modules.get( animation )

    def dance_lambada(self, factor=1):
        pass

    def sad_animation(self, factor=1):
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([-0.216421, 0.331613, 0.331613, -0.216421])

        names.append("HeadYaw")
        times.append([1, 4.96])
        keys.append([0, 0])

        names.append("HipPitch")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([-0.0261799, -0.162316, -0.162316, -0.0261799])

        names.append("HipRoll")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([0, 0, 0, 0])

        names.append("KneePitch")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([0, 0, 0, 0])

        names.append("LElbowRoll")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([-0.514872, -1.46259, -1.46259, -0.514872])

        names.append("LElbowYaw")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([-1.22173, -1.08734, -1.08734, -1.22173])

        names.append("LHand")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([0.59, 0.9, 0.9, 0.59])

        names.append("LShoulderPitch")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([1.5708, 0.279253, 0.279253, 1.5708])

        names.append("LShoulderRoll")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([0.118682, 0.0925025, 0.0925025, 0.118682])

        names.append("LWristYaw")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([-0.0296706, -1.33518, -1.33518, -0.0296706])

        names.append("RElbowRoll")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([0.514872, 1.46259, 1.46259, 0.514872])

        names.append("RElbowYaw")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([1.22173, 1.08734, 1.08734, 1.22173])

        names.append("RHand")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([0.59, 0.9, 0.9, 0.59])

        names.append("RShoulderPitch")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([1.5708, 0.279253, 0.279253, 1.5708])

        names.append("RShoulderRoll")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([-0.118682, -0.0925025, -0.0925025, -0.118682])

        names.append("RWristYaw")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([0.0296706, 1.33518, 1.33518, 0.0296706])

        return names, times, keys

    def happiness_animation(self, factor=1):
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([1, 2.96, 4.96])
        keys.append([-0.207694, -0.156742, -0.207694])

        names.append("HeadYaw")
        times.append([1, 4.96])
        keys.append([0, 0])

        names.append("HipPitch")
        times.append([1, 2.96, 4.96])
        keys.append([-0.0261799, 0.0174533, -0.0261799])

        names.append("HipRoll")
        times.append([1, 2.96, 4.96])
        keys.append([0, 0, 0])

        names.append("KneePitch")
        times.append([1, 2.96, 4.96])
        keys.append([0, 0, 0])

        names.append("LElbowRoll")
        times.append([1, 1.96, 4.96])
        keys.append([-0.525344, -0.705113, -0.525344])

        names.append("LElbowYaw")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([-1.22522, -0.537561, -0.677188, -1.22522])

        names.append("LHand")
        times.append([1, 1.96, 4.96])
        keys.append([0.59, 0.8, 0.59])

        names.append("LShoulderPitch")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([1.57778, 0.232129, -1.15715, 1.57778])

        names.append("LShoulderRoll")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([0.118682, 0.548033, 0.722566, 0.118682])

        names.append("LWristYaw")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([-0.0349066, 0.279253, 0.349066, -0.0349066])

        names.append("RElbowRoll")
        times.append([1, 1.96, 4.96])
        keys.append([0.525344, 0.705113, 0.525344])

        names.append("RElbowYaw")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([1.22522, 0.537561, 0.677188, 1.22522])

        names.append("RHand")
        times.append([1, 1.96, 4.96])
        keys.append([0.59, 0.8, 0.59])

        names.append("RShoulderPitch")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([1.57778, 0.232129, -1.15715, 1.57778])

        names.append("RShoulderRoll")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([-0.118682, -0.548033, -0.722566, -0.118682])

        names.append("RWristYaw")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([0.0349066, -0.279253, -0.349066, 0.0349066])

        return names, times, keys


    def ferocious_animation(self, factor=1):
        # Choregraphe simplified export in Python.
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([-0.221657, -0.0676186, -0.0676186, -0.221657])

        names.append("HeadYaw")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([0, -0.415388, 0.424115, 0])

        names.append("LElbowRoll")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([-0.520108, -1.2706, -1.2706, -0.520108])

        names.append("LElbowYaw")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([-1.22173, -1.48353, -1.48353, -1.22173])

        names.append("LHand")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([0.59, 0.97, 0.97, 0.59])

        names.append("LShoulderPitch")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([1.5708, 0.226893, 0.226893, 1.5708])

        names.append("LShoulderRoll")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([0.120428, 0.801106, 0.801106, 0.120428])

        names.append("LWristYaw")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([-0.0261799, 0.733038, 0.733038, -0.0261799])

        names.append("RElbowRoll")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([0.520108, 1.2706, 1.2706, 0.520108])

        names.append("RElbowYaw")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([1.22173, 1.48353, 1.48353, 1.22173])

        names.append("RHand")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([0.59, 0.97, 0.97, 0.59])

        names.append("RShoulderPitch")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([1.5708, 0.226893, 0.226893, 1.5708])

        names.append("RShoulderRoll")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([-0.120428, -0.801106, -0.801106, -0.120428])

        names.append("RWristYaw")
        times.append([1, 1.96, 2.96, 4.96])
        keys.append([0.0261799, -0.733038, -0.733038, 0.0261799])

        return names, times, keys

    def penguin_animation(self, factor=1):
        # Choregraphe simplified export in Python.
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([0.9, 1.96, 3.96, 5.96, 7.96, 9.96])
        keys.append([-0.207694, -0.0907571, 0.0872665, -0.0907571, 0.0872665, -0.207694])

        names.append("HeadYaw")
        times.append([0.9, 1.96, 3.96, 5.96, 7.96, 9.96])
        keys.append([0, 0.244346, -0.244346, 0.244346, -0.244346, 0])

        names.append("HipPitch")
        times.append([0.9, 3.96, 7.96, 9.96])
        keys.append([-0.0191986, -0.0226893, -0.0226893, -0.0191986])

        names.append("HipRoll")
        times.append([0.9, 1.96, 3.96, 5.96, 7.96, 9.96])
        keys.append([0, 0.244346, -0.23911, 0.244346, -0.23911, 0])

        names.append("KneePitch")
        times.append([0.9, 1.96, 3.96, 5.96, 7.96, 9.96])
        keys.append([0, 0, 0, 0, 0, 0])

        names.append("LElbowRoll")
        times.append([0.9, 9.96])
        keys.append([-0.509636, -0.509636])

        names.append("LElbowYaw")
        times.append([0.9, 9.96])
        keys.append([-1.20777, -1.20777])

        names.append("LHand")
        times.append([0.9, 9.96])
        keys.append([0.6, 0.6])

        names.append("LShoulderPitch")
        times.append([0.9, 9.96])
        keys.append([1.57778, 1.57778])

        names.append("LShoulderRoll")
        times.append([0.9, 9.96])
        keys.append([0.118682, 0.118682])

        names.append("LWristYaw")
        times.append([0.9, 9.96])
        keys.append([-0.0314159, -0.0314159])

        names.append("RElbowRoll")
        times.append([0.9, 9.96])
        keys.append([0.509636, 0.509636])

        names.append("RElbowYaw")
        times.append([0.9, 9.96])
        keys.append([1.20777, 1.20777])

        names.append("RHand")
        times.append([0.9, 9.96])
        keys.append([0.6, 0.6])

        names.append("RShoulderPitch")
        times.append([0.9, 9.96])
        keys.append([1.57778, 1.57778])

        names.append("RShoulderRoll")
        times.append([0.9, 9.96])
        keys.append([-0.118682, -0.118682])

        names.append("RWristYaw")
        times.append([0.9, 9.96])
        keys.append([0.0314159, 0.0314159])

        return names, times, keys

    def lion_animation(self, factor=1):
        # Choregraphe simplified export in Python.
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([0.9, 1.96, 2.96, 4.96, 6.96, 7.96, 9.96])
        keys.append([-0.219911, -0.115192, 0.0698132, -0.207694, 0.218166, 0.20944, -0.219911])

        names.append("HeadYaw")
        times.append([1.96, 4.96, 5.96, 6.96, 7.96, 9.96])
        keys.append([0, 0.541052, -0.541052, 0.541052, -0.541052, -0.00174533])

        names.append("LElbowRoll")
        times.append([0.9, 1.96, 2.96, 3.96, 4.96, 5.96, 6.96, 7.96, 9.96])
        keys.append([-0.532325, -1.26536, -1.09781, -1.26362, -1.24791, -1.26536, -1.25489, -1.24791, -0.532325])

        names.append("LElbowYaw")
        times.append([0.9, 1.96, 2.96, 3.96, 4.96, 5.96, 6.96, 7.96, 9.96])
        keys.append([-1.24093, -1.48528, -1.47829, -1.48702, -1.49051, -1.4748, -1.48178, -1.48178, -1.24093])

        names.append("LHand")
        times.append([0.9, 1.96, 2.96, 3.96, 4.96, 5.96, 7.96, 9.96])
        keys.append([0.6, 0.98, 0.07, 0.98, 0.98, 0.98, 0.98, 0.6])

        names.append("LShoulderPitch")
        times.append([0.9, 1.96, 2.96, 3.96, 4.96, 5.96, 6.96, 7.96, 9.96])
        keys.append([1.56032, 0.233874, 0.226893, 0.218166, 0.233874, 0.219911, 0.219911, 0.226893, 1.56032])

        names.append("LShoulderRoll")
        times.append([0.9, 1.96, 2.96, 3.96, 4.96, 5.96, 6.96, 7.96, 9.96])
        keys.append([0.146608, 0.722566, 0.240855, 0.703368, 0.701622, 0.71733, 0.715585, 0.715585, 0.146608])

        names.append("LWristYaw")
        times.append([0.9, 1.96, 2.96, 3.96, 4.96, 5.96, 6.96, 7.96, 9.96])
        keys.append([-0.020944, 0.733038, 0.741765, 0.733038, 0.745256, 0.733038, 0.734784, 0.733038, -0.020944])

        names.append("RElbowRoll")
        times.append([0.9, 1.96, 2.96, 3.96, 4.96, 5.96, 6.96, 7.96, 9.96])
        keys.append([0.532325, 1.26536, 1.09781, 1.26362, 1.24791, 1.26536, 1.25489, 1.24791, 0.532325])

        names.append("RElbowYaw")
        times.append([0.9, 1.96, 2.96, 3.96, 4.96, 5.96, 6.96, 7.96, 9.96])
        keys.append([1.24093, 1.48528, 1.47829, 1.48702, 1.49051, 1.4748, 1.48178, 1.48178, 1.24093])

        names.append("RHand")
        times.append([0.9, 1.96, 2.96, 3.96, 4.96, 5.96, 7.96, 9.96])
        keys.append([0.6, 0.98, 0.07, 0.98, 0.98, 0.98, 0.98, 0.6])

        names.append("RShoulderPitch")
        times.append([0.9, 1.96, 2.96, 3.96, 4.96, 5.96, 6.96, 7.96, 9.96])
        keys.append([1.56032, 0.233874, 0.226893, 0.218166, 0.233874, 0.219911, 0.219911, 0.226893, 1.56032])

        names.append("RShoulderRoll")
        times.append([0.9, 1.96, 2.96, 3.96, 4.96, 5.96, 6.96, 7.96, 9.96])
        keys.append([-0.146608, -0.722566, -0.240855, -0.703368, -0.701622, -0.71733, -0.715585, -0.715585, -0.146608])

        names.append("RWristYaw")
        times.append([0.9, 1.96, 2.96, 3.96, 4.96, 5.96, 6.96, 7.96, 9.96])
        keys.append([0.020944, -0.733038, -0.741765, -0.733038, -0.745256, -0.733038, -0.734784, -0.733038, 0.020944])

        return names, times, keys

    def toctoc_animation(self, factor=1):
        # Choregraphe simplified export in Python.
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([0.9, 2.96, 5.96])
        keys.append([-0.216421, 0, -0.216421])

        names.append("HeadYaw")
        times.append([0.9, 2.96, 5.96])
        keys.append([0, 0.0349066, 0])

        names.append("LElbowRoll")
        times.append([0.9, 1.96, 2.96, 3.96, 5.96])
        keys.append([-0.525344, -1.45735, -0.933751, -1.45735, -0.525344])

        names.append("LElbowYaw")
        times.append([0.9, 1.96, 3.96, 5.96])
        keys.append([-1.22522, -1.3247, -1.3247, -1.22522])

        names.append("LHand")
        times.append([1.96, 3.96])
        keys.append([0.07, 0.07])

        names.append("LShoulderPitch")
        times.append([0.9, 1.96, 3.96, 5.96])
        keys.append([1.57778, 0.0418879, 0.0418879, 1.57778])

        names.append("LShoulderRoll")
        times.append([0.9, 1.96, 3.96, 5.96])
        keys.append([0.113446, 0.0680678, 0.0680678, 0.113446])

        names.append("LWristYaw")
        times.append([0.9, 1.96, 3.96, 5.96])
        keys.append([-0.0349066, 1.36136, 1.36136, -0.0349066])

        names.append("RElbowRoll")
        times.append([0.9, 5.96])
        keys.append([0.525344, 0.525344])

        names.append("RElbowYaw")
        times.append([0.9, 5.96])
        keys.append([1.22522, 1.22522])

        names.append("RShoulderPitch")
        times.append([0.9, 5.96])
        keys.append([1.57778, 1.57778])

        names.append("RShoulderRoll")
        times.append([0.9, 5.96])
        keys.append([-0.113446, -0.113446])

        names.append("RWristYaw")
        times.append([0.9, 5.96])
        keys.append([0.0349066, 0.0349066])

        return names, times, keys

    def build_animation(self, factor=1):
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([0.9, 1.96, 2.96, 3.96, 4.96, 6.96])
        keys.append([-0.207694, 0.16057, 0.368264, 0.16057, 0.368264, -0.207694])

        names.append("HeadYaw")
        times.append([0.9, 1.96, 2.96, 3.96, 4.96, 6.96])
        keys.append([0, 0.127409, 0.115192, 0.127409, 0.115192, 0])

        names.append("LElbowRoll")
        times.append([0.9, 1.96, 3.96, 6.96])
        keys.append([-0.532325, -0.932006, -0.932006, -0.532325])

        names.append("LElbowYaw")
        times.append([0.9, 1.96, 3.96, 6.96])
        keys.append([-1.23046, -0.853466, -0.853466, -1.23046])

        names.append("LHand")
        times.append([0.9, 1.96, 3.96, 6.96])
        keys.append([0.59, 0.1, 0.1, 0.59])

        names.append("LShoulderPitch")
        times.append([0.9, 1.96, 3.96, 6.96])
        keys.append([1.55509, 0.916298, 0.916298, 1.55509])

        names.append("LShoulderRoll")
        times.append([0.9, 1.96, 3.96, 6.96])
        keys.append([0.141372, 0.10821, 0.10821, 0.141372])

        names.append("LWristYaw")
        times.append([0.9, 1.96, 3.96, 6.96])
        keys.append([-0.0418879, -0.195477, -0.195477, -0.0418879])

        names.append("RElbowRoll")
        times.append([0.9, 1.96, 3.96, 6.96])
        keys.append([0.532325, 1.43641, 1.43641, 0.532325])

        names.append("RElbowYaw")
        times.append([0.9, 1.96, 2.96, 3.96, 4.96, 6.96])
        keys.append([1.23046, 1.16413, 0.393412, 1.16413, 0.393413, 1.23046])

        names.append("RHand")
        times.append([0.9, 1.96, 3.96, 6.96])
        keys.append([0.59, 0.08, 0.08, 0.59])

        names.append("RShoulderPitch")
        times.append([0.9, 1.96, 3.96, 6.96])
        keys.append([1.55509, 0.698132, 0.698132, 1.55509])

        names.append("RShoulderRoll")
        times.append([0.9, 1.96, 2.96, 3.96, 4.96, 6.96])
        keys.append([-0.141372, -0.0855211, -0.0463179, -0.0855211, -0.0463179, -0.141372])

        names.append("RWristYaw")
        times.append([0.9, 1.96, 3.96, 6.96])
        keys.append([0.0418879, 1.06465, 1.06465, 0.0418879])

        return names, times, keys

    def big_eyes_animation(self, factor=1):
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([0.9, 6.96])
        keys.append([-0.207694, -0.207694])

        names.append("HeadYaw")
        times.append([0.9, 6.96])
        keys.append([-0.00698132, -0.00698132])

        names.append("LElbowRoll")
        times.append([0.9, 1.96, 6.96])
        keys.append([-0.511381, -1.44339, -0.511381])

        names.append("LElbowYaw")
        times.append([0.9, 1.96, 2.96, 6.96])
        keys.append([-1.20777, -0.938987, -0.954245, -1.20777])

        names.append("LHand")
        times.append([0.9, 1.96, 2.96, 3.96, 4.96, 6.96])
        keys.append([0.59, 0.06, 0.9, 0.06, 0.9, 0.59])

        names.append("LShoulderPitch")
        times.append([0.9, 1.96, 2.96, 6.96])
        keys.append([1.57603, -0.263545, -0.333358, 1.57603])

        names.append("LShoulderRoll")
        times.append([0.9, 1.96, 6.96])
        keys.append([0.118682, 0.113446, 0.118682])

        names.append("LWristYaw")
        times.append([0.9, 1.96, 6.96])
        keys.append([-0.0314159, 1.52367, -0.0314159])

        names.append("RElbowRoll")
        times.append([0.9, 1.96, 6.96])
        keys.append([0.511381, 1.44339, 0.511381])

        names.append("RElbowYaw")
        times.append([0.9, 1.96, 2.96, 6.96])
        keys.append([1.20777, 0.938987, 0.954245, 1.20777])

        names.append("RHand")
        times.append([0.9, 1.96, 2.96, 3.96, 4.96, 6.96])
        keys.append([0.59, 0.06, 0.9, 0.06, 0.9, 0.59])

        names.append("RShoulderPitch")
        times.append([0.9, 1.96, 2.96, 6.96])
        keys.append([1.57603, -0.263545, -0.333358, 1.57603])

        names.append("RShoulderRoll")
        times.append([0.9, 1.96, 6.96])
        keys.append([-0.118682, -0.113446, -0.118682])

        names.append("RWristYaw")
        times.append([0.9, 1.96, 6.96])
        keys.append([0.0314159, -1.52367, 0.0314159])

        return names, times, keys

    def show_wardrobe_animation(self, factor=1):
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([0.9, 5.96, 6.96])
        keys.append([-0.207694, 0.418879, -0.211185])

        names.append("HeadYaw")
        times.append([0.9, 5.96, 6.96])
        keys.append([0, -0.00523599, 0])

        names.append("LElbowRoll")
        times.append([0.9, 1.96, 3.96, 5.96, 6.96])
        keys.append([-0.53058, -1.43815, -1.4556, -0.356047, -0.53058])

        names.append("LElbowYaw")
        times.append([0.9, 1.96, 3.96, 5.96, 6.96])
        keys.append([-1.23569, -1.09432, -1.14668, -1.2514, -1.23569])

        names.append("LHand")
        times.append([0.9, 1.96, 6.96])
        keys.append([0.69, 0.59, 0.69])

        names.append("LShoulderPitch")
        times.append([0.9, 1.96, 3.96, 5.96, 6.96])
        keys.append([1.55334, -0.663225, 0.593412, 1.72788, 1.55334])

        names.append("LShoulderRoll")
        times.append([0.9, 1.96, 3.96, 5.96, 6.96])
        keys.append([0.144862, 0.319395, 0.109956, 0.249582, 0.144862])

        names.append("LWristYaw")
        times.append([0.9, 1.96, 3.96, 5.96, 6.96])
        keys.append([-0.0279253, -1.77325, -1.47655, 0.656244, -0.0279253])

        names.append("RElbowRoll")
        times.append([0.9, 1.96, 3.96, 5.96, 6.96])
        keys.append([0.53058, 1.43815, 1.4556, 0.356047, 0.53058])

        names.append("RElbowYaw")
        times.append([0.9, 1.96, 3.96, 5.96, 6.96])
        keys.append([1.23569, 1.09432, 1.14668, 1.2514, 1.23569])

        names.append("RHand")
        times.append([0.9, 1.96, 6.96])
        keys.append([0.69, 0.59, 0.69])

        names.append("RShoulderPitch")
        times.append([0.9, 1.96, 3.96, 5.96, 6.96])
        keys.append([1.55334, -0.663225, 0.593412, 1.72788, 1.55334])

        names.append("RShoulderRoll")
        times.append([0.9, 1.96, 3.96, 5.96, 6.96])
        keys.append([-0.144862, -0.319395, -0.109956, -0.249582, -0.144862])

        names.append("RWristYaw")
        times.append([0.9, 1.96, 3.96, 5.96, 6.96])
        keys.append([0.0279253, 1.77325, 1.47655, -0.656244, 0.0279253])

        return names, times, keys

    def explore_animation(self, factor=1):
        # Choregraphe simplified export in Python.
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([0.9, 9.92])
        keys.append([-0.225147, -0.225147])

        names.append("HeadYaw")
        times.append([0.9, 9.92])
        keys.append([0, 0])

        names.append("HipPitch")
        times.append([4.96, 5.96, 6.96])
        keys.append([0.235619, -0.244346, 0])

        names.append("HipRoll")
        times.append([4.96, 6.96, 7.96, 9.92])
        keys.append([0, 0.174533, -0.174533, 0])

        names.append("KneePitch")
        times.append([4.96, 5.96, 6.96])
        keys.append([0, 0, 0])

        names.append("LElbowRoll")
        times.append([0.9, 1.96, 3.96, 9.92])
        keys.append([-0.513127, -1.50796, -1.35088, -0.513127])

        names.append("LElbowYaw")
        times.append([0.9, 1.96, 9.92])
        keys.append([-1.22522, -1.05069, -1.22522])

        names.append("LHand")
        times.append([0.9, 9.92])
        keys.append([0.59, 0.59])

        names.append("LShoulderPitch")
        times.append([0.9, 1.96, 3.96, 9.92])
        keys.append([1.57429, 0.649262, -0.214866, 1.57429])

        names.append("LShoulderRoll")
        times.append([0.9, 1.96, 9.92])
        keys.append([0.115192, 0.00872665, 0.115192])

        names.append("LWristYaw")
        times.append([0.9, 9.92])
        keys.append([-0.0314159, -0.0314159])

        names.append("RElbowRoll")
        times.append([0.9, 1.96, 3.96, 9.92])
        keys.append([0.513127, 1.50796, 1.35088, 0.513127])

        names.append("RElbowYaw")
        times.append([0.9, 1.96, 9.92])
        keys.append([1.22522, 1.05069, 1.22522])

        names.append("RHand")
        times.append([0.9, 9.92])
        keys.append([0.59, 0.59])

        names.append("RShoulderPitch")
        times.append([0.9, 1.96, 3.96, 9.92])
        keys.append([1.57429, 0.649262, -0.214866, 1.57429])

        names.append("RShoulderRoll")
        times.append([0.9, 1.96, 9.92])
        keys.append([-0.115192, -0.00872665, -0.115192])

        names.append("RWristYaw")
        times.append([0.9, 9.92])
        keys.append([0.0314159, 0.0314159])

        return names, times, keys

    def eat_animation(self, factor = 1):
        # Choregraphe simplified export in Python.
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([1.96, 4.92])
        keys.append([-0.106465, 0.010472])

        names.append("HeadYaw")
        times.append([1.96])
        keys.append([0.340339])

        names.append("LElbowRoll")
        times.append([1.96, 3.96, 5.96, 7.96, 9.96, 10.96, 11.96])
        keys.append([-1.4757, -1.43117, -0.980875, -0.509636, -0.980875, -1.29503, -1.43466])

        names.append("LElbowYaw")
        times.append([1.96, 5.96, 7.96, 9.96, 10.96, 11.96])
        keys.append([-1.1205, -1.18333, -1.21824, -1.18333, -1.14843, -1.13097])

        names.append("LHand")
        times.append([1.96, 3.96, 5.96])
        keys.append([0.7, 0.08, 0.06])

        names.append("LShoulderPitch")
        times.append([1.96, 3.96, 5.96, 7.96, 9.96, 10.96, 11.96])
        keys.append([0.363028, 0.493928, 1.03498, 1.57603, 1.03498, 0.668461, 0.493928])

        names.append("LShoulderRoll")
        times.append([1.96, 5.96])
        keys.append([0.111701, 0.115192])

        names.append("LWristYaw")
        times.append([1.96])
        keys.append([-1.31598])

        names.append("RElbowRoll")
        times.append([0.9, 13.96])
        keys.append([0.520108, 0.520108])

        names.append("RElbowYaw")
        times.append([0.9, 13.96])
        keys.append([1.21649, 1.21649])

        names.append("RShoulderPitch")
        times.append([0.9, 13.96])
        keys.append([1.57254, 1.57254])

        names.append("RShoulderRoll")
        times.append([0.9, 13.96])
        keys.append([-0.111701, -0.111701])

        return names, times, keys

    def yawn_animation(self, factor = 1):
        # Choregraphe simplified export in Python.
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([0.9, 3.96, 7.96, 11.96, 13.96])
        keys.append([-0.219911, 0.0331613, 0.0331613, 0.0331613, -0.219911])

        names.append("HeadYaw")
        times.append([0.9, 13.96])
        keys.append([-0.00698132, -0.00698132])

        names.append("LElbowRoll")
        times.append([0.9, 1.96, 3.96, 5.96, 7.96, 9.96, 11.96, 13.96])
        keys.append([-0.518363, -0.855211, -1.51844, -0.844739, -1.51844, -0.844739, -1.51844, -0.518363])

        names.append("LElbowYaw")
        times.append([0.9, 1.96, 3.96, 5.96, 7.96, 9.96, 11.96, 13.96])
        keys.append([-1.22173, -0.71384, -0.731293, -0.719076, -0.731293, -0.719076, -0.731293, -1.22173])

        names.append("LHand")
        times.append([1.96])
        keys.append([0.9])

        names.append("LShoulderPitch")
        times.append([0.9, 1.96, 3.96, 5.96, 7.96, 9.96, 11.96, 13.96])
        keys.append([1.5708, 0.890118, 0.0872665, 0.0785398, 0.0872665, 0.0785398, 0.0872665, 1.5708])

        names.append("LShoulderRoll")
        times.append([0.9, 1.96, 3.96, 7.96, 11.96, 13.96])
        keys.append([0.115192, 0.109956, 0.116937, 0.116937, 0.116937, 0.115192])

        names.append("LWristYaw")
        times.append([0.9, 1.96, 13.96])
        keys.append([-0.0296706, -1.82387, -0.0296706])

        names.append("RElbowRoll")
        times.append([0.9, 13.96])
        keys.append([0.518363, 0.518363])

        names.append("RElbowYaw")
        times.append([0.9, 13.96])
        keys.append([1.22173, 1.22173])

        names.append("RShoulderPitch")
        times.append([0.9, 13.96])
        keys.append([1.5708, 1.5708])

        names.append("RShoulderRoll")
        times.append([0.9, 13.96])
        keys.append([-0.115192, -0.115192])

        names.append("RWristYaw")
        times.append([0.9, 13.96])
        keys.append([0.0296706, 0.0296706])

        return names, times, keys


    def blow_animation(self, factor = 1):
        # Choregraphe simplified export in Python.
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([0.9, 1.96, 3.96, 5.96])
        keys.append([0, 0.404916, -0.450295, 0])

        names.append("HeadYaw")
        times.append([0.9, 1.96, 5.96])
        keys.append([0, 0, 0])

        names.append("LElbowRoll")
        times.append([0.9, 1.96, 3.96, 5.96])
        keys.append([-0.518363, -1.54985, -1.35961, -0.518363])

        names.append("LElbowYaw")
        times.append([0.9, 1.96, 3.96, 5.96])
        keys.append([-0.0314159, -1.04371, -1.05592, -0.0314159])

        names.append("LHand")
        times.append([0.9, 3.96, 5.96])
        keys.append([0.59, 0.56, 0.59])

        names.append("LShoulderPitch")
        times.append([0.9, 1.96, 3.96, 5.96])
        keys.append([1.58825, 0.504287, -0.226893, 1.58825])

        names.append("LShoulderRoll")
        times.append([0.9, 1.96, 3.96, 5.96])
        keys.append([0.113446, 0.00872665, 0.00872665, 0.113446])

        names.append("LWristYaw")
        times.append([0.9, 3.96, 5.96])
        keys.append([-0.0314159, -0.0279253, -0.0314159])

        names.append("RElbowRoll")
        times.append([0.9, 1.96, 3.96, 5.96])
        keys.append([0.518363, 1.54985, 1.35961, 0.518363])

        names.append("RElbowYaw")
        times.append([0.9, 1.96, 3.96, 5.96])
        keys.append([0.0314159, 1.04371, 1.05592, 0.0314159])

        names.append("RHand")
        times.append([0.9, 3.96, 5.96])
        keys.append([0.59, 0.56, 0.59])

        names.append("RShoulderPitch")
        times.append([0.9, 1.96, 3.96, 5.96])
        keys.append([1.58825, 0.504287, -0.226893, 1.58825])

        names.append("RShoulderRoll")
        times.append([0.9, 1.96, 3.96, 5.96])
        keys.append([-0.113446, -0.00872665, -0.00872665, -0.113446])

        names.append("RWristYaw")
        times.append([0.9, 3.96, 5.96])
        keys.append([0.0314159, 0.0279253, 0.0314159])

        return names, times, keys

    def howl_animation(self, factor = 1):
        # Choregraphe simplified export in Python.
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([0.9, 1.96, 3.96])
        keys.append([0, 0.404916, -0.450295])

        names.append("HeadYaw")
        times.append([0.9, 1.96])
        keys.append([0, 0])

        names.append("LElbowRoll")
        times.append([0.9, 1.96, 3.96])
        keys.append([-0.518363, -1.54985, -1.35961])

        names.append("LElbowYaw")
        times.append([0.9, 1.96, 3.96])
        keys.append([-0.0314159, -1.04371, -1.05592])

        names.append("LHand")
        times.append([0.9, 3.96])
        keys.append([0.59, 0.56])

        names.append("LShoulderPitch")
        times.append([0.9, 1.96, 3.96])
        keys.append([1.58825, 0.504286, -0.226893])

        names.append("LShoulderRoll")
        times.append([0.9, 1.96, 3.96])
        keys.append([0.113446, 0.00872665, 0.00872665])

        names.append("LWristYaw")
        times.append([0.9, 3.96])
        keys.append([-0.0314159, -0.0279253])

        names.append("RElbowRoll")
        times.append([0.9, 1.96, 3.96])
        keys.append([0.518363, 1.54985, 1.35961])

        names.append("RElbowYaw")
        times.append([0.9, 1.96, 3.96])
        keys.append([0.0314159, 1.04371, 1.05592])

        names.append("RHand")
        times.append([0.9, 3.96])
        keys.append([0.59, 0.56])

        names.append("RShoulderPitch")
        times.append([0.9, 1.96, 3.96])
        keys.append([1.58825, 0.504286, -0.226893])

        names.append("RShoulderRoll")
        times.append([0.9, 1.96, 3.96])
        keys.append([-0.113446, -0.00872665, -0.00872665])

        names.append("RWristYaw")
        times.append([0.9, 3.96])
        keys.append([0.0314159, 0.0279253])

        return names, times, keys

    def dance_macarena(self, factor=1):
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([1, 1.96, 3.96, 7.96, 9.96, 13.96, 15.96, 19.96, 23.96, 27.96])
        keys.append(
            [-0.211185, -0.211185, -0.211185, 0.123918, 0.123918, 0.445059, 0.123918, 0.123918, 0.123918, 0.123918])

        names.append("HeadYaw")
        times.append([1, 1.96, 3.96, 7.96, 9.96, 13.96, 15.96, 19.96, 21.96, 23.96, 27.96])
        keys.append(
            [-0.00698132, 0.219911, -0.00698132, -0.364774, -0.0174533, -0.00698132, -0.00698132, 0.329867, -0.118682,
             0.127409, -0.0314159])

        names.append("HipPitch")
        times.append([1, 1.96, 13.96])
        keys.append([-0.0357826, -0.0474347, 0])

        names.append("HipRoll")
        times.append([1, 1.96, 13.96])
        keys.append([-0.0041018, -0.00654055, -0.00523599])

        names.append("KneePitch")
        times.append([1, 1.96, 13.96])
        keys.append([-0.0133719, -0.0163339, 0])

        names.append("LElbowRoll")
        times.append([1, 1.96, 3.96, 7.96, 15.96, 21.96, 23.96, 27.96])
        keys.append([-1.56207, -0.00872665, -0.00872665, -0.00872665, -0.00872665, -1.37357, -1.41372, -1.41372])

        names.append("LElbowYaw")
        times.append([1, 1.96, 3.96, 5.96, 7.96, 15.96, 21.96, 23.96, 27.96])
        keys.append([-0.118682, -0.118682, -0.118682, 0.722566, 0.722566, -1.80816, -0.197222, -0.830777, -0.849975])

        names.append("LHand")
        times.append([1, 1.96, 3.96, 5.96])
        keys.append([0.6, 0.2, 0.2, 0.87])

        names.append("LShoulderPitch")
        times.append([1, 1.96, 3.96, 5.96, 7.96, 15.96, 21.96, 23.96, 27.96])
        keys.append([1.32994, 1.7558, -1.22173, 0.0837758, 0.0837758, 0.0837758, 0.0837758, -0.289725, 1.69821])

        names.append("LShoulderRoll")
        times.append([1, 1.96, 3.96, 5.96, 7.96, 15.96, 21.96, 23.96, 27.96])
        keys.append([0.109956, 0.792379, 0.00872665, 0.198968, 0.198968, 0.198968, 0.0226893, 0.525344, 0.539307])

        names.append("LWristYaw")
        times.append([1, 1.96, 3.96, 7.96, 15.96, 21.96, 23.96])
        keys.append([-0.0314159, -0.708604, -0.708604, -0.708604, -0.708604, -0.301942, -0.29147])

        names.append("RElbowRoll")
        times.append([1, 1.96, 7.96, 9.96, 17.96, 19.96, 25.96, 29.96])
        keys.append([1.56207, 1.56207, 0.00872665, 0.00872665, 0.00872665, 1.41372, 1.41372, 1.41372])

        names.append("RElbowYaw")
        times.append([1, 1.96, 7.96, 9.96, 11.96, 17.96, 19.96, 23.96, 25.96, 29.96])
        keys.append([0.118682, 0.118682, 0.118682, 0.118682, -0.722566, 1.80816, 0.13439, 0.150098, 0.849975, 0.849975])

        names.append("RHand")
        times.append([1, 1.96, 11.96, 17.96])
        keys.append([0.02, 0.2, 0.87, 0.87])

        names.append("RShoulderPitch")
        times.append([1, 1.96, 7.96, 9.96, 11.96, 17.96, 25.96, 29.96])
        keys.append([1.32994, 1.32994, 0.127409, -1.09258, 0.0837758, 0.0837758, -0.300197, 1.69821])

        names.append("RShoulderRoll")
        times.append([1, 1.96, 7.96, 9.96, 11.96, 17.96, 19.96, 25.96, 29.96])
        keys.append(
            [-0.792379, -0.792379, -0.792379, -0.0314159, -0.00872665, -0.200713, -0.00872665, -0.539307, -0.539307])

        names.append("RWristYaw")
        times.append([1, 1.96, 7.96, 9.96, 17.96, 25.96, 29.96])
        keys.append([0.708604, -0.708604, 0.708604, 0.708604, 0.708604, 0.29147, 0.29147])

        return names, times, keys

    def walk_animation(self, factor=1):
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([1.96, 3.96])
        keys.append([-0.211185, -0.211185])

        names.append("HeadYaw")
        times.append([1, 1.96, 2.96, 3.96, 4.96, 6.96])
        keys.append([0, -0.390954, -0.00698132, 0.3735, -0.00698132, 0])

        names.append("HipPitch")
        times.append([1, 6.96])
        keys.append([-0.0191986, -0.0191986])

        names.append("KneePitch")
        times.append([1, 6.96])
        keys.append([0, 0])

        names.append("LElbowRoll")
        times.append([1, 1.96, 2.96, 3.96, 4.96, 6.96])
        keys.append([-0.513127, -1.43815, -1.44688, -1.43815, -1.44688, -0.513127])

        names.append("LElbowYaw")
        times.append([1, 1.96, 2.96, 3.96, 4.96, 6.96])
        keys.append([-1.21824, -1.57603, -1.57603, -1.57603, -1.57603, -1.21824])

        names.append("LHand")
        times.append([1, 1.96, 3.96, 6.96])
        keys.append([0.6, 0.07, 0.07, 0.6])

        names.append("LShoulderPitch")
        times.append([1, 1.96, 2.96, 3.96, 4.96, 6.96])
        keys.append([1.57778, 1.16937, 2.08567, 1.16937, 2.08567, 1.57778])

        names.append("LShoulderRoll")
        times.append([1, 1.96, 3.96, 6.96])
        keys.append([0.111701, 0.116937, 0.116937, 0.111701])

        names.append("LWristYaw")
        times.append([1.96, 2.96, 3.96, 4.96])
        keys.append([-0.0314159, -0.0314159, -0.0314159, -0.0314159])

        names.append("RElbowRoll")
        times.append([1, 1.96, 2.96, 3.96, 4.96, 6.96])
        keys.append([0.513127, 1.4399, 1.44513, 1.4399, 1.44513, 0.513127])

        names.append("RElbowYaw")
        times.append([1, 1.96, 3.96, 6.96])
        keys.append([1.21824, 1.5708, 1.5708, 1.21824])

        names.append("RHand")
        times.append([1, 1.96, 3.96, 6.96])
        keys.append([0.6, 0.06, 0.06, 0.6])

        names.append("RShoulderPitch")
        times.append([1, 1.96, 2.96, 3.96, 4.96, 6.96])
        keys.append([1.57778, 2.08567, 1.17461, 2.08567, 1.17461, 1.57778])

        names.append("RShoulderRoll")
        times.append([1, 1.96, 2.96, 3.96, 4.96, 6.96])
        keys.append([-0.111701, -0.115192, -0.115192, -0.115192, -0.115192, -0.111701])

        names.append("RWristYaw")
        times.append([1.96, 3.96])
        keys.append([0.0296706, 0.0296706])

        return names, times, keys

    def celebrate_animation(self, factor=1):
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([0.1, 0.96, 1.96, 2.96, 3.96, 4.96, 5.96])
        keys.append([0.326377, -0.15708, 0.445059, -0.15708, 0.436332, -0.162316, -0.162316])

        names.append("HeadYaw")
        times.append([0.1, 0.96, 1.96, 2.96, 3.96, 4.96, 5.96])
        keys.append([0, 0, 0, 0, 0.544543, -0.727802, 0.872665])

        names.append("HipPitch")
        times.append([0.96, 1.96, 2.96, 4.96])
        keys.append([0.0942478, -0.174533, 0.0942478, 0.0942478])

        names.append("KneePitch")
        times.append([4.96])
        keys.append([0])

        names.append("LElbowRoll")
        times.append([0.1, 0.96, 2.96])
        keys.append([-1.55858, -0.00872665, -0.00872665])

        names.append("LElbowYaw")
        times.append([0.1, 0.96, 2.96])
        keys.append([-0.528835, 1.7558, 1.7558])

        names.append("LHand")
        times.append([0.96, 2.96])
        keys.append([0.9, 0.9])

        names.append("LShoulderPitch")
        times.append([0.1, 0.96, 2.96])
        keys.append([0.443314, -1.27409, -1.27409])

        names.append("LShoulderRoll")
        times.append([0.1, 0.96, 2.96])
        keys.append([0.752237, 0.331613, 0.331613])

        names.append("LWristYaw")
        times.append([0.1, 0.96, 2.96])
        keys.append([-0.0314159, -1.80118, -1.80118])

        names.append("RElbowRoll")
        times.append([0.1, 0.96, 2.96])
        keys.append([1.55858, 0.00872665, 0.00872665])

        names.append("RElbowYaw")
        times.append([0.1, 0.96, 2.96])
        keys.append([0.528835, -1.7558, -1.7558])

        names.append("RHand")
        times.append([0.96, 2.96])
        keys.append([0.9, 0.9])

        names.append("RShoulderPitch")
        times.append([0.1, 0.96, 2.96])
        keys.append([0.443314, -1.27409, -1.27409])

        names.append("RShoulderRoll")
        times.append([0.1, 0.96, 2.96])
        keys.append([-0.752237, -0.331613, -0.331613])

        names.append("RWristYaw")
        times.append([0.1, 0.96, 2.96])
        keys.append([0.0314159, 1.80118, 1.80118])

        return names, times, keys

    def complete_conversation(self, factor=1):
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([1.96, 2.96])
        keys.append([0, 0])

        names.append("HeadYaw")
        times.append([2.96])
        keys.append([0])

        names.append("HipPitch")
        times.append([2.96])
        keys.append([-0.0261799])

        names.append("HipRoll")
        times.append([2.96])
        keys.append([-0.00523599])

        names.append("KneePitch")
        times.append([2.96])
        keys.append([0])

        names.append("LElbowRoll")
        times.append([0.96, 1.96, 2.96, 3.96, 4.96])
        keys.append([-1.45386, -0.998328, -1.45037, -1.45386, -0.998328])

        names.append("LElbowYaw")
        times.append([0.96, 1.96, 2.96, 3.96, 4.96])
        keys.append([-1.1205, -1.88496, -1.12574, -1.1205, -1.88496])

        names.append("LHand")
        times.append([2.96, 4.96])
        keys.append([0.59, 0.59])

        names.append("LShoulderPitch")
        times.append([0.96, 1.96, 2.96, 3.96, 4.96])
        keys.append([1.57952, 1.57952, 1.57952, 1.57952, 1.57952])

        names.append("LShoulderRoll")
        times.append([0.1, 0.96, 1.96, 2.96, 3.96, 4.96])
        keys.append([-1.18323, 0.109956, 0.109956, 0.109956, 0.109956, 0.109956])

        names.append("LWristYaw")
        times.append([0.96, 1.96, 2.96, 3.96, 4.96])
        keys.append([-1.05941, -1.05941, -1.05941, -1.05941, -1.05941])

        names.append("RElbowRoll")
        times.append([0.96, 1.96, 2.96, 3.96, 4.96])
        keys.append([1.45386, 1.45386, 0.998328, 1.45386, 0.998328])

        names.append("RElbowYaw")
        times.append([0.96, 1.96, 2.96, 3.96, 4.96])
        keys.append([1.1205, 1.1205, 1.88496, 1.1205, 1.88496])

        names.append("RHand")
        times.append([2.96, 4.96])
        keys.append([0.59, 0.59])

        names.append("RShoulderPitch")
        times.append([0.96, 1.96, 2.96, 3.96, 4.96])
        keys.append([1.57952, 1.57952, 1.57952, 1.57952, 1.57952])

        names.append("RShoulderRoll")
        times.append([0.96, 1.96, 2.96, 3.96, 4.96])
        keys.append([-0.109956, -0.109956, -0.109956, -0.109956, -0.109956])

        names.append("RWristYaw")
        times.append([0.96, 1.96, 2.96, 3.96, 4.96])
        keys.append([1.05941, 1.05941, 1.05941, 1.05941, 1.05941])

        return names, times, keys

    def happiness_dance_animation(self, factor=1):
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append(
            [0.16, 1.32, 2.52, 3.72, 4.92, 6.12, 7.32, 8.52, 9.72, 10.92, 12.12, 13.32, 14.52, 15.72, 16.92, 18.12,
             19.32, 20.52])
        keys.append(
            [0, 0, 0, 0, 0.445059, -0.307178, 0.445059, -0.307178, 0.445059, -0.307178, 0.445059, -0.307178, 0.445059,
             -0.307178, 0.445059, -0.307178, 0, -0.211185])

        names.append("HeadYaw")
        times.append(
            [0.16, 1.32, 2.52, 3.72, 4.92, 6.12, 7.32, 8.52, 9.72, 10.92, 12.12, 13.32, 14.52, 15.72, 16.92, 18.12,
             19.32, 20.52])
        keys.append(
            [0.733038, -0.733038, 0, 0, 0, 0, -0.600393, 0.441568, 0.603884, -0.335103, -0.680678, -0.668461, 0.593412,
             0.413643, 0, 0, 0, -0.00698132])

        names.append("HipPitch")
        times.append(
            [0.16, 1.32, 2.52, 3.72, 4.92, 6.12, 7.32, 8.52, 9.72, 10.92, 12.12, 13.32, 14.52, 15.72, 16.92, 18.12,
             19.32, 20.52])
        keys.append(
            [-0.0261799, -0.0261799, -0.0261799, -0.0261799, -0.0261799, -0.0261799, -0.0261799, -0.0261799, -0.0261799,
             -0.0261799, -0.0261799, -0.0261799, -0.0261799, -0.0261799, -0.0261799, -0.0261799, -0.0261799,
             -0.0261799])

        names.append("HipRoll")
        times.append(
            [0.16, 1.32, 2.52, 3.72, 4.92, 6.12, 7.32, 8.52, 9.72, 10.92, 12.12, 13.32, 14.52, 15.72, 16.92, 18.12,
             19.32, 20.52])
        keys.append(
            [-0.00523599, -0.00523599, -0.00523599, -0.00523599, -0.00523599, -0.00523599, -0.00523599, -0.00523599,
             -0.00523599, -0.00523599, -0.00523599, -0.240855, -0.240855, -0.00523599, 0.240855, 0.240855, -0.00523599,
             -0.00523599])

        names.append("KneePitch")
        times.append(
            [0.16, 1.32, 2.52, 3.72, 4.92, 6.12, 7.32, 8.52, 9.72, 10.92, 12.12, 13.32, 14.52, 15.72, 16.92, 18.12,
             19.32, 20.52])
        keys.append([0, 0, 0, 0, 0, 0, 0.127409, 0.144862, 0, -0.127409, -0.144862, 0, 0, 0, 0, 0, 0, 0])

        names.append("LElbowRoll")
        times.append(
            [0.16, 1.32, 2.52, 3.72, 4.92, 6.12, 7.32, 8.52, 9.72, 10.92, 12.12, 13.32, 14.52, 15.72, 16.92, 18.12,
             19.32, 20.52])
        keys.append([-0.518363, -0.518363, -0.518363, -0.518363, -0.518363, -0.518363, -0.518363, -0.518363, -0.518363,
                     -0.518363, -0.518363, -0.518363, -0.518363, -0.518363, -0.518363, -0.518363, -0.00872665,
                     -0.518363])

        names.append("LElbowYaw")
        times.append(
            [0.16, 1.32, 2.52, 3.72, 4.92, 6.12, 7.32, 8.52, 9.72, 10.92, 12.12, 13.32, 14.52, 15.72, 16.92, 18.12,
             19.32, 20.52])
        keys.append([-1.21824, -1.21824, -1.21824, -1.21824, -1.21824, -1.21824, -1.21824, -1.21824, -1.21824, -1.21824,
                     -1.21824, -1.21824, -1.21824, -1.21824, -1.21824, -1.21824, -1.21824, -1.21824])

        names.append("LHand")
        times.append(
            [0.16, 1.32, 2.52, 3.72, 4.92, 6.12, 7.32, 8.52, 9.72, 10.92, 12.12, 13.32, 14.52, 15.72, 16.92, 18.12,
             19.32, 20.52])
        keys.append(
            [0.59, 0.59, 0.59, 0.59, 0.59, 0.59, 0.59, 0.59, 0.59, 0.59, 0.59, 0.59, 0.59, 0.59, 0.59, 0.59, 0.95,
             0.59])

        names.append("LShoulderPitch")
        times.append(
            [0.16, 1.32, 2.52, 3.72, 4.92, 6.12, 7.32, 8.52, 9.72, 10.92, 12.12, 13.32, 14.52, 15.72, 16.92, 18.12,
             19.32, 20.52])
        keys.append(
            [1.57952, 1.57952, 1.57952, -0.403171, 1.60919, -0.865683, 1.60919, -0.865683, 1.60919, -0.865683, 1.60919,
             -0.865683, 1.60919, -0.865683, 1.60919, -0.865683, -1.26711, 1.57952])

        names.append("LShoulderRoll")
        times.append(
            [0.16, 1.32, 2.52, 3.72, 4.92, 6.12, 7.32, 8.52, 9.72, 10.92, 12.12, 13.32, 14.52, 15.72, 16.92, 18.12,
             19.32, 20.52])
        keys.append([0.300197, 0.300197, 0.300197, 0.300197, 0.300197, 0.300197, 0.300197, 0.300197, 0.300197, 0.300197,
                     0.300197, 0.300197, 0.300197, 0.300197, 0.300197, 0.300197, 0.340339, 0.116937])

        names.append("LWristYaw")
        times.append(
            [0.16, 1.32, 2.52, 3.72, 4.92, 6.12, 7.32, 8.52, 9.72, 10.92, 12.12, 13.32, 14.52, 15.72, 16.92, 18.12,
             19.32, 20.52])
        keys.append(
            [1.028, -1.82387, 1.028, -1.82387, 1.028, -1.82387, 1.028, -1.82387, 1.028, -1.82387, 1.028, -1.82387,
             1.028, -1.82387, 1.028, -1.82387, 0.792379, -0.0314159])

        names.append("RElbowRoll")
        times.append(
            [0.16, 1.32, 2.52, 3.72, 4.92, 6.12, 7.32, 8.52, 9.72, 10.92, 12.12, 13.32, 14.52, 15.72, 16.92, 18.12,
             19.32, 20.52])
        keys.append([0.518363, 0.518363, 0.518363, 0.518363, 0.518363, 0.518363, 0.518363, 0.518363, 0.518363, 0.518363,
                     0.518363, 0.518363, 0.518363, 0.518363, 0.518363, 0.518363, 0.00872665, 0.518363])

        names.append("RElbowYaw")
        times.append(
            [0.16, 1.32, 2.52, 3.72, 4.92, 6.12, 7.32, 8.52, 9.72, 10.92, 12.12, 13.32, 14.52, 15.72, 16.92, 18.12,
             19.32, 20.52])
        keys.append(
            [1.22522, 1.22522, 1.22522, 1.22522, 1.22522, 1.22522, 1.22522, 1.22522, 1.22522, 1.22522, 1.22522, 1.22522,
             1.22522, 1.22522, 1.22522, 1.22522, -1.72089, 1.22522])

        names.append("RHand")
        times.append(
            [0.16, 1.32, 2.52, 3.72, 4.92, 6.12, 7.32, 8.52, 9.72, 10.92, 12.12, 13.32, 14.52, 15.72, 16.92, 18.12,
             19.32, 20.52])
        keys.append(
            [0.59, 0.59, 0.59, 0.59, 0.59, 0.59, 0.59, 0.59, 0.59, 0.59, 0.59, 0.59, 0.586477, 0.59, 0.59, 0.59, 0.95,
             0.59])

        names.append("RShoulderPitch")
        times.append(
            [0.16, 1.32, 2.52, 3.72, 4.92, 6.12, 7.32, 8.52, 9.72, 10.92, 12.12, 13.32, 14.52, 15.72, 16.92, 18.12,
             19.32, 20.52])
        keys.append([1.57952, 1.57952, 1.57952, -0.403171, -0.403171, 1.50098, -0.403171, 1.50098, -0.403171, 1.50098,
                     -0.403171, 1.50098, -0.403171, 1.50098, -0.403171, 1.50098, -1.26711, 1.57952])

        names.append("RShoulderRoll")
        times.append(
            [0.16, 1.32, 2.52, 3.72, 4.92, 6.12, 7.32, 8.52, 9.72, 10.92, 12.12, 13.32, 14.52, 15.72, 16.92, 18.12,
             19.32, 20.52])
        keys.append([-0.115192, -0.115192, -0.115192, -0.115192, -0.115192, -0.115192, -0.115192, -0.115192, -0.115192,
                     -0.115192, -0.115192, -0.115192, -0.115192, -0.115192, -0.115192, -0.115192, -0.340339, -0.115192])

        names.append("RWristYaw")
        times.append(
            [0.16, 1.32, 2.52, 3.72, 4.92, 6.12, 7.32, 8.52, 9.72, 10.92, 12.12, 13.32, 14.52, 15.72, 16.92, 18.12,
             19.32, 20.52])
        keys.append(
            [-1.028, 1.82387, -1.028, 1.82387, -1.028, 1.82387, -1.028, 1.82387, -1.028, 1.82387, -1.028, 1.82387,
             -1.028, 1.82387, -1.028, 1.82387, 1.82387, 0.0279253])

        return names, times, keys

    def frag_conv_right(self, factor=1):
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([0.1, 0.92, 1.96])
        keys.append([-0.10821, 0, -0.193732])

        names.append("HeadYaw")
        times.append([0.92])
        keys.append([0])

        names.append("LElbowRoll")
        times.append([0.1, 0.92])
        keys.append([-0.518363, -1.45386])

        names.append("LElbowYaw")
        times.append([0.1, 0.92, 1.96])
        keys.append([-1.22522, -1.1205, -1.1205])

        names.append("LHand")
        times.append([0.1, 0.92])
        keys.append([0.59, 0.59])

        names.append("LShoulderPitch")
        times.append([0.1, 0.92])
        keys.append([1.57952, 1.57952])

        names.append("LShoulderRoll")
        times.append([0.1, 0.92])
        keys.append([0.115192, 0.109956])

        names.append("LWristYaw")
        times.append([0.1, 0.92])
        keys.append([0.0279253, -1.05941])

        names.append("RElbowRoll")
        times.append([0.1, 0.92, 1.96])
        keys.append([0.518363, 1.45386, 0.998328])

        names.append("RElbowYaw")
        times.append([0.1, 0.92, 1.96])
        keys.append([1.22522, 1.1205, 1.88496])

        names.append("RHand")
        times.append([0.1, 0.92])
        keys.append([0.59, 0.59])

        names.append("RShoulderPitch")
        times.append([0.1, 0.92])
        keys.append([1.57952, 1.57952])

        names.append("RShoulderRoll")
        times.append([0.1, 0.92])
        keys.append([-0.115192, -0.109956])

        names.append("RWristYaw")
        times.append([0.1, 0.92])
        keys.append([-0.0279253, 1.05941])

        return names, times, keys

    def frag_conv_right_without_focus(self, factor=1):
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([0.1, 0.92, 1.92])
        keys.append([-0.10821, 0.0872665, 0.0575959])

        names.append("HeadYaw")
        times.append([0.92, 1.92])
        keys.append([-0.118682, 0.336849])

        names.append("LElbowRoll")
        times.append([0.1, 0.92])
        keys.append([-0.518363, -1.45386])

        names.append("LElbowYaw")
        times.append([0.1, 0.92, 1.92])
        keys.append([-1.22522, -1.1205, -1.1205])

        names.append("LHand")
        times.append([0.1, 0.92])
        keys.append([0.59, 0.59])

        names.append("LShoulderPitch")
        times.append([0.1, 0.92])
        keys.append([1.57952, 1.57952])

        names.append("LShoulderRoll")
        times.append([0.1, 0.92])
        keys.append([0.115192, 0.109956])

        names.append("LWristYaw")
        times.append([0.1, 0.92])
        keys.append([0.0279253, -1.05941])

        names.append("RElbowRoll")
        times.append([0.1, 0.92, 1.92])
        keys.append([0.518363, 1.45386, 0.998328])

        names.append("RElbowYaw")
        times.append([0.1, 0.92, 1.92])
        keys.append([1.22522, 1.1205, 1.88496])

        names.append("RHand")
        times.append([0.1, 0.92])
        keys.append([0.59, 0.59])

        names.append("RShoulderPitch")
        times.append([0.1, 0.92])
        keys.append([1.57952, 1.57952])

        names.append("RShoulderRoll")
        times.append([0.1, 0.92])
        keys.append([-0.115192, -0.109956])

        names.append("RWristYaw")
        times.append([0.1, 0.92])
        keys.append([-0.0279253, 1.05941])

        return names, times, keys

    def frag_conv_left(self, factor=1):
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([0.96])
        keys.append([0])

        names.append("HeadYaw")
        times.append([0.96])
        keys.append([0])

        names.append("LElbowRoll")
        times.append([0.1, 0.96, 1.96])
        keys.append([-0.518363, -1.45386, -1.00182])

        names.append("LElbowYaw")
        times.append([0.1, 0.96, 1.96])
        keys.append([-1.22522, -1.1205, -1.87972])

        names.append("LHand")
        times.append([0.96])
        keys.append([0.59])

        names.append("LShoulderPitch")
        times.append([0.1, 0.96, 1.96])
        keys.append([1.57952, 1.57952, 1.57952])

        names.append("LShoulderRoll")
        times.append([0.1, 0.96, 1.96])
        keys.append([0.115192, 0.109956, 0.109956])

        names.append("LWristYaw")
        times.append([0.1, 0.96, 1.96])
        keys.append([0.0279253, -1.05941, -1.05941])

        names.append("RElbowRoll")
        times.append([0.1, 0.96])
        keys.append([0.518363, 1.45386])

        names.append("RElbowYaw")
        times.append([0.1, 0.96])
        keys.append([1.22522, 1.1205])

        names.append("RHand")
        times.append([0.96])
        keys.append([0.59])

        names.append("RShoulderPitch")
        times.append([0.1, 0.96])
        keys.append([1.57952, 1.57952])

        names.append("RShoulderRoll")
        times.append([0.1, 0.96])
        keys.append([-0.115192, -0.109956])

        names.append("RWristYaw")
        times.append([0.1, 0.96])
        keys.append([-0.0279253, 1.05941])

        return names, times, keys

    def frag_conv_left_without_focus(self, factor=1):
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([0.96, 1.96])
        keys.append([0.0767945, 0.00174533])

        names.append("HeadYaw")
        times.append([0.96, 1.96])
        keys.append([0.0802851, -0.136136])

        names.append("LElbowRoll")
        times.append([0.1, 0.96, 1.96])
        keys.append([-0.518363, -1.45386, -1.00182])

        names.append("LElbowYaw")
        times.append([0.1, 0.96, 1.96])
        keys.append([-1.22522, -1.1205, -1.87972])

        names.append("LHand")
        times.append([0.96])
        keys.append([0.59])

        names.append("LShoulderPitch")
        times.append([0.1, 0.96, 1.96])
        keys.append([1.57952, 1.57952, 1.57952])

        names.append("LShoulderRoll")
        times.append([0.1, 0.96, 1.96])
        keys.append([0.115192, 0.109956, 0.109956])

        names.append("LWristYaw")
        times.append([0.1, 0.96, 1.96])
        keys.append([0.0279253, -1.05941, -1.05941])

        names.append("RElbowRoll")
        times.append([0.1, 0.96])
        keys.append([0.518363, 1.45386])

        names.append("RElbowYaw")
        times.append([0.1, 0.96])
        keys.append([1.22522, 1.1205])

        names.append("RHand")
        times.append([0.96])
        keys.append([0.59])

        names.append("RShoulderPitch")
        times.append([0.1, 0.96])
        keys.append([1.57952, 1.57952])

        names.append("RShoulderRoll")
        times.append([0.1, 0.96])
        keys.append([-0.115192, -0.109956])

        names.append("RWristYaw")
        times.append([0.1, 0.96])
        keys.append([-0.0279253, 1.05941])

        return names, times, keys

    def question_animation(self, factor=1):
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([1, 1.96, 3.16, 4.36, 5.56, 7.16])
        keys.append([-0.223402, -0.143117, -0.10821, -0.143117, -0.0436332, -0.223402])

        names.append("HeadYaw")
        times.append([1, 1.96, 3.16, 4.36, 5.56, 7.16])
        keys.append([-0.0453786, -0.155334, -0.555015, -0.155334, 0.300197, -0.0453786])

        names.append("HipPitch")
        times.append([1, 1.96, 3.16, 4.36, 7.16])
        keys.append([0, 0, 0, 0, 0])

        names.append("HipRoll")
        times.append([1, 1.96, 3.16, 4.36, 5.56, 7.16])
        keys.append([-0.000594313, 0, 0, 0, -0.0296706, -0.000594313])

        names.append("KneePitch")
        times.append([1, 1.96, 3.16, 4.36, 7.16])
        keys.append([0, 0, 0, 0, 0])

        names.append("LElbowRoll")
        times.append([1, 1.96, 3.16, 4.36, 5.56, 7.16])
        keys.append([-0.5044, -1.44688, -1.44688, -1.44688, -1.3697, -0.5044])

        names.append("LElbowYaw")
        times.append([1, 1.96, 3.16, 4.36, 5.56, 7.16])
        keys.append([-1.30202, -0.246091, -0.246091, -0.246091, -0.255843, -1.30202])

        names.append("LHand")
        times.append([1, 1.96, 3.16, 4.36, 5.56, 7.16])
        keys.append([0.59, 0.08, 0.77, 0.08, 0.77, 0.59])

        names.append("LShoulderPitch")
        times.append([1, 1.96, 3.16, 4.36, 5.56, 7.16])
        keys.append([1.57603, -1.26536, -1.26536, -1.26536, -1.25919, 1.57603])

        names.append("LShoulderRoll")
        times.append([1, 1.96, 3.16, 4.36, 5.56, 7.16])
        keys.append([0.123918, 0.645772, 0.645772, 0.645772, 0.727989, 0.123918])

        names.append("LWristYaw")
        times.append([1, 1.96, 3.16, 4.36, 5.56, 7.16])
        keys.append([-0.0401426, -1.45735, -1.45735, -1.45735, -1.44868, -0.0401426])

        names.append("RElbowRoll")
        times.append([1, 1.96, 3.16, 4.36, 5.56, 7.16])
        keys.append([0.518363, 0.518363, 0.518363, 0.518363, 0.518363, 0.518363])

        names.append("RElbowYaw")
        times.append([1, 1.96, 3.16, 4.36, 5.56, 7.16])
        keys.append([1.22522, 1.22522, 1.22522, 1.22522, 1.22522, 1.22522])

        names.append("RHand")
        times.append([1, 1.96, 3.16, 4.36, 5.56, 7.16])
        keys.append([0.59, 0.59, 0.59, 0.59, 0.59, 0.59])

        names.append("RShoulderPitch")
        times.append([1, 1.96, 3.16, 4.36, 5.56, 7.16])
        keys.append([1.57952, 1.57952, 1.57952, 1.57952, 1.57952, 1.57952])

        names.append("RShoulderRoll")
        times.append([1, 1.96, 3.16, 4.36, 5.56, 7.16])
        keys.append([-0.115192, -0.115192, -0.115192, -0.115192, -0.115192, -0.115192])

        names.append("RWristYaw")
        times.append([1, 1.96, 3.16, 4.36, 5.56, 7.16])
        keys.append([0.0279253, 0.0279253, 0.0279253, 0.0279253, 0.0279253, 0.0279253])

        return names, times, keys

    def myself_animation(self, factor=1):
        names = list()
        times = list()
        keys = list()

        names.append("HeadPitch")
        times.append([0.1, 1.16])
        keys.append([-0.211185, 0.193732])

        names.append("HeadYaw")
        times.append([0.1, 1.16])
        keys.append([-0.00698132, -0.00645278])

        names.append("HipPitch")
        times.append([0])
        keys.append([-0.0261799])

        names.append("HipRoll")
        times.append([0])
        keys.append([-0.00523599])

        names.append("KneePitch")
        times.append([0])
        keys.append([0])

        names.append("LElbowRoll")
        times.append([0.1, 1.16])
        keys.append([-0.518363, -1.46084])

        names.append("LElbowYaw")
        times.append([0.1, 1.16])
        keys.append([-1.21824, -1.0472])

        names.append("LHand")
        times.append([0.1, 1.16])
        keys.append([0.59, 0.23])

        names.append("LShoulderPitch")
        times.append([0.1, 1.16])
        keys.append([1.57952, 0.902335])

        names.append("LShoulderRoll")
        times.append([0.1, 1.16])
        keys.append([0.116937, 0.00872665])

        names.append("LWristYaw")
        times.append([0.1, 1.16])
        keys.append([-0.0314159, -0.932006])

        names.append("RElbowRoll")
        times.append([0.1, 1.16])
        keys.append([0.518363, 0.518363])

        names.append("RElbowYaw")
        times.append([0.1, 1.16])
        keys.append([1.22522, 1.22522])

        names.append("RHand")
        times.append([0.1, 1.16])
        keys.append([0.59, 0.02])

        names.append("RShoulderPitch")
        times.append([0.1, 1.16])
        keys.append([1.57952, 1.57952])

        names.append("RShoulderRoll")
        times.append([0.1, 1.16])
        keys.append([-0.115192, -0.115192])

        names.append("RWristYaw")
        times.append([0.1, 1.16])
        keys.append([0.0279253, 0.0279253])

        return names, times, keys

    def emotion_rise_arms(self, factor=1):
        names = list()
        times = list()
        keys = list()

        names.append("LElbowRoll")
        times.append([0.52, 1.8, 3.52])
        keys.append([-0.241403, -0.757473, -0.199155])

        names.append("LElbowYaw")
        times.append([0.52, 1.8, 3.52])
        keys.append([-1.38405, -1.10654, -1.42887])

        names.append("LHand")
        times.append([0.52, 1.8, 3.52])
        keys.append([0.500169, 0.500169, 0.500169])

        names.append("LShoulderPitch")
        times.append([0.52, 1.8, 3.52])
        keys.append([-1.5708, -1.22522, 1.5942])

        names.append("LShoulderRoll")
        times.append([0.52, 1.8, 3.52])
        keys.append([0.165806, 0.546288, 0.0876731])

        names.append("LWristYaw")
        times.append([0.52, 1.8, 3.52])
        keys.append([-0.388582, -0.378515, -0.378515])

        names.append("RElbowRoll")
        times.append([0.52, 1.8, 3.52])
        keys.append([0.225129, 0.677188, 0.256482])

        names.append("RElbowYaw")
        times.append([0.52, 1.8, 3.52])
        keys.append([1.33531, 0.95295, 1.24597])

        names.append("RHand")
        times.append([0.52, 1.8, 3.52])
        keys.append([0.469323, 0.18, 0.424699])

        names.append("RShoulderPitch")
        times.append([0.52, 1.8, 3.52])
        keys.append([-1.54113, -1.45037, 1.72155])

        names.append("RShoulderRoll")
        times.append([0.52, 1.8, 3.52])
        keys.append([-0.0891729, -0.528835, -0.110002])

        names.append("RWristYaw")
        times.append([0.52, 1.8, 3.52])
        keys.append([0.567859, -0.00872665, 0.720498])

        return names, times, keys
