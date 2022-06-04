package com.zofa.apptest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Gallery extends AppCompatActivity {
    MaterialButton btn, btn2;
    ImageView img;
    StorageReference Sref;
    Uri selectedImage;
    ProgressDialog pd;

    DatabaseReference ref = FirebaseDatabase.getInstance("https://apptest-fed14-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Products");

    ArrayList < Product > data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        btn = (MaterialButton) findViewById(R.id.btnChoose);
        btn2 = (MaterialButton) findViewById(R.id.btnUpload);

        //button show image
        btn.setOnClickListener(view -> {

            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 3);

        });

        //button Upload Image
        btn2.setOnClickListener(view -> {

            uploadImage();

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            selectedImage = data.getData();
            img = findViewById(R.id.imgView);
            img.setImageURI(selectedImage);
        }
    }

    private void uploadImage() {
        String maxid = System.currentTimeMillis()+"" ;
        if (selectedImage != null) {
            pd = new ProgressDialog(this);
            pd.setTitle("Uploading File...");
            pd.show();

            Sref = FirebaseStorage.getInstance().getReference("images/" + maxid + ".png");
            EditText name = findViewById(R.id.username1);
            EditText  mail = findViewById(R.id.password1);

            //data

            Product member = new Product(name.getText().toString(),mail.getText().toString(), maxid+ ".png");
            ref.child(maxid).setValue(member);


            Sref.putFile(selectedImage)
                    .addOnSuccessListener(new OnSuccessListener < UploadTask.TaskSnapshot > () {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            img.setImageURI(null);
                            if (pd.isShowing())
                                pd.dismiss();

                            Toast.makeText(Gallery.this,"Data inserted",Toast.LENGTH_LONG).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if (pd.isShowing())
                                pd.dismiss();

                            Toast.makeText(Gallery.this, "Image Uploading Failed", Toast.LENGTH_LONG).show();
                        }
                    });




        } else {
            Toast.makeText(Gallery.this, "Select Image", Toast.LENGTH_LONG).show();

        }

    }

}