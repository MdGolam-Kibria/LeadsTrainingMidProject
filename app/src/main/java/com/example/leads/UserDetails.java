package com.example.leads;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


public class UserDetails extends AppCompatActivity {
    EditText NameEditText, EmailEditText, AdressEditText, passwordEditText;
    Button saveBtn, showBtn;
    ImageView imageview;
    DataBaseHelper dataBaseHelper;
    Bitmap photo;
    static final int REQUEST_IMAGE_CAPTURE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);


        NameEditText = findViewById(R.id.NameEditText);
        EmailEditText = findViewById(R.id.EmailEditText);
        AdressEditText = findViewById(R.id.AdressEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        imageview = findViewById(R.id.imageview);
        showBtn = findViewById(R.id.showBtn);
        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonDetails personDetails = new PersonDetails();
                personDetails.setName(NameEditText.getText().toString());
                personDetails.setEmail(EmailEditText.getText().toString());
                personDetails.setAdress(AdressEditText.getText().toString());
                personDetails.setPassword(passwordEditText.getText().toString());
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                personDetails.setImage(byteArray);

                if (NameEditText.getText().toString().equals("") || EmailEditText.getText().toString().equals("") || AdressEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "data missing", Toast.LENGTH_SHORT).show();
                }

                dataBaseHelper = new DataBaseHelper(getApplicationContext());

                long id = dataBaseHelper.insertData(personDetails);

                if (id == -1) {
                    Toast.makeText(getApplicationContext(), "Insert 404 error here!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), " All data Inserted successfully!", Toast.LENGTH_LONG).show();
                }


            }
        });


        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               try {
                   Intent intent = new Intent(UserDetails.this, ListViewShow.class);
                   startActivity(intent);
               }catch (Exception e){
                   Toast.makeText(UserDetails.this, "your wrong here"+e, Toast.LENGTH_LONG).show();
               }

            }
        });
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonDetails personDetails = new PersonDetails();
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            photo = (Bitmap) data.getExtras().get("data");
            imageview.setImageBitmap(photo);
        }
    }
}
