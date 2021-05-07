-- Generado por Oracle SQL Developer Data Modeler 21.1.0.092.1221
--   en:        2021-05-06 20:00:49 COT
--   sitio:      Oracle Database 12c
--   tipo:      Oracle Database 12c



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE accion (
    id          NUMBER NOT NULL,
    nombre      VARCHAR2(20) NOT NULL,
    tipo        VARCHAR2(20) NOT NULL,
    emocion_id  VARCHAR2(20) NOT NULL
);

ALTER TABLE accion ADD CONSTRAINT accion_pk PRIMARY KEY ( id );

CREATE TABLE accionxjoint (
    accion_id  NUMBER NOT NULL,
    joint_id   NUMBER NOT NULL
);

ALTER TABLE accionxjoint ADD CONSTRAINT accionxjoint_pk PRIMARY KEY ( accion_id,
                                                                      joint_id );

CREATE TABLE actividadpwa (
    id                     INTEGER NOT NULL,
    nombre                 VARCHAR2(30 CHAR) NOT NULL,
    tipo                   VARCHAR2(30) NOT NULL,
    duracion               NUMBER NOT NULL,
    dificultad_dificultad  VARCHAR2(10 CHAR)
);

ALTER TABLE actividadpwa ADD CONSTRAINT actividadpwa_pk PRIMARY KEY ( id );

CREATE TABLE actividadrutinaria (
    nombre                          VARCHAR2(40 CHAR) NOT NULL,
    id                              INTEGER NOT NULL,
    duracion                        NUMBER NOT NULL,
    hora                            DATE NOT NULL,
    perfil_medico_perfilpwa_cedula  VARCHAR2(30 CHAR)
);

ALTER TABLE actividadrutinaria ADD CONSTRAINT actividadrutinaria_pk PRIMARY KEY ( id );

CREATE TABLE actxpreferencia (
    actividadpwa_id            INTEGER NOT NULL,
    perfil_preferencia_cedula  VARCHAR2(30 CHAR) NOT NULL,
    activa                     NUMBER NOT NULL,
    gusto                      FLOAT NOT NULL,
    enriq                      INTEGER NOT NULL,
    dificultad_dificultad      VARCHAR2(10 CHAR) NOT NULL
);

ALTER TABLE actxpreferencia ADD CONSTRAINT actxpreferencia_pk PRIMARY KEY ( actividadpwa_id,
                                                                            perfil_preferencia_cedula );

CREATE TABLE antecedente (
    id        NUMBER NOT NULL,
    etiqueta  VARCHAR2(20) NOT NULL,
    valor     FLOAT NOT NULL
);

ALTER TABLE antecedente ADD CONSTRAINT antecedente_pk PRIMARY KEY ( id );

CREATE TABLE baile (
    id1            NUMBER NOT NULL,
    nombre         VARCHAR2(20) NOT NULL,
    gusto          FLOAT NOT NULL,
    genero_genero  VARCHAR2(20 CHAR) NOT NULL
);

ALTER TABLE baile ADD CONSTRAINT baile_pk PRIMARY KEY ( nombre );

CREATE TABLE bailexperfilpreferencia (
    baile_id                             VARCHAR2(20) NOT NULL, 
--  ERROR: Column name length exceeds maximum allowed length(30) 
    perfil_preferencia_perfilpwa_cedula  VARCHAR2(30 CHAR) NOT NULL
);

ALTER TABLE bailexperfilpreferencia ADD CONSTRAINT relation_29_pk PRIMARY KEY ( baile_id,
                                                                                perfil_preferencia_perfilpwa_cedula );

CREATE TABLE cancion (
    nombre         VARCHAR2(20 CHAR) NOT NULL,
    gusto          FLOAT NOT NULL,
    genero_genero  VARCHAR2(20 CHAR) NOT NULL,
    reminiscencia  NUMBER
);

ALTER TABLE cancion ADD CONSTRAINT cancion_pk PRIMARY KEY ( nombre );

CREATE TABLE causademencia (
    condicion VARCHAR2(30 CHAR) NOT NULL
);

ALTER TABLE causademencia ADD CONSTRAINT causademencia_pk PRIMARY KEY ( condicion );

