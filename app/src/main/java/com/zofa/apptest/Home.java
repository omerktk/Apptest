package com.zofa.apptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    MaterialButton btn,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn = (MaterialButton) findViewById(R.id.logout);
        btn2 = (MaterialButton) findViewById(R.id.gallery);

        btn.setOnClickListener(view -> {

            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(Home.this,login.class);
            startActivity(intent);

        });

        //button 2 change screen onclick
        btn2.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this,tpanel.class);
            startActivity(intent);
        });

    }
}