package creators;

import model.Person;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

public class CreateXLSX {

    public static final String DEST_XLSX = System.getProperty("user.dir") + "/workbook.xlsx";
    private static int rownum = 0;
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static Row row;
    private static Cell cell;
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

    public static void fillXLSX(String dest) throws IOException {
        for (Person person : CreatePersons.persons) {
            rownum++;
            row = sheet.createRow(rownum);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(person.getName());
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(person.getSurname());
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(person.getPatronymic());
            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue(person.getAge());
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(person.getGender());
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(person.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue(String.valueOf(person.getInn()));
            cell = row.createCell(7, CellType.NUMERIC);
            cell.setCellValue(person.getZipCode());
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue(person.getCountry());
            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue(person.getRegion());
            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue(person.getCity());
            cell = row.createCell(11, CellType.STRING);
            cell.setCellValue(person.getStreet());
            cell = row.createCell(12, CellType.NUMERIC);
            cell.setCellValue(person.getHouseNum());
            cell = row.createCell(13, CellType.NUMERIC);
            cell.setCellValue(person.getFlatNum());
        }

        File file = new File(System.getProperty("user.dir") + "/workbook.xlsx");

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        Logger.getLogger(CreateXLSX.class.getName()).info("Файл создан. Путь: " + dest);
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

    private static XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
}