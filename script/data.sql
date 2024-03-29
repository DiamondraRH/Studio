INSERT INTO type_plateau(id_type_plateau,libelle_type_plateau) VALUES (1,'privee');
INSERT INTO type_plateau(id_type_plateau, libelle_type_plateau) VALUES (2,'public');

INSERT INTO role_user(id_role,libelle_role) VALUES (1, 'scenariste');
INSERT INTO role_user(id_role,libelle_role) VALUES (2, 'acteur');


INSERT INTO genre(id_genre,libelle_genre) VALUES (1,'Homme');
INSERT INTO genre(id_genre,libelle_genre) VALUES (2,'Femme');


INSERT INTO users(username,password,id_role,id_genre,date_naissance) VALUES ('Tim Minear','root',1,1,'1964-10-24');
INSERT INTO users(username,password,id_role,id_genre,date_naissance) VALUES ('Ronen Rubinstein','root',2,1,'1993-11-07');
INSERT INTO users(username,password,id_role,id_genre,date_naissance) VALUES ('Rafael L. Silva','root',2,1,'1994-06-18');
INSERT INTO users(username,password,id_role,id_genre,date_naissance) VALUES ('Rob Lowe','root',2,1,'1964-03-17');
INSERT INTO users(username,password,id_role,id_genre,date_naissance) VALUES ('Lisa Edelstein','root',2,2,'1966-05-21');
INSERT INTO users(username,password,id_role,id_genre,date_naissance) VALUES ('Gina Torres','root',2,2,'1964-04-27');

INSERT INTO projet(titre,debut_production,fin_production) VALUES ('Episode 08 : An unlikely emmergency','2023-03-13','2023-03-30');

INSERT INTO personnage VALUES
(DEFAULT, 1, 2, 'Tyler Kennedy Strand'),
(DEFAULT, 1, 3, 'Carlos Reyes'),
(DEFAULT, 1, 4, 'Owen Strand'),
(DEFAULT, 1, 5, 'Gwyneth Morgan'),
(DEFAULT, 1, 6, 'Tommy Vega');

INSERT INTO plateau(id_type_plateau,longitude,latitude,numero,lieu) VALUES (1,45,32,1,'Anosy');
INSERT INTO plateau(id_type_plateau,longitude,latitude,numero,lieu) VALUES (2,30,31,2,'Analakely');
INSERT INTO plateau(id_type_plateau,longitude,latitude,numero,lieu) VALUES (2,24,32,3,'Andoharanofotsy');


INSERT INTO scene (id_projet, id_scenariste, titre, ordre, id_plateau, duration, estimation_tournage, debut_tournage_preferable, fin_tournage_preferable)
VALUES
    (1, 1, 'TK, Carlos and Owen at the loft', 1, 1, 6.5, '00:06:00','09:00:00', '10:30:00'),
    (1, 1, 'Gwyneth finding TK half concious', 2, 2, 5.0, '00:10:00','22:00:00', '23:00:00'),
    (1, 1, 'TK and Gwyneth back at ChinaTown', 3, 2, 4.5, '00:09:00','10:00:00', '13:00:00'),
    (1, 1, 'Owen finding TK at the firehouse', 4, 1, 7.0, '00:15:00','12:00:00', '12:30:00'),
    (1, 1, 'TK and Owen arriving at the airport', 5, 1, 2.5, '00:10:00','13:05:00', '13:30:00'),
    (1, 1, 'Carlos couldn t find available seats', 6, 1, 5.5, '00:10:40','13:30:00', '13:45:00'),
    (1, 1, 'TK and Owen packing up in the plane', 7, 1, 2.0, '00:04:00','14:00:00', '14:10:00'),
    (1, 1, 'TK ad Gwyneth on the plane to LA', 8, 2, 5.0, '00:10:00','21:00:00', '21:10:00'),
    (1, 1, 'A passenger being a jerk', 9, 1, 2.0, '00:04:00','14:11:00', '14:20:00'),
    (1, 1, 'TK sneeking to go to the toilet', 10, 2, 5.0, '00:10:00','21:11:00', '21:25:00'),
    (1, 1, 'The plane s reactor on fire', 11, 1, 6.5, '00:15:00','14:25:00', '14:40:00'),
    (1, 1, 'Owen calling Vega for help', 12, 1, 4.5, '00:09:00','14:41:00', '14:50:00'),
    (1, 1, '126 team witnessing the land on', 13, 1, 1.5, '00:03:00','14:51:00', '14:55:00'),
    (1, 1, 'Passengers hanging on', 14, 1, 1.5, '00:03:00','14:56:00', '15:00:00'),
    (1, 1, 'Gwyneth saying goodbye to TK', 15, 2, 3.0, '00:06:00','15:05:00', '15:15:00'),
    (1, 1, 'Carlos running to TK', 16, 1, 1.5, '00:30:00','15:17:00', '15:20:00'),
    (1, 1, 'Strand Family having dinner together', 17, 2, 3.0, '00:06:00','20:00:00', '22:30:00');

INSERT INTO scene_personnage
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 1),
    (2, 4),
    (3, 1),
    (3, 4),
    (4, 1),
    (4, 3),
    (5, 1),
    (5, 3),
    (6, 1),
    (6, 2),
    (6, 3),
    (7, 1),
    (7, 3),
    (8, 1),
    (8, 4),
    (9, 1),
    (9, 3),
    (10, 1),
    (10, 4),
    (11, 1),
    (11, 3),
    (12, 1),
    (12, 3),
    (12, 5),
    (14, 2),
    (15, 1),
    (15, 4),
    (16, 1),
    (16, 2),
    (17, 1),
    (17, 3),
    (17, 4);

UPDATE SCENE SET debut_tournage=null::timestamp, fin_tournage=null::timestamp where 2>1;
Update scene set duration=3.5 where titre='TK, Carlos and Owen at the loft';

