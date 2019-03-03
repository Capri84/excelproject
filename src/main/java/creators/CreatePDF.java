package creators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import generators.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

public class CreatePDF {

    public static final String DEST = "E:/IT learning/Tinkoff/HomeTasks/workbook.pdf";
    private static final String TABLE_HEADER = "./src/main/resources/table_header.txt";
    private static final int MIN_NUMBER_OF_PEOPLE = 1;
    private static final int MAX_NUMBER_OF_PEOPLE = 30;
    private static final String MALE_GENDER = "М";
    private static final String FEMALE_GENDER = "Ж";
    private static final String ALL_SURNAMES = "./src/main/resources/common/surnames.txt";
    private static final String FONT = "./src/main/resources/assets/times.ttf";
    private static String dobFormatted;
    private static PdfPTable table;
    private static Font font;

    public static void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate(), 0, 0, 0, 0);

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

        createHeader();
        fillPDF(generateRandomInt());

        document.add(table);
        document.close();
        Logger.getLogger(CreatePDF.class.getName()).info("Файл создан. Путь: " + dest);
    }

    private static void createHeader() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(TABLE_HEADER));
        while (scanner.hasNextLine()) {
            table.addCell(new PdfPCell(new Phrase(scanner.nextLine(), font)));
        }
        scanner.close();
    }

    private static int generateRandomInt() {
        int diff = MAX_NUMBER_OF_PEOPLE - MIN_NUMBER_OF_PEOPLE;
        Random random = new Random();
        return random.nextInt(diff + 1) + MIN_NUMBER_OF_PEOPLE;
    }

    private static String choose(File f, String src) throws IOException {
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

    private static LocalDate generateDOB() {
        DateOfBirthGenerator dob = new DateOfBirthGenerator(LocalDate.of(1919, 1, 1),
                LocalDate.of(2019, 1, 1));
        LocalDate dobDate = dob.nextDate();
        dobFormatted = dobDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return dobDate;
    }

    private static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        return Period.between(birthDate, currentDate).getYears();
    }

    private static void fillPDF(int numOfPeople) throws IOException {
        List<String> surnames = new ArrayList<>();
        for (int j = 0; j < numOfPeople; j++) {
            surnames.add(choose(new File(ALL_SURNAMES), ALL_SURNAMES));
        }

        for (String surname : surnames) {
            String name;
            String patronymic;
            String gender;
            if (ResourcesData.getMaleSurnamesList().contains(surname)) {
                name = choose(new File(ResourcesData.NAMES_MALE), ResourcesData.NAMES_MALE);
                patronymic = choose(new File(ResourcesData.PATRONYMICS_MALE), ResourcesData.PATRONYMICS_MALE);
                gender = MALE_GENDER;
            } else if (ResourcesData.getFemaleSurnamesList().contains(surname)) {
                name = choose(new File(ResourcesData.NAMES_FEMALE), ResourcesData.NAMES_FEMALE);
                patronymic = choose(new File(ResourcesData.PATRONYMICS_FEMALE), ResourcesData.PATRONYMICS_FEMALE);
                gender = FEMALE_GENDER;
            } else {
                System.out.println(surname + " " + "Фамилия не найдена в списках");
                continue;
            }

            table.addCell(new PdfPCell(new Phrase(name, font)));
            table.addCell(new PdfPCell(new Phrase(surname, font)));
            table.addCell(new PdfPCell(new Phrase(patronymic, font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(calculateAge(generateDOB(), LocalDate.now())), font)));
            table.addCell(new PdfPCell(new Phrase(gender, font)));
            table.addCell(new PdfPCell(new Phrase(dobFormatted, font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(InnGenerator.makeINN()), font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(ZipGenerator.generateRandomInt()), font)));
            table.addCell(new PdfPCell(new Phrase(choose(new File(ResourcesData.COUNTRIES), ResourcesData.COUNTRIES), font)));
            table.addCell(new PdfPCell(new Phrase(choose(new File(ResourcesData.REGIONS), ResourcesData.REGIONS), font)));
            table.addCell(new PdfPCell(new Phrase(choose(new File(ResourcesData.CITIES), ResourcesData.CITIES), font)));
            table.addCell(new PdfPCell(new Phrase(choose(new File(ResourcesData.STREETS), ResourcesData.STREETS), font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(HouseNumGenerator.generateRandomHouse()), font)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(FlatNumGenerator.generateRandomFlatNum()), font)));
        }
    }
}