package org.clinic;

import java.util.Scanner;

public class BaseMenu {
    public Scanner scanner = new Scanner(System.in);
    public int num = scanner.nextInt();

    public static void baseMenu() {
        System.out.println("1. manager panel");
        System.out.println("2. doctor panel");
        System.out.println("3. nurse panel");
        System.out.println("4. patient panel");

    }

}
