package com.appscottage.offline.maphd.MyFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appscottage.offline.maphd.R;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanorama.OnStreetViewPanoramaChangeListener;
import com.google.android.gms.maps.StreetViewPanoramaView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
/**
 * Created by Jointech on 5/10/2017.
 */

public class Streetview_fragment extends Fragment {

    public  View rootView;
    double lat;
    double long1;
    public StreetViewPanoramaView mStreetViewPanoramaView;
    TextView text;

    class C07961 implements OnStreetViewPanoramaReadyCallback {

        class C07951 implements OnStreetViewPanoramaChangeListener {
            C07951() {
            }

            public void onStreetViewPanoramaChange(StreetViewPanoramaLocation streetViewPanoramaLocation) {
                if (streetViewPanoramaLocation == null || streetViewPanoramaLocation.links == null) {
                    Streetview_fragment.this.mStreetViewPanoramaView.setVisibility(View.GONE);
                    Streetview_fragment.this.text.setVisibility(View.VISIBLE);
                }
            }
        }

        C07961() {
        }

        public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
            panorama.setPosition(new LatLng(Streetview_fragment.this.lat, Streetview_fragment.this.long1));
            panorama.setOnStreetViewPanoramaChangeListener(new C07951());
        }
    }

    public Streetview_fragment() {
        setHasOptionsMenu(true);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        try {
            rootView = inflater.inflate(R.layout.streetview_fragment, container, false);
        } catch (InflateException e) {
        }
        this.text = (TextView) rootView.findViewById(R.id.text);
        this.lat = Double.parseDouble((String) WondersFragment.stringwonderarray.get(0));
        this.long1 = Double.parseDouble((String) WondersFragment.stringwonderarray.get(1));
        this.mStreetViewPanoramaView = (StreetViewPanoramaView) rootView.findViewById(R.id.steet_view_panorama);
        this.mStreetViewPanoramaView.onCreate(savedInstanceState);
        this.mStreetViewPanoramaView.getStreetViewPanoramaAsync(new C07961());
        return rootView;
    }

    public void onResume() {
        super.onResume();
        this.mStreetViewPanoramaView.onResume();
    }

    public void onPause() {
        this.mStreetViewPanoramaView.onPause();
        super.onPause();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.mStreetViewPanoramaView.onSaveInstanceState(outState);
    }



    public void onDetach() {
        super.onDetach();
    }
}
