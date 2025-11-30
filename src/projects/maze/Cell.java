package projects.maze;

public class Cell {

    private final Coords coords;
    private Coords[] neighbors;
    private boolean explored;
    private CellStatus status;

    /**
     * Construct a new cell from the given coordinates and cell status. 
     * Defaults to unexplored state.
     * @param c Coordinates of this cell
     * @param s Status of this cell
     */
    public Cell(Coords c, CellStatus s) {
        if (c == null) {
            throw new IllegalArgumentException("Parameter c cannot be null");
        }
        if (s == null) {
            throw new IllegalArgumentException("Parameter s cannot be null");
        }
        coords = c;
        explored = false;
        status = s;
    }

    public Coords getCoords() {
        return coords;
    }

    public boolean isExplored() {
        return explored;
    }
    
    public Coords[] getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Coords[] neighbors) {
        this.neighbors = neighbors;
    }
    
    public CellStatus getStatus() {
        return status;
    }

}
