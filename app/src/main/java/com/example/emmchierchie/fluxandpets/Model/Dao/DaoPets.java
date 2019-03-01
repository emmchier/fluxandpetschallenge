package com.example.emmchierchie.fluxandpets.Model.Dao;
import com.example.emmchierchie.fluxandpets.Model.Pojo.Pet;
import com.example.emmchierchie.fluxandpets.Utils.ResultListener;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DaoPets {

    private Retrofit retrofit;
    private DaoService daoService;

    // conexión con Retrofit
    public DaoPets(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        // con GSON convierto los objetos JASON en JAVA
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://petstore.swagger.io/v2/")
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build()).build();
        daoService = retrofit.create(DaoService.class);
    }

    // obtengo la lista de mascotas mediante el llamado de Retrofit
    public void getPetByStatus(final ResultListener<List<Pet>> controllerListener, String status){

        Call<List<Pet>> retrofitCall = daoService.getPetList(status);
        retrofitCall.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                List<Pet> petList = response.body();
                controllerListener.finish(petList);
            }

            // si algo sale mal, devuelvo una lista vacía para que no me rompa la app
            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                controllerListener.finish(new ArrayList<Pet>());
            }
        });
    }

    // ahora obtengo el id del objeto Pet con el llamado de Retrofit
    public void getPetId(final ResultListener<Pet> controllerListener, String petId){

        Call<Pet> retrofitListener = daoService.getPetId(petId);
        retrofitListener.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                Pet pet = response.body();
                controllerListener.finish(pet);
            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {
            }
        });
    }
}
