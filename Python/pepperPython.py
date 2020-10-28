from naoqi import ALProxy
import socket
import threading
import json

def handle_client(conn, addr):
    print("Lo logro")
    
    msg_length = conn.recv(HEADER)
    msg_length = msg_length.decode(FORMAT, 'ignore')
    msg_length = safe_str(msg_length)
    #if msg_length:
    y = safe_str(msg_length).split('{')[1]
    print("msg:")
    y = "{" + y
    #print(y)
    jsonObj = json.loads(y)
    msg_length = len(jsonObj)
    msg = conn.recv((msg_length)).decode(FORMAT, 'ignore')
    if jsonObj["methodName"] == "hablar":
        print(jsonObj["methodName"])

def message_manage(key, msg):
        switch_accion = {
            "BEstadoEmocionalPwA": "BEstadoEmocionalPwA",
           1: "editar_pwa",
           2: "registrar_cuidador",
           3: "historial",
           4: "seleccionar_actividades",
           5: "login",
           6: "cambiar_actividad",
           7: "conversacion_empatica",
           8: "presentarse",
           9: "ayuda",
           10: "consultar_actividades",
           11: "saludar",
           12: "despedirse",
           13: "reinicio_actividad",
           14: "cancelar_actividad",
           15: "cancelar_interaccion",
           16: "bailar",
           17: "karaoke",
           18: "bateria",
           19: "cancion",
           20: "falta_informacion",
           21: "notificacion",
           22: "cambiar_dificultad",
           23: "seleccionar_cuento",
           24: "animar",
           25: "mantener_atencion",
           26: "reanudar_actividad",
           27: "reporte",
           28: "pausa_interaccion",
           29: "musicoterapia",
           30: "modificar_historia"
        }
        func = switch_accion.get(key)
        func(msg)

def BEstadoEmocionalPwA(info_human_state):
    if info_human_state == "attention":
        val_attention = getAttention()
    else:
        val_attention = getAttention()
    return val_attention

def getEmotionalReaction():
    #Returns:	The detected reaction.
    return alMood.getEmotionalReaction()

def getAttention():
    #“unengaged”: attentionLevel < 0.6
    #“semiEngaged” : 0.6 <= attentionLevel < 0.9
    #“fullyEngaged”: attentionLevel >= 0.9
    return alMood.attention()
    
