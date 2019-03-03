package generators;

import java.util.Random;

public class HouseNumGenerator {

    public static int generateRandomHouse() {
        Random random = new Random();
        return random.nextInt(150) + 1;
    }
}
