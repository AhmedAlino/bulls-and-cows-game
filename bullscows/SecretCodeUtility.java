package bullscows;

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class SecretCodeUtility {
    static String generateSecret(int maxLengthOfSecret, int numOfPossibleSymbol) {
        if (maxLengthOfSecret > 36 || numOfPossibleSymbol > 36) { // Max length should be 36
            System.out.println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.");
            System.exit(0); // exit the program
        }
        String[] alphaNumerics = {
                "0", "1", "2", "3", "4", "5", "6", "7", "8",
                "9", "a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q",
                "r", "s", "t", "u", "v", "w", "x", "y", "z"
        };
        Set<String> characterSet = new HashSet<String>();
        StringBuilder resultString = new StringBuilder();
        Random random = new Random();
        while (characterSet.size() < maxLengthOfSecret) {
            int randomIndex = Math.abs(random.nextInt(numOfPossibleSymbol));
            characterSet.add(alphaNumerics[randomIndex]);
        }
        for (String digitCharacter : characterSet)
            resultString.append(digitCharacter);
        return resultString.toString();
    }

    static String printPossibleCharacterToUse(int maxLengthOfSecret, int numOfPossibleSymbol) {
        String[] alphaNumerics = {
                "0", "1", "2", "3", "4", "5", "6", "7", "8",
                "9", "a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q",
                "r", "s", "t", "u", "v", "w", "x", "y", "z"
        };
        String numOfStars = "*".repeat(Math.max(0, maxLengthOfSecret));

        String alphaNumericCharacter = "";

        String O_to_9 = "";
        String a_to_z = "";
        if (numOfPossibleSymbol < 10) {
            O_to_9 = alphaNumerics[0] + "-" + alphaNumerics[numOfPossibleSymbol];
            alphaNumericCharacter = "(%s)".formatted(O_to_9);
        } else {
            O_to_9 = "0-9";
            if (numOfPossibleSymbol == 10)
                a_to_z = alphaNumerics[numOfPossibleSymbol];
            else
                a_to_z = alphaNumerics[10] + "-" + alphaNumerics[numOfPossibleSymbol - 1];
            alphaNumericCharacter = "(%s, %s)".formatted(O_to_9, a_to_z);
        }
        return "The secret is prepared: %s %s.".formatted(numOfStars, alphaNumericCharacter);
    }

    static void handleErrors(String maxLenSecret, String maxLenSymbolSecret) {
        if (Integer.parseInt(maxLenSecret) == 0)
            System.out.println("Error: length must be greater than 1.");
        if (Integer.parseInt(maxLenSecret) > Integer.parseInt(maxLenSymbolSecret)) {
            System.out.println("Error: it's not possible to generate a code with a length of 6 with 5 unique symbols.");
            System.exit(0);
        }
        if (Integer.parseInt(maxLenSymbolSecret) > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        }
    }

    static void handleException(String maxLenSecret) {
        try {
            Integer.parseInt(maxLenSecret);
        } catch (Exception e) {
            System.out.printf("Error: %s isn't a valid number.", maxLenSecret );
            System.exit(0);
        }
    }
}