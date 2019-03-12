package generators;

import java.util.Random;

public class NumOfPeopleGenerator implements Generatable<Integer> {

    private static final int MIN_NUMBER_OF_PEOPLE = 1;
    private static final int MAX_NUMBER_OF_PEOPLE = 30;

    @Override
    public Integer generate() {
        int diff = MAX_NUMBER_OF_PEOPLE - MIN_NUMBER_OF_PEOPLE;
        Random random = new Random();
        return random.nextInt(diff + 1) + MIN_NUMBER_OF_PEOPLE;
    }
}
