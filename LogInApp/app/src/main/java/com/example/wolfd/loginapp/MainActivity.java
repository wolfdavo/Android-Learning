package com.example.wolfd.loginapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void logIn(View logIn) {
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);
        Log.i("Email", email.getText().toString());;
        Log.i("Password", password.getText().toString());
        Toast.makeText(MainActivity.this, "Welcome, " + email.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    public void connectClick(View view) {
        Toast.makeText(MainActivity.this, "Connect app CC 2019", Toast.LENGTH_SHORT).show();

    }
}