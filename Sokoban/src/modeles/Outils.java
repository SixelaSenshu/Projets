package modeles;
import java.io.File;
import java.io.IOException;

/**
 * Classe qui permet de manipuler la vue txt avec l'invite de commande
 */
public class Outils {

    /**
     * Fonction qui permet la saisie d'un caractère.
     * @return Le caractère en question
     * @throws IOException : Lève une exception si il y a une erreur de saisie
     */
    public static char lireCaractere() throws IOException{
        int rep ;
        try {
            rep = System.in.read();
        }catch(IOException e){
            throw new IOException();
        }
        return (char) rep;
    }

    /**
     * Calcul la longueur d'un dossier
     * @param folder : Le dossier en question.
     * @return Le nombre de map
     */
    public static int folderSize(File folder) {
        long length = 0;
        for (File file : folder.listFiles()) { // récupère la liste des fichiers
             if (file.isFile()) length += file.length(); // si c'est un fichier, on incrémente
            else length += folderSize(file); 
        } 
        return (int)length; }

}
