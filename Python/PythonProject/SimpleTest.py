'''
from naoqi import *
tts = ALProxy("ALTextToSpeech", "10.195.22.168", 9559)

speech = ALProxy("ALDialog", "10.195.22.168",9559)


tts.say("Que mas pues gonorrreeeaaas")

topic_content_example = (

    # TOPIC - NAME
    # CONCEPTS - palabras diferentes utilizadas en un mismo contexto - Asi como (ingredientes_arepas) : [leche huevos sal agua harina]

    # Estructura
    # (frase del usuario) + respuesta
    # Cada frase puede cambiar dependiendo del uso de los conceptos o de palabras que pueden ser reemplazables. Asi como uno puede decir buenas, o buenas tardes-

    # Frase sencilla

    'topic: ~ejemploSimple()\n'

    'concept:(saludos) [Hola buenas saludos "Buenos dias" "Buen dia"]\n'
    'concept:(respuestas_simples) [si no "tal vez"]\n'
    'concept:(emociones_positivos) ["bien feliz alegre sonriente animado chevere "]\n'
    'concept:(emociones_negativas) [mal fatal terrible horrible aburrido triste desanimado]\n'




    'u: (como se encuentra?) Me encuentro muy bien, muchas gracias, que tal se encuentra usted?\n'


    # Utilizar un concepto en una frase
    'u: (_~saludos) Muy buenos dias! Espero que se encuentre muy bien. Que quiere hacer hoy?  .\n'
    'u: (I [want "would like"] {some} _~food) Sure! You must really like $1 .\n'

    # Utilizar un cambio en una frase - Un elemento en la frase que puede estar o no cuando se habla. Estoy muy bien - Estoy bien. Seria la misma frase pero quitandole el muy
    'u: ({Hoy} {Este dia} Quiero escuchar musica) De acuerdo, que genero quisieras escuchar?\n'

    # Variacion de palabra en la frase. Una frase puede tener variaciones, asi como los conceptos. estos se pueden poner en la frase
    'u: ([Vallenato Reggaeton Champeta Salsa Balada]) Con mucho gusto, ahorita lo pondremos\n'

)

# Acciones requeridas para el uso del topico
# CARGAR EL TOPICO COMO STRING

topico1 = speech.loadTopicContent(topic_content_example)
# ACTIVAR EL TOPICO CARGADO
speech.activateTopic(topico1)
# SUSCRIBIR AL ROBOT A UTILIZAR EL TOPICO - El parametro es un string cualquiera que se utilizara como identificador
speech.subscribe("PrimerTopico")

'''


import qi
import argparse
import sys

def main(session):
    """
    This example uses ALDialog methods.
    It's a short dialog session with two topics.
    """
    # Getting the service ALDialog
    ALDialog = session.service("ALDialog")
    ALDialog.setLanguage("Spanish")

    # writing topics' qichat code as text strings (end-of-line characters are important!)
    topic_content_1 = (    'topic: ~ejemploSimple()\n'
    'language: spe\n'
    'concept:(saludos) [Hola buenas saludos "Buenos dias" "Buen dia"]\n'
    'concept:(respuestas_simples) [si no "tal vez"]\n'
    'concept:(emociones_positivos) ["bien feliz alegre sonriente animado chevere "]\n'
    'concept:(emociones_negativas) [mal fatal terrible horrible aburrido triste desanimado]\n'
    'u: (como se encuentra?) Me encuentro muy bien, muchas gracias, que tal se encuentra usted?\n'
    'u: (_~saludos) Muy buenos dias! Espero que se encuentre muy bien. Que quiere hacer hoy?  .\n'
    'u: ({Hoy} {"Este dia"} Quiero escuchar musica) De acuerdo, que genero quisieras escuchar?\n'
    'u: ([Vallenato Reggaeton Champeta Salsa Balada]) Con mucho gusto, ahorita lo pondremos\n'
    'u: (Di la verdad) Leon tiene cabeza de kiwi\n'
    'u: (Dime algo) Tengo una vaca lecheraaaa, no es una vaca cualquieraaaaaa\n'

)

    topic_content_2 = ('topic: ~dummy_topic()\n'
                       'language: spe\n'
                       'u:(test) [a b "c d" "e f g"]\n')

    # Loading the topics directly as text strings
    topic_name_1 = ALDialog.loadTopicContent(topic_content_1)
    topic_name_2 = ALDialog.loadTopicContent(topic_content_2)

    # Activating the loaded topics
    ALDialog.activateTopic(topic_name_1)
    ALDialog.activateTopic(topic_name_2)

    # Starting the dialog engine - we need to type an arbitrary string as the identifier
    # We subscribe only ONCE, regardless of the number of topics we have activated
    ALDialog.subscribe('my_dialog_example')

    try:
        raw_input("\nSpeak to the robot using rules from both the activated topics. Press Enter when finished:")
    finally:
        # stopping the dialog engine
        ALDialog.unsubscribe('my_dialog_example')

        # Deactivating all topics
        ALDialog.deactivateTopic(topic_name_1)
        ALDialog.deactivateTopic(topic_name_2)

        # now that the dialog engine is stopped and there are no more activated topics,
        # we can unload all topics and free the associated memory
        ALDialog.unloadTopic(topic_name_1)
        ALDialog.unloadTopic(topic_name_2)


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--ip", type=str, default="10.195.22.168",
                        help="Robot's IP address. If on a robot or a local Naoqi - use '127.0.0.1' (this is the default value).")
    parser.add_argument("--port", type=int, default=9559,
                        help="port number, the default value is OK in most cases")

    args = parser.parse_args()
    session = qi.Session()
    try:
        session.connect("tcp://{}:{}".format(args.ip, args.port))
    except RuntimeError:
        print ("\nCan't connect to Naoqi at IP {} (port {}).\nPlease check your script's arguments."
               " Run with -h option for help.\n".format(args.ip, args.port))
        sys.exit(1)
    main(session)