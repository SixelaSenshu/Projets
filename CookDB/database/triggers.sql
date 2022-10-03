DROP TRIGGER IF EXISTS ajout_prix;
DROP TRIGGER IF EXISTS retrait_prix_cascade;
DROP TRIGGER IF EXISTS modification_quantite_changement_prix;
DROP TRIGGER IF EXISTS modification_ingredient_changement_prix;
DROP TRIGGER IF EXISTS modification_recette_changement_prix;
DROP TRIGGER IF EXISTS comporter_supression;
DROP TRIGGER IF EXISTS impliquer_supression;
DROP TRIGGER IF EXISTS servir_supression;
DROP TRIGGER IF EXISTS subdiviser_supression;
DROP TRIGGER IF EXISTS utiliser_supression;

CREATE TRIGGER ajout_prix
AFTER INSERT ON Comporter
BEGIN
    UPDATE Recette
    SET prix = prix + (SELECT (prix_ingredient*quantite)
    FROM Ingredient NATURAL JOIN Comporter
    WHERE id_ingredient = new.id_ingredient AND id_recette = new.id_recette)
    WHERE id_recette = new.id_recette;
END;

CREATE TRIGGER retrait_prix_cascade
AFTER DELETE ON Comporter
BEGIN
    UPDATE Recette
    SET prix = (SELECT sum(prix_ingredient*quantite)
    FROM Ingredient NATURAL JOIN Comporter WHERE id_recette = old.id_recette)
    WHERE id_recette = old.id_recette;
END;

CREATE TRIGGER modification_quantite_changement_prix
AFTER UPDATE OF quantite ON Comporter
BEGIN
    UPDATE Recette
    SET prix = (SELECT sum(prix_ingredient*quantite)
    FROM Ingredient NATURAL JOIN Comporter WHERE id_recette = old.id_recette)
    WHERE id_recette = old.id_recette;
END;

CREATE TRIGGER modification_ingredient_changement_prix
AFTER UPDATE OF id_ingredient ON Comporter
BEGIN
    UPDATE Recette
    SET prix = (SELECT sum(prix_ingredient*quantite)
    FROM Ingredient NATURAL JOIN Comporter WHERE id_recette = old.id_recette)
    WHERE id_recette = old.id_recette;
END;

CREATE TRIGGER modification_recette_changement_prix
AFTER UPDATE OF id_recette ON Comporter
BEGIN
    UPDATE Recette
    SET prix = (SELECT sum(prix_ingredient*quantite)
    FROM Ingredient NATURAL JOIN Comporter WHERE id_recette = old.id_recette)
    WHERE id_recette = old.id_recette;
    UPDATE Recette
    SET prix = (SELECT sum(prix_ingredient*quantite)
    FROM Ingredient NATURAL JOIN Comporter WHERE id_recette = new.id_recette)
    WHERE id_recette = new.id_recette;
END;

CREATE TRIGGER comporter_supression
AFTER UPDATE ON Comporter
WHEN new.id_recette = NULL OR new.id_ingredient = NULL
BEGIN
    DELETE FROM Comporter WHERE id_recette = NULL OR id_ingredient = NULL;
END;

CREATE TRIGGER impliquer_supression
AFTER UPDATE ON Impliquer
WHEN new.id_etape = NULL OR new.id_ingredient = NULL
BEGIN
    DELETE FROM Impliquer WHERE id_etape = NULL OR id_ingredient = NULL;
END;

CREATE TRIGGER utiliser_supression
AFTER UPDATE ON Utiliser
WHEN new.id_etape = NULL OR new.id_ustensile = NULL
BEGIN
    DELETE FROM Ustensile WHERE id_etape = NULL OR id_ustensile = NULL;
END;

CREATE TRIGGER servir_supression
AFTER UPDATE ON Servir
WHEN new.id_recette = NULL OR new.id_menu = NULL
BEGIN
    DELETE FROM Servir WHERE id_recette = NULL OR id_menu = NULL;
END;

CREATE TRIGGER subdiviser_supression
AFTER UPDATE ON Subdiviser
WHEN new.id_recette = NULL OR new.id_etape = NULL
BEGIN
    DELETE FROM Subdiviser WHERE id_recette = NULL OR id_menu = NULL;
END;
