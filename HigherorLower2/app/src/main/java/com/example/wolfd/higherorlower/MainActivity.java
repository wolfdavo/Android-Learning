package com.example.wolfd.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public int randomNum = new Random().nextInt(10) + 1;

    public void newNumber(View newNum) {

        randomNum = new Random().nextInt(10) + 1;
    }

    public void makeToast(String string){

        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();

    }


    public void guess(View view){

        EditText userGuess = (EditText) findViewById(R.id.numberGuess);

        String userGuessString = userGuess.getText().toString();

        int userGuessInt = Integer.parseInt(userGuessString);

        if (userGuessInt == randomNum){
            makeToast("That's right! Well done");
        }
        else if (userGuessInt > randomNum){
            makeToast("You're too high");
        }
        else if(userGuessInt < randomNum){
            makeToast("You're too low");
        }

    }
}
