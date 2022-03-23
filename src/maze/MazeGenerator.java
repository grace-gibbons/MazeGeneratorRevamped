package maze;

import java.util.*;

public class MazeGenerator {

    private static int rows;
    private static int columns;

    private Cell[][] maze;

    //Queue of cells that came before the current cell
    private List<Cell> previousQueue;

    //Store each cell that has been visited during maze generation
    private List<Cell> visitedList;

    private Cell currentCell;

    public MazeGenerator(int rows, int columns) {
        MazeGenerator.rows = rows;
        MazeGenerator.columns = columns;

        maze = new Cell[columns][rows];
        previousQueue = new LinkedList<>();
        visitedList = new ArrayList<>();

        //Set up cells and coordinates
        for(int x = 0; x < columns; x++) {
            for(int y = 0; y < rows; y++) {
                maze[x][y] = new Cell(x, y);
            }
        }

        //Add neighbours to each cell
        for(int x = 0; x < columns; x++) {
            for(int y = 0; y < rows; y++) {
                //Add top neighbour
                if(y != 0) {
                    maze[x][y].addNeighbour(maze[x][y-1]);
                }
                //Add left neighbour
                if(x != 0) {
                    maze[x][y].addNeighbour(maze[x-1][y]);
                }
                //Add right neighbour
                if(x != columns - 1) {
                    maze[x][y].addNeighbour(maze[x+1][y]);
                }
                //Add bottom neighbour
                if(y != rows - 1) {
                    maze[x][y].addNeighbour(maze[x][y+1]);
                }
            }
        }

        currentCell = maze[0][0];
        visitedList.add(currentCell);
        previousQueue.add(currentCell);
    }

    public void generate() {
        //Get a random unvisited neighbour of the current cell
        Cell nextCell = getRandUnvisitedNeighbour(currentCell);

        if(nextCell != null) {
            removeWall(currentCell, nextCell);
            currentCell = nextCell;
            previousQueue.add(currentCell);
            visitedList.add(currentCell);
        } else {
            if(!previousQueue.isEmpty()) {
                currentCell = previousQueue.remove(0);
            }
        }
    }


    public Cell getRandUnvisitedNeighbour(Cell cell) {
        //Get all the cell's neighbours and put them in the unchecked list
        ArrayList<Cell> uncheckedNeighbours = cell.getNeighbours();
        while(uncheckedNeighbours.size() > 0) {
            Cell random = uncheckedNeighbours.remove((int)(Math.random() * uncheckedNeighbours.size()));
            if(!visitedList.contains(random)) {
                return random;
            }
        }
        return null;
    }

    public void removeWall(Cell current, Cell next) {
        //If next cell is left of the current cell
        if(next.getX() + 1 == current.getX() && next.getY() == current.getY()) {
            next.setHasRightWall(false);
        }

        //If next cell is right of the current cell
        if(next.getX() - 1 == current.getX() && next.getY() == current.getY()) {
            current.setHasRightWall(false);
        }

        //If next cell is on top of the current cell
        if(next.getY() + 1 == current.getY() && next.getX() == current.getX()) {
            next.setHasBottomWall(false);
        }

        //If next cell is below of the current cell
        if(next.getY() - 1 == current.getY() && next.getX() == current.getX()) {
            current.setHasBottomWall(false);
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Cell getCell(int x, int y) {
        return maze[x][y];
    }

    public int getTotalCells() {
        return rows * columns;
    }

    public List<Cell> getVisitedList() {
        return visitedList;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }
}
