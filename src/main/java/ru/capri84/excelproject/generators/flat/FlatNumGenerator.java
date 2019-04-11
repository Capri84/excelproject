package ru.capri84.excelproject.generators.flat;

import ru.capri84.excelproject.generators.Generatable;
import ru.capri84.excelproject.utils.Utils;

public class FlatNumGenerator implements Generatable<Integer> {

    @Override
    public Integer generate() {
        return Utils.random.nextInt(FlatNumLimits.getUpperLimitValue()) + 1;
    }
}
