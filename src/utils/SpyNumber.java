package utils;

import java.util.Scanner;

public class SpyNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        printInstructions();

        while (true) {
            System.out.println("Enter a request:");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                printInstructions();
                continue;
            }

            String[] parts = input.split("\\s+");

            if (parts.length == 1) {
                try {
                    long number = Long.parseLong(parts[0]);

                    if (number == 0) {
                        System.out.println("Goodbye!");
                        break;
                    }

                    if (number < 0) {
                        System.out.println("The first parameter should be a natural number or zero.");
                        continue;
                    }

                    printProperties(number, false);

                } catch (NumberFormatException e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                }

            } else if (parts.length == 2) {
                try {
                    long start = Long.parseLong(parts[0]);
                    int count = Integer.parseInt(parts[1]);

                    if (start <= 0) {
                        System.out.println("The first parameter should be a natural number or zero.");
                        continue;
                    }

                    if (count <= 0) {
                        System.out.println("The second parameter should be a natural number.");
                        continue;
                    }

                    for (int i = 0; i < count; i++) {
                        printProperties(start + i, true);
                    }

                } catch (NumberFormatException e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                }

            } else if (parts.length == 3) {
                try {
                    long start = Long.parseLong(parts[0]);
                    int count = Integer.parseInt(parts[1]);
                    String property = parts[2].toUpperCase();

                    if (start <= 0) {
                        System.out.println("The first parameter should be a natural number or zero.");
                        continue;
                    }

                    if (count <= 0) {
                        System.out.println("The second parameter should be a natural number.");
                        continue;
                    }

                    if(!isValidProperty(property)) {
                        System.out.println("The property [" + property + "] is wrong.");
                        System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY]");
                        continue;
                    }

                    int found = 0;
                    long number = start;
                    while (found < count) {
                        if (hasProperty(number, property)) {
                            printProperties(number, true);
                            found++;
                        }
                        number++;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                }

            } else {
                System.out.println("The first parameter should be a natural number or zero.");
            }
        }
    }

    private static boolean hasProperty(long number, String property) {
        return switch (property) {
            case "EVEN" -> number % 2 == 0;
            case "ODD" -> number % 2 != 0;
            case "BUZZ" -> number % 7 == 0 || number % 10 == 7;
            case "DUCK" -> String.valueOf(number).contains("0");
            case "PALINDROMIC" ->
                    new StringBuilder(String.valueOf(number)).reverse().toString().equals(String.valueOf(number));
            case "GAPFUL" ->
                    number >= 100 && number % Integer.parseInt("" + String.valueOf(number).charAt(0) + String.valueOf(number).charAt(String.valueOf(number).length() - 1)) == 0;
            case "SPY" -> isSpy(number);
            default -> false;
        };
    }

    private static boolean isValidProperty(String property) {
        return property.equals("EVEN") || property.equals("ODD") || property.equals("BUZZ") || property.equals("DUCK") || property.equals("PALINDROMIC") || property.equals("GAPFUL") || property.equals("SPY");
    }

    private static void printInstructions() {
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- enter two natural numbers and a property to search for;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
        System.out.println();
    }

    private static void printProperties(long number, boolean singleLine) {
        boolean isEven = number % 2 == 0;
        boolean isOdd = !isEven;
        boolean isBuzz = number % 7 == 0 || number % 10 == 7;
        boolean isDuck = String.valueOf(number).contains("0");
        boolean isPalindromic = new StringBuilder(String.valueOf(number)).reverse().toString().equals(String.valueOf(number));
        boolean isGapful = number >= 100 && number % Integer.parseInt("" + String.valueOf(number).charAt(0) + String.valueOf(number).charAt(String.valueOf(number).length() - 1)) == 0;
        boolean isSpy = isSpy(number);

        if (singleLine) {
            System.out.print(number + " is ");
            if (isEven) System.out.print("even, ");
            if (isOdd) System.out.print("odd, ");
            if (isBuzz) System.out.print("buzz, ");
            if (isDuck) System.out.print("duck, ");
            if (isPalindromic) System.out.print("palindromic, ");
            if (isGapful) System.out.print("gapful, ");
            if (isSpy) System.out.print("spy, ");
            System.out.println();
        } else {
            System.out.println("Properties of " + number);
            System.out.println("        even: " + isEven);
            System.out.println("         odd: " + isOdd);
            System.out.println("        buzz: " + isBuzz);
            System.out.println("        duck: " + isDuck);
            System.out.println(" palindromic: " + isPalindromic);
            System.out.println("      gapful: " + isGapful);
            System.out.println("         spy: " + isSpy);
            System.out.println();
        }
    }

    private static boolean isSpy(long number) {
        String numberStr = String.valueOf(number);
        int sum = 0;
        int product = 1;
        for (char c : numberStr.toCharArray()) {
            int digit = Character.getNumericValue(c);
            sum += digit;
            product *= digit;
        }
        return sum == product;
    }
}
