package generators;

import java.util.Random;

public class HouseNumGenerator implements Generatable<Integer> {

    private static final int HOUSE_NUM_UPPER_LIMIT = 150;

    @Override
    public Integer generate() {
        Random random = new Random();
        return random.nextInt(HOUSE_NUM_UPPER_LIMIT) + 1;
    }
}
