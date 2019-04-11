package ru.capri84.excelproject.generators;

public enum GeneratorLimits {
    FLAT_NUM_UPPER_LIMIT (300),
    HOUSE_NUM_UPPER_LIMIT (150),
    INN_INDEX_LENGTH (6),
    DIGIT_BOUNDARY (10),
    MIN_NUMBER_OF_PEOPLE (1),
    MAX_NUMBER_OF_PEOPLE (30),
    POST_LOWER_LIMIT (100000),
    POST_UPPER_LIMIT (200000);

    private final int value;

    GeneratorLimits(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
