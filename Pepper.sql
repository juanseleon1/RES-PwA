INSERT INTO EstadoCivil (tipoEC) VALUES ( 'Soltero');
INSERT INTO EstadoCivil (tipoEC) VALUES ( 'Casado');
INSERT INTO EstadoCivil (tipoEC) VALUES ( 'Divorciado');
INSERT INTO EstadoCivil (tipoEC) VALUES ( 'Viudo');

INSERT INTO Nivel_Educativo (tipoNE) VALUES( 'Primaria');
INSERT INTO Nivel_Educativo (tipoNE) VALUES( 'Bachiller');
INSERT INTO Nivel_Educativo (tipoNE) VALUES( 'Universitario');
INSERT INTO Nivel_Educativo (tipoNE) VALUES( 'Tecnologia');
INSERT INTO Nivel_Educativo (tipoNE) VALUES( 'Master');
INSERT INTO Nivel_Educativo (tipoNE) VALUES( 'Doctorado');

INSERT INTO Cuidador (nombreUsuario,contrasena,Nombre ,correo,celular) VALUES( 'juleon',12345, 'Juan Leon', 'juleon@javeriana.edu.co',3204094111);

INSERT INTO PerfilPwa (nombre,apellido,fechaNacimiento,paisNacimiento,edad,cedula,profesion,EstadoCivil_tipoEC,Nivel_Educativo_tipoNE,Cuidador_nombreUsuario) VALUES( 'Juanita', 'Caballero',TO_DATE('17/05/1940', 'dd/mm/yyyy'), 'Colombia',80,123456789, 'Ingeniera industrial', 'Casado', 'Doctorado', 'juleon');


INSERT INTO CausaDemencia (condicion) VALUES ( 'Parkinson');
INSERT INTO CausaDemencia (condicion) VALUES ( 'Alzheimer');


INSERT INTO Perfil_Medico (PerfilPwa_cedula,tomaMedicamentos,discapAuditiva,discapVisual,discapMotora,estadioEnfermedad,periodoVigilia,causaDemencia_condicion,FAST) VALUES(123456789,1,0,1,0,5,60, 'Alzheimer',4);

INSERT INTO Perfil_Preferencia (PerfilPwa_cedula,nombrePreferido,gustoKaraoke,gustoMusica,gustoBaile,volPreferido,BRILLOPREFERIDO) VALUES(123456789, 'Juanis',0.7,1,1,50,40);

INSERT INTO Genero (genero) VALUES( 'Vallenato');
INSERT INTO Genero (genero) VALUES( 'Carranga');
INSERT INTO Genero (genero) VALUES( 'Pop');
INSERT INTO Genero (genero) VALUES( 'Latin');

INSERT INTO Cancion (nombre,Genero_genero,url) VALUES( 'Mi cacharrito', 'Latin', 'http://10.195.22.103:49152/content/media/object_id/22/res_id/0');
INSERT INTO Cancion (nombre,Genero_genero,url) VALUES( 'Mi querido viejo', 'Pop', 'http://10.195.22.103:49152/content/media/object_id/48/res_id/0');

INSERT INTO PreferenciaXCancion (Perfil_Preferencia_PerfilPwa_cedula,Cancion_nombre,gusto,reminiscencia) VALUES(123456789, 'Mi cacharrito',0.8,1);
INSERT INTO PreferenciaXCancion (Perfil_Preferencia_PerfilPwa_cedula,Cancion_nombre,gusto,reminiscencia) VALUES(123456789, 'Mi querido viejo',0.5,1);

INSERT INTO Genero (genero) VALUES('Literatura Infantil');
INSERT INTO Cuento (Genero_genero,Autor,nombre) VALUES( 'Literatura Infantil', 'Charles Perrault', 'Caperucita roja');
INSERT INTO Cuento (Genero_genero,Autor,nombre) VALUES( 'Literatura Infantil', 'Jon Scieszka', 'Los 3 cerditos');

INSERT INTO PreferenciaXCuento (Perfil_Preferencia_PerfilPwa_cedula,Cuento_nombre,gusto) VALUES(123456789, 'Caperucita roja',0.3);
INSERT INTO PreferenciaXCuento (Perfil_Preferencia_PerfilPwa_cedula,Cuento_nombre,gusto) VALUES(123456789, 'Los 3 cerditos',0.6);

