package ru.capri84.excelproject.generators;

import ru.capri84.excelproject.utils.Utils;

import java.time.LocalDate;

public class DateOfBirthGenerator implements Generatable<LocalDate> {

    private static final LocalDate MIN_DATE = LocalDate.of(1919, 1, 1);
    private static final LocalDate MAX_DATE = LocalDate.of(2019, 1, 1);

    @Override
    public LocalDate generate() {
        int minDay = (int) MIN_DATE.toEpochDay();
        int maxDay = (int) MAX_DATE.toEpochDay();
        long randomDay = minDay + Utils.random.nextInt(maxDay - minDay);
        return LocalDate.ofEpochDay(randomDay);
    }
}