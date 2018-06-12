/**
 * Manage rules
 */
public class Rule {

    private String name;
    private int initialCellState;
    private int nextCellState;
    private int neighborsPositionsAndColors; // où 1er est en haut à gauche, 4eme en haut au milieu et 8eme en bas a droite
    private int requiredAliveNeighbors;
    private int requiredAliveNeighborsColor;
    private String operator;
    private boolean activated;

    public Rule(String name, int initialCellState, String operator, int requiredAliveNeighbors, int requiredAliveNeighborsColor, int nextCellState, boolean activated) {
        this.name = name;
        this.initialCellState = initialCellState;
        this.nextCellState = nextCellState;
        this.operator = operator;
        this.requiredAliveNeighbors = requiredAliveNeighbors;
        this.requiredAliveNeighborsColor = requiredAliveNeighborsColor;
        this.activated = activated;
    }

    public int getRequiredAliveNeighborsColor() {
        return requiredAliveNeighborsColor;
    }

    public void setRequiredAliveNeighborsColor(int requiredAliveNeighborsColor) {
        this.requiredAliveNeighborsColor = requiredAliveNeighborsColor;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public int getNeighborsPositionsAndColors() {
        return neighborsPositionsAndColors;
    }

    public void setNeighborsPositionsAndColors(int neighborsPositionsAndColors) {
        this.neighborsPositionsAndColors = neighborsPositionsAndColors;
    }

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        return "Nom : [" + this.name +
                "] Used : [" + this.activated +
                "] Init : [" + this.initialCellState +
                "] Final : [" + this.nextCellState +
                "] OP : [" + this.operator +
                "] Ran : [" + this.requiredAliveNeighbors + "]";
    }
}