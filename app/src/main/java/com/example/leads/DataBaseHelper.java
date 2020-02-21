package com.example.leads;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper {
    Context context;
    DB myHelper;

    public DataBaseHelper(Context context) {
        this.context = context;
        myHelper = new DB(context);

        //Content value class has to be instanticated
        //ContentValues contentValues = new ContentValues();


    }

    public long insertData(PersonDetails personDetails) {
        SQLiteDatabase db = myHelper.getWritableDatabase();

        //Content value class has to be instanticated
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB.name, personDetails.getName());
        contentValues.put(DB.email, personDetails.getEmail());
        contentValues.put(DB.adress, personDetails.getAdress());
        contentValues.put(DB.password, personDetails.getPassword());
        contentValues.put(DB.IMAGE, personDetails.getImage());
        long id = db.insert(DB.db_TableName, null, contentValues);

        return id;

    }

    public boolean updateData(String id,String name,String email,String address,String password,byte[]image) {
        SQLiteDatabase db = myHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB.name, id);
        contentValues.put(DB.name, name);
        contentValues.put(DB.email,email);
        contentValues.put(DB.adress,address);
        contentValues.put(DB.password,password);
        contentValues.put(DB.IMAGE, image);
        db.update(myHelper.db_TableName, contentValues, myHelper.uid + " = ?", new String[]{id});

        return true;
    }

    public Cursor showAllData() {
        SQLiteDatabase sqLiteDatabase = myHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB.db_TableName, null);
        return cursor;
    }


    public class DB extends SQLiteOpenHelper {

        private static final String db_name = "PersonDB.db";
        private static final String db_TableName = "PersonTable";
        private static final int db_version = 3;
        private static final String uid = "_id";
        private static final String name = "Name";
        private static final String email = "Email";
        public static final String IMAGE = "image";
        private static final String adress = "Adress";
        private static final String password = "Password";
        Context context;


        String createTable = "Create Table " + db_TableName + " ( " + uid + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + name + " VARCHAR(255) ,"
                + email + " VARCHAR(25) ,"
                + adress + " VARCHAR(255) , "
                + IMAGE + " VARCHAR(25),"
                + password + " VARCHAR(255));";

        String dropTable = "Drop Table if EXISTS " + db_TableName;


        public DB(@Nullable Context context) {
            super(context, db_name, null, db_version);
            this.context = context;//the version change will be monired by creation of constructor!
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            //create Table for DB
            try {
                sqLiteDatabase.execSQL(createTable);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, "Table created", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newversion) {
            try {
                sqLiteDatabase.execSQL(dropTable);
                onCreate(sqLiteDatabase);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}