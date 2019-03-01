package com.example.emmchierchie.fluxandpets.View.Adapters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.emmchierchie.fluxandpets.Model.Pojo.Pet;
import com.example.emmchierchie.fluxandpets.R;
import java.util.ArrayList;
import java.util.List;

public class AdapterRVPets extends RecyclerView.Adapter {

    public Context context;

    private List<Pet> petList;
    private PetListener listener;

    public AdapterRVPets(PetListener listener, Context context) {
        this.petList = new ArrayList<>();
        this.listener = listener;
        this.context = context;
    }

    // info de la celda del recycler y le asigno el ViewHolder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View cellView = layoutInflater.inflate( R.layout.cell_pet,parent,false);
        ViewHolder viewHolder = new ViewHolder(cellView);

        return viewHolder;
    }

    // cargo el recycler con la lista de mascotas
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Pet pet = petList.get(position);
        ViewHolder vh = (ViewHolder) holder;
        vh.loadAllPets(pet);
    }

    // recorro la lista de mascotas
    @Override
    public int getItemCount() {
        return petList.size();
    }

    // si la info no viene nula, limpio la lista en el activity, la lleno de datos y chequeo la info
    public void refreshPetList(List<Pet> list) {
        if(list != null){
            petList.clear();
            petList.addAll(list);
            notifyDataSetChanged();
        }
    }

    // esta clase ViewHolder contiene la infomación de la celda a reciclar
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewPetName;

        public ViewHolder(final View itemView) {
            super(itemView);

            // al hacer click sobre la celda se sobrescribe un método enviado desde la interfaz al activity
            textViewPetName = itemView.findViewById(R.id.textViewPetName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.petSelected(petList.get(getAdapterPosition()));
                }
            } );
        }
        // si el objeto no es nulo, le seteo el nombre del objeto Pet en posición
        public void loadAllPets(Pet pet) {

            if(pet.getName() != null){
                textViewPetName.setText(pet.getName());
            }
        }
    }

    // interfaz que envía un método con la info de la celda al activity de contexto
    public interface PetListener {
        void petSelected(Pet pet);
    }
}
