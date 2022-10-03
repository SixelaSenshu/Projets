PRAGMA foreign_keys = true;
DROP TABLE IF EXISTS Impliquer;
DROP TABLE IF EXISTS Comporter;
DROP TABLE IF EXISTS Subdiviser;
DROP TABLE IF EXISTS Utiliser;
DROP TABLE IF EXISTS Servir;
DROP TABLE IF EXISTS Ingredient;
DROP TABLE IF EXISTS Etape;
DROP TABLE IF EXISTS Ustensile;
DROP TABLE IF EXISTS Recette;
DROP TABLE IF EXISTS Origine;
DROP TABLE IF EXISTS Menu;


CREATE TABLE Ingredient(
    id_ingredient integer PRIMARY KEY AUTOINCREMENT,
    prix_ingredient float NOT NULL,
    nom_ingredient varchar(30) NOT NULL,
    type_ingredient varchar(30) NOT NULL,
   type_mesure varchar(5) CHECK (type_mesure in ("c","u","ml","cl","l","mg","cg","g","kg"))
);

CREATE TABLE Etape(
    id_etape integer PRIMARY KEY AUTOINCREMENT,
    nom_etape varchar(30) NOT NULL,
    contenu_etape varchar(200) NOT NULL
);

CREATE TABLE Ustensile(
    id_Ustensile integer PRIMARY KEY AUTOINCREMENT,
    nom_Ustensile varcha(30) NOT NULL
);

CREATE TABLE Recette(
    id_recette integer PRIMARY KEY AUTOINCREMENT,
    type_recette varchar(10) CHECK(type_recette IN ("dessert","entree","plat","boisson")),
    nom_recette varchar(50),
    duree integer CHECK (duree > 0),
    prix float NOT NULL,
    id_origine integer REFERENCES Origine(id_origine)
);

CREATE TABLE Origine(
    id_origine integer PRIMARY KEY AUTOINCREMENT,
    origine varchar(30) NOT NULL
);

CREATE TABLE Menu(
    id_menu integer PRIMARY KEY AUTOINCREMENT,
    nom varchar(50) NOT NULL
);

CREATE TABLE Impliquer(
    id_etape integer REFERENCES Etape(id_etape) ON UPDATE CASCADE ON DELETE CASCADE,
    id_ingredient integer REFERENCES Ingredient(id_ingredient) ON UPDATE CASCADE ON DELETE CASCADE,

    CONSTRAINT impliquer_pk PRIMARY KEY (id_etape,id_ingredient)
);

CREATE TABLE Comporter(
    id_ingredient integer REFERENCES Ingredient(id_ingredient) ON UPDATE CASCADE ON DELETE CASCADE,
    id_recette integer REFERENCES Recette(id_recette) ON UPDATE CASCADE ON DELETE CASCADE,
    quantite integer NOT NULL CHECK (quantite > 0),

    CONSTRAINT comporter_pk PRIMARY KEY (id_ingredient,id_recette)
);

CREATE TABLE Subdiviser(
    id_etape integer REFERENCES Etape(id_etape) ON UPDATE CASCADE ON DELETE CASCADE,
    id_recette integer REFERENCES Recette(id_recette) ON UPDATE CASCADE ON DELETE CASCADE,
    ordre integer NOT NULL CHECK (ordre > 0),

    CONSTRAINT unicite_ordre_recette UNIQUE (id_recette,ordre),
    CONSTRAINT subdiviser_pk PRIMARY KEY (id_etape,id_recette)
);

CREATE TABLE Utiliser(
    id_etape integer REFERENCES Etape(id_etape) ON UPDATE CASCADE ON DELETE CASCADE,
    id_Ustensile integer REFERENCES Ustensile(id_ustensile) ON UPDATE CASCADE ON DELETE CASCADE,

    CONSTRAINT impliquer_pk PRIMARY KEY (id_etape,id_Ustensile)
);

CREATE TABLE Servir(
  id_menu integer REFERENCES Menu(id_menu) ON UPDATE CASCADE ON DELETE CASCADE,
  id_recette integer REFERENCES Recette(id_recette) ON UPDATE CASCADE ON DELETE CASCADE,

  CONSTRAINT servir_pk PRIMARY KEY (id_menu,id_recette)
);
