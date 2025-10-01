package behavioral.observer;

public class Investor {
    private String name;

    public Investor(String name) {
        this.name = name;
    }

    public void update(double price) {
        System.out.println(name + " notified: New Stock Price = " + price);
    }
}
