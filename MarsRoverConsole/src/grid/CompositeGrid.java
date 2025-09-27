package grid;

import rover.Position;
import util.Logger;

import java.util.ArrayList;
import java.util.List;

public class CompositeGrid {
    private final int width;
    private final int height;
    private final List<GridComponent> components = new ArrayList<>();

    public CompositeGrid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public List<GridComponent> getComponents() { return components; }

    public void addComponent(GridComponent comp) {
        components.add(comp);
    }

    public boolean hasObstacle(int x, int y) {
        return components.stream().anyMatch(c -> c.getX() == x && c.getY() == y);
    }

    public boolean removeObstacleAt(int x, int y) {
        return components.removeIf(c -> c.getX() == x && c.getY() == y);
    }

    public boolean inBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public void printGrid(Position roverPos) {
        char[][] gridMap = new char[height][width];
        // Fill with empty
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) gridMap[i][j] = '.';
        }
        // Place obstacles
        for (GridComponent c : components) {
            gridMap[c.getY()][c.getX()] = c.render();
        }
        // Place rover
        if (roverPos != null) {
            gridMap[roverPos.getY()][roverPos.getX()] = 'R';
        }
        // Print
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) System.out.print(gridMap[i][j] + " ");
            System.out.println();
        }
    }
}
