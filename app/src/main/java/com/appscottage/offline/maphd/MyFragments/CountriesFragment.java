package com.appscottage.offline.maphd.MyFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.appscottage.offline.maphd.Adapter.CountriesAdapter;
import com.appscottage.offline.maphd.Classes.CounriesDetail;
import com.appscottage.offline.maphd.Classes.CountriesData;
import com.appscottage.offline.maphd.R;
import com.appscottage.offline.maphd.Utils.GsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Jointech on 5/9/2017.
 */

public class CountriesFragment extends Fragment {

    public CountriesFragment() {
        this.searchView = null;
        setHasOptionsMenu(true);
    }
    static {
        countriesarray = new ArrayList();
        stringcountriesarray = new ArrayList();
    }

    public static ArrayList<CounriesDetail> countriesarray;
    public static ArrayList<String> stringcountriesarray;
    CountriesAdapter adapter;
    GsonUtils gsonUtils;
    ListView listView;
    private OnQueryTextListener queryTextListener;
    private SearchView searchView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.countriesfragment, container, false);
        GsonUtils gsonUtils = this.gsonUtils;

        this.gsonUtils = GsonUtils.getInstance();
        findviewbyid(rootView);
        try {
            CountriesData lsd = (CountriesData) this.gsonUtils.getGson().fromJson(String.valueOf(new JSONObject(loadJSONFromAsset())), CountriesData.class);
            countriesarray.clear();
            countriesarray.addAll(lsd.getCountrydetail());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.adapter = new CountriesAdapter(getActivity(), countriesarray);
        this.adapter.sortByQuantityDesc();
        this.listView.setAdapter(this.adapter);
        return rootView;
    }

    public void findviewbyid(View rootView) {
        this.listView = (ListView) rootView.findViewById(R.id.listview);
    }


    public String loadJSONFromAsset() {
        try {
            InputStream is = getActivity().getAssets().open("country_updated.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            return json;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }




    class OnSearchListener implements OnQueryTextListener {
        OnSearchListener() {
        }

        public boolean onQueryTextChange(String newText) {
            CountriesFragment.this.adapter.getFilter().filter(newText);
            return true;
        }

        public boolean onQueryTextSubmit(String query) {
            CountriesFragment.this.adapter.getFilter().filter(query);
            CountriesFragment.this.searchView.clearFocus();
            return true;
        }
    }
}
