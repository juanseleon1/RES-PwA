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
        
def hablar(texto_hablar):
    tts = ALProxy("ALTextToSpeech", HOST, 9559)
    tts.say(texto_hablar)
    
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
        1: "la ira es una emoción normal, pero no debemos dejarla salir de control porque no nos permite tomar buenas decisiones",
        2: "aveces la ira nos puede hacer sentir mal, sin embargo debemos tomar las cosas con calma",
        3: "pero sabes? las cosas tienen la importancia que nosotros les damos",
        4: "la ira nos pone tensos y nos dificulta pensar con claridad"
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
