package generators;

import java.util.Random;

public class FlatNumGenerator implements Generatable<Integer> {

    private static final int FLAT_NUM_UPPER_LIMIT = 300;

    @Override
    public Integer generate() {
        Random random = new Random();
        return random.nextInt(FLAT_NUM_UPPER_LIMIT) + 1;
    }
}
