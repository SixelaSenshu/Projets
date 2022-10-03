package modeles;

/**
 * Classe qui permet de générer un joueur avec une Position
 */
public class Player{
    private Position position;

    /*** Constructeur de l'objet Player 
     * @param x : position x du joueur
     * @param y : position y du joueur*/
    public Player(int x, int y){
        this.position = new Position(x, y);
    }

    /*** Accesseur de x
     * @return la valeur*/
    public int getX(){
        return this.position.getX();
    }

    /*** Accesseur de y
    * @return la valeur*/
    public int getY(){
        return this.position.getY();
    }

     /*** Setteur de x
     * @param x : la nouvelle valeur*/
    public void setX(int x){
        this.position.setX(x);
    }

    /*** Setteur de y
     * @param y : la nouvelle valeur*/
    public void setY(int y){
        this.position.setY(y);
    }

    /*** Accesseur de l'objet position 
    * @return l'objet*/
    public Position getPosition(){
        return this.position;
    }

    /*** Accesseur de l'objet courant
    * @return l'objet en question*/
    public Player getBox(){
        return this;
    }

    /***Change la position du joueur
     * @param x : la nouvelle position x
     * @param y : la nouvelle position y*/
    public void changePosition(int x, int y){
        this.setX(x);
        this.setY(y);
    }
}
