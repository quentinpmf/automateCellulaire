import java.awt.*;

/**
 * Manage rules
 */
public class Rule {

    private int initialCellState;
    private int nextCellState;
    private int aliveNeighbors;
    private String operator;

    public Rule(int initialCellState, int nextCellState, String operator, int aliveNeighbors) {
        this.initialCellState = initialCellState;
        this.nextCellState = nextCellState;
        this.aliveNeighbors = aliveNeighbors;
        this.operator = operator;
    }




    public int getInitialCellState() {
        return initialCellState;
    }

    public void setInitialCellState(int initialCellState) {
        this.initialCellState = initialCellState;
    }

    public int getAliveNeighbors() {
        return aliveNeighbors;
    }

    public void setAliveNeighbors(int aliveNeighbors) {
        this.aliveNeighbors = aliveNeighbors;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getNextCellState() {
        return nextCellState;
    }

    public void setNextCellState(int nextCellState) {
        this.nextCellState = nextCellState;
    }
}
