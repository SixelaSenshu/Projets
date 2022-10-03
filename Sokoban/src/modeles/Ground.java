package modeles;

/**
 * Classe qui sous-hérite de Case
 * Defini la case de type sol
 */
public class Ground extends Case{

    /*** Constructeur de la sous-classe Case : Ground
     * @param caracter :  Valeur qui sera afficher
     * @param player : Si il y a un joueur dessus
     * @param box :  Si il y a une caisse dessus
     * @param isBlock : Si l'élement est une limite de terrain ou non*/
    public Ground(String caracter, boolean player, boolean box , boolean isBlock){ 
        // si on creer une destination avec un joueur 
        // ou une caisse dessus
        super(caracter,player,box,isBlock);
    }

    /**Constructeur avec paramètre par défaut*/
    public Ground(){
        super(" ",false,false,false);
    }
    
    /**Retourne la disponibilité de la case
     * @return Si il y a ou non */
    @Override
    public boolean isEmpty(){
        if (getPlayerOn() == false && getBoxOn() == false){
            return true;
        }
        return false;
    }

    /**Retourne la présence d'un joueur
     * @return Si il y a ou non */
    @Override
    public boolean playerOn(){
        return getPlayerOn();
    }

    /**Retourne la présence d'une caisse
     * @return Si il y a ou non */
    @Override
    public boolean boxOn(){
        return getBoxOn();
    }

    /** Change les valeurs de la case (Ground)*/
    @Override
    public void setGround() {
        setBoxOn(false);
        setValue(" ");
        setPlayerOn(false);
        setIsBlock(false);
    }

    /** Change la valeur de la case (Box)
    * @param c : nouvelle valeur de la case */
    @Override
    public void setBox(String c) {
        setBoxOn(true);
        setValue(c);
        setPlayerOn(false);
        setIsBlock(true);
    }

    /** Change les valeurs de la case (Destination) */
    @Override
    public void setDestination() {
        setBoxOn(false);
        setValue(".");
        setPlayerOn(false);
        setIsBlock(false);
    }

    /** Change la valeur de la case (Player)
    * @param c : nouvelle valeur de la case */
    @Override
    public void setPlayer(String c) {
        setBoxOn(false);
        setValue(c);
        setPlayerOn(true);
        setIsBlock(true);
    }
    
}