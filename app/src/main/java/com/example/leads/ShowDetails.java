package com.example.leads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ShowDetails extends AppCompatActivity {
    TextView showDetailsTextviewid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        showDetailsTextviewid = findViewById(R.id.showDetailsTextviewid);

    }
}
