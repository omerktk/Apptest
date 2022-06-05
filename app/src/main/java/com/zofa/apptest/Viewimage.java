package com.zofa.apptest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Viewimage extends AppCompatActivity {


    ListView lv;

    DatabaseReference ref = FirebaseDatabase.getInstance("https://apptest-fed14-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Products");

    ArrayList<Product> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewimage);
        lv = (ListView) findViewById(R.id.listalldata);
        fetch();
    }

    public void fetch(){
        data = new ArrayList<>();

//write data ref / then addvalue / then new space enter

        ValueEventListener valueEventListener = ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot firedata : dataSnapshot.getChildren()) {
                    Product s1 = firedata.getValue(Product.class);
                    data.add(s1);
                }
                fetchdatabase f1 = new fetchdatabase(data, Viewimage.this);
                lv.setAdapter(f1);
            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }
}