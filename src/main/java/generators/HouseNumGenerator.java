package generators;

import java.util.Random;

public class HouseNumGenerator {

    private static final int HOUSE_NUM_UPPER_LIMIT = 150;

    public static int generateRandomHouse() {
        Random random = new Random();
        return random.nextInt(HOUSE_NUM_UPPER_LIMIT) + 1;
    }
}
