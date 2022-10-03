import java.awt.Point;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class ModeleMemory{
    private int columns;
    private int rows;
    private String[] listImage = {"wii.png","wii.png","xbox.png","xbox.png","switch.png","switch.png","ps4.png","ps4.png","ps3.png","ps3.png","gameplay.png","gameplay.png","atari.png","atari.png", "360.png", "360.png","360.png"};
    private Image[][] board;
    private Random rand = new Random();
    private Image[] currentImage = new Image[2];
    private List<Image> currentImageCheck = new ArrayList<>();

    public ModeleMemory(int nbColumns, int nbRows){
        this.columns = nbColumns;
        this.rows = nbRows;
        this.board = new Image[this.rows][this.columns];
        this.reinit();
    }

    public ModeleMemory(){
        this(4,4);
        this.reinit();
    }

    public int getRows(){
        return this.rows;
    }

    public int getColumns(){
        return this.columns;
    }

    public String[] getListImg(){
        return this.listImage;
    }

    public Image[][] getBoard(){
        return this.board;
    }

    public Image getBoard(int x, int y){
        return this.board[x][y];
    }

    public void revealCard(int x ,int y){
        this.board[x][y].setChecked(2);
    }

    public void switchCard(int x, int y){ //is temporaly reveal
        if(this.board[x][y].getValue() == 1){
            this.board[x][y].setChecked(0);
        }else{
            this.board[x][y].setChecked(1);
        }
    }

    public boolean addCurrent(int x, int y){ //add cards to list of comparaison of even
        if(this.currentImage[0] == null){
            this.currentImage[0] = this.board[x][y];
        }else{
            this.currentImage[1] = this.board[x][y];
        }
        return true;
    }

    public boolean compareCurrentCard(Image One, Image Two){
        this.currentImage = new Image[2]; //put an empty table 
        if(One.getNum() == Two.getNum()){
            this.currentImageCheck.add(One); // allow to compare if the cards is revelead or no and allow to not take a second time the cards
            this.currentImageCheck.add(Two);
            One.setChecked(2);
            Two.setChecked(2);
            return true;
        }else{ //not even
            this.revealCard((int)One.getPosition().getX(),(int)One.getPosition().getY());
            this.revealCard((int)Two.getPosition().getX(),(int)Two.getPosition().getY());
            return false;
        }
    }

    public boolean end(){
        if(this.currentImageCheck.size() == this.rows * this.columns){
            return true;
        }   // if all cards is checked, is the end of game
        else{
            return false;
        }
    }

    public void shuffle(List<Point> listOfPoint){
       
        while(listOfPoint.size() != 0){ 
            // Allow to change with random the position with 2 cards
            Point pointDep = listOfPoint.get(rand.nextInt(listOfPoint.size()));
            listOfPoint.remove(pointDep); 
            //Remove the card to allow take only one time a card
            int xDep = (int)pointDep.getX(); 
            int yDep = (int)pointDep.getY();
            Point pointDes = listOfPoint.get(rand.nextInt(listOfPoint.size()));
            listOfPoint.remove(pointDes);
            int xDes = (int)pointDes.getX();
            int yDes = (int)pointDes.getY();
            Image tempo = this.board[xDep][yDep]; 
            // Permutation of both
            this.board[xDep][yDep] = this.board[xDes][yDes];
            this.board[xDes][yDes] = tempo;
            //change position
            this.board[xDep][yDep].setPosition(xDes, yDes); 
            this.board[xDes][yDes].setPosition(xDep, yDep);
        }
    }
    
    public void reinit(){
        List<Point> listOfPoint = new ArrayList<>(); // list to save the cards for random permutations after
        for(int x = 0; x < this.rows; x++){
            for(int y = 0; y < this.columns; y++){
                //Save the image with position, url and num 
                this.board[x][y] = new Image(listImage[x*this.rows+y], new Point(x,y));
                listOfPoint.add(new Point(x,y));
                this.board[x][y].ToString();
                //save the pair of cards (card1 with card1Bis, ...)
                if( (x + y) / 2 == 0){
                    this.board[x][y].setNum((x + y)/2);
                }else{
                    this.board[x][y].setNum(((x + y)-1 )/2);
                }
            }
        }
        this.shuffle(listOfPoint);
    }
}