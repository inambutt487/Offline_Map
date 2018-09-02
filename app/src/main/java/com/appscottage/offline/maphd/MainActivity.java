package com.appscottage.offline.maphd;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.appscottage.offline.maphd.MyFragments.CountriesFragment;
import com.appscottage.offline.maphd.MyFragments.PeakFragment;
import com.appscottage.offline.maphd.MyFragments.RiversFragment;
import com.appscottage.offline.maphd.MyFragments.WondersFragment;
import com.appscottage.offline.maphd.Utils.Constant;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AdView adViewMain5;
    InterstitialAd mInterstitialAd;
    View view1, view2, view3, view4;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (Constant.isNetworkAvailable(getApplicationContext())) {
            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId("ca-app-pub-8002008697356859/8559359641");
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
            mInterstitialAd.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    }
                }
            });

            adViewMain5 = (AdView) findViewById(R.id.adViewMain5);
            adViewMain5.loadAd(new AdRequest.Builder().build());
            adViewMain5.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    adViewMain5.setVisibility(View.VISIBLE);
                }
            });
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.onBackPressed();
            }
        });
        view1 = getLayoutInflater().inflate(R.layout.customtab, null);
        view1.findViewById(R.id.icon).setBackgroundResource(R.drawable.country_icon);
        view2 = getLayoutInflater().inflate(R.layout.customtab, null);
        view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.river_icon);
        view3 = getLayoutInflater().inflate(R.layout.customtab, null);
        view3.findViewById(R.id.icon).setBackgroundResource(R.drawable.peak_icon);
        view4 = getLayoutInflater().inflate(R.layout.customtab, null);
        view4.findViewById(R.id.icon).setBackgroundResource(R.drawable.wonders);

        this.tabLayout = (TabLayout) findViewById(R.id.tabs);
        this.viewPager = (ViewPager) findViewById(R.id.viewpager);

        setupViewPager(this.viewPager);
        this.viewPager.setOffscreenPageLimit(View.INVISIBLE);
        this.tabLayout.setupWithViewPager(this.viewPager);

        setupTabIcons();

    }

    private void setupTabIcons() {

        tabLayout.getTabAt(0).setCustomView(view1);
        tabLayout.getTabAt(1).setCustomView(view2);
        tabLayout.getTabAt(2).setCustomView(view3);
        tabLayout.getTabAt(3).setCustomView(view4);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new CountriesFragment(), "");
        adapter.addFrag(new RiversFragment(), "");
        adapter.addFrag(new PeakFragment(), "");
        adapter.addFrag(new WondersFragment(), "");
        viewPager.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList;
        private final List<String> mFragmentTitleList;

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
            this.mFragmentList = new ArrayList();
            this.mFragmentTitleList = new ArrayList();
        }

        public Fragment getItem(int position) {
            return (Fragment) this.mFragmentList.get(position);
        }

        public int getCount() {
            return this.mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            this.mFragmentList.add(fragment);
            this.mFragmentTitleList.add(title);
        }

        public CharSequence getPageTitle(int position) {
            return (CharSequence) this.mFragmentTitleList.get(position);
        }
    }

}
