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

    public Cell() {
		setBorder(BorderFactory.createLineBorder(Color.lightGray));
		setBackground(Color.WHITE);
		addMouseListener(this);
	}

	public void addNeighbor(Cell c) {
        this.neighbors.add(c);
	}

	public int aliveNeighborsCount() {
        int count = 0;
		for (Cell c : this.neighbors) {
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

	public void stateCondition(String operator, int itsNeighbors, int nextCellState){
        int aliveNeighbors = aliveNeighborsCount();
        switch (operator) {
            case "egal":
                if (aliveNeighbors == itsNeighbors) {
                    System.out.println("IN "+itsNeighbors+" | AN "+aliveNeighbors);
                    System.out.println("Je passe par ici");
                    setState(nextCellState);
                }
                break;
            case "<":
                if (aliveNeighbors < itsNeighbors) {
                    setState(nextCellState);
                }
                break;
            case ">":
                if (aliveNeighbors > itsNeighbors) {
                    setState(nextCellState);
                }
                break;
            default :
                System.out.println("I am "+operator);
        }
    }
	
	public void nextState() {

        for (Rule item: MainGUI.rules) {
			switch (item.getInitialCellState()) {
                case 0:
                    stateCondition(item.getOperator(), item.getAliveNeighbors(), item.getNextCellState());
                    //System.out.println("Init: "+item.getInitialCellState()+"/ OP: "+item.getOperator()+" / N: "+ item.getAliveNeighbors()+"/ Next: "+ item.getNextCellState()+"/ State: "+ getState());
                    break;
                case 1:
                    stateCondition(item.getOperator(), item.getAliveNeighbors(), item.getNextCellState());
                    //System.out.println("Init: "+item.getInitialCellState()+"/ OP: "+item.getOperator()+" / N: "+ item.getAliveNeighbors()+"/ Next: "+ item.getNextCellState()+"/ State: "+ getState());
                    break;
                case 2:
                    stateCondition(item.getOperator(), item.getAliveNeighbors(), item.getNextCellState());
                case 3:
                    stateCondition(item.getOperator(), item.getAliveNeighbors(), item.getNextCellState());
                    break;
                case 4:
                    stateCondition(item.getOperator(), item.getAliveNeighbors(), item.getNextCellState());
                    break;


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
		switch (state) {
            case 0:
                setBackground(Color.white);
                break;
            case 1:
                setBackground(Color.black);
                break;
            case 2:
                setBackground(Color.red);
                break;
            case 3:
                setBackground(Color.green);
                break;
            case 4:
                setBackground(Color.gray);
                break;
            default:
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
		display();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if ( SwingUtilities.isLeftMouseButton (e) ){
            setState(1);
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
