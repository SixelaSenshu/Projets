package modeles;

/**
 * Classe qui défini une case de type vide
 * Est un sous élements de la classe abstraite Case
 */
public class Void extends Case {

    /**Constructeur par défaut de Void */
    public Void(){super("/",false,false,true);}

    /**Retourne la disponibilité de la case
     * @return Si il y a ou non */
    @Override
    public boolean isEmpty(){return false;}

    /**Retourne la présence d'un joueur
     * @return Si il y a ou non */
    @Override
    public boolean playerOn(){return false;}

    /**Retourne la présence d'une caisse
     * @return Si il y a ou non */
    @Override
    public boolean boxOn(){return false;}

    /** Change les valeurs de la case (Ground) */
    @Override
    public void setGround() {}

    /** Change la valeur de la case (Box)
    * @param c : nouvelle valeur de la case */
    @Override
    public void setBox(String c) {}

    /** Change la valeur de la case (Player)
    * @param c : nouvelle valeur de la case */
    @Override
    public void setPlayer(String c) {}

    /** Change les valeurs de la case (Destination) */
    @Override
    public void setDestination() {
    }
}