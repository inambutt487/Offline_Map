package com.appscottage.offline.maphd;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.appscottage.offline.maphd.Utils.Constant;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.NativeExpressAdView;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;

import cn.refactor.lib.colordialog.PromptDialog;
import permission.auron.com.marshmallowpermissionhelper.ActivityManagePermission;
import permission.auron.com.marshmallowpermissionhelper.PermissionResult;
import permission.auron.com.marshmallowpermissionhelper.PermissionUtils;

import static com.unity3d.ads.properties.ClientProperties.getApplicationContext;

/**
 * Created by Jointech on 5/12/2017.
 */

public class MainMenu extends ActivityManagePermission {



    AdView adViewMain1,adViewMain2;
    InterstitialAd mInterstitialAd;
    ImageView offline_map,map_atlas;
    boolean denay=false;
//    UnityAds unityAds;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        Bannernative();
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8002008697356859/8559359641");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                finish();
               System.exit(0);
            }
        });
        requestNewInterstitial();
        askCompactPermissions(new String[]{
                        PermissionUtils.Manifest_ACCESS_FINE_LOCATION,
                        PermissionUtils.Manifest_ACCESS_COARSE_LOCATION,
                        PermissionUtils.Manifest_WRITE_EXTERNAL_STORAGE,},
                new PermissionResult() {
                    @Override
                    public void permissionGranted() {
                        denay=false;
                    }

                    @Override
                    public void permissionDenied() {
                        denay=true;
                        permissionWarningDialog();
                    }

                    @Override
                    public void permissionForeverDenied() {
                        denay=true;
                        permissionWarningDialog();
                    }

                });



        map_atlas= (ImageView) findViewById(R.id.map_atlas);
        offline_map= (ImageView) findViewById(R.id.offline_map);
        offline_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (denay)
                    Toast.makeText(getApplicationContext() , "First Allow the Permission!" , Toast.LENGTH_SHORT).show();
                else
                    startActivity(new Intent(MainMenu.this , com.example.waseem.gpss.MainActivity.class));
            }
        });

        map_atlas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (denay)
                    Toast.makeText(getApplicationContext() , "First Allow the Permission!" , Toast.LENGTH_SHORT).show();
                else
                    startActivity(new Intent(MainMenu.this , MainActivity.class));

            }
        });

    }



    @Override
    public void onBackPressed() {
        exitSystem();
    }

    public void exitSystem(){
        AlertDialog.Builder dilog=new AlertDialog.Builder(MainMenu.this);
        dilog.setMessage("You want to exit?").setTitle("Exit").setIcon(R.drawable.icon);
        dilog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                else {
                    System.exit(0);
                }
            }
        });
        dilog.setNegativeButton("Rate Us", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" +
                        getApplicationContext().getPackageName()));
                startActivity(rateIntent);
            }

        });
        dilog.show();
    }

    public void permissionWarningDialog() {

        new PromptDialog(MainMenu.this)
                .setDialogType(PromptDialog.DIALOG_TYPE_WARNING)
                .setAnimationEnable(true)
                .setTitleText(getString(R.string.pdialog_title))
                .setContentText(getString(R.string.pdialog_text))
                .setPositiveListener(getString(R.string.pdialog_setting_btn), new PromptDialog.OnPositiveListener() {
                    @Override
                    public void onClick(PromptDialog dialog) {
                        askCompactPermissions(new String[]{
                                        PermissionUtils.Manifest_ACCESS_FINE_LOCATION,
                                        PermissionUtils.Manifest_ACCESS_COARSE_LOCATION,
                                        PermissionUtils.Manifest_WRITE_EXTERNAL_STORAGE,},
                                new PermissionResult() {
                                    @Override
                                    public void permissionGranted() {
                                        denay=false;
                                    }

                                    @Override
                                    public void permissionDenied() {
                                        denay=true;
                                        permissionWarningDialog();
                                    }

                                    @Override
                                    public void permissionForeverDenied() {
                                        denay=true;
                                        permissionWarningDialog();
                                    }

                                });

                            dialog.dismiss();

                    }
                }).show();

    }

    private void Bannernative() {
        adViewMain1 = (AdView) findViewById(R.id.adViewMain1);
        adViewMain1.loadAd(new AdRequest.Builder().build());
        adViewMain2= (AdView) findViewById(R.id.adViewMain2);
        adViewMain2.loadAd(new AdRequest.Builder().build());
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);

    }
}
