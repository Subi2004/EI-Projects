package creational.factory;

public class ShapeFactory {
    public static Shape getShape(String type) {
        if ("circle".equalsIgnoreCase(type)) return new Circle();
        if ("square".equalsIgnoreCase(type)) return new Square();
        return null;
    }
}
