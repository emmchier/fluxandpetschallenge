package com.example.emmchierchie.fluxandpets.View.Fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.emmchierchie.fluxandpets.Model.Pojo.Pet;
import com.example.emmchierchie.fluxandpets.R;

public class FragmentPetData extends Fragment {

    private Pet pet;
    private String name;
    private String status;
    private String id;

    private TextView petName;
    private TextView petStatus;
    private TextView petId;

    // con esta variable seteo el status del detalle
    private String available = "available";

    public FragmentPetData() {
    }

    // constructor que crea el fragment y pasa un bundle con info del objeto a su vista
    public static FragmentPetData createFragmentPetData(Pet pet){
        FragmentPetData fragmentPetDetail = new FragmentPetData();
        Bundle bundle = new Bundle();
        bundle.putString("petName", pet.getName());
        bundle.putString("petId", pet.getId());
        bundle.putString("petStatus", pet.getStatus());
        fragmentPetDetail.setArguments(bundle);
        return fragmentPetDetail;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_pet_data, container, false );

        petName = view.findViewById(R.id.petName);
        petStatus = view.findViewById(R.id.petStatus);
        petId = view.findViewById(R.id.petId);

        // recibo el bundle y si los datos no son nulos, seteo las vistas del fragment
        Bundle bundle = getArguments();
        if (bundle != null) {
            name = bundle.getString("petName");
            status = bundle.getString("petStatus");
            id = bundle.getString("petId");

            petName.setText(name);
            petId.setText(id);
            setStatus();
        }

        return view;
    }

    // seteo el texto del status en el detalle según el status del objeto Pet
    private void setStatus() {
        if (status.equals(available)) {
            petStatus.setText(R.string.available);
        } else {
            petStatus.setText(R.string.sold);
        }
    }
}
