package databaseGestionnary;
import java.io.FileNotFoundException;
import java.sql.*;

public abstract class Modification {

    /** Fonction qui formule la requête de mise à jour
    * @return la nouvelle requête
    */
    public abstract String formulerUpdate();

    /** Fonction qui permet de générer une requête pour un mise à jour dans la base de données
    * @throws FileNotFoundException Renvoie les exceptions liées à un fichier non trouvé.
     *@throws SQLException Renvoie les exceptions liées au SQL.
     */
    public void executerUpdate() throws SQLException, FileNotFoundException {
        DatabaseConnection connectionBase = new DatabaseConnection("jdbc:sqlite:./database/database.db");
        connectionBase.executerUpdate(this.formulerUpdate());
        connectionBase.StopConnection();
    }
}
