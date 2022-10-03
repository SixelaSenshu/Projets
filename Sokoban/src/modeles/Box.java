package modeles;
/**
 * Classe qui permet de définir la position des caisses
 */
public class Box{
    private Position position;

    /*** Constructeur de la caisse
     * @param x : valeur de la position x 
     * @param y : valeur de la position x */
    public Box(int x, int y){
        this.position = new Position(x, y);
    }

    /** Accesseur de la valeur x 
     * @return la valeur en question*/
    public int getX(){
        return this.position.getX();
    }

    /** Accesseur de la valeur y
     * @return la valeur en question*/
    public int getY(){
        return this.position.getY();
    }

    /** Setteur de la valeur x 
     * @param x : la nouvelle valeur*/
    public void setX(int x){
        this.position.setX(x);
    }

    /** Setteur de la valeur y
     * @param y : la nouvelle valeur*/
    public void setY(int y){
        this.position.setY(y);
    }

    /** Accesseur de la position
     * @return la position en question*/
    public Position getPosition(){
        return this.position;
    }

    /** Accesseur de l'objet
     * @return l'objet en question*/
    public Box getBox(){
        return this;
    }

    /** Fonction qui change la position de la caisse 
    *@param x : la nouvelle valeur de x
    *@param y : la nouvelle valeur de y
    */
    public void changePosition(int x, int y){
        this.setX(x);
        this.setY(y);
    }
}
