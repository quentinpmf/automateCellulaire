import java.awt.*;

/**
 * Manage rules
 */
public class Rule {

    private Color initialCellState;
    private int aliveNeighbors;
    private String operator;
    private Color nextCellState;

    public Color getInitialCellState() {
        return initialCellState;
    }

    public void setInitialCellState(Color initialCellState) {
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

    public Color getNextCellState() {
        return nextCellState;
    }

    public void setNextCellState(Color nextCellState) {
        this.nextCellState = nextCellState;
    }

    public Rule(Color initialCellState, String sign, int aliveNeighbors, Color nextCellState){
        this.initialCellState = initialCellState;
        this.operator = sign;
        this.aliveNeighbors = aliveNeighbors;
        this.nextCellState = nextCellState;
    }
}
