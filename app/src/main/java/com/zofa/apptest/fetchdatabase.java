package com.zofa.apptest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class fetchdatabase extends BaseAdapter {


    //array with model



    ArrayList<Product> data;
    Context context;

    String Sref = "gs://apptest-fed14.appspot.com/images";

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


      String imagename = "https://firebasestorage.googleapis.com/v0/b/apptest-fed14.appspot.com/o/images%2F"+data.get(position).pimage+"?alt=media";

        pname.setText(data.get(position).pname);
        pdetails.setText(data.get(position).pdetail);



        //display photos from database (tell me how to make it work)
        Glide.with(this.context).load(imagename).into(pimage);



        //display photos by link (working)
//       Glide.with(this.context).load("https://i.stack.imgur.com/4NTnO.png").into(pimage);





        return root;
    }
}
