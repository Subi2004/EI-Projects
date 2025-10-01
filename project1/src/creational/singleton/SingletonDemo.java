package creational.singleton;

import java.util.Scanner;

public class SingletonDemo {

    public static void main(String[] args) {
        runInteractive(); // standalone use; will create its own Scanner
    }

    public static void runDemo() {
        LoggerSingleton logger = LoggerSingleton.getInstance();
        logger.log("First log message.");
        logger.log("Second log message.");
    }

    // Create the scanner when the function is called; do NOT close it.
    public static void runInteractive() {
        LoggerSingleton logger = LoggerSingleton.getInstance();

        Scanner sc = new Scanner(System.in); // intentionally not try-with-resources [web:32][web:30]

        System.out.println("=== LoggerSingleton Interactive Demo ===");
        System.out.println("Enter messages to log. Type 'exit' to quit.");

        while (true) {
            System.out.print("Message> ");
            if (!sc.hasNextLine()) { // EOF guard [web:30]
                System.out.println("No more input. Returning.");
                break;
            }
            String line = sc.nextLine();
            String trimmed = line.trim();
            if (trimmed.equalsIgnoreCase("exit")) {
                break;
            }
            if (trimmed.isEmpty()) {
                System.out.println("(empty line ignored)");
                continue;
            }
            logger.log(trimmed);
        }

        System.out.println("Goodbye!");
        // Do NOT call sc.close(); closing it would close System.in [web:32][web:30]
    }
}
