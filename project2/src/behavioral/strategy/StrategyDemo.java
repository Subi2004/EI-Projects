package behavioral.strategy;

import java.util.Scanner;

public class StrategyDemo {
    public static void runDemo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter payment amount: ");
        double amount = sc.nextDouble();
        sc.nextLine(); // consume newline
        System.out.print("Choose method (card/paypal): ");
        String method = sc.nextLine().toLowerCase();

        PaymentStrategy strategy;
        if ("card".equals(method)) {
            strategy = new CreditCardPayment();
        } else if ("paypal".equals(method)) {
            strategy = new PaypalPayment();
        } else {
            System.out.println("Invalid payment method. Please choose 'card' or 'paypal'.");
            return; // exit the method if invalid input
        }
        
        strategy.pay(amount);
    }
}
