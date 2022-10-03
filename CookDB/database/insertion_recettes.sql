DELETE FROM Impliquer;
DELETE FROM Comporter;
DELETE FROM Subdiviser;
DELETE FROM Utiliser;
DELETE FROM Servir;
DELETE FROM Ingredient;
DELETE FROM Etape;
DELETE FROM Ustensile;
DELETE FROM Recette;
DELETE FROM Origine;
DELETE FROM Menu;

INSERT INTO Ingredient(id_ingredient,prix_ingredient,nom_ingredient,type_ingredient,type_mesure) VALUES 
(1,0.00171,"Pomme de terre","feculent","g"),
(2,0.1,"Oignon jaune","legume","g"),
(3,0.06,"Gousse d'ail","legume","u"),
(4,0.0028,"Tomate","legume","g"),
(5,0.00069,"Farine de blé tout usage","poudre","g"),
(6,0.10,"Herbes de Provence","herbe","g"),
(7,0.024,"Parmesan","fromage","g"),
(8,0.01156,"Beurre demi-sel","beurre","g"),
(9,0.0001,"Sel","poudre","g"),
(10,0.0059,"Fromage râpé","fromage","g"),
(11,0.2424,"Poivre noir","epice","g"),
(12,0.00643,"Viande hachée de boeuf","viande","g"),
(13,0.255,"Oeuf de poule","oeuf","u"),
(14,0.005,"Sucre","poudre","g"),
(15,0.00043,"Carotte orange","legume","g"),
(16,0.00175,"Spaghetti","feculent","g"),
(17,0.00275,"Tagiliatelles","feculent","g"),
(18,0.01623,"Thym","epice","g"),
(19,0.01514,"Laurier","epice","g"),
(20,0.02958,"Clou de girofle","epice","g"),
(21,0.01,"Lait","liquide","cl"),
(22,20,"Poulet","viande","u"),
(23,5,"Cuisse de poulet","viande","u"),
(24,0.013,"Blanc de poulet","viande","g"),
(25,0,"Eau","liquide","cl"),
(26,0.0145,"Feuilles de thé vert","epice","g"),
(27,0.004,"Feuilles de menthe","epice","g"),
(28,0.00462,"Moutarde","epice","g"),
(29,0.0251,"Persil","epice","g"),
(30,0.0155,"Huile de colza","Liquide","cl")
;


INSERT INTO Etape(id_etape,nom_etape,contenu_etape) VALUES 
(1,"Hachis Parmentier : Découpe et cuisson des légumes","Hachez l'oignon et l'ail. Coupez les tomates en dés. Faîtes revenir l'oignon et l'ail avec du beurre."),
(2,"Hachis Parmentier : Cuisson du hachis","Ajoutez les tomates, la viande hachée, la farine, du sel, du poivre et des herbes de Provence. Faîtes cuire et laissez mijoter."),
(3,"Hachis Parmentier : Mélange","Quand tout est cuit, coupez le feu et ajoutez le jaune d'oeuf et un peu de parmesan. Mélangez bien."),
(4,"Hachis Parmentier : Disposition et cuisson","Préchauffez le four à 200°C (thermostat 6-7). Etaler au fond du plat à gratin. Préparer la purée. L'étaler au dessus de la viande. Saupoudrer de fromage râpé et faire gratiner."),
(5,"Quatre-Quarts : Avant-propos","Assurez-vous d'avoir autant de farine que de sucre, de beurre et d'oeuf."),
(6,"Quatre-Quarts : Préparation 1","Battez les jaunes d'oeuf avec le sucre."),
(7,"Quatre-Quarts : Préparation 2","Ajoutez le beurre fondu et continuez à battre."),
(8,"Quatre-Quarts : Préparation 3","Ajoutez par petits quantités la farine. Puis, ajoutez les blancs d'oeuf et faites-les monter en neige."),
(9,"Quatre-Quarts : Cuisson","Salez. Versez la préparation dans un moule à cake beurré et enfournez dans un four à 160°C pendant 45 minutes."),
(10,"Napolitaine : Préparation","Hachez l'oignon, écrasez l'ail, coupez les tomates en quartiers."),
(11,"Napolitaine : Cuisson 1","Faites revenir les oignons."),
(12,"Napolitaine : Cuisson 2","Ajoutez l'ail, la farine, faites blondir et ajoutez 3 décilitres d'eau."),
(13,"Napolitaine : Cuisson 3","Ajoutez les tomates, le thym, le laurier, les clous de girofle. Laissez mijoter pendant 30 minutes, avant de passer au chinois. La sauce sera prête."),
(14,"Napolitaine : Cuisson 4","Faites cuire les pâtes dans de l'eau bouillante pour la durée indiquée sur le paquet."),
(15,"Thé vert : Enlever l'amerture du thé","Portez de l'eau à ébullition. Versez en un petit verre sur les feuilles de thé, avant d'égouter."),
(16,"Thé vert : Dans la théière 1","Versez dans une théière les feuilles et remplir la théière d'eau. Noyez les feuilles de menthe dans l'eau."),
(17,"Thé vert : Dans la théière 2","Ajoutez le sucre dans le liquide. Mélangez. Vous pouvez servir en versant assez haut pour faire << mousser >> le sucre"),
(18,"Mimosa : Oeufs durs","Cuisez les oeufs dans l'eau bouillante pendant 10 minutes. Plongez-les dans l'eau froide avant de les écalez. Coupez les oeufs dans la longueur et séparez les blancs des jaunes."),
(19,"Mimosa : Mayonnaise","Montez une mayonnaise avec un jaune d'oeuf, une cuillère à café de moutarde et 25 cl d'huile."),
(20,"Mimosa : Les jaunes d'oeuf","Émiettez dans une assiette creuse les jaunes d'oeuf. Incorporez-en une partie dans la mayonnaise."),
(21,"Mimosa : Disposition","Remplissez les oeufs de la mayonnaise. Ajoutez l'émietté de jaune par-dessus, puis des herbes hachées.")
;

