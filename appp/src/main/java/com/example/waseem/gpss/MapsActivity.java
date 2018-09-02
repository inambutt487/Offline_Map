
package com.example.waseem.gpss;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity  {

    private GoogleMap mMap;
    boolean islocationChange = false;
    RadioGroup radioGroupbtn;
    Context con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        BannerAdmob();
       setUpMapIfNeeded();
































        con=getApplicationContext();

        radioGroupbtn = (RadioGroup) findViewById(R.id.radiogrp);

        radioGroupbtn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group,
                                         int checkedId) {
                if (checkedId == R.id.rb_normal) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
                else if (checkedId == R.id.rb_satellite) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
                else if (checkedId == R.id.rb_terrain) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                }
                else if (checkedId == R.id.rb_hybride) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
            }
        });



    }




  void setUpMapIfNeeded() {


        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            mMap.setMyLocationEnabled(true);


            if (mMap != null) {

                mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                    @Override
                    public void onMyLocationChange(Location arg0) {
                        if (islocationChange==false) {
                            LatLng manure = new LatLng(arg0.getLatitude(), arg0.getLongitude());
                            mMap.addMarker(new MarkerOptions().position(manure).title("It's Me!"));
                            CameraUpdate cam=CameraUpdateFactory.newLatLngZoom(manure, 15);
                            mMap.animateCamera(cam);
                            islocationChange = true;
                        }
                    }
                });
            }


        }
    }

    private void BannerAdmob() {
        // TODO Auto-generated method stub
        AdView adView = (AdView) this.findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());
    }



}
