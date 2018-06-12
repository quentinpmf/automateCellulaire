public class PatternFactory {

    private Cell[][] cells;

    public PatternFactory(Cell[][] cells) {
        this.cells = cells;
    }

    /**
     * empty pattern
     */
    private final String[] empty = new String[]{
            "."
    };

    /**
     * SmallExploder pattern
     */
    private final String[] smallExploder = new String[]{
            "....................................",
            "....................................",
            "....................................",
            "....................................",
            "..................O.................",
            ".................OOO................",
            ".................O.O................",
            "..................O................."
    };

    /**
     * SpaceShip pattern
     */
    private final String[] spaceShip = new String[]{
            "....................................",
            ".................OOOO...............",
            "................O...O...............",
            "....................O...............",
            "................O..O................",
    };

    /**
     * TenCellRow pattern
     */
    private final String[] tenCellRow = new String[]{
            "....................................",
            "....................................",
            "....................................",
            "....................................",
            "....................................",
            "....................................",
            "..............OOOOOOOOOO............",
    };

    /**
     * GosperGliderGun pattern
     */
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

    /**
     * Glider pattern
     */
    private final String[] glider = new String[]{
            ".................O..................",
            "..................O.................",
            "................OOO................."
    };

    /**
     * TheFloorIsLava pattern
     */
    private final String[] TheFloorIsLava = new String[]{
            "LAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELA",
            "EVALEVALEVALEVALEVALEVALEAAAAVALEVALEVALEVALEVALEV",
            "VELAVELEEEEEEEEAVELAVELAVELEEEEEEEEAVELAVELEEEEEEE",
            "LEVALEVALEEEEEEALEVALEVAAAAAAAVALEVAVALEVAVALEVAVA",
            "LEVALEEEEEEEEEEALEVALEVALEVALEVALEVALEVALEVALEVALE",
            "LAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELA",
            "EVALEVALEVALEVALEVALEVALEVALEVALEVALEVALEEVALEEVAL",
            "VELAVELAVELALLLLLELAVELAVELAVELAVELAVELAVELAVELAVE",
            "LEVALEVALEVLLLLLLLLALEVALEVALEVALEVALEVALELEVALELE",
            "LEVALEVALEVALLLLLEVALEVALEVALEVALEVALLLLLELLLLLELL",
            "EVVVVVVVVVVVVVALEVALEVALEVALEVALEVALEVVVVVVEVVVVVV",
            "VELAVVVVVVLAVELAVELLLLLLLLLLLLLLVELAVVVVVVLVVVVVVL",
            "LEVAVVVVVVVVVVVALEVALEVALEVALEVALEVALEVAVVLEVAVVLE",
            "LEVALEVALEVALEVALEVALEVALEVALEVALEVAALEVAALEVAALEV",
            "LAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELA",
            "EVALEVALEVALEVALEVALEVALEAAAAVALEVALEVALEVALEVALEV",
            "VELAVELEEEEEEEEAVELAVELAVELEEEEEEEEAVELAVELEEEEEEE",
            "LEVALEVALEEEEEEALEVALEVAAAAAAAVALEVAVALEVAVALEVAVA",
            "LEVALEEEEEEEEEEALEVALEVALEVALEVALEVALEVALEVALEVALE",
            "LAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELA",
            "EVALEVALEVALEVALEVALEVALEVALEVALEVALEVALEEVALEEVAL",
            "VELAVELAVELALLLLLELAVELAVELAVELAVELAVELAVELAVELAVE",
            "LEVALEVALEVLLLLLLLLALEVALEVALEVALEVALEVALELEVALELE",
            "LEVALEVALEVALLLLLEVALEVALEVALEVALEVALLLLLELLLLLELL",
            "EVVVVVVVVVVVVVALEVALEVALEVALEVALEVALEVVVVVVEVVVVVV",
            "VELAVVVVVVLAVELAVELLLLLLLLLLLLLLVELAVVVVVVLVVVVVVL",
            "LEVAVVVVVVVVVVVALEVALEVALEVALEVALEVALEVAVVLEVAVVLE",
            "LEVALEVALEVALEVALEVALEVALEVALEVALEVAALEVAALEVAALEV",
            "LAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELA",
            "EVALEVALEVALEVALEVALEVALEAAAAVALEVALEVALEVALEVALEV",
            "VELAVELEEEEEEEEAVELAVELAVELEEEEEEEEAVELAVELEEEEEEE",
            "LEVALEVALEEEEEEALEVALEVAAAAAAAVALEVAVALEVAVALEVAVA",
            "LEVALEEEEEEEEEEALEVALEVALEVALEVALEVALEVALEVALEVALE",
            "LAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELA",
            "EVALEVALEVALEVALEVALEVALEVALEVALEVALEVALEEVALEEVAL",
            "VELAVELAVELALLLLLELAVELAVELAVELAVELAVELAVELAVELAVE",
            "LEVALEVALEVLLLLLLLLALEVALEVALEVALEVALEVALELEVALELE",
            "LEVALEVALEVALLLLLEVALEVALEVALEVALEVALLLLLELLLLLELL",
            "EVVVVVVVVVVVVVALEVALEVALEVALEVALEVALEVVVVVVEVVVVVV",
            "VELAVVVVVVLAVELAVELLLLLLLLLLLLLLVELAVVVVVVLVVVVVVL",
            "LEVAVVVVVVVVVVVALEVALEVALEVALEVALEVALEVAVVLEVAVVLE",
            "LEVALEVALEVALEVALEVALEVALEVALEVALEVAALEVAALEVAALEV",
            "LAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELA",
            "EVALEVALEVALEVALEVALEVALEAAAAVALEVALEVALEVALEVALEV",
            "VELAVELEEEEEEEEAVELAVELAVELEEEEEEEEAVELAVELEEEEEEE",
            "LEVALEVALEEEEEEALEVALEVAAAAAAAVALEVAVALEVAVALEVAVA",
            "LEVALEEEEEEEEEEALEVALEVALEVALEVALEVALEVALEVALEVALE",
            "LAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELAVELA",
            "EVALEVALEVALEVALEVALEVALEVALEVALEVALEVALEEVALEEVAL",
            "VELAVELAVELALLLLLELAVELAVELAVELAVELAVELAVELAVELAVE",
    };

    /**
     * FeuDeForet pattern
     */
    private final String[] FeuDeForet = new String[]{
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFEFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
            "FFFFFFFFFFFFFFFFFFFFFF----------------------------",
            "FFFFFFFFFFFFFFFFFFFFFFFFF-------------------------",
    };

    /**
     * All the patterns
     */
    public final String[][] patterns = new String[][]{empty, smallExploder, spaceShip, tenCellRow, gosperGliderGun, glider, TheFloorIsLava, FeuDeForet};

    /**
     * Generates a cell-matrix from a pattern
     *
     * @param pattern the pattern to copy
     * @param offsetX the horizontal margin from left
     * @param offsetY the vertical margin from top
     * @return
     */
    public Cell[][] createPattern(String[] pattern, int offsetX, int offsetY) {
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[i].length(); j++) {
                if (i + offsetX >= cells.length || j + offsetY >= cells[i].length) {
                    throw new IllegalArgumentException("Pattern can't be loaded, too big. Increase grid size !");
                }
                if (pattern[i].charAt(j) == 'O') {
                    cells[i + offsetX][j + offsetY].setState(1);
                } else if (pattern[i].charAt(j) == 'L') {
                    cells[i + offsetX][j + offsetY].setState(2);
                } else if (pattern[i].charAt(j) == 'A') {
                    cells[i + offsetX][j + offsetY].setState(3);
                } else if (pattern[i].charAt(j) == 'V') {
                    cells[i + offsetX][j + offsetY].setState(4);
                } else if (pattern[i].charAt(j) == 'E') {
                    cells[i + offsetX][j + offsetY].setState(5);
                } else if (pattern[i].charAt(j) == 'F') {
                    cells[i + offsetX][j + offsetY].setState(6);
                } else if (pattern[i].charAt(j) == 'R') {
                    cells[i + offsetX][j + offsetY].setState(7);
                }
                else {
                    cells[i + offsetX][j + offsetY].setState(0);
                }
                cells[i + offsetX][j + offsetY].display(cells[i + offsetX][j + offsetY].getState());
            }
        }
        return cells;
    }

}