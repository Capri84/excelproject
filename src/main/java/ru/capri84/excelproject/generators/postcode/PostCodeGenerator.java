package ru.capri84.excelproject.generators.postcode;

import ru.capri84.excelproject.generators.Generatable;
import ru.capri84.excelproject.utils.Utils;

public class PostCodeGenerator implements Generatable<Integer> {

    @Override
    public Integer generate() {
        int diff = PostCodeLimits.getUpperLimitValue() - PostCodeLimits.getLowerLimitValue();
        return Utils.random.nextInt(diff + 1) + PostCodeLimits.getLowerLimitValue();
    }
}
