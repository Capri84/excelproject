import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import creators.CreatePDF;
import creators.CreatePersons;
import creators.CreateXLSX;
import generators.NumOfPeopleGenerator;
import model.Person;
import network.RandomPersonApi;
import network.RetrofitInstance;
import org.apache.poi.ss.usermodel.CellType;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import static creators.CreatePDF.document;
import static creators.CreatePDF.table;
import static creators.CreateXLSX.*;

class Main {

    public static void main(String[] args) throws IOException, DocumentException {
        CreateXLSX.createXLSX();
        CreatePDF.createPdf(CreatePDF.DEST_PDF);
        makeRequest();
    }

    private static void makeRequest() {
        RandomPersonApi randomPersonApi = RetrofitInstance.getRetrofitInstance().create(RandomPersonApi.class);
        final String excludedData = "email,login,registered,phone,cell,id,picture,nat";
        final String nat = "au,br,ch,de,dk,es,fi,fr,ie,no,nl,nz,tr,us";
        final String noinfo = "noinfo";
        Call<model.Response> call = randomPersonApi.getPersonsData(CreatePersons.numOfPeople, excludedData, nat, noinfo);

        /*Log the URL called*/
        System.out.println(call.request().url() + "");

        call.enqueue(new Callback<model.Response>() {
            @Override
            public void onResponse(Call<model.Response> call, Response<model.Response> response) {
                if (response.isSuccessful()) {
                    CreatePersons.response1 = response.body();
                    System.out.println("response " + CreatePersons.response1);
                    try {
                        CreatePersons.buildPersonsList();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        fillXLSXFromApi(CreateXLSX.DEST_XLSX);
                        fillPDFFromApi(CreatePDF.DEST_PDF);
                    } catch (IOException | DocumentException ioe) {
                        ioe.printStackTrace();
                    }
                } else {
                    System.out.println("response code " + response.code());
                }
            }

            @Override
            public void onFailure(Call<model.Response> call, Throwable t) {
                System.out.println("Подключение к интернету отсутствует. Данные взяты из ресурсов.");
                try {
                    CreateXLSX.fillXLSX(NumOfPeopleGenerator.generateRandomInt(), CreateXLSX.DEST_XLSX);
                    CreatePDF.fillPDF(NumOfPeopleGenerator.generateRandomInt(), CreatePDF.DEST_PDF);
                } catch (IOException | DocumentException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private static void fillXLSXFromApi(String dest) throws IOException {
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

    private static void fillPDFFromApi(String dest) throws DocumentException {
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