CREATE TABLE cdr (
    memoria                         INTEGER NOT NULL,
    orientacion                     INTEGER NOT NULL,
    juicio                          INTEGER NOT NULL,
    vida_social                     INTEGER NOT NULL,
    hogar                           INTEGER NOT NULL,
    cuidadopersonal                 INTEGER NOT NULL,
    perfil_medico_perfilpwa_cedula  VARCHAR2(30 CHAR) NOT NULL
);

ALTER TABLE cdr ADD CONSTRAINT cdr_pk PRIMARY KEY ( perfil_medico_perfilpwa_cedula );

CREATE TABLE cuento (
    genero_genero  VARCHAR2(20 CHAR) NOT NULL,
    autor          VARCHAR2(20 CHAR) NOT NULL,
    gusto          FLOAT NOT NULL,
    nombre         VARCHAR2(15 CHAR) NOT NULL
);

ALTER TABLE cuento ADD CONSTRAINT cuento_pk PRIMARY KEY ( nombre );

CREATE TABLE cuidador (
    nombreusuario  VARCHAR2(30 CHAR) NOT NULL,
    contrasena     VARCHAR2(30 CHAR) NOT NULL,
    nombre         VARCHAR2(30 CHAR) NOT NULL,
    correo         VARCHAR2(40 CHAR) NOT NULL,
    celular        VARCHAR2(12 CHAR) NOT NULL
);

ALTER TABLE cuidador ADD CONSTRAINT cuidador_pk PRIMARY KEY ( nombreusuario );

CREATE TABLE dificultad (
    dificultad VARCHAR2(10 CHAR) NOT NULL
);

ALTER TABLE dificultad ADD CONSTRAINT dificultad_pk PRIMARY KEY ( dificultad );

CREATE TABLE emocion (
    id            VARCHAR2(20) NOT NULL,
    emotionaltag  VARCHAR2(20) NOT NULL,
    robot_id      NUMBER NOT NULL
);

ALTER TABLE emocion ADD CONSTRAINT emocion_pk PRIMARY KEY ( id );

CREATE TABLE enriq (
    frases_orden          INTEGER,
    frases_cuento_nombre  VARCHAR2(15 CHAR),
    params                VARCHAR2(20 CHAR) NOT NULL,
    valor                 VARCHAR2(20 CHAR) NOT NULL,
    cancion_nombre        VARCHAR2(20 CHAR) NOT NULL
);

ALTER TABLE enriq ADD CONSTRAINT enriq_pk PRIMARY KEY ( params );

CREATE TABLE estadocivil (
    tipoec VARCHAR2(20 CHAR) NOT NULL
);

ALTER TABLE estadocivil ADD CONSTRAINT estadocivil_pk PRIMARY KEY ( tipoec );

CREATE TABLE familiar (
    id          NUMBER NOT NULL,
    nombre      VARCHAR2(30 CHAR) NOT NULL,
    parentesco  VARCHAR2(20 CHAR) NOT NULL,
    interes     FLOAT NOT NULL,
    estavivo    NUMBER NOT NULL,
    nacimiento  DATE
);

ALTER TABLE familiar ADD CONSTRAINT familiar_pk PRIMARY KEY ( id );

CREATE TABLE familiares (
    perfilpwa_cedula  VARCHAR2(30 CHAR) NOT NULL,
    familiar_id       NUMBER NOT NULL
);

ALTER TABLE familiares ADD CONSTRAINT familiares_pk PRIMARY KEY ( perfilpwa_cedula,
                                                                  familiar_id );

CREATE TABLE frases (
    contenido      VARCHAR2(100 CHAR) NOT NULL,
    orden          INTEGER NOT NULL,
    cuento_nombre  VARCHAR2(15 CHAR) NOT NULL
);

ALTER TABLE frases ADD CONSTRAINT frases_pk PRIMARY KEY ( orden,
                                                          cuento_nombre );

CREATE TABLE genero (
    genero  VARCHAR2(20 CHAR) NOT NULL,
    gusto   FLOAT NOT NULL
);

ALTER TABLE genero ADD CONSTRAINT genero_pk PRIMARY KEY ( genero );

CREATE TABLE joint (
    id      NUMBER NOT NULL,
    nombre  VARCHAR2(20) NOT NULL,
    angulo  FLOAT NOT NULL,
    tiempo  NUMBER NOT NULL
);

ALTER TABLE joint ADD CONSTRAINT joint_pk PRIMARY KEY ( id );

