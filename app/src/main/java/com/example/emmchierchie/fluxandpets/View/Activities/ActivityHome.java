package com.example.emmchierchie.fluxandpets.View.Activities;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import com.example.emmchierchie.fluxandpets.Controller.Controller;
import com.example.emmchierchie.fluxandpets.Model.Pojo.Pet;
import com.example.emmchierchie.fluxandpets.R;
import com.example.emmchierchie.fluxandpets.Utils.ResultListener;
import com.example.emmchierchie.fluxandpets.View.Adapters.AdapterRVPets;
import java.util.List;

public class ActivityHome extends AppCompatActivity implements AdapterRVPets.PetListener {

    private AdapterRVPets adapterPets;
    private Controller controller;

    private ActionBar actionBar;
    private Toolbar toolbar;
    private RecyclerView recyclerViewPets;
    private ProgressBar progressBar;
    private View parent_view;
    private SwipeRefreshLayout swipeToRefresh;

    // pido el status que quiera desde una variable que le paso al método del controlador
    private String status = "available";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );

        // modulo el trabajo en métodos y desde la vista solo los llamo
        initViews();
        serRecyclerView();
        setRefresh();
        loadPetList();
        initToolbar();

        // carga la progress bar
        progressBar.setVisibility(View.VISIBLE);
    }

    // pido al controlador la lista de mascotas con el status declarado en la variable
    private void loadPetList() {
        controller = new Controller(this);
        ResultListener<List<Pet>> escuchadorVista = new ResultListener<List<Pet>>() {
            @Override
            public void finish(List<Pet> resultado) {
                // si los resultados no son nulos, deja de cargar la progress bar, se muestra la lista y avisa por snackbar
                // de lo contrario avisa el resultado negativo

                if (resultado != null) {
                    adapterPets.refreshPetList(resultado);
                    progressBar.setVisibility(View.GONE);
                    Snackbar.make(parent_view, "Carga exitosa!", Snackbar.LENGTH_LONG).show();
                } else {
                    progressBar.setVisibility(View.GONE);
                    Snackbar.make(parent_view, "Error al cargar. Volvé a intentarlo", Snackbar.LENGTH_LONG).show();
                }
            }
        };
        controller.getPetsByStatus(escuchadorVista,status);
    }

    // llamo a las vistas
    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        recyclerViewPets = findViewById(R.id.recyclerViewPets);
        progressBar = findViewById(R.id.progressBar);
        parent_view = findViewById(android.R.id.content);
        swipeToRefresh = findViewById(R.id.swipeToRefresh);
    }

    // seteo el recycler view como una lista de grid
    private void serRecyclerView() {
        adapterPets = new AdapterRVPets(this, getApplicationContext());
        recyclerViewPets.setAdapter(adapterPets);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewPets.setLayoutManager(layoutManager);
        recyclerViewPets.setHasFixedSize(true);
    }

    // toolbar con ícono y título
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Flux & Pets");
        getSupportActionBar().setIcon(R.mipmap.fluxandpetslogo);
    }

    // swipeo para refrezcar datos de la lista
    private void setRefresh() {
        swipeToRefresh.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadPetList();

                new Handler().postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        swipeToRefresh.setRefreshing(false);
                    }
                }, 4000);
            }
        } );
    }

    // al clickear una celda del recycler, envío el id del objeto al activity detalle
    @Override
    public void petSelected(Pet pet) {
        Intent intent = new Intent(this, ActivityPetDetail.class);
        Bundle bundle = new Bundle();
        bundle.putString(ActivityPetDetail.PET_ID, pet.getId());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
