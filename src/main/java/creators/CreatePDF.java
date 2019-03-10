package creators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Logger;

public class CreatePDF {

    public static final String DEST_PDF = System.getProperty("user.dir") + "/workbook.pdf";
    private static final String FONT = "./src/main/resources/assets/times.ttf";
    private static final String GENERATED_TABLE = "Сгенерированная таблица";
    private static final String DATE_PATTERN = "dd-MM-yyyy";
    private static final String LOG_MESSAGE = "Файл создан. Путь: ";
    private static final int FONT_SIZE = 8;
    private static final int NUM_OF_COLUMNS = 14;
    private static final int TABLE_WIDTH_PERCENTAGE = 100;
    private static final float TABLE_SPACING_BEFORE = 0f;
    private static final float TABLE_SPACING_AFTER = 0f;
    private static final int CELL_COLSPAN = 14;
    private static final float CELL_PADDING = 5.0f;
    private static final int BACKGROUND_COLOUR_RED = 255;
    private static final int BACKGROUND_COLOUR_GREEN = 241;
    private static final int BACKGROUND_COLOUR_BLUE = 223;
    private static PdfPTable table;
    private static Font font;
    private static Document document;

    public static void createPdf(String dest) throws IOException, DocumentException {
        document = new Document(PageSize.A4.rotate(), 0, 0, 0, 0);

        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        font = new Font(bf, FONT_SIZE, Font.NORMAL);

        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        table = new PdfPTable(NUM_OF_COLUMNS);

        table.setWidthPercentage(TABLE_WIDTH_PERCENTAGE);
        table.setSpacingBefore(TABLE_SPACING_BEFORE);
        table.setSpacingAfter(TABLE_SPACING_AFTER);

        PdfPCell cell = new PdfPCell(new Phrase(GENERATED_TABLE, font));
        cell.setColspan(CELL_COLSPAN);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(CELL_PADDING);
        cell.setBackgroundColor(new BaseColor(BACKGROUND_COLOUR_RED, BACKGROUND_COLOUR_GREEN, BACKGROUND_COLOUR_BLUE));
        table.addCell(cell);
    }

    public static void createPdfHeader() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(ResourcesData.TABLE_HEADER));
        while (scanner.hasNextLine()) {
            table.addCell(new PdfPCell(new Phrase(scanner.nextLine(), font)));
        }
        scanner.close();
    }

    public static void fillPDF(String dest) throws DocumentException {
        for (Person person : CreatePersons.persons) {
            table.addCell(new PdfPCell(new Phrase(person.getName(), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(person.getSurname(), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(person.getPatronymic(), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(person.getAge()), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(person.getGender(), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(person.getDateOfBirth().
                    format(DateTimeFormatter.ofPattern(DATE_PATTERN)), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(person.getInn()), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(person.getPostCode(), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(person.getCountry(), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(person.getRegion(), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(person.getCity(), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(person.getStreet(), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(person.getHouseNum()), CreatePDF.font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(person.getFlatNum()), CreatePDF.font)));
        }
        document.add(table);
        document.close();
        Logger.getLogger(CreatePDF.class.getName()).info(LOG_MESSAGE + dest);
    }
}
