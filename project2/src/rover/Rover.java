package rover;

import grid.CompositeGrid;
import util.Logger;

public class Rover {
    private final CompositeGrid grid;
    private final Position position;

    public Rover(Position position, CompositeGrid grid) {
        this.position = position;
        this.grid = grid;
    }

    public Position getPosition() { return position; }

    public void move() {
        int nx = position.getX() + position.getDirection().dx();
        int ny = position.getY() + position.getDirection().dy();
        if (grid.inBounds(nx, ny) && !grid.hasObstacle(nx, ny)) {
            position.moveTo(nx, ny);
            Logger.info("Rover moved to " + position);
        } else Logger.warn("Blocked! Cannot move.");
        grid.printGrid(position);
    }

    public void turnLeft() {
        position.turnLeft();
        Logger.info("Rover turned left. Now facing " + position.getDirection());
    }

    public void turnRight() {
        position.turnRight();
        Logger.info("Rover turned right. Now facing " + position.getDirection());
    }
}
