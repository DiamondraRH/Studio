INSERT INTO type_plateau(id_type_plateau,libelle_type_plateau) VALUES (1,'privee');
INSERT INTO type_plateau(id_type_plateau, libelle_type_plateau) VALUES (2,'public');

INSERT INTO role_user(id_role,libelle_role) VALUES (1,'scenariste');


INSERT INTO genre(id_genre,libelle_genre) VALUES (1,'homme');
INSERT INTO genre(id_genre,libelle_genre) VALUES (2,'femme');


INSERT INTO users(username,password,id_role,id_genre,date_naissance) VALUES ('Jean','jean',1,1,'1999-10-12');

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

UPDATE SCENE SET debut_tournage=null::timestamp, fin_tournage=null::timestamp where 2>1;

select this_.id_scene as id_scene1_6_4_, this_.debut_tournage as debut_to2_6_4_, this_.debut_tournage_preferable as debut_to3_6_4_, this_.duration as duration4_6_4_, this_.estimation_tournage as estimati5_6_4_, this_.fin_tournage as fin_tour6_6_4_, this_.fin_tournage_preferable as fin_tour7_6_4_, this_.ordre as ordre8_6_4_, this_.id_plateau as id_plat10_6_4_, this_.id_projet as id_proj11_6_4_, this_.titre as titre9_6_4_, this_.id_scenariste as id_scen12_6_4_, plateau2_.id_plateau as id_plate1_3_0_, plateau2_.latitude as latitude2_3_0_, plateau2_.lieu as lieu3_3_0_, plateau2_.longitude as longitud4_3_0_, plateau2_.numero as numero5_3_0_, plateau2_.id_type_plateau as id_type_6_3_0_, typeplatea3_.id_type_plateau as id_type_1_7_1_, typeplatea3_.libelle_type_plateau as libelle_2_7_1_, projet4_.id_projet as id_proje1_4_2_, projet4_.debut_production as debut_pr2_4_2_, projet4_.fin_production as fin_prod3_4_2_, projet4_.titre as titre4_4_2_, user5_.id_user as id_user1_8_3_, user5_.date_naissance as date_nai2_8_3_, user5_.id_genre as id_genre5_8_3_, user5_.id_role as id_role6_8_3_, user5_.password as password3_8_3_, user5_.username as username4_8_3_ from scene this_ left outer join plateau plateau2_ on this_.id_plateau=plateau2_.id_plateau left outer join type_plateau typeplatea3_ on plateau2_.id_type_plateau=typeplatea3_.id_type_plateau left outer join projet projet4_ on this_.id_projet=projet4_.id_projet left outer join users user5_ on this_.id_scenariste=user5_.id_user where (this_.id_projet=1 and (this_.debut_tournage is not null and this_.fin_tournage is not null));