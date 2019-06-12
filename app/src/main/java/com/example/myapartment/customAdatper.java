package com.example.myapartment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class customAdatper extends ArrayAdapter<flat>
{
    ArrayList<flat> values;

    public customAdatper(Context context, ArrayList<flat> list) {
        super(context, R.layout.flatlist,list);
        this.values = list;
    }


    @Override
    public View getView(int position,View convertView, ViewGroup parent)
    {
        LayoutInflater infater = LayoutInflater.from(getContext());

        View cview = infater.inflate(R.layout.flatlist,parent,false);

        TextView tvFlat = (TextView) cview.findViewById(R.id.tvFlat);
        TextView tvResName = (TextView) cview.findViewById(R.id.tvResName);

        tvFlat.setText(values.get(position).getFlatName());
        tvResName.setText(values.get(position).getFlatResident());

        return cview;

    }
}
