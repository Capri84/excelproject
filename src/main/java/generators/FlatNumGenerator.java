package generators;

import java.util.Random;

public class FlatNumGenerator {

    private static final int FLAT_NUM_UPPER_LIMIT = 300;

    public static int generateRandomFlatNum() {
        Random random = new Random();
        return random.nextInt(FLAT_NUM_UPPER_LIMIT) + 1;
    }
}
