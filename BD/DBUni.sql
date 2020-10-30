-- Generado por Oracle SQL Developer Data Modeler 20.2.0.167.1538
--   en:        2020-10-29 17:27:22 COT
--   sitio:      Oracle Database 12cR2
--   tipo:      Oracle Database 12cR2



DROP TABLE actividadpwa CASCADE CONSTRAINTS;

DROP TABLE actividadrutinaria CASCADE CONSTRAINTS;

DROP TABLE actxpreferencia CASCADE CONSTRAINTS;

DROP TABLE cancion CASCADE CONSTRAINTS;

DROP TABLE causademencia CASCADE CONSTRAINTS;

DROP TABLE cdr CASCADE CONSTRAINTS;

DROP TABLE cuento CASCADE CONSTRAINTS;

DROP TABLE cuidador CASCADE CONSTRAINTS;

DROP TABLE dificultad CASCADE CONSTRAINTS;

DROP TABLE estadocivil CASCADE CONSTRAINTS;

DROP TABLE familiar CASCADE CONSTRAINTS;

DROP TABLE familiares CASCADE CONSTRAINTS;

DROP TABLE frases CASCADE CONSTRAINTS;

DROP TABLE genero CASCADE CONSTRAINTS;

DROP TABLE listatags CASCADE CONSTRAINTS;

DROP TABLE nivel_educativo CASCADE CONSTRAINTS;

DROP TABLE perfil_medico CASCADE CONSTRAINTS;

DROP TABLE perfil_preferencia CASCADE CONSTRAINTS;

DROP TABLE perfilpwa CASCADE CONSTRAINTS;

DROP TABLE preferenciacancion CASCADE CONSTRAINTS;

DROP TABLE preferenciaxcuento CASCADE CONSTRAINTS;

DROP TABLE tags CASCADE CONSTRAINTS;

-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE actividadpwa (
    id                       INTEGER NOT NULL,
    nombre                   VARCHAR2(30 CHAR) NOT NULL,
    tipo                     VARCHAR2(30) NOT NULL,
    duracion                 NUMBER NOT NULL,
    gusto                    FLOAT NOT NULL,
    enriquecimientofavorito  INTEGER NOT NULL,
    dificultad_dificultad    VARCHAR2(10 CHAR)
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
    actividadpwa_id  INTEGER NOT NULL,
    cedula           VARCHAR2(30 CHAR) NOT NULL
);

ALTER TABLE actxpreferencia ADD CONSTRAINT actxpreferencia_pk PRIMARY KEY ( actividadpwa_id,
                                                                            cedula );

CREATE TABLE cancion (
    nombre        VARCHAR2(20 CHAR) NOT NULL,
    gusto         FLOAT NOT NULL,
    nombregenero  VARCHAR2(20 CHAR) NOT NULL
);

ALTER TABLE cancion ADD CONSTRAINT cancion_pk PRIMARY KEY ( nombre );

CREATE TABLE causademencia (
    enfermedad VARCHAR2(30 CHAR) NOT NULL
);

ALTER TABLE causademencia ADD CONSTRAINT causademencia_pk PRIMARY KEY ( enfermedad );

CREATE TABLE cdr (
    memoria               INTEGER NOT NULL,
    orientacion           INTEGER NOT NULL,
    juicio                INTEGER NOT NULL,
    vida_social           INTEGER NOT NULL,
    hogar                 INTEGER NOT NULL,
    cuidadopersonal       INTEGER NOT NULL,
    perfil_medico_cedula  VARCHAR2(30 CHAR) NOT NULL
);

ALTER TABLE cdr ADD CONSTRAINT cdr_pk PRIMARY KEY ( perfil_medico_cedula );

CREATE TABLE cuento (
    nombregenero  VARCHAR2(20 CHAR) NOT NULL,
    nombreautor   VARCHAR2(20 CHAR) NOT NULL,
    gusto         FLOAT NOT NULL,
    nombrecuento  VARCHAR2(15 CHAR) NOT NULL
);

ALTER TABLE cuento ADD CONSTRAINT cuento_pk PRIMARY KEY ( nombrecuento );

CREATE TABLE cuidador (
    nombreusuario  VARCHAR2(30 CHAR) NOT NULL,
    contraseņa     VARCHAR2(30 CHAR) NOT NULL,
    nombre         VARCHAR2(30 CHAR) NOT NULL,
    correo         VARCHAR2(40 CHAR) NOT NULL,
    celular        VARCHAR2(12 CHAR) NOT NULL
);

ALTER TABLE cuidador ADD CONSTRAINT cuidador_pk PRIMARY KEY ( nombreusuario );

CREATE TABLE dificultad (
    dificultad VARCHAR2(10 CHAR) NOT NULL
);

