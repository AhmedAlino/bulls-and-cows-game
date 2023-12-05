package bullscows;

import java.util.List;

public class SecretManager {
    private int numberBulls;
    private int numberCows;

    public SecretManager(int numberBulls, int numberCows) {
        this.numberBulls = numberBulls;
        this.numberCows = numberCows;
    }

    public int getNumberBulls() {
        return numberBulls;
    }

    public int getNumberCows() {
        return numberCows;
    }

    /**
     * O(n^2) time Complexity.
     * @param secretProvided
     * @param userSecret
     */
    public void countBullOrCow(List<SecretNode> secretProvided, List<SecretNode> userSecret) {
        for(SecretNode secretNode : secretProvided) {
            for (SecretNode userSecretNode: userSecret) {
                if (secretNode.element() == userSecretNode.element()) {
                    if (secretNode.position() == userSecretNode.position())
                        numberBulls++;
                    else
                        numberCows++;
                }
            }
        }

    }

    public String grade(String secret) {
        if (this.numberCows == 0 && this.numberBulls == 0)
            return "Grade: None";
        else if (this.numberCows == 0)
            return "Grade: %s bull(s)".formatted(this.numberBulls);
        else if (this.numberBulls == 0)
            return "Grade: %s cow(s)".formatted(this.numberCows);
        return "Grade: %s bull(s) and %s cow(s)".formatted(this.numberBulls, this.numberCows);
    }

}
