package com.appscottage.offline.maphd.MyFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appscottage.offline.maphd.R;

/**
 * Created by Jointech on 5/9/2017.
 */

public class FactsFragment extends Fragment {

//    static Typeface regular;
//    static Typeface semibold;
    private TextView txtAirport;
    private TextView txtAirportValue;
    private TextView txtArea;
    private TextView txtAreaValue;
    private TextView txtBrate;
    private TextView txtBrateValue;
    private TextView txtDemographics;
    private TextView txtDrate;
    private TextView txtDrateValue;
    private TextView txtEconomy;
    private TextView txtGdp;
    private TextView txtGdpValue;
    private TextView txtGdpc;
    private TextView txtGdpcValue;
    private TextView txtLife;
    private TextView txtLifeValue;
    private TextView txtLiteracy;
    private TextView txtLiteracyValue;
    private TextView txtMage;
    private TextView txtMageValue;
    private TextView txtPopulation;
    private TextView txtPopulationValue;
    private TextView txtRailway;
    private TextView txtRailwayValue;
    private TextView txtRoadway;
    private TextView txtRoadwayValue;
    private TextView txtSratio;
    private TextView txtSratioValue;
    private TextView txtTransportation;
    private TextView txtWaterway;
    private TextView txtWaterwayValue;

    public FactsFragment() {
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.facts_fragment, container, false);
//        regular = Typeface.createFromAsset(getActivity().getAssets(), font.regular);
//        semibold = Typeface.createFromAsset(getActivity().getAssets(), font.semybold);
        findViews(rootView);
        this.txtPopulationValue.setText((CharSequence) CountriesFragment.stringcountriesarray.get(9));
        this.txtAreaValue.setText((CharSequence) CountriesFragment.stringcountriesarray.get(10));
        this.txtLifeValue.setText((CharSequence) CountriesFragment.stringcountriesarray.get(16));
        this.txtMageValue.setText((CharSequence) CountriesFragment.stringcountriesarray.get(17));
        this.txtBrateValue.setText((CharSequence) CountriesFragment.stringcountriesarray.get(18));
        this.txtDrateValue.setText((CharSequence) CountriesFragment.stringcountriesarray.get(19));
        this.txtSratioValue.setText((CharSequence) CountriesFragment.stringcountriesarray.get(20));
        this.txtLiteracyValue.setText((CharSequence) CountriesFragment.stringcountriesarray.get(21));
        this.txtRoadwayValue.setText((CharSequence) CountriesFragment.stringcountriesarray.get(22));
        this.txtRailwayValue.setText((CharSequence) CountriesFragment.stringcountriesarray.get(23));
        this.txtAirportValue.setText((CharSequence) CountriesFragment.stringcountriesarray.get(24));
        this.txtWaterwayValue.setText((CharSequence) CountriesFragment.stringcountriesarray.get(25));
        this.txtGdpValue.setText((CharSequence) CountriesFragment.stringcountriesarray.get(26));
        this.txtGdpcValue.setText((CharSequence) CountriesFragment.stringcountriesarray.get(27));
        return rootView;
    }
    private void findViews(View rootview) {
        this.txtDemographics = (TextView) rootview.findViewById(R.id.txt_demographics);
        this.txtLife = (TextView) rootview.findViewById(R.id.txt_life);
        this.txtLifeValue = (TextView) rootview.findViewById(R.id.txt_life_value);
        this.txtMage = (TextView) rootview.findViewById(R.id.txt_mage);
        this.txtMageValue = (TextView) rootview.findViewById(R.id.txt_mage_value);
        this.txtBrate = (TextView) rootview.findViewById(R.id.txt_brate);
//        this.txtBrate.setTypeface(regular);
        this.txtBrateValue = (TextView) rootview.findViewById(R.id.txt_brate_value);
//        this.txtBrateValue.setTypeface(regular);
        this.txtDrate = (TextView) rootview.findViewById(R.id.txt_drate);
//        this.txtDrate.setTypeface(regular);
        this.txtDrateValue = (TextView) rootview.findViewById(R.id.txt_drate_value);
//        this.txtDrateValue.setTypeface(regular);
        this.txtPopulation = (TextView) rootview.findViewById(R.id.txt_population);
//        this.txtPopulation.setTypeface(regular);
        this.txtPopulationValue = (TextView) rootview.findViewById(R.id.txt_population_value);
//        this.txtPopulationValue.setTypeface(regular);
        this.txtSratio = (TextView) rootview.findViewById(R.id.txt_sratio);
//        this.txtSratio.setTypeface(regular);
        this.txtSratioValue = (TextView) rootview.findViewById(R.id.txt_sratio_value);
//        this.txtSratioValue.setTypeface(regular);
        this.txtLiteracy = (TextView) rootview.findViewById(R.id.txt_literacy);
//        this.txtLiteracy.setTypeface(regular);
        this.txtLiteracyValue = (TextView) rootview.findViewById(R.id.txt_literacy_value);
//        this.txtLiteracyValue.setTypeface(regular);
        this.txtArea = (TextView) rootview.findViewById(R.id.txt_area);
//        this.txtArea.setTypeface(regular);
        this.txtAreaValue = (TextView) rootview.findViewById(R.id.txt_area_value);
//        this.txtAreaValue.setTypeface(regular);
        this.txtTransportation = (TextView) rootview.findViewById(R.id.txt_Transportation);
//        this.txtTransportation.setTypeface(semibold);
        this.txtRoadway = (TextView) rootview.findViewById(R.id.txt_roadway);
//        this.txtRoadway.setTypeface(regular);
        this.txtRoadwayValue = (TextView) rootview.findViewById(R.id.txt_roadway_value);
//        this.txtRoadwayValue.setTypeface(regular);
        this.txtRailway = (TextView) rootview.findViewById(R.id.txt_railway);
//        this.txtRailway.setTypeface(regular);
        this.txtRailwayValue = (TextView) rootview.findViewById(R.id.txt_railway_value);
//        this.txtRailwayValue.setTypeface(regular);
        this.txtAirport = (TextView) rootview.findViewById(R.id.txt_airport);
//        this.txtAirport.setTypeface(regular);
        this.txtAirportValue = (TextView) rootview.findViewById(R.id.txt_airport_value);
//        this.txtAirportValue.setTypeface(regular);
        this.txtWaterway = (TextView) rootview.findViewById(R.id.txt_waterway);
//        this.txtWaterway.setTypeface(regular);
        this.txtWaterwayValue = (TextView) rootview.findViewById(R.id.txt_waterway_value);
//        this.txtWaterwayValue.setTypeface(regular);
        this.txtEconomy = (TextView) rootview.findViewById(R.id.txt_Economy);
//        this.txtEconomy.setTypeface(semibold);
        this.txtGdp = (TextView) rootview.findViewById(R.id.txt_gdp);
//        this.txtGdp.setTypeface(regular);
        this.txtGdpValue = (TextView) rootview.findViewById(R.id.txt_gdp_value);
//        this.txtGdpValue.setTypeface(regular);
        this.txtGdpc = (TextView) rootview.findViewById(R.id.txt_gdpc);
//        this.txtGdpc.setTypeface(regular);
        this.txtGdpcValue = (TextView) rootview.findViewById(R.id.txt_gdpc_value);
//        this.txtGdpcValue.setTypeface(regular);
    }

}
