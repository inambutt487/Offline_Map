package com.appscottage.offline.maphd.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.appscottage.offline.maphd.Classes.CounriesDetail;
import com.appscottage.offline.maphd.CountriesDetailActivity;
import com.appscottage.offline.maphd.MyFragments.CountriesFragment;
import com.appscottage.offline.maphd.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import de.hdodenhof.circleimageview.CircleImageView;

public class CountriesAdapter extends BaseAdapter implements Filterable {
    Context context;
    ArrayList<CounriesDetail> countrylist;
    ArrayList<CounriesDetail> mStringFilterList;
    ValueFilter valueFilter;

    public CountriesAdapter(Context context, ArrayList<CounriesDetail> countrylist) {
        this.context = context;
        this.countrylist = countrylist;
        this.mStringFilterList = countrylist;


    }


    private class C04141 implements Comparator<CounriesDetail> {
        public int compare(CounriesDetail object1, CounriesDetail object2) {
            return object1.getName().compareTo(object2.getName());
        }
    }

     private class itemClick implements OnClickListener {
        final  int val$position;

        itemClick(int i) {
            this.val$position = i;
        }

        public void onClick(View v) {
            CountriesFragment.stringcountriesarray.clear();
            CountriesFragment.stringcountriesarray.add(0, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getName());
            CountriesFragment.stringcountriesarray.add(1, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getCapital());
            CountriesFragment.stringcountriesarray.add(2, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getContinent());
            CountriesFragment.stringcountriesarray.add(3, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getOfficialName());
            CountriesFragment.stringcountriesarray.add(4, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getAlternativeName());
            CountriesFragment.stringcountriesarray.add(5, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getRegion());
            CountriesFragment.stringcountriesarray.add(6, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getSubRegion());
            CountriesFragment.stringcountriesarray.add(7, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getCurrency());
            CountriesFragment.stringcountriesarray.add(8, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getLanguages());
            CountriesFragment.stringcountriesarray.add(9, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getPopulation());
            CountriesFragment.stringcountriesarray.add(10, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getArea());
            CountriesFragment.stringcountriesarray.add(11, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getBorder());
            CountriesFragment.stringcountriesarray.add(12, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getPhone());
            CountriesFragment.stringcountriesarray.add(13, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getLat());
            CountriesFragment.stringcountriesarray.add(14, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getLng());
            CountriesFragment.stringcountriesarray.add(15, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getCode());
            CountriesFragment.stringcountriesarray.add(16, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getLife());
            CountriesFragment.stringcountriesarray.add(17, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getAge());
            CountriesFragment.stringcountriesarray.add(18, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getBirth());
            CountriesFragment.stringcountriesarray.add(19, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getDeath());
            CountriesFragment.stringcountriesarray.add(20, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getSexratio());
            CountriesFragment.stringcountriesarray.add(21, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getLiteracy());
            CountriesFragment.stringcountriesarray.add(22, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getRoadways());
            CountriesFragment.stringcountriesarray.add(23, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getRailway());
            CountriesFragment.stringcountriesarray.add(24, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getAirports());
            CountriesFragment.stringcountriesarray.add(25, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getWaterways());
            CountriesFragment.stringcountriesarray.add(26, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getGDP());
            CountriesFragment.stringcountriesarray.add(27, ((CounriesDetail) CountriesAdapter.this.countrylist.get(this.val$position)).getGdpPerCapita());
            CountriesAdapter.this.context.startActivity(new Intent(CountriesAdapter.this.context, CountriesDetailActivity.class));
//            Toast.makeText(context, "OnClickListener "+ val$position ,Toast.LENGTH_SHORT).show();
        }
    }

    private class ValueFilter extends Filter {
        private ValueFilter() {
        }

        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() <= 0) {
                results.count = CountriesAdapter.this.mStringFilterList.size();
                results.values = CountriesAdapter.this.mStringFilterList;
            } else {
                ArrayList<CounriesDetail> filterList = new ArrayList();
                for (int i = 0; i < CountriesAdapter.this.mStringFilterList.size(); i++) {
                    if (((CounriesDetail) CountriesAdapter.this.mStringFilterList.get(i)).getName().toUpperCase().contains(constraint.toString().toUpperCase())) {
                        filterList.add((CounriesDetail) CountriesAdapter.this.mStringFilterList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            }
            return results;
        }

        protected void publishResults(CharSequence constraint, FilterResults results) {
            CountriesAdapter.this.countrylist = (ArrayList) results.values;
            CountriesAdapter.this.notifyDataSetChanged();
        }
    }

    private static class ViewHolder {
        public final AdView adView;
        public final CircleImageView img_coutries;
        public final View rootView;
        public final TextView txt_capital;
        public final TextView txt_countries;

        private ViewHolder(View rootView, TextView txt_countries, TextView txt_capital, CircleImageView img_coutries, AdView adView) {
            this.rootView = rootView;
            this.txt_countries = txt_countries;
            this.txt_capital = txt_capital;
            this.img_coutries = img_coutries;
            this.adView = adView;
        }

        public static ViewHolder create(View rootView) {
            TextView txt_countries = (TextView) rootView.findViewById(R.id.heading);
            TextView txt_capital = (TextView) rootView.findViewById(R.id.txt);
            AdView adView = (AdView) rootView.findViewById(R.id.adView);
          return new ViewHolder(rootView, txt_countries, txt_capital, (CircleImageView) rootView.findViewById(R.id.image), adView);
        }
    }


    public void sortByQuantityDesc() {
        Collections.sort(this.countrylist, new C04141());
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.countrylist.size();
    }

    public Object getItem(int position) {
        return this.countrylist.get(position);
    }

    public long getItemId(int position) {
        return (long) this.countrylist.indexOf(getItem(position));
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
        CounriesDetail country = this.countrylist.get(position);
        try {
            vh.img_coutries.setImageDrawable(Drawable.createFromStream(context.getAssets().open("flag/" + country.getCode() + ".png"), null));
        } catch (IOException e) {
        }
        vh.txt_countries.setText(country.getName());
        vh.txt_capital.setText("\u00a9" + country.getCapital());
//        vh.txt_countries.setTypeface(Constant.headingTypeface(context));
//        vh.txt_capital.setTypeface(Constant.simpleTypeface(context));
        vh.rootView.setOnClickListener(new itemClick(position));
        int count = position + 1;
        if (count % 20 != 0 || count <= 0) {
            vh.adView.setVisibility(View.GONE);
        } else {
            vh.adView.setVisibility(View.VISIBLE);
            AdRequest adRequest = new AdRequest.Builder()
                    .build();
            vh.adView.loadAd(adRequest);
        }
        return vh.rootView;
    }

    public Filter getFilter() {
        if (this.valueFilter == null) {
            this.valueFilter = new ValueFilter();
        }
        return this.valueFilter;
    }
}
