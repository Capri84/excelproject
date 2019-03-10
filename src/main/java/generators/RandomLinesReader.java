package generators;

import java.io.*;
import java.util.Random;

public class RandomLinesReader {

    public static String choose(File f, String src) throws IOException {
        Random random = new Random();
        int rndLine = random.nextInt(fileLinesCount(src)) + 1;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        for (int i = 0; i < rndLine - 1; i++) {
            bufferedReader.readLine();
        }
        return bufferedReader.readLine();
    }

    private static int fileLinesCount(String src) {
        File file = new File(src);
        int linesCount = 0;
        try {
            LineNumberReader lnr = new LineNumberReader(new FileReader(file));
            while (null != lnr.readLine()) {
                linesCount++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return linesCount;
    }
}
