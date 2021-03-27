
Insert into RESPWA1.ACTIVIDADPWA (ID,NOMBRE,TIPO,DURACION) values ('0','MUSICOTERAPIA','Entretenimiento','60');
Insert into RESPWA1.ACTIVIDADPWA (ID,NOMBRE,TIPO,DURACION) values ('1','CUENTERIA','Entretenimiento','60');
commit;

Insert into RESPWA1.ACTXPREFERENCIA (ACTIVIDADPWA_ID,PERFIL_PREFERENCIA_CEDULA,ACTIVA,GUSTO,ENRIQ,DIFICULTAD_DIFICULTAD) values ('0','123456789','1','0,8','5',null);
Insert into RESPWA1.ACTXPREFERENCIA (ACTIVIDADPWA_ID,PERFIL_PREFERENCIA_CEDULA,ACTIVA,GUSTO,ENRIQ,DIFICULTAD_DIFICULTAD) values ('1','123456789','1','0,5','2',null);
commit;

Insert into RESPWA1.BOOKS (BOOK_ID,TITLE,AUTHOR_LAST_NAME,AUTHOR_FIRST_NAME,RATING) values ('qweqwe','qweqwe','qweqwe',null,null);
Insert into RESPWA1.BOOKS (BOOK_ID,TITLE,AUTHOR_LAST_NAME,AUTHOR_FIRST_NAME,RATING) values ('123','asdas','asdasd',null,null);
Insert into RESPWA1.BOOKS (BOOK_ID,TITLE,AUTHOR_LAST_NAME,AUTHOR_FIRST_NAME,RATING) values ('hp7','hp7','hp7','hp7','7');
Insert into RESPWA1.BOOKS (BOOK_ID,TITLE,AUTHOR_LAST_NAME,AUTHOR_FIRST_NAME,RATING) values ('A1111','Moby Dick','Melville','Herman','10');
Insert into RESPWA1.BOOKS (BOOK_ID,TITLE,AUTHOR_LAST_NAME,AUTHOR_FIRST_NAME,RATING) values ('A2222','Get Rich Really Fast','Scammer','Ima','1');
Insert into RESPWA1.BOOKS (BOOK_ID,TITLE,AUTHOR_LAST_NAME,AUTHOR_FIRST_NAME,RATING) values ('A3333','Finding Inner Peace','Blissford','Serenity',null);
Insert into RESPWA1.BOOKS (BOOK_ID,TITLE,AUTHOR_LAST_NAME,AUTHOR_FIRST_NAME,RATING) values ('A4444','Great Mystery Stories','Whodunit','Rodney','5');
Insert into RESPWA1.BOOKS (BOOK_ID,TITLE,AUTHOR_LAST_NAME,AUTHOR_FIRST_NAME,RATING) values ('A5555','Software Wizardry','Abugov','D.','10');
commit;

Insert into RESPWA1.CANCION (NOMBRE,GUSTO,GENERO_GENERO) values ('Tu cumpleaños','0,8','Vallenato');
Insert into RESPWA1.CANCION (NOMBRE,GUSTO,GENERO_GENERO) values ('Estrato 8','0,5','Carranga');
Insert into RESPWA1.CANCION (NOMBRE,GUSTO,GENERO_GENERO) values ('Wannabe','0,5','Pop');
Insert into RESPWA1.CANCION (NOMBRE,GUSTO,GENERO_GENERO) values ('El camino de la vida','0,9','Mariachi');
commit;

Insert into RESPWA1.CAUSADEMENCIA (CONDICION) values ('Parkinson');
commit;

Insert into RESPWA1.CUENTO (GENERO_GENERO,AUTOR,GUSTO,NOMBRE) values ('Literatura Infantil','Charles Perrault','1','Caperucita roja');
Insert into RESPWA1.CUENTO (GENERO_GENERO,AUTOR,GUSTO,NOMBRE) values ('Literatura Infantil','Jon Scieszka','0,7','Los 3 cerditos');
commit;

Insert into RESPWA1.CUIDADOR (NOMBREUSUARIO,"CONTRASEÑA",NOMBRE,CORREO,CELULAR) values ('juleon','12345','Juan Leon','juleon@javeriana.edu.co','3204094111');
commit;

Insert into RESPWA1.ESTADOCIVIL (TIPOEC) values ('Casado');
Insert into RESPWA1.ESTADOCIVIL (TIPOEC) values ('Divorciado');
Insert into RESPWA1.ESTADOCIVIL (TIPOEC) values ('Soltero');
Insert into RESPWA1.ESTADOCIVIL (TIPOEC) values ('Viudo');
commit;

Insert into RESPWA1.FAMILIAR (ID,NOMBRE,PARENTESCO,INTERES,ESTAVIVO,NACIMIENTO) values ('0','Olga','Hermana','1','0',to_date('10/12/45','DD/MM/RR'));
Insert into RESPWA1.FAMILIAR (ID,NOMBRE,PARENTESCO,INTERES,ESTAVIVO,NACIMIENTO) values ('1','Eugenio','Esposo','1','1',to_date('05/03/40','DD/MM/RR'));
Insert into RESPWA1.FAMILIAR (ID,NOMBRE,PARENTESCO,INTERES,ESTAVIVO,NACIMIENTO) values ('2','Jorge','Hijo','1','1',to_date('25/07/70','DD/MM/RR'));
Insert into RESPWA1.FAMILIAR (ID,NOMBRE,PARENTESCO,INTERES,ESTAVIVO,NACIMIENTO) values ('3','Cecilia','Hija','1','1',to_date('20/09/72','DD/MM/RR'));
commit;

