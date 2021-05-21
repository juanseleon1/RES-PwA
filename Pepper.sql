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


INSERT INTO Perfil_Medico (PerfilPwa_cedula,tomaMedicamentos,discapAuditiva,discapVisual,discapMotora,estadioEnfermedad,periodoVigilia,causaDemencia_condicion) VALUES(123456789,1,0,1,0,5,60, 'Alzheimer');

INSERT INTO Perfil_Preferencia (PerfilPwa_cedula,nombrePreferido,gustoKaraoke,gustoMusica,gustoBaile,volPreferido) VALUES(123456789, 'Juanis',0.7,1,1,50);

INSERT INTO Genero (genero) VALUES( 'Vallenato');
INSERT INTO Genero (genero) VALUES( 'Carranga');
INSERT INTO Genero (genero) VALUES( 'Pop');
INSERT INTO Genero (genero) VALUES( 'Latin');

INSERT INTO Cancion (nombre,Genero_genero,reminiscencia,url) VALUES( 'Mi cacharrito', 'Latin',1, 'http://10.195.22.103:49152/content/media/object_id/22/res_id/0');
INSERT INTO Cancion (nombre,Genero_genero,reminiscencia,url) VALUES( 'Mi querido viejo', 'Pop',1, 'http://10.195.22.103:49152/content/media/object_id/48/res_id/0');

INSERT INTO PreferenciaXCancion (Perfil_Preferencia_PerfilPwa_cedula,Cancion_nombre,gusto) VALUES(123456789, 'Mi cacharrito',0.8);
INSERT INTO PreferenciaXCancion (Perfil_Preferencia_PerfilPwa_cedula,Cancion_nombre,gusto) VALUES(123456789, 'Mi querido viejo',0.5);

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

INSERT INTO Robot (id,nombre) VALUES (0, 'pepper');


INSERT INTO Emocion (id,emotionalTag,robot_id) VALUES (0,'VHAPPY',0); 
INSERT INTO Emocion (id,emotionalTag,robot_id) VALUES (1,'HAPPY',0); 
INSERT INTO Emocion (id,emotionalTag,robot_id) VALUES (2,'NORMAL',0); 
INSERT INTO Emocion (id,emotionalTag,robot_id) VALUES (3,'SAD',0); 
INSERT INTO Emocion (id,emotionalTag,robot_id) VALUES (4,'VSAD',0); 

INSERT INTO Accion (id,nombre,Emocion_id,tipo) VALUES (0, 'POSTURA',4, 'postura'); 
INSERT INTO Accion (id,nombre,Emocion_id,tipo) VALUES (1, 'POSTURA',0, 'postura'); 
INSERT INTO Accion (id,nombre,Emocion_id,tipo) VALUES (4, 'POSTURA',3, 'postura'); 
INSERT INTO Accion (id,nombre,Emocion_id,tipo) VALUES (5, 'POSTURA',1, 'postura'); 
<<<<<<< HEAD
INSERT INTO Accion (id,nombre,Emocion_id,tipo) VALUES (6, 'POSTURA',2, 'postura'); 
=======
INSERT INTO Accion (id,nombre,Emocion_id,tipo) VALUES (6, 'POSTURA ',2, 'postura'); 
>>>>>>> origin/master


INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (0, 'HeadPitch',0.333358,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (1, 'HeadYaw',0.0173742,0.76); 
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
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (17, 'HeadPitch',-0.22017,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (18, 'HeadYaw',0,0.76); 
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
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (46, 'HeadPitch',0.174533,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (47, 'HipPitch',-0.436332,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (48, 'LShoulderPitch',1.309,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (49, 'RShoulderPitch',1.309,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (50, 'HeadPitch',-0.122173,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (51, 'HipPitch',0.0349066,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (52, 'LElbowRoll',-0.959931,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (53, 'RElbowRoll',0.959931,0.76); 
INSERT INTO Joint (id,nombre,angulo,tiempo) VALUES (54, 'HeadPitch',0,0.76); 
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

INSERT INTO eventinfluence (id,eventinfluence,eventname,EVTINF_ID) VALUES (0,0.7,'INTERNALPOSPLUS',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,EVTINF_ID) VALUES (1,0.3,'INTERNALPOS',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,EVTINF_ID) VALUES (2,0.3,'INTERNALNEG',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,EVTINF_ID) VALUES (3,0.7,'INTERNALNEGPLUS',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,EVTINF_ID) VALUES (4,0.4,'SPOKE',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,EVTINF_ID) VALUES (5,0.4,'NOTLOOKING',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,EVTINF_ID) VALUES (6,0.1,'NOTDETECTED',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,EVTINF_ID) VALUES (7,0.3,'NORESPONSE',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,EVTINF_ID) VALUES (8,0.5,'MOVEDAWAY',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,EVTINF_ID) VALUES (9,0.5,'APPROACHED',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,EVTINF_ID) VALUES (10,0.2,'SMILING',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,EVTINF_ID) VALUES (11,0.4,'POSVOICEEMOTION',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,EVTINF_ID) VALUES (12,0.4,'NEGVOICEEMOTION',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,EVTINF_ID) VALUES (13,0.6,'POSEMOSTATE',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,EVTINF_ID) VALUES (14,0.6,'NEGEMOSTATE',0);
INSERT INTO eventinfluence (id,eventinfluence,eventname,EVTINF_ID) VALUES (15,0.4,'SPEECHDETECTED',0);


commit;

<<<<<<< HEAD
update emotionaxisconfig set basevalue=0.2;
=======
update emotionaxisconfig set basevalue=-8;
>>>>>>> 0e229c540249cf3a7d4bb0975d88108fd246098e
commit;