import java.util.Random;

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

    private static final String FloorIsLavaLetters  = "LAVE";
    private static final String FeuDeForetLetters  = "F-";

    /**
     * TheFloorIsLava pattern
     */
    private String[] TheFloorIsLava = new String[MainGUI.rows];
    int generateFloorIsLavaPattern() {
        for(int i = 0; i < MainGUI.rows; i++)
        {
            char[] patternLine = generatePatternLine(MainGUI.rows, MainGUI.rows, MainGUI.rows,FloorIsLavaLetters);
            TheFloorIsLava[i] = new String(patternLine);
        }
        return 0;
    }

    /**
     * FeuDeForet pattern
     */
    private String[] FeuDeForet = new String[MainGUI.rows];
    int generateFeuDeForetPattern() {
        for(int i = 0; i < MainGUI.rows; i++)
        {
            char[] patternLine = generatePatternLine(MainGUI.rows, MainGUI.rows, MainGUI.rows,FeuDeForetLetters);
            FeuDeForet[i] = new String(patternLine);
        }
        return 0;
    }

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

    //génère une ligne de 50 caractères pour le pattern
    public static char[] generatePatternLine(int longueurMin, int longueurMax, int nbDeLettres, String tableauDeLettres) {
        Random random = new Random();
        int longueur = random.nextInt(longueurMax - longueurMin + 1) + longueurMin;
        char[] patternLine = new char[longueur];
        int index = 0;
        for (int i = 0; i < nbDeLettres; i++) {
            index = getNextIndex(random, longueur, patternLine);
            patternLine[index] = tableauDeLettres.charAt(random.nextInt(tableauDeLettres.length()));
        }
        return patternLine;
    }

    //récupère de manière aléatoire la lettre suivante dans le tabeau.
    private static int getNextIndex(Random random, int longueur, char[] patternLine) {
        int index = random.nextInt(longueur);
        while(patternLine[index = random.nextInt(longueur)] != 0);
        return index;
    }

}