package network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    // Base URL for random user data generation
    private static final String BASE_URL = "https://randomuser.me/";
    private static Retrofit retrofit;

    /* This method will provide the retrofit instance if it exists else it will create a new
           instance and return it.*/
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //Конвертер, необходимый для преобразования JSON'а в объекты
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}