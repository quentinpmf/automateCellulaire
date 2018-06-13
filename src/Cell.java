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
	private int currentState;
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

	/**
	 * Count neighbors of the cell according to their color
	 * @param state
	 * @return count
	 */
	public int aliveNeighborsCount(int state) {
		int count = 0;
		for (Cell c : getNeighbors()) {
			if(state != -1) {
				if(c.getCurrentState() == state) count++;
			}
			else {
				if(c.getCurrentState() > 0) count++;
			}
		}
		return count;
	}

	public int getCurrentState() {
		return currentState;
	}

	public void setCurrentState(int etat) {
		this.currentState = etat;
	}

	public int getNextState() {
		return nextState;
	}

	public void setNextState(int nextState) {
		this.nextState = nextState;
	}

	/**
	 * Affect next state to current state to prepare next iteration
	 */
	public void applyNextState(){
		this.currentState = nextState;
	}

	/**
	 * Set next state of a cell according to the rules
	 */
	public void nextState() {
        if(MainGUI.rules.size() != 0){
            for (Rule item : MainGUI.rules) {
                if(item.isActivated()) {
					int aliveNbCount = aliveNeighborsCount(item.getRequiredAliveNeighborsColor());
                    if (item.getInitialCellState() == getCurrentState()) {
						if (item.getOperator() == "a exactement (=)" || item.getOperator() == "=") {
							if (aliveNbCount == item.getRequiredAliveNeighbors()) {
								setNextState(item.getNextCellState());
							}
						} else if (item.getOperator() == "a strictement moins de (<)" || item.getOperator() == "<") {
							if (aliveNbCount < item.getRequiredAliveNeighbors()) {
								setNextState(item.getNextCellState());
							}
						} else if (item.getOperator() == "a strictement plus de (>)" || item.getOperator() == ">") {
							if (aliveNbCount > item.getRequiredAliveNeighbors()) {
								setNextState(item.getNextCellState());
							}
						} else {
							System.out.println("Operator error");
						}
                    }
                }
            }
        }else{
            setNextState(getCurrentState());
        }
	}

	/**
	 * Display next current state of cell
	 */
	public void display(int state) {
		switch (state) {
			case 0:
				setBackground(Color.white);
				break;
			case 1:
				setBackground(Color.black);
				break;

			//Floor is lava
			case 2:
				setBackground(Color.yellow);
				break;
			case 3:
				setBackground(Color.decode("#FFAA00")); //light orange
				break;
			case 4:
				setBackground(Color.decode("#FF5500")); // dark orange
				break;
			case 5:
				setBackground(Color.red);
				break;

			//Feu de foret
			case 6:
				setBackground(Color.green);
				break;
			case 7:
				setBackground(Color.gray);
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
		if (getCurrentState() == 0) {
			setCurrentState(1);
		}else if (getCurrentState() == 1) {
			setCurrentState(2);
		}else if (getCurrentState() == 2) {
			setCurrentState(3);
		}else if (getCurrentState() == 3) {
			setCurrentState(4);
		}else if (getCurrentState() == 4) {
			setCurrentState(5);
		}else if (getCurrentState() == 5) {
			setCurrentState(6);
		}else if (getCurrentState() == 6) {
			setCurrentState(7);
		}else if (getCurrentState() == 7) {
			setCurrentState(0);
		}
		display(getCurrentState());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			this.setCurrentState(1);
			display(getCurrentState());
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