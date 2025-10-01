package structural.decorator;

import java.util.Scanner;

public class DecoratorDemo {
    public static void runDemo() {
        Scanner sc = new Scanner(System.in);
        Coffee coffee = new SimpleCoffee();

        System.out.println("\n--- Decorator Pattern Demo ---");
        System.out.println("Base coffee: " + coffee.getDescription() + " $" + coffee.getCost());

        System.out.print("Enter decorators separated by comma (Milk,Sugar,Milk,...), or leave empty for none: ");
        String input = sc.nextLine().trim();

        if (!input.isEmpty()) {
            String[] decorators = input.split(",");
            for (String dec : decorators) {
                switch (dec.trim().toLowerCase()) {
                    case "milk" -> {
                        coffee = new MilkDecorator(coffee);
                        System.out.println("Added Milk.");
                    }
                    case "sugar" -> {
                        coffee = new SugarDecorator(coffee);
                        System.out.println("Added Sugar.");
                    }
                    default -> System.out.println("Unknown decorator: " + dec + ", skipping.");
                }
                System.out.println("Current coffee: " + coffee.getDescription() + " Rs. " + coffee.getCost());
            }
        }

        System.out.println("Final coffee: " + coffee.getDescription() + " Rs. " + coffee.getCost());
    }
}
