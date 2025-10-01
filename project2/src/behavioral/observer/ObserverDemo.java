package behavioral.observer;

import java.util.Scanner;

public class ObserverDemo {
    public static void runDemo() {
        Scanner sc = new Scanner(System.in);
        StockMarket market = new StockMarket();
        System.out.print("Enter first investor name: ");
        market.registerInvestor(new Investor(sc.nextLine()));
        System.out.print("Enter second investor name: ");
        market.registerInvestor(new Investor(sc.nextLine()));

        System.out.print("Enter new stock price: ");
        double price = sc.nextDouble();
        market.setPrice(price);
    }
}
