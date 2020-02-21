package com.example.leads;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class FastItem extends AppCompatActivity {
    EditText id,name, email, address, password;
    ImageView camera;
    DataBaseHelper dataBaseHelper;
    Bitmap photo;
    static final int REQUEST_IMAGE_CAPTURE = 2;

    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_item);
        id = (EditText)findViewById(R.id.id);
        name = (EditText) findViewById(R.id.uName);
        email = (EditText) findViewById(R.id.emailEditText);
        address = (EditText) findViewById(R.id.adressEditText);
        password = (EditText) findViewById(R.id.passwordEditText);
        camera = (ImageView) findViewById(R.id.imageview);
        update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
                PersonDetails personDetails = new PersonDetails();
                personDetails.setName(name.getText().toString());
                personDetails.setEmail(email.getText().toString());
                personDetails.setAdress(address.getText().toString());
                personDetails.setPassword(password.getText().toString());
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                 String idd = id.getText().toString();
                 String namee = name.getText().toString();
                 String emaill = email.getText().toString();
                 String addresss = address.getText().toString();
                 String passwordd = password.getText().toString();
                  DataBaseHelper dataBaseHelper1 = new DataBaseHelper(getApplicationContext());
                Boolean upgreted =   dataBaseHelper.updateData(idd,namee,emaill,addresss,passwordd,byteArray);
                if (upgreted==true){
                    Toast.makeText(FastItem.this, "Table is upgreated", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(FastItem.this, "Table is not upgreated", Toast.LENGTH_LONG).show();
                }

            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
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
            camera.setImageBitmap(photo);
        }
    }
}
