package modeles;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Matrice générer et créer avec une lecture de fichier
 * Contient toutes les informations pour manipuler et jouer au Sokoban
 */
public class Carte {
    private Lecture data;
    private Player player;
    private List<String> listString;
    private List<Position> listDestination;
    private int width;
    private int heigth;
    private int score;
    private Case[][] map ;
    private List<Position> caseChanged;

    /** Constructeur de la Carte
     * @param data : Lecteur de fichier
     * @throws IOException : Lance une exception en cas de problème de lecture de fichier*/
    public Carte(Lecture data) throws IOException{
        this.data = data;
        this.listString = data.getList();
        this.heigth = data.getHeight();
        this.width = data.getWidth();
        this.map = new Case[this.heigth][this.width];
        this.listDestination = new ArrayList<Position>();
        this.changeMap();
    }

    /** * Acceseur du lecteur
     * @return le lecteur*/
    public Lecture getData(){return this.data;}

    /** * Acceseur de la map
     * @return la matrice de case*/
    public Case[][] getMap(){return this.map;}

    /** * Acceseur d'un élement de la map
     * @param x : Position X dans le tableau
     * @param y : Position Y dans le tableau
     * @return la matrice de case*/
    public Case getElementMap(int x, int y){return this.map[x][y];}


    /*** Acceseur de la liste des chaine de caractères
     * @return la liste de chaine de caractères*/
    public List<String> getListString(){return this.listString;}

    /*** Acceseur de la liste des Destination
     * @return la liste de Destination */
    public List<Position> getListDestination(){return this.listDestination;}

    /*** Acceseur de la liste des cases modifier ( Interface Graphique )
     * @return la liste de chaine de caractères*/
    public List<Position> getListCaseChanged(){return this.caseChanged;}

    /*** Acceseur de la longeur de la liste
     * @return la longeur*/
    public int getWidth(){return this.width;}
    
    /*** Acceseur de la hauteur de la liste
     * @return la hauteur*/
    public int getHeight(){return this.heigth;}

    /*** Acceseur du joueur
     * @return le joueur*/
    public Player getPlayer(){return this.player;}

    /*** Acceseur du score
     * @return le score*/
    public int getScore(){return this.score;}
    
    /*** Ajoute un element a la map ( chaine de caractères)
     * @param extension : nouvelle extension*/
    public void addStringElement(String extension){this.listString.add(extension);}
    
    /*** Change les données de la map
     * @param newData nouveau lecteur de fichier */
    public void changeMapData(Lecture newData){
        this.data = newData;
        this.listString = newData.getList();
        this.heigth = newData.getHeight();
        this.width = newData.getWidth();
        this.map = new Case[this.heigth][this.width];
        this.listDestination = new ArrayList<Position>();
        this.changeMap();
    }

    /**
     * Fonction qui vérifie si la partie est finie
     * @return si est l'est ou non
     */
    public boolean isEnd(){
        this.score = 0;
        for(int i = 0; i < this.listDestination.size(); i++){
            Position des = this.listDestination.get(i).getPosition();
            if(this.map[des.getX()][des.getY()].boxOn())score++;
        }
        if(score == this.listDestination.size()){
            System.out.println("La partie est finie ");
            return true;
        }
        return false;
    }

    
    /*** Génère la matrice de Case*/
    public void changeMap(){
        this.map = new Case[this.heigth][this.width];
        for(int x = 0; x < this.heigth ; x++){
            for(int y = 0; y < this.width; y++){
                switch (this.listString.get(x).charAt(y)) {
                    case '#': 
                        map[x][y] = new Wall();
                        break;
                    case '/': 
                        map[x][y] = new Void();
                        break;
                    case '.': 
                        map[x][y] = new Destination();
                        listDestination.add(new Position(x, y));
                        break;
                    case '+':
                        map[x][y] = new Destination("+",true,false,true);
                        listDestination.add(new Position(x, y));
                        break;
                    case '@': 
                        map[x][y] = new Ground("@",true,false,false);
                        this.player = new Player(x, y);
                        break;
                    case '$': 
                        map[x][y] = new Ground("$",false,true,true);
                        break;
                    case '*': 
                        map[x][y] = new Destination("*",false,true,true);
                        break;
                    case ' ': 
                        map[x][y] = new Ground();
                        break;
                    default: 
                        map[x][y] = new Void();
                        break;
                }
            }
        }
    }

