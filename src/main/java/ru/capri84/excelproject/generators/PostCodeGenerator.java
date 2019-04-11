package ru.capri84.excelproject.generators;

import ru.capri84.excelproject.utils.Utils;

public class PostCodeGenerator implements Generatable<Integer> {

    @Override
    public Integer generate() {
        int diff = GeneratorLimits.POST_UPPER_LIMIT.getValue() -
                GeneratorLimits.POST_LOWER_LIMIT.getValue();
        return Utils.random.nextInt(diff + 1) + GeneratorLimits.POST_LOWER_LIMIT.getValue();
    }
}
