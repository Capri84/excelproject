package ru.capri84.excelproject.generators.people;

public enum PeopleNumLimits {
    MIN_NUMBER_OF_PEOPLE(1),
    MAX_NUMBER_OF_PEOPLE(30);

    private final int value;

    PeopleNumLimits(int value) {
        this.value = value;
    }

    public static int getLowerLimitValue() {
        return MIN_NUMBER_OF_PEOPLE.value;
    }

    public static int getUpperLimitValue() {
        return MAX_NUMBER_OF_PEOPLE.value;
    }
}
