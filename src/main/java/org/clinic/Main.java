package org.clinic;

public class Main {
    public static void main(String[] args) {
        try {
            BaseMenu.baseMenu();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}