--Index pour acceder uniquement aux prix des ingrédients--
CREATE INDEX prix_nom_ingredient ON Ingredient(id_ingredient,prix_ingredient);
--Index pour acceder uniquement aux données type des ingrédients--
CREATE INDEX type_ingredient ON Ingredient(id_ingredient,nom_ingredient,type_ingredient,type_mesure);
--Index pour les valeurs d'une recettes--
CREATE INDEX type_recette ON Recette(type_recette,nom_recette,id_origine);
