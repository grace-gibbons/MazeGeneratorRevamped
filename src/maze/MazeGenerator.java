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

    private List<Cell> path;

    private Cell currentCell;

    private boolean noLoop = true;

    public MazeGenerator(int rows, int columns) {
        MazeGenerator.rows = rows;
        MazeGenerator.columns = columns;

        maze = new Cell[columns][rows];
        previousQueue = new LinkedList<>();
        visitedList = new ArrayList<>();
        path = new ArrayList<>();

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

        //Choose a random starting cell
        int randomRow = (int)(Math.random() * rows);
        int randomCol = (int)(Math.random() * columns);
        currentCell = maze[randomCol][randomRow];
        visitedList.add(currentCell);
        previousQueue.add(currentCell);

        //System.out.println("1: " + maze[0][0].getNeighbours());
    }

    public void generate() {
        //Generate the maze while there are unvisited cells
        if(getVisitedList().size() != getTotalCells()) {
            //Get a random unvisited neighbour of the current cell
            Cell nextCell = getRandUnvisitedNeighbour(currentCell);

            if(nextCell != null) {
                //Remove the wall between the cells and connect them
                currentCell.removeWall(nextCell);
                currentCell.addConnected(nextCell);
                nextCell.addConnected(currentCell);

                currentCell = nextCell;
                previousQueue.add(currentCell);
                visitedList.add(currentCell);
            } else {
                if(!previousQueue.isEmpty()) {
                    currentCell = previousQueue.remove(0);
                }
            }
        } else {
            //Maze has finished generating, get shortest path
            currentCell = null;
            if(noLoop) {
                path = findPath(maze[0][0], maze[5][5]);
                for(Cell cell : path) {
                    System.out.println(cell);
                }
                noLoop = false;
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

    public List<Cell> findPath(Cell start, Cell end) {
        //Cells to visit
        List<Cell> queue = new LinkedList<>();
        //Predecessor map to find shortest path
        Map<Cell, Cell> predecessors = new HashMap<>();

        queue.add(start);
        predecessors.put(start, start);

        while(!queue.isEmpty()) {
            Cell current = queue.remove(0);
            //If the end cell is found, exit the loop
            if(current.equals(end)) {
                break;
            }
            for(Cell connected : current.getConnected()) {
                //Add neighbour to map if not visited
                if(!predecessors.containsKey(connected)) {
                    predecessors.put(connected, current);
                    queue.add(connected);
                }
            }
        }

        //Create shortest path
        List<Cell> shortestPath = new LinkedList<>();
        if(predecessors.containsKey(end)) {
            Cell current = end;
            while(!current.equals(start)) {
                shortestPath.add(0, current);
                current = predecessors.get(current);
            }
            shortestPath.add(0, start);
        }
        return shortestPath;
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

    public List<Cell> getPath() {
        return path;
    }
}