# Choregraphe bezier export in Python.
def dance_macarena():
    names = list()
    times = list()
    keys = list()

    names.append("HeadPitch")
    times.append([0, 0.18, 0.38, 0.78, 0.98, 1.38, 1.58, 1.98, 2.38, 2.78])
    keys.append([[-0.211185, [3, -0.00666667, 0], [3, 0.06, 0]], [-0.211185, [3, -0.06, 0], [3, 0.0666667, 0]], [-0.211185, [3, -0.0666667, 0], [3, 0.133333, 0]], [0.123918, [3, -0.133333, 0], [3, 0.0666667, 0]], [0.123918, [3, -0.0666667, 0], [3, 0.133333, 0]], [0.445059, [3, -0.133333, 0], [3, 0.0666667, 0]], [0.123918, [3, -0.0666667, 0], [3, 0.133333, 0]], [0.123918, [3, -0.133333, 0], [3, 0.133333, 0]], [0.123918, [3, -0.133333, 0], [3, 0.133333, 0]], [0.123918, [3, -0.133333, 0], [3, 0, 0]]])

    names.append("HeadYaw")
    times.append([0, 0.18, 0.38, 0.78, 0.98, 1.38, 1.58, 1.98, 2.18, 2.38, 2.78])
    keys.append([[-0.00698132, [3, -0.00666667, 0], [3, 0.06, 0]], [0.219911, [3, -0.06, 0], [3, 0.0666667, 0]], [-0.00698132, [3, -0.0666667, 0.064965], [3, 0.133333, -0.12993]], [-0.364774, [3, -0.133333, 0], [3, 0.0666667, 0]], [-0.0174533, [3, -0.0666667, -0.00523599], [3, 0.133333, 0.010472]], [-0.00698132, [3, -0.133333, 0], [3, 0.0666667, 0]], [-0.00698132, [3, -0.0666667, 0], [3, 0.133333, 0]], [0.329867, [3, -0.133333, 0], [3, 0.0666667, 0]], [-0.118682, [3, -0.0666667, 0], [3, 0.0666667, 0]], [0.127409, [3, -0.0666667, 0], [3, 0.133333, 0]], [-0.0314159, [3, -0.133333, 0], [3, 0, 0]]])

    names.append("HipPitch")
    times.append([0, 0.18, 1.38])
    keys.append([[-0.0357826, [3, -0.00666667, 0], [3, 0.06, 0]], [-0.0474347, [3, -0.06, 0], [3, 0.4, 0]], [0, [3, -0.4, 0], [3, 0, 0]]])

    names.append("HipRoll")
    times.append([0, 0.18, 1.38])
    keys.append([[-0.0041018, [3, -0.00666667, 0], [3, 0.06, 0]], [-0.00654055, [3, -0.06, 0], [3, 0.4, 0]], [-0.00523599, [3, -0.4, 0], [3, 0, 0]]])

    names.append("KneePitch")
    times.append([0, 0.18, 1.38])
    keys.append([[-0.0133719, [3, -0.00666667, 0], [3, 0.06, 0]], [-0.0163339, [3, -0.06, 0], [3, 0.4, 0]], [0, [3, -0.4, 0], [3, 0, 0]]])

    names.append("LElbowRoll")
    times.append([0, 0.18, 0.38, 0.78, 1.58, 2.18, 2.38, 2.78])
    keys.append([[-1.56207, [3, -0.00666667, 0], [3, 0.06, 0]], [-0.00872665, [3, -0.06, 0], [3, 0.0666667, 0]], [-0.00872665, [3, -0.0666667, 0], [3, 0.133333, 0]], [-0.00872665, [3, -0.133333, 0], [3, 0.266667, 0]], [-0.00872665, [3, -0.266667, 0], [3, 0.2, 0]], [-1.37357, [3, -0.2, 0.120428], [3, 0.0666667, -0.0401426]], [-1.41372, [3, -0.0666667, 0], [3, 0.133333, 0]], [-1.41372, [3, -0.133333, 0], [3, 0, 0]]])

    names.append("LElbowYaw")
    times.append([0, 0.18, 0.38, 0.58, 0.78, 1.58, 2.18, 2.38, 2.78])
    keys.append([[-0.118682, [3, -0.00666667, 0], [3, 0.06, 0]], [-0.118682, [3, -0.06, 0], [3, 0.0666667, 0]], [-0.118682, [3, -0.0666667, 0], [3, 0.0666667, 0]], [0.722566, [3, -0.0666667, 0], [3, 0.0666667, 0]], [0.722566, [3, -0.0666667, 0], [3, 0.266667, 0]], [-1.80816, [3, -0.266667, 0], [3, 0.2, 0]], [-0.197222, [3, -0.2, 0], [3, 0.0666667, 0]], [-0.830777, [3, -0.0666667, 0.00959933], [3, 0.133333, -0.0191987]], [-0.849975, [3, -0.133333, 0], [3, 0, 0]]])

    names.append("LHand")
    times.append([0, 0.18, 0.38, 0.58])
    keys.append([[0.02, [3, -0.00666667, 0], [3, 0.06, 0]], [0.2, [3, -0.06, 0], [3, 0.0666667, 0]], [0.2, [3, -0.0666667, 0], [3, 0.0666667, 0]], [0.87, [3, -0.0666667, 0], [3, 0, 0]]])

    names.append("LShoulderPitch")
    times.append([0, 0.18, 0.38, 0.58, 0.78, 1.58, 2.18, 2.38, 2.78])
    keys.append([[1.32994, [3, -0.00666667, 0], [3, 0.06, 0]], [1.7558, [3, -0.06, 0], [3, 0.0666667, 0]], [-1.22173, [3, -0.0666667, 0], [3, 0.0666667, 0]], [0.0837758, [3, -0.0666667, 0], [3, 0.0666667, 0]], [0.0837758, [3, -0.0666667, 0], [3, 0.266667, 0]], [0.0837758, [3, -0.266667, 0], [3, 0.2, 0]], [0.0837758, [3, -0.2, 0], [3, 0.0666667, 0]], [-0.289725, [3, -0.0666667, 0], [3, 0.133333, 0]], [1.69821, [3, -0.133333, 0], [3, 0, 0]]])

    names.append("LShoulderRoll")
    times.append([0, 0.18, 0.38, 0.58, 0.78, 1.58, 2.18, 2.38, 2.78])
    keys.append([[0.792379, [3, -0.00666667, 0], [3, 0.06, 0]], [0.792379, [3, -0.06, 0], [3, 0.0666667, 0]], [0.00872665, [3, -0.0666667, 0], [3, 0.0666667, 0]], [0.198968, [3, -0.0666667, 0], [3, 0.0666667, 0]], [0.198968, [3, -0.0666667, 0], [3, 0.266667, 0]], [0.198968, [3, -0.266667, 0], [3, 0.2, 0]], [0.0226893, [3, -0.2, 0], [3, 0.0666667, 0]], [0.525344, [3, -0.0666667, -0.00698131], [3, 0.133333, 0.0139626]], [0.539307, [3, -0.133333, 0], [3, 0, 0]]])

    names.append("LWristYaw")
    times.append([0, 0.18, 0.38, 0.78, 1.58, 2.18, 2.38])
    keys.append([[-0.708604, [3, -0.00666667, 0], [3, 0.06, 0]], [-0.708604, [3, -0.06, 0], [3, 0.0666667, 0]], [-0.708604, [3, -0.0666667, 0], [3, 0.133333, 0]], [-0.708604, [3, -0.133333, 0], [3, 0.266667, 0]], [-0.708604, [3, -0.266667, 0], [3, 0.2, 0]], [-0.301942, [3, -0.2, -0.0314158], [3, 0.0666667, 0.0104719]], [-0.29147, [3, -0.0666667, 0], [3, 0, 0]]])

    names.append("RElbowRoll")
    times.append([0, 0.18, 0.78, 0.98, 1.78, 1.98, 2.58, 2.98])
    keys.append([[1.56207, [3, -0.00666667, 0], [3, 0.06, 0]], [1.56207, [3, -0.06, 0], [3, 0.2, 0]], [0.00872665, [3, -0.2, 0], [3, 0.0666667, 0]], [0.00872665, [3, -0.0666667, 0], [3, 0.266667, 0]], [0.00872665, [3, -0.266667, 0], [3, 0.0666667, 0]], [1.41372, [3, -0.0666667, 0], [3, 0.2, 0]], [1.41372, [3, -0.2, 0], [3, 0.133333, 0]], [1.41372, [3, -0.133333, 0], [3, 0, 0]]])

    names.append("RElbowYaw")
    times.append([0, 0.18, 0.78, 0.98, 1.18, 1.78, 1.98, 2.38, 2.58, 2.98])
    keys.append([[0.118682, [3, -0.00666667, 0], [3, 0.06, 0]], [0.118682, [3, -0.06, 0], [3, 0.2, 0]], [0.118682, [3, -0.2, 0], [3, 0.0666667, 0]], [0.118682, [3, -0.0666667, 0], [3, 0.0666667, 0]], [-0.722566, [3, -0.0666667, 0], [3, 0.2, 0]], [1.80816, [3, -0.2, 0], [3, 0.0666667, 0]], [0.13439, [3, -0.0666667, 0], [3, 0.133333, 0]], [0.150098, [3, -0.133333, -0.015708], [3, 0.0666667, 0.00785399]], [0.849975, [3, -0.0666667, 0], [3, 0.133333, 0]], [0.849975, [3, -0.133333, 0], [3, 0, 0]]])

    names.append("RHand")
    times.append([0, 0.18, 1.18, 1.78])
    keys.append([[0.02, [3, -0.00666667, 0], [3, 0.06, 0]], [0.2, [3, -0.06, -0.0432203], [3, 0.333333, 0.240113]], [0.87, [3, -0.333333, 0], [3, 0.2, 0]], [0.87, [3, -0.2, 0], [3, 0, 0]]])

    names.append("RShoulderPitch")
    times.append([0, 0.18, 0.78, 0.98, 1.18, 1.78, 2.58, 2.98])
    keys.append([[1.32994, [3, -0.00666667, 0], [3, 0.06, 0]], [1.32994, [3, -0.06, 0], [3, 0.2, 0]], [0.127409, [3, -0.2, 0.605629], [3, 0.0666667, -0.201876]], [-1.09258, [3, -0.0666667, 0], [3, 0.0666667, 0]], [0.0837758, [3, -0.0666667, 0], [3, 0.2, 0]], [0.0837758, [3, -0.2, 0], [3, 0.266667, 0]], [-0.300197, [3, -0.266667, 0], [3, 0.133333, 0]], [1.69821, [3, -0.133333, 0], [3, 0, 0]]])

    names.append("RShoulderRoll")
    times.append([0, 0.18, 0.78, 0.98, 1.18, 1.78, 1.98, 2.58, 2.98])
    keys.append([[-0.792379, [3, -0.00666667, 0], [3, 0.06, 0]], [-0.792379, [3, -0.06, 0], [3, 0.2, 0]], [-0.792379, [3, -0.2, 0], [3, 0.0666667, 0]], [-0.0314159, [3, -0.0666667, -0.0226893], [3, 0.0666667, 0.0226893]], [-0.00872665, [3, -0.0666667, 0], [3, 0.2, 0]], [-0.200713, [3, -0.2, 0], [3, 0.0666667, 0]], [-0.00872665, [3, -0.0666667, 0], [3, 0.2, 0]], [-0.539307, [3, -0.2, 0], [3, 0.133333, 0]], [-0.539307, [3, -0.133333, 0], [3, 0, 0]]])

    names.append("RWristYaw")
    times.append([0, 0.18, 0.78, 0.98, 1.78, 2.58, 2.98])
    keys.append([[0.708604, [3, -0.00666667, 0], [3, 0.06, 0]], [-0.708604, [3, -0.06, 0], [3, 0.2, 0]], [0.708604, [3, -0.2, 0], [3, 0.0666667, 0]], [0.708604, [3, -0.0666667, 0], [3, 0.266667, 0]], [0.708604, [3, -0.266667, 0], [3, 0.266667, 0]], [0.29147, [3, -0.266667, 0], [3, 0.133333, 0]], [0.29147, [3, -0.133333, 0], [3, 0, 0]]])



