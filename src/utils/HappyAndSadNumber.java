package utils;

import java.util.*;

/**
 * The HappyAndSadNumber class provides a command-line interface for users to check if a given natural number is a Happy or Sad number.
 * A Happy number is a number which eventually reaches 1 when replaced by the sum of the square of each digit.
 * A Sad number is a number that does not reach 1 and ends up in a cycle when replaced by the sum of the square of each digit.
 */
public class HappyAndSadNumber {
    private static final Set<String> AVAILABLE_PROPERTIES = new HashSet<>(Arrays.asList(
            "EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY", "JUMPING", "HAPPY", "SAD"
    ));

    private static final Set<Set<String>> MUTUALLY_EXCLUSIVE_PROPERTIES = new HashSet<>(Arrays.asList(
            Set.of("EVEN", "ODD"),
            Set.of("DUCK", "SPY"),
            Set.of("SUNNY", "SQUARE"),
            Set.of("HAPPY", "SAD")
    ));

    /**
     * The main method is the entry point of the program. It handles user input and checks if the number is a Happy or Sad number.
     *
     * @param args Command-line arguments (not used).
     */
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

            } else {
                try {
                    long start = Long.parseLong(parts[0]);
                    int count = Integer.parseInt(parts[1]);
                    Set<String> properties = new HashSet<>();
                    Set<String> excludedProperties = new HashSet<>();

                    for (int i = 2; i < parts.length; i++) {
                        String property = parts[i].toUpperCase();
                        if (property.startsWith("-")) {
                            excludedProperties.add(property.substring(1));
                        } else {
                            properties.add(property);
                        }
                    }

                    if (start <= 0) {
                        System.out.println("The first parameter should be a natural number or zero.");
                        continue;
                    }

                    if (count <= 0) {
                        System.out.println("The second parameter should be a natural number.");
                        continue;
                    }

                    List<String> invalidProperties = new ArrayList<>();
                    for (String property : properties) {
                        if (!AVAILABLE_PROPERTIES.contains(property)) {
                            invalidProperties.add(property);
                        }
                    }
                    for (String property : excludedProperties) {
                        if (!AVAILABLE_PROPERTIES.contains(property)) {
                            invalidProperties.add(property);
                        }
                    }

                    if (!invalidProperties.isEmpty()) {
                        System.out.println("The property " + invalidProperties + " is wrong.");
                        System.out.println("Available properties: " + AVAILABLE_PROPERTIES);
                        continue;
                    }

                    if (hasMutuallyExclusiveProperties(properties, excludedProperties)) {
                        System.out.println("The request contains mutually exclusive properties: " + properties + " and " + excludedProperties);
                        System.out.println("There are no numbers with these properties.");
                        continue;
                    }

                    int found = 0;
                    long number = start;
                    while (found < count) {
                        if (hasAllProperties(number, properties) && !hasAnyExcludedProperties(number, excludedProperties)) {
                            printProperties(number, true);
                            found++;
                        }
                        number++;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                }
            }
        }
    }

    /**
     * Checks if the given sets of properties contain any mutually exclusive properties.
     *
     * @param properties The set of properties to check.
     * @param excludedProperties The set of excluded properties to check.
     * @return true if there are mutually exclusive properties, false otherwise.
     */
    private static boolean hasMutuallyExclusiveProperties(Set<String> properties, Set<String> excludedProperties) {
        for (Set<String> pair : MUTUALLY_EXCLUSIVE_PROPERTIES) {
            if (properties.containsAll(pair) || excludedProperties.containsAll(pair)) {
                return true;
            }
        }

        for (String property : properties) {
            if (excludedProperties.contains(property)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given number has all the specified properties.
     *
     * @param number The number to check.
     * @param properties The set of properties to check.
     * @return true if the number has all the properties, false otherwise.
     */
    private static boolean hasAllProperties(long number, Set<String> properties) {
        for (String property : properties) {
            if (!hasProperty(number, property)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given number has any of the excluded properties.
     *
     * @param number The number to check.
     * @param excludedProperties The set of excluded properties to check.
     * @return true if the number has any of the excluded properties, false otherwise.
     */
    private static boolean hasAnyExcludedProperties(long number, Set<String> excludedProperties) {
        for (String property : excludedProperties) {
            if (hasProperty(number, property)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given number has the specified property.
     *
     * @param number The number to check.
     * @param property The property to check.
     * @return true if the number has the property, false otherwise.
     */
    private static boolean hasProperty(long number, String property) {
        return switch (property) {
            case "EVEN" -> number % 2 == 0;
            case "ODD" -> number % 2 != 0;
            case "BUZZ" -> number % 7 == 0 || number % 10 == 7;
            case "DUCK" -> String.valueOf(number).contains("0");
            case "PALINDROMIC" -> new StringBuilder(String.valueOf(number)).reverse().toString().equals(String.valueOf(number));
            case "GAPFUL" -> number >= 100 && number % Integer.parseInt("" + String.valueOf(number).charAt(0) + String.valueOf(number).charAt(String.valueOf(number).length() - 1)) == 0;
            case "SPY" -> isSpy(number);
            case "SQUARE" -> isSquare(number);
            case "SUNNY" -> isSunny(number);
            case "JUMPING" -> isJumping(number);
            case "HAPPY" -> isHappy(number);
            case "SAD" -> !isHappy(number);
            default -> false;
        };
    }

    /**
     * Checks if the given number is a spy number.
     *
     * @param number The number to check.
     * @return true if the number is a spy number, false otherwise.
     */
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

    /**
     * Checks if the given number is a square number.
     *
     * @param number The number to check.
     * @return true if the number is a square number, false otherwise.
     */
    private static boolean isSquare(long number) {
        long sqrt = (long) Math.sqrt(number);
        return sqrt * sqrt == number;
    }

    /**
     * Checks if the given number is a sunny number.
     *
     * @param number The number to check.
     * @return true if the number is a sunny number, false otherwise.
     */
    private static boolean isSunny(long number) {
        return isSquare(number + 1);
    }

    /**
     * Checks if the given number is a jumping number.
     *
     * @param number The number to check.
     * @return true if the number is a jumping number, false otherwise.
     */
    private static boolean isJumping(long number) {
        String numStr = String.valueOf(number);
        for (int i = 1; i < numStr.length(); i++) {
            int diff = Math.abs(numStr.charAt(i) - numStr.charAt(i - 1));
            if (diff != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given number is a happy number.
     *
     * @param number The number to check.
     * @return true if the number is a happy number, false otherwise.
     */
    private static boolean isHappy(long number) {
        Set<Long> seen = new HashSet<>();
        while (number != 1 && !seen.contains(number)) {
            seen.add(number);
            number = sumOfSquaresOfDigits(number);
        }
        return number == 1;
    }

    /**
     * Calculates the sum of the squares of the digits of the given number.
     *
     * @param number The number to process.
     * @return The sum of the squares of the digits.
     */
    private static long sumOfSquaresOfDigits(long number) {
        long sum = 0;
        while (number > 0) {
            long digit = number % 10;
            sum += digit * digit;
            number /= 10;
        }
        return sum;
    }

    /**
     * Prints the instructions for using the program.
     */
    private static void printInstructions() {
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- enter two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
        System.out.println();
    }

    /**
     * Prints the properties of the given number.
     *
     * @param number The number to process.
     * @param singleLine Whether to print the properties in a single line or multiple lines.
     */
    private static void printProperties(long number, boolean singleLine) {
        boolean isEven = number % 2 == 0;
        boolean isOdd = !isEven;
        boolean isBuzz = number % 7 == 0 || number % 10 == 7;
        boolean isDuck = String.valueOf(number).contains("0");
        boolean isPalindromic = new StringBuilder(String.valueOf(number)).reverse().toString().equals(String.valueOf(number));
        boolean isGapful = number >= 100 && number % Integer.parseInt("" + String.valueOf(number).charAt(0) + String.valueOf(number).charAt(String.valueOf(number).length() - 1)) == 0;
        boolean isSpy = isSpy(number);
        boolean isSquare = isSquare(number);
        boolean isSunny = isSunny(number);
        boolean isJumping = isJumping(number);
        boolean isHappy = isHappy(number);
        boolean isSad = !isHappy;

        if (singleLine) {
            System.out.print(number + " is ");
            if (isEven) System.out.print("even, ");
            if (isOdd) System.out.print("odd, ");
            if (isBuzz) System.out.print("buzz, ");
            if (isDuck) System.out.print("duck, ");
            if (isPalindromic) System.out.print("palindromic, ");
            if (isGapful) System.out.print("gapful, ");
            if (isSpy) System.out.print("spy, ");
            if (isSquare) System.out.print("square, ");
            if (isSunny) System.out.print("sunny, ");
            if (isJumping) System.out.print("jumping, ");
            if (isHappy) System.out.print("happy, ");
            if (isSad) System.out.print("sad, ");
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
            System.out.println("      square: " + isSquare);
            System.out.println("       sunny: " + isSunny);
            System.out.println("     jumping: " + isJumping);
            System.out.println("       happy: " + isHappy);
            System.out.println("         sad: " + isSad);
            System.out.println();
        }
    }
}