INSERT INTO Tags (id,tag) VALUES(0, 'Familia');
INSERT INTO Tags (id,tag) VALUES(1, 'Matrimonio');
INSERT INTO Tags (id,tag) VALUES(2, 'Cumpleaños');
INSERT INTO Tags (id,tag) VALUES(3, 'Amigos');
INSERT INTO Tags (id,tag) VALUES(4, 'Graduacion');

INSERT INTO listaTags (Cancion_nombre,Tags_id) VALUES( 'Mi cacharrito',0);
INSERT INTO listaTags (Cancion_nombre,Tags_id) VALUES( 'Mi cacharrito',2);
INSERT INTO listaTags (Cancion_nombre,Tags_id) VALUES( 'Mi querido viejo',0);
INSERT INTO listaTags (Cancion_nombre,Tags_id) VALUES( 'Mi querido viejo',3);

INSERT INTO ActividadPwA (id,nombre,tipo,duracion) VALUES(0, 'MUSICOTERAPIA', 'Entretenimiento',60);
INSERT INTO ActividadPwA (id,nombre,tipo,duracion) VALUES(1, 'CUENTERIA', 'Entretenimiento',60);


INSERT INTO actXPreferencia (ActividadPwA_id,Perfil_Preferencia_cedula,activa,gusto,enriq) VALUES(0,123456789,1,0.8,5);
INSERT INTO actXPreferencia (ActividadPwA_id,Perfil_Preferencia_cedula,activa,gusto,enriq) VALUES(1,123456789,1,0.5,2);


INSERT INTO Familiar (id,nombre,parentesco,interes,estaVivo,nacimiento) VALUES(0, 'Olga', 'Hermana',1,0,TO_DATE('10/12/1945', 'dd/mm/yyyy'));
INSERT INTO Familiar (id,nombre,parentesco,interes,estaVivo,nacimiento) VALUES(1, 'Eugenio', 'Esposo',1,1,TO_DATE('05/03/1940', 'dd/mm/yyyy'));
INSERT INTO Familiar (id,nombre,parentesco,interes,estaVivo,nacimiento) VALUES(2, 'Jorge', 'Hijo',1,1,TO_DATE('25/07/1970', 'dd/mm/yyyy'));
INSERT INTO Familiar (id,nombre,parentesco,interes,estaVivo,nacimiento) VALUES(3, 'Cecilia', 'Hija',1,1,TO_DATE('20/09/1972', 'dd/mm/yyyy'));

INSERT INTO familiares (PerfilPwa_cedula,Familiar_id) VALUES(123456789,0);
INSERT INTO familiares (PerfilPwa_cedula,Familiar_id) VALUES(123456789,1);
INSERT INTO familiares (PerfilPwa_cedula,Familiar_id) VALUES(123456789,2);
INSERT INTO familiares (PerfilPwa_cedula,Familiar_id) VALUES(123456789,3);

INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Había una vez tres hermanos cerditos que vivían en el bosque',1, 'Los 3 cerditos', ' ', ' ', 'http://10.195.22.103:49152/content/media/object_id/42/res_id/0');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Como el malvado lobo siempre los estaba persiguiendo para comérselos dijo un día el mayor:',2, 'Los 3 cerditos','LOBO_PERSEGUIR_CERDO', 'FRAGCONVLEFT', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Tenemos que hacer una casa para protegernos de lobo',3, 'Los 3 cerditos', ' ', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Así podremos escondernos dentro de ella cada vez que el lobo aparezca por aquí',4, 'Los 3 cerditos','CERDO_SONAR_CASA', 'FRAGCONVRIGHT', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'A los otros dos les pareció muy buena idea',5, 'Los 3 cerditos', ' ', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'pero no se ponían de acuerdo respecto a qué material utilizar',6, 'Los 3 cerditos', ' ', 'FRAGCONVLEFT', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Al final, y para no discutir, decidieron que cada uno la hiciera de lo que quisiese',7, 'Los 3 cerditos', ' ', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'El más pequeño optó por utilizar paja, para no tardar mucho y poder irse a jugar después',8, 'Los 3 cerditos','CERDO_CONSTRUIR_CASA', 'BUILD', 'http://10.195.22.103:49152/content/media/object_id/45/res_id/0');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'El mediano prefirió construirla de madera',9, 'Los 3 cerditos','CERDO_CONSTRUIR_CASA', ' ', 'http://10.195.22.103:49152/content/media/object_id/63/res_id/0');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'que era más resistente que la paja y tampoco le llevaría mucho tiempo hacerla',10, 'Los 3 cerditos', ' ', 'FRAGCONVRIGHT', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Pero el mayor pensó que aunque tardara más que sus hermanos',11, 'Los 3 cerditos', ' ', ' ', 'http://10.195.22.103:49152/content/media/object_id/17/res_id/0');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'lo mejor era hacer una casa resistente y fuerte con ladrillos',12, 'Los 3 cerditos', ' ', 'BUILD', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Además así podré hacer una chimenea con la que calentarme en invierno, pensó el cerdito',13, 'Los 3 cerditos','CERDO_CONSTRUIR_CASA', 'FRAGCONVLEFT', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Cuando los tres acabaron sus casas se metieron cada uno en la suya',14, 'Los 3 cerditos', ' ', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'y entonces apareció por ahí el malvado lobo',15, 'Los 3 cerditos','LOBO_APARECER_NULL', 'FEROCIOUS', 'http://10.195.22.103:49152/content/media/object_id/60/res_id/0');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Se dirigió a la de paja y llamó a la puerta:',16, 'Los 3 cerditos', ' ', 'TOCTOC', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Anda cerdito se bueno y déjame entrar',17, 'Los 3 cerditos', ' ', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( '¡No! ¡Eso ni pensarlo!',18, 'Los 3 cerditos', ' ', 'FRAGCONVRIGHT', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( '¡Pues soplaré y soplaré y la casita derribaré!',19, 'Los 3 cerditos', ' ', ' ', 'http://10.195.22.103:49152/content/media/object_id/30/res_id/0');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Y el lobo empezó a soplar y a estornudar, la débil casa acabó viniéndose abajo',20, 'Los 3 cerditos','LOBO_DESTRUIR_CASA', 'BLOW', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Pero el cerdito echó a correr y se refugió en la casa de su hermano mediano',21, 'Los 3 cerditos','CERDO_REFUGIARSE_NULL', 'WALK', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'que estaba hecha de madera',22, 'Los 3 cerditos', ' ', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Anda cerditos sed buenos y dejarme entrar',23, 'Los 3 cerditos', ' ', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( '¡No! ¡Eso ni pensarlo!, dijeron los dos',24, 'Los 3 cerditos', ' ', 'COMPLETECONV', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( '¡Pues soplaré y soplaré y la casita derribaré!',25, 'Los 3 cerditos', ' ', ' ', 'http://10.195.22.103:49152/content/media/object_id/36/res_id/0');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'El lobo empezó a soplar y a estornudar',26, 'Los 3 cerditos', ' ', 'BLOW', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'aunque esta vez tuvo que hacer más esfuerzos para derribar la casa al final la madera acabó cediendo',27, 'Los 3 cerditos','LOBO_DESTRUIR_CASA', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'y los cerditos salieron corriendo en dirección hacia la casa de su hermano mayor',28, 'Los 3 cerditos','CERDO_REFUGIARSE_NULL', 'WALK', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'El lobo estaba cada vez más hambriento así que sopló y sopló con todas sus fuerzas',29, 'Los 3 cerditos', ' ', 'BLOW', 'http://10.195.22.103:49152/content/media/object_id/14/res_id/0');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'pero esta vez no tenía nada que hacer porque la casa no se movía ni siquiera un poco',30, 'Los 3 cerditos', ' ', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Dentro los cerditos celebraban la resistencia de la casa de su hermano',31, 'Los 3 cerditos','CERDO_CELEBRAR_EXITO', 'CELEBRATE', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'y cantaban alegres por haberse librado del lobo:',32, 'Los 3 cerditos', ' ', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( '¿Quien teme al lobo feroz? ¡No, no, no!',33, 'Los 3 cerditos','CERDO_VITOREAR_NULL', 'QUESTION', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Fuera el lobo continuaba soplando en vano, cada vez más enfadado',34, 'Los 3 cerditos', ' ', 'BLOW', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Hasta que decidió parar para descansar y entonces reparó en que la casa tenía una chimenea',35, 'Los 3 cerditos', ' ', ' ', 'http://10.195.22.103:49152/content/media/object_id/27/res_id/0');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( '¡Ja! ¡Pensaban que de mí iban a librarse! ¡Subiré por la chimenea y me los comeré a los tres!',36, 'Los 3 cerditos','LOBO_PERSEGUIR_CERDO', 'FRAGCONVLEFT', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Pero los cerditos le oyeron, y para darle su merecido llenaron la chimenea de leña',37, 'Los 3 cerditos', ' ', ' ', 'http://10.195.22.103:49152/content/media/object_id/33/res_id/0');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'y pusieron al fuego un gran caldero con agua',38, 'Los 3 cerditos', ' ', 'COMPLETECONV', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Así cuando el lobo cayó por la chimenea el agua estaba hirviendo',39, 'Los 3 cerditos','LOBO_QUEMAR_COLA', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'se quemó tanto que salió gritando de la casa y no volvió a comer cerditos en una larga temporada',40, 'Los 3 cerditos','LOBO_HUIR_NULL', 'FRAGCONVRIGHT', ' ');
commit;

INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Había una vez tres hermanos cerditos que vivían en el bosque',1, 'Los 3 cerditos', ' ', ' ', 'http://10.195.22.103:49152/content/media/object_id/42/res_id/0');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Como el malvado lobo siempre los estaba persiguiendo para comérselos dijo un día el mayor:',2, 'Los 3 cerditos','LOBO_PERSEGUIR_CERDO', 'FRAGCONVLEFT', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Tenemos que hacer una casa para protegernos de lobo',3, 'Los 3 cerditos', ' ', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Así podremos escondernos dentro de ella cada vez que el lobo aparezca por aquí',4, 'Los 3 cerditos','CERDO_SONAR_CASA', 'FRAGCONVRIGHT', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'A los otros dos les pareció muy buena idea',5, 'Los 3 cerditos', ' ', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'pero no se ponían de acuerdo respecto a qué material utilizar',6, 'Los 3 cerditos', ' ', 'FRAGCONVLEFT', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Al final, y para no discutir, decidieron que cada uno la hiciera de lo que quisiese',7, 'Los 3 cerditos', ' ', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'El más pequeño optó por utilizar paja, para no tardar mucho y poder irse a jugar después',8, 'Los 3 cerditos','CERDO_CONSTRUIR_CASA', 'BUILD', 'http://10.195.22.103:49152/content/media/object_id/45/res_id/0');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'El mediano prefirió construirla de madera',9, 'Los 3 cerditos','CERDO_CONSTRUIR_CASA', ' ', 'http://10.195.22.103:49152/content/media/object_id/63/res_id/0');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'que era más resistente que la paja y tampoco le llevaría mucho tiempo hacerla',10, 'Los 3 cerditos', ' ', 'FRAGCONVRIGHT', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Pero el mayor pensó que aunque tardara más que sus hermanos',11, 'Los 3 cerditos', ' ', ' ', 'http://10.195.22.103:49152/content/media/object_id/17/res_id/0');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'lo mejor era hacer una casa resistente y fuerte con ladrillos',12, 'Los 3 cerditos', ' ', 'BUILD', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Además así podré hacer una chimenea con la que calentarme en invierno, pensó el cerdito',13, 'Los 3 cerditos','CERDO_CONSTRUIR_CASA', 'FRAGCONVLEFT', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Cuando los tres acabaron sus casas se metieron cada uno en la suya',14, 'Los 3 cerditos', ' ', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'y entonces apareció por ahí el malvado lobo',15, 'Los 3 cerditos','LOBO_APARECER_NULL', 'FEROCIOUS', 'http://10.195.22.103:49152/content/media/object_id/60/res_id/0');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Se dirigió a la de paja y llamó a la puerta:',16, 'Los 3 cerditos', ' ', 'TOCTOC', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Anda cerdito se bueno y déjame entrar',17, 'Los 3 cerditos', ' ', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( '¡No! ¡Eso ni pensarlo!',18, 'Los 3 cerditos', ' ', 'FRAGCONVRIGHT', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( '¡Pues soplaré y soplaré y la casita derribaré!',19, 'Los 3 cerditos', ' ', ' ', 'http://10.195.22.103:49152/content/media/object_id/30/res_id/0');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Y el lobo empezó a soplar y a estornudar, la débil casa acabó viniéndose abajo',20, 'Los 3 cerditos','LOBO_DESTRUIR_CASA', 'BLOW', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Pero el cerdito echó a correr y se refugió en la casa de su hermano mediano',21, 'Los 3 cerditos','CERDO_REFUGIARSE_NULL', 'WALK', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'que estaba hecha de madera',22, 'Los 3 cerditos', ' ', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Anda cerditos sed buenos y dejarme entrar',23, 'Los 3 cerditos', ' ', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( '¡No! ¡Eso ni pensarlo!, dijeron los dos',24, 'Los 3 cerditos', ' ', 'COMPLETECONV', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( '¡Pues soplaré y soplaré y la casita derribaré!',25, 'Los 3 cerditos', ' ', ' ', 'http://10.195.22.103:49152/content/media/object_id/36/res_id/0');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'El lobo empezó a soplar y a estornudar',26, 'Los 3 cerditos', ' ', 'BLOW', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'aunque esta vez tuvo que hacer más esfuerzos para derribar la casa al final la madera acabó cediendo',27, 'Los 3 cerditos','LOBO_DESTRUIR_CASA', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'y los cerditos salieron corriendo en dirección hacia la casa de su hermano mayor',28, 'Los 3 cerditos','CERDO_REFUGIARSE_NULL', 'WALK', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'El lobo estaba cada vez más hambriento así que sopló y sopló con todas sus fuerzas',29, 'Los 3 cerditos', ' ', 'BLOW', 'http://10.195.22.103:49152/content/media/object_id/14/res_id/0');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'pero esta vez no tenía nada que hacer porque la casa no se movía ni siquiera un poco',30, 'Los 3 cerditos', ' ', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Dentro los cerditos celebraban la resistencia de la casa de su hermano',31, 'Los 3 cerditos','CERDO_CELEBRAR_EXITO', 'CELEBRATE', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'y cantaban alegres por haberse librado del lobo:',32, 'Los 3 cerditos', ' ', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( '¿Quien teme al lobo feroz? ¡No, no, no!',33, 'Los 3 cerditos','CERDO_VITOREAR_NULL', 'QUESTION', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Fuera el lobo continuaba soplando en vano, cada vez más enfadado',34, 'Los 3 cerditos', ' ', 'BLOW', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Hasta que decidió parar para descansar y entonces reparó en que la casa tenía una chimenea',35, 'Los 3 cerditos', ' ', ' ', 'http://10.195.22.103:49152/content/media/object_id/27/res_id/0');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( '¡Ja! ¡Pensaban que de mí iban a librarse! ¡Subiré por la chimenea y me los comeré a los tres!',36, 'Los 3 cerditos','LOBO_PERSEGUIR_CERDO', 'FRAGCONVLEFT', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Pero los cerditos le oyeron, y para darle su merecido llenaron la chimenea de leña',37, 'Los 3 cerditos', ' ', ' ', 'http://10.195.22.103:49152/content/media/object_id/33/res_id/0');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'y pusieron al fuego un gran caldero con agua',38, 'Los 3 cerditos', ' ', 'COMPLETECONV', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'Así cuando el lobo cayó por la chimenea el agua estaba hirviendo',39, 'Los 3 cerditos','LOBO_QUEMAR_COLA', ' ', ' ');
INSERT INTO Frases (contenido,orden,Cuento_nombre,emotionalEvent,accion,urlImagen) VALUES( 'se quemó tanto que salió gritando de la casa y no volvió a comer cerditos en una larga temporada',40, 'Los 3 cerditos','LOBO_HUIR_NULL', 'FRAGCONVRIGHT', ' ');

