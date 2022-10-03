package vueGraph;

import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

import modeles.Carte;
import modeles.Lecture;

public class ModeChoice {
    private JFrame window;
    private JPanel windowContent;
    private Dimension dimWindow;

    /**
     * Constrcteur de la fenêtre de choix
     */
    public ModeChoice(){
        this.window = new JFrame("Choice");
        this.windowContent = (JPanel) window.getContentPane();
        initWindow();
        initListChoice();
    }

    /**
     * Génération de la fenêtre ainsi que ces ajustements
     */
    public void initWindow(){
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dimWindow = window.getToolkit().getScreenSize();
        window.setLocationRelativeTo(null);
		window.setLocation((dimWindow.width - window.getWidth()) / 2,(dimWindow.height - window.getHeight()) / 2);
        window.setSize(400,70);
        window.setVisible(true); 
    }

    /**
     * Génération de la liste des choix ainsi que ces ajustements
     */
    protected void initListChoice(){
        windowContent.setLayout(new FlowLayout());
        JButton buttonLvl1 = new JButton("Level 1");
        windowContent.add(buttonLvl1);
        JButton buttonLvl2 = new JButton("Level 2");
        windowContent.add(buttonLvl2);
        JButton buttonLvl3 = new JButton("Level 3");
        windowContent.add(buttonLvl3);

        Action startLvl1 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            try {
                start(1);
            } catch (IOException | UnsupportedLookAndFeelException e1) {
                e1.printStackTrace();
            }
            }
        };

        Action startLvl2 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            try {
                start(2);
            } catch (IOException | UnsupportedLookAndFeelException e1) {
                e1.printStackTrace();
            }
            }
        };

        Action startLvl3 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            try {
                start(3);
            } catch (IOException | UnsupportedLookAndFeelException e1) {
                e1.printStackTrace();
            }
            }
        };

        buttonLvl1.addActionListener(startLvl1);
        buttonLvl2.addActionListener(startLvl2);
        buttonLvl3.addActionListener(startLvl3);
    }

    /**
     * Récupération de la carte , puis lancement de la vueGraphique
     * @return Si la génération à bien été éffectué
     * @throws IOException : Lance une exception si la map n'est pas existante
     * @throws UnsupportedLookAndFeelException
     */
    protected boolean start(int args) throws IOException, UnsupportedLookAndFeelException{
        try{
            Carte map = new Carte(new Lecture("./map/map" + args + ".txt"));
            ModeVue mv = new ModeVue(map);
            Help hp = new Help();
            System.out.println("La génération de la map à fonctionné");
            return true;
        }catch(IOException io){
            System.out.println("La génération de la map à échoué");
            throw io;
        }
    }

}
