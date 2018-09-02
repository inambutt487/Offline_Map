package com.appscottage.offline.maphd.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.appscottage.offline.maphd.BuildConfig;
import com.appscottage.offline.maphd.Classes.PeakDetail;
import com.appscottage.offline.maphd.MyFragments.WondersFragment;
import com.appscottage.offline.maphd.R;
import com.appscottage.offline.maphd.StreetviewMainActivity;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PeakAdapter extends BaseAdapter {
     private Context context;

    ArrayList<PeakDetail> peaklist;

    /* renamed from: com.infomart.worldmap.Adapter.PeakAdapter.1 */
    class C04161 implements OnClickListener {
        final /* synthetic */ int val$position;

        C04161(int i) {
            this.val$position = i;
        }

        public void onClick(View v) {
            WondersFragment.stringwonderarray.clear();
            WondersFragment.stringwonderarray.add(0, ((PeakDetail) PeakAdapter.this.peaklist.get(this.val$position)).getLat());
            WondersFragment.stringwonderarray.add(1, ((PeakDetail) PeakAdapter.this.peaklist.get(this.val$position)).getLng());
            WondersFragment.stringwonderarray.add(2, ((PeakDetail) PeakAdapter.this.peaklist.get(this.val$position)).getName());
            context.startActivity(new Intent(context, StreetviewMainActivity.class));
        }
    }

    private static class ViewHolder {
        public final AdView adView;
        public final CircleImageView img_river;
        public final View rootView;
        public final TextView txt_km;
        public final TextView txt_name;

        private ViewHolder(View rootView, TextView txt_name, TextView txt_km, CircleImageView img_river, AdView adView) {
            this.rootView = rootView;
            this.txt_name = txt_name;
            this.txt_km = txt_km;

            this.img_river = img_river;
            this.adView = adView;
        }

        public static ViewHolder create(View rootView) {
            TextView txt_name = (TextView) rootView.findViewById(R.id.heading);
            TextView txt_km = (TextView) rootView.findViewById(R.id.txt);
//            TextView number = (TextView) rootView.findViewById(R.id.number);
            AdView adView = (AdView) rootView.findViewById(R.id.adView);

            return new ViewHolder(rootView, txt_name, txt_km, (CircleImageView) rootView.findViewById(R.id.image), adView);
        }
    }

    public PeakAdapter(Context context, ArrayList<PeakDetail> countrylist) {
        this.context = context;
        this.peaklist = countrylist;

    }

    public int getCount() {
        return this.peaklist.size();
    }

    public Object getItem(int position) {
        return this.peaklist.get(position);
    }

    public long getItemId(int position) {
        return (long) this.peaklist.indexOf(getItem(position));
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.single_row, parent, false);
            vh = ViewHolder.create(view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        PeakDetail country = (PeakDetail) this.peaklist.get(position);
        try {
            vh.img_river.setImageDrawable(Drawable.createFromStream(context.getAssets().open("mountain.png"), null));
        } catch (IOException e) {
        }
        vh.txt_name.setText(BuildConfig.FLAVOR + country.getName());
        vh.txt_km.setText(country.getDistance() + " m");

//        vh.txt_name.setTypeface(Constant.headingTypeface(context));

        vh.rootView.setOnClickListener(new C04161(position));
        if (position == 5) {

        } else {
            vh.adView.setVisibility(View.GONE);
        }
        return vh.rootView;
    }
}
