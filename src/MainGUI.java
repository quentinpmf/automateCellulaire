import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainGUI extends JFrame implements ActionListener, ChangeListener{

	/**The number of rows for the cells array*/
	public static final int CELL_ROWS = 80;
	
	/**The number of cols for the cells array*/
	public static final int CELL_COLS = 80;
	
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
		setTitle("Cellular Automaton");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(gbTrace);

		JPanel panel_speedcontrol = initSpeedControl();
		JPanel panel_openmodel = initOpenModel();
		JPanel panel_cells = initCells();
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

		pack();
		this.grid = new Grid(cells);
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

		String[] strPattern = new String[]{"Small exploder","Gosper glider gun", "Gourmet", "Glider", "Ten Cell Row"};
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
	 * Builds the cells display
	 */
	private JPanel initCells() {
		JPanel pan = new JPanel();
		pan.setBorder(BorderFactory.createTitledBorder("Grille"));
		pan.setLayout(new GridLayout(CELL_ROWS,CELL_ROWS));
		cells = new Cell[CELL_ROWS][CELL_ROWS];
		for (int i = 0; i < CELL_ROWS; i++) {
			for (int j = 0; j < CELL_ROWS; j++) {
				Cell c = new Cell();
				cells[i][j] = c;
				pan.add(c);
			}
		}
		return pan;
	}

	public static void main(String[] args) {
		System.setProperty( "com.apple.eawt.CocoaComponent.CompatibilityMode", "false" );
		try {
			// OS-based look and feel
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Unable to set lookAndFeel");
			System.exit(-1);
		}
		
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
		for (int i = 0; i < CELL_ROWS; i++) {
			for (int j = 0; j < CELL_ROWS; j++) {
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
		lblSpeed.setText(slider.getValue()+ "ms");
	}

}
