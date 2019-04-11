package ru.capri84.excelproject.generators;

import ru.capri84.excelproject.utils.Utils;

public class InnGenerator {

    /**
     * An array of inspections of the 77 region
     */
    private static final int[] inspectionsArray = {7701, 7702, 7703, 7704, 7705, 7706, 7707, 7708, 7709, 7710, 7713,
            7714, 7715, 7716, 7717, 7718, 7719, 7720, 7721, 7722, 7723, 7724, 7725, 7726, 7727, 7728, 7729, 7730, 7731,
            7733, 7734, 7735, 7736, 7743, 7746, 7747, 7751};

    /**
     * An array of multipliers used to calculate the eleventh digit of INN
     */
    private static final int[] digitElevenFactors = {7, 2, 4, 10, 3, 5, 9, 4, 6, 8};

    /** An array of multipliers used to calculate the twelves digit of INN */
    private static final int[] digitTwelveFactors = {3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8};

    /**
     * This method generates digits 5-10 of INN
     */
    private static int[] generateMiddle() {
        int[] innIndexArray = new int[GeneratorLimits.INN_INDEX_LENGTH.getValue()];
        for (int i = 0; i < GeneratorLimits.INN_INDEX_LENGTH.getValue(); i++) {
            innIndexArray[i] = Utils.random.nextInt(GeneratorLimits.DIGIT_BOUNDARY.getValue());
        }
        return innIndexArray;
    }

    private static int getRandomInspection() {
        return inspectionsArray[Utils.random.nextInt(inspectionsArray.length)];
    }

    private static int[] convertIntToArray() {
        String temp = Integer.toString(getRandomInspection());
        char[] chars = temp.toCharArray();
        int[] intToArray = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            intToArray[i] = Character.getNumericValue(chars[i]);
        }
        return intToArray;
    }

    private static int countDigitEleven(int[] firstFourDigits, int[] middleSixDigits) {
        return ((digitElevenFactors[0] * firstFourDigits[0] + digitElevenFactors[1] * firstFourDigits[1] +
                digitElevenFactors[2] * firstFourDigits[2] + digitElevenFactors[3] * firstFourDigits[3] +
                digitElevenFactors[4] * middleSixDigits[0] + digitElevenFactors[5] * middleSixDigits[1] +
                digitElevenFactors[6] * middleSixDigits[2] + digitElevenFactors[7] * middleSixDigits[3] +
                digitElevenFactors[8] * middleSixDigits[4] + digitElevenFactors[9] * middleSixDigits[5]) % 11) % 10;
    }

    private static int countDigitTwelve(int[] firstFourDigits, int[] middleSixDigits) {
        return ((digitTwelveFactors[0] * firstFourDigits[0] + digitTwelveFactors[1] * firstFourDigits[1] +
                digitTwelveFactors[2] * firstFourDigits[2] + digitTwelveFactors[3] * firstFourDigits[3] +
                digitTwelveFactors[4] * middleSixDigits[0] + digitTwelveFactors[5] * middleSixDigits[1] +
                digitTwelveFactors[6] * middleSixDigits[2] + digitTwelveFactors[7] * middleSixDigits[3] +
                digitTwelveFactors[8] * middleSixDigits[4] + digitTwelveFactors[9] * middleSixDigits[5] +
                digitTwelveFactors[10] * countDigitEleven(firstFourDigits, middleSixDigits)) % 11) % 10;
    }

    public static long generateINN() {
        StringBuilder sb = new StringBuilder();
        int[] firstFourDigits = convertIntToArray();
        int[] middleSixDigits = generateMiddle();
        for (int first : firstFourDigits) {
            sb.append(first);
        }
        for (int middle : middleSixDigits) {
            sb.append(middle);
        }
        sb.append(countDigitEleven(firstFourDigits, middleSixDigits));
        sb.append(countDigitTwelve(firstFourDigits, middleSixDigits));
        return Long.parseLong(sb.toString());
    }
}