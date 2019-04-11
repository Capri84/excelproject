package ru.capri84.excelproject.generators.people;

import ru.capri84.excelproject.generators.Generatable;
import ru.capri84.excelproject.utils.Utils;

public class NumOfPeopleGenerator implements Generatable<Integer> {

    @Override
    public Integer generate() {
        int diff = PeopleNumLimits.getUpperLimitValue() - PeopleNumLimits.getLowerLimitValue();
        return Utils.random.nextInt(diff + 1) + PeopleNumLimits.getLowerLimitValue();
    }
}
