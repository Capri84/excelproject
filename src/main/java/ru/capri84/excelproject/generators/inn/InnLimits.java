package ru.capri84.excelproject.generators.inn;

public enum InnLimits {
    INN_INDEX_LENGTH(6),
    DIGIT_BOUNDARY(10);

    private final int value;

    InnLimits(int value) {
        this.value = value;
    }

    public static int getInnIndexLength() {
        return INN_INDEX_LENGTH.value;
    }

    public static int getDigitBoundaryValue() {
        return DIGIT_BOUNDARY.value;
    }
}
