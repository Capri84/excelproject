package network;

import model.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomPersonApi {
    @GET("api/")
        //https://randomuser.me/api/?results=5&exc=email,login,registered,phone,cell,id,picture&
        // nat=au,br,ch,de,dk,es,fi,fr,ie,no,nl,nz,tr,us&noinfo
    Call<Response> getPersonsData(@Query("results") int numOfPeople, @Query("exc")
            String parameters, @Query("nat") String nat, @Query("noinfo") String noInfo);
}
