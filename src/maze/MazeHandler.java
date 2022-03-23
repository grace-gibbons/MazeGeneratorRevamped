package maze;

import processing.core.PApplet;

public class MazeHandler extends PApplet {

    private static MazeGenerator mazeGenerator = new MazeGenerator(10, 10);;

    private GraphicsHandler graphicsHandler = new GraphicsHandler(this, mazeGenerator);

    public static void main(String[] args) {
        PApplet.main("maze.MazeHandler");
    }

    public void settings() {
        setSize(640, 480);
    }

    public void setup() {
        background(0, 0, 0);
        frameRate(12);
    }

    public void draw() {
        background(0, 0, 0);

        if(mazeGenerator.getVisitedList().size() != mazeGenerator.getTotalCells()) {
            mazeGenerator.generate();
        }


        //Draw the maze cells
        /*
        for(int x = 0; x < mazeGenerator.getColumns(); x++) {
            for(int y = 0; y < mazeGenerator.getRows(); y++) {
                graphicsHandler.drawCell(mazeGenerator.getCell(x, y));
            }
        }*/
        graphicsHandler.drawMaze(mazeGenerator);
        //Draw maze border
        graphicsHandler.drawBorder(mazeGenerator.getRows(), mazeGenerator.getColumns());

    }
}
