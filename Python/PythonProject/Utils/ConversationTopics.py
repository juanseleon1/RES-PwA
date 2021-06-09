#-------------------------------------------------EJEMPLO DE TOPICOS-----------------------------------------------------------#

from main import robot

# --------------------------------------------------EJEMPLO DE TOPICOS-----------------------------------------------------------#


topic_content_example = (
    # TOPIC - NAME
    'topic: ~ejemploSimple()\n'
    # EL LENGUAJE DEL TOPICO DEBE SER EL MISMO DEL SISTEMA - SPE = espanol - ENU = ingles
    'language: spe\n'
    # CONCEPTS - palabras diferentes utilizadas en un mismo contexto - Asi como (ingredientes_arepas) : [leche huevos sal agua harina]
    'concept:(saludos) [Hola buenas saludos "Buenos dias" "Buen dia"]\n'
    'concept:(respuestas_simples) [si no "tal vez"]\n'
    'concept:(emociones_positivos) ["bien feliz alegre sonriente animado chevere "]\n'
    'concept:(emociones_negativas) [mal fatal terrible horrible aburrido triste desanimado]\n'
    # Estructura
    # (frase del usuario) + respuesta
    # Cada frase puede cambiar dependiendo del uso de los conceptos o de palabras que pueden ser reemplazables. Asi como uno puede decir buenas, o buenas tardes-

    # Frase sencilla
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
topico1 = robot.load_topic_content(topic_content_example)
# ACTIVAR EL TOPICO CARGADO
robot.activate_conversational_topic(topico1)
# SUSCRIBIR AL ROBOT A UTILIZAR EL TOPICO - El parametro es un string cualquiera que se utilizara como identificador del topico
robot.subscribe_topic("PrimerTopico")

# PARA QUE QUEDE EN ESTADO DE ESCUCHA PARA LOS TOPICOS Y SI SE ACABA TERMINE DESUSCRIBIENDOSE PARA EVITAR PROBLEMAS DE MEMORIA
try:
    raw_input("\nHABLA AL ROBOT CON EL MANEJO DE REGLAS DEL TOPICO, PRESIONA ENTER PARA FINALIZAR DE HABLAR:")
finally:
    robot.unsubscribe('PrimerTopico')
    robot.deactivateTopic(topico1)
    robot.unloadTopic(topico1)

# --------------------------------------------------EJEMPLO DE TOPICOS-----------------------------------------------------------#

# TIPOS DE TOPICOS A HACER
# ----MOTIVACION
# ----SALUDOS Y PRESENTACION
# ----EMPATIA
# ----FAMILIA Y AMIGOS
# ----INFORMACION PERSONAL
# ----GUSTOS Y PREFERENCIAS

    topico_AnimoAnimoAnimo = (
        'concept: (Triggers) [ FALTA DETERMINAR CUALES FRASES VAN A SER LOS DETONANTES PARA DAR ANIMO]\n'
        'topic: ~motivacion()\n'
        'u: (animar) lo estas haciendo muy bien, sigue asi\n'
        'u: (trigger) Todavia lo puedes hacer mucho mejor, tu puedes\n'
        'u: (trigger) Vas muy bien sigue asi\n'
        'u: (trigger) No te desanimes, sigue intentandolo\n'

    )

    topico_bien_hecho = (
        'topic: ~Presentacion()\n'

        'u: (Que vamos a hacer?) Me encuentro muy bien, muchas gracias, que tal se encuentra usted?\n'
        'u: (Quien eres?) Me llamo Pepper y soy un robot humanoide que quiere jugar contigo\n'
        'u: (Que vamos a hacer?) Hoy vamos a hacer algo de musicoterapia para divertirnos un poco, te parece?\n'
        'u: ([Si claro "por supuesto" "esta bien" "de acuerdo"]) Excelente!\n'

)

    conversacion_musica = (
        'concept: (generos) [clasica jazz soul blues flamenco tango pop house rock punk metal disco] \n'
        'u: (hablar sobre musica) Te gusta la musica? \n'
            'u1: (si) Enserio? Que genero te gusta escuchar?\n'
                'u2: (Vallenato) A mi tambien me gusta mucho el vallenato, te gusta diomedez?\n'
                    'u3: (si) Es muy bueno! Escuchemos una cancion de el\n'
                    'u3: (no) Entonces escuchemos musica vallenata variada.\n'
                'u2: (Reggaeton) A mi tambien me gusta mucho el reggaeton, te gusta Bad Bunny?\n'
                    'u3: (si) Es muy bueno! Escuchemos una cancion de el\n'
                    'u3: (no) Entonces escuchemos reggaeton variado.\n'
                'u2: (Salsa) A mi tambien me gusta mucho la salsa, te gusta el Joe Arroyo?\n'
                    'u3: (si) Es muy bueno! Escuchemos una cancion de el\n'
                    'u3: (no) Entonces escuchemos una buena salsa variada.\n'
                'u2: (Balada) A mi tambien me gusta mucho el reggaeton, te gusta Ricardo Montaner?\n'
                    'u3: (si) Es muy bueno! Escuchemos una cancion de el\n'
                    'u3: (no) Entonces escuchemos unas baladas variadas.\n'
                'u2: (Llanera) A mi tambien me gusta mucho el reggaeton, te gusta Reynaldo Armas?\n'
                    'u3: (si) Es muy bueno! Escuchemos una cancion de el\n'
                    'u3: (no) Entonces escuchemos musica llanera variada.\n'
                'u2: (_~generos) No conozco mucho de ese genero, que te gusta mas de ese genero?\n'
            'u1: (No) Que triste, creo que me deberia ir \n'

)

topico_emocional = (
    'concept: (emociones_positivas) [feliz alegre alegria genial bacano bacana chevere rico] \n'
    'concept: (emociones_tristes) [triste tristeza achantado dolor mal melancolia melancolico aburrido miedo] \n'
    'concept: (emociones_enojo) [ira enojo piedra molestia rabia molesto molesta joda jode jodes mamado mamada quiero azare ] \n'
    'concept: (emociones_normales) [Bien bueno ok alegra agradable "como quiera" nada normal] \n'

    'u: ([muy porque imaginate imaginese estoy super] _~emociones_positivas) FALTA CONSULTAR CON PAOLA QUE PUEDE RESPONDERSE \n'
    'u: ([Tengo "Me siento" Estoy] _~emociones_tristes) FALTA CONSULTAR CON PAOLA QUE PUEDE RESPONDERSE \n'
    'u: ([Estoy No Tengo] _~emociones_enojo) FALTA CONSULTAR CON PAOLA QUE PUEDE RESPONDERSE \n'
    'u: ([Vayase Vete]) Esta bien nos vemos luego \n'
    'u: ([Esta Ah Me Todo Estoy] _~emociones_normales) FALTA CONSULTAR CON PAOLA QUE PUEDE RESPONDERSE\n'

)


#FUNCION PARA EL USO DE TOPICOS QUE ESTÁN ARRIBA SEGUN EL EJEMPLO DE LA DOCUMENTACION

def invocarTopico(topico,IdTopicoString):
    topico1 = robot.load_topic_content(topico)
    robot.activate_conversational_topic(topico1)
    robot.subscribe_topic(IdTopicoString)
    try:
        raw_input("\nHABLA AL ROBOT CON EL MANEJO DE REGLAS DEL TOPICO, PRESIONA ENTER PARA FINALIZAR DE HABLAR:")
    finally:
        robot.unsubscribe('PrimerTopico')
        robot.deactivateTopic(topico1)
        robot.unloadTopic(topico1)