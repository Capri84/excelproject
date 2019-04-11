package ru.capri84.excelproject.generators.flat;

public enum FlatNumLimits {
    FLAT_NUM_UPPER_LIMIT(300);

    private final int value;

    FlatNumLimits(int value) {
        this.value = value;
    }

    public static int getUpperLimitValue() {
        return FLAT_NUM_UPPER_LIMIT.value;
    }
}
