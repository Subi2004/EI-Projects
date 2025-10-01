package util;

import java.util.Scanner;

public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt(String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value < min || value > max) {
                    Logger.warn("Value must be between " + min + " and " + max);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                Logger.warn("Invalid number. Try again.");
            }
        }
    }

    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
