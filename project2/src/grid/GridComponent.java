package grid;

public abstract class GridComponent {
    protected final int x;
    protected final int y;

    public GridComponent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public abstract char render();
}
