package com.example.wolfd.buttonfromscratch;

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

    public void teleport(View teleport){
        EditText destination = (EditText) findViewById(R.id.destination);
        Log.i("Teleporting to ", destination.getText().toString());
        Log.i("", "ZOOOOM!");
        Toast.makeText(MainActivity.this, "Teleporting to: " + destination.getText().toString() + "!", Toast.LENGTH_LONG).show();
    }

}
