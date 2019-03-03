import com.itextpdf.text.DocumentException;
import creators.CreatePDF;
import creators.CreateXLSX;

import java.io.IOException;

class Main {

    public static void main(String[] args) throws IOException, DocumentException {
        CreateXLSX.readRandLinesFromFile(CreateXLSX.generateRandomInt());
        CreatePDF.createPdf(CreatePDF.DEST);
    }
}
