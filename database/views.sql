DROP VIEW IF EXISTS statistiques;
DROP VIEW IF EXISTS menu_avec_plat;
DROP VIEW IF EXISTS plats_par_menu;
DROP VIEW IF EXISTS nb_menus_origine;
DROP VIEW IF EXISTS recette_par_origine;
DROP VIEW IF EXISTS prix_recette;
DROP VIEW IF EXISTS nb_etapes;
DROP VIEW IF EXISTS ingredients_recette;
DROP VIEW IF EXISTS ustensiles_par_recette;
DROP VIEW IF EXISTS plats_par_ingredient;
DROP VIEW IF EXISTS nb_ingredients_recette;
DROP VIEW IF EXISTS informations_par_recette;
DROP VIEW IF EXISTS recettes_avec_origine;

--Affiche le nombre de plats, de menus, d'ingrédients et d'ustensiles dans la base
CREATE VIEW statistiques AS
    SELECT nb_recettes, nb_menus, nb_ingredients, nb_ustensiles, nb_origines
    FROM
    (SELECT count(id_recette) nb_recettes FROM Recette) stats_recettes,
    (SELECT count(id_menu) nb_menus FROM Menu) stats_menus,
    (SELECT count(id_ingredient) nb_ingredients FROM Ingredient) stats_ingredients,
    (SELECT count(id_ustensile) nb_ustensiles FROM Ustensile) stats_ustensiles,
    (SELECT count(id_origine) nb_origines FROM Origine) stats_origine
;

--Affiche la liste des menu avec leur plats
CREATE VIEW menu_avec_plat AS
    SELECT Servir.id_menu, Servir.id_recette 
    FROM Menu NATURAL JOIN Servir NATURAL JOIN Recette 
    GROUP BY Servir.id_menu;
;

--Affiche le nombre d'ingrédients par recette
CREATE VIEW nb_ingredients_recette AS
    SELECT nom_recette, count(id_ingredient) as decompte
    FROM Ingredient NATURAL JOIN Comporter NATURAL JOIN Recette
    GROUP BY id_recette
    ORDER BY nom_recette
;

--Affiche la liste d'un menu avec ces plats
CREATE VIEW plats_par_menu AS
  WITH Service AS (SELECT * FROM Recette NATURAL JOIN Servir NATURAL JOIN Menu)
  SELECT nom, nom_recette
  FROM Service
  ORDER BY (nom_recette)
;

--Affiche le nombre de menus avec un plat d'une origine
CREATE VIEW nb_menus_origine AS
    SELECT origine, count(id_menu) as decompte
    FROM Menu NATURAL JOIN Servir NATURAL JOIN Recette NATURAL JOIN Origine
    GROUP BY (id_origine)
;

--Affiche le nombre de plats avec une origine
CREATE VIEW recettes_par_origine AS 
  WITH Originaire AS (SELECT * FROM Recette NATURAL JOIN Origine)
  SELECT origine, nom_recette
  FROM Originaire
  ORDER BY (origine)
;

CREATE VIEW recettes_avec_origine AS
  SELECT id_recette, type_recette, nom_recette, duree, prix, origine 
  FROM Recette NATURAL JOIN Origine
  ORDER BY (nom_recette)
;

--Affiche le prix d'une recette
CREATE VIEW prix_recette AS
    with R as( SELECT * 
    FROM Recette NATURAL JOIN Comporter NATURAL JOIN Ingredient)
    SELECT sum(R.prix_ingredient)
    FROM R
;

--Affiche le nombre d'étape d'une recette
CREATE VIEW nb_etapes AS
    SELECT count(id_etape) as nbEtapes
    FROM Etape NATURAL JOIN Subdiviser NATURAL JOIN Recette
    GROUP BY (id_recette)
;

--Affiche les ingredient pour une recette
CREATE VIEW ingredients_recette AS
    SELECT nom_recette, nom_ingredient
    FROM Ingredient NATURAL JOIN Recette NATURAL JOIN Comporter
    ORDER BY nom_recette, nom_ingredient
;

--Affiche les ustensible pour une recette
CREATE VIEW ustensiles_par_recette AS 
  WITH Utilisation AS (SELECT * FROM Ustensile NATURAL JOIN Utiliser NATURAL JOIN Etape NATURAL JOIN Subdiviser NATURAL JOIN Recette)
  SELECT DISTINCT nom_recette,nom_ustensile
  FROM Utilisation
  ORDER BY (nom_recette)
;

/*--Affiche les etapes pour une recette
CREATE VIEW informations_par_recette AS 
  WITH EtapeRecette AS (SELECT * FROM Recette NATURAL JOIN Subdiviser NATURAL JOIN Etape)
  SELECT (nom_recette, nom_etape, (contenu_etape as chaine) FROM EtapeRecette)
  UNION (SELECT nom_recette, nom_etape, (nom_ustensile as chaine) FROM EtapeRecette NATURAL JOIN Utiliser NATURAL JOIN Ustensile)
  UNION SELECT (nom_recette, nom_etape, (nom_ingredient as chaine) FROM EtapeRecette NATURAL JOIN Impliquer NATURAL JOIN Ingredient)
  ORDER BY (nom_recette,nom_etape)
;*/

--Affiche les recette pour une ingredient
CREATE VIEW plats_par_ingredient AS 
  WITH Jointure AS (SELECT * FROM Ingredient NATURAL JOIN Comporter NATURAL JOIN Recette)
  SELECT nom_ingredient, nom_recette
  FROM Jointure
  ORDER BY nom_ingredient, nom_recette
;




