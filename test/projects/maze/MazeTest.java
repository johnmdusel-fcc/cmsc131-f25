package projects.maze;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeTest {
    private Maze maze;

    @BeforeEach
    /**
     * X,S,X,X
     * X,O,O,E
     * X,O,X,X
     */
    void setup() {
        String sourceOfTruth = "data/test_maze.txt"; //
        maze = MazeReader.load(sourceOfTruth);
    }

    @Test
    void testLoadedMaze(){
        Cell[] allCells = maze.getAllCells();
        assertEquals(5, allCells.length);
        Coords[] cellCoords = {
            new Coords(0, 1),
            new Coords(1, 1), new Coords(1, 2), new Coords(1, 3), 
            new Coords(2, 1)
        };
        CellStatus[] cellStatuses = {
            CellStatus.S,
            CellStatus.O, CellStatus.O, CellStatus.E,
            CellStatus.O
        };
        for (int idx = 0; idx < allCells.length; idx++) {
            Cell cell = allCells[idx];
            assertTrue(cell.getCoords().equals(cellCoords[idx]));
            assertEquals(cell.isExplored(), false);
            assertEquals(cell.getStatus(), cellStatuses[idx]);
        }
    }
    
    @Test
    void testDiscoverNeighbors(){
        Coords[] expected = { // NSEW ordering like Maze::discoverNeighbors
            new Coords(0, 1), 
            new Coords(2, 1),
            new Coords(1, 2),
        };
        Coords[] actual = maze.discoverNeighbors(
            new Cell(
                new Coords(1, 1), 
                CellStatus.O
            )
        );
        assertEquals(expected.length, actual.length);
        for (int idx = 0; idx < expected.length; idx++){
            assertTrue(expected[idx].equals(actual[idx]));
        }
    }

    @Test
    /**
     * Maze::getAllCells is supposed to trim nulls from the end of its grid
     */
    void testGetAllCells(){
        Maze testMaze = new Maze(2);
        Cell testCell = new Cell(new Coords(0, 0), CellStatus.O);
        testMaze.insertCell(testCell);
        assertEquals(1, testMaze.getAllCells().length);
        assertEquals(testCell, testMaze.getAllCells()[0]);
    }

}
