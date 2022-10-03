import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import javax.swing.*;

import databaseGestionnary.*;

public class Fenetre extends JFrame implements ActionListener {
    
    // Actions nécessaires au bon fonctionnement de l'application
    Action exit;
    Action reset;
    Action update;
    Action about;
        // Actions qui agissent sur la base de données
    Action addRecipe;
    Action addIngredient;
    Action addMenu;

    Action deleteRecipe;
    Action deleteIngredient;
    Action deleteMenu;

        // Actions qui agissent sur l'interface graphique
    Action displayRecipe;
    Action displayIngredient;
    Action displayMenu;

    Action displayView;

    Action searchRecipe;
    Action searchIngredient;
    Action searchMenu;

    //
    Action modifRecipe;
    Action modifIngredient;
    Action modifMenu;

    //
    Action addRecipePanel;
    Action addIngredientPanel;
    Action addMenuPanel;

    //
    Action deleteRecipePanel;
    Action deleteIngredientPanel;
    Action deleteMenuPanel;

        // ======================== //
    boolean canDeleteIng = false;
    boolean canDeleteRecipe = false;
    boolean canDeleteMenu = false;



    public Fenetre() {
        // Initialisation de la fenetre du menu Principal
        super("Le four à repasser");
        System.out.println("Bienvenue dans le four à repasser !");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        initActions();
        initMenuBar();
        setVisible(true);
    }

