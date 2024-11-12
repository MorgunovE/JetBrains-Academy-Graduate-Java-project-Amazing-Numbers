package utils;

import java.util.Scanner;

/**
 * The NaturalNumber class provides a method to get a natural number from user input.
 * A natural number is a positive integer greater than zero.
 */
public class NaturalNumber {
    /**
     * Prompts the user to enter a natural number and returns it.
     * If the input is not a natural number, it prints an error message and returns -1.
     *
     * @return The natural number entered by the user, or -1 if the input is invalid.
     */
    public static int getNaturalNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a natural number:");

        if (!scanner.hasNextInt()) {
            System.out.println("This number is not natural!");
            return -1;
        }

        int number = scanner.nextInt();

        if (number <= 0) {
            System.out.println("This number is not natural!");
            return -1;
        }

        return number;
    }
}