package com.zofa.apptest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TeaMarkDataUpload extends AppCompatActivity {
    MaterialButton btn;
    ProgressDialog pd;
    DatabaseReference ref = FirebaseDatabase.getInstance("https://apptest-fed14-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Marks");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamarkdataupload);

        btn = (MaterialButton) findViewById(R.id.btnUpload);


        //button Upload Image
        btn.setOnClickListener(view -> {
            uploadImage();
        });

    }



    private void uploadImage() {
        String maxid = System.currentTimeMillis()+"" ;

            pd = new ProgressDialog(this);
            pd.setTitle("Uploading File...");
            pd.show();
            EditText name = findViewById(R.id.username);
            EditText  mail = findViewById(R.id.marks);
        EditText  id = findViewById(R.id.rolenmbr);
            //data
            JavaModal member = new JavaModal(name.getText().toString(),mail.getText().toString(), id.getText().toString());
            ref.child(maxid).setValue(member);
        pd.hide();
        Toast.makeText(TeaMarkDataUpload.this, "Uploaded", Toast.LENGTH_LONG).show();

    }

}