package ru.capri84.excelproject.network;

import com.itextpdf.text.DocumentException;
import retrofit2.Call;
import retrofit2.Callback;
import ru.capri84.excelproject.creators.CreatePDF;
import ru.capri84.excelproject.creators.CreatePersons;
import ru.capri84.excelproject.creators.CreateXLSX;
import ru.capri84.excelproject.model.Response;

import java.io.IOException;

public final class RequestUtils {

    private static final String NO_CONNECTION_MSG = "Подключение к интернету отсутствует. Данные взяты из ресурсов.";
    private static CreatePersons personCreator = new CreatePersons();

    public static void makeRequest() {
        RandomPersonApi randomPersonApi = RetrofitInstance.getRetrofitInstance().create(RandomPersonApi.class);
        final String noinfo = "noinfo";
        Call<ru.capri84.excelproject.model.Response> call = randomPersonApi.getPersonsData(personCreator.numOfPeople,
                APIUtils.buildStringFromEnum(APIUtils.ExcludedData.values()),
                APIUtils.buildStringFromEnum(APIUtils.Nation.values()), noinfo);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    CreatePersons.apiResponse = response.body();
                    try {
                        personCreator.buildPersonsList();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                    try {
                        CreateXLSX.fillXLSX(CreateXLSX.DEST_XLSX);
                        CreatePDF.fillPDF(CreatePDF.DEST_PDF);
                    } catch (DocumentException de) {
                        de.printStackTrace();
                    }
                } else {
                    System.out.println("response code " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ru.capri84.excelproject.model.Response> call, Throwable t) {
                System.out.println(NO_CONNECTION_MSG);
                try {
                    personCreator.buildPersonsList();
                    CreateXLSX.fillXLSX(CreateXLSX.DEST_XLSX);
                    CreatePDF.fillPDF(CreatePDF.DEST_PDF);
                } catch (IOException | DocumentException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