Insert into RESPWA1.FAMILIARES (PERFILPWA_CEDULA,FAMILIAR_ID) values ('123456789','0');
Insert into RESPWA1.FAMILIARES (PERFILPWA_CEDULA,FAMILIAR_ID) values ('123456789','1');
Insert into RESPWA1.FAMILIARES (PERFILPWA_CEDULA,FAMILIAR_ID) values ('123456789','2');
Insert into RESPWA1.FAMILIARES (PERFILPWA_CEDULA,FAMILIAR_ID) values ('123456789','3');
commit;

Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Érase una vez una preciosa niña','1','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('que siempre llevaba una capa roja con capucha para protegerse del frío','2','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Por eso, todo el mundo la llamaba Caperucita Roja','3','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Caperucita vivía en una casita cerca del bosque','4','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Un día, la mamá de  Caperucita le dijo','5','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Hija mía, tu abuelita está enferma','6','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('He preparado una cestita con tortas y un tarrito de miel para que se la lleves','7','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('¡Ya verás qué contenta se pone!','8','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('¡Estupendo, mamá!','9','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Yo también tengo muchas ganas de ir a visitarla, dijo Caperucita saltando de alegría','10','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Cuando Caperucita se disponía a salir de casa','11','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('su mamá, con gesto un poco serio, le hizo una advertencia','12','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Ten mucho cuidado, cariño. No te entretengas con nada y no hables con extraños','13','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Sabes que en el bosque vive el lobo y es muy peligroso','14','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Si ves que aparece, sigue tu camino sin detenerte','15','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('No te preocupes, mamita, dijo la niña. Tendré en cuenta todo lo que me dices','16','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Está bien, contestó la mamá, confiada. Dame un besito y no tardes en regresar','17','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Así lo haré, mamá, afirmó de nuevo Caperucita diciendo adiós con su manita mientras se alejaba','18','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Cuando llegó al bosque','19','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('la pequeña comenzó a distraerse contemplando los pajaritos y recogiendo flores','20','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('No se dio cuenta de que alguien la observaba detrás de un viejo y frondoso árbol','21','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('De repente, oyó una voz dulce y zalamera','22','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('¿A dónde vas, Caperucita?','23','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('La niña, dando un respingo, se giró y vio que quien le hablaba era un enorme lobo','24','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Voy a casa de mi abuelita, al otro lado del bosque','25','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Está enferma y le llevo una deliciosa merienda y unas flores para alegrarle el día','26','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('¡Oh, eso es estupendo!, dijo el astuto lobo, Yo también vivo por allí','27','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Te echo una carrera a ver quién llega antes','28','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Cada uno iremos por un camino diferente ¿te parece bien?','29','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('La inocente niña pensó que era una idea divertida y asintió con la cabeza','30','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('No sabía que el lobo había elegido el camino más corto para llegar primero a su destino','31','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Cuando el animal  llegó a casa de la abuela, llamó a la puerta','32','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('¿Quién es?, gritó la mujer','33','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Soy yo, abuelita, tu querida nieta Caperucita','34','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Ábreme la puerta, dijo el lobo imitando la voz de la niña','35','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Pasa, querida mía. La puerta está abierta, contestó la abuela','36','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('El malvado lobo entró en la casa y sin pensárselo dos veces','37','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('saltó sobre la cama y se comió a la anciana','38','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Después, se puso su camisón y su gorrito de dormir','39','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('y se metió entre las sábanas esperando a que llegara la niña','40','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Al rato, se oyeron unos golpes','41','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('¿Quién llama?, dijo el lobo forzando la voz como si fuera la abuelita','42','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Soy yo, Caperucita. Vengo a hacerte una visita y a traerte unos ricos dulces para merendar','43','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Pasa, querida, estoy deseando abrazarte, dijo el lobo malvado relamiéndose','44','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('La habitación estaba en penumbra','45','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Cuando se acercó a la cama, a Caperucita le pareció que su abuela estaba muy cambiada','46','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Extrañada, le dijo: Abuelita, abuelita ¡qué ojos tan grandes tienes!','47','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Son para verte mejor, preciosa mía, contestó el lobo, suavizando la voz','48','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Abuelita, abuelita ¡qué orejas tan grandes tienes!','49','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Son para oírte mejor, querida','50','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Pero abuelita, abuelita ¡qué boca tan grande tienes!','51','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('¡Es para comerte mejor!, gritó el lobo dando un enorme salto y comiéndose a la niña de un bocado','52','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Con la barriga llena después de tanta comida, al lobo le entró sueño','53','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Salió de la casa, se tumbó en el jardín y cayó profundamente dormido','54','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('El fuerte sonido de sus ronquidos llamó la atención de un cazador que pasaba por allí','55','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('El hombre se acercó y vio que el animal tenía la panza muy hinchada, demasiado para ser un lobo','56','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Sospechando que pasaba algo extraño, cogió un cuchillo y le rajó la tripa','57','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('¡Se llevó una gran sorpresa cuando vio que de ella salieron sanas y salvas la abuela y la niña!','58','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Después de liberarlas, el cazador cosió la barriga del lobo','59','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('y esperaron un rato a que el animal se despertará','60','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Cuando por fin abrió los ojos, vio como los tres le rodeaban y escuchó la profunda','61','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('y amenazante voz del cazador que le gritaba enfurecido:','62','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('¡Lárgate, lobo malvado! ¡No te queremos en este bosque!','63','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('¡Como vuelva a verte por aquí, no volverás a contarlo!','64','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('El lobo, aterrado, puso pies en polvorosa y salió despavorido','65','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Caperucita y su abuelita, con lágrimas cayendo sobre sus mejillas, se abrazaron','66','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('El susto había pasado y la niña había aprendido una importante lección:','67','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('nunca más desobedecería a su mamá ni se fiaría de extraños','68','Caperucita roja');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Había una vez tres hermanos cerditos que vivían en el bosque','1','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Como el malvado lobo siempre los estaba persiguiendo para comérselos dijo un día el mayor:','2','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Tenemos que hacer una casa para protegernos de lobo','3','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Así podremos escondernos dentro de ella cada vez que el lobo aparezca por aquí','4','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('A los otros dos les pareció muy buena idea','5','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('pero no se ponían de acuerdo respecto a qué material utilizar','6','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Al final, y para no discutir, decidieron que cada uno la hiciera de lo que quisiese','7','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('El más pequeño optó por utilizar paja, para no tardar mucho y poder irse a jugar después','8','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('El mediano prefirió construirla de madera','9','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('que era más resistente que la paja y tampoco le llevaría mucho tiempo hacerla','10','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Pero el mayor pensó que aunque tardara más que sus hermanos','11','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('lo mejor era hacer una casa resistente y fuerte con ladrillos','12','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Además así podré hacer una chimenea con la que calentarme en invierno, pensó el cerdito','13','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Cuando los tres acabaron sus casas se metieron cada uno en la suya','14','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('y entonces apareció por ahí el malvado lobo','15','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Se dirigió a la de paja y llamó a la puerta:','16','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Anda cerdito se bueno y déjame entrar','17','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('¡No! ¡Eso ni pensarlo!','18','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('¡Pues soplaré y soplaré y la casita derribaré!','19','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Y el lobo empezó a soplar y a estornudar, la débil casa acabó viniéndose abajo','20','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Pero el cerdito echó a correr y se refugió en la casa de su hermano mediano','21','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('que estaba hecha de madera','22','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Anda cerditos sed buenos y dejarme entrar','23','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('¡No! ¡Eso ni pensarlo!, dijeron los dos','24','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('¡Pues soplaré y soplaré y la casita derribaré!','25','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('El lobo empezó a soplar y a estornudar','26','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('aunque esta vez tuvo que hacer más esfuerzos para derribar la casa al final la madera acabó cediendo','27','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('y los cerditos salieron corriendo en dirección hacia la casa de su hermano mayor','28','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('El lobo estaba cada vez más hambriento así que sopló y sopló con todas sus fuerzas','29','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('pero esta vez no tenía nada que hacer porque la casa no se movía ni siquiera un poco','30','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Dentro los cerditos celebraban la resistencia de la casa de su hermano','31','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('y cantaban alegres por haberse librado del lobo:','32','Los 3 cerditos');
commit;
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('¿Quien teme al lobo feroz? ¡No, no, no!','33','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Fuera el lobo continuaba soplando en vano, cada vez más enfadado','34','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Hasta que decidió parar para descansar y entonces reparó en que la casa tenía una chimenea','35','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('¡Ja! ¡Pensaban que de mí iban a librarse! ¡Subiré por la chimenea y me los comeré a los tres!','36','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Pero los cerditos le oyeron, y para darle su merecido llenaron la chimenea de leña','37','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('y pusieron al fuego un gran caldero con agua','38','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('Así cuando el lobo cayó por la chimenea el agua estaba hirviendo','39','Los 3 cerditos');
Insert into RESPWA1.FRASES (CONTENIDO,ORDEN,CUENTO_NOMBRE) values ('se quemó tanto que salió gritando de la casa y no volvió a comer cerditos en una larga temporada','40','Los 3 cerditos');
commit;

Insert into RESPWA1.GENERO (GENERO,GUSTO) values ('Literatura Infantil','0,7');
Insert into RESPWA1.GENERO (GENERO,GUSTO) values ('Vallenato','0,9');
Insert into RESPWA1.GENERO (GENERO,GUSTO) values ('Carranga','0,6');
Insert into RESPWA1.GENERO (GENERO,GUSTO) values ('Pop','0,3');
Insert into RESPWA1.GENERO (GENERO,GUSTO) values ('Mariachi','0,8');
commit;

Insert into RESPWA1.LISTATAGS (CANCION_NOMBRE,TAGS_ID) values ('El camino de la vida','0');
Insert into RESPWA1.LISTATAGS (CANCION_NOMBRE,TAGS_ID) values ('El camino de la vida','1');
Insert into RESPWA1.LISTATAGS (CANCION_NOMBRE,TAGS_ID) values ('Estrato 8','0');
Insert into RESPWA1.LISTATAGS (CANCION_NOMBRE,TAGS_ID) values ('Estrato 8','3');
Insert into RESPWA1.LISTATAGS (CANCION_NOMBRE,TAGS_ID) values ('Tu cumpleaños','0');
Insert into RESPWA1.LISTATAGS (CANCION_NOMBRE,TAGS_ID) values ('Tu cumpleaños','2');
Insert into RESPWA1.LISTATAGS (CANCION_NOMBRE,TAGS_ID) values ('Wannabe','4');
commit;

Insert into RESPWA1.NIVEL_EDUCATIVO (TIPONE) values ('Bachiller');
Insert into RESPWA1.NIVEL_EDUCATIVO (TIPONE) values ('Doctorado');
Insert into RESPWA1.NIVEL_EDUCATIVO (TIPONE) values ('Master');
Insert into RESPWA1.NIVEL_EDUCATIVO (TIPONE) values ('Primaria');
Insert into RESPWA1.NIVEL_EDUCATIVO (TIPONE) values ('Tecnologia');
Insert into RESPWA1.NIVEL_EDUCATIVO (TIPONE) values ('Universitario');
commit;

Insert into RESPWA1.PERFIL_MEDICO (PERFILPWA_CEDULA,TOMAMEDICAMENTOS,DISCAPAUDITIVA,DISCAPVISUAL,DISCAPMOTORA,ESTADIOENFERMEDAD,PERIODOVIGILIA,CAUSADEMENCIA_CONDICION) values ('123456789','1','0','1','0','5','60','Parkinson');
commit;

Insert into RESPWA1.PERFIL_PREFERENCIA (PERFILPWA_CEDULA,NOMBREPREFERIDO,GUSTOKARAOKE,GUSTOMUSICA,GUSTOBAILE,VOLPREFERIDO) values ('123456789','Juanis','0,7','1','1','50');
commit;

Insert into RESPWA1.PERFILPWA (NOMBRE,APELLIDO,FECHANACIMIENTO,PAISNACIMIENTO,EDAD,CEDULA,PROFESION,ESTADOCIVIL_TIPOEC,NIVEL_EDUCATIVO_TIPONE,CUIDADOR_NOMBREUSUARIO,IDROBOT) values ('Juanita','Caballero',to_date('17/05/40','DD/MM/RR'),'Colombia','80','123456789','Ingeniera industrial','Casado','Doctorado','juleon','0');
commit;

Insert into RESPWA1.PREFERENCIACANCION (PERFIL_PREFERENCIA_PERFILPWA_CEDULA,CANCION_NOMBRE) values ('123456789','El camino de la vida');
Insert into RESPWA1.PREFERENCIACANCION (PERFIL_PREFERENCIA_PERFILPWA_CEDULA,CANCION_NOMBRE) values ('123456789','Estrato 8');
Insert into RESPWA1.PREFERENCIACANCION (PERFIL_PREFERENCIA_PERFILPWA_CEDULA,CANCION_NOMBRE) values ('123456789','Tu cumpleaños');
Insert into RESPWA1.PREFERENCIACANCION (PERFIL_PREFERENCIA_PERFILPWA_CEDULA,CANCION_NOMBRE) values ('123456789','Wannabe');
commit;

Insert into RESPWA1.PREFERENCIAXCUENTO (PERFIL_PREFERENCIA_PERFILPWA_CEDULA,CUENTO_NOMBRE) values ('123456789','Caperucita roja');
Insert into RESPWA1.PREFERENCIAXCUENTO (PERFIL_PREFERENCIA_PERFILPWA_CEDULA,CUENTO_NOMBRE) values ('123456789','Los 3 cerditos');
commit;

Insert into RESPWA1.TAGS (ID,TAG) values ('0','Familia');
Insert into RESPWA1.TAGS (ID,TAG) values ('1','Matrimonio');
Insert into RESPWA1.TAGS (ID,TAG) values ('2','Cumpleaños');
Insert into RESPWA1.TAGS (ID,TAG) values ('3','Amigos');
Insert into RESPWA1.TAGS (ID,TAG) values ('4','Graduacion');
commit;
--------------------------------------------------------
--  DDL for Index ACTIVIDADPWA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."ACTIVIDADPWA_PK" ON "RESPWA1"."ACTIVIDADPWA" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index ACTIVIDADRUTINARIA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."ACTIVIDADRUTINARIA_PK" ON "RESPWA1"."ACTIVIDADRUTINARIA" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  ;
--------------------------------------------------------
--  DDL for Index ACTXPREFERENCIA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."ACTXPREFERENCIA_PK" ON "RESPWA1"."ACTXPREFERENCIA" ("ACTIVIDADPWA_ID", "PERFIL_PREFERENCIA_CEDULA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index AUTHOR_TITLE_UNIQUE
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."AUTHOR_TITLE_UNIQUE" ON "RESPWA1"."BOOKS" ("AUTHOR_LAST_NAME", "TITLE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index BOOKS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."BOOKS_PK" ON "RESPWA1"."BOOKS" ("BOOK_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index CANCION_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."CANCION_PK" ON "RESPWA1"."CANCION" ("NOMBRE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index CAUSADEMENCIA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."CAUSADEMENCIA_PK" ON "RESPWA1"."CAUSADEMENCIA" ("CONDICION") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index CDR_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."CDR_PK" ON "RESPWA1"."CDR" ("PERFIL_MEDICO_CEDULA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  ;
--------------------------------------------------------
--  DDL for Index CUENTO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."CUENTO_PK" ON "RESPWA1"."CUENTO" ("NOMBRE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index CUIDADOR_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."CUIDADOR_PK" ON "RESPWA1"."CUIDADOR" ("NOMBREUSUARIO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index DIFICULTAD_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."DIFICULTAD_PK" ON "RESPWA1"."DIFICULTAD" ("DIFICULTAD") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  ;
--------------------------------------------------------
--  DDL for Index ENRIQ_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."ENRIQ_PK" ON "RESPWA1"."ENRIQ" ("PARAMS") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  ;
--------------------------------------------------------
--  DDL for Index ESTADOCIVIL_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."ESTADOCIVIL_PK" ON "RESPWA1"."ESTADOCIVIL" ("TIPOEC") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index FAMILIARES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."FAMILIARES_PK" ON "RESPWA1"."FAMILIARES" ("PERFILPWA_CEDULA", "FAMILIAR_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index FAMILIAR_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."FAMILIAR_PK" ON "RESPWA1"."FAMILIAR" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index FRASES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."FRASES_PK" ON "RESPWA1"."FRASES" ("ORDEN", "CUENTO_NOMBRE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index GENERO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."GENERO_PK" ON "RESPWA1"."GENERO" ("GENERO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index LISTATAGS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."LISTATAGS_PK" ON "RESPWA1"."LISTATAGS" ("CANCION_NOMBRE", "TAGS_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index NIVEL_EDUCATIVO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."NIVEL_EDUCATIVO_PK" ON "RESPWA1"."NIVEL_EDUCATIVO" ("TIPONE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index PERFIL_MEDICO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."PERFIL_MEDICO_PK" ON "RESPWA1"."PERFIL_MEDICO" ("PERFILPWA_CEDULA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index PERFIL_PREFERENCIA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."PERFIL_PREFERENCIA_PK" ON "RESPWA1"."PERFIL_PREFERENCIA" ("PERFILPWA_CEDULA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index PERFILPWA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."PERFILPWA_PK" ON "RESPWA1"."PERFILPWA" ("CEDULA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index PREFERENCIACANCION_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."PREFERENCIACANCION_PK" ON "RESPWA1"."PREFERENCIACANCION" ("PERFIL_PREFERENCIA_PERFILPWA_CEDULA", "CANCION_NOMBRE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index PREFERENCIAXCUENTO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."PREFERENCIAXCUENTO_PK" ON "RESPWA1"."PREFERENCIAXCUENTO" ("PERFIL_PREFERENCIA_PERFILPWA_CEDULA", "CUENTO_NOMBRE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index REGISTROACTIVIDAD_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."REGISTROACTIVIDAD_PK" ON "RESPWA1"."REGISTROACTIVIDAD" ("FECHA", "TIPO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  ;
--------------------------------------------------------
--  DDL for Index TAGS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."TAGS_PK" ON "RESPWA1"."TAGS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index ACTIVIDADPWA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."ACTIVIDADPWA_PK" ON "RESPWA1"."ACTIVIDADPWA" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index ACTIVIDADRUTINARIA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."ACTIVIDADRUTINARIA_PK" ON "RESPWA1"."ACTIVIDADRUTINARIA" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  ;
--------------------------------------------------------
--  DDL for Index ACTXPREFERENCIA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."ACTXPREFERENCIA_PK" ON "RESPWA1"."ACTXPREFERENCIA" ("ACTIVIDADPWA_ID", "PERFIL_PREFERENCIA_CEDULA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index BOOKS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."BOOKS_PK" ON "RESPWA1"."BOOKS" ("BOOK_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index AUTHOR_TITLE_UNIQUE
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."AUTHOR_TITLE_UNIQUE" ON "RESPWA1"."BOOKS" ("AUTHOR_LAST_NAME", "TITLE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index CANCION_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."CANCION_PK" ON "RESPWA1"."CANCION" ("NOMBRE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index CAUSADEMENCIA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."CAUSADEMENCIA_PK" ON "RESPWA1"."CAUSADEMENCIA" ("CONDICION") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index CDR_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."CDR_PK" ON "RESPWA1"."CDR" ("PERFIL_MEDICO_CEDULA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  ;
--------------------------------------------------------
--  DDL for Index CUENTO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."CUENTO_PK" ON "RESPWA1"."CUENTO" ("NOMBRE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index CUIDADOR_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."CUIDADOR_PK" ON "RESPWA1"."CUIDADOR" ("NOMBREUSUARIO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index DIFICULTAD_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."DIFICULTAD_PK" ON "RESPWA1"."DIFICULTAD" ("DIFICULTAD") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  ;
--------------------------------------------------------
--  DDL for Index ENRIQ_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."ENRIQ_PK" ON "RESPWA1"."ENRIQ" ("PARAMS") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  ;
--------------------------------------------------------
--  DDL for Index ESTADOCIVIL_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."ESTADOCIVIL_PK" ON "RESPWA1"."ESTADOCIVIL" ("TIPOEC") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index FAMILIAR_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."FAMILIAR_PK" ON "RESPWA1"."FAMILIAR" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index FAMILIARES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."FAMILIARES_PK" ON "RESPWA1"."FAMILIARES" ("PERFILPWA_CEDULA", "FAMILIAR_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index FRASES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."FRASES_PK" ON "RESPWA1"."FRASES" ("ORDEN", "CUENTO_NOMBRE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index GENERO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."GENERO_PK" ON "RESPWA1"."GENERO" ("GENERO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index LISTATAGS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."LISTATAGS_PK" ON "RESPWA1"."LISTATAGS" ("CANCION_NOMBRE", "TAGS_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index NIVEL_EDUCATIVO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."NIVEL_EDUCATIVO_PK" ON "RESPWA1"."NIVEL_EDUCATIVO" ("TIPONE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index PERFIL_MEDICO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."PERFIL_MEDICO_PK" ON "RESPWA1"."PERFIL_MEDICO" ("PERFILPWA_CEDULA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index PERFIL_PREFERENCIA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."PERFIL_PREFERENCIA_PK" ON "RESPWA1"."PERFIL_PREFERENCIA" ("PERFILPWA_CEDULA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index PERFILPWA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."PERFILPWA_PK" ON "RESPWA1"."PERFILPWA" ("CEDULA") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index PREFERENCIACANCION_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."PREFERENCIACANCION_PK" ON "RESPWA1"."PREFERENCIACANCION" ("PERFIL_PREFERENCIA_PERFILPWA_CEDULA", "CANCION_NOMBRE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index PREFERENCIAXCUENTO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."PREFERENCIAXCUENTO_PK" ON "RESPWA1"."PREFERENCIAXCUENTO" ("PERFIL_PREFERENCIA_PERFILPWA_CEDULA", "CUENTO_NOMBRE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  DDL for Index REGISTROACTIVIDAD_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."REGISTROACTIVIDAD_PK" ON "RESPWA1"."REGISTROACTIVIDAD" ("FECHA", "TIPO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  ;
--------------------------------------------------------
--  DDL for Index TAGS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "RESPWA1"."TAGS_PK" ON "RESPWA1"."TAGS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ;
--------------------------------------------------------
--  Constraints for Table ACTIVIDADPWA
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."ACTIVIDADPWA" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."ACTIVIDADPWA" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."ACTIVIDADPWA" MODIFY ("TIPO" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."ACTIVIDADPWA" MODIFY ("DURACION" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."ACTIVIDADPWA" ADD CONSTRAINT "ACTIVIDADPWA_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
   ENABLE;
--------------------------------------------------------
--  Constraints for Table ACTIVIDADRUTINARIA
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."ACTIVIDADRUTINARIA" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."ACTIVIDADRUTINARIA" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."ACTIVIDADRUTINARIA" MODIFY ("DURACION" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."ACTIVIDADRUTINARIA" MODIFY ("HORA" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."ACTIVIDADRUTINARIA" ADD CONSTRAINT "ACTIVIDADRUTINARIA_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
   ENABLE;
--------------------------------------------------------
--  Constraints for Table ACTXPREFERENCIA
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."ACTXPREFERENCIA" MODIFY ("ACTIVIDADPWA_ID" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."ACTXPREFERENCIA" MODIFY ("PERFIL_PREFERENCIA_CEDULA" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."ACTXPREFERENCIA" MODIFY ("ACTIVA" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."ACTXPREFERENCIA" MODIFY ("GUSTO" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."ACTXPREFERENCIA" MODIFY ("ENRIQ" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."ACTXPREFERENCIA" ADD CONSTRAINT "ACTXPREFERENCIA_PK" PRIMARY KEY ("ACTIVIDADPWA_ID", "PERFIL_PREFERENCIA_CEDULA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
   ENABLE;
--------------------------------------------------------
--  Constraints for Table BOOKS
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."BOOKS" MODIFY ("TITLE" CONSTRAINT "TITLE_NOT_NULL" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."BOOKS" MODIFY ("AUTHOR_LAST_NAME" CONSTRAINT "LAST_NAME_NOT_NULL" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."BOOKS" ADD CONSTRAINT "RATING_1_TO_10" CHECK (rating IS NULL OR
      (rating >= 1 and rating <= 10)) ENABLE;
  ALTER TABLE "RESPWA1"."BOOKS" ADD CONSTRAINT "BOOKS_PK" PRIMARY KEY ("BOOK_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
   ENABLE;
  ALTER TABLE "RESPWA1"."BOOKS" ADD CONSTRAINT "AUTHOR_TITLE_UNIQUE" UNIQUE ("AUTHOR_LAST_NAME", "TITLE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
   ENABLE;
--------------------------------------------------------
--  Constraints for Table CANCION
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."CANCION" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."CANCION" MODIFY ("GUSTO" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."CANCION" MODIFY ("GENERO_GENERO" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."CANCION" ADD CONSTRAINT "CANCION_PK" PRIMARY KEY ("NOMBRE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
   ENABLE;
--------------------------------------------------------
--  Constraints for Table CAUSADEMENCIA
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."CAUSADEMENCIA" MODIFY ("CONDICION" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."CAUSADEMENCIA" ADD CONSTRAINT "CAUSADEMENCIA_PK" PRIMARY KEY ("CONDICION")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
   ENABLE;
--------------------------------------------------------
--  Constraints for Table CDR
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."CDR" MODIFY ("MEMORIA" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."CDR" MODIFY ("ORIENTACION" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."CDR" MODIFY ("JUICIO" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."CDR" MODIFY ("VIDA_SOCIAL" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."CDR" MODIFY ("HOGAR" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."CDR" MODIFY ("CUIDADOPERSONAL" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."CDR" MODIFY ("PERFIL_MEDICO_CEDULA" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."CDR" ADD CONSTRAINT "CDR_PK" PRIMARY KEY ("PERFIL_MEDICO_CEDULA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
   ENABLE;
--------------------------------------------------------
--  Constraints for Table CUENTO
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."CUENTO" MODIFY ("GENERO_GENERO" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."CUENTO" MODIFY ("AUTOR" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."CUENTO" MODIFY ("GUSTO" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."CUENTO" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."CUENTO" ADD CONSTRAINT "CUENTO_PK" PRIMARY KEY ("NOMBRE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
   ENABLE;
--------------------------------------------------------
--  Constraints for Table CUIDADOR
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."CUIDADOR" MODIFY ("NOMBREUSUARIO" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."CUIDADOR" MODIFY ("CONTRASEÑA" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."CUIDADOR" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."CUIDADOR" MODIFY ("CORREO" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."CUIDADOR" MODIFY ("CELULAR" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."CUIDADOR" ADD CONSTRAINT "CUIDADOR_PK" PRIMARY KEY ("NOMBREUSUARIO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
   ENABLE;
--------------------------------------------------------
--  Constraints for Table DIFICULTAD
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."DIFICULTAD" MODIFY ("DIFICULTAD" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."DIFICULTAD" ADD CONSTRAINT "DIFICULTAD_PK" PRIMARY KEY ("DIFICULTAD")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
   ENABLE;
--------------------------------------------------------
--  Constraints for Table ENRIQ
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."ENRIQ" MODIFY ("PARAMS" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."ENRIQ" MODIFY ("VALOR" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."ENRIQ" MODIFY ("CANCION_NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."ENRIQ" ADD CONSTRAINT "ENRIQ_PK" PRIMARY KEY ("PARAMS")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
   ENABLE;
--------------------------------------------------------
--  Constraints for Table ESTADOCIVIL
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."ESTADOCIVIL" MODIFY ("TIPOEC" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."ESTADOCIVIL" ADD CONSTRAINT "ESTADOCIVIL_PK" PRIMARY KEY ("TIPOEC")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
   ENABLE;
--------------------------------------------------------
--  Constraints for Table FAMILIAR
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."FAMILIAR" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."FAMILIAR" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."FAMILIAR" MODIFY ("PARENTESCO" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."FAMILIAR" MODIFY ("INTERES" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."FAMILIAR" MODIFY ("ESTAVIVO" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."FAMILIAR" ADD CONSTRAINT "FAMILIAR_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
   ENABLE;
--------------------------------------------------------
--  Constraints for Table FAMILIARES
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."FAMILIARES" MODIFY ("PERFILPWA_CEDULA" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."FAMILIARES" MODIFY ("FAMILIAR_ID" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."FAMILIARES" ADD CONSTRAINT "FAMILIARES_PK" PRIMARY KEY ("PERFILPWA_CEDULA", "FAMILIAR_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
   ENABLE;
--------------------------------------------------------
--  Constraints for Table FRASES
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."FRASES" MODIFY ("CONTENIDO" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."FRASES" MODIFY ("ORDEN" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."FRASES" MODIFY ("CUENTO_NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."FRASES" ADD CONSTRAINT "FRASES_PK" PRIMARY KEY ("ORDEN", "CUENTO_NOMBRE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
   ENABLE;
--------------------------------------------------------
--  Constraints for Table GENERO
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."GENERO" MODIFY ("GENERO" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."GENERO" MODIFY ("GUSTO" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."GENERO" ADD CONSTRAINT "GENERO_PK" PRIMARY KEY ("GENERO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
   ENABLE;
--------------------------------------------------------
--  Constraints for Table LISTATAGS
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."LISTATAGS" MODIFY ("CANCION_NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."LISTATAGS" MODIFY ("TAGS_ID" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."LISTATAGS" ADD CONSTRAINT "LISTATAGS_PK" PRIMARY KEY ("CANCION_NOMBRE", "TAGS_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
   ENABLE;
--------------------------------------------------------
--  Constraints for Table NIVEL_EDUCATIVO
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."NIVEL_EDUCATIVO" MODIFY ("TIPONE" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."NIVEL_EDUCATIVO" ADD CONSTRAINT "NIVEL_EDUCATIVO_PK" PRIMARY KEY ("TIPONE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
   ENABLE;
--------------------------------------------------------
--  Constraints for Table PERFIL_MEDICO
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."PERFIL_MEDICO" MODIFY ("PERFILPWA_CEDULA" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PERFIL_MEDICO" MODIFY ("TOMAMEDICAMENTOS" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PERFIL_MEDICO" MODIFY ("DISCAPAUDITIVA" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PERFIL_MEDICO" MODIFY ("DISCAPVISUAL" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PERFIL_MEDICO" MODIFY ("DISCAPMOTORA" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PERFIL_MEDICO" MODIFY ("ESTADIOENFERMEDAD" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PERFIL_MEDICO" MODIFY ("PERIODOVIGILIA" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PERFIL_MEDICO" ADD CONSTRAINT "PERFIL_MEDICO_PK" PRIMARY KEY ("PERFILPWA_CEDULA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
   ENABLE;
--------------------------------------------------------
--  Constraints for Table PERFIL_PREFERENCIA
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."PERFIL_PREFERENCIA" MODIFY ("PERFILPWA_CEDULA" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PERFIL_PREFERENCIA" MODIFY ("NOMBREPREFERIDO" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PERFIL_PREFERENCIA" MODIFY ("GUSTOKARAOKE" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PERFIL_PREFERENCIA" MODIFY ("GUSTOMUSICA" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PERFIL_PREFERENCIA" MODIFY ("GUSTOBAILE" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PERFIL_PREFERENCIA" MODIFY ("VOLPREFERIDO" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PERFIL_PREFERENCIA" ADD CONSTRAINT "PERFIL_PREFERENCIA_PK" PRIMARY KEY ("PERFILPWA_CEDULA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
   ENABLE;
--------------------------------------------------------
--  Constraints for Table PERFILPWA
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."PERFILPWA" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PERFILPWA" MODIFY ("APELLIDO" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PERFILPWA" MODIFY ("FECHANACIMIENTO" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PERFILPWA" MODIFY ("PAISNACIMIENTO" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PERFILPWA" MODIFY ("EDAD" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PERFILPWA" MODIFY ("CEDULA" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PERFILPWA" MODIFY ("PROFESION" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PERFILPWA" MODIFY ("CUIDADOR_NOMBREUSUARIO" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PERFILPWA" ADD CONSTRAINT "PERFILPWA_PK" PRIMARY KEY ("CEDULA")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
   ENABLE;
--------------------------------------------------------
--  Constraints for Table PREFERENCIACANCION
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."PREFERENCIACANCION" MODIFY ("PERFIL_PREFERENCIA_PERFILPWA_CEDULA" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PREFERENCIACANCION" MODIFY ("CANCION_NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PREFERENCIACANCION" ADD CONSTRAINT "PREFERENCIACANCION_PK" PRIMARY KEY ("PERFIL_PREFERENCIA_PERFILPWA_CEDULA", "CANCION_NOMBRE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
   ENABLE;
--------------------------------------------------------
--  Constraints for Table PREFERENCIAXCUENTO
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."PREFERENCIAXCUENTO" MODIFY ("PERFIL_PREFERENCIA_PERFILPWA_CEDULA" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PREFERENCIAXCUENTO" MODIFY ("CUENTO_NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."PREFERENCIAXCUENTO" ADD CONSTRAINT "PREFERENCIAXCUENTO_PK" PRIMARY KEY ("PERFIL_PREFERENCIA_PERFILPWA_CEDULA", "CUENTO_NOMBRE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
   ENABLE;
--------------------------------------------------------
--  Constraints for Table REGISTROACTIVIDAD
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."REGISTROACTIVIDAD" MODIFY ("FECHA" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."REGISTROACTIVIDAD" MODIFY ("ESTADOINICIAL" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."REGISTROACTIVIDAD" MODIFY ("ESTADOFINAL" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."REGISTROACTIVIDAD" MODIFY ("PERFILPWA_CEDULA" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."REGISTROACTIVIDAD" MODIFY ("TIPO" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."REGISTROACTIVIDAD" ADD CONSTRAINT "REGISTROACTIVIDAD_PK" PRIMARY KEY ("FECHA", "TIPO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
   ENABLE;
--------------------------------------------------------
--  Constraints for Table TAGS
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."TAGS" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."TAGS" MODIFY ("TAG" NOT NULL ENABLE);
  ALTER TABLE "RESPWA1"."TAGS" ADD CONSTRAINT "TAGS_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
   ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ACTIVIDADRUTINARIA
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."ACTIVIDADRUTINARIA" ADD CONSTRAINT "ACTIVIDADRUTINARIA_PERFIL_MEDICO_FK" FOREIGN KEY ("PERFIL_MEDICO_PERFILPWA_CEDULA")
	  REFERENCES "RESPWA1"."PERFIL_MEDICO" ("PERFILPWA_CEDULA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ACTXPREFERENCIA
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."ACTXPREFERENCIA" ADD CONSTRAINT "ACTXPREFERENCIA_ACTIVIDADPWA_FK" FOREIGN KEY ("ACTIVIDADPWA_ID")
	  REFERENCES "RESPWA1"."ACTIVIDADPWA" ("ID") ENABLE;
  ALTER TABLE "RESPWA1"."ACTXPREFERENCIA" ADD CONSTRAINT "ACTXPREFERENCIA_DIFICULTAD_FK" FOREIGN KEY ("DIFICULTAD_DIFICULTAD")
	  REFERENCES "RESPWA1"."DIFICULTAD" ("DIFICULTAD") ENABLE;
  ALTER TABLE "RESPWA1"."ACTXPREFERENCIA" ADD CONSTRAINT "ACTXPREFERENCIA_PERFIL_PREFERENCIA_FK" FOREIGN KEY ("PERFIL_PREFERENCIA_CEDULA")
	  REFERENCES "RESPWA1"."PERFIL_PREFERENCIA" ("PERFILPWA_CEDULA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CANCION
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."CANCION" ADD CONSTRAINT "CANCION_GENERO_FK" FOREIGN KEY ("GENERO_GENERO")
	  REFERENCES "RESPWA1"."GENERO" ("GENERO") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CDR
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."CDR" ADD CONSTRAINT "CDR_PERFIL_MEDICO_FK" FOREIGN KEY ("PERFIL_MEDICO_CEDULA")
	  REFERENCES "RESPWA1"."PERFIL_MEDICO" ("PERFILPWA_CEDULA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CUENTO
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."CUENTO" ADD CONSTRAINT "CUENTO_GENERO_FK" FOREIGN KEY ("GENERO_GENERO")
	  REFERENCES "RESPWA1"."GENERO" ("GENERO") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ENRIQ
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."ENRIQ" ADD CONSTRAINT "ENRIQ_CANCION_FK" FOREIGN KEY ("CANCION_NOMBRE")
	  REFERENCES "RESPWA1"."CANCION" ("NOMBRE") ENABLE;
  ALTER TABLE "RESPWA1"."ENRIQ" ADD CONSTRAINT "ENRIQ_FRASES_FK" FOREIGN KEY ("FRASES_ORDEN", "FRASES_NOMBRE")
	  REFERENCES "RESPWA1"."FRASES" ("ORDEN", "CUENTO_NOMBRE") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FAMILIARES
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."FAMILIARES" ADD CONSTRAINT "FAMILIARES_FAMILIAR_FK" FOREIGN KEY ("FAMILIAR_ID")
	  REFERENCES "RESPWA1"."FAMILIAR" ("ID") ENABLE;
  ALTER TABLE "RESPWA1"."FAMILIARES" ADD CONSTRAINT "FAMILIARES_PERFILPWA_FK" FOREIGN KEY ("PERFILPWA_CEDULA")
	  REFERENCES "RESPWA1"."PERFILPWA" ("CEDULA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FRASES
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."FRASES" ADD CONSTRAINT "FRASES_CUENTO_FK" FOREIGN KEY ("CUENTO_NOMBRE")
	  REFERENCES "RESPWA1"."CUENTO" ("NOMBRE") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table LISTATAGS
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."LISTATAGS" ADD CONSTRAINT "LISTATAGS_CANCION_FK" FOREIGN KEY ("CANCION_NOMBRE")
	  REFERENCES "RESPWA1"."CANCION" ("NOMBRE") ENABLE;
  ALTER TABLE "RESPWA1"."LISTATAGS" ADD CONSTRAINT "LISTATAGS_TAGS_FK" FOREIGN KEY ("TAGS_ID")
	  REFERENCES "RESPWA1"."TAGS" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PERFIL_MEDICO
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."PERFIL_MEDICO" ADD CONSTRAINT "PERFIL_MEDICO_CAUSADEMENCIA_FK" FOREIGN KEY ("CAUSADEMENCIA_CONDICION")
	  REFERENCES "RESPWA1"."CAUSADEMENCIA" ("CONDICION") ENABLE;
  ALTER TABLE "RESPWA1"."PERFIL_MEDICO" ADD CONSTRAINT "PERFIL_MEDICO_PERFILPWA_FK" FOREIGN KEY ("PERFILPWA_CEDULA")
	  REFERENCES "RESPWA1"."PERFILPWA" ("CEDULA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PERFIL_PREFERENCIA
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."PERFIL_PREFERENCIA" ADD CONSTRAINT "PERFIL_PREFERENCIA_PERFILPWA_FK" FOREIGN KEY ("PERFILPWA_CEDULA")
	  REFERENCES "RESPWA1"."PERFILPWA" ("CEDULA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PERFILPWA
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."PERFILPWA" ADD CONSTRAINT "PERFILPWA_CUIDADOR_FK" FOREIGN KEY ("CUIDADOR_NOMBREUSUARIO")
	  REFERENCES "RESPWA1"."CUIDADOR" ("NOMBREUSUARIO") ENABLE;
  ALTER TABLE "RESPWA1"."PERFILPWA" ADD CONSTRAINT "PERFILPWA_ESTADOCIVIL_FK" FOREIGN KEY ("ESTADOCIVIL_TIPOEC")
	  REFERENCES "RESPWA1"."ESTADOCIVIL" ("TIPOEC") ENABLE;
  ALTER TABLE "RESPWA1"."PERFILPWA" ADD CONSTRAINT "PERFILPWA_NIVEL_EDUCATIVO_FK" FOREIGN KEY ("NIVEL_EDUCATIVO_TIPONE")
	  REFERENCES "RESPWA1"."NIVEL_EDUCATIVO" ("TIPONE") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PREFERENCIACANCION
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."PREFERENCIACANCION" ADD CONSTRAINT "PREFERENCIACANCION_CANCION_FK" FOREIGN KEY ("CANCION_NOMBRE")
	  REFERENCES "RESPWA1"."CANCION" ("NOMBRE") ENABLE;
  ALTER TABLE "RESPWA1"."PREFERENCIACANCION" ADD CONSTRAINT "PREFERENCIACANCION_PERFIL_PREFERENCIA_FK" FOREIGN KEY ("PERFIL_PREFERENCIA_PERFILPWA_CEDULA")
	  REFERENCES "RESPWA1"."PERFIL_PREFERENCIA" ("PERFILPWA_CEDULA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PREFERENCIAXCUENTO
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."PREFERENCIAXCUENTO" ADD CONSTRAINT "PREFERENCIAXCUENTO_CUENTO_FK" FOREIGN KEY ("CUENTO_NOMBRE")
	  REFERENCES "RESPWA1"."CUENTO" ("NOMBRE") ENABLE;
  ALTER TABLE "RESPWA1"."PREFERENCIAXCUENTO" ADD CONSTRAINT "PREFERENCIAXCUENTO_PERFIL_PREFERENCIA_FK" FOREIGN KEY ("PERFIL_PREFERENCIA_PERFILPWA_CEDULA")
	  REFERENCES "RESPWA1"."PERFIL_PREFERENCIA" ("PERFILPWA_CEDULA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table REGISTROACTIVIDAD
--------------------------------------------------------

  ALTER TABLE "RESPWA1"."REGISTROACTIVIDAD" ADD CONSTRAINT "REGISTROACTIVIDAD_ACTIVIDADPWA_FK" FOREIGN KEY ("ACTIVIDADPWA_ID")
	  REFERENCES "RESPWA1"."ACTIVIDADPWA" ("ID") ENABLE;
  ALTER TABLE "RESPWA1"."REGISTROACTIVIDAD" ADD CONSTRAINT "REGISTROACTIVIDAD_PERFILPWA_FK" FOREIGN KEY ("PERFILPWA_CEDULA")
	  REFERENCES "RESPWA1"."PERFILPWA" ("CEDULA") ENABLE;
