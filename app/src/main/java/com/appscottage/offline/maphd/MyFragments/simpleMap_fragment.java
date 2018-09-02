package com.appscottage.offline.maphd.MyFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
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
 * Created by Jointech on 5/10/2017.
 */

public class simpleMap_fragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {
    public static View rootView;
    private GoogleMap googleMap;
    Marker markerobject;

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.normal:

                simpleMap_fragment.this.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.hybrid:
                simpleMap_fragment.this.googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

                break;

            case R.id.satellite:
                simpleMap_fragment.this.googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

                 break;
        }
    }





    Button normal, satellite,hybrid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        try {
            rootView = inflater.inflate(R.layout.siteveiw_fragment, container, false);
        } catch (InflateException e) {
        }
        initilizeMap();


        normal= (Button) rootView.findViewById(R.id.normal);
        satellite= (Button) rootView.findViewById(R.id.satellite);
        hybrid= (Button) rootView.findViewById(R.id.hybrid);

        hybrid.setOnClickListener(this);
        normal.setOnClickListener(this);
        satellite.setOnClickListener(this);

        return rootView;
    }

    public void onResume() {
        super.onResume();
    }

    public void onDetach() {
        super.onDetach();
    }

    public boolean onMarkerClick(Marker marker) {
        marker_click(marker);
        return false;
    }

    private void initilizeMap() {
        if (this.googleMap == null) {
            ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        readymap();
    }

    public void readymap() {
        LatLng latLng = new LatLng(Double.parseDouble((String) WondersFragment.stringwonderarray.get(0)), Double.parseDouble((String) WondersFragment.stringwonderarray.get(1)));
        if (this.googleMap != null) {
            this.googleMap.setMapType(1);
            this.googleMap.getUiSettings().setZoomControlsEnabled(true);
            this.googleMap.getUiSettings().setCompassEnabled(true);
            this.googleMap.getUiSettings().setRotateGesturesEnabled(true);
            this.googleMap.getUiSettings().setZoomGesturesEnabled(true);
            this.markerobject = this.googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            marker_click(this.markerobject);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 12.0f);
            this.googleMap.moveCamera(cameraUpdate);
            this.googleMap.animateCamera(cameraUpdate);
        }
    }

    public void marker_click(Marker marker) {
    }

    public void onPause() {
        super.onPause();
    }
}