INSERT INTO Ustensile(id_ustensile,nom_ustensile) VALUES 
(1,"Couteau"),
(2,"Four"),
(3,"Saladier"),
(4,"Plaque de cuisson"),
(5,"Plat"),
(6,"Spatule"),
(7,"Chinois"),
(8,"Poêle"),
(9,"Casserole"),
(10,"Sauteuse"),
(11,"Wok"),
(12,"Fouet"),
(13,"Moule à cake"),
(14,"Moule à tarte"),
(15,"Moule à gâteau"),
(16,"Planche à découper"),
(17,"Verre-doseur"),
(18,"Passoire"),
(19,"Théière"),
(20,"Cuillère"),
(21,"Assiette creuse")
;

INSERT INTO Origine(id_origine,origine) VALUES 
(1,"France"),
(2,"Italie"),
(3,"Maroc")
;

INSERT INTO Recette(id_recette,type_recette,nom_recette,duree,prix,id_origine) VALUES 
(1,"plat","Hachis parmentier",45,0,1),
(2,"dessert","Quatre-Quarts",55,0,1),
(3,"plat","Spaghetti à la napolitaine",40,0,2),
(4,"boisson","Thé à la menthe",10,0,3),
(5,"entree","Oeufs mimosa",35,0,1)
;

INSERT INTO Menu(id_menu,nom) VALUES 
(1,"Menu franc");

INSERT INTO Utiliser(id_etape,id_ustensile) VALUES 
(1,1),
(1,3),
(1,6),
(3,4),
(4,5),
(4,2),
(6,3),
(6,12),
(7,3),
(7,12),
(8,3),
(8,12),
(9,2),
(9,14),
(10,1),
(10,16),
(11,4),
(11,9),
(11,6),
(12,17),
(13,7),
(14,9),
(14,18),
(15,9),
(16,19),
(17,20),
(18,9),
(18,1),
(19,12),
(20,21),
(21,1)
;

INSERT INTO Comporter(id_ingredient,id_recette,quantite) VALUES 
(2,1,2),
(3,1,2),
(8,1,30),
(4,1,400),
(12,1,0.4),
(5,1,1),
(9,1,20),
(11,1,10),
(10,1,50),
(13,1,1),
(6,1,1),
(7,1,30),
(1,1,300),
(13,2,2),
(5,2,140),
(8,2,140),
(14,2,140),
(16,3,300),
(3,3,1),
(4,3,300),
(2,3,100),
(5,3,15),
(18,3,5),
(19,3,5),
(20,3,5),
(25,4,500),
(26,4,10),
(27,4,12),
(14,4,150),
(13,5,5),
(28,5,5),
(29,5,5),
(30,5,250)
;

INSERT INTO Subdiviser(id_recette,id_etape,ordre) VALUES 
(1,1,1),
(1,2,2),
(1,3,3),
(1,4,4),
(2,5,1),
(2,6,2),
(2,7,3),
(2,8,4),
(2,9,5),
(3,10,1),
(3,11,2),
(3,12,3),
(3,13,4),
(3,14,5),
(4,15,1),
(4,16,2),
(4,17,3),
(5,18,1),
(5,19,2),
(5,20,3),
(5,21,4)
;

INSERT INTO Impliquer(id_etape,id_ingredient) VALUES 
(1,2),
(1,3),
(1,8),
(2,4),
(2,12),
(2,5),
(2,9),
(2,11),
(2,6),
(3,13),
(4,1),
(4,10),
(6,13),
(6,14),
(7,8),
(8,5),
(8,13),
(10,2),
(10,3),
(10,4),
(11,2),
(12,3),
(12,5),
(13,4),
(13,18),
(13,19),
(13,20),
(14,16),
(15,25),
(15,26),
(16,27),
(17,14),
(18,13),
(19,13),
(19,28),
(19,30),
(21,20)
;

INSERT INTO Servir(id_menu,id_recette)
VALUES
(1,1),
(1,2),
(1,5)
;
