import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainGUI extends JFrame implements ActionListener, ChangeListener{

	private GridBagLayout gbTrace = new GridBagLayout();
	private JTextField nbIterationsMax = new JTextField();
	private static JLabel nbIterationsFaites = new JLabel(""+Grid.nbIterations);
	private static JLabel lblReglesChoisies = new JLabel("");
	private static JLabel labelNbIterationsMax = new JLabel();
	private Cell[][] cells;
	private static ArrayList<String> reglesChoisies = new ArrayList<>();
	private Grid grid;
	private static JComboBox cbLoad, cbRules;
	private static JButton btnReset, btnStart, btnAppliquer, btnQuit, btnCreate, btnPopupCreate;
	private JSlider slider;
	private JLabel lblSpeed, lblRuleName;
	private JTextField RuleName = new JTextField();
	/**
	 * Constructor
	 */
	public MainGUI() {

		JPanel dialogBox = new JPanel();
		int rows = Integer.parseInt(JOptionPane.showInputDialog(dialogBox,"Nombre de lignes ? [minimum 50]"));
		if(rows >= 50){
			setTitle("Cellular Automaton");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			setResizable(false);
			setLayout(gbTrace);

			JPanel panel_speedcontrol = initSpeedControl();
			JPanel panel_openmodel = initOpenModel();
			JPanel panel_cells = initCells(rows);
			JPanel panel_config = initConfigurationBlock();
			JPanel panel_boutons = initButtons();

			setLayout(new BorderLayout());
			//création du panel droite
			JPanel panel_gauche = new JPanel();
			panel_gauche.setLayout(new BorderLayout());
			panel_gauche.add(panel_openmodel,BorderLayout.NORTH);
			panel_gauche.add(panel_config,BorderLayout.CENTER);
			panel_gauche.add(panel_boutons,BorderLayout.SOUTH);

			//création du panel gauche
			JPanel panel_droite = new JPanel();
			panel_droite.setLayout(new BorderLayout());
			panel_droite.add(panel_speedcontrol,BorderLayout.NORTH);
			panel_droite.add(panel_cells,BorderLayout.SOUTH);

			//ajout des deux panel gauche et droite dans le jframe
			add(panel_gauche,BorderLayout.WEST);
			add(panel_droite,BorderLayout.EAST);

			pack(); //sizes the frame so that all its contents are at or above their preferred sizes


			new Rule(Color.black,"-",2,Color.white);
			new Rule(Color.black,"+",3,Color.white);
			new Rule(Color.white,"=",3,Color.black);


			this.grid = new Grid(cells, rows);
			grid.setSpeed(50);
			slider.setValue(50);
			setVisible(true);
			new Thread(grid).start();
		}
	}
	
	/**
	 * Builds the slider bar
	 */
	private JPanel initSpeedControl() {
		JPanel pan = new JPanel();
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

	/**
	 * Builds the bottom menu bar
	 */
	private JPanel initOpenModel(){
		JPanel pan = new JPanel();
		pan.setBorder(BorderFactory.createTitledBorder("Ouvrir modèle"));
		pan.setLayout(new BoxLayout(pan, BoxLayout.X_AXIS));

		pan.add(Box.createHorizontalStrut(5));

		String[] strPattern = new String[]{"-- Choisir --","Small exploder","spaceShip","Ten Cell Row","Gosper glider gun", "Glider","Test"};
		cbLoad = new JComboBox(strPattern);
		cbLoad.addActionListener(this);
		pan.add(cbLoad);
		return pan;
	}

	/**
	 * Builds the bottom menu bar
	 */
	private JPanel initButtons(){
		JPanel pan = new JPanel();
		pan.setBorder(BorderFactory.createTitledBorder("Actions"));
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));

		btnStart = new JButton("Start");
		btnStart.addActionListener(this);
		btnReset = new JButton("Reset");
		btnReset.addActionListener(this);
		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(this);

		pan.add(Box.createHorizontalGlue());
		pan.add(btnStart);
		pan.add(Box.createHorizontalStrut(5));
		pan.add(btnReset);
		pan.add(Box.createHorizontalStrut(5));
		pan.add(btnQuit);

		return pan;
	}

	/**
	 * Builds the bottom menu bar
	 */
	private JPanel initConfigurationBlock() {
		JPanel pan = new JPanel();
		pan.setBorder(BorderFactory.createTitledBorder("Configuration"));
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));

		JPanel pan_iterations = new JPanel(new BorderLayout());

		pan_iterations.setBorder(BorderFactory.createTitledBorder("Itérations"));
		pan_iterations.setLayout(new BoxLayout(pan_iterations, BoxLayout.Y_AXIS));

		pan_iterations.add(Box.createHorizontalGlue());
		nbIterationsFaites.setText("Nombre d'itérations : 0");
		pan_iterations.add(nbIterationsFaites);
		pan_iterations.add(Box.createHorizontalStrut(5));

		labelNbIterationsMax.setText("Nombre d'itérations max :");
		pan_iterations.add(labelNbIterationsMax,BorderLayout.WEST);
		pan_iterations.add(Box.createHorizontalStrut(5));

		nbIterationsMax.setText("20");
		nbIterationsMax.setPreferredSize(new Dimension(150,20));
		nbIterationsMax.setMaximumSize(new Dimension(150,20));
		pan_iterations.add(nbIterationsMax,BorderLayout.CENTER);
		pan_iterations.add(Box.createHorizontalStrut(5));

		JPanel pan_rules = new JPanel(new BorderLayout());
		pan_rules.setBorder(BorderFactory.createTitledBorder("Règles"));
		pan_rules.setLayout(new BoxLayout(pan_rules, BoxLayout.Y_AXIS));
		pan_rules.setPreferredSize(new Dimension(150,20));
		pan_rules.setMaximumSize(new Dimension(150,20));

		btnCreate = new JButton("Créer");
		btnCreate.addActionListener(this);

		JFrame fenetre_creation_regles = new JFrame();
		//titre pour notre fenetre
		fenetre_creation_regles.setTitle("Création d'une règle");
		//taille : 400 pixels de large et 100 pixels de haut
		fenetre_creation_regles.setSize(300,200);
		//positionner au centre
		fenetre_creation_regles.setLocationRelativeTo(null);

		JPanel pan_fenetre_rules = new JPanel(new BorderLayout());
		pan_fenetre_rules.setBorder(BorderFactory.createTitledBorder("Créer une règle"));
		pan_fenetre_rules.setLayout(new BoxLayout(pan_fenetre_rules, BoxLayout.Y_AXIS));

		lblRuleName = new JLabel("Nom de la règle");
		pan_fenetre_rules.add(lblRuleName);
		RuleName.setPreferredSize(new Dimension(250,20));
		RuleName.setMaximumSize(new Dimension(250,20));
		pan_fenetre_rules.add(RuleName);

		btnPopupCreate = new JButton("Créer");
		btnPopupCreate.addActionListener(this);
		pan_fenetre_rules.add(btnPopupCreate);
		fenetre_creation_regles.add(pan_fenetre_rules);

		String[] strPattern = new String[]{"-- Choisir --"};
		cbRules = new JComboBox(strPattern);
		cbRules.addActionListener(this);

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
				//ajout de la nouvelle regle dans le ComboBox
				cbRules.addItem(RuleName.getText());
				//selection de la nouvelle regle dans le ComboBox
				cbRules.setSelectedIndex(cbRules.getItemCount()-1);
				//cacher la popup
				fenetre_creation_regles.setVisible(false);
				//réinitialisation des paramètres
				RuleName.setText("");
			}
		});

		btnAppliquer = new JButton("Appliquer");
		btnAppliquer.addActionListener(this);

		//au clic sur appliquer, on ajoute la regle selectionner
		btnAppliquer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//get selected item in combobox
				Object ruleName = cbRules.getSelectedItem();
				//if différent de la regle --Choisir--
				if(ruleName != "-- Choisir --")
				{
					if(reglesChoisies.size() != 0)
					{
						boolean boolDejaPresent = false;
						for(int i = 0; i < reglesChoisies.size() ; i++)
						{
							if(ruleName == reglesChoisies.get(i))
							{
								boolDejaPresent = true;
								break;
							}
						}
						if(!boolDejaPresent)
						{
							reglesChoisies.add(cbRules.getSelectedItem().toString());
							setChosenRule(ruleName.toString());
						}
					}
					else
					{
						reglesChoisies.add(cbRules.getSelectedItem().toString());
						setChosenRule(ruleName.toString());
					}
				}

			}
		});
		//au clic sur le bouton créer : ouvrir une nouvelle popup de création de règles

		pan_rules.add(Box.createHorizontalGlue());

		//regles choisies :
		pan_rules.add(Box.createHorizontalStrut(5));
		pan_rules.add(lblReglesChoisies);

		pan_rules.add(Box.createHorizontalStrut(5));
		JPanel wrapper1 = new JPanel();
		wrapper1.add( btnCreate );
		pan_rules.add( wrapper1 );

		//select
		pan_rules.add(Box.createHorizontalStrut(5));
		JPanel wrapper2 = new JPanel();
		wrapper2.add( cbRules );
		pan_rules.add( wrapper2 );

		pan_rules.add(Box.createHorizontalStrut(5));
		JPanel wrapper3 = new JPanel();
		wrapper3.add( btnAppliquer );
		pan_rules.add( wrapper3 );

		setLayout(new BorderLayout());
		pan.setLayout(new BorderLayout());
		pan.add(pan_iterations,BorderLayout.NORTH);
		pan.add(pan_rules,BorderLayout.CENTER);

		return pan;
	}

	/**
	 * Builds the cells display
	 */
	private JPanel initCells(int cell_rows) {
		JPanel pan = new JPanel();
		pan.setBorder(BorderFactory.createTitledBorder("Grille"));
		pan.setLayout(new GridLayout(cell_rows,cell_rows));
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

	public static void main(String[] args) {
		// putting the GUI in the Event dispatch thread
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainGUI();
			}
		});
	}

	/**
	 * Change state of every cells to dead
	 */
	private void killAll() {
		for (int i = 0; i < Grid.rows; i++) {
			for (int j = 0; j < Grid.rows; j++) {
				cells[i][j].die();
				cells[i][j].display();
			}
		}
		//reset nbIterations + nbMaxIterations
		nbIterationsFaites.setText("Nombre d'itérations : 0");
        lblReglesChoisies.setText("");
		reglesChoisies.clear();
		nbIterationsMax.setText("0");
		Grid.nbIterations = 0;
		Grid.nbIterationsMax = 0;
		Grid.IterationMaxAtteint = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnStart) {
			if (btnStart.getText().equals("Start")) {
				btnStart.setText("Stop");
				grid.resume(nbIterationsMax.getText());
			} else {
				btnStart.setText("Start");
				grid.pause();
			}
		} else if (obj == btnReset) {
			killAll();
		} else if (obj == btnQuit) {
			grid.setRunning(false);
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
		lblSpeed.setText(slider.getValue()+ "ms");
	}

	public static void setBtnStart(String text)
	{
		switch (text) {
			case "Start":
				btnStart.setText("Start");
				break;
			case "Stop":
				btnStart.setText("Stop");
			default:
				btnStart.setText("Start");
		}
	}

    public static void showIterationsInLabel(String text)
    {
        nbIterationsFaites.setText("Nombre d'itérations : "+text);
    }

    public static void setChosenRule(String text)
    {
    	String textBefore = lblReglesChoisies.getText();
    	String intro = "Règle(s) choisie(s) :";

    	//si contient 1 règle ou +
    	if(textBefore.toLowerCase().contains(intro.toLowerCase()))
		{
			//on remplace le point par une virgule
			textBefore = textBefore.replace('.',',');
			lblReglesChoisies.setText(textBefore+" "+text+".");
		}
		else
		{
			//si contient aucune règle
			lblReglesChoisies.setText(intro+" "+text+".");
		}
    }

}
