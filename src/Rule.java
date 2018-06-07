/**
 * Manage rules
 */
public class Rule {

    private int initialCellState;
    private int nextCellState;
    private int neighborsPositionsAndColors; // où 1er est en haut à gauche, 4eme en haut au milieu et 8eme en bas a droite
    private int requiredAliveNeighbors;
    private String operator;

    public Rule(int initialCellState, String operator, int requiredAliveNeighbors, int nextCellState) {
        this.initialCellState = initialCellState;
        this.nextCellState = nextCellState;
        this.operator = operator;
        this.requiredAliveNeighbors = requiredAliveNeighbors;
    }

    public int getNeighborsPositionsAndColors() {
        return neighborsPositionsAndColors;
    }

    public void setNeighborsPositionsAndColors(int neighborsPositionsAndColors) {
        this.neighborsPositionsAndColors = neighborsPositionsAndColors;
    }

    public int getInitialCellState() {
        return initialCellState;
    }

    public void setInitialCellState(int initialCellState) {
        this.initialCellState = initialCellState;
    }

    public int getRequiredAliveNeighbors() {
        return requiredAliveNeighbors;
    }

    public void setRequiredAliveNeighbors(int requiredAliveNeighbors) {
        this.requiredAliveNeighbors = requiredAliveNeighbors;
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