def run_animation(animation_names, animation_times, animation_keys):
    try:
      # uncomment the following line and modify the IP if you use this script outside Choregraphe.
      # motion = ALProxy("ALMotion", IP, 9559)
      alMotion.angleInterpolationBezier(animation_names, animation_times, animation_keys)
    except BaseException, err:
      print err
      
def run_animation( animation_path, animation_tag):
    alAnimationPlayer.runTag(animation_path, animation_tag)

def go_to_posture( posture, speed):
    alRobotPosture.goToPosture(posture, speed)

def learn_face(person_name):
    #Returns: true if the operation succeeded
    return alFaceDetection.learnFace(person_name)
    
def hablar(texto_hablar):
    alTexToSpeech.say(texto_hablar)
    
def safe_str(obj):
    try: return str(obj)
    except UnicodeEncodeError:
        return obj.encode(FORMAT, 'ignore').decode(FORMAT)
    return ""

def registrar_cuidador(params):
    pass

def historial(params):
    pass
def seleccionar_actividades(params):
    pass
def login(params):
    pass
def cambiar_actividad(params):
    pass
def conversacion_empatica(params):
    estado_emocional = params.get("estado_emocional")
    switch_emocion = {
        "triste": "conversacion_triste",
        "alegre": "conversacion_alegre",
        "ira": "conversacion_ira"
    }
    func = switch_emocion.get(params)
    func()

