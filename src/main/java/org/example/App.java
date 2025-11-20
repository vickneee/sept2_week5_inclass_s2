package org.example;

import java.util.Scanner;
import java.util.logging.Logger;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());


    public static void main(String[] args) {

        logger.info("Hello Application started");

        int limit = askInt();
        String result = "i = %d";
        for (int i = 1; i <= limit; i++) {
            int finalI = i;
            logger.info(() -> String.format(result, finalI));
        }
    }

    public static int askInt() {
        Scanner input = new Scanner(System.in);
        String userInput = "Enter a number: ";
        logger.info(() -> String.format(userInput));
        return input.nextInt();
    }
}