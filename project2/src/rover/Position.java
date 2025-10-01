package rover;

public class Position {
    private int x;
    private int y;
    private Direction direction;

    public Position(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Direction getDirection() { return direction; }

    public boolean moveTo(int nx, int ny) {
        x = nx;
        y = ny;
        return true;
    }

    public void turnLeft() { direction = direction.turnLeft(); }
    public void turnRight() { direction = direction.turnRight(); }

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + direction + ")";
    }
}
