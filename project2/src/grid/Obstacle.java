package grid;

public class Obstacle extends GridComponent {
    public Obstacle(int x, int y) {
        super(x, y);
    }

    @Override
    public char render() {
        return 'X';
    }
}
