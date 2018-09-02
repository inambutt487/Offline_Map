package com.appscottage.offline.maphd.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.appscottage.offline.maphd.R;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class AboutAdapter extends BaseAdapter {

    private Context context;

    private ArrayList<String> Stringarray;

    private static class ViewHolder {
        public final AdView adView;
        public final View rootView;
        public final TextView txt_subtext;
        public final TextView txt_text;

        private ViewHolder(View rootView, TextView txt_text, TextView txt_subtext, AdView adView) {
            this.rootView = rootView;
            this.txt_text = txt_text;
            this.txt_subtext = txt_subtext;
            this.adView = adView;
        }

        public static ViewHolder create(View rootView) {
            TextView txt_text = (TextView) rootView.findViewById(R.id.txt_text);
            TextView txt_subtext = (TextView) rootView.findViewById(R.id.txt_subtext);
            AdView adView = (AdView) rootView.findViewById(R.id.adView);

            return new ViewHolder(rootView, txt_text, txt_subtext, adView);
        }
    }

    public AboutAdapter(Context context, ArrayList<String> Stringarray) {
        this.context = context;
        this.Stringarray = Stringarray;
    }

    public int getCount() {
        return 12;
    }

    public Object getItem(int position) {
        return this.Stringarray.get(position);
    }

    public long getItemId(int position) {
        return (long) this.Stringarray.indexOf(getItem(position));
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.about_row, parent, false);
            vh = ViewHolder.create(view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        if (position == 0) {
            vh.txt_text.setText("Official Name");
            vh.txt_subtext.setText((CharSequence) this.Stringarray.get(3));
        } else if (position == 1) {
            vh.txt_text.setText("Alternative Name");
            vh.txt_subtext.setText((CharSequence) this.Stringarray.get(4));
        } else if (position == 2) {
            vh.txt_text.setText("Region");
            vh.txt_subtext.setText((CharSequence) this.Stringarray.get(5));
        } else if (position == 3) {
            vh.txt_text.setText("Sub Region");
            vh.txt_subtext.setText((CharSequence) this.Stringarray.get(6));
        } else if (position == 4) {
            vh.txt_text.setText("Currency");
            vh.txt_subtext.setText((CharSequence) this.Stringarray.get(7));
        } else if (position == 5) {
            vh.txt_text.setText("Languages");
            vh.txt_subtext.setText((CharSequence) this.Stringarray.get(8));
        } else if (position == 6) {
            vh.txt_text.setText("Population");
            vh.txt_subtext.setText((CharSequence) this.Stringarray.get(9));
        } else if (position == 7) {
            vh.txt_text.setText("Area");
            vh.txt_subtext.setText((CharSequence) this.Stringarray.get(10));
        } else if (position == 8) {
            vh.txt_text.setText("Border");
            vh.txt_subtext.setText((CharSequence) this.Stringarray.get(11));
        } else if (position == 9) {
            vh.txt_text.setText("Country Code");
            vh.txt_subtext.setText((CharSequence) this.Stringarray.get(12));
        } else if (position == 10) {
            vh.txt_text.setText("Latitude");
            vh.txt_subtext.setText((CharSequence) this.Stringarray.get(13));
        } else if (position == 11) {
            vh.txt_text.setText("Longitude");
            vh.txt_subtext.setText((CharSequence) this.Stringarray.get(14));
        }
        if (position == 5) {

        } else {
            vh.adView.setVisibility(View.GONE);
        }
        return vh.rootView;
    }
}
