import java.util.Random;

class FlatNumGenerator {

    static int generateRandomFlatNum() {
        Random random = new Random();
        return random.nextInt(300) + 1;
    }
}
