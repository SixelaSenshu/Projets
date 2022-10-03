package databaseGestionnary;

import java.util.*;

public class Update extends ModificationConditionnelle {
    
    String attribut;
    String newValeur;

    /**Constructeur de la requête Update pour certaines lignes suivant les conditions 
    * @param ensemble : table ou vue sur laquelle on effectue une modification
    * @param attribut : colonne sur laquelle on veut une modification
    * @param newValeur : valeur de la colonne sur laquelle on aura une modification
    * @param attributsContraints Attribut de la tables sur laquelle on aura une condition 
    * @param typesContraintes : Type de contrainte sur une certaine colonne de la table
    * @param valeursContraintes : Valeurs pour une contraite sur une certaine colonne de la table
    * @param operateursLogiques : Opérateur après une contraite sur une certainte colonne de la table 
    */
    public Update(String ensemble, String attribut, String newValeur, List<String> attributsContraints, List<String> typesContraintes, List<String> valeursContraintes, List<String> operateursLogiques) {
        super(ensemble,attributsContraints,typesContraintes,valeursContraintes,operateursLogiques);
        this.attribut = attribut;
        this.newValeur = newValeur;
    }

    /**Constructeur de la requête Update pour certaines lignes sans conditions prédéfinies
    * @param ensemble : table ou vue sur laquelle on effectue une modification
    * @param attribut : colonne sur laquelle on veut une modification
    * @param newValeur : valeur de la colonne sur laquelle on aura une modification
    */
    public Update(String ensemble, String attribut, String newValeur) {
        super(ensemble);
        this.attribut = attribut;
        this.newValeur = newValeur;
    }

    /**
     * Retourne une représentation textuelle de la requête SQL.
     * @return Une représenattion textuelle de la requête SQL.
     */
    public String formulerUpdate() {
        assert montrerConditions().size() <= montrerTypesConditions().size();
        assert montrerConditions().size() <= montrerValeursConditions().size();
        assert montrerConditions().size() <= montrerOperateursLogiques().size();
        StringBuffer bufferGen = new StringBuffer("UPDATE " + montrerEnsemble() + " ");
        bufferGen.append("SET " + attribut + " = " + newValeur);
        //S'il y a des conditions, on fournit les conditions.
        List<String> conditions = montrerConditions();
        List<String> types = montrerTypesConditions();
        List<String> valeurs = montrerValeursConditions();
        List<String> operateurs = montrerOperateursLogiques();
        if (conditions.size() > 0) {
            bufferGen.append(" WHERE ");
            for (int i = 0; i < conditions.size(); i++) {
                bufferGen.append(conditions.get(i) + " ");
                bufferGen.append(types.get(i) + " ");
                bufferGen.append(valeurs.get(i) + " ");
                if (i != conditions.size() - 1) {
                    bufferGen.append(operateurs.get(i));
                }
            }
        }
        bufferGen.append(";");
        return bufferGen.toString();
    }
}
