package com.example.emmchierchie.fluxandpets.View.Fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.emmchierchie.fluxandpets.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FragmentMaps extends Fragment implements OnMapReadyCallback {

    private GoogleMap gMap;
    private MapView mapView;

    public FragmentMaps() {
    }

    // constructor que autocrea y devuelve el fragment
    public static FragmentMaps createFragmentMaps(){
        FragmentMaps fragmentMaps = new FragmentMaps();
        return fragmentMaps;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_maps, container, false );
        return view;
    }

    // seteo el maps
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        mapView = view.findViewById( R.id.map );

        // si los datos no bajan nulos, seteo la información del mapa
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    // inicializo el mapa, le paso coordenadas para localizar las 2 sucursales de la pet shop y seteo los movimientos de cámara
    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());

        gMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.addMarker( new MarkerOptions().position( new LatLng( -34.6188126, -58.3677217)).title( "Flux & Pets - Sucursal 1" ).snippet("Allí Vamos!"));
        CameraPosition fluxAndPets1 = CameraPosition.builder().target(new LatLng(-34.6188126, -58.3677217)).zoom(16).bearing(0).tilt(45).build();
        googleMap.moveCamera( CameraUpdateFactory.newCameraPosition(fluxAndPets1));

        googleMap.addMarker( new MarkerOptions().position( new LatLng( -34.9208142, -57.9518059)).title( "Flux & Pets - Sucursal 2" ).snippet("Allí Vamos!"));
        CameraPosition fluxAndPets2 = CameraPosition.builder().target(new LatLng(-34.9208142, -57.9518059)).zoom(16).bearing(0).tilt(45).build();
        googleMap.moveCamera( CameraUpdateFactory.newCameraPosition(fluxAndPets2));
    }
}
