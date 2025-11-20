package org.example;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        System.out.println("Hello and welcome!");

        int limit = askInt();

        for (int i = 1; i <= limit; i++) {
            System.out.println("i = " + i);
        }
    }

    public static int askInt() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number: ");
        return input.nextInt();
    }
}