package com.example.waseem.gpss;

import android.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends Activity implements View.OnClickListener {

    Button btn_rout_finder, my_current_location, share_my_location , srchlctin_btn, rate_btn, sharebtn;
    LocationManager gps_see;

    InterstitialAd mInterstitialAd;




    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {android.Manifest.permission.INTERNET, android.Manifest.permission.ACCESS_NETWORK_STATE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.SYSTEM_ALERT_WINDOW ,
            android.Manifest.permission.GET_TASKS
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        BannerAdmob();



        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1963616082919996/2033447465");
        requestNewInterstitial();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });


        gps_see = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!gps_see.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || !gps_see.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.b);
            builder.setMessage("you want to open the GPS?")
                    .setCancelable(false)
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    Intent intent = new Intent(
                                            Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    startActivity(intent);
                                }
                            })
                    .setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {

                                    dialog.cancel();
                                }
                            });

            AlertDialog alert = builder.create();
            alert.setTitle("Your Device GPS is OFF");
            alert.show();
        }

        btn_rout_finder = (Button) findViewById(R.id.rout_finder);
        my_current_location = (Button) findViewById(R.id.mylocation);
        share_my_location = (Button) findViewById(R.id.sharemylocation);
        srchlctin_btn = (Button) findViewById(R.id.srchlctinbtn);
        rate_btn= (Button) findViewById(R.id.rateapp);

        rate_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("market://details?id="
                                        + "com.ttg.tattoo.photo.editor.prism.master")));

                    }
                }
        );

        sharebtn= (Button) findViewById(R.id.shareapp);
        sharebtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent share = new Intent(android.content.Intent.ACTION_SEND);
                        share.setType("text/plain");
                        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                        share.putExtra(Intent.EXTRA_SUBJECT, "Tattoo Design My Photo Editor : 3D Tattoo Master");
                        share.putExtra(
                                Intent.EXTRA_TEXT,
                                "https://play.google.com/store/apps/details?id="
                                        + getPackageName()).toString();
                        startActivity(Intent.createChooser(share, "Tattoo Design My Photo Editor : 3D Tattoo Master"));
                    }
                }
        );

        btn_rout_finder.setOnClickListener(this);
        my_current_location.setOnClickListener(this);
        share_my_location.setOnClickListener(this);
        srchlctin_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.rout_finder) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Intent distnc = new Intent("android.intent.action.VIEW",
                        Uri.parse("google.navigation:q="));
                distnc.setPackage("com.google.android.apps.maps");
                MainActivity.this.startActivity(distnc);
            }
        } else if (v.getId() == R.id.mylocation) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {

                Intent crntlcations = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(crntlcations);
            }
        } else if (v.getId() == R.id.sharemylocation)
        {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Intent shr = new Intent(MainActivity.this, shareActivity.class);
            startActivity(shr);
        }

    }else if (v.getId() == R.id.srchlctinbtn) {

            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Intent srch = new Intent(MainActivity.this, findlocation.class);
                startActivity(srch);
            }


        }


    }

    private void BannerAdmob() {
        // TODO Auto-generated method stub
        AdView adView = (AdView) this.findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());
    }


    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }




    private void requestNewInterstitial() {
        // TODO Auto-generated method stub
        AdRequest adRequest = new AdRequest.Builder().build();

        mInterstitialAd.loadAd(adRequest);
    }


    @Override
    public void onBackPressed() {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }else {

            super.onBackPressed();
        }
    }
}
