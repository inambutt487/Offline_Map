package com.appscottage.offline.maphd.MyFragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.appscottage.offline.maphd.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jointech on 5/10/2017.
 */

public class StreetviewmainFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    UnityAds unityAds;
    AlertDialog dialog;
    View rootView;
    InterstitialAd mInterstitialAd;
    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList;
        private final List<String> mFragmentTitleList;

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
            this.mFragmentList = new ArrayList();
            this.mFragmentTitleList = new ArrayList();
        }

        public Fragment getItem(int position) {
            return this.mFragmentList.get(position);
        }

        public int getCount() {
            return this.mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            this.mFragmentList.add(fragment);
            this.mFragmentTitleList.add(title);
        }

        public CharSequence getPageTitle(int position) {
            return  this.mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.fragment_streetview_main, container, false);

        final UnityAdsListener unityAdsListener = new UnityAdsListener();
        unityAds=new UnityAds();
        unityAds.initialize(getActivity(), "1591373", unityAdsListener);
        mInterstitialAd = new InterstitialAd(getActivity());

        /*from Main menu*/
        mInterstitialAd.setAdUnitId("ca-app-pub-3444255945927869/5251348649");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {

            }
        });
        requestNewInterstitial();
        this.viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        setupViewPager(this.viewPager);
        this.viewPager.setOffscreenPageLimit(2);
        this.tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        this.tabLayout.setupWithViewPager(this.viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition())
                {

                    case 1:
                    {
                        Alertdialog1();
                        rootView.setVisibility(View.GONE);
                        break;
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return rootView;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new simpleMap_fragment(), "Map");
        adapter.addFrag(new Streetview_fragment(), "Streetview");
        viewPager.setAdapter(adapter);
    }
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);

    }
    private class UnityAdsListener implements IUnityAdsListener {
        @Override
        public void onUnityAdsReady(String s) {

        }

        @Override
        public void onUnityAdsStart(String s) {
            //Called when a video begins playing
        }

        @Override
        public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {


        }

        @Override
        public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
            //Called when the Unity Ads detects an error
        }
    }
    public  void Alertdialog()
    {
        Toast.makeText(getActivity(),"Street View for this city is not Available...",Toast.LENGTH_LONG).show();
        dialog=    new AlertDialog.Builder(getActivity())
//                .setIcon(R.drawable.iconmap)
                .setTitle("Live Street View is not Available")

                .setMessage("Watch Ad to see the other map views of this city ")
                .setPositiveButton("WATCH AD", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (unityAds.isReady()) {
                            unityAds.show(getActivity(),"video");
                        }
                        else
                        {
                            if(mInterstitialAd.isLoaded())
                            {
                                mInterstitialAd.show();
                            }
                            else
                            {
                                /*MainMenu.this.finish();
                                System.exit(0);*/
                            }
                        }
                        rootView.setVisibility(View.VISIBLE);
                    }

                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        getActivity().finish();
                    }
                })
                .setCancelable(false)
                .show();


        // if you do the following it will be left aligned, doesn't look
        // correct
        // button.setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.ic_media_play,
        // 0, 0, 0);

        /*Button button = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        button.setCompoundDrawablesWithIntrinsicBounds(this.getResources().getDrawable(
                R.drawable.iconmap), null, null, null);*/
        TextView textView = (TextView) dialog.findViewById(android.R.id.message);
        textView.setTextSize(20);
    }
    public  void Alertdialog1()
    {

        dialog=    new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.icon)
                .setTitle("Watch AD")

                .setMessage("Watch Ad to see Street view ")
                .setPositiveButton("WATCH AD", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (unityAds.isReady()) {
                            unityAds.show(getActivity(),"video");
                        }
                        else
                        {
                            if(mInterstitialAd.isLoaded())
                            {
                                mInterstitialAd.show();
                            }
                            else
                            {
                                /*MainMenu.this.finish();
                                System.exit(0);*/
                            }
                        }
                        rootView.setVisibility(View.VISIBLE);
                    }

                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getActivity().finish();
                    }
                })
                .setCancelable(false)
                .show();


        // if you do the following it will be left aligned, doesn't look
        // correct
        // button.setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.ic_media_play,
        // 0, 0, 0);

        /*Button button = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        button.setCompoundDrawablesWithIntrinsicBounds(this.getResources().getDrawable(
                R.drawable.iconmap), null, null, null);*/
        TextView textView = (TextView) dialog.findViewById(android.R.id.message);
        textView.setTextSize(20);
    }
}
