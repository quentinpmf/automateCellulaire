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

	public int aliveNeighborsCount(int state) {
		int count = 0;
		for (Cell c : getNeighbors()) {
			if(state != -1) {
				if(c.getState() == state) count++;
			}
			else {
				if(c.getState() > 0) count++;
			}
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
        if(MainGUI.rules.size() != 0){
            for (Rule item : MainGUI.rules) {
                if(item.isActivated()) {
					int aliveNbCount = aliveNeighborsCount(item.getRequiredAliveNeighborsColor());
                    if (item.getInitialCellState() == getState()) {
                        switch (item.getInitialCellState()) {
                            case 0:
                                stateCondition(item.getOperator(), item.getRequiredAliveNeighbors(), item.getNextCellState(), aliveNbCount);
                                break;
                            case 1:
                                stateCondition(item.getOperator(), item.getRequiredAliveNeighbors(), item.getNextCellState(), aliveNbCount);
                                break;
							case 2:
								stateCondition(item.getOperator(), item.getRequiredAliveNeighbors(), item.getNextCellState(), aliveNbCount);
								break;
							case 3:
								stateCondition(item.getOperator(), item.getRequiredAliveNeighbors(), item.getNextCellState(), aliveNbCount);
								break;
							case 4:
								stateCondition(item.getOperator(), item.getRequiredAliveNeighbors(), item.getNextCellState(), aliveNbCount);
								break;
							case 5:
								stateCondition(item.getOperator(), item.getRequiredAliveNeighbors(), item.getNextCellState(), aliveNbCount);
								break;
							case 6:
								stateCondition(item.getOperator(), item.getRequiredAliveNeighbors(), item.getNextCellState(), aliveNbCount);
								break;
							case 7:
								stateCondition(item.getOperator(), item.getRequiredAliveNeighbors(), item.getNextCellState(), aliveNbCount);
								break;
							default:
								setNextState(getState());
                        }
                    }
                }
            }
        }else{
            setNextState(getState());
        }
	}

	public void stateCondition(String operator, int requiredNeighborsToChange, int nextCellState,int aliveNbCount) {
		if (operator == "a exactement (=)" || operator == "=") {
			if (aliveNbCount == requiredNeighborsToChange) {
				setNextState(nextCellState);
			}
		} else if (operator == "a strictement moins de (<)" || operator == "<") {
			if (aliveNbCount < requiredNeighborsToChange) {
				setNextState(nextCellState);
			}
		} else if (operator == "a strictement plus de (>)" || operator == ">") {
			if (aliveNbCount > requiredNeighborsToChange) {
				setNextState(nextCellState);
			}
		} else {
			System.out.println("Operator error");
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
		if (getState() == 0) {
			setState(1);
		}else if (getState() == 1) {
			setState(2);
		}else if (getState() == 2) {
			setState(3);
		}else if (getState() == 3) {
			setState(4);
		}else if (getState() == 4) {
			setState(5);
		}else if (getState() == 5) {
			setState(6);
		}else if (getState() == 6) {
			setState(7);
		}else if (getState() == 7) {
			setState(0);
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