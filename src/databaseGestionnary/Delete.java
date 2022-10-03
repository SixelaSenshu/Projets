package databaseGestionnary;

import java.util.*;

public class Delete extends ModificationConditionnelle {

    /**
     * Constructeur pour une requête Delete avec des contraintes prédéfinies.
     * @param ensemble Table sur laquelle la requête sera effectuée;
     * @param attributsContraintes Les attributs de la table qui seront mis en contrainte
     * @param typesContraintes Les contraintes exercées sur les attributs
     * @param valeursContraintes Les valeurs sur lesquelles les contraintes seront exercées.
     * @param operateursLogiques Les opétateurs qui seront placés après les conditions
     */
    public Delete(String ensemble, List<String> attributsContraintes, List<String> typesContraintes, List<String> valeursContraintes, List<String> operateursLogiques) {
        super(ensemble,attributsContraintes,typesContraintes,valeursContraintes,operateursLogiques);
    }
    
    /**
     * Constructeur pour une requête Delete sans des contraintes prédéfinies.
     * @param ensemble Table sur laquelle la requête sera effectuée;
     */
    public Delete(String ensemble) {
        super(ensemble);
    }

    /**
     * Retourne une représentation textuelle de la requête SQL.
     * @return Une représenattion textuelle de la requête SQL.
     */
    public String formulerUpdate() {
        assert montrerConditions().size() <= montrerTypesConditions().size();
        assert montrerConditions().size() <= montrerValeursConditions().size();
        assert montrerConditions().size() <= montrerOperateursLogiques().size();
        StringBuffer bufferGen = new StringBuffer("DELETE FROM " + montrerEnsemble());
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
