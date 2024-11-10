package utils;

public class PalindromicNumber {
    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter 0 to exit.");
        System.out.println();

        while (true) {
            System.out.println("Enter a request:");
            int number = NaturalNumber.getNaturalNumber();
            if (number == -1) {
                continue;
            }

            if (number == 0) {
                System.out.println("Goodbye!");
                break;
            }

            // Determine properties
            boolean isEven = number % 2 == 0;
            boolean isOdd = !isEven;
            boolean isBuzz = number % 7 == 0 || number % 10 == 7;
            boolean isDuck = String.valueOf(number).contains("0");
            boolean isPalindromic = new StringBuilder(String.valueOf(number)).reverse().toString().equals(String.valueOf(number));

            // Print properties
            System.out.println("Properties of " + number);
            System.out.println("        even: " + isEven);
            System.out.println("         odd: " + isOdd);
            System.out.println("        buzz: " + isBuzz);
            System.out.println("        duck: " + isDuck);
            System.out.println(" palindromic: " + isPalindromic);
            System.out.println();
        }
    }
}