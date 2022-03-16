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

    public void drawCell(int x, int y) {
        parent.noFill();
        parent.strokeWeight(2);
        parent.stroke(0, 255, 0);
        parent.rect(screenBuffer + (x * cellSize), screenBuffer + (y * cellSize), cellSize, cellSize);
    }

}
