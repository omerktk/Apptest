package com.zofa.apptest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class fetchdatabase extends BaseAdapter {


    //array with model



    ArrayList<Product> data;
    Context context;


    //alt insert to create contractor
    public fetchdatabase(ArrayList<Product> data, Context context) {
        this.data = data;
        this.context = context;

    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    //write in this
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = LayoutInflater.from(context).inflate(R.layout.listview12, null);

        TextView pname = root.findViewById(R.id.textView1);
        TextView pdetails = root.findViewById(R.id.textView2);
        ImageView pimage = root.findViewById(R.id.imageView23);

        pname.setText(data.get(position).pname());
        pdetails.setText(data.get(position).pdetail());




        return root;
    }
}
