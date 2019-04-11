package ru.capri84.excelproject.generators.house;

import ru.capri84.excelproject.generators.Generatable;
import ru.capri84.excelproject.utils.Utils;

public class HouseNumGenerator implements Generatable<Integer> {

    @Override
    public Integer generate() {
        return Utils.random.nextInt(HouseNumLimits.getUpperLimitValue()) + 1;
    }
}
