package modeles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe qui permet la lecture d'un fichier pour récuperer les valeurs afin de génerer une carte
 */
public class Lecture {
    private String url;
    private BufferedReader input;
    private int width ; 
    private int heigth ; 
    private List<String> list;

    /*** Constructeur de l'objet Lecture 
     * @param url : lien vers le fichier txt contenant la map
     * @throws IOException : Lance une exception si il y a une erreur de saisie */
    public Lecture(String url) throws IOException{
        this.url = url;
        this.input = new BufferedReader(new FileReader(url));
        this.width = 0;
        this.heigth = 0;
        this.list = new ArrayList<String>();
        this.affectData();
        this.correctData();
    }

    /*** Function qui lit le fichier map et défini les paramètres de celle-ci 
     * @return si la récuperation des données est effectuer
     * @throws IOException : Lance une exception si il y a une erreur de saisie*/
    public boolean affectData() throws IOException{
        String ligne = "";
        try{
            while(( ligne = this.input.readLine()) != null){
                this.heigth ++;
                if(ligne.length() > this.width){
                    this.width = ligne.length();
                }
                this.list.add(ligne);
            }
        }catch(FileNotFoundException f){
            System.out.println("No found file");
            return false;
        }finally{
            if(input != null){
                input.close();
            }
        }
        return true;
    }

    /*** Function qui ajoute les valeurs manquantes dans le cas d'un fichier
     * avec des lignes moin grandes que d'autres
     * @return si la correction est effectuer*/
    public boolean correctData(){
        if(this.width == 0){
            return false;
        }
        int size;
        String currentLigne;
        for(int ind = 0; ind < this.list.size(); ind++){
            currentLigne = this.list.get(ind);
            size = currentLigne.length();
            if(size < this.width){
                for(int pos = size +1; pos < this.width; pos++){
                    currentLigne += "/";
                }
            }
        }
        return true;
    }

    /*** Acceseur à la longeur de la map
     * @return la valeur*/
    public int getHeight(){
        return this.heigth;
    }

    /*** Acceseur à la largeur de la map
     * @return la valeur*/
    public int getWidth(){
        return this.width;
    }

    /*** Acceseur à la liste des chaines de caractères de la map
     * @return la liste en question */
    public List<String> getList(){
        return this.list;
    }

    /*** Acceseur à l'url de la map
     * @return la valeur en question  */
    public String getUrl(){
        return this.url;
    }
}
