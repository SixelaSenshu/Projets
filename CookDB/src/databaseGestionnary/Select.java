package databaseGestionnary;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Classe qui initialise les Requêtes SELECT et en retourne les résultats.
 * Initialiser une requête et en récupérer les résultats peut se faire ainsi :
 * Select req
 */
public class Select {

    private List<String> ensembles;
    private List<String> attributs;
    private List<String> attributsContraints;
    private List<String> typesContraintes;
    private List<String> valeursContraintes;
    private List<String> operateursLogiques;
    private ResultSet rslt;
    private DatabaseConnection connectionBase;

    private List<Map<String,String>> resultats;
    private List<String> attributsResultats;
    private List<String> typeAttributsResultats;

    /** Constructeur d'un objet Select.
     * @param ensembles Liste des ensembles dans lesquels se feront la requête.
     * @param attributs Liste des attributs demandés dans la requête.
     * @param attributsContraints Liste des attributs qui subissent une contrainte. Constituera les contraintes.
     * @param typesContraintes Liste des types de contraintes exercées sur un attribut.
     * @param valeursContraintes Liste des variables pour que la contrainte soit exercée.
     * @param operateursLogiques Liste des opérateurs logiques s'exerçant entre les contraintes.
     */
    public Select(List<String> ensembles, List<String> attributs, List<String> attributsContraints, List<String> typesContraintes, List<String> valeursContraintes, List<String> operateursLogiques) {
        this.ensembles = ensembles;
        this.attributs = attributs;
        this.attributsContraints = attributsContraints;
        this.typesContraintes = typesContraintes;
        this.valeursContraintes = valeursContraintes;
        this.operateursLogiques = operateursLogiques;
    }

    /** Constructeur d'un objet Select. La requête est initialisée sans contraintes.
     * @param ensembles Liste des ensembles dans lesquels se feront la requête.
     * @param attributs Liste des attributs demandés dans la requête.
     */
    public Select(List<String> ensembles, List<String> attributs) {
        this(ensembles,attributs,new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>());
    }

    /** Constructeur d'un objet Select. La requête est initialisée sans contraintes, et prend tous les attributs.
     * @param ensembles Liste des ensembles dans lesquels se feront la requête.
     */
    public Select(List<String> ensembles) {
        this(ensembles,Stream.of("*").collect(Collectors.toList()));
    }

    /** Constructeur d'un objet Select. La requête est initialisée sans contraintes, prend tous les attributs et se base sur un seul ensemble.
     * @param ensemble Liste des ensembles dans lesquels se feront la requête.
     */
    public Select(String ensemble) {
        this(Stream.of(ensemble).collect(Collectors.toList()),Stream.of("*").collect(Collectors.toList()));
    }

    /**
     * Ajoute un attribut à la liste de ceux demandés.
     * Réinitialise la liste au préalable si l'attribut ajouté est * ou si le seul attribut présent est *.
     * @param attribut Nouvel attribut qui sera importé dans la requête
     */
    public void ajouterAttribut(String attribut) {
        if (attributs.get(0) == "*" || attribut == "*") {
            attributs.clear();
        }
        attributs.add(attribut);
    }
    
    /**
     * Retourne une représentation textuelle de la requête SQL.
     * @return Une représenattion textuelle de la requête SQL.
     */
    public String formulerRequete() {
        assert attributsContraints.size() <= typesContraintes.size();
        assert attributsContraints.size() <= valeursContraintes.size();
        assert attributsContraints.size() <= operateursLogiques.size();
        StringBuffer bufferGen = new StringBuffer("SELECT ");
        //On fournit les attributs à afficher.
        for (int i = 0; i < attributs.size(); i++) {
            bufferGen.append(attributs.get(i));
            if (i != attributs.size() - 1) {
                bufferGen.append(",");
            } else {
                bufferGen.append(" ");
            }
        }
        bufferGen.append("FROM ");
        //On fournit les ensembles.
        for (int i = 0; i < ensembles.size(); i++) {
            bufferGen.append(ensembles.get(i));
            if (i != ensembles.size() - 1) {
                bufferGen.append(",");
            }
        }
        //S'il y a des conditions, on fournit les conditions.
        if (attributsContraints.size() > 0) {
            bufferGen.append(" WHERE ");
            for (int i = 0; i < attributsContraints.size(); i++) {
                bufferGen.append(attributsContraints.get(i) + " ");
                bufferGen.append(typesContraintes.get(i) + " ");
                bufferGen.append(valeursContraintes.get(i) + " ");
                if (i != ensembles.size() - 1) {
                    bufferGen.append(operateursLogiques.get(i));
                }
            }
        }
        bufferGen.append(";");
        return bufferGen.toString();
    }
    
