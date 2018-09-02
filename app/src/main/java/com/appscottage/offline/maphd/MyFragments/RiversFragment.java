package com.appscottage.offline.maphd.MyFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.appscottage.offline.maphd.Adapter.RiverAdapter;
import com.appscottage.offline.maphd.Classes.RiverData;
import com.appscottage.offline.maphd.Classes.RiverDetail;
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

public class RiversFragment extends Fragment {
    RiverAdapter adapter;
    GsonUtils gsonUtils;
    ListView listView;
    ArrayList<RiverDetail> riverarray;

    public RiversFragment() {
        this.riverarray = new ArrayList();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.riversfragment, container, false);
        GsonUtils gsonUtils = this.gsonUtils;
        this.gsonUtils = GsonUtils.getInstance();
        findviewbyid(rootView);
        try {
            RiverData RD = (RiverData) this.gsonUtils.getGson().fromJson(String.valueOf(new JSONObject(loadJSONFromAsset())), RiverData.class);
            this.riverarray.clear();
            this.riverarray.addAll(RD.getRiver());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.adapter = new RiverAdapter(getActivity(), this.riverarray);
        this.listView.setAdapter(this.adapter);
        return rootView;
    }

    public String loadJSONFromAsset() {
        try {
            InputStream is = getActivity().getAssets().open("river.json");
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    public void findviewbyid(View rootView) {
        this.listView = (ListView) rootView.findViewById(R.id.listview);
    }
}
