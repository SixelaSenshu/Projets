package databaseGestionnary;

import java.util.*;
import java.sql.*;

public class DBMetaData {
    
    private List<String> nomsTables;
    private List<String> nomsVues;
    private Map<String,List<String>> clesPrimaires;
    private DatabaseConnection connectionBase;
    
    /** Contrusteur de l'instance DBMetaData , qui contient des informations sur la base de données
     *@throws SQLException Renvoie les exceptions liées au SQL.
    */
    public DBMetaData() throws SQLException {
        connectionBase = new DatabaseConnection("jdbc:sqlite:./database/database.db");
        connectionBase.connectionDataBase();
        genererNomsTables();
        connectionBase.StopConnection();
    }

    /** Fonction qui permet de récuperer les noms des attributs des tables
     *@throws SQLException Renvoie les exceptions liées au SQL.
    */
    public void genererNomsTables() throws SQLException {
        String[] type = {"TABLE"};
        String[] type2 = {"VIEW"};
        DatabaseMetaData md = connectionBase.getDBMetaData();
        ResultSet rs = md.getTables(null, null, "%", type);
        ResultSet rs2 = md.getTables(null, null, "%", type2);
        ResultSet rsClesPrimaires;
        nomsTables = new ArrayList<String>();
        nomsVues = new ArrayList<String>();
        clesPrimaires = new HashMap<String,List<String>>();
        while (rs.next()) {
            if (rs.getString(3) != "sqlite_sequence") {
                nomsTables.add(rs.getString(3));
                rsClesPrimaires = md.getPrimaryKeys(null, null, rs.getString(3));
                clesPrimaires.put(rs.getString(3),new ArrayList<String>());
                while (rsClesPrimaires.next()) {
                    clesPrimaires.get(rs.getString(3)).add(rsClesPrimaires.getString(4));
                }
            }
        }
        while (rs2.next()) {
            nomsVues.add(rs2.getString(3));
        }
    }

    /** Fonction qui permet de récupérer les clé primaires.
    * @param table La table dont on souhaite récupérer les clés primaires.
    * @return Les clés primaires définissant la table.
    */
    public List<String> getClesPrimaires(String table) {
        assert nomsTables.contains(table);
        return clesPrimaires.get(table);
    }

    /** Méthode qui permet de récupérer les noms des tables
    * @return Les nom des tables
    */
    public List<String> getNomsTables() {
        return nomsTables;
    }

    /** Méthode qui permet de récupérer les noms des vues.
    * @return Les nom des vues
    */
    public List<String> getNomsVues() {
        return nomsVues;
    }
}
