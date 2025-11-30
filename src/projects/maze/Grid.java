package projects.maze;

public class Grid {

    private final Cell[] cells;
    private int cellCount;

    /**
     * Construct a new grid with the indicated maxumum number of cells.
     * @param maxCells Maximum number of cells in this grid.
     */
    public Grid(int maxCells) {
        cells = new Cell[maxCells];
        cellCount = 0;
    }

    /**
     * Attempting to add a cell beyond this grid's capacity 
     * will have no effect, and this function will return false.
     * @param cell Cell to be inserted in this grid.
     * @return true if and only if insertion is successful.
     */
    public boolean insertCell(Cell cell) {
        if (cell == null) {
            throw new IllegalArgumentException("Parameter cell cannot be null");
        }
        if (cellCount < cells.length) {
            cells[cellCount++] = cell;
            return true;
        }
        return false;
    }

    /**
     * @param vh Coordinates of desired cell.
     * @return Cell object with provided coordinates, 
     * or {@code null} if no cell with those coordinates exists.
     */
    public Cell getCell(Coords vh) {
        if (vh == null) {
            throw new IllegalArgumentException("Parameter vh cannot be null");
        }
        for (int idx = 0; idx < cellCount; idx++) {
            if ( cells[idx].getCoords().equals(vh) ) {
               return cells[idx];
            }
        }
        return null;
    }

    public int getCellCount() {
        return cellCount;
    }

    /**
     * @return Array of all cells in this grid, without {@code null} elements.
     */
    public Cell[] getAllCells() {
        Cell[] allCells = new Cell[cellCount];
        for (int idx = 0; idx < cellCount; idx++) {
            allCells[idx] = cells[idx];
        }
        return allCells;
    }

}
