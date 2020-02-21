package com.example.leads;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListViewShow extends AppCompatActivity {
    ListView listView;
    DataBaseHelper dataBaseHelper;
    ArrayList<String> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_show);
        listView = (ListView) findViewById(R.id.listView);
        dataBaseHelper = new DataBaseHelper(this);

       try {
           Cursor cursor = dataBaseHelper.showAllData();
           if (cursor.getCount() == 0) {
               Toast.makeText(getApplicationContext(), "No data available in your database", Toast.LENGTH_SHORT).show();
           } else {

           }
           while (cursor.moveToNext()) {
               listData.add(cursor.getString(0) + " \n" + cursor.getString(1) + " \n" + cursor.getString(2) + "\n" + cursor.getString(3) + "\n" + cursor.getString(5));
           }
       }catch (Exception e){
           Toast.makeText(this, "wrong = "+e, Toast.LENGTH_LONG).show();
       }



        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.sample_view, R.id.textViewID, listData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(ListViewShow.this, FastItem.class);
                        startActivity(intent);
                    case 1:
                        Intent intent1 = new Intent(ListViewShow.this, SndItem.class);
                        startActivity(intent1);
                    case 2:
                        Intent intent2 = new Intent(ListViewShow.this, TrdItem.class);
                        startActivity(intent2);
                }
            }
        });
    }


}
