package com.example.emmchierchie.fluxandpets.Controller;
import android.content.Context;
import com.example.emmchierchie.fluxandpets.Model.Dao.DaoPets;
import com.example.emmchierchie.fluxandpets.Model.Pojo.Pet;
import com.example.emmchierchie.fluxandpets.Utils.ResultListener;
import java.util.List;

public class Controller {

    private Context context;

    public Controller(Context context) {
        this.context = context;
    }

    // pido la lista de mascotas según su status
    public void getPetsByStatus(final ResultListener<List<Pet>> viewListener, String status) {
        ResultListener<List<Pet>> petListener = new ResultListener<List<Pet>>() {
            @Override
            public void finish(List<Pet> petResults) {
                viewListener.finish(petResults);
            }
        };
        DaoPets daoRetrofit = new DaoPets();
        daoRetrofit.getPetByStatus(petListener, status);
    }

    // pido el id del objeto Pet
    public void getPetId(final ResultListener<Pet> viewListener, String petId) {
        ResultListener<Pet> petIdListener = new ResultListener<Pet>() {
            @Override
            public void finish(Pet results) {
                viewListener.finish(results);
            }
        };

        DaoPets daoRetrofit = new DaoPets();
        daoRetrofit.getPetId(petIdListener, petId);
    }
}