CREATE TABLE listatags (
    cancion_nombre  VARCHAR2(20 CHAR) NOT NULL,
    tags_id         INTEGER NOT NULL
);

ALTER TABLE listatags ADD CONSTRAINT listatags_pk PRIMARY KEY ( cancion_nombre,
                                                                tags_id );

CREATE TABLE nivel_educativo (
    tipone VARCHAR2(20 CHAR) NOT NULL
);

ALTER TABLE nivel_educativo ADD CONSTRAINT nivel_educativo_pk PRIMARY KEY ( tipone );

CREATE TABLE perfil_medico (
    perfilpwa_cedula         VARCHAR2(30 CHAR) NOT NULL,
    tomamedicamentos         NUMBER NOT NULL,
    discapauditiva           NUMBER NOT NULL,
    discapvisual             NUMBER NOT NULL,
    discapmotora             NUMBER NOT NULL,
    estadioenfermedad        INTEGER NOT NULL,
    periodovigilia           NUMBER NOT NULL,
    causademencia_condicion  VARCHAR2(30 CHAR)
);

ALTER TABLE perfil_medico ADD CONSTRAINT perfil_medico_pk PRIMARY KEY ( perfilpwa_cedula );

CREATE TABLE perfil_preferencia (
    perfilpwa_cedula  VARCHAR2(30 CHAR) NOT NULL,
    nombrepreferido   VARCHAR2(40 CHAR) NOT NULL,
    gustokaraoke      FLOAT NOT NULL,
    gustomusica       FLOAT NOT NULL,
    gustobaile        FLOAT NOT NULL,
    volpreferido      INTEGER NOT NULL
);

ALTER TABLE perfil_preferencia ADD CONSTRAINT perfil_preferencia_pk PRIMARY KEY ( perfilpwa_cedula );

CREATE TABLE perfilpwa (
    nombre                  VARCHAR2(50 CHAR) NOT NULL,
    apellido                VARCHAR2(50 CHAR) NOT NULL,
    fechanacimiento         DATE NOT NULL,
    paisnacimiento          VARCHAR2(40 CHAR) NOT NULL,
    edad                    INTEGER NOT NULL,
    cedula                  VARCHAR2(30 CHAR) NOT NULL,
    profesion               VARCHAR2(20 CHAR) NOT NULL,
    estadocivil_tipoec      VARCHAR2(20 CHAR),
    nivel_educativo_tipone  VARCHAR2(20 CHAR),
    cuidador_nombreusuario  VARCHAR2(30 CHAR) NOT NULL,
    idrobot                 INTEGER
);

ALTER TABLE perfilpwa ADD CONSTRAINT perfilpwa_pk PRIMARY KEY ( cedula );

CREATE TABLE preferenciacancion ( 
--  ERROR: Column name length exceeds maximum allowed length(30) 
    perfil_preferencia_perfilpwa_cedula  VARCHAR2(30 CHAR) NOT NULL,
    cancion_nombre                       VARCHAR2(20 CHAR) NOT NULL
);

ALTER TABLE preferenciacancion ADD CONSTRAINT preferenciacancion_pk PRIMARY KEY ( perfil_preferencia_perfilpwa_cedula,
                                                                                  cancion_nombre );

CREATE TABLE preferenciaxcuento ( 
--  ERROR: Column name length exceeds maximum allowed length(30) 
    perfil_preferencia_perfilpwa_cedula  VARCHAR2(30 CHAR) NOT NULL,
    cuento_nombre                        VARCHAR2(15 CHAR) NOT NULL
);

ALTER TABLE preferenciaxcuento ADD CONSTRAINT preferenciaxcuento_pk PRIMARY KEY ( perfil_preferencia_perfilpwa_cedula,
                                                                                  cuento_nombre );

CREATE TABLE registroactividad (
    fecha             DATE NOT NULL,
    estadoinicial     VARCHAR2(30 CHAR) NOT NULL,
    estadofinal       VARCHAR2(30 CHAR) NOT NULL,
    perfilpwa_cedula  VARCHAR2(30 CHAR) NOT NULL,
    tipo              VARCHAR2(20 CHAR) NOT NULL,
    actividadpwa_id   INTEGER
);

