package utils;

/**
 * The BuzzNumber class provides a command-line interface for users to check if a given natural number is a Buzz number.
 * A Buzz number is a number that is either divisible by 7 or ends with the digit 7.
 */
public class BuzzNumber {
    /**
     * The main method is the entry point of the program. It handles user input and checks if the number is a Buzz number.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int number = NaturalNumber.getNaturalNumber();
        if (number == -1) {
            return;
        }

        // Determine parity
        String parity = number % 2 == 0 ? "even" : "odd";
        System.out.println("This number is " + parity + ".");

        // Check if the number is a Buzz number
        boolean isDivisibleBy7 = (number % 7 == 0);
        boolean endsWith7 = (number % 10 == 7);

        if (isDivisibleBy7 || endsWith7) {
            System.out.println("It is a Buzz number.");
            System.out.println("Explanation: ");
            if (isDivisibleBy7 && endsWith7) {
                System.out.println(number + " is divisible by 7 and ends with 7.");
            } else if (isDivisibleBy7) {
                System.out.println(number + " is divisible by 7.");
            } else {
                System.out.println(number + " ends with 7.");
            }
        } else {
            System.out.println("It is not a Buzz number.");
            System.out.println("Explanation: " + number + " is neither divisible by 7 nor does it end with 7.");
        }
    }
}