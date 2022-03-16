package maze;

public class MazeGenerator {

    private static int rows;
    private static int columns;

    public Cell[][] maze;

    public MazeGenerator(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        maze = new Cell[rows][columns];

        //Set up cells and coordinates
        for(int x = 0; x < columns; x++) {
            for(int y = 0; y < rows; y++) {
                maze[x][y] = new Cell(x, y);
            }
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



}
