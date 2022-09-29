package com.zofa.apptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class tpanel extends AppCompatActivity {
    MaterialButton btn,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpanel);
        btn = (MaterialButton) findViewById(R.id.logout);
        btn2 = (MaterialButton) findViewById(R.id.gallery);

        btn.setOnClickListener(view -> {

            Intent intent = new Intent(tpanel.this, TeaMarkDataUpload.class);
            startActivity(intent);

        });

        //button 2 change screen onclick
        btn2.setOnClickListener(view -> {
            Intent intent = new Intent(tpanel.this, TeaMarkList2.class);
            startActivity(intent);
        });

    }
}