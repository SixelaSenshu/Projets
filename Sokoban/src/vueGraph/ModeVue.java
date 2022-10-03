package vueGraph;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;

import java.io.IOException;

import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import modeles.Carte;
import modeles.Case;
import modeles.Lecture;
import modeles.Move;
import modeles.Position;

public class ModeVue implements ActionListener {

    // Ensemble de constanste pour les génération d'images
    public final static String CLASSPATH = "./img/";
    public final static String WALL = CLASSPATH + "mur.gif";
    public final static String DESTINATION = CLASSPATH + "but.gif";
    public final static String BOX_DESTINATION = CLASSPATH + "caisse2.gif";
    public final static String BOX = CLASSPATH + "caisse1.gif";
    public final static String PLAYER_BOTTOM = CLASSPATH + "Bas.gif";
    public final static String PLAYER_TOP = CLASSPATH + "Haut.gif";
    public final static String PLAYER_RIGTH = CLASSPATH + "Droite.gif";
    public final static String PLAYER_LEFT = CLASSPATH + "Gauche.gif";
    public final static String GROUND = CLASSPATH + "sol.gif";

    public final static int SIZE_BOARD = 50;

    private Carte mapArray;
    private JFrame window = new JFrame("Sokoban");
    private Dimension dimWindow;
    private JMenuBar nav;
    private JPanel containerLayout = (JPanel) window.getContentPane();
    private JLabel[][] board;
    private JLabel labelScore;
    private JLabel labelCpt;
    private JLabel labelBestScore;
    private int widthBoard;
    private int heightBoard;
    private int nblvl = 3;
    private int[] cpt =  new int[nblvl];;
    private int[] maxscore=  new int[nblvl];;
    private int[] score = new int[nblvl];
    private int level = 0;

    public ModeVue(Carte mapArray) throws UnsupportedLookAndFeelException{
        this.mapArray = mapArray;
        this.widthBoard = mapArray.getWidth();
        this.heightBoard = mapArray.getHeight();
        initEvents();
    }

    private void initEvents() throws UnsupportedLookAndFeelException{
        initWindow();
        initControls();
        initFormWindow();
        initNavigation();
    }
    
    private void initWindow() throws UnsupportedLookAndFeelException{
        //Gestion du style de la jframe
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        
        //Gestion de la taille + localisation de la fenêtre
		dimWindow = window.getToolkit().getScreenSize();
        window.setLocationRelativeTo(null);
        window.setMaximumSize(new Dimension(1000,1000));
        window.setMinimumSize(new Dimension(600,600));
        window.setPreferredSize(new Dimension (600,600));
		window.setLocation((dimWindow.width - window.getWidth()) / 2,(dimWindow.height - window.getHeight()) / 2);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        //Gestion des élements de la jframe
        initFormWindow();
    }

