package com.example.emmchierchie.fluxandpets.Model.Dao;
import com.example.emmchierchie.fluxandpets.Model.Pojo.Pet;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DaoService {

    // convierto los resultados de la API en un Json
    @Headers({"Accept: application/json"})

    // obtengo por la URL lista de mascotas
    @GET("pet/findByStatus?")
    Call<List<Pet>> getPetList(@Query("status") String status);

    // obtengo por la URL el id de la mascota
    @GET("pet/{petId}")
    Call<Pet> getPetId(@Path("petId")String petId);
}

