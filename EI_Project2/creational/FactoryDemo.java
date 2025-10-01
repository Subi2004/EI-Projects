package creational;

import java.util.Scanner;

public class FactoryDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("\nChoose Notification (email/sms/push) or 'exit': ");
            String choice = sc.nextLine();

            if (choice.equalsIgnoreCase("exit")) break;

            Notification notif = NotificationFactory.getNotification(choice);
            if (notif != null) {
                System.out.print("Enter message: ");
                String msg = sc.nextLine();
                notif.notifyUser(msg);
            } else {
                System.out.println("Invalid type!");
            }
        }
        sc.close();
        System.out.println("Exiting Notification System...");
    }
}
