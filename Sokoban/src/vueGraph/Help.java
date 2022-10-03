package vueGraph;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;

public class Help{
    private JFrame frame;
    private JPanel windowContent;
    private Dimension dimWindow;

    public Help(){
        this.frame = new JFrame("Help");
        this.windowContent = (JPanel) frame.getContentPane();
        initwindow();
        showControls();
        System.out.println("ok");
    }

    private void showControls(){
        this.windowContent.setLayout(new GridLayout(6,1));
        windowContent.add(new JLabel("Q / <- = Mouvement à Gauche"));
        windowContent.add(new JLabel("D / -> = Mouvement à Droite"));
        windowContent.add(new JLabel("S / v = Mouvement en Bas"));
        windowContent.add(new JLabel("Z / ^ = Mouvement en Haut"));
        windowContent.add(new JLabel("R = Recommencer"));
        windowContent.add(new JLabel("H = Aides"));
    }

    private void initwindow(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        dimWindow = frame.getToolkit().getScreenSize();
		frame.setLocation(((int)dimWindow.getWidth() - frame.getWidth()) / 2,((int)dimWindow.getHeight()) - frame.getHeight() / 2);
        frame.setVisible(true);
        frame.setSize(300,500);
    }
}
