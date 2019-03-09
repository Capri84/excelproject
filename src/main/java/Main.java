import com.itextpdf.text.DocumentException;
import creators.CreatePDF;
import creators.CreatePersons;
import creators.CreateXLSX;
import network.RandomPersonApi;
import network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

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
                        CreateXLSX.fillXLSX(CreateXLSX.DEST_XLSX);
                        CreatePDF.fillPDF(CreatePDF.DEST_PDF);
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
                    CreatePersons.buildPersonsList();
                    CreateXLSX.fillXLSX(CreateXLSX.DEST_XLSX);
                    CreatePDF.fillPDF(CreatePDF.DEST_PDF);
                } catch (IOException | DocumentException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
