package ru.capri84.excelproject.creators;

import ru.capri84.excelproject.model.Person;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Logger;

public class CreateXLSX {

    public static final String DEST_XLSX = System.getProperty("user.dir") + "/workbook.xlsx";
    private static final String SHEET_NAME = "Workbook sheet";
    private static final String DATE_PATTERN = "dd-MM-yyyy";
    private static final String LOG_MESSAGE = "Файл создан. Путь: ";
    private static int rownum = 0;
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static Row row;
    private static Cell cell;
    private static XSSFCellStyle style;

    public static void createXLSX() {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(SHEET_NAME);
        style = createStyleForTitle(workbook);
        row = sheet.createRow(rownum);
    }

    public static void createXlsxHeader() {
        try (Scanner scanner = new Scanner(new File(ResourcesData.TABLE_HEADER))) {
            int i = 0;
            while (scanner.hasNextLine()) {
                cell = row.createCell(i, CellType.STRING);
                cell.setCellValue(scanner.nextLine());
                cell.setCellStyle(style);
                i++;
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    public static void fillXLSX(String dest) {
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
            cell.setCellValue(person.getDateOfBirth().format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue(String.valueOf(person.getInn()));
            cell = row.createCell(7, CellType.NUMERIC);
            cell.setCellValue(person.getPostCode());
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

        File file = new File(DEST_XLSX);

        try (FileOutputStream outFile = new FileOutputStream(file)) {
            workbook.write(outFile);
            workbook.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        Logger.getLogger(CreateXLSX.class.getName()).info(LOG_MESSAGE + dest);
    }

    private static XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
}