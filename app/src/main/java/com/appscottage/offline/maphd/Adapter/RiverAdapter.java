package com.appscottage.offline.maphd.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.appscottage.offline.maphd.BuildConfig;
import com.appscottage.offline.maphd.Classes.RiverDetail;
import com.appscottage.offline.maphd.MyFragments.WondersFragment;
import com.appscottage.offline.maphd.R;
import com.appscottage.offline.maphd.StreetviewMainActivity;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RiverAdapter extends BaseAdapter {
    static Context context;
    static Typeface regular;
    static Typeface semibold;
    ArrayList<RiverDetail> riverlist;

    /* renamed from: com.infomart.worldmap.Adapter.RiverAdapter.1 */
    class C04171 implements OnClickListener {
        final /* synthetic */ int val$position;

        C04171(int i) {
            this.val$position = i;
        }

        public void onClick(View v) {
            WondersFragment.stringwonderarray.clear();
            WondersFragment.stringwonderarray.add(0, ((RiverDetail) RiverAdapter.this.riverlist.get(this.val$position)).getLat());
            WondersFragment.stringwonderarray.add(1, ((RiverDetail) RiverAdapter.this.riverlist.get(this.val$position)).getLng());
            WondersFragment.stringwonderarray.add(2, ((RiverDetail) RiverAdapter.this.riverlist.get(this.val$position)).getName());
            RiverAdapter.context.startActivity(new Intent(RiverAdapter.context, StreetviewMainActivity.class));
        }
    }

    private static class ViewHolder {
        public final AdView adView;
        public final CircleImageView img_river;

        public final View rootView;
        public final TextView txt_km;
        public final TextView txt_name;

        private ViewHolder(View rootView, TextView txt_name, TextView txt_km, CircleImageView img_river,  AdView adView) {
            this.rootView = rootView;
            this.txt_name = txt_name;
            this.txt_km = txt_km;

            this.img_river = img_river;
            this.adView = adView;
        }

        public static ViewHolder create(View rootView) {
            TextView txt_name = (TextView) rootView.findViewById(R.id.heading);
            TextView txt_km = (TextView) rootView.findViewById(R.id.txt);

            AdView adView = (AdView) rootView.findViewById(R.id.adView);
            txt_name.setTypeface(RiverAdapter.semibold);
            txt_km.setTypeface(RiverAdapter.regular);
            return new ViewHolder(rootView, txt_name, txt_km, (CircleImageView) rootView.findViewById(R.id.image),  adView);
        }
    }

    public RiverAdapter(Context context, ArrayList<RiverDetail> riverlist) {
        this.context = context;
        this.riverlist = riverlist;
    }

    public int getCount() {
        return this.riverlist.size();
    }

    public Object getItem(int position) {
        return this.riverlist.get(position);
    }

    public long getItemId(int position) {
        return (long) this.riverlist.indexOf(getItem(position));
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.single_row, parent, false);
            vh = ViewHolder.create(view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        RiverDetail country = (RiverDetail) this.riverlist.get(position);
        try {
            vh.img_river.setImageDrawable(Drawable.createFromStream(context.getAssets().open("river.png"), null));
        } catch (IOException e) {
        }
        vh.txt_name.setText(BuildConfig.FLAVOR + country.getName());
        vh.txt_km.setText(country.getDistance() + " km");
//        vh.txt_name.setTypeface(Constant.headingTypeface(context));

        vh.rootView.setOnClickListener(new C04171(position));
        if (position == 5) {

        } else {
            vh.adView.setVisibility(View.GONE);
        }
        return vh.rootView;
    }
}
