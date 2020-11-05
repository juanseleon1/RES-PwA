# in myservice.py

import qi

class MyService:

    def echo(self, message):
        return message

    def do_something(self):
        pass

def main():
    app = qi.Application()
    app.start()
    session = app.session
    myService = MyService()
    session.registerService("MyService", myService)
    app.run()

if __name__ == "__main__":
    main()

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
        "alegre": "conversacio_alegre"
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