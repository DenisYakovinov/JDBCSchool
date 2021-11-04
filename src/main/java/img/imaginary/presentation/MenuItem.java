package img.imaginary.presentation;

import java.io.PrintStream;
import java.util.Scanner;

public class MenuItem {

    protected PrintStream out = System.out;
    protected Scanner scanner = new Scanner(System.in);

    public int readInt(String prompt) {
        out.println(prompt + ":");
        final int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public String readString(String prompt) {
        out.println(prompt + ":");
        return scanner.nextLine();
    }
}
