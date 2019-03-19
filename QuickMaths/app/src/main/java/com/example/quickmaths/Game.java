package com.example.quickmaths;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity {

TextView timeLeft;
TextView question;
TextView scoreView;
public static int scoreInt;
String scoreString;

ArrayList<Button> options;

int firstTerm;
String operator;
int secondTerm;
String displayEquation;

int wrongSum;

int correctAnswer;
String correctAnswerString;
Button correctButton;


public void wrongAnswer(Button wrongButton){

    String[] wrongOperators = new String[3];
    wrongOperators[0] = "x";
    wrongOperators[1] = "+";
    wrongOperators[2] = "-";

    int wrongFirstTerm = new Random().nextInt(6);
    int wrongSecondTerm = new Random().nextInt(6);
    String wrongOperator = wrongOperators[new Random().nextInt(3)];
    wrongSum = 0;

    switch (wrongOperator) {
        case "x":
            wrongSum = wrongFirstTerm * wrongSecondTerm;
            break;
        case "+":
            wrongSum = wrongFirstTerm + wrongSecondTerm;
            break;
        case "-":
            wrongSum = wrongFirstTerm - wrongSecondTerm;
            break;
    }

    String wrongSumString = Integer.toString(wrongSum);

    wrongButton.setText(wrongSumString);
}

public void formatQuestion(){

    question = findViewById(R.id.questionTextView);

    String[] operators = new String[3];
        operators[0] = "x";
        operators[1] = "+";
        operators[2] = "-";

    firstTerm = new Random().nextInt(6);
    operator = operators[new Random().nextInt(3)];
    secondTerm = new Random().nextInt(6);
    displayEquation = Integer.toString(firstTerm) + operator + Integer.toString(secondTerm);
    question.setText(displayEquation);

    switch (operator) {
        case "x":
            correctAnswer = firstTerm * secondTerm;
            break;
        case "+":
            correctAnswer = firstTerm + secondTerm;
            break;
        case "-":
            correctAnswer = firstTerm - secondTerm;
            break;
    }

    correctAnswerString = Integer.toString(correctAnswer);
    correctButton = options.get(new Random().nextInt(4));
    correctButton.setText(correctAnswerString);

    for (Button button : options){
        if (button.getTag() != correctButton.getTag()) {

            wrongAnswer(button);

            if (button.getText().equals(String.valueOf(correctAnswer))){
                button.setText(String.valueOf(correctAnswer + 1));
            }
        }

    }



}


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.game);

        scoreView = findViewById(R.id.score);

        scoreInt = 0;
        String scoreString = Integer.toString(scoreInt);
        scoreView.setText(scoreString);


        Button b0 = findViewById(R.id.b0);
        Button b1 = findViewById(R.id.b1);
        Button b2 = findViewById(R.id.b2);
        Button b3 = findViewById(R.id.b3);

        timeLeft = findViewById(R.id.timeLeft);

        options = new ArrayList<>();
            options.add(b0);
            options.add(b1);
            options.add(b2);
            options.add(b3);

        formatQuestion();

        new CountDownTimer(60000 + 100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {

                startActivity(new Intent(Game.this, FinishedGame.class));

            }
        }.start();

        new CountDownTimer(60101, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                finish();
            }
        }.start();

    }

    public void guess(View view){

        Button guessedButton = (Button) view;

        if (guessedButton.getId() == correctButton.getId()){

            scoreInt++;
            scoreString = Integer.toString(scoreInt);
            scoreView.setText(scoreString);

            formatQuestion();
        }else {
            formatQuestion();
        }
    }

   public void quit(View view){
        finish();
    }

}