    /**
     * Detecte si il y a une case acessible ou non avec son type vers la direction du joueur 
     * @param PosXm Valeur du joueur sur x ainsi que la valeur du mouvement
     * @param PosYm Valeur du joueur sur y ainsi que la valeur du mouvement
     * @return -1, -1 si la case est libre ou la position du bloc 
     */
    public int detectNextCase(int PosXm, int PosYm){
        if(this.map[PosXm][PosYm].getBoxOn()){return 1;
        }else if(!this.map[PosXm][PosYm].getIsBlock()){
            return -1;
        }else return 0;
    }

    /**
     * Permet le déplacement du joueur uniquement si le déplacement est possible 
     * 1. Si la case suivante est vide 
     * 2. Si la case suivante est une caisse et que la suivante est libre
     * @param move : direction du joueur
     * @return si le mouvement est réalisé ou non
     */
    public boolean move(Move move){ 
        caseChanged = new ArrayList<Position>();
        int posXm = this.player.getX() + move.getX(); // valeur joueur + déplacement
        int posYm = this.player.getY() + move.getY(); // idem
        int pos = detectNextCase(posXm, posYm); // regarde si la case suivante est vide ou non et de quel type
        if(pos == -1){ // si la case est vide
            caseChanged.add(new Position(this.player.getX(),this.player.getY()));
            caseChanged.add(new Position(posXm,posYm));
            return movePlayer(posXm, posYm); // on deplace le joueur
        }else if(pos == 1){ // si la case est une caisse
            int PXn = move.getX() + posXm; // on recupere les valeurs
            int PYn = move.getY() + posYm;
            if(!this.map[PXn][PYn].getIsBlock()){
                caseChanged.add(new Position(this.player.getX(),this.player.getY()));
                caseChanged.add(new Position(posXm,posYm));
                caseChanged.add(new Position(PXn, PYn));
                moveBox(this.map[PXn][PYn],this.map[posXm][posYm]); // on procède au déplacement de la box puis du joueur
                return movePlayer(posXm, posYm);
            }
            return true;
        }else{
            return false;
        }           
    }
    
    /**
     * Fonction qui effectue le mouvement de la caisse si il y a un déplacement du joueur vers cette case.
     * @param nextCase : case qui est situé après la caisse
     * @param currentCase : case actuelle ou est la caisse
     * @return si le déplacement à été fait ou non
     */
    public boolean moveBox(Case nextCase, Case currentCase){
        if(currentCase.getValue() == "*"){ // On regarde sur quoi le joueur va atterir est indique le signe 
            currentCase.setDestination();;
        }else{
            currentCase.setGround();
        }
        if(nextCase.getValue() == "."){
            nextCase.setBox("*");
        }else{
            nextCase.setBox("$");
        }
        return true;
    }
    
    /**
     * Fonction qui effectue le déplacement du joueur et le rafraichiement de l'ancienne de case.
     * @param xC : Position x de la case suivante
     * @param yC : Position y de la case suivante
     * @return Que le déplacement à été effectuer
     */
    public boolean movePlayer(int xC, int yC){
        Case currentCase = this.map[xC][yC];
        Case currentPlayer = this.map[this.player.getX()][this.player.getY()]; // Position de la case du joueur
        if(currentPlayer.getValue() == "+"){ // Si le joueur etait sur une destination ou non , on re-actualise la valeur
            currentPlayer.setDestination();
        }else{
            currentPlayer.setGround();
        }
        if(currentCase.getValue() == "."){ // On regarde sur quoi le joueur va atterir est indique le signe 
            currentCase.setPlayer("+");;
        }else{
            currentCase.setPlayer("@");
        }
        this.player.changePosition(xC,yC);
        return true;
    }


    /*** Génère un chaine des caractères d'une ligne du tableau.
     * @param ind : position dans la matrice
     * @return Une partie de la map sous forme de chaine de caractères */
    private String generateString(int ind){
        String str = "";
        for(int pos = 0; pos < this.width; pos++){
            str += this.map[ind][pos].getValue();
        }
        return str;
    }

    /*** Ecrit dans la console la carte.*/
    public void toStringLog(){
        for(int ind = 0; ind <  this.heigth ; ind++){
            String str = generateString(ind);
            System.out.println(str);
        }
    }
}