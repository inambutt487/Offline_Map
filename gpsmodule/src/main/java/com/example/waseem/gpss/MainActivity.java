package com.example.waseem.gpss;

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
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends Activity implements View.OnClickListener {


    ImageView btn_rout_finder, my_current_location, share_my_location, srchlctin_btn, sharebtn,favbtn;
    LocationManager gps_see;
    private ProgressBar spinner;
    InterstitialAd mInterstitialAd;

    View view;
//   UnityAds unityAds;

    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {android.Manifest.permission.INTERNET, android.Manifest.permission.ACCESS_NETWORK_STATE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.SYSTEM_ALERT_WINDOW,
            android.Manifest.permission.GET_TASKS
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main43);
        favbtn = (ImageView) findViewById(R.id.fav_location);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        favbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        });
        BannerAdmob();
        view = getLayoutInflater().inflate(R.layout.activity_main43, null);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8002008697356859/8559359641");
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

        btn_rout_finder = (ImageView) findViewById(R.id.rout_finder);
        my_current_location = (ImageView) findViewById(R.id.mylocation);
        share_my_location = (ImageView) findViewById(R.id.sharemylocation);
        srchlctin_btn = (ImageView) findViewById(R.id.srchlctinbtn);


        sharebtn = (ImageView) findViewById(R.id.shareapp);
        sharebtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent share = new Intent(android.content.Intent.ACTION_SEND);
                        share.setType("text/plain");
                        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                        share.putExtra(Intent.EXTRA_SUBJECT, "Gps");
                        share.putExtra(
                                Intent.EXTRA_TEXT,
                                "https://play.google.com/store/apps/details?id=com.gps.offline.route.finder.navigate.Maps.locatte")
                        ;
                        startActivity(Intent.createChooser(share, "Gps"));
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

        int id = (int) v.getId();
        if (id == R.id.rout_finder) {
            {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                Intent distnc = new Intent("android.intent.action.VIEW",
                        Uri.parse("google.navigation:q="));
                distnc.setPackage("com.google.android.apps.maps");
                MainActivity.this.startActivity(distnc);
            }

        } else if (id == R.id.mylocation) {
            {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                Intent crntlcations = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(crntlcations);
            }

        } else if (id == R.id.sharemylocation) {
            {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                Intent shr = new Intent(MainActivity.this, shareActivity.class);
                startActivity(shr);
            }

        } else if (id == R.id.srchlctinbtn) {

            Timermap();

        }


    }

    public void Timermap() {
        Intent i = new Intent(getApplicationContext(), findlocation.class);
        startActivity(i);
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    private void BannerAdmob() {
        // TODO Auto-generated method stub
        AdView adViewMain3 = (AdView) this.findViewById(R.id.adViewMain3);
        adViewMain3.loadAd(new AdRequest.Builder().build());
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

        {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
            super.onBackPressed();
        }
    }

}
