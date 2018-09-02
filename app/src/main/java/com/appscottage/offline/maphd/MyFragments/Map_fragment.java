package com.appscottage.offline.maphd.MyFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.appscottage.offline.maphd.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Jointech on 5/9/2017.
 */

public class Map_fragment extends Fragment implements OnMapReadyCallback ,GoogleMap.OnMarkerClickListener, GoogleMap.OnMapLoadedCallback {

    public View rootView;
    private GoogleMap googleMap;
    Marker markerobject;
    Button normal,satellite,hybrid;
    private SupportMapFragment supportMapFragment;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.map_fragment, container, false);


        FragmentManager fm = getActivity().getSupportFragmentManager();/// getChildFragmentManager();
        supportMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, supportMapFragment).commit();
        }
        supportMapFragment.getMapAsync(this);



        normal= (Button) rootView.findViewById(R.id.normal);
        satellite= (Button) rootView.findViewById(R.id.satellite);
        hybrid= (Button) rootView.findViewById(R.id.hybrid);

        hybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map_fragment.this.googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            }
        });

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map_fragment.this.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });

        satellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map_fragment.this.googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

            }
        });

        return rootView;
    }





    public void readymap() {
        LatLng latLng = new LatLng(Double.parseDouble((String) CountriesFragment.stringcountriesarray.get(13)), Double.parseDouble((String) CountriesFragment.stringcountriesarray.get(14)));
        if (this.googleMap != null) {
            this.googleMap.setMapType(1);
            this.googleMap.getUiSettings().setZoomControlsEnabled(true);
            this.googleMap.getUiSettings().setCompassEnabled(true);
            this.googleMap.getUiSettings().setRotateGesturesEnabled(true);
            this.googleMap.getUiSettings().setZoomGesturesEnabled(true);
            this.markerobject = this.googleMap.addMarker(new MarkerOptions().position(latLng).title((String) CountriesFragment.stringcountriesarray.get(0)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
//            marker_click(this.markerobject);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 4.0f);
            this.googleMap.moveCamera(cameraUpdate);
            this.googleMap.animateCamera(cameraUpdate);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setOnMapLoadedCallback(this);
        readymap();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMapLoaded() {

    }
}