ALTER TABLE registroactividad
    ADD CONSTRAINT registroactividad_pk PRIMARY KEY ( fecha,
                                                      tipo,
                                                      perfilpwa_cedula,
                                                      actividadpwa_id );

CREATE TABLE regla (
    id        NUMBER NOT NULL,
    feedback  FLOAT NOT NULL,
    etiqueta  VARCHAR2(20) NOT NULL
);

ALTER TABLE regla ADD CONSTRAINT regla_pk PRIMARY KEY ( id );

CREATE TABLE reglaxantecedente (
    regla_id        NUMBER NOT NULL,
    antecedente_id  NUMBER NOT NULL
);

ALTER TABLE reglaxantecedente ADD CONSTRAINT relation_32_pk PRIMARY KEY ( regla_id,
                                                                          antecedente_id );

CREATE TABLE robot (
    id      NUMBER NOT NULL,
    nombre  VARCHAR2(20)
);

ALTER TABLE robot ADD CONSTRAINT robot_pk PRIMARY KEY ( id );

CREATE TABLE tags (
    id   INTEGER NOT NULL,
    tag  VARCHAR2(20 CHAR) NOT NULL
);

ALTER TABLE tags ADD CONSTRAINT tags_pk PRIMARY KEY ( id );

ALTER TABLE accion
    ADD CONSTRAINT accion_emocion_fk FOREIGN KEY ( emocion_id )
        REFERENCES emocion ( id );

ALTER TABLE accionxjoint
    ADD CONSTRAINT accionxjoint_accion_fk FOREIGN KEY ( accion_id )
        REFERENCES accion ( id );

ALTER TABLE accionxjoint
    ADD CONSTRAINT accionxjoint_joint_fk FOREIGN KEY ( joint_id )
        REFERENCES joint ( id );

ALTER TABLE actividadpwa
    ADD CONSTRAINT actividadpwa_dificultad_fk FOREIGN KEY ( dificultad_dificultad )
        REFERENCES dificultad ( dificultad );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE actividadrutinaria
    ADD CONSTRAINT actividadrutinaria_perfil_medico_fk FOREIGN KEY ( perfil_medico_perfilpwa_cedula )
        REFERENCES perfil_medico ( perfilpwa_cedula );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE actxpreferencia
    ADD CONSTRAINT actxpreferencia_actividadpwa_fk FOREIGN KEY ( actividadpwa_id )
        REFERENCES actividadpwa ( id );

ALTER TABLE actxpreferencia
    ADD CONSTRAINT actxpreferencia_dificultad_fk FOREIGN KEY ( dificultad_dificultad )
        REFERENCES dificultad ( dificultad );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE actxpreferencia
    ADD CONSTRAINT actxpreferencia_perfil_preferencia_fk FOREIGN KEY ( perfil_preferencia_cedula )
        REFERENCES perfil_preferencia ( perfilpwa_cedula );

ALTER TABLE baile
    ADD CONSTRAINT baile_genero_fk FOREIGN KEY ( genero_genero )
        REFERENCES genero ( genero );

ALTER TABLE cancion
    ADD CONSTRAINT cancion_genero_fk FOREIGN KEY ( genero_genero )
        REFERENCES genero ( genero );

ALTER TABLE cdr
    ADD CONSTRAINT cdr_perfil_medico_fk FOREIGN KEY ( perfil_medico_perfilpwa_cedula )
        REFERENCES perfil_medico ( perfilpwa_cedula );

ALTER TABLE cuento
    ADD CONSTRAINT cuento_genero_fk FOREIGN KEY ( genero_genero )
        REFERENCES genero ( genero );

ALTER TABLE emocion
    ADD CONSTRAINT emocion_robot_fk FOREIGN KEY ( robot_id )
        REFERENCES robot ( id );

ALTER TABLE enriq
    ADD CONSTRAINT enriq_cancion_fk FOREIGN KEY ( cancion_nombre )
        REFERENCES cancion ( nombre );

ALTER TABLE enriq
    ADD CONSTRAINT enriq_frases_fk FOREIGN KEY ( frases_orden,
                                                 frases_cuento_nombre )
        REFERENCES frases ( orden,
                            cuento_nombre );

ALTER TABLE familiares
    ADD CONSTRAINT familiares_familiar_fk FOREIGN KEY ( familiar_id )
        REFERENCES familiar ( id );

