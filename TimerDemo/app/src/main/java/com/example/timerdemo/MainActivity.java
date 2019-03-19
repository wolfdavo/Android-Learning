package com.example.timerdemo;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(10000, 1000){
            public void onTick(long millisecondsUnitlFinished){

                //code for every time counter ticks down set interval.

                Log.i("info", String.valueOf(millisecondsUnitlFinished / 1000));

            }

            public void onFinish(){

                //code for when timer finishes counting down (10 seconds)

                Log.i("Done!", "Countdown timer finished!");

            }
        }.start();

/*
        final Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {

                //INSERT CODE HERE

                Log.i("info", "runnable has run!");

                handler.postDelayed(this, 1000);

            }
        };
        handler.post(run);
*/
    }
}
