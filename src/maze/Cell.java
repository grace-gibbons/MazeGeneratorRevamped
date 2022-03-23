package maze;

import java.util.ArrayList;

public class Cell implements Comparable<Cell> {
    private final int x;
    private final int y;

    private boolean hasRightWall = true;
    private boolean hasBottomWall = true;

    //List of all cells adjacent to the cell
    private ArrayList<Cell> neighbours;
    //List of all cells that are not blocked by a wall
    private ArrayList<Cell> connected;

    public Cell(int x, int y) {
        neighbours = new ArrayList<>();
        connected = new ArrayList<>();
        this.x = x;
        this.y = y;
    }

    /**
     * Add a neighbouring cell to the list
     * @param cell
     */
    public void addNeighbour(Cell cell) {
        if(!neighbours.contains(cell)) {
            neighbours.add(cell);
        }
    }

    public void addConnected(Cell cell) {
        if(!connected.contains(cell)) {
            connected.add(cell);
            //do i need this???
            //connected.sort(Cell::compareTo);
        }
    }

    /**
     * Remove the wall between two cells
     * @param next The cell next to the current cell
     */
    public void removeWall(Cell next) {
        //If next cell is left of the current cell
        if(next.getX() + 1 == getX() && next.getY() == getY()) {
            next.setHasRightWall(false);
            //addConnected(next);
        }

        //If next cell is right of the current cell
        if(next.getX() - 1 == getX() && next.getY() == getY()) {
            setHasRightWall(false);
        }

        //If next cell is on top of the current cell
        if(next.getY() + 1 == getY() && next.getX() == getX()) {
            next.setHasBottomWall(false);
        }

        //If next cell is below of the current cell
        if(next.getY() - 1 == getY() && next.getX() == getX()) {
            setHasBottomWall(false);
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

    public ArrayList<Cell> getConnected() {
        return connected;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    @Override
    public String toString() {
        return x + " " + y;
    }

    @Override
    public int compareTo(Cell o) {
        int result = this.x - o.x;
        if(result == 0) {
            result = this.y - o.y;
        }
        return result;
    }
}
