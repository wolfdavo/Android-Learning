package com.example.pizzatimernewerapi;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    SeekBar howToasty;

    TextView timeDisplay;

    MediaPlayer alarmPlayer;

    int minutes;
    int seconds;

    String minutesView;

    boolean isCooking;
    boolean startORstop;

    public void progressToMins(int progresss) {
        if (progresss == 0) {
            minutes = 8;
        } else if (progresss == 1) {
            minutes = 10;
        } else if (progresss == 2) {
            minutes = 12;
        } else if (progresss == 3) {
            minutes = 14;
        } else if (progresss == 4) {
            minutes = 16;
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        minutesView = minutes + " : " + seconds;

        startORstop = true;

        isCooking = false;

        timeDisplay = findViewById(R.id.timerView);

        alarmPlayer = MediaPlayer.create(MainActivity.this, R.raw.alarm);

        howToasty = findViewById(R.id.seekBar);
        howToasty.setMax(4);
        howToasty.setProgress(2);

        progressToMins(howToasty.getProgress());

        setTimeDisplay();

        howToasty.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                progressToMins(progress);

                setTimeDisplay();

                Log.i("minutes", minutesView);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    public void setTimeDisplay() {

        if (seconds < 10) {
            minutesView = (minutes + " : " + "0" + seconds);
            timeDisplay.setText(minutesView);
        } else if (seconds == 60){
            minutesView = (minutes + " : " + "59");
            timeDisplay.setText(minutesView);
        } else {
            minutesView = minutes + " : " + seconds;
            timeDisplay.setText(minutesView);
        }
    }



    public void start(View view) {

        TextView startButton = findViewById(R.id.startButton);

        if (startORstop) {

            howToasty.setEnabled(false);
            startORstop = false;
            startButton.setText("Stop");

            new CountDownTimer(minutes * 60000, 60000) {
                public void onTick(final long millisecondsUnitlFinished) {
                    //code for every time counter ticks down set interval.
                    if (!startORstop) {
                        minutes = (int) millisecondsUnitlFinished / 60000;

                        new CountDownTimer(60000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                if (!startORstop) {
                                    seconds = (int) TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
                                    setTimeDisplay();
                                }else {
                                    this.cancel();
                                }
                            }

                            @Override
                            public void onFinish() {

                            }
                        }.start();

                        setTimeDisplay();

                    }else {
                        this.cancel();
                    }

                }

                public void onFinish() {

                    //code for when timer finishes counting down (10 seconds)
                    if (!startORstop) {
                        isCooking = false;
                        alarmPlayer.start();
                        Log.i("Done!", "Countdown timer finished!");
                    }else {
                        this.cancel();
                    }
                }

            }.start();

        }else {

            alarmPlayer.stop();
            howToasty.setEnabled(true);
            startORstop = true;
            startButton.setText("start");
            progressToMins(howToasty.getProgress());
            seconds = 0;
            setTimeDisplay();
        }
    }

    public void info(View view){
        startActivity(new Intent(MainActivity.this, CookingInfo.class));
    }
}




//setup seek bar. 5 values. min 0 max 4.

//One countdowntimer for minutues that loops for the numer of minutes defined by the seekbar.
//left side is equal to number of minutes defined by seekbar,
//right side is the countdown timer.
// 0, 8 mins,
//1, 10
//2, 12
//3, 14
//4, 16 mins

//start button starts the two counters.