    private void initNavigation(){
        this.nav = new JMenuBar();
        JMenu option = new JMenu("Option");
        JMenu lvl = new JMenu("Level");
        JMenu help = new JMenu("Help");
        JMenuItem reinit = new JMenuItem("Reinit");
        JMenuItem assitance = new JMenuItem("Assistance");
        JMenuItem lvl1 = new JMenuItem("Map 1");
        JMenuItem lvl2 = new JMenuItem("Map 2");
        JMenuItem lvl3 = new JMenuItem("Map 3");
        JMenuItem exit = new JMenuItem("Exit");
        option.add(reinit);
        option.add(exit);
        help.add(assitance);
        lvl.add(lvl1);
        lvl.add(lvl2);
        lvl.add(lvl3);
        this.nav.add(option);
        this.nav.add(help);
        this.nav.add(lvl);

        Action actionExit = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }  
        };

        Action actionReset = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                reinit();
            }
        };

        Action actionAssistance = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                showControls();
            }
        };

        Action actionLvl1 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    changeMap(1);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        };

        Action actionLvl2 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    changeMap(2);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        };

        Action actionLvl3 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    changeMap(3);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        };

        exit.addActionListener(actionExit);
        reinit.addActionListener(actionReset);
        help.addActionListener(actionAssistance);
        lvl1.addActionListener(actionLvl1);
        lvl2.addActionListener(actionLvl2);
        lvl3.addActionListener(actionLvl3);
        this.window.setJMenuBar(this.nav);
    }

    private JPanel initBoardLayout(){
        JPanel boardLayout = new JPanel();
        boardLayout.setVisible(true);
        boardLayout.setLayout(new GridLayout(this.heightBoard,this.widthBoard));
        this.board = new JLabel[this.heightBoard][this.widthBoard];
        Case currentCase; 
        for(int x = 0; x < this.heightBoard; x++){
            for(int y = 0; y < this.widthBoard; y++){
                currentCase = this.mapArray.getElementMap(x, y);
                switch(currentCase.getValue()){
                    case "#": 
                        this.board[x][y] = new JLabel(new ImageIcon(WALL)); 
                        break;
                    case "/": 
                        this.board[x][y] = new JLabel(new ImageIcon("")); 
                        break;
                    case ".": 
                        this.board[x][y] = new JLabel(new ImageIcon(DESTINATION)); 
                        break;
                    case "+":
                        this.board[x][y] = new JLabel(new ImageIcon(PLAYER_BOTTOM)); 
                        break;
                    case "@": 
                        this.board[x][y] = new JLabel(new ImageIcon(PLAYER_BOTTOM));
                        break;
                    case "$": 
                        this.board[x][y] = new JLabel(new ImageIcon(BOX));
                        break;
                    case "*": 
                        this.board[x][y] = new JLabel(new ImageIcon(BOX_DESTINATION));  
                        break;                      
                    case " " :
                        this.board[x][y] = new JLabel(new ImageIcon(GROUND));
                        break;
                    default: 
                        this.board[x][y] = new JLabel(new ImageIcon(""));
                        break;
                }
                this.board[x][y].setBackground(Color.WHITE);
            }
        }
        for(int x = 0; x < this.heightBoard; x++){
            for(int y = 0; y < this.widthBoard; y++){
                boardLayout.add(this.board[x][y]);
            }
        }

        return boardLayout;
    }

    private JPanel initOptionLayout() {
        JPanel optionLayout = new JPanel(new BorderLayout());
        this.labelCpt = new JLabel("Deplacement : " + cpt[this.level]);
        optionLayout.add(this.labelCpt,BorderLayout.EAST);
        this.labelScore = new JLabel("Vie : " + score[this.level]);
        optionLayout.add(this.labelScore,BorderLayout.WEST);
        optionLayout.setPreferredSize(new Dimension(300,100));
        return optionLayout;
    }

    private JPanel initScoreLayout(){
        JPanel scoreLayout = new JPanel(new BorderLayout());
        this.labelBestScore = new JLabel("Max Score : " + this.maxscore[this.level]);
        scoreLayout.add(this.labelBestScore,BorderLayout.CENTER);
        scoreLayout.setPreferredSize(new Dimension(300,100));
        return scoreLayout;
    }

    private void initControls(){
        KeyListener listener = new KeyListener(){
            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e){}
            public void keyPressed(KeyEvent e) {
                if(!end()){
                switch(e.getKeyCode()){
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_KP_LEFT:
                    case KeyEvent.VK_Q:
                        mapArray.move(Move.LEFT);
                        refreshCase("Gauche");
                        break;
    
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_KP_RIGHT:
                    case KeyEvent.VK_D:
                        mapArray.move(Move.RIGHT);
                        refreshCase("Droite");
                        break;
    
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_KP_UP:
                    case KeyEvent.VK_Z:
                        mapArray.move(Move.TOP);
                        refreshCase("Haut");
                        break;
                
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_KP_DOWN:
                    case KeyEvent.VK_S:
                        mapArray.move(Move.BOTTOM);
                        refreshCase("Bas");
                        break;
                    case KeyEvent.VK_R:
                        reinit();
                        break;
                    case KeyEvent.VK_H:
                        showControls();
                        break;
                    }
                }else{
                    updateScore();
                    reinit();
                }
            }
        };
        this.window.addKeyListener(listener);
    }

    private void initFormWindow(){
        this.window.remove(this.containerLayout);
        JPanel optionPanel = initOptionLayout();
        optionPanel.setVisible(true); 
        optionPanel.updateUI();
        JPanel boardPanel = initBoardLayout();
        boardPanel.setVisible(true);
        boardPanel.updateUI();
        JPanel bestScore = initScoreLayout();
        bestScore.setVisible(true);
        bestScore.updateUI();
        containerLayout.add(optionPanel,BorderLayout.NORTH);
        containerLayout.add(boardPanel,BorderLayout.CENTER);
        containerLayout.add(bestScore,BorderLayout.SOUTH);
        containerLayout.setVisible(true); 
    }

    private void reinit(){
        this.mapArray.changeMap();
        this.cpt[this.level] = 0;
        this.score[this.level] = 0;
        initFormWindow();
    }

    private void changeMap(int arg) throws IOException{
        this.mapArray.changeMapData(new Lecture("./map/map" + arg + ".txt"));
        this.level = arg-1;
        this.cpt[this.level] = 0;
        this.score[this.level] = 0;
        this.widthBoard = mapArray.getWidth();
        this.heightBoard = mapArray.getHeight();
        initFormWindow();
    }

    private boolean end(){
        return this.mapArray.isEnd();
    }

    private void refreshCase(String move){
        List<Position> list = this.mapArray.getListCaseChanged();
        if(!list.isEmpty()){
            cpt[this.level]++;
            this.labelCpt.setText("Deplacement : " + cpt[this.level]);
            score[this.level] = this.mapArray.getScore();
            this.labelScore.setText("Vie : " + score[this.level]);
            for(int i = 0; i < list.size(); i++){
                int x = list.get(i).getX();
                int y = list.get(i).getY();
                switch(this.mapArray.getElementMap(x, y).getValue()){
                    case "#": 
                        this.board[x][y].setIcon(new ImageIcon(WALL));
                        break;
                    case "/": 
                        this.board[x][y].setIcon(new ImageIcon(""));
                        break;
                    case ".": 
                        this.board[x][y].setIcon(new ImageIcon(DESTINATION)); 
                        break;
                    case "+":
                        this.board[x][y].setIcon(new ImageIcon(CLASSPATH + move + ".gif")); 
                        break;
                    case "@": 
                        this.board[x][y].setIcon(new ImageIcon(CLASSPATH+ move + ".gif"));
                        break;
                    case "$": 
                        this.board[x][y].setIcon(new ImageIcon(BOX));
                        break;
                    case "*": 
                        this.board[x][y].setIcon(new ImageIcon(BOX_DESTINATION));  
                        break;                      
                    case " " :
                        this.board[x][y].setIcon(new ImageIcon(GROUND));
                        break;
                    default: 
                        this.board[x][y].setIcon(new ImageIcon(""));
                    break;
                }
            }
        }
    }

    private void updateScore(){
        if(this.cpt[this.level] < this.maxscore[this.level] && this.cpt[this.level] != 0){
            this.maxscore[this.level] = this.cpt[this.level];
            this.labelBestScore.setText("Max Score : " + this.maxscore[this.level]);
        }
    }

    private void showControls(){
        Help hp = new Help();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click");
    }
}

