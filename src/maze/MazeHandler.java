package maze;

import processing.core.PApplet;

public class MazeHandler extends PApplet {

    private static MazeGenerator mazeGenerator;

    private static GraphicsHandler graphicsHandler;

    public static void main(String[] args) {
        PApplet.main("maze.MazeHandler");
    }

    public void settings() {
        setSize(640, 480);
    }

    public void setup() {
        mazeGenerator = new MazeGenerator(10, 10);;
        graphicsHandler = new GraphicsHandler(this, mazeGenerator);

        background(0, 0, 0);
        frameRate(12);
    }

    public void draw() {
        background(0, 0, 0);

        mazeGenerator.generate();

        //Draw the maze
        graphicsHandler.drawMaze(mazeGenerator);
        //Draw maze border
        graphicsHandler.drawBorder(mazeGenerator.getRows(), mazeGenerator.getColumns());

    }
}
