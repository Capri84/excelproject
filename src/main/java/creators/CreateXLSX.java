package creators;

import generators.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

public class CreateXLSX {

    public static final String DEST_XLSX = System.getProperty("user.dir") + "/workbook.xlsx";
    public static int rownum = 0;
    private static String dobFormatted;
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;
    public static Row row;
    public static Cell cell;
    private static XSSFCellStyle style;

    public static void createXLSX() throws IOException {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Workbook sheet");
        style = createStyleForTitle(workbook);
        row = sheet.createRow(rownum);

        createXlsxHeader();
    }

    private static void createXlsxHeader() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(ResourcesData.TABLE_HEADER));
        int i = 0;
        while (scanner.hasNextLine()) {
            cell = row.createCell(i, CellType.STRING);
            cell.setCellValue(scanner.nextLine());
            cell.setCellStyle(style);
            i++;
        }
        scanner.close();
    }

    public static void fillXLSX(int numOfPeople, String dest) throws IOException {
        List<String> surnames = new ArrayList<>();
        for (int j = 0; j < numOfPeople; j++) {
            surnames.add(choose(new File(ResourcesData.ALL_SURNAMES), ResourcesData.ALL_SURNAMES));
        }

        for (String surname : surnames) {
            String name;
            String patronymic;
            String gender;
            if (ResourcesData.getMaleSurnamesList().contains(surname)) {
                rownum++;
                name = choose(new File(ResourcesData.NAMES_MALE), ResourcesData.NAMES_MALE);
                patronymic = choose(new File(ResourcesData.PATRONYMICS_MALE), ResourcesData.PATRONYMICS_MALE);
                gender = ResourcesData.MALE_GENDER;
            } else if (ResourcesData.getFemaleSurnamesList().contains(surname)) {
                rownum++;
                name = choose(new File(ResourcesData.NAMES_FEMALE), ResourcesData.NAMES_FEMALE);
                patronymic = choose(new File(ResourcesData.PATRONYMICS_FEMALE), ResourcesData.PATRONYMICS_FEMALE);
                gender = ResourcesData.FEMALE_GENDER;
            } else {
                System.out.println(surname + " " + "Фамилия не найдена в списках");
                continue;
            }
            row = sheet.createRow(rownum);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(name);
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(surname);
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(patronymic);
            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue(calculateAge(generateDOB(), LocalDate.now()));
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(gender);
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(dobFormatted);
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue(String.valueOf(InnGenerator.makeINN()));
            cell = row.createCell(7, CellType.NUMERIC);
            cell.setCellValue(ZipGenerator.generateRandomInt());
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue(choose(new File(ResourcesData.COUNTRIES), ResourcesData.COUNTRIES));
            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue(choose(new File(ResourcesData.REGIONS), ResourcesData.REGIONS));
            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue(choose(new File(ResourcesData.CITIES), ResourcesData.CITIES));
            cell = row.createCell(11, CellType.STRING);
            cell.setCellValue(choose(new File(ResourcesData.STREETS), ResourcesData.STREETS));
            cell = row.createCell(12, CellType.NUMERIC);
            cell.setCellValue(HouseNumGenerator.generateRandomHouse());
            cell = row.createCell(13, CellType.NUMERIC);
            cell.setCellValue(FlatNumGenerator.generateRandomFlatNum());
        }

        File file = new File(dest);

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        Logger.getLogger(CreateXLSX.class.getName()).info("Файл создан. Путь: " + file.getAbsolutePath());
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

    private static XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
}