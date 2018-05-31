import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Cell extends JPanel implements MouseListener{
	
	private ArrayList<Cell> neighboors = new ArrayList<Cell>();
	private boolean alive;
	private boolean nextAlive;

	public Cell() {
		setBorder(BorderFactory.createLineBorder(Color.lightGray));
		setBackground(Color.WHITE);
		addMouseListener(this);
	}
	
	///////////////////////////
	//Début Méthodes à coder //
	///////////////////////////
	
	public void addNeighboor(Cell c) {
		neighboors.add(c);
	}

	public int aliveNeighborsCount() {
		int count = 0;
		for (Cell c : neighboors) {
			if (c.isAlive())
				count++;
		}
		return count;
	}
	
	public void nextState() {
		int aliveNeighbors = aliveNeighborsCount();

		if (isAlive()) {
			if (aliveNeighbors < 2 || aliveNeighbors > 3)
				die();

		} else {
			if (aliveNeighbors == 3) {
				live();
			}
		}
	}

	public void die() {
		nextAlive = false;
	}

	public void live() {
		nextAlive = true;
	}

	public boolean isAlive() {
		return alive;
	}
	
	/////////////////////////
	//FIN Méthodes à coder //
	/////////////////////////

	public void display() {
		alive = nextAlive;
		if (nextAlive) {
			setBackground(Color.GREEN);
		} else {
			setBackground(Color.WHITE);
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(7,7);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

			live();
		display();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if ( SwingUtilities.isLeftMouseButton (e) ){

				live();

			display();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
