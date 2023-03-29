DROP DATABASE IF EXISTS studio;
CREATE DATABASE studio;

CREATE TABLE genre (
    id_genre        SMALLINT NOT NULL PRIMARY KEY,
    libelle_genre   VARCHAR(5) NOT NULL
);

CREATE TABLE role_user (
    id_role         SMALLINT NOT NULL PRIMARY KEY,
    libelle_role    VARCHAR(35) NOT NULL UNIQUE
);

CREATE TABLE users (
    id_user         SERIAL NOT NULL PRIMARY KEY,
    username        VARCHAR(255) NOT NULL,
    "password"      VARCHAR(36) NOT NULL,
    id_role         SMALLINT NOT NULL REFERENCES role_user (id_role),
    id_genre        SMALLINT NOT NULL REFERENCES genre (id_genre),
    date_naissance  DATE NOT NULL
);

CREATE TABLE projet (
    id_projet           SERIAL NOT NULL PRIMARY KEY,
    titre               VARCHAR(100),
    debut_production    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fin_production      TIMESTAMP NOT NULL CHECK (debut_production < fin_production)
);

CREATE TABLE personnage (
    id_personnage       SERIAL NOT NULL PRIMARY KEY,
    id_projet           INTEGER NOT NULL REFERENCES projet (id_projet),
    id_acteur           INTEGER NOT NULL REFERENCES users (id_user),
    nom                 VARCHAR(100),
    UNIQUE (id_projet, id_acteur)
);

CREATE TABLE type_plateau (
    id_type_plateau         SMALLINT NOT NULL PRIMARY KEY,
    libelle_type_plateau    VARCHAR(15) NOT NULL
);

CREATE TABLE plateau (
    id_plateau          SERIAL NOT NULL PRIMARY KEY,
    id_type_plateau     SMALLINT NOT NULL REFERENCES type_plateau (id_type_plateau),
    longitude           FLOAT NOT NULL,
    latitude            FLOAT NOT NULL,
    numero              SMALLINT,
    lieu                VARCHAR(100)
);

CREATE TABLE scene (
    id_scene                    SERIAL NOT NULL PRIMARY KEY,
    id_projet                   INTEGER NOT NULL REFERENCES projet (id_projet),
    id_scenariste               INTEGER NOT NULL REFERENCES users (id_user),
    titre                       VARCHAR(100),
    ordre                       SMALLINT CHECK (ordre > 0),
    id_plateau                  INTEGER NOT NULL REFERENCES plateau (id_plateau),
    duration                    FLOAT NOT NULL CHECK (duration > 0),
    estimation_tournage         TIME,
    debut_tournage_preferable   TIME,
    fin_tournage_preferable     TIME,
    debut_tournage              TIMESTAMP,
    fin_tournage                TIMESTAMP
);

CREATE TABLE dialogue (
    id_dialogue         SERIAL NOT NULL PRIMARY KEY,
    ordre               SMALLINT,
    id_scene            INTEGER NOT NULL REFERENCES scene (id_scene),
    id_personnage       INTEGER NOT NULL REFERENCES personnage (id_personnage),
    dialogue            TEXT,
    "action"            TEXT
);
