package modeles;

/**
 * Enumeration pour les déplacements
 */
public enum Move {
    /**Haut*/
    TOP(-1,0) , 
    /**Bas*/
    BOTTOM(1,0) ,
    /**Gauche*/
    LEFT (0,-1),
    /**Droite */ 
    RIGHT(0,1);

    private int x;
    private int y;

    /*** Constructeur de Move
     * @param x : La valeur x du déplacement
     * @param y : La valeur y du déplacement */
    Move(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**Acceseur à la valeur x
     * @return la valeur en question */
    public int getX(){
        return this.x;
    }

    /**Acceseur à la valeur y
    * @return la valeur en question */
    public int getY(){
        return this.y;
    }

     /**Acceseur l'objet
     * @return l'objet en question */
    public Move getMove(){
        return this;
    }
}
