/** `Cell` class additions

Extra attributes

- `neighbors` (Coords[]) - coordinates of neighbors
    - needs accessor/mutator

- `explored` (boolean) - traversal flag (defaults to false)
    - needs accessor/mutator

- `status` (CellStatus enum) - cell's role/state
    - Enum values    
        - `S` maze entrance
        - `E` maze exit  
        - `O` open cell
        - `P` part of solution path
    - Needs accessor/mutator

A cell will be constructed with a coordinates and a status. Decide for yourself what are sensible default values (if any) for the other attributes.
 */

package projects.maze;

public class Cell {

    private final Coords coords;
    private Coords[] neighbors;
    private boolean explored;
    private CellStatus status;

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
