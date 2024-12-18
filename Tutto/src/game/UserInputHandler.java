package main.java.com.tuttogame.game;

import java.util.Scanner;

public class UserInputHandler {
    private static final Scanner sc = new Scanner(System.in);

    // Method to ask the user a Yes/No question
    public static boolean askYesOrNo(String prompt) {
        System.out.print(prompt + " (y/n): ");
        while (true) {
            String answer = sc.nextLine().trim().toLowerCase();
            if (answer.equals("y")) {
                return true;
            } else if (answer.equals("n")) {
                return false;
            }
            System.out.print("Invalid input. Please enter 'y' for yes or 'n' for no: ");
        }
    }

    // Method to wait for user input to roll dice or draw a card
    public static void waitForUserPressingEnter(String prompt) {
        System.out.print(prompt);
        sc.nextLine(); // Wait for the user to press Enter
    }
}

