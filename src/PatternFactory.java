public class PatternFactory {

    private Cell[][] cells;

    public PatternFactory(Cell[][] cells) {
        this.cells = cells;
    }

    /** SmallExploder pattern */
    private final String[] smallExploder = new String[]{
            "....................................",
            "..................O.................",
            ".................OOO................",
            ".................O.O................",
            "..................O................."
    };

    /** SpaceShip pattern */
    private final String[] spaceShip = new String[]{
            "....................................",
            ".................OOOO...............",
            "................O...O...............",
            "....................O...............",
            "................O..O................",
    };

    /** TenCellRow pattern */
    private final String[] tenCellRow = new String[]{
            "..............OOOOOOOOOO............",
    };

    /** GosperGliderGun pattern */
    private final String[] gosperGliderGun = new String[]{
            "........................O...........",
            "......................O.O...........",
            "............OO......OO............OO",
            "...........O...O....OO............OO",
            "OO........O.....O...OO..............",
            "OO........O...O.OO....O.O...........",
            "..........O.....O.......O...........",
            "...........O...O....................",
            "............OO......................"
    };

    /** Glider pattern */
    private final String[] glider = new String[]{
            ".................O..................",
            "..................O.................",
            "................OOO................."
    };

    /** All the patterns */
    public final String[][] patterns = new String[][]{smallExploder, spaceShip, tenCellRow, gosperGliderGun, glider};

    /**
     * Generates a cell-matrix from a pattern
     * @param pattern the pattern to copy
     * @param offsetX the horizontal margin from left
     * @param offsetY the vertical margin from top
     * @return
     */
    public Cell[][] createPattern(String[] pattern, int offsetX, int offsetY) {
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[i].length(); j++) {
                if (i+offsetX >= cells.length || j+offsetY >= cells[i].length) {
                    throw new IllegalArgumentException("Pattern can't be loaded, too big. Increase grid size !");
                }
                if (pattern[i].charAt(j) == 'O') {
                    cells[i+offsetX][j+offsetY].setState(1);
                }  else {
                    cells[i+offsetX][j+offsetY].setState(0);
                }
                cells[i+offsetX][j+offsetY].display(cells[i+offsetX][j+offsetY].getState());
            }
        }
        return cells;
    }

}
