package databaseGestionnary;
import java.util.*;
public abstract class ModificationConditionnelle extends Modification {

    String ensemble;

    List<String> conditions;
    List<String> typeConditions;
    List<String> valeursConditions;
    List<String> operateursLogiques;

    /**Constructeur de la ModificationConditionnelle pour un certain type de requêtes
    * @param ensemble : table ou vue sur laquelle on effectue une modification
    * @param conditions : conditions sur un attribut de la table sur laquelle on aura une recherche conditionelle
    * @param typeConditions : type de la contrainte sur l'attribut d'une colonne
    * @param valeursConditions : Valeurs pour une contraite sur une certaine colonne de la table 
    * @param operateursLogiques : Opérateur après une contraite sur une certainte colonne de la table 
    */
    public ModificationConditionnelle(String ensemble, List<String> conditions, List<String> typeConditions, List<String> valeursConditions, List<String> operateursLogiques) {
        this.ensemble = ensemble;
        this.conditions = conditions;
        this.typeConditions = typeConditions;
        this.valeursConditions = valeursConditions;
        this.operateursLogiques = operateursLogiques;
    }

    /**Constructeur de la ModificationConditionnelle pour un certain type de requêtes sans conditions préfiniees
    * @param ensemble : table ou vue sur laquelle on effectue une modification
    */
    public ModificationConditionnelle(String ensemble) {
        this(ensemble,new ArrayList<String>(),new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());
    }

    /** Function qui permet l'ajout d'une condition
    * @param condition : condition sur un attribut de la table sur laquelle on aura une recherche conditionelle
    * @param typeCondition : type de la contrainte sur l'ttribut d'une colonne
    * @param valeurCondition : Valeurs pour une contraite sur une certaine colonne de la table 
    * @param operateurLogique : Opérateur après une contraite sur une certainte colonne de la table 
    */
    public void ajouterCondition(String condition, String typeCondition, String valeurCondition, String operateurLogique) {
        conditions.add(condition);
        typeConditions.add(typeCondition);
        valeursConditions.add(valeurCondition);
        operateursLogiques.add(operateurLogique);
    }

    /** Fonction qui permet de montrer la table concernée
    * @return la table sous forme de String
    */
    public String montrerEnsemble() {
        return ensemble;
    }

    /** Fonction qui permet de montrer les conditions
    * @return la list des conditions
    */
    public List<String> montrerConditions() {
        return new ArrayList<String>(conditions);
    }

    /** Fonction qui permet de montrer les types des conditions
    * @return la list des conditions
    */
    public List<String> montrerTypesConditions() {
        return new ArrayList<String>(typeConditions);
    }

    /** Fonction qui permet de montrer les valeurs des conditions
    * @return la list des conditions
    */
    public List<String> montrerValeursConditions() {
        return new ArrayList<String>(valeursConditions);
    }

    /** Fonction qui permet de montrer les opérateurs après les conditions
    * @return la list des conditions
    */
    public List<String> montrerOperateursLogiques() {
        return new ArrayList<String>(valeursConditions);
    }
}