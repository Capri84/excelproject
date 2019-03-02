import java.util.Random;

class HouseNumGenerator {

    static int generateRandomHouse() {
        Random random = new Random();
        return random.nextInt(150) + 1;
    }
}
