package com.example.b312967.quizapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by b312967 on 23.12.2015..
 */
public class Adapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater mInflater;

    public Adapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return DataStorage.listViewData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = mInflater.inflate(R.layout.list_view_single_item, parent, false);

        final TextView ime = (TextView) convertView.findViewById(R.id.name);
        final ImageView imageTmb = (ImageView) convertView.findViewById(R.id.image_tmb);
        final Kategorija kategorija = DataStorage.listViewData.get(position);
        Log.d("Scroll", String.valueOf(position));
        ime.setText(kategorija.getIme());
        imageTmb.setImageResource(kategorija.getTmbImageId(mContext));

        return convertView;
    }
}
