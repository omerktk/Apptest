package com.zofa.apptest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class login extends AppCompatActivity {

    DatabaseReference ref;
    EditText name,pass;
    private FirebaseAuth mAuth;
    MaterialButton btn,btn2;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pass = findViewById(R.id.password);
        name = findViewById(R.id.username);
        btn = (MaterialButton) findViewById(R.id.loginbtn);
        btn2 = (MaterialButton) findViewById(R.id.btn2);
        progressBar = findViewById(R.id.progressBar2);

        //button 1 login
        btn.setOnClickListener(view -> {

            userLogin();

        });


        //button 2 change screen onclick
        btn2.setOnClickListener(view -> {
            Intent intent = new Intent(login.this,reg.class);
            startActivity(intent);
        });


    }

    private void userLogin() {

        String dmail = name.getText().toString().trim();
        String dpassword = pass.getText().toString().trim();

        if(dmail.isEmpty()){
            name.setError("Field Is Empty");
            name.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(dmail).matches()){
            name.setError("Email Is Not Vaild");
            name.requestFocus();
            return;
        }


        if(dpassword.isEmpty()){
            pass.setError("Field Is Empty");
            pass.requestFocus();
            return;
        }


        if(dpassword.length() < 6){
            pass.setError("Password is less then 6 words!");
            pass.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        FirebaseAuth.getInstance().signInWithEmailAndPassword(dmail,dpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(login.this, "Login is Successful", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(login.this,Home.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(login.this, "Failed to Login", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}