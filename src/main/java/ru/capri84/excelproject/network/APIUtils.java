package ru.capri84.excelproject.network;

class APIUtils {

    public enum ExcludedData {
        EMAIL, LOGIN, REGISTERED, PHONE, CELL, ID, PICTURE, NAT
    }

    public enum Nation {
        AU, BR, CH, DE, DK, ES, FI, FR, IE, NO, NL, NZ, TR, US
    }

    static String buildStringFromEnum(Enum[] enums) {
        StringBuilder builder = new StringBuilder();
        int enumLength = enums.length;
        for (int i = 0; i < enumLength; i++) {
            if (i == enumLength - 1) {
                builder.append(enums[i].name().toLowerCase());
            } else {
                builder.append(enums[i].name().toLowerCase()).append(",");
            }
        }
        return builder.toString();
    }
}
