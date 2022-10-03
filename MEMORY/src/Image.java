import java.awt.*;
public class Image {
    private final static int CHECK = 2; 
    private final static int NOCHECK = 0;
    private final static int TESTCHECK = 1;
    private String urlImg = "img/";
    private Point position = new Point(-1,-1);
    private int value;
    private int num; 

    protected Image(String extentionUrl, Point newPosition){
        this.urlImg += extentionUrl;
        this.position = newPosition;
        this.value = NOCHECK;
        this.num = -1;
    } 

    protected Image(){
        this("",new Point(-1,-1));
    }

    protected int getValue(){
        return this.value;
    }

    protected Image getSelf(){
        return this;
    }

    protected String getUrl(){
        return this.urlImg;
    }

    protected Point getPosition(){
        return this.position;
    }

    protected int getNum(){
        return this.num;
    }

    protected void setNum(int newNum){
        this.num = newNum;
    }
    protected void setPosition(int x , int y){
        this.position = new Point(x,y);
    }

    protected void setUrl(String url){
        this.urlImg = "..img/" + url;
    }

    protected void setChecked(int newValue){
        if(newValue == 2){
            this.value = CHECK;
        }else if(newValue == 1){
            this.value = TESTCHECK;
        }else{
            this.value = NOCHECK;
        }
    }

    protected String ToString(){
        return (this.urlImg + "/checked: " + this.value + "/num: " + this.num + "/position" + this.position.getX() + "," + this.position.getY()).toString();
    }
}
