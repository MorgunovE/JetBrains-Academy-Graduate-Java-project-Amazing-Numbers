package utils;

public class DuckNumber {
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
