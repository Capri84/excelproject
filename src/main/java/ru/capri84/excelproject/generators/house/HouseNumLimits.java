package ru.capri84.excelproject.generators.house;

public enum HouseNumLimits {
    HOUSE_NUM_UPPER_LIMIT(150);

    private final int value;

    HouseNumLimits(int value) {
        this.value = value;
    }

    public static int getUpperLimitValue() {
        return HOUSE_NUM_UPPER_LIMIT.value;
    }
}
