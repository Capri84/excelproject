package ru.capri84.excelproject.generators.birthdate;

import java.time.LocalDate;

public enum DateOfBirthLimits {
    MIN_DATE(LocalDate.of(1919, 1, 1).toEpochDay()),
    MAX_DATE(LocalDate.of(2019, 1, 1).toEpochDay());

    private final long value;

    DateOfBirthLimits(long value) {
        this.value = value;
    }

    public static int getLowerDate() {
        return (int) MIN_DATE.value;
    }

    public static int getUpperDate() {
        return (int) MAX_DATE.value;
    }
}
