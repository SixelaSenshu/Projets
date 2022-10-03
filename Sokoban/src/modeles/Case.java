package modeles;

/**
 * Classe qui hérite des attributs général pour la création d'une case
 * Défini la classe abstraite qu'est la Case
 */
public abstract class Case {

    private String value; 
    private boolean isBlock;
    private boolean player;
    private boolean box;

    /*** Constructeur de la Case 
     * @param value : valeur de la case
     * @param player : si il y a un joueur
     * @param box : si il y a une caisse
     * @param isBlock : si c'est un élement inaccesible*/
    public Case(String value, boolean player, boolean box, boolean isBlock){
        this.value = value;
        this.player = player;
        this.box = box;
        this.isBlock = isBlock;
    }

     /**Retourne la disponibilité de la case
     * @return Si il y a ou non */
    abstract public boolean isEmpty();

    /**Retourne la présence d'un joueur
     * @return Si il y a ou non */
    abstract public boolean playerOn();

    /**Retourne la présence d'une caisse
     * @return Si il y a ou non */
    abstract public boolean boxOn();

    /** Change les valeurs de la case (Ground)*/
    abstract public void setGround();

     /** Change les valeurs de la case (Ground)*/
     abstract public void setDestination();

    /** Change la valeur de la case (Box)
    * @param c : nouvelle valeur de la case */
    abstract public void setBox(String c);

    /** Change la valeur de la case (Player)
    * @param c : nouvelle valeur de la case */
    abstract public void setPlayer(String c);

    /** Acceseur à la valeur
     * @return la valeur en question */
    public String getValue(){
        return this.value;
    }

    /** Acceseur à la valeur de limite
     * @return la valeur en question */
    public boolean getIsBlock(){
        return this.isBlock;
    }

    /** Acceseur à la valeur de présence de joueur
     * @return la valeur en question */
    public boolean getPlayerOn(){
        return this.player;
    }

    /** Acceseur à la valeur de présence de caisse
     * @return la valeur en question */
    public boolean getBoxOn(){
        return this.box;
    }

    /** Setteur de la valeur 
     * @param newS: nouvelle valeur de la case */
    public void setValue(String newS){
        this.value = newS;
    }

    /** Setteur de la valeur de présence de caisse
     * @param newIs : nouvelle valeur de la case */
    public void setIsBlock(boolean newIs){
        this.isBlock = newIs;
    }

    /** Setteur de la valeur de présence de joueur
     * @param newOn : nouvelle valeur de la case */
    public void setPlayerOn(boolean newOn){
        this.player = newOn;
    }

    /** Setteur de la valeur de  limite
     * @param newOn : nouvelle valeur de la case */
    public void setBoxOn(boolean newOn){
        this.box = newOn;
    }

}
