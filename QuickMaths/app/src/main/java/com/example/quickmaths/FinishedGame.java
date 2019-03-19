package com.example.quickmaths;

import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class FinishedGame extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finishedgame);

        TextView finalScore = findViewById(R.id.scoreFinal);
        String finalScoreString = Integer.toString(Game.scoreInt);
        finalScore.setText(finalScoreString);

    }

    public void home(View view){
        startActivity(new Intent(FinishedGame.this, MainActivity.class));
        finish();
    }
}
