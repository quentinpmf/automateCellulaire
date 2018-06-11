import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static java.lang.System.exit;

public class MainGUI extends JFrame implements ActionListener, ChangeListener {

    private GridBagLayout gbTrace = new GridBagLayout();
    private JTextField nbIterationsMax = new JTextField();
    private static JLabel nbIterationsFaites = new JLabel("" + Grid.nbIterations);
    private static JLabel lblReglesChoisies = new JLabel("∅");
    private static JLabel labelEmpty = new JLabel();
    private Cell[][] cells;
    private static ArrayList<String> reglesChoisies = new ArrayList<>();
    public static ArrayList<Rule> rules = new ArrayList<Rule>();
    private Grid grid;
    private static JComboBox cbLoad, cbRules;
    private static JButton btnReset, btnStart, btnAppliquer, btnQuit, btnCreate, btnPopupCreate;
    private JSlider slider;
    private JLabel lblSpeed;
    private JTextField RuleName = new JTextField();
    private static JComboBox cbRegleOperateur,cbEtatInitial,cbEtatFinal;
    private JTextField regleNbVoisins = new JTextField();

    //Constructeur
    public MainGUI() {

        JPanel dialogBox = new JPanel(new BorderLayout());
        int rows = Integer.parseInt(JOptionPane.showInputDialog(dialogBox, "Nombre de lignes ? [minimum 50]", "50"));

        if (rows >= 50) {
            setTitle("Automate Cellulaire [Boudinot Delcourt Martinelli]");
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            setResizable(true);
            setLayout(gbTrace);

            JPanel panel_speedcontrol = initSpeedControl();
            JPanel panel_cells = initCells(rows);
            JPanel panel_haut = initPanelHaut();
            JPanel panel_milieu = initPanelMilieu();
            JPanel panel_bas = initPanelBas();

            setLayout(new BorderLayout());
            //création du panel gauche
            JPanel panel_gauche = new JPanel(new BorderLayout());
            panel_gauche.setLayout(new BorderLayout());
            panel_gauche.setMaximumSize(new Dimension(300, 800));
            panel_gauche.setPreferredSize(new Dimension(300, 800));
            panel_gauche.add(panel_haut, BorderLayout.NORTH);
            panel_gauche.add(panel_milieu, BorderLayout.CENTER);
            panel_gauche.add(panel_bas, BorderLayout.SOUTH);

            //création du panel droite
            JPanel panel_droite = new JPanel(new BorderLayout());
            panel_droite.setLayout(new BorderLayout());
            panel_droite.setMaximumSize(new Dimension(700, 800));
            panel_droite.setPreferredSize(new Dimension(700, 800));
            panel_droite.add(panel_speedcontrol, BorderLayout.NORTH);
            panel_droite.add(panel_cells, BorderLayout.CENTER);

            //ajout des deux panel gauche et droite dans le jframe
            add(panel_gauche, BorderLayout.WEST);
            add(panel_droite, BorderLayout.EAST);

            //sizes the frame so that all its contents are at or above their preferred sizes
            pack();

            this.grid = new Grid(cells, rows);
            grid.setSpeed(50);
            slider.setValue(50);
            setVisible(true);
            new Thread(grid).start();
        }
    }

