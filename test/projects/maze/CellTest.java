package projects.maze;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CellTest {
    @Test
    void constructorDataValidation() {
        Exception e = assertThrows(
            IllegalArgumentException.class,
            () -> {new Cell(null, CellStatus.S);}
        );
        assertEquals(
            e.getMessage(),
            "Parameter c cannot be null"
        );
        e = assertThrows(
            IllegalArgumentException.class,
            () -> {new Cell(new Coords(0, 0), null);}
        );
        assertEquals(
            e.getMessage(),
            "Parameter s cannot be null"
        );
    }
}
