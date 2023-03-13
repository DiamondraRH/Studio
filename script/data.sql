INSERT INTO type_plateau(id_type_plateau,libelle_type_plateau) VALUES (1,'privee');
INSERT INTO type_plateau(id_type_plateau, libelle_type_plateau) VALUES (2,'public');

INSERT INTO role_user(id_role,libelle_role) VALUES (1,'scenariste');


INSERT INTO genre(id_genre,libelle_genre) VALUES (1,'homme');
INSERT INTO genre(id_genre,libelle_genre) VALUES (2,'femme');


INSERT INTO users(username.password,id_role,id_genre,date_naissance) VALUES ('Jean','jean',1,1,'1999-10-12');

INSERT INTO projet(titre,debut_production,fin_production) VALUES ('film1','2023-03-13','2023-03-30');

INSERT INTO plateau(id_type_plateau,longitude,latitude,numero,lieu) VALUES (1,45,32,1,'Anosy');
INSERT INTO plateau(id_type_plateau,longitude,latitude,numero,lieu) VALUES (2,30,31,2,'Analakely');
INSERT INTO plateau(id_type_plateau,longitude,latitude,numero,lieu) VALUES (2,24,32,3,'Andoharanofotsy');

INSERT INTO scene (id_projet, id_scenariste, titre, ordre, id_plateau, duration, estimation_tournage,debut_tournage_preferable, fin_tournage_preferable)
VALUES
    (1, 1, 'Scène 1', 1, 1, 2.5, '00:6:00','08:00:00', '10:30:00'),
    (1, 1, 'Scène 2', 2, 1, 1.5, '00:4:00','10:45:00', '12:15:00'),
    (1, 1, 'Scène 3', 3, 2, 3.0, '00:6:00','13:00:00', '16:00:00'),
    (1, 1, 'Scène 4', 4, 2, 2.0, '00:10:00','16:15:00', '18:15:00'),
    (1, 1, 'Scène 5', 5, 3, 1.5, '00:20:00','09:00:00', '10:30:00'),
    (1, 1, 'Scène 6', 6, 3, 2.5, '00:30:00','10:45:00', '13:15:00'),
    (1, 1, 'Scène 7', 7, 1, 2.0, '00:40:00','13:00:00', '15:00:00'),
    (1, 1, 'Scène 8', 8, 2, 1.0, '00:50:00','09:00:00', '10:00:00'),
    (1, 1, 'Scène 9', 9, 3, 2.0, '00:20:00','13:30:00', '15:30:00'),
    (1, 1, 'Scène 10', 10, 1, 1.5, '00:30:00','16:00:00', '17:30:00');