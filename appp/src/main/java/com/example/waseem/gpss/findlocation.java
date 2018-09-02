package com.example.waseem.gpss;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class findlocation extends Activity {

    GridView grdvw;
    Uri gmmIntentUri;
    Intent mapIntent_new;
    InterstitialAd mInterstitialAd;
    public static String[] prgmNameList = {

            "Hospital", "Hotel", "Mosque", "Airport",
            "Railway Station", "Bus Stand", "Bank", "ATM",
            "Post Office", "School", "College", "University",
            "Fire Station", "Police Station", "Service Station", "Backers",
            "Beauty Salon", "Book Shops", "Clubs", "cafes",
            "Court House", "Car Repair", "Car Rental", "Doctors",
            "Furniture", "Fast Food", "Gyms", "Health",
            "Dentist", "laundry", "lawyer", "Markets",
            "Museums", "Pharmacy", "Parking", "Restaurant",
            "Travel Agent", "Zoo", "Rent A Car", "Hostel",
            "patrol pump", "Car Wash", "Plumber", "Electrician",
            "Electronic Store", "Hindu Temples", "Pet Shop", "Stadium",
            "Mobile Shop", "Casino", "Software House", "Movie theater",
            "Shoping Mall", "Taxi Stand", " Water Supplier", "Academy",
            "Talor shop"


    };

    public static int[] prgmImages = {

            R.drawable.hospital, R.drawable.hotel, R.drawable.mosque, R.drawable.airport,
            R.drawable.railwaystation, R.drawable.busstation, R.drawable.bank, R.drawable.atm,
            R.drawable.postoffice, R.drawable.school, R.drawable.college, R.drawable.university,
            R.drawable.firestation, R.drawable.policestation, R.drawable.b, R.drawable.bakery,
            R.drawable.beautysaloon, R.drawable.bookshop, R.drawable.club, R.drawable.cafe,
            R.drawable.court, R.drawable.carrepair, R.drawable.carrental, R.drawable.doctor,
            R.drawable.furniture, R.drawable.fastfood, R.drawable.gym, R.drawable.health,
            R.drawable.dentist, R.drawable.laundry, R.drawable.lawyer, R.drawable.market,
            R.drawable.meseum, R.drawable.pharmacy, R.drawable.parking, R.drawable.restaurant,
            R.drawable.travelagent, R.drawable.zoo, R.drawable.carrental, R.drawable.hostel,
            R.drawable.petrolpump, R.drawable.carwash, R.drawable.plumber, R.drawable.electrician,
            R.drawable.electronicstore, R.drawable.hindutemple, R.drawable.petshop, R.drawable.stadium,
            R.drawable.mobileshop, R.drawable.casino, R.drawable.softwarehouse, R.drawable.movietheater,
            R.drawable.shoppingmall, R.drawable.taxistand, R.drawable.watersupplier, R.drawable.academy,
            R.drawable.b

    };

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findlocation);

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


        grdvw = (GridView) findViewById(R.id.gridViewz);
        grdvw.setAdapter(new Adapter_location(this, prgmNameList,
                prgmImages));

        grdvw.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {


                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {

                    switch (position) {

                        case 0:
                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=hospitals");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);


                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;
                            }

                        case 1:
                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            }

                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=hotels");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;

                        case 2:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {


                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=mosques");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;
                            }
                        case 3:
                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            }
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=airports");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;
                        case 4:
                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=railway stations");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }
                        case 5:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            }
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=bus stands");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;
                        case 6:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {


                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=banks");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;
                            }

                        case 7:
                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            }
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=atms");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;
                        case 8:


                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=post offices");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }
                        case 9:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=schools");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;
                        case 10:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=colleges");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;
                            }
                        case 11:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=universities");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;
                        case 12:


                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=fire stations");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }
                        case 13:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=police stations");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;

                        case 14:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=service station");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }

                        case 15:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=bakerys");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;
                        case 16:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {


                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=salons");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }
                        case 17:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=book stores");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;
                        case 18:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=clubs");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }
                        case 19:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=cafes");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;
                        case 20:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=court houses");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }
                        case 21:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=car repair");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;
                        case 22:


                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=car rental");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }
                        case 23:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=doctors");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;
                        case 24:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=furniture store");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }
                        case 25:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=food");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;
                        case 26:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=gyms");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }
                        case 27:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=health");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;


                        case 28:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=dentist");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }


                        case 29:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=laundry");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;
                        case 30:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=lawyers");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }
                        case 31:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=markets");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;
                        case 32:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=museums");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }
                        case 33:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=pharmacy");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;
                        case 34:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=parking");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }

                        case 35:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=restaurants");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;
                        case 36:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=travel agency");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }


                        case 37:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=zoo");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;


                        case 38:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=rent a car");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }


                        case 39:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=hostel");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;


                        case 40:


                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=patrol pump");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new


                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }
                        case 41:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=car wash");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;
                        case 42:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=plumber");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;
                            }

                        case 43:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=Electrician");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;
                        case 44:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=Electronic store");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }
                        case 45:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=Hindu Temples");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;
                        case 46:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=Pet Shops");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }
                        case 47:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=Stadium");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;


                        case 48:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=Mobile Shops");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }
                        case 49:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=Casino");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;
                        case 50:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=Software House");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }
                        case 51:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=Movie theater");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;


                        case 52:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=Shoping Malls");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }
                        case 53:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=Taxi stand");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;
                        case 54:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=Water filter supplier");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;

                            }
                        case 55:
                            findlocation.this.gmmIntentUri = Uri
                                    .parse("geo:0,0?q=Academy");
                            findlocation.this.mapIntent_new = new Intent(
                                    "android.intent.action.VIEW",
                                    findlocation.this.gmmIntentUri);
                            findlocation.this.mapIntent_new
                                    .setPackage("com.google.android.apps.maps");
                            findlocation.this
                                    .startActivity(findlocation.this.mapIntent_new);
                            return;

                        case 56:

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {

                                findlocation.this.gmmIntentUri = Uri
                                        .parse("geo:0,0?q=Talor shop");
                                findlocation.this.mapIntent_new = new Intent(
                                        "android.intent.action.VIEW",
                                        findlocation.this.gmmIntentUri);
                                findlocation.this.mapIntent_new
                                        .setPackage("com.google.android.apps.maps");
                                findlocation.this
                                        .startActivity(findlocation.this.mapIntent_new);
                                return;
                            }

                    }
                }
            }
        });
    }


    private void BannerAdmob() {
        // TODO Auto-generated method stub
        AdView adView = (AdView) this.findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());
    }

    private void requestNewInterstitial() {
        // TODO Auto-generated method stub
        AdRequest adRequest = new AdRequest.Builder().build();

        mInterstitialAd.loadAd(adRequest);

    }


}