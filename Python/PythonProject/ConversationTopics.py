from Robot import *
from naoqi import *

#--------------------------------------------------EJEMPLO DE TOPICOS-----------------------------------------------------------#


topic_content_example = (

    #TOPIC - NAME
    'topic: ~ejemploSimple()\n'
    #CONCEPTS - palabras diferentes utilizadas en un mismo contexto - Así como (ingredientes_arepas) : [leche huevos sal agua harina]
    
    'concept:(saludos) [Hola buenas saludos "Buenos dias" "Buen dia"]\n'
    'concept:(respuestas_simples) [si no "tal vez"]\n'
    'concept:(emociones_positivos) ["bien feliz alegre sonriente animado chevere "]\n'
    'concept:(emociones_negativas) [mal fatal terrible horrible aburrido triste desanimado]\n'
    
    
    
    #Estructura
        # (frase del usuario) + respuesta
        # Cada frase puede cambiar dependiendo del uso de los conceptos o de palabras que pueden ser reemplazables. Así como uno puede decir buenas, o buenas tardes-
    
    
    #Frase sencilla
    'u: (¿como se encuentra?) Me encuentro muy bien, muchas gracias, qué tal se encuentra usted?\n'
    
    
    #Utilizar un concepto en una frase
    'u: (_~saludos) Muy buenos dias! Espero que se encuentre muy bien. Que quiere hacer hoy?  .\n'
    'u: (I [want "would like"] {some} _~food) Sure! You must really like $1 .\n'
 
    #Utilizar un cambio en una frase - Un elemento en la frase que puede estar o no cuando se habla. Estoy muy bien - Estoy bien. Sería la misma frase pero quitandole el muy
    'u: ({Hoy} {Este dia} Quiero escuchar musica) De acuerdo, qué genero quisieras escuchar?\n'
    
    #Variación de palabra en la frase. Una frase puede tener variaciones, así como los conceptos. estos se pueden poner en la frase
    'u: ([Vallenato Reggaeton Champeta Salsa Balada]) Con mucho gusto, ahorita lo pondremos\n'

)



#Acciones requeridas para el uso del topico
#CARGAR EL TOPICO COMO STRING

topico1 = robot.load_topic_content(topic_content_example)
#ACTIVAR EL TOPICO CARGADO
robot.activate_conversational_topic(topico1)
#SUSCRIBIR AL ROBOT A UTILIZAR EL TOPICO - El parametro es un string cualquiera que se utilizará como identificador
robot.subscribe_topic("PrimerTopico")


#--------------------------------------------------EJEMPLO DE TOPICOS-----------------------------------------------------------#

#TIPOS DE TOPICOS A HACER
#----MOTIVACION
#----SALUDOS Y PRESENTACION
#----EMPATIA
#----FAMILIA Y AMIGOS
#----INFORMACION PERSONAL
#----GUSTOS Y PREFERENCIAS

topico_AnimoAnimoAnimo = (
    'concept: (Triggers) [ FALTA DETERMINAR CUALES FRASES VAN A SER LOS DETONANTES PARA DAR ANIMO]\n'  
    
    'topic: ~motivacion()\n'
    'u: (animar) lo estas haciendo muy bien, sigue así\n'
    'u: (trigger) Todavía lo puedes hacer mucho mejor, tu puedes\n'
    'u: (trigger) Vas muy bien sigue así\n'
    'u: (trigger) No te desanimes, sigue intentandolo\n'

)



topico_bien_hecho = (
    'topic: ~Presentacion()\n'
      
    'u: (Qué vamos a hacer?) Me encuentro muy bien, muchas gracias, qué tal se encuentra usted?\n'
    'u: (¿Quién eres?) Me llamo Pepper y soy un robot humanoide que quiere jugar contigo\n'
    'u: (Qué vamos a hacer?) Hoy vamos a hacer algo de musicoterapia para divertirnos un poco, te parece?\n'
    'u: ([Si claro "por supuesto" "esta bien" "de acuerdo"]) Excelente!\n'


)


conversacion_musica = (
    'concept: (generos) [clasica jazz soul blues flamenco tango pop house rock punk metal disco] \n'  

    'u: (hablar sobre musica) Te gusta la musica? \n'
        'u1: (si) Enserio? Qué genero te gusta escuchar?\n'
            'u2: (Vallenato) A mi también me gusta mucho el vallenato, te gusta diomedez?\n'
                'u3: (si) Es muy bueno! Escuchemos una canción de el\n'
                'u3: (no) Entonces escuchemos musica vallenata variada.\n'                
            'u2: (Reggaeton) A mi tambien me gusta mucho el reggaeton, te gusta Bad Bunny?\n'
                'u3: (si) Es muy bueno! Escuchemos una canción de el\n'
                'u3: (no) Entonces escuchemos reggaeton variado.\n'
            'u2: (Salsa) A mi tambien me gusta mucho la salsa, te gusta el Joe Arroyo?\n'
                'u3: (si) Es muy bueno! Escuchemos una canción de el\n'
                'u3: (no) Entonces escuchemos una buena salsa variada.\n'
            'u2: (Balada) A mi tambien me gusta mucho el reggaeton, te gusta Ricardo Montaner?\n'    
                'u3: (si) Es muy bueno! Escuchemos una canción de el\n'
                'u3: (no) Entonces escuchemos unas baladas variadas.\n'
            'u2: (Llanera) A mi tambien me gusta mucho el reggaeton, te gusta Reynaldo Armas?\n'
                'u3: (si) Es muy bueno! Escuchemos una canción de el\n'
                'u3: (no) Entonces escuchemos musica llanera variada.\n'
            'u2: (_~generos) No conozco mucho de ese genero, que te gusta más de ese genero?\n'

            
        'u2: (No) Que triste, creo que me deberia ir \n'

)


topico_emocional = (
    'concept: (emociones_positivas) [feliz alegre alegria genial bacano bacana chevere rico] \n'
    'concept: (emociones_tristes) [triste tristeza achantado dolor mal melancolia melancólico aburrido miedo] \n'
    'concept: (emociones_enojo) [ira enojo piedra molestia rabia molesto molesta joda jode jodes mamado mamada quiero azare ] \n'
    'concept: (emociones_normales) [Bien bueno ok alegra agradable "como quiera" nada normal] \n'

    'u: ([muy porque imaginate imaginese estoy super] _~emociones_positivas) FALTA CONSULTAR CON PAOLA QUE PUEDE RESPONDERSE \n'
    'u: ([Tengo "Me siento" Estoy] _~emociones_tristes) FALTA CONSULTAR CON PAOLA QUE PUEDE RESPONDERSE \n'
    'u: ([Estoy No Tengo] _~emociones_enojo) FALTA CONSULTAR CON PAOLA QUE PUEDE RESPONDERSE \n'
    'u: ([Váyase Vete]) Está bien, nos vemos luego \n'
    'u: ([Está Ah Me Todo Estoy] _~emociones_normales) FALTA CONSULTAR CON PAOLA QUE PUEDE RESPONDERSE\n'

)