ALTER TABLE dificultad ADD CONSTRAINT dificultad_pk PRIMARY KEY ( dificultad );

CREATE TABLE estadocivil (
    tipoestado VARCHAR2(20 CHAR) NOT NULL
);

ALTER TABLE estadocivil ADD CONSTRAINT estadocivil_pk PRIMARY KEY ( tipoestado );

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
    cedula       VARCHAR2(30 CHAR) NOT NULL,
    familiar_id  NUMBER NOT NULL
);

ALTER TABLE familiares ADD CONSTRAINT familiares_pk PRIMARY KEY ( cedula,
                                                                  familiar_id );

CREATE TABLE frases (
    contenido     VARCHAR2(100 CHAR) NOT NULL,
    orden         INTEGER NOT NULL,
    nombrecuento  VARCHAR2(15 CHAR) NOT NULL
);

ALTER TABLE frases ADD CONSTRAINT frases_pk PRIMARY KEY ( orden,
                                                          nombrecuento );

CREATE TABLE genero (
    nombregenero  VARCHAR2(20 CHAR) NOT NULL,
    gusto         FLOAT NOT NULL
);

ALTER TABLE genero ADD CONSTRAINT genero_pk PRIMARY KEY ( nombregenero );

CREATE TABLE listatags (
    cancion_nombre  VARCHAR2(20 CHAR) NOT NULL,
    tags_id         INTEGER NOT NULL
);

ALTER TABLE listatags ADD CONSTRAINT listatags_pk PRIMARY KEY ( cancion_nombre,
                                                                tags_id );

CREATE TABLE nivel_educativo (
    tiponiveleducativo VARCHAR2(20 CHAR) NOT NULL
);

ALTER TABLE nivel_educativo ADD CONSTRAINT nivel_educativo_pk PRIMARY KEY ( tiponiveleducativo );

CREATE TABLE perfil_medico (
    perfilpwa_cedula          VARCHAR2(30 CHAR) NOT NULL,
    tomamedicamentos          NUMBER NOT NULL,
    discapacidadauditiva      NUMBER NOT NULL,
    discapacidadvisual        NUMBER NOT NULL,
    discapacidadmotora        NUMBER NOT NULL,
    estadioenfermedad         INTEGER NOT NULL,
    periodovigilia            NUMBER NOT NULL,
    causademencia_enfermedad  VARCHAR2(30 CHAR)
);

ALTER TABLE perfil_medico ADD CONSTRAINT perfil_medico_pk PRIMARY KEY ( perfilpwa_cedula );

CREATE TABLE perfil_preferencia (
    perfilpwa_cedula  VARCHAR2(30 CHAR) NOT NULL,
    nombrepreferido   VARCHAR2(40 CHAR) NOT NULL,
    gustokaraoke      FLOAT NOT NULL,
    gustomusica       FLOAT NOT NULL,
    gustobaile        FLOAT NOT NULL,
    volumenpreferido  INTEGER NOT NULL
);

ALTER TABLE perfil_preferencia ADD CONSTRAINT perfil_preferencia_pk PRIMARY KEY ( perfilpwa_cedula );

CREATE TABLE perfilpwa (
    nombre              VARCHAR2(50 CHAR) NOT NULL,
    apellido            VARCHAR2(50 CHAR) NOT NULL,
    fechanacimiento     DATE NOT NULL,
    paisnacimiento      VARCHAR2(40 CHAR) NOT NULL,
    edad                INTEGER NOT NULL,
    cedula              VARCHAR2(30 CHAR) NOT NULL,
    profesion           VARCHAR2(20 CHAR) NOT NULL,
    tipoestadocivil     VARCHAR2(20 CHAR),
    tiponiveleducativo  VARCHAR2(20 CHAR),
    cuidadornombre      VARCHAR2(30 CHAR) NOT NULL,
    idrobot             INTEGER
);

ALTER TABLE perfilpwa ADD CONSTRAINT perfilpwa_pk PRIMARY KEY ( cedula );

CREATE TABLE preferenciacancion (
    cedula         VARCHAR2(30 CHAR) NOT NULL,
    cancionnombre  VARCHAR2(20 CHAR) NOT NULL
);

ALTER TABLE preferenciacancion ADD CONSTRAINT preferenciacancion_pk PRIMARY KEY ( cedula,
                                                                                  cancionnombre );

CREATE TABLE preferenciaxcuento (
    cedula        VARCHAR2(30 CHAR) NOT NULL,
    nombrecuento  VARCHAR2(15 CHAR) NOT NULL
);

ALTER TABLE preferenciaxcuento ADD CONSTRAINT preferenciaxcuento_pk PRIMARY KEY ( cedula,
                                                                                  nombrecuento );

