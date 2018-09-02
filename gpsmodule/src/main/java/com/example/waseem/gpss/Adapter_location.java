package com.example.waseem.gpss;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter_location extends BaseAdapter {

    String[] resultz;
    Context context;
    int[] imageIdz;
    private static LayoutInflater inflater_new = null;

    public Adapter_location(findlocation mainActivity, String[] prgmNameList,
                         int[] prgmImages) {
        // TODO Auto-generated constructor stub
        resultz = prgmNameList;
        context = mainActivity;
        imageIdz = prgmImages;
        inflater_new = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return resultz.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder {
        TextView tv_new;
        ImageView img_new;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;

        rowView = inflater_new.inflate(R.layout.gpszz_row_add, null);
        holder.tv_new = (TextView) rowView.findViewById(R.id.texticon_new);
        holder.img_new = (ImageView) rowView.findViewById(R.id.tpyeicon_new);

        holder.tv_new.setText(resultz[position]);
        holder.img_new.setImageResource(imageIdz[position]);

        return rowView;
    }

}