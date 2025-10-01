package creational;

public class NotificationFactory {
    public static Notification getNotification(String type) {
        if (type.equalsIgnoreCase("email")) return new EmailNotification();
        if (type.equalsIgnoreCase("sms")) return new SMSNotification();
        if (type.equalsIgnoreCase("push")) return new PushNotification();
        return null;
    }
}
