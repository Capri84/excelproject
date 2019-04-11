package ru.capri84.excelproject.network;

import java.util.Arrays;
import java.util.stream.Collectors;

class APIUtils {

    public enum ExcludedData {
        EMAIL, LOGIN, REGISTERED, PHONE, CELL, ID, PICTURE, NAT
    }

    public enum Nation {
        AU, BR, CH, DE, DK, ES, FI, FR, IE, NO, NL, NZ, TR, US
    }

    static String getExcludedData() {
        return buildStringFromEnum(ExcludedData.values());
    }

    static String getNations() {
        return buildStringFromEnum(Nation.values());
    }

    private static String buildStringFromEnum(Enum[] enums) {
        return Arrays.stream(enums).map(e -> e.name().toLowerCase()).collect(Collectors.joining(","));
    }
}
