package main;

import java.io.File;
import java.util.Scanner;
import java.util.logging.*;

import behavioral.observer.ObserverDemo;
import behavioral.strategy.StrategyDemo;
import creational.factory.FactoryDemo;
import creational.singleton.SingletonDemo;
import structural.adapter.AdapterDemo;
import structural.decorator.DecoratorDemo;

public class AppMain {
    private static final Logger logger = Logger.getLogger(AppMain.class.getName());

    public static void main(String[] args) {
        try {
            // Reset JUL and reconfigure handlers explicitly
            LogManager.getLogManager().reset(); // clears default handlers; must re-add [web:16][web:17]

            // Ensure logs directory exists (FileHandler won't create parent dirs)
            File logDir = new File("logs");
            if (!logDir.exists() && !logDir.mkdirs()) {
                System.err.println("Warning: could not create logs directory; falling back to console-only logging.");
            }

            // Configure logger
            logger.setUseParentHandlers(false); // don't propagate to cleared parents [web:16]
            logger.setLevel(Level.ALL);

            // Console handler (always present so errors are visible)
            ConsoleHandler ch = new ConsoleHandler();
            ch.setLevel(Level.ALL);
            ch.setFormatter(new SimpleFormatter());
            logger.addHandler(ch); // add custom console handler [web:69][web:17]

            // File handler (optional; wrap to avoid aborting if path invalid)
            try {
                FileHandler fh = new FileHandler("logs/app.log", true);
                fh.setLevel(Level.ALL);
                fh.setFormatter(new SimpleFormatter());
                logger.addHandler(fh);
            } catch (Exception e) {
                logger.log(Level.WARNING, "File logging disabled (cannot open logs/app.log). Continuing with console only.", e);
            }

            // Menu loop
            try (Scanner sc = new Scanner(System.in)) {
                int choice = -1;
                while (choice != 0) {
                    System.out.println("\n==== Design Patterns Demo ====");
                    System.out.println("1. Observer Pattern");
                    System.out.println("2. Strategy Pattern");
                    System.out.println("3. Factory Pattern");
                    System.out.println("4. Singleton Pattern");
                    System.out.println("5. Adapter Pattern");
                    System.out.println("6. Decorator Pattern");
                    System.out.println("0. Exit");
                    System.out.print("Enter your choice: ");

                    // Read entire line and parse to avoid nextInt()/newline pitfalls
                    String line = sc.nextLine(); // safer in loops [web:51][web:66]
                    try {
                        choice = Integer.parseInt(line.trim());
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid input. Please enter a number.");
                        continue;
                    }

                    try {
                        switch (choice) {
                            case 1 -> ObserverDemo.runDemo();
                            case 2 -> StrategyDemo.runDemo();
                            case 3 -> FactoryDemo.runDemo();
                            case 4 -> SingletonDemo.runInteractive(); // calls interactive singleton demo
                            case 5 -> AdapterDemo.runDemo();
                            case 6 -> DecoratorDemo.runDemo();
                            case 0 -> System.out.println("Exiting program. Goodbye!");
                            default -> System.out.println("Invalid choice, try again.");
                        }
                    } catch (Exception e) {
                        logger.log(Level.SEVERE, "Error running demo", e);
                    }
                }
            }
        } catch (Exception e) {
            // Last-resort visibility if logging setup fails
            e.printStackTrace();
        }
    }
}
