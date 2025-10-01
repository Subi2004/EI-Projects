package creational.factory;

import java.util.Scanner;

public class FactoryDemo {
    public static void runDemo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter shape (circle/square): ");
        String shapeType = sc.nextLine();
        Shape shape = ShapeFactory.getShape(shapeType);
        if (shape != null) shape.draw();
        else System.out.println("Unknown shape.");
    }
}
