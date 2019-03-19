package com.example.pizzatimernewerapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CookingInfo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cooking_info);

    }

    public void backToMain(View view){
        startActivity(new Intent(CookingInfo.this, MainActivity.class));
    }
}
