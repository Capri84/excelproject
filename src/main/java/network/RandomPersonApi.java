package network;

import model.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomPersonApi {
    @GET("api/")
    Call<Response> getPersonsData(@Query("results") int numOfPeople, @Query("exc")
            String parameters, @Query("nat") String nat, @Query("noinfo") String noInfo);
}