    //initialise la barre du controle de la vitesse d'animation
    private JPanel initSpeedControl() {
        JPanel pan = new JPanel(new BorderLayout());
        pan.setBorder(BorderFactory.createTitledBorder("Vitesse d'animation"));
        pan.setLayout(new BoxLayout(pan, BoxLayout.X_AXIS));

        slider = new JSlider();
        slider.setMaximum(500);
        slider.setMinimum(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(this);
        slider.setValue(50);

        lblSpeed = new JLabel(slider.getValue() + "ms");

        pan.add(lblSpeed);
        pan.add(Box.createHorizontalStrut(5));
        pan.add(slider);
        return pan;
    }

    //initialise la grille avec toutes les cellules
    private JPanel initCells(int cell_rows) {
        JPanel pan = new JPanel();
        pan.setBorder(BorderFactory.createTitledBorder("Grille"));
        pan.setLayout(new GridLayout(cell_rows, cell_rows));
        cells = new Cell[cell_rows][cell_rows];
        for (int i = 0; i < cell_rows; i++) {
            for (int j = 0; j < cell_rows; j++) {
                Cell c = new Cell();
                cells[i][j] = c;
                pan.add(c);
            }
        }
        return pan;
    }

    //initialise la partie du panel_gauche : OUVRIR MODELE et CONFIGURATION
    private JPanel initPanelHaut() {
        JPanel panel_haut = new JPanel(new BorderLayout());
        panel_haut.setLayout(new BoxLayout(panel_haut, BoxLayout.Y_AXIS));

        //1 : PANEL MODELE
        JPanel panel_modele = new JPanel(new BorderLayout());
        panel_modele.setBorder(BorderFactory.createTitledBorder("Ouvrir modèle"));
        panel_modele.setLayout(new BoxLayout(panel_modele, BoxLayout.X_AXIS));
        panel_modele.add(Box.createHorizontalStrut(5));
        String[] strPattern = new String[]{"-- Choisir --", "Small exploder", "spaceShip", "Ten Cell Row", "Gosper glider gun", "Glider", "Test"};
        cbLoad = new JComboBox(strPattern);
        cbLoad.addActionListener(this);
        panel_modele.add(cbLoad);
        //2 : PANEL CONFIG
        JPanel panel_config = new JPanel(new BorderLayout());

        panel_config.setBorder(BorderFactory.createTitledBorder("Configuration"));
        panel_config.setLayout(new BoxLayout(panel_config, BoxLayout.Y_AXIS));

        //2.1 : PANEL ITERATIONS
        JPanel panel_iterations = new JPanel(new BorderLayout());
        panel_iterations.setBorder(BorderFactory.createTitledBorder("Nombre d'itérations"));
        panel_iterations.setLayout(new BoxLayout(panel_iterations, BoxLayout.X_AXIS));

        nbIterationsFaites.setText("0");
        panel_iterations.add(nbIterationsFaites, BorderLayout.WEST);

        //2.1 : PANEL ITERATIONS_MAX
        JPanel panel_iterations_max = new JPanel(new BorderLayout());
        panel_iterations_max.setBorder(BorderFactory.createTitledBorder("Nombre d'itérations max"));
        panel_iterations_max.setLayout(new BoxLayout(panel_iterations_max, BoxLayout.X_AXIS));

        nbIterationsMax.setText("20");
        nbIterationsMax.setPreferredSize(new Dimension(40, 20));
        nbIterationsMax.setMaximumSize(new Dimension(40, 20));
        panel_iterations_max.add(nbIterationsMax);

        setLayout(new BorderLayout());
        panel_config.setLayout(new BorderLayout());
        panel_config.add(panel_iterations, BorderLayout.NORTH);
        panel_config.add(panel_iterations_max, BorderLayout.SOUTH);

        panel_haut.add(panel_modele, BorderLayout.NORTH);
        panel_haut.add(panel_config, BorderLayout.SOUTH);
        return panel_haut;
    }

    //initialise la partie du panel_gauche : REGLES
    private JPanel initPanelMilieu() {
        JPanel panel_milieu = new JPanel(new BorderLayout());
        panel_milieu.setLayout(new BoxLayout(panel_milieu, BoxLayout.Y_AXIS));

        //1: PANEL REGLES
        JPanel panel_regles = new JPanel(new BorderLayout());
        panel_regles.setBorder(BorderFactory.createTitledBorder("Règles"));
        panel_regles.setLayout(new BoxLayout(panel_regles, BoxLayout.X_AXIS));
        //1.1 : PANEL LISTE+APPLIQUER
        JPanel panel_liste_appliquer = new JPanel(new BorderLayout());
        panel_liste_appliquer.setBorder(BorderFactory.createTitledBorder("Liste des règles disponibles"));
        panel_liste_appliquer.setLayout(new BoxLayout(panel_liste_appliquer, BoxLayout.X_AXIS));
        String[] strPattern = new String[]{"-- Choisir --", "Jeu de la vie"};
        cbRules = new JComboBox(strPattern);
        cbRules.addActionListener(this);

        btnAppliquer = new JButton("Appliquer");
        btnAppliquer.addActionListener(this);

        panel_liste_appliquer.add(cbRules, BorderLayout.WEST);
        panel_liste_appliquer.add(Box.createHorizontalStrut(5));
        panel_liste_appliquer.add(btnAppliquer, BorderLayout.EAST);

        //1.2 : PANEL REGLES CHOISIES
        JPanel panel_regles_choisies = new JPanel(new BorderLayout());
        panel_regles_choisies.setBorder(BorderFactory.createTitledBorder("Liste des règles choisies"));
        panel_regles_choisies.setLayout(new BoxLayout(panel_regles_choisies, BoxLayout.Y_AXIS));
        panel_regles_choisies.add(lblReglesChoisies, BorderLayout.WEST);

        //1.3 : PANEL CREER
        JPanel panel_creer = new JPanel(new BorderLayout());
        panel_creer.setLayout(new BoxLayout(panel_creer, BoxLayout.Y_AXIS));

        JFrame fenetre_creation_regles = new JFrame();
        fenetre_creation_regles.setTitle("Création d'une règle");
        fenetre_creation_regles.setSize(350, 350);
        fenetre_creation_regles.setLocationRelativeTo(null);

        JPanel panel_fenetre_rules = new JPanel(new BorderLayout());
        panel_fenetre_rules.setLayout(new BoxLayout(panel_fenetre_rules, BoxLayout.Y_AXIS));

        //nom de la règle
        JPanel panel_fenetre_rules_name = new JPanel(new BorderLayout());
        panel_fenetre_rules_name.setBorder(BorderFactory.createTitledBorder("Nom de la règle"));
        panel_fenetre_rules_name.setLayout(new BoxLayout(panel_fenetre_rules_name, BoxLayout.Y_AXIS));
        RuleName.setPreferredSize(new Dimension(300, 20));
        RuleName.setMaximumSize(new Dimension(300, 20));
        panel_fenetre_rules_name.add(RuleName);

        //état initial
        JPanel panel_fenetre_rules_state = new JPanel(new BorderLayout());
        panel_fenetre_rules_state.setBorder(BorderFactory.createTitledBorder("Etat initial"));
        panel_fenetre_rules_state.setLayout(new BoxLayout(panel_fenetre_rules_state, BoxLayout.Y_AXIS));
		String[] strEtats = new String[]{"Blanc", "Noir"};
		cbEtatInitial = new JComboBox(strEtats);
		cbEtatInitial.setPreferredSize(new Dimension(300, 20));
		cbEtatInitial.setMaximumSize(new Dimension(300, 20));
		cbEtatInitial.addActionListener(this);
		panel_fenetre_rules_state.add(cbEtatInitial);

        //opérateurs
        JPanel panel_fenetre_rules_operators = new JPanel(new BorderLayout());
        panel_fenetre_rules_operators.setBorder(BorderFactory.createTitledBorder("Opérateur"));
        panel_fenetre_rules_operators.setLayout(new BoxLayout(panel_fenetre_rules_operators, BoxLayout.Y_AXIS));
        String[] strOperators = new String[]{"+", "-", "="};
        cbRegleOperateur = new JComboBox(strOperators);
        cbRegleOperateur.setPreferredSize(new Dimension(300, 20));
        cbRegleOperateur.setMaximumSize(new Dimension(300, 20));
        cbRegleOperateur.addActionListener(this);
        panel_fenetre_rules_operators.add(cbRegleOperateur);

        //nombre de voisins
        JPanel panel_fenetre_rules_nbvoisins = new JPanel(new BorderLayout());
        panel_fenetre_rules_nbvoisins.setBorder(BorderFactory.createTitledBorder("Nombre de voisins"));
        panel_fenetre_rules_nbvoisins.setLayout(new BoxLayout(panel_fenetre_rules_nbvoisins, BoxLayout.Y_AXIS));
        regleNbVoisins.setPreferredSize(new Dimension(300, 20));
        regleNbVoisins.setMaximumSize(new Dimension(300, 20));
        panel_fenetre_rules_nbvoisins.add(regleNbVoisins);

        //état final
        JPanel panel_fenetre_rules_finalstate = new JPanel(new BorderLayout());
        panel_fenetre_rules_finalstate.setBorder(BorderFactory.createTitledBorder("Etat final"));
        panel_fenetre_rules_finalstate.setLayout(new BoxLayout(panel_fenetre_rules_finalstate, BoxLayout.Y_AXIS));
		cbEtatFinal = new JComboBox(strEtats);
		cbEtatFinal.setPreferredSize(new Dimension(300, 20));
		cbEtatFinal.setMaximumSize(new Dimension(300, 20));
		cbEtatFinal.addActionListener(this);
		cbEtatFinal.setSelectedItem("Noir");
		panel_fenetre_rules_finalstate.add(cbEtatFinal);

        //bouton créer
        btnPopupCreate = new JButton("Créer");
        btnPopupCreate.addActionListener(this);

        JPanel panel_fenetre_rules_btnCreer = new JPanel(new BorderLayout());
        panel_fenetre_rules_btnCreer.setLayout(new BoxLayout(panel_fenetre_rules_btnCreer, BoxLayout.Y_AXIS));
        panel_fenetre_rules_btnCreer.add(btnPopupCreate);

        panel_fenetre_rules.add(panel_fenetre_rules_name);
        panel_fenetre_rules.add(panel_fenetre_rules_state);
        panel_fenetre_rules.add(panel_fenetre_rules_nbvoisins);
        panel_fenetre_rules.add(panel_fenetre_rules_finalstate);
        panel_fenetre_rules.add(panel_fenetre_rules_operators);
        panel_fenetre_rules.add(panel_fenetre_rules_btnCreer);

        fenetre_creation_regles.add(panel_fenetre_rules);

        btnCreate = new JButton("Créer une règle");
        btnCreate.addActionListener(this);

        //on ouvre la popup au click sur Créer
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //rendre visible
                fenetre_creation_regles.setVisible(true);
            }
        });

        //on ferme la popup au click sur Créer et on ajoute la règle
        btnPopupCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ajout de la nouvelle regle au tableau des regles
				int etatInitial = getIntFromCbChar(cbEtatInitial);
				int etatFinal = getIntFromCbChar(cbEtatFinal);

                rules.add(new Rule(RuleName.getText(), etatInitial, (String) cbRegleOperateur.getSelectedItem(), Integer.parseInt(regleNbVoisins.getText()), etatFinal, false));
                //ajout de la nouvelle regle dans le ComboBox
                cbRules.addItem(RuleName.getText());
                //selection de la nouvelle regle dans le ComboBox
                cbRules.setSelectedIndex(cbRules.getItemCount() - 1);
                //cacher la popup
                fenetre_creation_regles.setVisible(false);
                //réinitialisation des paramètres
                RuleName.setText("");
				cbEtatInitial.setSelectedItem("Blanc");
				cbEtatFinal.setSelectedItem("Noir");
                regleNbVoisins.setText("");
            }
        });

        //au clic sur appliquer, on ajoute la regle selectionner
        btnAppliquer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get selected item in combobox
                Object ruleName = cbRules.getSelectedItem();
                //if différent de la regle --Choisir--
                if (ruleName == "Jeu de la vie") {
                    if(!lblReglesChoisies.getText().contains("Jeu de la vie")) {
                        if (lblReglesChoisies.getText() == "∅") lblReglesChoisies.setText("");
                        //JeuDeLaVie rules
                        //Si une cellule a exactement deux voisines vivantes, elle reste dans son état actuel à l’étape suivante.
                        rules.add(new Rule("JDV1", 0, "=", 2, 0, true));
                        rules.add(new Rule("JDV2", 1, "=", 2, 1, true));
                        //Si une cellule a exactement trois voisines vivantes, elle est vivante à l’étape suivante.
                        rules.add(new Rule("JDV3", 0, "=", 3, 1, true));
                        rules.add(new Rule("JDV4", 1, "=", 3, 1, true));
                        //Si une cellule a strictement moins de deux ou strictement plus de trois voisines vivantes, elle est morte à l’étape suivante.
                        rules.add(new Rule("JDV5", 1, ">", 3, 0, true));
                        rules.add(new Rule("JDV6", 1, "<", 2, 0, true));
                        lblReglesChoisies.setText(lblReglesChoisies.getText() + ruleName + " /");
                    }
                } else if (ruleName != "-- Choisir --") {
                    for (Rule item : rules) {
                        if (ruleName.equals(item.getName())) {
                            if(!item.isActivated()) {
                                if(lblReglesChoisies.getText() == "∅")lblReglesChoisies.setText("");
                                item.setActivated(true);
                                lblReglesChoisies.setText(lblReglesChoisies.getText() + item.getName() + " /");
                                break;
                            }
                        }
                    }
                }
                checkIfIssetOneRule();
            }
        });

        panel_creer.add(btnCreate);

        setLayout(new BorderLayout());
        panel_regles.setLayout(new BorderLayout());
        panel_regles.add(panel_liste_appliquer, BorderLayout.NORTH);
        panel_regles.add(panel_regles_choisies, BorderLayout.CENTER);
        panel_regles.add(panel_creer, BorderLayout.SOUTH);

        //2: PANEL VIDE
        JPanel panel_vide = new JPanel(new BorderLayout());
        panel_vide.setLayout(new BoxLayout(panel_vide, BoxLayout.Y_AXIS));
        labelEmpty.setText("");
        panel_vide.add(labelEmpty);

        setLayout(new BorderLayout());
        panel_milieu.setLayout(new BorderLayout());
        panel_milieu.add(panel_regles, BorderLayout.NORTH);
        panel_milieu.add(panel_vide, BorderLayout.SOUTH);

        return panel_milieu;
    }

    //initialise la partie du panel_gauche : ACTIONS
    private JPanel initPanelBas() {
        JPanel pan = new JPanel(new BorderLayout());
        pan.setBorder(BorderFactory.createTitledBorder("Actions"));
        pan.setLayout(new BoxLayout(pan, BoxLayout.X_AXIS));

        btnStart = new JButton("Démarrer");
        btnStart.addActionListener(this);
        btnReset = new JButton("Réinitialiser");
        btnReset.addActionListener(this);
        btnQuit = new JButton("Quitter");
        btnQuit.addActionListener(this);

        pan.add(Box.createHorizontalGlue());
        pan.add(btnStart);
		btnStart.setEnabled(false);
        pan.add(Box.createHorizontalStrut(5));
        pan.add(btnReset);
        pan.add(Box.createHorizontalStrut(5));
        pan.add(btnQuit);

        return pan;
    }

    public static void main(String[] args) {
        // putting the GUI in the Event dispatch thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainGUI();
            }
        });
    }

    //tue toutes les cellules
    private void killAll() {
        for (int i = 0; i < grid.rows; i++) {
            for (int j = 0; j < grid.rows; j++) {
                cells[i][j].setState(0);
                cells[i][j].setNextState(0);
                cells[i][j].display(cells[i][j].getState());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == btnStart) {
            System.out.println(rules);
            if (btnStart.getText().equals("Démarrer")) {
                btnStart.setText("Arrêter");
                grid.resume(nbIterationsMax.getText());
            } else {
                btnStart.setText("Démarrer");
                grid.pause();
            }
        } else if (obj == btnReset) {
            killAll();
            //reset nbIterations + nbMaxIterations
            nbIterationsFaites.setText("0");
            lblReglesChoisies.setText("∅");
            reglesChoisies.clear();
            Grid.nbIterations = 0;
            Grid.nbIterationsMax = 0;
            Grid.IterationMaxAtteint = 0;
			btnStart.setEnabled(false);
            cbRules.removeAllItems();
            cbRules.addItem("--Choisir--");
            cbRules.addItem("Jeu de la vie");
            rules.clear();
        } else if (obj == btnQuit) {
            grid.setRunning(false);
            exit(0);
            dispose();
        } else if (obj == cbLoad) {
            killAll();
            PatternFactory pFact = new PatternFactory(cells);
            this.cells = pFact.createPattern(pFact.patterns[cbLoad.getSelectedIndex()], 5, 5);
        }
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        grid.setSpeed(slider.getValue());
        lblSpeed.setText(slider.getValue() + "ms");
    }

    //initialisation du Bouton Démarrer/Arrêter (Actions)
    public static void setBtnStart(String text) {
        switch (text) {
            case "Démarrer":
                btnStart.setText("Démarrer");
                break;
            case "Arrêter":
                btnStart.setText("Arrêter");
            default:
                btnStart.setText("Démarrer");
        }
    }

    //initialisation des itérations dans un champ texte
    public static void showIterationsInLabel(String text) {
        nbIterationsFaites.setText(text);
    }

    //vérifie si au moins une règle est choisie (déverrouillage du bouton démarrer)
	public void checkIfIssetOneRule()
	{
		if(!lblReglesChoisies.getText().equals(""))
		{
			btnStart.setEnabled(true);
		}
	}

	//transforme "Blanc" => int 0, "Noir" => int 1
	int getIntFromCbChar(JComboBox cb)
	{
		if(cb.getSelectedItem().equals("Blanc"))
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
}