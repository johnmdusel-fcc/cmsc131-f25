# Maze project

# Phase 1. Maze modeling

You "inherited" this model of a grid from an engineer who has moved on. 
For phase 1, you need to finish the testing and documentation of the grid model. 
There is also a bug in the implementation that needs to be found and fixed.

Once the grid model is tested and documented, you will use it to implement a model of a maze. 

## Phase 1 checklist 

- [X] Get the files from the class repo into your fork.
    - [ ] from `src/projects/maze`: README.md, Coords.java, Cell.java, Grid.java.
    - [ ] `test/projects/maze`: CoordsTest.java, CellTest.java, GridTest.java.
- [ ] Fix the bug making the current tests fail.
- [ ] Add more unit tests to ensure complete coverage.
- [ ] Add javadocs to the important methods. Add comments in code if you like.

Work-in-progress (Dusel will update)
- [ ] Implement maze class.
    - [ ] Add a new subclass `MazeCell` of `Cell` as described below
    - [ ] 



## Design doc for grid prototype (left behind by former engineer)

These descriptions show the engineer's intent when they designed and implemented the classes in their prototype grid model.

### 1. 1 Coords.java

**Purpose:** Immutable coordinates for a cells's position in the grid

**Attributes:**
- `row` (int) - vertical (row) coordinate
- `col` (int) - horizontal (column) coordinate

**Methods:**
- `getRow()`, `getCol()` - accessors
- `equals(Coords other)` - checks if two Coords refer to same position

### 1.2 Cell.java

**Purpose:** Represents a single grid cell. 

#### 1.2.1 Attributes
- `coords` (Coords) - this cell's grid coordinates

#### 1.2.3 Methods
- `getCoords()` - returns this cell's SpaceID

### 1.3 Grid.java

**Purpose:** Container for grid cells.

#### 1.3.1 Attributes
- `cells` (Cell[]) - fixed-size array that may contain nulls
- `cellCount` (int) - tracks how many cells have been added

#### 1.3.2 Construction
- `Grid(int maxCells)` - pre-allocates array to maximum size

#### 1.3.3 Cell management

- `insertCell(Cell cell)` - adds cell to next available position, increments counter
- `getCell(Coords vh)` - linear search through array to find space by coordinate ***(TODO returns null if not found)***
- `getCellCount()` - returns number of cells currently in grid
- `getAllCells()` - returns the entire grid array


## `MazeCell` class specification

Subclass of `Cell` 

### Extra attributes
- `neighbors` (Coords[]) - array of up to 4 neighboring grid coordinates (North, South, East, West). 
- `explored` (boolean) - traversal flag (defaults to false)
- `status` (CellStatus enum) - cell's role/state

### `MazeCellStatus` enum
- `Start` maze entrance
- `End` maze exit  
- `Open` open cell
- `InPath` part of solution path

### Extra public methods
- `setNeighbors(Cell[])` and `getNeighbors()` manages neighbor references
- `setExplored(boolean)` / `getExplored()` manages traversal flag
- `setStatus(MazeCellStatus)` / `getStatus()` manages status enum
