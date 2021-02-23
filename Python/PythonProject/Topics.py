topic_content_1 = ('topic: ~basicoConv()\n'
                   'language: spe\n'
                   'concept:(saludos) [Hola buenas saludos "Muy buenos dias" "Buen dia"]\n'
                   'concept:(respuestas_simples) [si no "tal vez"]\n'
                   'concept:(emociones_positivos) ["bien feliz alegre sonriente animado chevere "]\n'
                   'concept:(emociones_negativas) [mal fatal terrible horrible aburrido triste desanimado]\n'
                   'u: (como se encuentra?) Me encuentro muy bien, muchas gracias, que tal se encuentra usted?\n'
                   'u: (_~saludos) Muy buenos dias! Espero que se encuentre muy bien. Que quiere hacer hoy?  .\n'
                   'u: ({Hoy} {"Este dia"} Quiero escuchar musica) De acuerdo, que genero quisieras escuchar?\n'
                   'u: ([Vallenato Reggaeton Champeta Salsa Balada]) Con mucho gusto, ahorita lo pondremos\n'
                   'u: (Di la verdad) Leon tiene cabeza de kiwi\n'
                   'u: (Dime algo) Tengo una vaca lechera, no es una vaca cualquiera\n'
                   'u: (Que se dice) Que se narra? la mojarrra\n'
                   'u: (Mafe me cae mal) A mi tambien\n'
                    'u: (Estoy cansado) Callese, perro\n'
                    'u: (Canta porfavor) Guatermelon shugar...high, Guatermelon shugar...high\n'
                    'u: (Chiste)La foca le dice a su mama: I lof llu, moder foca.\n'
                    'u: (Mafe es facil) La Mafacil, le dicen\n'
                    'u: (Mafe no me quiere) A nadie, solo se quiere a si misma\n'

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
