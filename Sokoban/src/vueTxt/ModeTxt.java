package vueTxt;
import modeles.Carte;
import modeles.Move;
import modeles.Outils;
import modeles.Lecture;
import java.io.*;

/**Classe qui permet la gestion de la vue texte */
public class ModeTxt {
    private Carte mapArray;
    private Character choice;

    /**
     * Fonction qui permet le choix de la map
     * @throws IOException : Lance une exception si il y a un problème de lecture
     */
    public void selectMap() throws IOException{
        try{
            System.out.println("-------------------");
            System.out.println("Choice a map : ( 1 / 2 / 3) ");
            char choice = Outils.lireCaractere();
            mapArray = new Carte(new Lecture("./map/map" + choice + ".txt"));
           
        }
        catch (IOException e) {
           throw new IOException();
        }
    }

    /**
     * Permet le lancement de la carte en vue txt
     * @throws IOException : Lance une exception si il y a un problème de lecture
     */
    public void start() throws IOException{
        boolean end = false;
        while(end == false){
            if(mapArray.isEnd()){ // si la partie est finie, on arrete tout
                end = true;
            }
            System.out.println("                   ");
            mapArray.toStringLog();
            System.out.println("                   ");
            System.out.println("-------------------");
            System.out.println("Action ? (Z/Q/S/D): ");
            choice = Outils.lireCaractere();
            switch(choice){
                case 'Z':
                case 'z':
                    mapArray.move(Move.TOP);
                    break;
                case 'S':
                case 's':
                    mapArray.move(Move.BOTTOM);
                    break;
                case 'Q':
                case 'q':
                    mapArray.move(Move.LEFT);
                    break;
                case 'D':
                case 'd':
                    mapArray.move(Move.RIGHT);
                    break;
                case 'E':
                case 'e':
                    end = true;
                break;
                default:
                    break;
            }
        }
    }


}
