package vueTexte;

import databaseGestionnary.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class ModeTexte {

    private boolean continuer = true;
    BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

    /** Constructeur du mode textuel de la base de données.
     * Affiche un message de bienvenue avant d'initialiser la boucle d'événements.
     * @throws IOException Renvoie les exceptions d'entrée/sortie.
     * @throws SQLException Renvoie les exceptions liées au SQL.
     */
    public ModeTexte() throws IOException, SQLException {
        System.out.println("Bienvenue, utilisateur.");
        System.out.println("Afin de profiter pleinement de cette application, veuillez suivre les instructions à l'écran.");
        while (continuer) {
            System.out.println("Voulez-vous :");
            System.out.println("1 - Afficher des données");
            System.out.println("2 - Insérer des données");
            System.out.println("3 - Mettre à jour des données");
            System.out.println("4 - Supprimer des données");
            System.out.println("5 - Quitter le programme");
            traitementAction();
            //System.out.printf("Afficher une vue (V) ou une table (T) ? ");
        }
    }

    /** Fonction qui permet de saisir une chaine de caractère
    * @return Une chaine de caractères saisie
    * @throws IOException Exception lors de la saisie de données
    */
    public String lectureChaine() throws IOException {
        return buffer.readLine();
    }

    /** Fonction qui permet de saisir un caractère
    * @return le caractère saisie
    * @throws IOException Exception lors de la saisie de données
    */
    public char lectureCaractere() throws IOException {
        return buffer.readLine().charAt(0);
    }

    /** Fonction qui permet de sélectionner une choix de traitement
    * @throws IOException Exception lors de la saisie de données
    * @throws SQLDataException Exception lors de la manipulatin de la base de données
    */
    public void traitementAction() throws IOException, SQLException {
        char action = lectureCaractere();
        switch (action) {
            case '1':
                System.out.println("Consulter des données");
                traitementRecherche();
                break;
            case '2':
                System.out.println("Ajout de données");
                traitementInsertion();
                break;
            case '3':
                System.out.println("Mise à jour de données");
                traitementMiseAJour();
                break;
            case '4':
                System.out.println("Suppression de données");
                traitementSuppression();
                break;
            case '5':
                System.out.println("À bientôt !");
                System.exit(0);
        }
    }

     /** Fonction qui permet de traiter la selection de données
    * @throws IOException Exception lors de la saisie de données
    * @throws SQLDataException Exception lors de la manipulatin de la base de données
    */
    public void traitementRecherche() throws IOException, SQLException {
        boolean end = false;
        System.out.printf("Souhaitez-vous visionner une vue (V) ou une table (T) ? ");
        String ensemble;
        switch (lectureCaractere()) {
            case 'V':
            case 'v':
                ensemble = rechercheVue();
                break;
            case 'T':
            case 't':
            default:
                ensemble = rechercheTable();
                break;
        }

        Select req = new Select(ensemble);
        Select reqComplet = new Select(ensemble);
        List<String> nomsColonnes = req.getColumnsNames();
        String attribut = "";

        for (String colonne : nomsColonnes) {
            System.out.printf(colonne + " / ");
        }
        System.out.printf("\n");
        char choix = ' '; 
        while (choix != 'N' && choix != 'n' && choix != '0') {
            System.out.printf("Voulez-vous observer un attribut particulier ? (Y/N) ");
            choix = lectureCaractere();
            if (choix == 'Y' || choix == 'y' || choix == '1') {
                while (!nomsColonnes.contains(attribut)) {
                    System.out.printf("Veuillez sélectionner un attribut à observer");
                    attribut = lectureChaine();
                }
                req.ajouterAttribut(attribut);
            }
            System.out.println(choix);
        }
        try {
            while (!end) {
                System.out.printf("Voulez vous ajouter une condition ?");
                choix = lectureCaractere();
                switch(choix){
                    case '1':
                    case 'y':
                    case 'Y':
                    case 'o':
                    case 'O':
                        ajouterCondition(req,req);
                        break;
                    case '0':
                    case 'n':
                    case 'N':
                    default:
                        end = true;
                        break;
                    }
                System.out.println(req);
            }
        } catch (IOException io) {
            throw new IOException();
        }
    }

    public String rechercheTable() throws IOException, SQLException {
        String nomTable = "";
        List<String> nomsTables = (new DBMetaData()).getNomsTables();
        for (String table : nomsTables) {
            System.out.printf(table + " / ");
        }
        System.out.printf("\n");
        while (!nomsTables.contains(nomTable)) {
            System.out.printf("Veuillez entrer la table à chercher. ");
            nomTable = lectureChaine();
        }
        return nomTable;
    }

    public String rechercheVue() throws IOException, SQLException {
        String nomVue = "";
        List<String> nomsVues = (new DBMetaData()).getNomsVues();
        for (String vue : nomsVues) {
            System.out.printf(vue + " / ");
        }
        System.out.printf("\n");
        while (!nomsVues.contains(nomVue)) {
            System.out.printf("Veuillez entrer la vue à chercher. ");
            nomVue = lectureChaine();
        }
        return nomVue;
    }

     /** Fonction qui permet de traiter la mise à jour de données
    * @throws IOException Exception lors de la saisie de données
    * @throws SQLDataException Exception lors de la manipulatin de la base de données
    */
    public void traitementInsertion() throws IOException, SQLException {
        List<String> nomsColonnes = null;
        //List<String> typeColonnes = null;
        List<String> nomsTables = (new DBMetaData()).getNomsTables();
        String nomTable = "";
        while (!nomsTables.contains(nomTable)) {
            System.out.printf("Veuillez entrer la table à renseigner. ");
            nomTable = lectureChaine();
        }
        ArrayList<String> variables = new ArrayList<String>();
        Insertion ins = new Insertion(nomTable);
        ins.remplissageAutomatique(false);
        nomsColonnes = ins.getAttributs();
        for (int i = 0; i < nomsColonnes.size(); i++) {
            System.out.printf("Veuillez rajouter une donnée pour " + nomsColonnes.get(i) + ". ");
            variables.add(lectureChaine());
        }
        ins.ajouterValeur(variables);
        ins.executerUpdate();
    }


     /** Fonction qui permet de traiter la mise à jour de données
    * @throws IOException Exception lors de la saisie de données
    * @throws SQLDataException Exception lors de la manipulatin de la base de données
    */
    public void traitementMiseAJour() throws IOException, SQLException{
        boolean end = false;
        String attribut = "";
        String newValeur;
        Character choix;
        try{
            List<String> nomsTables = (new DBMetaData()).getNomsTables();
            String nomTable = "";
            while (!nomsTables.contains(nomTable)) {
                System.out.printf("Veuillez entrer la table qui sera mise à jour. ");
                nomTable = lectureChaine();
            }

            Select req = new Select(nomTable);
            List<String> attributs = req.getColumnsNames();

            for (String att : attributs) {
                System.out.printf(att + " / ");
            }
            System.out.printf("\n");
            while (!attributs.contains(attribut)) {
                System.out.printf("Veuillez entrer l'attribut qui sera mis à jour, parmi ceux ci-dessus.");
                attribut = lectureChaine();
            }

            System.out.printf("Veuillez entrer la valeur qui sera placée dans l'attribut. ");
            newValeur = lectureChaine();

            Update upt = new Update(nomTable,attribut,newValeur);

            while (!end) {
                System.out.printf("Voulez vous ajouter une condition ? ");
                choix = lectureCaractere();
                switch(choix){
                    case '1':
                    case 'y':
                    case 'Y':
                    case 'o':
                    case 'O':
                        ajouterCondition(upt,req);
                        break;
                    case '0':
                    case 'n':
                    case 'N':
                    default:
                        end = true;
                        break;
                    }
                }
            upt.executerUpdate();
        }catch(IOException io){
            throw new IOException();
        }
    } 

    /** Fonction qui permet de traiter la suppresion de données
    * @throws IOException Exception lors de la saisie de données
    * @throws SQLException Exception lors de la manipulatin de la base de données
    */
    public void traitementSuppression() throws IOException , SQLException{
        boolean end = false;
        Character choix;
        try{
            List<String> nomsTables = (new DBMetaData()).getNomsTables();
            String nomTable = "";
            while (!nomsTables.contains(nomTable)) {
                System.out.printf("Veuillez entrer la table qui sera mise à jour. ");
                nomTable = lectureChaine();
            }

            Select req = new Select(nomTable);
            Delete upt = new Delete(nomTable);
            
            while (!end) {
                System.out.printf("Voulez vous ajouter une condition ? ");
                choix = lectureCaractere();
                switch(choix){
                    case '1':
                    case 'y':
                    case 'Y':
                    case 'o':
                    case 'O':
                        ajouterCondition(upt,req);
                        break;
                    case '0':
                    case 'n':
                    case 'N':
                    default:
                        end = true;
                        break;
                    }
                }
            upt.executerUpdate();
        }catch(IOException io){
            throw new IOException();
        }
    }

    /** Fonction qui permet l'ajout d'une condition à une requête conditionelle
    * @param mod Requête actuelle sur laquelle sera ajouter la nouvelle condition
    * @param req Requête Select servant à afficher les attributs
    * @return Si l'utilisateur veut continuer à générer une condition
    * @throws SQLException Renvoie les exceptions liées au SQL.
    * @throws IOException Exception lors de la saisie de données
    */
    public boolean ajouterCondition(ModificationConditionnelle mod, Select req) throws IOException, SQLException {
        
        List<String> attributs = req.getColumnsNames();
        for (String att : attributs) {
            System.out.printf(att + " / ");
        }
        System.out.printf("\n");
        
        String condition = "";

        while (attributs.contains(condition)) {
            System.out.printf("Quel attribut voulez vous soumettre à condition, parmi ceux ci-dessus ? ");
            condition = lectureChaine();
        }

        String[] contraintes = {"=",">=","<=",">","<","LIKE"};

        System.out.println("1 - =");
        System.out.println("2 - >=");
        System.out.println("3 - <=");
        System.out.println("4 - >");
        System.out.println("5 - <");
        System.out.println("6 - LIKE ");
        char choix = '\0';
        while ('1' <= choix && choix <= '6') {
            System.out.printf("Quelle sera la nature de la contrainte ? ");
            choix = lectureCaractere();
        }
        String type = contraintes[Integer.valueOf(choix) - Integer.valueOf('1')];

        System.out.println("Quelle sera valeur exercée par la contrainte ? ");
        String valeur = lectureChaine();

        System.out.println("Voulez-vous ajouter un opérateur, afin de préparer une autre condition ?");
        char choice = lectureCaractere();
        switch (choice) {
            case '0':
            case 'n':
            case 'N':
                mod.ajouterCondition(condition, type, valeur, "");
                return false;
            case '1':
            case 'y':
            case 'Y':
            case 'o':
            case 'O':
                System.out.printf("Quel opérateur ?");
                String operateurLogique = lectureChaine();
                mod.ajouterCondition(condition, type, valeur, operateurLogique);
                return true;
            default:
                return false;
        }
    }

    /** Fonction qui permet l'ajout d'une condition à une recherche
    * @param select Requête actuelle sur laquelle sera ajouter la nouvelle condition
    * @param req Récupérer les attributs, afin d'aiguiller l(uit)
    * @return Si l'utilisateur veut continuer à générer une condition
    * @throws SQLDataException Exception lors de la manipulatin de la base de données
    * @throws IOException Exception lors de la saisie de données
    */
    public boolean ajouterCondition(Select select, Select req) throws IOException, SQLException {
        
        List<String> attributs = req.getColumnsNames();
        for (String att : attributs) {
            System.out.printf(att + " / ");
        }
        System.out.printf("\n");
        
        String condition = "";

        while (attributs.contains(condition)) {
            System.out.printf("Quel attribut voulez vous soumettre à condition, parmi ceux ci-dessus ? ");
            condition = lectureChaine();
        }

        String[] contraintes = {"=",">=","<=",">","<","LIKE"};

        System.out.println("1 - =");
        System.out.println("2 - >=");
        System.out.println("3 - <=");
        System.out.println("4 - >");
        System.out.println("5 - <");
        System.out.println("6 - LIKE ");
        char choix = '\0';
        while ('1' <= choix && choix <= '6') {
            System.out.printf("Quelle sera la nature de la contrainte ? ");
            choix = lectureCaractere();
        }
        String type = contraintes[Integer.valueOf(choix) - Integer.valueOf('1')];

        System.out.printf("Quelle sera valeur exercée par la contrainte ? ");
        String valeur = lectureChaine();

        System.out.printf("Voulez-vous ajouter un opérateur, afin de préparer une autre condition ?");
        char choice = lectureCaractere();
        switch (choice) {
            case '0':
            case 'n':
            case 'N':
                select.ajouterCondition(condition, type, valeur, "");
                return false;
            case '1':
            case 'y':
            case 'Y':
            case 'o':
            case 'O':
                System.out.printf("Quel opérateur ?");
                String operateurLogique = lectureChaine();
                select.ajouterCondition(condition, type, valeur, operateurLogique);
                return true;
            default:
                return false;
        }
    }
}