def conversacion_triste():
    frase_inicial = "Sé que estás triste," #realizar una pausa! IMPORTANTE!!!!!!!!!!!!!!
    switch_conversacion = {
        1: "pero sabes? la tristeza nos puede ayudar a hacer una introspeccion y esto nos permite estar mejor.",
        2: "Aveces la tristeza nos puede hacer sentir mal, te cuento que como el resto de emociones tiene una función que nos permite volver mejores",
        3: "¿Sabías que usualmente necesitamos la ayuda de otras personas cuando estamos tristes?",
        4: "y te sientes mal, pero esta emoción te permite sanar heridas"
    }
    frase_principal = "¿Te gustaría escuchar una canción?"

def conversacion_alegre():
    frase_inicial = "Me gusta verte así,"
    switch_conversacion = {
        1: "La alegría te genera una sonrisa hermosa",
        2: "Las personas se desempeñan mucho mejor cuando están alegres",
        3: "La alegría es parte de la vida y es vital para disfrutar nuestra estadia",
        4: "luces genial, esa alegría que reflejas te hace ver mejor"
    }
    frase_principal = "¿Escuchamos una canción?"

def conversacion_ira(genero):
    frase_inicial = "Veo que estás enfadad"

    if genero =='M':
        frase_inicial = frase_inicial + "o"
    else:
        frase_inicial = frase_inicial + "a"
    
    frase_inicial = frase_inicial + ","

    switch_conversacion = {
        1: "es normal sentir ira, pero no debes dejarla salir de control porque no te permite tomar buenas decisiones",
        2: "aveces la ira te puede hacer sentir mal, sin embargo debes tomar las cosas con serenidad",
        3: "pero sabes? las cosas tienen la importancia que tú les das",
        4: "la ira te indispone y dificulta que pienses con claridad"
    }
    frase_principal = "¿si quieres podemos escuchar una canción para relajarnos?"

