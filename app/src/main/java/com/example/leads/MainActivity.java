package com.example.leads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText usernameEditText, passwordEditText;
    private Button saveButtonid, loadButtonid, loginButtonid;
    public String name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadButtonid = (Button) findViewById(R.id.loadButtonid);
        loginButtonid = (Button) findViewById(R.id.loginButtonid);
        saveButtonid = (Button) findViewById(R.id.saveButtonid);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        usernameEditText = (EditText) findViewById(R.id.uName);
        loadButtonid.setOnClickListener(this);
        saveButtonid.setOnClickListener(this);
        loginButtonid.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.saveButtonid) {
            String name = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            if (name.equals(null) || password.equals(null)) {
                Toast.makeText(getApplicationContext(), "Data not entered", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("usernameKey", name);
                editor.putString("passwordKey", password);
                editor.commit();
                usernameEditText.setText("");
                passwordEditText.setText("");
                Toast.makeText(getApplicationContext(), "Data Stoared sucessful in shared preference", Toast.LENGTH_SHORT).show();
            }
        } else if (view.getId() == R.id.loadButtonid) {
            SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
            if (sharedPreferences.contains("usernameKey") && sharedPreferences.contains("passwordKey")) {
                name = sharedPreferences.getString("usernameKey", "Data not found");
                password = sharedPreferences.getString("passwordKey", "Data not found");
                usernameEditText.setText(name);
                passwordEditText.setText(password);
                Toast.makeText(this, "Data loaded from shared preference", Toast.LENGTH_LONG).show();
            }

        } else if (view.getId() == R.id.loginButtonid) {
            if (usernameEditText.getText().toString().equals(name) && passwordEditText.getText().toString().equals(password)) {
                Intent intent = new Intent(MainActivity.this, UserDetails.class);
                startActivity(intent);

            } else {
                Toast.makeText(this, "Please enter correct name/password", Toast.LENGTH_LONG).show();
                usernameEditText.setText("");
                passwordEditText.setText("");
            }
        }
    }
}
