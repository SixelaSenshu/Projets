package databaseGestionnary;
import java.sql.*;

public class DatabaseConnection {
    private String Url;
    private Connection con;
    private ResultSet rslt;
    private ResultSetMetaData rsmd;
    private Statement stmt;

    /** Constructeur de DatabaseConnection
     * @param url L'url de la base de données.
     */
    public DatabaseConnection(String url){
      this.Url = url;
      this.con = null;
      this.rslt = null;
      this.rsmd = null;
      this.stmt = null;
      this.connectionDataBase();
    }

    /**Fonction pour afficher un message  d'erreur et un arrêt du programme
    * @param message: message qui sera afficher*/
    private void arret(String message) {
      System.err.println(message);
      System.exit(0);
    }  

    /**Fonction pour recuperer la valeur de l'url
     *@return la valeur de l'url*/
    protected String getUrl(){
      return this.Url;
    }

    /**Fonction pour recuperer la connection de la base
     *@return la connection*/
    protected Connection getConnection(){
      return this.con;
    }

    /**Fonction pour recuperer la MetaData de la base
     *@return Les MétaDonnées
     *@throws SQLException Renvoie les exceptions liées au SQL.
     */
    protected DatabaseMetaData getDBMetaData() throws SQLException {
      return this.con.getMetaData();
    }

    /**Fonction pour recuperer le resultSet de la base
     *@return la connection*/
    protected ResultSet getResultSet(){
      return this.rslt;
    }

    /**Fonction pour recuperer le resultSetMetaData de la base
     *@return la connection*/
    protected ResultSetMetaData getResultSetMetaData(){
      return this.rsmd;
    }

    /**Fonction pour recuperer le resultSetMetaData de la base
     *@return la connection*/
    protected Statement getStatement(){
      return this.stmt;
    }

    /**Fonction qui permet d'executer une selection dans la base de données
    * @param requete La requete en question
    * @return L'ensemble résultat de la recherche.
    * @throws SQLException Renvoie les exceptions liées au SQL.
    */
    protected ResultSet executerRecherche(String requete) throws SQLException {
        this.rslt = this.stmt.executeQuery(requete);
        return rslt;
    }

    /**Fonction qui permet d'executer une mise à jour dans la base de données
    * @param requete La requete en question
    * @return La valeur retournée par executeUpdate
    * @throws SQLException Renvoie les exceptions liées au SQL.
    */
    protected int executerUpdate(String requete) throws SQLException{
        int res = this.stmt.executeUpdate(requete);
        return res;
    }

    /**Fonction pour stopper la connection
    * @throws SQLException Renvoie les exceptions liées au SQL.
    */
    public void StopConnection() throws SQLException{
      try{
          if(this.rslt != null)
            this.rslt.close();

          if(this.stmt != null)
            this.stmt.close();

          if(this.con != null)
            this.con.close();

        }catch(SQLException e){
          // connection close failed.
          System.err.println(e);
        }
    }


    /**Fonction pour lancer une connection a la base de données
     */
    public void connectionDataBase(){
      try {
        Class.forName("org.sqlite.JDBC");
        //System.out.println("Connexion au pilote en cours");
      }catch (ClassNotFoundException e) {
        arret("Impossible de charger le pilote jdbc:odbc"); // Erreur de chargement
      }
      //System.out.println("Connexion a la base de données en cours");
      try {
        this.con = DriverManager.getConnection(this.Url);
        this.stmt = this.con.createStatement();
        //System.out.println("Connexion à la base réussi");
      } catch (SQLException e) {
        arret("Connection à la base de données impossible"); // erreur de connection a la bd
      };
    }
}
