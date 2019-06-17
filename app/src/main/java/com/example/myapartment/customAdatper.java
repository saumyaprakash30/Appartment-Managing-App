package com.example.myapartment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class customAdatper extends ArrayAdapter<flat>
{
    ArrayList<flat> values;
    Context c;

    public customAdatper(Context context, ArrayList<flat> list) {
        super(context, R.layout.flatlist,list);
        this.c = context;
        this.values = list;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        //LayoutInflater infater = LayoutInflater.from(getContext());

        LayoutInflater infater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View cview = infater.inflate(R.layout.flatlist,parent,false);

        TextView tvFlat = (TextView) cview.findViewById(R.id.tvFlat);
        TextView tvResName = (TextView) cview.findViewById(R.id.tvResName);


        tvFlat.setText(values.get(position).getFlatName());
        tvResName.setText(values.get(position).getFlatResident());




        return cview;

    }

}
