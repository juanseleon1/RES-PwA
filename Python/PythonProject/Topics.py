"""topic_content_1 = ('topic: ~basicoTopic()\n'
        'language: spe\n'
        'concept:(saludos) [Hola buenas saludos "Muy buenos dias" "Buen dia"]\n'
        'concept:(respuestas_simples) [si no "tal vez"]\n'
        'concept:(emociones_positivos) ["bien feliz alegre sonriente animado chevere "]\n'
        'concept:(emociones_negativas) [mal fatal terrible horrible aburrido triste desanimado]\n'
        'u: (como se encuentra?) Me encuentro muy bien, muchas gracias, que tal se encuentra usted?\n'
        'u: (_~saludos) Muy buenos dias! Espero que se encuentre muy bien. Que quiere hacer hoy?  .\n'
        'u: ({Hoy} {"Este dia"} Quiero escuchar musica) De acuerdo, que genero quisieras escuchar?\n'
            'u2: ([Vallenato Reggaeton Champeta Salsa Balada]) Con mucho gusto, ahorita lo pondremos\n'
        'u: (Di la verdad) Leon tiene cabeza de kiwi\n'
        'u: (Dime algo) Tengo una vaca lechera, no es una vaca cualquiera\n'
        'u: (Que se dice) Que se narra? la mojarrra\n'
        'u: (Mafe me cae mal) A mi tambien\n'
        'u: (Estoy cansado) Callese, perro\n'
        'u: (Canta porfavor) Guatermelon shugar...high, Guatermelon shugar...high\n'
        'u: (Chiste)La foca le dice a su mama: I lof llu, moder foca.\n'
        'u: (Mafe es facil) La Mafacil, le dicen\n'
        'u: (Mafe no me quiere) A nadie, solo se quiere a si misma\n'
        'u: (Tengo hambre) Vamos a criollo\n'
        'u: (Quien es laion?) Es el chico oracol\n'
        'u: (Mafe tiene hambre) solo traga dulces pa desayunar  \n'
        )

topico_emocional = (
     'topic: ~emoTopic()\n'
     'language: spe\n'
    'concept: (emociones_positivas) [feliz alegre alegria genial bacano bacana chevere rico] \n'
    'concept: (emociones_tristes) [triste tristeza achantado dolor mal melancolia melancolico aburrido miedo] \n'
    'concept: (emociones_enojo) [ira enojo piedra molestia rabia molesto molesta joda jode jodes mamado mamada quiero azare ] \n'
    'concept: (emociones_normales) [Bien bueno ok alegra agradable "como quiera" nada normal] \n'
    'u: ([muy porque imaginate imaginese estoy super] _~emociones_positivas) FALTA CONSULTAR CON PAOLA QUE PUEDE RESPONDERSE \n'
    'u: ([Tengo "Me siento" Estoy] _~emociones_tristes) FALTA CONSULTAR CON PAOLA QUE PUEDE RESPONDERSE \n'
    'u: ([Estoy No Tengo] _~emociones_enojo) FALTA CONSULTAR CON PAOLA QUE PUEDE RESPONDERSE \n'
    'u: ([Vayase Vete]) Esta bien nos vemos luego \n'
    'u: ([Esta Ah Me "To do" Estoy  _~emociones_normales) Me alegra, no hay nada como sentirse bien\n'
    'rn1: ("Respuesta Normal Tipo uno") Eso esta de lujo\n'
    'rn2: ("Respuesta Normal Tipo dos") Que bueno escuchar eso! Yo me alegro por ti\n'
)


topico_alegre = (
    'topic: ~alegreTopic()\n'
    'language: spe\n'
    'concept: (bloque_uno) [feliz alegre emocionado chevere animado animada emocionada contento contenta] \n'
    'concept: (bloque_dos) [alegria felicidad esperanza emocion gratitud serenidad diversion] \n'
    'concept: (bloque_tres) [amor orgullo ] \n'
    'u: ({"Imaginese que"}[Estoy "Me siento" ]{super}{mega}{re}{"re contra"} _~bloque_uno) Verte tan alegre te hace lucir genial, te ves muy bien hoy! \n'
    'u: ([Siento Tengo] {[mucha muchisima tantisima demasiada]} _~bloque_dos) La alegria te da una sonrisa muy hermosa \n'
    'u: ([Siento Tengo] {[mucho muchisimo tantisimo demasiado]} _~bloque_tres) Esos son los sentimientos mas hermosos de una persona \n'

)


topico_triste = (
    'topic: ~sadTopic()\n'
   'language: spe\n'
    'concept: (bloque_uno) [aburrido cansado fastidiado mamado] \n'
    'concept: (bloque_dos) [mal terrible horrible] \n'
    'concept: (bloque_tres) [triste melancolica melancolico adolorido dolido ] \n'
    'concept: (bloque_cuatro ) [tristeza melancolia ] \n'
    'u: ({"Imaginese que"}[Estoy "Me siento" ]{super}{mega}{re}{"re contra"} _~bloque_uno) Que lastima, quisiera poder hacer algo para hacerte sentir mejor, Que tal una cancion? \n'
    'u: ({"Imaginese que"}[Estoy "Me siento" ]{super}{mega}{re}{"re contra"} _~bloque_dos) Aca estoy para ayudarte con eso, esos sentimientos nos ayudan a sanar heridas\n'
    'u: ({"Imaginese que"}[Estoy "Me siento" ]{super}{mega}{re}{"re contra"} _~bloque_tres) Tranquilo, to do pasara. Recuerda que pronto volveras a estar bien y seras mucho mejor \n'
    'u: ([Siento Tengo] {[mucha muchisima tantisima demasiada]} _~bloque_cuatro) Te comprendo, pero piensa que podras verte a ti mismo de otra manera y saldras adelante mucho mejor\n'
)


topico_ira = (
    'topic: ~iraTopic()\n'
    'language: spe\n'
    'concept: (bloque_uno) [molesto emputado enojado jodido fastidiado mamado mamada] \n'
    'concept: (bloque_dos) [piedra rabia ira furia colera] \n'
    'u: ([Estoy "Me siento" ]{super}{mega}{re}{"re contra"} _~bloque_uno) Que lastima, quisiera poder hacer algo para hacerte sentir mejor, Que tal una cancion? \n'
    'u: ([Siento Tengo] {[mucha muchisima tantisima demasiada]} _~bloque_dos) Entiendo que te sientas asi, pero si no te     \n'
    'u: ([Vayase Vete]) Esta bien nos vemos luego \n'
)


topico_normal= (
    'topic: ~normTopic()\n'
    'language: spe\n'
    'concept: (bloque_uno) [bueno "ah bueno" okey "Esta bien" ] \n'
    'concept: (bloque_dos) ["to do bien" "Esta agradable" "Me siento bien" bien "Esta Bueno"] \n'
    'concept: (bloque_tres) ["me alegra" "Estoy normal" ] \n'
    'u: (_~bloque_uno) Me alegra, no hay nada como sentirse bien\n'
    'u: (_~bloque_dos) Que bueno escuchar eso! Yo me alegro por ti\n'
    'u: (_~bloque_tres) Eso esta de lujo\n'
)

conversacion_musica = (
    'topic: ~musicTopic()\n'
    'language: spe\n'
    'concept: (generos) [clasica jazz soul blues flamenco tango pop house rock punk metal disco]\n'    
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
)"""

topic_content_1 = "/data/home/nao/topics/basicTopic.top"
topico_alegre = "/data/home/nao/topics/alegreTopic.top"
topico_triste = "/data/home/nao/topics/sadTopic.top"
topico_ira = "/data/home/nao/topics/iraTopic.top"
#topico_emocional = "/data/home/nao/topics/emoTopic.top"
topico_normal = "/data/home/nao/topics/normalTopic.top"
topico_ayuda = "/data/home/nao/topics/ayudaTopic.top"
topico_retroCancion = "/data/home/nao/topics/retroCancionTopic.top"
topico_retroCuento = "/data/home/nao/topics/retroCuentoTopic.top"
topico_saludable = "/data/home/nao/topics/saludaTopic.top"
topico_sonido = "/data/home/nao/topics/soundTopic.top"
topico_blank = "/data/home/nao/topics/blankaTopic.top"

#topicorron = "/data/home/nao/topics/superTopico.top"

#topic_list = [topic_content_1, topico_alegre, topico_triste, topico_ira, topico_normal, conversacion_musica]

topic_list = [topico_alegre, topico_ira, topic_content_1,topico_ayuda,topico_retroCancion,topico_retroCuento,topico_saludable,topico_triste, topico_blank]

