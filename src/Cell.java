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
	private int alive;
	private int nextAlive;
	private int currentColor;

	public Cell() {
		setBorder(BorderFactory.createLineBorder(Color.lightGray));
		setBackground(Color.WHITE);
		addMouseListener(this);
	}
	
	public void addNeighboor(Cell c) {
		neighbors.add(c);
	}

	public int aliveNeighborsCount() {
		int count = 0;
		for (Cell c : neighbors) {
			if (c.isAlive())
				count++;
		}
		return count;
	}
	
	public void nextState() {
		int aliveNeighbors = aliveNeighborsCount();

        for (Rule item: MainGUI.rules) {
			if (item.initialState) {
                switch (item.operator) {
                    case "<":
                        if (aliveNeighbors < item.aliveNeighbors) nextAlive = item.nextCellState;
                        break;
                    case ">":
                        if (aliveNeighbors > item.aliveNeighbors) nextAlive = item.nextCellState;
                        break;
                    case "=":
                        if (aliveNeighbors == item.aliveNeighbors) nextAlive = item.nextCellState;
                }
			}
        }

		/*
		if (isAlive()) {
			if (aliveNeighbors < 2 || aliveNeighbors > 3)
				die();

		} else {
			if (aliveNeighbors == 3) {
				live();
			}
		}
		*/
	}

	public boolean isAlive() {
	    return alive;
	}

    /**
     * Display next state of cell
     */
	public void display() {
		if(nextAlive >= 1){
			alive = true;
		}
		switch (nextAlive) {
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
                setBackground(Color.grey);
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
            nextAlive = 0;
		} else {
            nextAlive = 1;
		}
		display();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if ( SwingUtilities.isLeftMouseButton (e) ){
            nextAlive = 1;
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
