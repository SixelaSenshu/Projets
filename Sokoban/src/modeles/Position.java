package modeles;

/**
 * Classe qui définie une position sur une matrice à deux coordonnées
 */
public class Position {
    private int x;
    private int y;

    /*** Constructeur de Position
     * @param x : coordonnée x dans une matrice
     * @param y : coordonnée y dans une matrice*/
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /*** Fonction qui retourne l'objet
     * @return l'objet en lui même*/
    public Position getPosition(){
        return this;
    }

    /*** Fonction qui retourne l'attribut x
     * @return : qui retourne la valeur*/
    public int getX(){
        return this.x;
    }

    /*** Fonction qui retourne l'attribut y
     * @return : qui retourne la valeur*/
    public int getY(){
        return this.y;
    }

    /*** Fonction qui défini la valeur de x
    * @param x : nouvelle valeur*/
    public void setX(int x){
        this.x = x;
    }

     /*** Fonction qui défini la valeur de y
    * @param y : nouvelle valeur*/
    public void setY(int y){
        this.y = y;
    }

    /**
     * Fonction qui change la position
     * @param i : nouvelle valeur de x
     * @param j : nouvelle valeur de y
     */
    public void changePosition(int i, int j) {
        this.y = j;
        this.x = i;
    }
}
