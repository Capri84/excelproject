package ru.capri84.excelproject.generators.postcode;

public enum PostCodeLimits {
    POST_LOWER_LIMIT(100000),
    POST_UPPER_LIMIT(200000);

    private final int value;

    PostCodeLimits(int value) {
        this.value = value;
    }

    public static int getLowerLimitValue() {
        return POST_LOWER_LIMIT.value;
    }

    public static int getUpperLimitValue() {
        return POST_UPPER_LIMIT.value;
    }
}
