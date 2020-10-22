# in myservice.py

import qi
import helper
import mylib

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