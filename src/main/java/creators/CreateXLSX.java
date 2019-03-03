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

    private static final int MIN_NUMBER_OF_PEOPLE = 1;
    private static final int MAX_NUMBER_OF_PEOPLE = 30;
    private static final String MALE_GENDER = "М";
    private static final String FEMALE_GENDER = "Ж";
    private static final String TABLE_HEADER = "./src/main/resources/table_header.txt";
    private static final String ALL_SURNAMES = "./src/main/resources/common/surnames.txt";
    private static int rownum = 0;
    private static String dobFormatted;

    public static int generateRandomInt() {
        int diff = MAX_NUMBER_OF_PEOPLE - MIN_NUMBER_OF_PEOPLE;
        Random random = new Random();
        return random.nextInt(diff + 1) + MIN_NUMBER_OF_PEOPLE;
    }

    private static XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public static void readRandLinesFromFile(int numOfPeople) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Workbook sheet");

        XSSFCellStyle style = createStyleForTitle(workbook);
        Row row = sheet.createRow(rownum);

        Scanner scanner = new Scanner(new File(TABLE_HEADER));
        int i = 0;
        Cell cell;
        while (scanner.hasNextLine()) {
            cell = row.createCell(i, CellType.STRING);
            cell.setCellValue(scanner.nextLine());
            cell.setCellStyle(style);
            i++;
        }
        scanner.close();

        List<String> surnames = new ArrayList<>();
        for (int j = 0; j < numOfPeople; j++) {
            surnames.add(choose(new File(ALL_SURNAMES), ALL_SURNAMES));
        }

        for (String surname : surnames) {
            String name;
            String patronymic;
            String gender;
            if (ResourcesData.getMaleSurnamesList().contains(surname)) {
                rownum++;
                name = choose(new File(ResourcesData.NAMES_MALE), ResourcesData.NAMES_MALE);
                patronymic = choose(new File(ResourcesData.PATRONYMICS_MALE), ResourcesData.PATRONYMICS_MALE);
                gender = MALE_GENDER;
            } else if (ResourcesData.getFemaleSurnamesList().contains(surname)) {
                rownum++;
                name = choose(new File(ResourcesData.NAMES_FEMALE), ResourcesData.NAMES_FEMALE);
                patronymic = choose(new File(ResourcesData.PATRONYMICS_FEMALE), ResourcesData.PATRONYMICS_FEMALE);
                gender = FEMALE_GENDER;
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

        File file = new File(System.getProperty("user.dir") + "/workbook.xlsx");

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
}