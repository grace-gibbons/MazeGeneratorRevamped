package maze;

import processing.core.PApplet;

public class GraphicsHandler {

    private PApplet parent;

    private static int cellSize;

    //Distance from edge of screen to where maze starts drawing
    private static int screenBuffer = 100;

    public GraphicsHandler(PApplet parent, MazeGenerator mazeGenerator) {
        this.parent = parent;
        cellSize = mazeGenerator.getTotalCells() / 4;
    }

    public void drawBorder(int rows, int columns) {
        parent.rect(screenBuffer, screenBuffer, cellSize * columns, cellSize * rows);
    }

    public void drawCell(Cell cell) {
        parent.noFill();
        parent.strokeWeight(1);
        parent.stroke(0, 255, 0);
        //top left coordinates of each cell
        int x1 = screenBuffer + (cell.getX() * cellSize);
        int y1 = screenBuffer + (cell.getY() * cellSize);
        //Draw cell walls
        if(cell.getHasRightWall()) {
            parent.line(x1 + cellSize, y1, x1 + cellSize, y1 + cellSize);
        }
        if(cell.getHasBottomWall()) {
            parent.line(x1, y1 + cellSize, x1 + cellSize, y1 + cellSize);
        }
        //System.out.println("Draw cell");
    }

    public void drawCurrentCell(Cell cell) {
        parent.fill(100);
        parent.noStroke();
        parent.rect(screenBuffer + (cell.getX() * cellSize) + 1, screenBuffer + (cell.getY() * cellSize) + 1, cellSize, cellSize);
    }

    public void drawPathCell(Cell cell) {
        parent.fill(100, 0, 20);
        parent.noStroke();
        //The +1 is no the cell walls are not drawn over
        parent.rect(screenBuffer + (cell.getX() * cellSize) + 1, screenBuffer + (cell.getY() * cellSize) + 1, cellSize, cellSize);
    }

    public void drawMaze(MazeGenerator mazeGenerator) {
        //Draw the maze cells
        for(int x = 0; x < mazeGenerator.getColumns(); x++) {
            for(int y = 0; y < mazeGenerator.getRows(); y++) {
                if(mazeGenerator.getCurrentCell() != null && mazeGenerator.getCurrentCell().getX() == x && mazeGenerator.getCurrentCell().getY() == y) {
                    drawCurrentCell(mazeGenerator.getCell(x, y));
                }
                if(mazeGenerator.getPath().contains(mazeGenerator.getCell(x, y))) {
                    drawPathCell(mazeGenerator.getCell(x, y));
                }
                drawCell(mazeGenerator.getCell(x, y));
            }
        }
    }

}
