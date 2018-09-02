package com.appscottage.offline.maphd.MyFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.appscottage.offline.maphd.Adapter.WonderAdapter;
import com.appscottage.offline.maphd.Classes.WondersData;
import com.appscottage.offline.maphd.Classes.WondersDetail;
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

public class WondersFragment extends Fragment{

    public static ArrayList<String> stringwonderarray;
    public static ArrayList<WondersDetail> wonderarray;
    WonderAdapter adapter;
    GsonUtils gsonUtils;
    ListView listView;
    static {
        wonderarray = new ArrayList();
        stringwonderarray = new ArrayList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.wondersfragment, container, false);
        GsonUtils gsonUtils = this.gsonUtils;
        this.gsonUtils = GsonUtils.getInstance();
        findviewbyid(rootView);
        try {
            WondersData wd =  this.gsonUtils.getGson().fromJson(String.valueOf(new JSONObject(loadJSONFromAsset())), WondersData.class);
            wonderarray.clear();
            wonderarray.addAll(wd.getWonder());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.adapter = new WonderAdapter(getActivity(), wonderarray);
        this.listView.setAdapter(this.adapter);
        return rootView;
    }

    public String loadJSONFromAsset() {
        try {
            InputStream is = getActivity().getAssets().open("wonder.json");
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

}
