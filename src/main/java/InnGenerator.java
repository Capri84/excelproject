import java.util.Random;

class InnGenerator {

    private static final int INN_INDEX_LENGTH = 6;
    private static final int DIGIT_BOUNDARY = 10;
    private static int inspectionsArray[] = {7701, 7702, 7703, 7704, 7705, 7706, 7707, 7708, 7709, 7710, 7713, 7714, 7715,
            7716, 7717, 7718, 7719, 7720, 7721, 7722, 7723, 7724, 7725, 7726, 7727, 7728, 7729, 7730, 7731, 7733, 7734,
            7735, 7736, 7743, 7746, 7747, 7751};
    private static int[] digitElevenFactors = {7, 2, 4, 10, 3, 5, 9, 4, 6, 8};
    private static int[] digitTwelveFactors = {3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8};

    private static int[] generateMiddle() {
        Random random = new Random();
        int[] innIndexArray = new int[INN_INDEX_LENGTH];
        for (int i = 0; i < INN_INDEX_LENGTH; i++) {
            innIndexArray[i] = random.nextInt(DIGIT_BOUNDARY);
        }
        return innIndexArray;
    }

    private static int getRandomInspection() {
        Random random = new Random();
        return inspectionsArray[random.nextInt(inspectionsArray.length)];
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

    private static int countDigitEleven(int[] first4digits, int[] middle6digits) {
        return ((digitElevenFactors[0] * first4digits[0] + digitElevenFactors[1] * first4digits[1] +
                digitElevenFactors[2] * first4digits[2] + digitElevenFactors[3] * first4digits[3] +
                digitElevenFactors[4] * middle6digits[0] + digitElevenFactors[5] * middle6digits[1] +
                digitElevenFactors[6] * middle6digits[2] + digitElevenFactors[7] * middle6digits[3] +
                digitElevenFactors[8] * middle6digits[4] + digitElevenFactors[9] * middle6digits[5]) % 11) % 10;
    }

    private static int countDigitTwelve(int[] first4digits, int[] middle6digits) {
        return ((digitTwelveFactors[0] * first4digits[0] + digitTwelveFactors[1] * first4digits[1] +
                digitTwelveFactors[2] * first4digits[2] + digitTwelveFactors[3] * first4digits[3] +
                digitTwelveFactors[4] * middle6digits[0] + digitTwelveFactors[5] * middle6digits[1] +
                digitTwelveFactors[6] * middle6digits[2] + digitTwelveFactors[7] * middle6digits[3] +
                digitTwelveFactors[8] * middle6digits[4] + digitTwelveFactors[9] * middle6digits[5] +
                digitTwelveFactors[10] * countDigitEleven(first4digits, middle6digits)) % 11) % 10;
    }

    static long makeINN() {
        StringBuilder sb = new StringBuilder();
        int[] first4digits = convertIntToArray();
        int[] middle6digits = generateMiddle();
        for (int first : first4digits) {
            sb.append(first);
        }
        for (int middle : middle6digits) {
            sb.append(middle);
        }
        sb.append(countDigitEleven(first4digits, middle6digits));
        sb.append(countDigitTwelve(first4digits, middle6digits));
        return Long.parseLong(sb.toString());
    }
}