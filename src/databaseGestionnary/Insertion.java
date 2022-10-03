package databaseGestionnary;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

public class Insertion extends Modification {
    
    private String ensemble;
    private List<String> attributs;
    private List<ArrayList<String>> valeurs;

    /**Contructeur pour la requête Insertion 
    * @param ensemble : la table sur laquelle on aura une insertions
    * @param attributs : les attributs de la table
    * @param valeurs : les valeurs qu'on inserera dans une nouvelle ligne du tableau
    */
    public Insertion(String ensemble, List<String> attributs, List<ArrayList<String>> valeurs) {
        this.ensemble = ensemble;
        this.attributs = attributs;
        this.valeurs = valeurs;
    }

    /**Contructeur pour la requête Insertion  sans paramètres prédéfinies
    * @param ensemble : la table sur laquelle on aura une insertions
     */
    public Insertion(String ensemble) {
        this(ensemble,new ArrayList<String>(), new ArrayList<ArrayList<String>>());
    }

    /** Fonction qui remplis automatiquement les attributs qui sont des clés primaires
    * @param clesPrimaires les clés primaires de la tables
    * @throws SQLException  Exception de manipulation de données
    */
    public void remplissageAutomatique(boolean clesPrimaires) throws SQLException {
        Select req = new Select(ensemble);
        req.executerRequete();
        List<String> colonnes = req.getColumnsNames();
        if (!clesPrimaires) {
            DBMetaData md = new DBMetaData();
            if (md.getClesPrimaires(ensemble).size() == 1) {
                colonnes.removeAll(md.getClesPrimaires(ensemble));
            }
        }
        attributs = colonnes;
    }

    /** Fonction pour ajouter un attribut à la requête
    * @param attribut : le nouvelle attribut de la requête
    */
    public void ajouterAttribut(String attribut) {
        assert attribut != null && attribut != "";
        attributs.add(attribut);
    }

    /** Fonction pour ajouter un ensemble d'attributs
    * @param attributs : les nouveaux attributs de la requête
    */
    public void ajouterAttributs(List<String> attributs) {
        assert attributs != null;
        attributs.addAll(attributs);
    }

    /** Fonction pour ajouter des valeurs à la requête
    * @param ligne : la nouvelle liste des valeurs qui sera implémenté dans la table
    */
    public void ajouterValeur(ArrayList<String> ligne) {
        assert ligne.size() == attributs.size();
        valeurs.add(ligne);
    }
    
    /**Fonction pour formule la requête sous la forme d'un String
    * @return la requête sous forme de String */
    public String formulerUpdate() {
        assert attributs.size() != 0;
        assert valeurs.size() != 0;
        assert valeurs.get(0).size() > attributs.size();
        StringBuffer bufferGen = new StringBuffer("INSERT INTO " + ensemble);
        StringBuffer bufferAtt = new StringBuffer();
        StringBuffer bufferVal = new StringBuffer();
        int lim = attributs.size();
        //On fournit les attributs à remplir.
        bufferAtt.append("(");
        for (int i = 0; i < lim; i++) {
            bufferAtt.append(attributs.get(i));
            if (i != lim - 1) {
                bufferAtt.append(",");
            }
        }
        //On fournit les valeurs à remplir.
        bufferAtt.append(")");
        for (int i = 0; i < valeurs.size(); i++) {
            bufferVal.append("(");
            for (int j = 0; j < lim; j++) {
                bufferVal.append("\""+valeurs.get(i).get(j)+"\"");
                if (j != lim - 1) {
                    bufferVal.append(",");
                }
            }
            bufferVal.append(")");
            if (i != valeurs.size() - 1) {
                bufferVal.append(",");
            } else {
                bufferVal.append(";");
            }
        }
        bufferGen.append(bufferAtt);
        bufferGen.append(" VALUES " + bufferVal);
        return bufferGen.toString();
    }
    
    /** Fonction qui permets de retourner les attributs
    * @return La liste des attributs
    */
    public List<String> getAttributs() {
        return new ArrayList<String>(attributs);
    }
}
