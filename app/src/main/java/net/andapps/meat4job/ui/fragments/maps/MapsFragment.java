/*
Created by Helm  11/01/2017.
*/


package net.andapps.meat4job.ui.fragments.maps;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import net.andapps.meat4job.Meat4Job;
import net.andapps.meat4job.R;
import net.andapps.meat4job.data.model.PlaceLocations;
import net.andapps.meat4job.data.repositories.MapsDataRepository;
import net.andapps.meat4job.ui.di.ActivityModule;
import net.andapps.meat4job.ui.di.DaggerActivityComponent;
import net.andapps.meat4job.ui.di.ViewModule;
import net.andapps.meat4job.ui.menu.MenuActivity;

import javax.inject.Inject;
import java.util.List;

public class MapsFragment extends Fragment implements OnMapReadyCallback, MapsView {

    @Inject
    Context context;
    @Inject
    MapsDataRepository repository;
    @Inject
    MapsPresenter presenter;

    private static final int REQUEST_CODE = 0;
    GoogleMap map;
    View rootView;
    MapView mapView;
    private Context parentActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_map, container, false);
        return rootView;
    }


    @Override
    public void addPinsToMap(PlaceLocations locations) {
        List<PlaceLocations.Geometry> places = locations.getResults();
        for (int i = 0; i < places.size(); i++) {

            map.addMarker(new MarkerOptions().position(
                    new LatLng(places.get(i).getGeometry().getLocation().getLat(),
                            places.get(i).getGeometry().getLocation().getLng())));
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeInjection();
        presenter.onViewCreated();

        mapView = (MapView) rootView.findViewById(R.id.map);
        if (mapView != null) {
            // Initialise the MapView
            mapView.onCreate(null);
            mapView.onResume();
            // Set the map ready callback to receive the GoogleMap object
            mapView.getMapAsync(this);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parentActivity = context;
    }

    public void initializeInjection() {
        Meat4Job application = (Meat4Job) ((MenuActivity) parentActivity).getApplication();
        DaggerActivityComponent.builder()
                .applicationComponent(((MenuActivity) parentActivity).getApplicationComponent(application))
                .activityModule(new ActivityModule(this))
                .viewModule(new ViewModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());

        map = googleMap;

        LatLng barcelona = new LatLng(41.390782,2.170132);
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(barcelona, 10));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(barcelona)      // Sets the center of the map to Mountain View
                .zoom(10)                   // Sets the zoom
                .build();                   // Creates a CameraPosition from the builder
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        if (ActivityCompat.checkSelfPermission(rootView.getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(rootView.getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(((Activity) rootView.getContext()),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE);
        } else {
//            map.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(rootView.getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(rootView.getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
//            map.setMyLocationEnabled(true);
        }
    }




}