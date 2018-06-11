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
    private int xavier;
    private int yvan;

    public int getXavier() {
        return xavier;
    }

    public void setXavier(int xavier) {
        this.xavier = xavier;
    }

    public int getYvan() {
        return yvan;
    }

    public void setYvan(int yvan) {
        this.yvan = yvan;
    }


    public Cell(int x, int y) {
        setBorder(BorderFactory.createLineBorder(Color.lightGray));
        setBackground(Color.WHITE);
        addMouseListener(this);
        setXavier(x);
        setYvan(y);
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
            System.out.println("I am cell ["+c.getXavier()+","+c.getYvan()+"] my state is "+c.getState());
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
        System.out.println("Have ["+neighbors.size()+"] neighbors and ["+aliveNbCount+"] are alive");
        for (Rule item : MainGUI.rules) {
            if (item.getInitialCellState() == getState()) {
                switch (item.getInitialCellState()) {
                    case 0:
                        System.out.println("Is dead");
                        stateCondition(item.getOperator(), item.getRequiredAliveNeighbors(), item.getNextCellState(),aliveNbCount);
                        break;
                    case 1:
                        System.out.println("Is alive");
                        stateCondition(item.getOperator(), item.getRequiredAliveNeighbors(), item.getNextCellState(),aliveNbCount);
                        break;
                }
            }
        }
    }

    public void stateCondition(String operator, int requiredNeighborsToChange, int nextCellState,int aliveNbCount) {
        if (operator == "=") {
            System.out.println("my sign is "+operator);
            if (aliveNbCount == requiredNeighborsToChange) {
                setNextState(nextCellState);
                System.out.println("And has exactly "+aliveNbCount+" just like the rule wants "+requiredNeighborsToChange+" so its next state will be "+nextCellState+":"+getNextState());
            }else{
                System.out.println("Damn I have "+aliveNbCount+" I should have "+requiredNeighborsToChange+" so its shit");
            }
        } else if (operator == "<") {
            System.out.println("my sign is "+operator);
            if (aliveNbCount < requiredNeighborsToChange) {
                setNextState(nextCellState);
                System.out.println("And has less than "+aliveNbCount+" just like the rule wants "+requiredNeighborsToChange+" so its next state will be "+nextCellState+":"+getNextState());
            }else{
                System.out.println("Damn I have "+aliveNbCount+" I should have "+requiredNeighborsToChange+" so its shit");
            }
        } else if (operator == ">") {
            System.out.println("my sign is "+operator);
            if (aliveNbCount > requiredNeighborsToChange) {
                setNextState(nextCellState);
                System.out.println("And has more than "+aliveNbCount+" just like the rule wants "+requiredNeighborsToChange+" so its next state will be "+nextCellState+":"+getNextState());
            }else{
                System.out.println("Damn I have "+aliveNbCount+" I should have "+requiredNeighborsToChange+" so its shit");
            }
        }
    }

    public boolean isAlive() {
        if (getState() > 0){
            System.out.println("Currently alive");
            return true;
        }else {
            System.out.println("Currently dead");
            return false;
        }
    }

    /**
     * Display next state of cell
     */
    public void display(int state) {
        System.out.println("State in display is : "+state);
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
            System.out.println("I am cell ["+getXavier()+","+getYvan()+"] my state is "+getState());
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
