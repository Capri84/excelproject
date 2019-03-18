import com.itextpdf.text.DocumentException;
import creators.CreatePDF;
import creators.CreatePersons;
import creators.CreateXLSX;
import database.CreateDBTables;
import database.DBOperations;
import generators.Generatable;
import generators.NumOfPeopleGenerator;
import network.RandomPersonApi;
import network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

class Main {

    private static final String NO_CONNECTION_MSG = "Подключение к интернету отсутствует. Данные взяты из ресурсов.";
    private static CreatePersons personCreator = new CreatePersons();
    private static Generatable<Integer> numOfPeopleGenerator = new NumOfPeopleGenerator();
    private static int numOfPeople = numOfPeopleGenerator.generate();

    public static void main(String[] args) {
        CreateDBTables.createTables();
        CreateXLSX.createXLSX();
        CreateXLSX.createXlsxHeader();
        CreatePDF.createPdf(CreatePDF.DEST_PDF);
        CreatePDF.createPdfHeader();
        makeRequest();
    }

    private static void makeRequest() {
        RandomPersonApi randomPersonApi = RetrofitInstance.getRetrofitInstance().create(RandomPersonApi.class);
        final String excludedData = "email,login,registered,phone,cell,id,picture,nat";
        final String nat = "au,br,ch,de,dk,es,fi,fr,ie,no,nl,nz,tr,us";
        final String noinfo = "noinfo";
        Call<model.Response> call = randomPersonApi.getPersonsData(numOfPeople, excludedData, nat, noinfo);

        call.enqueue(new Callback<model.Response>() {
            @Override
            public void onResponse(Call<model.Response> call, Response<model.Response> response) {
                if (response.isSuccessful()) {
                    CreatePersons.apiResponse = response.body();
                    try {
                        personCreator.buildPersonsList(numOfPeople);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                    try {
                        CreateXLSX.fillXLSX(CreateXLSX.DEST_XLSX);
                        CreatePDF.fillPDF(CreatePDF.DEST_PDF);
                        DBOperations.fillDB();
                    } catch (DocumentException de) {
                        de.printStackTrace();
                    }
                } else {
                    System.out.println("response code " + response.code());
                }
            }

            @Override
            public void onFailure(Call<model.Response> call, Throwable t) {
                System.out.println(NO_CONNECTION_MSG);
                try {
                    DBOperations.selectRndFromDB(numOfPeople);
                    CreateXLSX.fillXLSX(CreateXLSX.DEST_XLSX);
                    CreatePDF.fillPDF(CreatePDF.DEST_PDF);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
