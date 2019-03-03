package generators;

import java.util.Random;

public class FlatNumGenerator {

    public static int generateRandomFlatNum() {
        Random random = new Random();
        return random.nextInt(300) + 1;
    }
}