INSERT INTO Robot (id,nombre) VALUES (0, 'pepper');

INSERT INTO Emocion (id,emotionalTag,robot_id,imagen) VALUES (0, 'VHAPPY',0, 'http://10.195.22.103:49152/content/media/object_id/84/res_id/0'); 
INSERT INTO Emocion (id,emotionalTag,robot_id,imagen) VALUES (1, 'HAPPY',0, 'http://10.195.22.103:49152/content/media/object_id/75/res_id/0'); 
INSERT INTO Emocion (id,emotionalTag,robot_id,imagen) VALUES (2, 'NORMAL',0, 'http://10.195.22.103:49152/content/media/object_id/78/res_id/0'); 
INSERT INTO Emocion (id,emotionalTag,robot_id,imagen) VALUES (3, 'SAD',0, 'http://10.195.22.103:49152/content/media/object_id/81/res_id/0'); 
INSERT INTO Emocion (id,emotionalTag,robot_id,imagen) VALUES (4, 'VSAD',0,  'http://10.195.22.103:49152/content/media/object_id/72/res_id/0'); 

INSERT INTO Accion (id,nombre,Emocion_id,tipo) VALUES (0, 'POSTURA',4, 'postura'); 
INSERT INTO Accion (id,nombre,Emocion_id,tipo) VALUES (1, 'POSTURA',0, 'postura'); 
INSERT INTO Accion (id,nombre,Emocion_id,tipo) VALUES (4, 'POSTURA',3, 'postura'); 
INSERT INTO Accion (id,nombre,Emocion_id,tipo) VALUES (5, 'POSTURA',1, 'postura'); 
INSERT INTO Accion (id,nombre,Emocion_id,tipo) VALUES (6, 'POSTURA',2, 'postura'); 



INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (2, 'HipPitch',-0.726057,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (3, 'HipRoll',-0.00359941,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (4, 'KneePitch',-0.00905603,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (5, 'LElbowRoll',-0.110514,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (6, 'LElbowYaw',-1.71537,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (7, 'LHand',0.6942,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (8, 'LShoulderPitch',1.20428,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (9, 'LShoulderRoll',0.113541,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (10, 'LWristYaw',0.0345809,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (11, 'RElbowRoll',0.0923345,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (12, 'RElbowYaw',1.68466,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (13, 'RHand',0.688049,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (14, 'RShoulderPitch',1.20446,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (15, 'RShoulderRoll',-0.117913,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (16, 'RWristYaw',-0.0225847,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (19, 'HipPitch',0.0733038,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (20, 'HipRoll',0,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (21, 'KneePitch',0,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (22, 'LElbowRoll',-1.22173,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (23, 'LElbowYaw',-1.71042,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (24, 'LHand',0.7,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (25, 'LShoulderPitch',1.51669,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (26, 'LShoulderRoll',0.10821,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (27, 'LWristYaw',0.0349066,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (28, 'RElbowRoll',1.22173,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (29, 'RElbowYaw',1.67552,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (30, 'RHand',0.7,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (31, 'RShoulderPitch',1.53589,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (32, 'RShoulderRoll',-0.115192,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (33, 'RWristYaw',-0.0244346,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (34, 'LElbowRoll',-1.22173,0.36); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (35, 'LShoulderPitch',1.02974,0.36); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (36, 'LShoulderPitch',2.08567,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (37, 'LShoulderPitch',1.02974,1.16); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (38, 'LShoulderPitch',2.08567,1.56); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (39, 'RElbowRoll',1.22173,0.36); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (40, 'RShoulderPitch',2.74312,0.36); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (41, 'RShoulderPitch',1.02974,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (42, 'RShoulderPitch',2.74312,1.16); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (43, 'RShoulderPitch',1.02974,1.56); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (44, 'LElbowRoll',-0.698132,0.36); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (45, 'RElbowRoll',0.698132,0.36); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (47, 'HipPitch',-0.436332,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (48, 'LShoulderPitch',1.309,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (49, 'RShoulderPitch',1.309,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (51, 'HipPitch',0.0349066,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (52, 'LElbowRoll',-0.959931,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (53, 'RElbowRoll',0.959931,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (55, 'HipPitch',0,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (56, 'LElbowRoll',-0.349066,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (57, 'RElbowRoll',0.349066,0.76);


INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (0,0); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (0,1); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (0,2); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (0,3); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (0,4); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (0,5); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (0,6); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (0,7); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (0,8); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (0,9); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (0,10); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (0,11); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (0,12); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (0,13); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (0,14); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (0,15); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (0,16); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (1,17); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (1,18); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (1,19); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (1,20); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (1,21); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (1,22); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (1,23); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (1,24); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (1,25); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (1,26); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (1,27); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (1,28); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (1,29); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (1,30); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (1,31); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (1,32); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (1,33); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (2,34); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (2,35); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (2,36); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (2,37); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (2,38); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (2,39); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (2,40); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (2,41); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (2,42); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (2,43); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (3,35); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (3,36); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (3,37); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (3,38); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (3,40); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (3,41); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (3,42); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (3,43); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (3,44); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (3,45); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (4,1); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (4,3); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (4,4); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (4,5); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (4,6); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (4,7); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (4,9); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (4,10); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (4,11); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (4,12); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (4,13); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (4,15); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (4,16); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (4,46); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (4,47); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (4,48); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (4,49); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (5,18); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (5,20); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (5,21); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (5,23); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (5,24); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (5,25); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (5,26); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (5,27); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (5,29); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (5,30); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (5,31); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (5,32); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (5,33); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (5,50); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (5,51); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (5,52); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (5,53); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (6,18); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (6,20); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (6,21); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (6,23); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (6,24); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (6,25); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (6,26); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (6,27); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (6,29); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (6,30); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (6,31); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (6,32); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (6,33); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (6,54); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (6,55); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (6,56); 
INSERT INTO AccionxJoint (Accion_id,Joint_id) VALUES (6,57); 


Insert into emotionaxisconfig values (0,0.3, 0.2,'Tristeza', 'Felicidad' );

INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (0,0.7,'INTERNALPOSPLUS',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (1,0.3,'INTERNALPOS',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (2,0.3,'INTERNALNEG',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (3,0.7,'INTERNALNEGPLUS',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (4,0.4,'SPOKE',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (5,0.4,'NOTLOOKING',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (6,0.1,'NOTDETECTED',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (7,0.3,'NORESPONSE',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (8,0.5,'MOVEDAWAY',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (9,0.5,'APPROACHED',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (10,0.2,'SMILING',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (11,0.4,'POSVOICEEMOTION',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (12,0.4,'NEGVOICEEMOTION',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (13,0.6,'POSEMOSTATE',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (14,0.6,'NEGEMOSTATE',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (15,0.4,'SPEECHDETECTED',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (16,0,6,'ENFERMAR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (17,0,6,'ALEGRARSE',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (18,0,4,'ASUSTAR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (19,0,4,'VER',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (20,0,5,'EMOCIONAR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (21,0,8,'DEVORAR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (22,0,8,'ESCAPAR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (23,0,5,'HUIR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (24,0,6,'ABRAZAR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (25,0,3,'LLEVAR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (26,0,3,'OBSERVAR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (27,0,5,'HABLAR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (28,0,5,'AMENAZAR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (29,0,5,'APRECIAR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (30,0,6,'ENGANAR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (31,0,7,'TRAICIONAR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (32,0,5,'SOSPECHAR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (33,0,5,'SORPRENDER',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (34,0,3,'GRITAR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (35,0,5,'PERSEGUIR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (36,0,4,'SONAR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (37,0,5,'CONSTRUIR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (38,0,4,'APARECER',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (39,0,7,'DESTRUIR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (40,0,5,'REFUGIARSE',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (41,0,6,'CELEBRAR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (42,0,6,'VITOREAR',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,evtinf_id) VALUES (43,0,6,'QUEMAR',0);



INSERT INTO BAILE (ID, NOMBRE, GENERO_GENERO) VALUES (0, 'MACARENA', 'Carranga');
INSERT INTO BAILE (ID, NOMBRE, GENERO_GENERO) VALUES (1, 'CELEBRATE', 'Vallenato' );
INSERT INTO BAILE (ID, NOMBRE, GENERO_GENERO) VALUES (2, 'LAMBADA',  'Pop');

insert into preferenciaxbaile (BAILE_ID,PERFIL_PREFERENCIA_PERFILPWA_CEDULA,GUSTO) values (0,123456789,0.7);
insert into preferenciaxbaile (BAILE_ID,PERFIL_PREFERENCIA_PERFILPWA_CEDULA,GUSTO) values (1,123456789,1);
insert into preferenciaxbaile (BAILE_ID,PERFIL_PREFERENCIA_PERFILPWA_CEDULA,GUSTO) values (2,123456789, 0.4);



INSERT INTO ANTECEDENTE (ID, ETIQUETA, VALOR) VALUES (0, 'EMOTIONFEEDBACK', 1);
INSERT INTO ANTECEDENTE (ID, ETIQUETA, VALOR) VALUES (1, 'EMOTIONFEEDBACK', 0.65);
INSERT INTO ANTECEDENTE (ID, ETIQUETA, VALOR) VALUES (2, 'EMOTIONFEEDBACK', 0.35);
INSERT INTO ANTECEDENTE (ID, ETIQUETA, VALOR) VALUES (3, 'EMOTIONFEEDBACK', -0.35);
INSERT INTO ANTECEDENTE (ID, ETIQUETA, VALOR) VALUES (4, 'EMOTIONFEEDBACK', -0.65);
INSERT INTO ANTECEDENTE (ID, ETIQUETA, VALOR) VALUES (5, 'EMOTIONFEEDBACK', -1);
INSERT INTO ANTECEDENTE (ID, ETIQUETA, VALOR) VALUES (6, 'VOICEFEEDBACK', 0);
INSERT INTO ANTECEDENTE (ID, ETIQUETA, VALOR) VALUES (7, 'VOICEFEEDBACK', 0.5);
INSERT INTO ANTECEDENTE (ID, ETIQUETA, VALOR) VALUES (8, 'VOICEFEEDBACK', 1);


INSERT INTO REGLA (ID, FEEDBACK,ETIQUETA) VALUES (0, 0.2,'EXCELENTE');
INSERT INTO REGLA (ID, FEEDBACK,ETIQUETA) VALUES (1, 0.2,'EXCELENTE');
INSERT INTO REGLA (ID, FEEDBACK,ETIQUETA) VALUES (2, 0.2,'EXCELENTE');
INSERT INTO REGLA (ID, FEEDBACK,ETIQUETA) VALUES (3, 0.1,'BUENO');
INSERT INTO REGLA (ID, FEEDBACK,ETIQUETA) VALUES (4, 0.1,'BUENO');
INSERT INTO REGLA (ID, FEEDBACK,ETIQUETA) VALUES (5, 0.1,'BUENO');
INSERT INTO REGLA (ID, FEEDBACK,ETIQUETA) VALUES (6, 0.1,'BUENO');
INSERT INTO REGLA (ID, FEEDBACK,ETIQUETA) VALUES (7, 0,'REGULAR');
INSERT INTO REGLA (ID, FEEDBACK,ETIQUETA) VALUES (8, 0,'REGULAR');
INSERT INTO REGLA (ID, FEEDBACK,ETIQUETA) VALUES (9, 0,'REGULAR');
INSERT INTO REGLA (ID, FEEDBACK,ETIQUETA) VALUES (10, -0.1,'MALO');
INSERT INTO REGLA (ID, FEEDBACK,ETIQUETA) VALUES (11, -0.1,'MALO');
INSERT INTO REGLA (ID, FEEDBACK,ETIQUETA) VALUES (12, -0.1,'MALO');
INSERT INTO REGLA (ID, FEEDBACK,ETIQUETA) VALUES (13, -0.1,'MALO');
INSERT INTO REGLA (ID, FEEDBACK,ETIQUETA) VALUES (14, -0.1,'MALO');
INSERT INTO REGLA (ID, FEEDBACK,ETIQUETA) VALUES (15, -0.2,'MUYMALO');
INSERT INTO REGLA (ID, FEEDBACK,ETIQUETA) VALUES (16, -0.2,'MUYMALO');
INSERT INTO REGLA (ID, FEEDBACK,ETIQUETA) VALUES (17, -0.2,'MUYMALO');


INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (0, 8);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (0, 0);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (1, 1);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (1, 8);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (2, 8);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (2, 5);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (3, 2);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (3, 8);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (4, 3);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (4, 8);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (5, 4);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (5, 8);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (6, 0);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (6, 7);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (7, 1);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (7, 7);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (8, 2);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (8, 7);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (9, 3);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (9, 7);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (10, 4);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (10, 7);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (11, 5);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (11, 7);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (12, 1);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (12, 6);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (13, 2);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (13, 6);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (14, 3);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (14, 6);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (15, 0);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (15, 6);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (16, 4);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (16, 6);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (17, 5);
INSERT INTO REGLAXANTECEDENTE (REGLA_ID, ANTECEDENTE_ID) VALUES (17, 6);

commit;