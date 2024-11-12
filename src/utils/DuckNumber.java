package utils;

/**
 * The DuckNumber class provides a command-line interface for users to check if a given natural number is a Duck number.
 * A Duck number is a number that contains at least one zero in it, but the number should not start with zero.
 */
public class DuckNumber {
    /**
     * The main method is the entry point of the program. It handles user input and checks if the number is a Duck number.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int number = NaturalNumber.getNaturalNumber();
        if (number == -1) {
            return;
        }

        // Determine properties
        boolean isEven = number % 2 == 0;
        boolean isOdd = !isEven;
        boolean isBuzz = number % 7 == 0 || number % 10 == 7;
        boolean isDuck = String.valueOf(number).contains("0");

        // Print properties
        System.out.println("Properties of " + number);
        System.out.println("        even: " + isEven);
        System.out.println("         odd: " + isOdd);
        System.out.println("        buzz: " + isBuzz);
        System.out.println("        duck: " + isDuck);
    }
}