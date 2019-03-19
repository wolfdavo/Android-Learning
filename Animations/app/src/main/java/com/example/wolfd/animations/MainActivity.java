package com.example.wolfd.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView bart = findViewById(R.id.bart);

        ImageView homer = findViewById(R.id.homer);

        homer.setAlpha(0f);
        bart.setTranslationX(-1000f);

    }

    class Integer{
        int decider;
    }

    Integer integer = new Integer();

    public void fadeBart(View view) {

        ImageView bart = findViewById(R.id.bart);
        ImageView homer = findViewById(R.id.homer);


        if ((integer.decider == 0) && (bart.getTranslationX() == -1000f)) {

            bart.animate()
                    .scaleXBy(-0.5f)
                    .scaleYBy(-0.5f)
                    .rotationBy(1800f)
                    .translationXBy(1000f)
                    .setDuration(1500);

            integer.decider = 1;
        }else if((integer.decider == 1) &&  (bart.getTranslationX() == 0f)){
            bart.animate().scaleXBy(0.5f).scaleYBy(0.5f).rotationBy(1800f).translationXBy(-1000f).setDuration(1500);
            integer.decider = 0;

        }
    }
}
