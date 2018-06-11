import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Cell extends JPanel implements MouseListener {
	private ArrayList<Cell> neighbors = new ArrayList<Cell>();
	private int state;
	private int nextState = 0;

	public Cell() {
		setBorder(BorderFactory.createLineBorder(Color.lightGray));
		setBackground(Color.WHITE);
		addMouseListener(this);
	}

	public void addNeighbor(Cell c) {
		getNeighbors().add(c);
	}

	public ArrayList<Cell> getNeighbors() {
		return neighbors;
	}

	public int aliveNeighborsCount() {
		int count = 0;
		for (Cell c : getNeighbors()) {
			if(c.getState() >= 1) count++;
		}
		return count;
	}

	public int getState() {
		return state;
	}

	public void setState(int etat) {
		this.state = etat;
	}

	public int getNextState() {
		return nextState;
	}

	public void setNextState(int nextState) {
		this.nextState = nextState;
	}

	public void applyNextState(){
		this.state = nextState;
	}

	public void nextState() {
		int aliveNbCount = aliveNeighborsCount();
		for (Rule item : MainGUI.rules) {
			if (item.getInitialCellState() == getState()) {
				switch (item.getInitialCellState()) {
					case 0:
						stateCondition(item.getOperator(), item.getRequiredAliveNeighbors(), item.getNextCellState(),aliveNbCount);
						break;
					case 1:
						stateCondition(item.getOperator(), item.getRequiredAliveNeighbors(), item.getNextCellState(),aliveNbCount);
						break;
				}
			}
		}
	}

	public void stateCondition(String operator, int requiredNeighborsToChange, int nextCellState,int aliveNbCount) {
		if (operator == "=") {
			if (aliveNbCount == requiredNeighborsToChange) {
				setNextState(nextCellState);
			}
		} else if (operator == "<") {
			if (aliveNbCount < requiredNeighborsToChange) {
				setNextState(nextCellState);
			}
		} else if (operator == ">") {
			if (aliveNbCount > requiredNeighborsToChange) {
				setNextState(nextCellState);
			}
		}
	}

	public boolean isAlive() {
		if (getState() > 0){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Display next state of cell
	 */
	public void display(int state) {
		switch (state) {
			case 0:
				setBackground(Color.white);
				break;
			case 1:
				setBackground(Color.black);
				break;
			default:
				setBackground(Color.pink);
				break;
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(10, 10);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (isAlive()) {
			setState(0);
		} else {
			setState(1);
		}
		display(getState());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			this.setState(1);
			display(getState());
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}