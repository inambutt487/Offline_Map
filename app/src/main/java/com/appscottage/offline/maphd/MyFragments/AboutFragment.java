package com.appscottage.offline.maphd.MyFragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.appscottage.offline.maphd.Adapter.AboutAdapter;
import com.appscottage.offline.maphd.R;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jointech on 5/9/2017.
 */

public class AboutFragment extends Fragment{

    AboutAdapter adapter;
    TextView capital;
    TextView continent;
    TextView countries;
    CircleImageView flag;
    ListView listView;
    TextView text_capital;
    TextView text_continent;
    TextView text_countries;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.aboutfragment, container, false);
        findviewbyid(rootView);
        this.adapter = new AboutAdapter(getActivity(), CountriesFragment.stringcountriesarray);
        this.listView.setAdapter(this.adapter);
        this.countries.setText((CharSequence) CountriesFragment.stringcountriesarray.get(0));
        this.capital.setText((CharSequence) CountriesFragment.stringcountriesarray.get(1));
        this.continent.setText((CharSequence) CountriesFragment.stringcountriesarray.get(2));
        try {
            this.flag.setImageDrawable(Drawable.createFromStream(getActivity().getAssets().open("flag/" + ((String) CountriesFragment.stringcountriesarray.get(15)) + ".png"), null));
        } catch (IOException e) {

        }
        return rootView;
    }

    public void findviewbyid(View rootView) {
        this.listView = (ListView) rootView.findViewById(R.id.listview);
        this.countries = (TextView) rootView.findViewById(R.id.country);
        this.capital = (TextView) rootView.findViewById(R.id.capital);
        this.continent = (TextView) rootView.findViewById(R.id.continent);
        this.text_countries = (TextView) rootView.findViewById(R.id.text_country);
        this.text_capital = (TextView) rootView.findViewById(R.id.text_capital);
        this.text_continent = (TextView) rootView.findViewById(R.id.text_continent);
        this.flag = (CircleImageView) rootView.findViewById(R.id.flag);

    }

    public void onResume() {
        super.onResume();
    }
}
