package maze;

import processing.core.PApplet;

public class GraphicsHandler {

    PApplet parent;

    private static int cellSize;

    //Distance from edge of screen to where maze starts drawing
    private static int screenBuffer = 100;

    public GraphicsHandler(PApplet parent, MazeGenerator mazeGenerator) {
        this.parent = parent;
        cellSize = mazeGenerator.getTotalCells() / 4;
    }

    /*
    public void drawCell(int x, int y) {
        parent.noFill();
        parent.strokeWeight(2);
        parent.stroke(0, 255, 0);
        parent.rect(screenBuffer + (x * cellSize), screenBuffer + (y * cellSize), cellSize, cellSize);
    }*/

    public void drawBorder(int rows, int columns) {
        parent.rect(screenBuffer, screenBuffer, cellSize * columns, cellSize * rows);
    }

    /*
    public void drawCell(Cell cell) {
        parent.noFill();
        parent.strokeWeight(2);
        parent.stroke(0, 255, 0);
        //top left coordinates of each cell
        int x1 = screenBuffer + (cell.getCoords().getX() * cellSize);
        int y1 = screenBuffer + (cell.getCoords().getY() * cellSize);
        //Draw cell walls
        if(cell.getHasRightWall()) {
            parent.line(x1 + cellSize, y1, x1 + cellSize, y1 + cellSize);
        }
        if(cell.getHasBottomWall()) {
            parent.line(x1, y1 + cellSize, x1 + cellSize, y1 + cellSize);
        }
    }*/

    public void drawCell(Cell cell) {
        parent.noFill();
        parent.strokeWeight(2);
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
    }

    public void drawMaze(MazeGenerator mazeGenerator) {
        //Draw the maze cells
        for(int x = 0; x < mazeGenerator.getColumns(); x++) {
            for(int y = 0; y < mazeGenerator.getRows(); y++) {
                if(mazeGenerator.getCurrentCell().getX() == x && mazeGenerator.getCurrentCell().getY() == y) {
                    parent.fill(100);
                    parent.noStroke();
                    parent.rect(screenBuffer + (x * cellSize), screenBuffer + (y * cellSize), cellSize, cellSize);
                }
                drawCell(mazeGenerator.getCell(x, y));
            }
        }
    }

}
