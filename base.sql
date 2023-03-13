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

insert into type_plateau(id_type_plateau,libelle_type_plateau) values(1,'privee');
insert into type_plateau(id_type_plateau, libelle_type_plateau) values(2,'public');

insert into role_user(id_role,libelle_role) values(1,'scenariste');


insert into genre(id_genre,libelle_genre) values(1,'homme');
insert into genre(id_genre,libelle_genre) values(2,'femme');


insert into users(username.password,id_role,id_genre,date_naissance) values('Jean','jean',1,1,'1999-10-12');

insert into projet(titre,debut_production,fin_production) values('film1','2023-03-13','2023-03-30');

insert into plateau(id_type_plateau,longitude,latitude,numero,lieu) values(1,45,32,1,'Anosy');
insert into plateau(id_type_plateau,longitude,latitude,numero,lieu) values(2,30,31,2,'Analakely');
insert into plateau(id_type_plateau,longitude,latitude,numero,lieu) values(2,24,32,3,'Andoharanofotsy');

INSERT INTO scene (id_projet, id_scenariste, titre, ordre, id_plateau, duration, debut_tournage_preferable, fin_tournage_preferable)
VALUES
    (1, 1, 'Scène 1', 1, 1, 2.5, '08:00:00', '10:30:00'),
    (1, 1, 'Scène 2', 2, 1, 1.5, '10:45:00', '12:15:00'),
    (1, 1, 'Scène 3', 3, 2, 3.0, '13:00:00', '16:00:00'),
    (1, 1, 'Scène 4', 4, 2, 2.0, '16:15:00', '18:15:00'),
    (1, 1, 'Scène 5', 5, 3, 1.5, '09:00:00', '10:30:00'),
    (1, 1, 'Scène 6', 6, 3, 2.5, '10:45:00', '13:15:00'),
    (1, 1, 'Scène 7', 7, 1, 2.0, '13:00:00', '15:00:00'),
    (1, 1, 'Scène 8', 8, 2, 1.0, '09:00:00', '10:00:00'),
    (1, 1, 'Scène 9', 9, 3, 2.0, '13:30:00', '15:30:00'),
    (1, 1, 'Scène 10', 10, 1, 1.5, '16:00:00', '17:30:00');