CREATE TABLE tags (
    id   INTEGER NOT NULL,
    tag  VARCHAR2(20 CHAR) NOT NULL
);

ALTER TABLE tags ADD CONSTRAINT tags_pk PRIMARY KEY ( id );

ALTER TABLE actxpreferencia
    ADD CONSTRAINT actividad_fk FOREIGN KEY ( actividadpwa_id )
        REFERENCES actividadpwa ( id );

ALTER TABLE actividadpwa
    ADD CONSTRAINT actividadpwa_dificultad_fk FOREIGN KEY ( dificultad_dificultad )
        REFERENCES dificultad ( dificultad );

ALTER TABLE preferenciacancion
    ADD CONSTRAINT cancion_fk FOREIGN KEY ( cancionnombre )
        REFERENCES cancion ( nombre );

ALTER TABLE listatags
    ADD CONSTRAINT cancion_fkv2 FOREIGN KEY ( cancion_nombre )
        REFERENCES cancion ( nombre );

ALTER TABLE cancion
    ADD CONSTRAINT cancion_genero_fk FOREIGN KEY ( nombregenero )
        REFERENCES genero ( nombregenero );

ALTER TABLE perfil_medico
    ADD CONSTRAINT causademencia_fk FOREIGN KEY ( causademencia_enfermedad )
        REFERENCES causademencia ( enfermedad );

ALTER TABLE cdr
    ADD CONSTRAINT cdr_perfil_medico_fk FOREIGN KEY ( perfil_medico_cedula )
        REFERENCES perfil_medico ( perfilpwa_cedula );

ALTER TABLE frases
    ADD CONSTRAINT cuento_fk FOREIGN KEY ( nombrecuento )
        REFERENCES cuento ( nombrecuento );

ALTER TABLE preferenciaxcuento
    ADD CONSTRAINT cuento_fkv2 FOREIGN KEY ( nombrecuento )
        REFERENCES cuento ( nombrecuento );

ALTER TABLE cuento
    ADD CONSTRAINT cuento_genero_fk FOREIGN KEY ( nombregenero )
        REFERENCES genero ( nombregenero );

ALTER TABLE perfilpwa
    ADD CONSTRAINT cuidador_fk FOREIGN KEY ( cuidadornombre )
        REFERENCES cuidador ( nombreusuario );

ALTER TABLE perfilpwa
    ADD CONSTRAINT estadocivil_fk FOREIGN KEY ( tipoestadocivil )
        REFERENCES estadocivil ( tipoestado );

ALTER TABLE familiares
    ADD CONSTRAINT familiar_fk FOREIGN KEY ( familiar_id )
        REFERENCES familiar ( id );

ALTER TABLE perfilpwa
    ADD CONSTRAINT nivel_educativo_fk FOREIGN KEY ( tiponiveleducativo )
        REFERENCES nivel_educativo ( tiponiveleducativo );

ALTER TABLE actividadrutinaria
    ADD CONSTRAINT perfil_medico_fk FOREIGN KEY ( perfil_medico_perfilpwa_cedula )
        REFERENCES perfil_medico ( perfilpwa_cedula );

ALTER TABLE preferenciacancion
    ADD CONSTRAINT perfil_preferencia_fk FOREIGN KEY ( cedula )
        REFERENCES perfil_preferencia ( perfilpwa_cedula );

ALTER TABLE actxpreferencia
    ADD CONSTRAINT perfil_preferencia_fkv1 FOREIGN KEY ( cedula )
        REFERENCES perfil_preferencia ( perfilpwa_cedula );

ALTER TABLE preferenciaxcuento
    ADD CONSTRAINT perfil_preferencia_fkv3 FOREIGN KEY ( cedula )
        REFERENCES perfil_preferencia ( perfilpwa_cedula );

ALTER TABLE familiares
    ADD CONSTRAINT perfilpwa_fk FOREIGN KEY ( cedula )
        REFERENCES perfilpwa ( cedula );

ALTER TABLE perfil_preferencia
    ADD CONSTRAINT perfilpwa_fkv2 FOREIGN KEY ( perfilpwa_cedula )
        REFERENCES perfilpwa ( cedula );

ALTER TABLE perfil_medico
    ADD CONSTRAINT perfilpwa_fkv3 FOREIGN KEY ( perfilpwa_cedula )
        REFERENCES perfilpwa ( cedula );

ALTER TABLE listatags
    ADD CONSTRAINT tags_fk FOREIGN KEY ( tags_id )
        REFERENCES tags ( id );



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            22
-- CREATE INDEX                             0
-- ALTER TABLE                             44
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
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
