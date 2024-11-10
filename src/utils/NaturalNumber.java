package utils;

import java.util.Scanner;

public class NaturalNumber {
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
