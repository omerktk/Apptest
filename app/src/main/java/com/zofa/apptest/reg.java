package com.zofa.apptest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class reg extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText username,age,mail,password;
    MaterialButton loginbtn,btn2;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.username);
        age = findViewById(R.id.age);
        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        loginbtn = (MaterialButton) findViewById(R.id.loginbtn);


        loginbtn.setOnClickListener(view -> {
            registerUser();
        });
    }

    private void registerUser() {

        String dmail = mail.getText().toString().trim();
        String dage = age.getText().toString().trim();
        String dusername = username.getText().toString().trim();
        String dpassword = password.getText().toString().trim();




        if(dusername.isEmpty()){
            username.setError("Field Is Empty");
            username.requestFocus();
            return;
        }

        if(dage.isEmpty()){
            age.setError("Field Is Empty");
            age.requestFocus();
            return;
        }

        if(dmail.isEmpty()){
            mail.setError("Field Is Empty");
            mail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(dmail).matches()){
            mail.setError("Email Is Not Vaild");
            mail.requestFocus();
            return;
        }


        if(dpassword.isEmpty()){
            password.setError("Field Is Empty");
            password.requestFocus();
            return;
        }


        if(dpassword.length() < 6){
            password.setError("Password is less then 6 words!");
            password.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(dmail,dpassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                        User user = new User(dusername,dage,dmail);

                            FirebaseDatabase.getInstance("https://apptest-fed14-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(reg.this, "User Registered", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                                Intent intent = new Intent(reg.this,login.class);
                                                startActivity(intent);
                                            }else {
                                                Toast.makeText(reg.this, "Failed to Registered", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });

                        }else {
                            Toast.makeText(reg.this, "Failed to Registered", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });


    }
}