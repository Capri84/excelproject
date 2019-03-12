package generators;

import java.util.Random;

public class PostCodeGenerator implements Generatable<Integer> {

    public static final int POST_LOWER_LIMIT = 100000;
    public static final int POST_UPPER_LIMIT = 200000;

    @Override
    public Integer generate() {
        int diff = POST_UPPER_LIMIT - POST_LOWER_LIMIT;
        Random random = new Random();
        return random.nextInt(diff + 1) + POST_LOWER_LIMIT;
    }
}
