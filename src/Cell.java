import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Cell extends JPanel implements MouseListener{
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
        System.out.println(getNeighbors());
        int count = 0;
		for (Cell c : getNeighbors()) {
			if (c.isAlive()) {
                count++;
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

	public void stateCondition(int aliveNeighbors, String operator, int itsNeighbors, int nextCellState){
        if(nextCellState == -1)System.out.println("Whut?");
        switch (operator) {
            case "=":
                if (aliveNeighbors == itsNeighbors) {
                    setNextState(nextCellState);
                    System.out.println("case = Requis "+itsNeighbors+" | AN "+aliveNeighbors +" "+getState()+" NextState->"+nextCellState);
                }
                break;
            case "<":

                if (aliveNeighbors < itsNeighbors) {
                    setNextState(nextCellState);
                    System.out.println("case <  Requis "+itsNeighbors+" | AN "+aliveNeighbors +" "+getState()+" NextState->"+nextCellState);
                }
                break;
            case ">":

                if (aliveNeighbors > itsNeighbors) {
                    setNextState(nextCellState);
                    System.out.println("case >  Requis "+itsNeighbors+" | AN "+aliveNeighbors+" "+getState()+" NextState->"+nextCellState);
                }
                break;
            default :
        }
    }

    public int getNextState() {
        return nextState;
    }

    public void setNextState(int nextState) {
        this.nextState = nextState;
    }

    public void nextState() {
        int aliveNeighbors = aliveNeighborsCount();
        for (Rule item: MainGUI.rules) {
            if (item.getInitialCellState() == getState()) {
                switch (item.getInitialCellState()) {
                    case 0:
                        stateCondition(aliveNeighbors, item.getOperator(), item.getAliveNeighbors(), item.getNextCellState());
                        break;
                    case 1:
                        stateCondition(aliveNeighbors, item.getOperator(), item.getAliveNeighbors(), item.getNextCellState());
                        break;

                }
            }
        }

	}

    public boolean isAlive() {
		boolean alive;
	    if(getState() != 0){
			alive = true;
		}else{
			alive = false;
		}
		return alive;
	}

    /**
     * Display next state of cell
     */
    public void display() {
        display(getNextState());
    }

    /**
     * Display next state of cell
     */
	public void display(int state) {
		switch (state) {
            case 0:
                setBackground(Color.white);
                setState(getNextState());
                break;
            case 1:
                setBackground(Color.black);
                setState(getNextState());
                break;
            default:
                System.out.println("State > "+state);
                setBackground(Color.pink);
                break;
        }
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(10,10);
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
		if ( SwingUtilities.isLeftMouseButton (e) ){
            setState(1);
			display(getState());
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