def presentarse(params):
    pass
def ayuda(params):
    pass
def consultar_actividades(params):
    pass
def saludar(params):
    pass
def despedirse(params):
    pass
def reinicio_actividad(params):
    pass
def cancelar_actividad(params):
    pass
def cancelar_interaccion(params):
    pass
def bailar(params):
    pass
def karaoke(params):
    pass
def bateria(params):
    pass
def cancion(params):
    pass
def falta_informacion(params):
    pass
def notificacion(params):
    pass
def cambiar_dificultad(params):
    pass
def seleccionar_cuento(params):
    pass
def animar(params):
    pass
def mantener_atencion(params):
    pass
def reanudar_actividad(params):
    pass
def reporte(params):
    pass
def pausa_interaccion(params):
    pass
def musicoterapia(params):
    pass
def modificar_historia(params):
    pass

#Declare the NaoqiCore variables --------------------------------------------------------------------
alMood = ALProxy("ALMood")
alTexToSpeech = ALProxy("ALTextToSpeech", HOST, 9559)
alAnimationPlayer = ALProxy("ALAnimationPlayerProxy", HOST, 9559)
alMotion = ALProxy("ALMotion", HOST, 9559)
alRobotPosture = ALProxy("ALRobotPosture", HOST, 9559)
alFaceDetection = ALProxy("ALFaceDetectionProxy", HOST, 9559)
#----------------------------------------------------------------------------------------------------

print("Server starting...pop")
HOST = '127.0.0.1' #socket.gethostbyname(socket.gethostname()) # Standard loopback interface             address (localhost)
print("Server starting on", HOST)
PORT = 7895    # Port to listen on (non-privileged ports are > 1023)
print("Server starting...pop0000000000000000")
ADDR = (HOST, PORT)
server = None
HEADER = 1024
FORMAT = 'utf-8'
DISCONNECT_MESSAGE = "!DISCONNECT"
print("Server starting...pop1111111111111111111")
server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

print("Server starting...pop22222222222222222222222")
ADDR = (HOST, PORT)
print("Server starting...pop3333333333333333333333333")
server.bind(ADDR)
print("Server starting...pop4444444444444444444444444444")
server.listen(5)
print("[STARTING] server is listening on", HOST)
conn, addr = server.accept()
thread = threading.Thread(target=handle_client, args=(conn, addr))
thread.start()
