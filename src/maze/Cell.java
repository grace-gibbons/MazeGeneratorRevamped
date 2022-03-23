package maze;

import java.util.ArrayList;

public class Cell {
    private int x;
    private int y;

    private boolean hasRightWall = true;
    private boolean hasBottomWall = true;

    private ArrayList<Cell> neighbours;

    public Cell(int x, int y) {
        neighbours = new ArrayList<>();
        this.x = x;
        this.y = y;
    }

    public void addNeighbour(Cell cell) {
        if(!neighbours.contains(cell)) {
            neighbours.add(cell);
        }
    }

    public boolean getHasRightWall() {
        return hasRightWall;
    }

    public void setHasRightWall(boolean hasRightWall) {
        this.hasRightWall = hasRightWall;
    }

    public boolean getHasBottomWall() {
        return hasBottomWall;
    }

    public void setHasBottomWall(boolean hasBottomWall) {
        this.hasBottomWall = hasBottomWall;
    }

    public ArrayList<Cell> getNeighbours() {
        return neighbours;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Cell) {
            Cell c = (Cell) o;
            if (x == c.x && y == c.y) {
                return true;
            }
        }
        return false;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
