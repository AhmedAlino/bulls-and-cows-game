package bullscows;

import java.util.*;
import static bullscows.SecretCodeUtility.generateSecret;
import static bullscows.SecretCodeUtility.printPossibleCharacterToUse;
import static bullscows.SecretCodeUtility.handleErrors;
import static bullscows.SecretCodeUtility.handleException;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
             Scanner scannerString = new Scanner(System.in);
        ) {

            System.out.println("Input the length of the secret code:");
            String lengthLimitAsString = scannerString.nextLine();
            handleException(lengthLimitAsString);

            System.out.println("Input the number of possible symbols in the code:");
            String numOfPossibleCharacterOfTheSecretAsString = scannerString.nextLine();
            handleException(numOfPossibleCharacterOfTheSecretAsString);

            handleErrors(lengthLimitAsString, numOfPossibleCharacterOfTheSecretAsString);

            int lengthLimit = Integer.parseInt(lengthLimitAsString);
            int numOfPossibleCharacterOfTheSecret = Integer.parseInt(numOfPossibleCharacterOfTheSecretAsString);

            String secretProvided = generateSecret(lengthLimit, numOfPossibleCharacterOfTheSecret); // random secret with limit

            final String POSSIBLE_CHARACTER_TO_BE_USE = printPossibleCharacterToUse(lengthLimit, numOfPossibleCharacterOfTheSecret);
            System.out.println(POSSIBLE_CHARACTER_TO_BE_USE);

            System.out.println("Okay, let's start a game!");

            int numberOfBulls = 0;
            int numberOfTurns = 1;
            while (numberOfBulls != secretProvided.length()){
                SecretManager secretManager = new SecretManager(0, 0); // init bull and cow to 0

                System.out.println("Turn %s:".formatted(numberOfTurns));
                String userSecret = scanner.nextLine();

                List<SecretNode> secretProvidedList = new ArrayList<>();
                for (int i = 0; i < secretProvided.length(); i++) {
                    secretProvidedList.add(new SecretNode(secretProvided.charAt(i), i));
                }
                List<SecretNode> userSecretList = new ArrayList<>();
                for (int i = 0; i < userSecret.length(); i++) {
                    userSecretList.add(new SecretNode(userSecret.charAt(i), i));
                }

                secretManager.countBullOrCow(secretProvidedList, userSecretList);
                System.out.println(secretManager.grade(secretProvided));

                numberOfBulls = secretManager.getNumberBulls();
                numberOfTurns++;
            }
            System.out.println("Congratulations! You guessed the secret code.");
        }
    }

}
