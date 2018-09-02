package com.appscottage.offline.maphd.MyFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.appscottage.offline.maphd.Adapter.PeakAdapter;
import com.appscottage.offline.maphd.Classes.PeakData;
import com.appscottage.offline.maphd.Classes.PeakDetail;
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

public class PeakFragment extends Fragment {
    PeakAdapter adapter;
    GsonUtils gsonUtils;
    ListView listView;
    ArrayList<PeakDetail> peakarray;

    public PeakFragment() {
        this.peakarray = new ArrayList();
        setHasOptionsMenu(true);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.perkfragment, container, false);
        GsonUtils gsonUtils = this.gsonUtils;
        this.gsonUtils = GsonUtils.getInstance();
        findviewbyid(rootView);
        try {
            PeakData pd =  this.gsonUtils.getGson().fromJson(String.valueOf(new JSONObject(loadJSONFromAsset())), PeakData.class);
            this.peakarray.clear();
            this.peakarray.addAll(pd.getPeak());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.adapter = new PeakAdapter(getActivity(), this.peakarray);
        this.listView.setAdapter(this.adapter);
        return rootView;
    }

    public String loadJSONFromAsset() {
        try {
            InputStream is = getActivity().getAssets().open("peak.json");
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

    public void findviewbyid(View rootView) {
        this.listView = (ListView) rootView.findViewById(R.id.listview);
    }

    public void onResume() {
        super.onResume();
    }
}
