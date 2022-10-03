package databaseGestionnary;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws Exception {

        Select req = new Select("Ingredient");
        System.out.println(req.formulerRequete());
        System.out.println(req);

        System.out.println("---");

        Insertion ins = new Insertion("Ingredient");
        ArrayList<String> arr = new ArrayList<String>();
        ArrayList<String> arr2 = new ArrayList<String>();
        ins.ajouterAttribut("id_ingredient");
        ins.ajouterAttribut("prix_ingredient");
        ins.ajouterAttribut("nom_ingredient");
        ins.ajouterAttribut("type_ingredient");
        ins.ajouterAttribut("type_mesure");
        arr.add("255");
        arr.add("0.01");
        arr.add("Riz blanc");
        arr.add("feculent");
        arr.add("g");
        arr2.add("256");
        arr2.add("0.01");
        arr2.add("Riz noir");
        arr2.add("feculent");
        arr2.add("g");
        ins.ajouterValeur(arr);
        ins.ajouterValeur(arr2);
        Modification up = ins;
        System.out.println(up.formulerUpdate());
        up.executerUpdate();
        System.out.println(req);

        System.out.println("---");

        ModificationConditionnelle modif2 = new Update("Ingredient","id_ingredient","id_ingredient - 1");
        modif2.ajouterCondition("nom_ingredient","LIKE","\"Riz%\"","");
        System.out.println(modif2.formulerUpdate());
        modif2.executerUpdate();
        System.out.println(req);

        System.out.println("---");
        
        ModificationConditionnelle modif = new Delete("Ingredient");
        modif.ajouterCondition("nom_ingredient","LIKE","\"Riz%\"","");
        System.out.println(modif.formulerUpdate());
        modif.executerUpdate();
        System.out.println(req);
    }
}
