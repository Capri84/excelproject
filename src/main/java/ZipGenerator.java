import java.util.Random;

public class ZipGenerator {

    private static final int ZIP_LOWER_LIMIT = 100000;
    private static final int ZIP_UPPER_LIMIT = 200000;

    static int generateRandomInt() {
        int diff = ZIP_UPPER_LIMIT - ZIP_LOWER_LIMIT;
        Random random = new Random();
        return random.nextInt(diff + 1) + ZIP_LOWER_LIMIT;
    }
}
