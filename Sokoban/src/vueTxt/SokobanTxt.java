package vueTxt;

import java.io.IOException;
 
/**
 * Classe qui permet le lancement du jeu Sokoban en vue textuelle
 */
public class SokobanTxt {
    /**
     * Fonction main
     * @param args : Aucune
     * @throws IOException : Lance une exception si il y a un problème de lecture
     */
    public static void main(String[] args) throws IOException{
        ModeTxt mode = new ModeTxt();
        mode.selectMap();
        mode.start();

    }


}
