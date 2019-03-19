package com.example.wolfd.imagechanger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeLight(View view){
        ImageView lightView= (ImageView) findViewById(R.id.redLight);
        lightView.setImageResource(R.drawable.green);
        Toast.makeText(MainActivity.this, "zoom zoom", Toast.LENGTH_SHORT).show();

    }
}
