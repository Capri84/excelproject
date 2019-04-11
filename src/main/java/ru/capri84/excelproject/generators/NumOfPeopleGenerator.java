package ru.capri84.excelproject.generators;

import ru.capri84.excelproject.utils.Utils;

public class NumOfPeopleGenerator implements Generatable<Integer> {

    @Override
    public Integer generate() {
        int diff = GeneratorLimits.MAX_NUMBER_OF_PEOPLE.getValue() -
                GeneratorLimits.MIN_NUMBER_OF_PEOPLE.getValue();
        return Utils.random.nextInt(diff + 1) + GeneratorLimits.MIN_NUMBER_OF_PEOPLE.getValue();
    }
}
