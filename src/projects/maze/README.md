# Maze project

# Phase 1. Maze modeling

You "inherited" this model of a grid from an engineer who has moved on. 
For phase 1, you need to finish the testing and documentation of the grid model. 
There is also a bug in the implementation that needs to be found and fixed.

Once the grid model is tested and documented, you will use it to implement a model of a maze. 

## Checklists

### 1.1 Debug, test, and document the starter code
- [ ] Get the files from the class repo into your fork.
    - [ ] from `src/projects/maze`: README.md, Coords.java, Cell.java, Grid.java.
    - [ ] `test/projects/maze`: CoordsTest.java, CellTest.java, GridTest.java.
- [ ] Fix the bug making the current tests fail.
- [ ] Add more unit tests to ensure complete coverage.
- [ ] Add javadocs to the important methods. Add comments in code if you like.

### 1.1 Extend the starter code to be useful for the maze project
Design notes are in the headers of Maze.java and MazeCell.java
- [ ] Implement maze class. **Test coverage is mandatory!**
- [ ] Add new features to cell class. **Test coverage is mandatory!**
- [ ] Run the main routine and confirm that the I/O routines work with your Maze 
implementation. Your output text file should be this:

```
X X X X X X X X 
S O O O O O O X 
X O X O X X O X 
X X X O O O X X 
X O O O X O X X 
X O X O X O O E 
```

# Phase 2. Maze solving

Add a feature that will find a path from `S` to `E` using depth-first traversal with backtracking. 

# Checklist

# 2.1 Depth-first traversal
- [ ] Add a `solve` method to your maze class.

```
public boolean solve() {
    Cell startCell = getStart();
    return explorePath(startCell);
}
```

- [ ] Add an `explorePath` method to your maze class.

```
private boolean explorePath(Cell currentCell) {
    // Student fills in method body   
}
```

- [ ] Fill in the body of `explorePath` to implement a depth-first traversal. Consider printing the coordinates of the cells being explored, so you can inspect the output for correctness. 

Bear these points in mind: 
- The traversal order is sensitive to your implementation of `Maze::discoverAndSetupNeighbors`. For example, if adding neighbors in the order of `{North, South, East, West}` can yield a different traversal order than `{North, East, South, West}`.
- The `Cell::explored` attribute gets mutated from `false` (default) to `true` when a cell is traversed.
- The boolean return value is for the maze logic step. At this point you can just let `explorePath` return `true`.


# 2.2 Maze solving logic

- [ ] Enhance the `explorePath` method to mutate the status of each cell being traversed. 

- [ ] Run the updated main routine against the sample mazes. Visually inspect the serialized solutions.