    /**
     * Initialise la barre de menu regroupant toutes les actions possibles pour l'utilisateur et ajoute la barre à la JFrame instanciée.
     */
    private void initActions() {
        exit = new AbstractAction("Quitter") {
			public void actionPerformed(ActionEvent e) {
                System.out.println("Fermeture de l'application");
				System.exit(0);
			}
        };
        reset = new AbstractAction("Reset") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Redémarrage de l'application.");
                restart();
            }
        };
        update = new AbstractAction("Actualisation") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Actualisation de la base de données");
                // A faire
            }
        };
        about = new AbstractAction("à propos") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Cette foncionnalité n'a pas encore été implémentée :(");
            }
        };

        addRecipe = new AbstractAction("Ajout recette") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ajout d'une recette à la base de données");
                // A faire
            }
        };

        addIngredient = new AbstractAction("Ajout ingredient") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ajout d'un ingredient à la base de données");
                // A faire
            }
        };

        addMenu = new AbstractAction("Ajout menu") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ajout d'un menu à la base de données");
                // A faire
            }
        };

        deleteRecipe = new AbstractAction("Supprimer recette") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Supprimer une recette");
                // A faire
            }
        };

        deleteIngredient = new AbstractAction("Supprimer ingredient") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("");
                // A faire
            }
        };

        deleteMenu = new AbstractAction("Supprimer menu") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("");
                // A faire
            }
        };

        displayRecipe = new AbstractAction("Afficher recette") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Affichage de l'intégralité des recettes présentes dans la base de données");
                // A faire
                try {
                    initDisplay("Recette");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        };

        displayIngredient = new AbstractAction("Afficher ingredient") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Affichage de l'intégralité des ingrédients présents dans la base de données");
                // A faire
                try {
                    initDisplay("Ingredient");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        };

        displayMenu = new AbstractAction("Afficher menu") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Affichage de l'intégralité des menus présents dans la base de données");
                // A faire
                    try {
                        initDisplay("Menu");
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
            }
        };

        searchRecipe = new AbstractAction("Rechercher recette") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Recherche d'une recette");
                // A faire
                initSearchRecipePanel();
            }
        };

        addRecipePanel = new AbstractAction("Fenetre ajout recette") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ouverture de la fenetre d'ajout de recette");
                try {
                    initAddPanel("Recette");
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                // A faire
            }
        };

        addIngredientPanel = new AbstractAction("Fenetre ajout ingredient") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ouverture de la fenetre d'ajout d'ingredient.");
                // A faire
                try {
                    initAddPanel("Ingredient");
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        };

        addMenuPanel = new AbstractAction("Fenetre ajout menu") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ouverture de la fenetre d'ajout de menu");
                // A faire
                try {
                    initAddPanel("Menu");
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        };

        deleteRecipePanel = new AbstractAction("Fenetre supprimer recette") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("");
                // A faire
            }
        };

        deleteIngredientPanel = new AbstractAction("Fenetre supprimer recette") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("");
                // A faire
            }
        };

        deleteMenuPanel = new AbstractAction("Fenetre supprimer recette") {
            public void actionPerformed(ActionEvent e) {
                System.out.println("");
                // A faire
            }
        };

    }

    /**
     * Redémarre la fenêtre
     */
    private void restart() {
        // Ferme la fenêtre en cours
        this.dispose();
        // Instancie une nouvelle instance de la classe Fenetre et donc ouvre une nouvelle fenêtre
        new Fenetre();
    }

    /**
     * Génère la barre de menu en haut de la fenetre, permettant de réaliser toutes les acions permises à l'utilisateur.
     */
    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        // Rubriques de la barre de menu
        JMenu menuDisplay= new JMenu("Afficher");
        JMenu menuSearch = new JMenu("Rechercher");
        JMenu menuModif = new JMenu("Modifications");
        JMenu menuAdd = new JMenu("Ajouter");
        JMenu menuDelete = new JMenu("Supprimer");
        JMenu menuHelp = new JMenu("Aide");
        JMenu menuQuit = new JMenu("Quitter");

        // Items des différentes rubriques
        JMenuItem itemDisplayRecipe = new JMenuItem("Afficher la liste des recettes.");
        JMenuItem itemDisplayIngredient= new JMenuItem("Afficher la liste des ingrédients.");
        JMenuItem itemDisplayMenu = new JMenuItem("Afficher la liste des menus.");

        JMenuItem itemSearchRecipe = new JMenuItem("Rechercher une recette");
        JMenuItem itemSearchIngredient = new JMenuItem("Rechercher un ingrédient");
        JMenuItem itemSearchMenu = new JMenuItem("Rechercher un menu");

        JMenuItem itemModifRecipe = new JMenuItem("Modifier une recette");
        JMenuItem itemModifIngredient = new JMenuItem("Midifier un ingrédient");
        JMenuItem itemModifMenu = new JMenuItem("Modifier un menu");

        JMenuItem itemAddRecipe = new JMenuItem("Ajouter une recette");
        JMenuItem itemAddIngredient = new JMenuItem("Ajouter un ingrédient");
        JMenuItem itemAddMenu = new JMenuItem("Ajouter un menu");

        JMenuItem itemDeleteRecipe = new JMenuItem("Supprimer une recette");
        JMenuItem itemDeleteIngredient = new JMenuItem("Supprimer un ingrédient");
        JMenuItem itemDeleteMenu = new JMenuItem("Supprimer un menu");
        
        JMenuItem itemAbout = new JMenuItem("à propos.");

        JMenuItem itemQuit = new JMenuItem("Quitter");
        JMenuItem itemReset = new JMenuItem("Redémarrer");

        // Ajout des items aux rubriques

        menuDisplay.add(itemDisplayRecipe);
        menuDisplay.add(itemDisplayIngredient);
        menuDisplay.add(itemDisplayMenu);

        menuModif.add(itemModifRecipe);
        menuModif.add(itemModifIngredient);
        menuModif.add(itemModifMenu);

        menuSearch.add(itemSearchRecipe);
        menuSearch.add(itemSearchIngredient);
        menuSearch.add(itemSearchMenu);

        menuAdd.add(itemAddRecipe);
        menuAdd.add(itemAddIngredient);
        menuAdd.add(itemAddMenu);

        menuDelete.add(itemDeleteRecipe);
        menuDelete.add(itemDeleteIngredient);
        menuDelete.add(itemDeleteMenu);

        menuHelp.add(itemAbout);

        menuQuit.add(itemQuit);
        menuQuit.add(itemReset);

        // Action listeners
        itemQuit.addActionListener(exit);
        itemReset.addActionListener(reset);
        itemDisplayRecipe.addActionListener(displayRecipe);
        itemDisplayIngredient.addActionListener(displayIngredient);
        itemDisplayMenu.addActionListener(displayMenu);
        itemModifRecipe.addActionListener(modifRecipe);
        itemModifIngredient.addActionListener(modifIngredient);
        itemModifMenu.addActionListener(modifMenu);
        itemSearchRecipe.addActionListener(searchRecipe);
        itemSearchIngredient.addActionListener(searchIngredient);
        itemSearchMenu.addActionListener(searchMenu);
        itemAddRecipe.addActionListener(addRecipePanel);
        itemAddIngredient.addActionListener(addIngredientPanel);
        itemAddMenu.addActionListener(addMenuPanel);

        itemAbout.addActionListener(about);

        // Ajout des rubriques à la barre de menu.
		menuBar.add(menuDisplay);
		menuBar.add(menuModif);
		menuBar.add(menuSearch);
        menuBar.add(menuAdd);
        menuBar.add(menuDelete);
        menuBar.add(menuHelp);
        menuBar.add(menuQuit);
		setJMenuBar(menuBar);
    }

    /**
     * Genere un scrollpane contenant toutes les recettes enregistrées dans la base de données.
     * Retire de la fenetre tous les éléments sauf la barre de menu.
     * @throws SQLException
     * @throws DataException
     */
    private void initDisplay(String param) throws SQLException {
        Container fen= this.getContentPane();
        fen.removeAll();
        fen.setLayout(null);

        Select currentRequete = new Select(param);
        // Exécution de la requête
        currentRequete.executerRequete();
        List<Map<String, String>>  lm = currentRequete.getResultat();
        Map<String, String> ligne;
        System.out.println(currentRequete.formulerRequete());

        

        ResultSet rsmd = currentRequete.getResultSet();
        System.out.println("zqger");
        System.out.println(rsmd);
        String name;

        int n = lm.size();
        Object[][] data = new Object[n][lm.get(0).size()];
        String[] columns = new String[lm.get(0).size()+1];
        ligne = lm.get(0);

        //Probleme ! ligne contient des valeurs nulles ! Raison inconnue ...
        for (int i = 0; i < n; i++) {
            Object[] temp = new Object[ligne.size()];
            ligne = lm.get(i);
            for (int j = 0; j < ligne.size(); j++) {
                String vals = ligne.get(j);
                System.out.println(vals);
                temp[j] = vals;
            }
            data[i] = temp;
            rsmd.next();
        }

        // Récupération des noms de colonne ==> Fonctionne !
        Set<String> set = ligne.keySet();
        System.out.println(set);

        Object[] obj = set.toArray();
        System.out.println(data);
        for (int i = 0; i < data.length; i++) {
            for(int j = 0; j < data[i].length; j++) {
                System.out.println(data[i][j]);
            }
        }

        JTable table = new JTable(data, obj) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        JScrollPane scrollPanelRecipe = new JScrollPane(table);
        JLabel label = new JLabel("Recettes contenues dans la base de données:");
        // Positionnement du JScrollPane et du label
        scrollPanelRecipe.setBounds(40, 50, 700, 450);
        //Affichage et parametrage des ScrollBar
        scrollPanelRecipe.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPanelRecipe.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanelRecipe.getHorizontalScrollBar().setUnitIncrement(10);
        scrollPanelRecipe.getVerticalScrollBar().setUnitIncrement(10);

        table.setRowSelectionAllowed(false);

        fen.add(label);
        fen.add(scrollPanelRecipe);
        this.setContentPane(fen);
    }
    
    /**
     * Genere le panel de recherche de recette
     */
    private void initSearchRecipePanel() {
        Container fen = getContentPane();
        fen.removeAll();
        fen.setLayout(null);
        JPanel panel = new JPanel();


    }

    private void initAddPanel(String param) throws SQLException {
        Container fen= this.getContentPane();
        fen.removeAll();
        fen.setLayout(null);
        // Récupération des attributs
        Select currentRequete = new Select(param);
        List<String> attr = new ArrayList<String>();
        // Exécution de la requête
        currentRequete.executerRequete();
        List<Map<String, String>>  lm = currentRequete.getResultat();
        Map<String, String> ligne = lm.get(0);
        Set<String> set = ligne.keySet();
        System.out.println(set);
        
        // Ajout des atttributs de la table à la liste attr
        for (int i = 0; i < set.size(); i++) {
            attr.add(set.toArray()[i].toString());
        }
        // On empêche à l'utilisateur de choisir un id pour eviter de choisir un id deja existant
        attr.remove(1);
        
        // Création du nombre de JTextLabel proportionnellement au nombre d'attributs.
        // Liste des textfield que l'utilisateur doit remplir pour renseigner les attributs.
        List<JTextField> listeTxtField = new ArrayList<JTextField>();
        List<JLabel> listeLabels = new ArrayList<JLabel>();
        for(int i = 0; i < attr.size(); i++) {
            // Ajout d'un nouveau label
            JLabel label = new JLabel(attr.get(i));
            Dimension dim = new Dimension(10, 10);
            label.setMaximumSize(dim);
            listeLabels.add(label);
            // Ajout d'un txtField pour renseigner la valeur de l'attribut donné
            JTextField txtField = new JTextField();
            listeTxtField.add(txtField);
        }
        GridLayout layout = new GridLayout(attr.size()+2, 2, 10, 10);
        Container c = new Container();
        c.setLayout(layout);
        c.setSize(420, 300);
        c.setBackground(Color.YELLOW);
        for (int i = 0; i < attr.size(); i++) {
            c.add(listeLabels.get(i));
            c.add(listeTxtField.get(i));
        }

        Insertion ins = new Insertion(param);
        // CE BOUTON SERT A EXECUTER UNE METHODE QUI PRENDRA EN PARAMETRE LE CONTAINER DES TEXTFIELD
        JButton confirmer = new JButton("Confirmer");
        // CHANGER L ACTION DE ANNULER DANS LA FONCION INIT ACTIONS ET AJOUTER UNE ACTION QUI SUPPRIME
        // UNIQUEMENT LES VALEURS DANS LES TEXTFIELD AU LIEU DE REDEMARRER LA FENETRE (BONUS)
        JButton annuler = new JButton("Annuler");
        annuler.addActionListener(reset);
        confirmer.addActionListener(addIngredient);

        c.add(confirmer);
        c.add(annuler);

        fen.add(c);
        setContentPane(fen);

        /*
        Insertion ins = new Insertion(nomTable);
        ins.ajouterAttributs(nomsColonnes);
        for (int i = 0; i < nomsColonnes.size(); i++) {
            System.out.printf("Veuillez rajouter une donnée pour " + nomsColonnes.get(i) + ". ");
            variables.add(lectureChaine());
        }
        ins.ajouterValeur(variables);
        System.out.println();
        */
    }

    /**
     * Affiche la recette sur la ligne ou le bouton a été cliqué
     */
    private void afficheRecette() {
    }

    @Override
	public void actionPerformed(ActionEvent e) {
	}
    
}

	