ALTER TABLE familiares
    ADD CONSTRAINT familiares_perfilpwa_fk FOREIGN KEY ( perfilpwa_cedula )
        REFERENCES perfilpwa ( cedula );

ALTER TABLE frases
    ADD CONSTRAINT frases_cuento_fk FOREIGN KEY ( cuento_nombre )
        REFERENCES cuento ( nombre );

ALTER TABLE listatags
    ADD CONSTRAINT listatags_cancion_fk FOREIGN KEY ( cancion_nombre )
        REFERENCES cancion ( nombre );

ALTER TABLE listatags
    ADD CONSTRAINT listatags_tags_fk FOREIGN KEY ( tags_id )
        REFERENCES tags ( id );

ALTER TABLE perfil_medico
    ADD CONSTRAINT perfil_medico_causademencia_fk FOREIGN KEY ( causademencia_condicion )
        REFERENCES causademencia ( condicion );

ALTER TABLE perfil_medico
    ADD CONSTRAINT perfil_medico_perfilpwa_fk FOREIGN KEY ( perfilpwa_cedula )
        REFERENCES perfilpwa ( cedula );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE perfil_preferencia
    ADD CONSTRAINT perfil_preferencia_perfilpwa_fk FOREIGN KEY ( perfilpwa_cedula )
        REFERENCES perfilpwa ( cedula );

ALTER TABLE perfilpwa
    ADD CONSTRAINT perfilpwa_cuidador_fk FOREIGN KEY ( cuidador_nombreusuario )
        REFERENCES cuidador ( nombreusuario );

ALTER TABLE perfilpwa
    ADD CONSTRAINT perfilpwa_estadocivil_fk FOREIGN KEY ( estadocivil_tipoec )
        REFERENCES estadocivil ( tipoec );

ALTER TABLE perfilpwa
    ADD CONSTRAINT perfilpwa_nivel_educativo_fk FOREIGN KEY ( nivel_educativo_tipone )
        REFERENCES nivel_educativo ( tipone );

ALTER TABLE preferenciacancion
    ADD CONSTRAINT preferenciacancion_cancion_fk FOREIGN KEY ( cancion_nombre )
        REFERENCES cancion ( nombre );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE preferenciacancion
    ADD CONSTRAINT preferenciacancion_perfil_preferencia_fk FOREIGN KEY ( perfil_preferencia_perfilpwa_cedula )
        REFERENCES perfil_preferencia ( perfilpwa_cedula );

ALTER TABLE preferenciaxcuento
    ADD CONSTRAINT preferenciaxcuento_cuento_fk FOREIGN KEY ( cuento_nombre )
        REFERENCES cuento ( nombre );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE preferenciaxcuento
    ADD CONSTRAINT preferenciaxcuento_perfil_preferencia_fk FOREIGN KEY ( perfil_preferencia_perfilpwa_cedula )
        REFERENCES perfil_preferencia ( perfilpwa_cedula );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE registroactividad
    ADD CONSTRAINT registroactividad_actividadpwa_fk FOREIGN KEY ( actividadpwa_id )
        REFERENCES actividadpwa ( id );

ALTER TABLE registroactividad
    ADD CONSTRAINT registroactividad_perfilpwa_fk FOREIGN KEY ( perfilpwa_cedula )
        REFERENCES perfilpwa ( cedula );

ALTER TABLE bailexperfilpreferencia
    ADD CONSTRAINT relation_29_baile_fk FOREIGN KEY ( baile_id )
        REFERENCES baile ( nombre );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE bailexperfilpreferencia
    ADD CONSTRAINT relation_29_perfil_preferencia_fk FOREIGN KEY ( perfil_preferencia_perfilpwa_cedula )
        REFERENCES perfil_preferencia ( perfilpwa_cedula );

ALTER TABLE reglaxantecedente
    ADD CONSTRAINT relation_32_antecedente_fk FOREIGN KEY ( antecedente_id )
        REFERENCES antecedente ( id );

ALTER TABLE reglaxantecedente
    ADD CONSTRAINT relation_32_regla_fk FOREIGN KEY ( regla_id )
        REFERENCES regla ( id );



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            34
-- CREATE INDEX                             0
-- ALTER TABLE                             70
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- TSDP POLICY                              0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                  11
-- WARNINGS                                 0
