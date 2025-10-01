package structural.adapter;

import java.util.Scanner;

public class AdapterDemo {
    public static void runDemo() {
        AudioPlayer player = new AudioPlayer();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter file type (mp3/mp4/vlc): ");
        String type = sc.nextLine();
        System.out.print("Enter file name: ");
        String name = sc.nextLine();
        player.play(type, name);
    }
}
