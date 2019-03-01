package com.example.emmchierchie.fluxandpets.View.Activities;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import com.example.emmchierchie.fluxandpets.Controller.Controller;
import com.example.emmchierchie.fluxandpets.Model.Pojo.Pet;
import com.example.emmchierchie.fluxandpets.R;
import com.example.emmchierchie.fluxandpets.Utils.ResultListener;
import com.example.emmchierchie.fluxandpets.View.Adapters.AdapterViewPager;

public class ActivityPetDetail extends AppCompatActivity {

    private ViewPager viewPagerData;
    private AdapterViewPager adapterViewPager;
    private TabLayout tab;
    private Toolbar toolbar;
    private String petId;
    private ProgressBar progressBar;
    private ImageButton backButton;

    private Controller controller;

    private FragmentManager fragmentManager;

    // recibo el ID del objeto en la celda seleccionada
    public static final String PET_ID = "PET_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pet_detail );

        initViews();
        initToolbar();
        setComponents();
        setBackButton();

        progressBar.setVisibility(View.VISIBLE);

        // obtengo la info por bundle y si no es nulo, lo guardo en una variable global
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            petId = bundle.getString(PET_ID);
        }

        // pido al controlador el ID del objeto seleccionado y si no es nulo, cargo la lista de fragments en el ViewPager
        controller = new Controller(this);
        ResultListener<Pet> viewListener = new ResultListener<Pet>() {
            @Override
            public void finish(Pet results) {
                if (results != null) {
                    adapterViewPager.loadFragments(results);
                    progressBar.setVisibility(View.GONE);
                }
            }
        };
        controller.getPetId(viewListener,petId);
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        viewPagerData = findViewById(R.id.viewPager);
        tab = findViewById(R.id.tab);
        progressBar = findViewById(R.id.progressBar);
        backButton = findViewById(R.id.backButton);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Flux & Pets");
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
    }

    // seteo ViewPager y Tabs
    private void setComponents() {
        // viewPager
        fragmentManager = getSupportFragmentManager();
        adapterViewPager = new AdapterViewPager(fragmentManager, getApplicationContext());

        // tabs
        viewPagerData.setAdapter(adapterViewPager);
        tab.setupWithViewPager(viewPagerData);

    }
    // backButton
    public void setBackButton() {
        backButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
    }
}
