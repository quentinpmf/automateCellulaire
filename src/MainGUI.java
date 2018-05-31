import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainGUI extends JFrame implements ActionListener, ChangeListener{

	private GridBagLayout gbTrace = new GridBagLayout();
	private GridBagConstraints gbConstraints  = new GridBagConstraints();
	
	private Cell[][] cells;
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
		initSpeedControl();
		initCells(rows);
		initMenu();
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
	
	/**
	 * Builds the slider bar
	 */
	private void initSpeedControl() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		slider = new JSlider();
		slider.setMaximum(500);
		slider.setMinimum(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(this);
		slider.setValue(50);
		
		lblSpeed = new JLabel("Animation speed (ms) : " + slider.getValue());
		panel.add(lblSpeed);
		panel.add(Box.createHorizontalStrut(5));
		panel.add(slider);
		addComponent(getContentPane(), panel, gbTrace, gbConstraints, 1, 0, GridBagConstraints.HORIZONTAL, 5, 5, 5, 5);
	}

	/**
	 * Builds the bottom menu bar
	 */
	private void initMenu(){
		JPanel pan = new JPanel();
		pan.setLayout(new BoxLayout(pan, BoxLayout.X_AXIS));
		
		JLabel lblLoad = new JLabel("Ouvrir modèle : ");
		pan.add(lblLoad);
		pan.add(Box.createHorizontalStrut(5));

		String[] strPattern = new String[]{"Small exploder","spaceShip","Ten Cell Row","Gosper glider gun", "Glider"};
		cbLoad = new JComboBox(strPattern);
		cbLoad.addActionListener(this);
		pan.add(cbLoad);
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(this);
		btnReset = new JButton("Reset");
		btnReset.addActionListener(this);
		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(this);

		pan.add(Box.createHorizontalGlue());
		pan.add(btnReset);
		pan.add(Box.createHorizontalStrut(5));
		pan.add(btnStart);
		pan.add(Box.createHorizontalStrut(5));
		pan.add(btnQuit);
		addComponent(getContentPane(), pan, gbTrace, gbConstraints, 1, 0, GridBagConstraints.HORIZONTAL, 5,5, 5,5);
	}

	/**
	 * This method allow to add component in a GridBagLayout-container
	 * @param container le conteneur
	 * @param component le composant � ajouter
	 * @param gbTrace le GridBagLayout
	 * @param gbConstraints les contraintes du layout
	 * @param weightx le poids horizontal
	 * @param weighty le poids vertical
	 * @param fill le comportement en cas de redimensionnement de la fen�tre
	 * @param insetsTop la marge en haut
	 * @param insetsRight la marge � droite
	 * @param insetsBottom la marge en bas
	 * @param insetsLeft la marge � gauche
	 */
	public void addComponent( Container container, Component
			component,
			GridBagLayout gbTrace, GridBagConstraints gbConstraints,
			int weightx, int weighty, int fill,
			int insetsTop, int insetsRight, int insetsBottom, int
			insetsLeft) {
		gbConstraints.weightx = weightx;
		gbConstraints.weighty = weighty;
		gbConstraints.fill = fill;
		gbConstraints.gridx = 0;
		gbConstraints.gridy = GridBagConstraints.RELATIVE;
		gbConstraints.gridwidth = 1;
		gbConstraints.gridheight = 1;
		gbConstraints.insets = new Insets(insetsTop, insetsRight,
				insetsBottom, insetsLeft);
		gbTrace.setConstraints( component, gbConstraints );
		container.add( component );
	}

	/**
	 * Builds the cells display
	 */
	private void initCells(int cell_rows) {
		JPanel panGrid = new JPanel();
		panGrid.setLayout(new GridLayout(cell_rows,cell_rows));
		cells = new Cell[cell_rows][cell_rows];
		for (int i = 0; i < cell_rows; i++) {
			for (int j = 0; j < cell_rows; j++) {
				Cell c = new Cell();
				cells[i][j] = c;
				panGrid.add(c);
			}
		}
		addComponent(getContentPane(), panGrid, gbTrace, gbConstraints, 0, 0, GridBagConstraints.NONE, 0, 5, 0, 5);
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
		lblSpeed.setText("Animation speed (ms) : " + slider.getValue());
	}

}
