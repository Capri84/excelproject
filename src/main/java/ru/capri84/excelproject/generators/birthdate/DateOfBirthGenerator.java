package ru.capri84.excelproject.generators.birthdate;

import ru.capri84.excelproject.generators.Generatable;
import ru.capri84.excelproject.utils.Utils;

import java.time.LocalDate;

public class DateOfBirthGenerator implements Generatable<LocalDate> {

    @Override
    public LocalDate generate() {
        long randomDay = DateOfBirthLimits.getLowerDate() +
                Utils.random.nextInt(DateOfBirthLimits.getUpperDate() - DateOfBirthLimits.getLowerDate());
        return LocalDate.ofEpochDay(randomDay);
    }
}