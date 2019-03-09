package creators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Person;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

public class CreatePDF {

    public static final String DEST_PDF = System.getProperty("user.dir") + "/workbook.pdf";
    private static final String FONT = "./src/main/resources/assets/times.ttf";
    private static PdfPTable table;
    private static Font font;
    private static Document document;

    public static void createPdf(String dest) throws IOException, DocumentException {
        document = new Document(PageSize.A4.rotate(), 0, 0, 0, 0);

        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        font = new Font(bf, 8, Font.NORMAL);

        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        table = new PdfPTable(14);

        table.setWidthPercentage(100);
        table.setSpacingBefore(0f);
        table.setSpacingAfter(0f);

        PdfPCell cell = new PdfPCell(new Phrase("Сгенерированная таблица", font));
        cell.setColspan(14);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        cell.setBackgroundColor(new BaseColor(255, 241, 223));
        table.addCell(cell);

        createPdfHeader();
    }

    private static void createPdfHeader() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(ResourcesData.TABLE_HEADER));
        while (scanner.hasNextLine()) {
            table.addCell(new PdfPCell(new Phrase(scanner.nextLine(), font)));
        }
        scanner.close();
    }

    static String choose(File f, String src) throws IOException {
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

    public static void fillPDF(String dest) throws DocumentException {
        for (Person person : CreatePersons.persons) {
            table.addCell(new PdfPCell(new Phrase(person.getName(), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(person.getSurname(), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(person.getPatronymic(), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(person.getAge()), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(person.getGender(), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(person.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(person.getInn()), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(person.getZipCode(), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(person.getCountry(), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(person.getRegion(), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(person.getCity(), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(person.getStreet(), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(person.getHouseNum()), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(person.getFlatNum()), CreatePDF.font)));
        }
        document.add(table);
        document.close();
        Logger.getLogger(CreatePDF.class.getName()).info("Файл создан. Путь: " + dest);
    }
}
