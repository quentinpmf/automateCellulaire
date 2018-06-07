import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainGUI extends JFrame implements ActionListener, ChangeListener{

	private GridBagLayout gbTrace = new GridBagLayout();
	private GridBagConstraints gbConstraints  = new GridBagConstraints();
	
	private Cell[][] cells;
    public static ArrayList<Rule> rules = new ArrayList<Rule>();
	private Grid grid;
	private JComboBox cbLoad;
	private JButton btnReset, btnStart, btnQuit;
	private JSlider slider;
	private JLabel lblSpeed;

	/**
	 * Constructor
	 */
	public MainGUI() {

		JPanel dialogBox = new JPanel();
		int rows = Integer.parseInt(JOptionPane.showInputDialog(dialogBox,"Number of rows ?"));

		setTitle("Cellular Automaton");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(gbTrace);

		JPanel panel_speedcontrol = initSpeedControl();
		JPanel panel_openmodel = initOpenModel();
		JPanel panel_cells = initCells(rows);
		JPanel panel_boutons = initButtons();

		setLayout(new BorderLayout());
		//création du panel gauche
		JPanel panel_gauche = new JPanel();
		panel_gauche.setLayout(new BorderLayout());
		panel_gauche.add(panel_speedcontrol,BorderLayout.NORTH);
		panel_gauche.add(panel_cells,BorderLayout.SOUTH);

		//création du panel droite
		JPanel panel_droite = new JPanel();
		panel_droite.setLayout(new BorderLayout());
		panel_droite.add(panel_openmodel,BorderLayout.NORTH);
		panel_droite.add(panel_boutons,BorderLayout.SOUTH);

		//ajout des deux panel gauche et droite dans le jframe
		add(panel_gauche,BorderLayout.WEST);
		add(panel_droite,BorderLayout.EAST);

        //sizes the frame so that all its contents are at or above their preferred sizes
		pack();

        //JeuDeLaVie rules
		//Si une cellule a exactement deux voisines vivantes, elle reste dans son état actuel à l’étape suivante.
		rules.add(new Rule(0,"=",2,0));
        rules.add(new Rule(1,"=",2,1));
        //Si une cellule a exactement trois voisines vivantes, elle est vivante à l’étape suivante.
        rules.add(new Rule(0,"=",3,1));
        rules.add(new Rule(1,"=",3,1));
        //Si une cellule a strictement moins de deux ou strictement plus de trois voisines vivantes, elle est morte à l’étape suivante.
        rules.add(new Rule(1,">",3,0));
        rules.add(new Rule(1,"<",2,0));

		this.grid = new Grid(cells, rows);
		grid.setSpeed(50);
		slider.setValue(50);
		setVisible(true);
		new Thread(grid).start();

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

		String[] strPattern = new String[]{"Small exploder","spaceShip","Ten Cell Row","Gosper glider gun", "Glider"};
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
	 * Construit les cellules
	 */
	private JPanel initCells(int cell_rows) {
		JPanel pan = new JPanel();
		pan.setBorder(BorderFactory.createTitledBorder("Grille"));
		pan.setLayout(new GridLayout(cell_rows,cell_rows));
		cells = new Cell[cell_rows][cell_rows];
		for (int i = 0; i < cell_rows; i++) {
			for (int j = 0; j < cell_rows; j++) {
				Cell c = new Cell(i,j);
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
				cells[i][j].setState(0);
				cells[i][j].display(cells[i][j].getState());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnStart) {
			if (btnStart.getText().equals("Start")) {
				btnStart.setText("Stop");
				grid.resume();
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

}