    /** 
     * Execute la requête avec la requête actuellement rédigée.
     * Génère les résultats en concordance avec ce qui est obtenu.
     * @throws SQLException On renvoie les exceptions SQL
     */
    public void executerRequete() throws SQLException {
        try {
            connectionBase = new DatabaseConnection("jdbc:sqlite:./database/database.db");
            connectionBase.executerRecherche(formulerRequete());
            rslt = connectionBase.getResultSet();
        } catch (SQLException e) {
            System.out.println(e);
            System.exit(0);
        }
        try {
            genererResultats();
        } catch (SQLException e) {
            System.out.println(e);
            System.exit(0);
        }
        connectionBase.StopConnection();
    }

    /**
     * Génère une liste de dictionnaires contenant les attributs et les données pour chaque ligne résultant de la requête.
     * @throws SQLException On renvoie les exceptions SQL
     */
    public void genererResultats() throws SQLException {
        assert rslt != null;
        genererNomsColonnes();
        resultats = new ArrayList<Map<String,String>>();
        HashMap<String,String> donnee;
        String nomAttribut;
        while (rslt.next() != false) {
            donnee = new HashMap<String,String>();
            for (int i = 0; i < attributsResultats.size() ; i++) {
                nomAttribut = attributsResultats.get(i);
                donnee.put(nomAttribut,rslt.getString(nomAttribut));
            }
            resultats.add(donnee);
        }
    }

    /** Génère une liste des noms de colonne de la table de la requête
    * @throws SQLException On renvoie les exceptions SQL
    */
    public void genererNomsColonnes() throws SQLException {
        ResultSetMetaData rsmd = rslt.getMetaData();
        attributsResultats = new ArrayList<String>();
        typeAttributsResultats = new ArrayList<String>();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            attributsResultats.add(rsmd.getColumnName(i));
            typeAttributsResultats.add(rsmd.getColumnTypeName(i));
        }
    }

    /**
     * Ajoute une condition à la requête.
     * @param attribut L'attribut soumis à la contrainte.
     * @param contrainte Le type de contrainte exercée. Il peut s'agir d'un opérateur booléen, comme d'opérations plus complexes, comme LIKE.
     * @param valeur La valeur de l'attribut soumis à la contraite
     * @param operateurSuivant L'opérateur après la contraite
     */
    public void ajouterCondition(String attribut, String contrainte, String valeur, String operateurSuivant) {
        if (operateurSuivant == "") {
            operateurSuivant = "AND";
        }
        attributsContraints.add(attribut);
        typesContraintes.add(contrainte);
        valeursContraintes.add(valeur);
        operateursLogiques.add(operateurSuivant);
    }

    /** Fonction qui écrit dans la console l'ensemble des résultats.
     * Sert à des fins de déboggage pour vérifier si les résultats visibles sur l'interface graphique sont cohérents avec ceux écrits.
     * @return Une représentation textuelle du résultat.
     */ 
    public String toString() {
        try {
            this.executerRequete();
            if (resultats.size() == 0) {
                return "";
            }
            StringBuffer buffer = new StringBuffer();
            for (String attribut : resultats.get(0).keySet()) {
                buffer.append(attribut + " - ");
            }
            buffer.setLength(buffer.length() - 3);
            buffer.append('\n');
            for (int i = 0; i < resultats.size(); i++) {
                for (String attribut : resultats.get(0).keySet()) {
                    buffer.append(resultats.get(i).get(attribut) + " - ");
                }
                buffer.setLength(buffer.length() - 3);
                buffer.append('\n');
            }
            return buffer.toString();
        } catch (SQLException e) {
            return "";
        }
    }

    /**Fonction qui retourne la liste des résultats
    * @return la liste contenant les lignes de résultats de la requêtes
    */
    public List<Map<String,String>> getResultat() {
        return resultats;
    }

    /**Fonction qui retourne l'attribut resultSet
    * @return l'attribut ResultSet
    */
    public ResultSet getResultSet(){
        return connectionBase.getResultSet();
    }

    /** Fonction qui retourne les noms des colonnes. 
     * @return Les noms des colonnes.
     * @throws SQLException Renvoie les exceptions SQl que la méthode reçoit.
    */
    public List<String> getColumnsNames() throws SQLException {
        try {
            executerRequete();
            return attributsResultats;
        } catch (SQLException e) {
            return null;
        }
    }

    /** Fonction qui retourne les types des colonnes.
    * @return les types des colonnes
    * @throws SQLException Renvoie les exceptions SQl que la méthode reçoit.
    */
    public List<String> getColumnsTypes() throws SQLException {
        try{
            executerRequete();
            return typeAttributsResultats;
        }catch(SQLException e){
            return null;
        }
    }
}
