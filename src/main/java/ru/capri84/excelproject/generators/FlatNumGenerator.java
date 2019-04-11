package ru.capri84.excelproject.generators;

import ru.capri84.excelproject.utils.Utils;

public class FlatNumGenerator implements Generatable<Integer> {

    @Override
    public Integer generate() {
        return Utils.random.nextInt(GeneratorLimits.FLAT_NUM_UPPER_LIMIT.getValue()) + 1;
    }
}
