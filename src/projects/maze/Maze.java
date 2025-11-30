package projects.maze;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Maze {

    private final Grid grid;

    public Maze(int maxCells) {
        grid = new Grid(maxCells);
    }

    public Cell[] getAllCells() {
        Cell[] mazeCells = new Cell[grid.getCellCount()];
        Cell[] cells = grid.getAllCells();
        for (int idx = 0; idx < grid.getCellCount(); idx++) {
            mazeCells[idx] = cells[idx];
        }
        return mazeCells;
    }

    private Cell getFirstCellWithStatus(CellStatus status) {
        if (status == null) {
            throw new IllegalArgumentException(
                "Parameter status cannot be null"
            );
        }
        Cell[] cells = grid.getAllCells();
        for (int idx = 0; idx < cells.length; idx++) {
            if (cells[idx].getStatus() == status) {
                return cells[idx];
            }
        }
        return null;
    }

    private Cell getEnd() {
        return getFirstCellWithStatus(CellStatus.E);
    }

    private Cell getStart() {
        return getFirstCellWithStatus(CellStatus.S);
    }

    /**
     * Alias for this maze's underlying grid::insertCell.
     * @param cell Cell to be inserted.
     * @return true if and only insertion is successful.
     */
    public boolean insertCell(Cell cell) {
        if (cell == null) {
            throw new IllegalArgumentException(
                "Parameter cell cannot be null"
            );
        }
        return grid.insertCell(cell);
    }

    /**
     * This method is public for testing purposes only. 
     * See javadoc for {@code Maze::discoverAndSetupNeighbors}
     * @param cell Not validated, due to usage in discoverAndSetupNeighbors.
     * @return Array containing coordinates of neighbor cells in this maze.
     */
    public Coords[] discoverNeighbors(Cell cell) {
        Coords coords = cell.getCoords();
        Coords[] potentialNeighbors = {
            new Coords(coords.getRow() - 1, coords.getCol()), // north
            new Coords(coords.getRow() + 1, coords.getCol()), // south
            new Coords(coords.getRow(), coords.getCol() + 1), // east
            new Coords(coords.getRow(), coords.getCol() - 1) // west
        };
        Coords[] neighbors = new Coords[potentialNeighbors.length];
        int neighborsCount = 0;
        for (Coords offset : potentialNeighbors) {
            if (grid.getCell(offset) != null) {
                neighbors[neighborsCount++] = offset;
            }
        }
        Coords[] toReturn = new Coords[neighborsCount];
        for (int idx = 0; idx < neighborsCount; idx++) { // trim nulls
            toReturn[idx] = neighbors[idx];
        }
        return toReturn;
    }

    /**
     * Populates {@code neighbors} attribute of each cell in this maze's 
     * underlying grid. Neighbor cells are scanned in NSEW order.
     */
    public void discoverAndSetupNeighbors() {
        Cell[] cells = grid.getAllCells();
        for (int idxCell = 0; idxCell < cells.length; idxCell++) {
            Cell cell = cells[idxCell];
            Coords[] neighbors = discoverNeighbors(cell);
            cell.setNeighbors(neighbors);
        }
    }

    public void serialize(String filename) {
        if (filename == null) {
            throw new IllegalArgumentException(
                "Parameter filename cannot be null"
            );
        }
        Cell[] cells = getAllCells();

        FileWriter writer;
        try {
            writer = new FileWriter(new File(filename));
            // discover dimension of maze's underlying grid
            int maxRow = 0, maxCol = 0;
            int idxCell;
            for (idxCell = 0; idxCell < cells.length; idxCell++) {
                int row = cells[idxCell].getCoords().getRow();
                int col = cells[idxCell].getCoords().getCol();
                if (row > maxRow) { maxRow = row; }
                if (col > maxCol) { maxCol = col; }
            }
    
            // write cell statuses, using 'X' for absent (inaccessible) cells
            idxCell = 0;
            for (int row = 0; row <= maxRow; row++) {
                for (int col = 0; col <= maxCol; col++) {
                    Cell maybeCell = grid.getCell(
                        new Coords(row, col)
                    );
                    if (maybeCell != null) {
                        writer.write(maybeCell.getStatus().name());
                        idxCell++;
                    } else {
                        writer.write('X');
                    }
                    writer.write(' ');
                }
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}
