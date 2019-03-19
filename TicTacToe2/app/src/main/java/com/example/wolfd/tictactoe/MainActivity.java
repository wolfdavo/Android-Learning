package com.example.wolfd.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class  MainActivity extends AppCompatActivity {

    int[] board = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    //ht0, hm1, hb2, vl3, vm4, vr5, dur6, ddl7
    int[][] winCombos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {6, 3, 0}, {7, 4, 1}, {8, 5, 2}, {6, 4, 2}, {0, 4, 8}};

    public boolean cutSwitch;

    public static class TurnDecider{
        static int whosTurn = 0;
    }

    public static class TotalCount{
       static int count;
    }

    public static class WinLineCounter{
        static int counter = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset();

    }

    public void turnImage(){
        ImageView img = findViewById(R.id.osTurn);
        ImageView imgx = findViewById(R.id.xsTurn);

        if (TurnDecider.whosTurn == 0){
            img.animate().alpha(1f);
            imgx.animate().alpha(0f);
        }else {
            imgx.animate().alpha(1f);
            img.animate().alpha(0f);
        }
    }

    public void hasWon(){

        ImageView[] winLines = {null, null, null, null, null, null, null, null};
        winLines[0] = findViewById(R.id.lineHt);
        winLines[1] = findViewById(R.id.lineHm);
        winLines[2] = findViewById(R.id.lineHb);
        winLines[3] = findViewById(R.id.lineVl);
        winLines[4] = findViewById(R.id.lineVm);
        winLines[5] = findViewById(R.id.lineVr);
        winLines[6] = findViewById(R.id.lineHur);
        winLines[7] = findViewById(R.id.lineHdl);

        for (int[] winCombo : winCombos){

            WinLineCounter.counter = WinLineCounter.counter + 1;

            if (board[winCombo[0]] == board[winCombo[1]] &&
                    board[winCombo[1]] == board[winCombo[2]] &&
                    board[winCombo[0]] != 2){

                if (!cutSwitch) {
                    int testing = WinLineCounter.counter - 1;

                    winLines[testing].animate().alphaBy(1f).setDuration(500);

                    cutSwitch = true;
                }

                if (TurnDecider.whosTurn == 0){
                    Toast.makeText(MainActivity.this, "Xs have won the game!!", Toast.LENGTH_SHORT).show();
                }
                else if (TurnDecider.whosTurn == 1){
                    Toast.makeText(MainActivity.this, "Os have won the game!!", Toast.LENGTH_SHORT).show();
                }

                //reset all indexes in board[] to 3 so no one can place x or o.
                for (int i = 0; i < board.length; i ++){

                    board[i] = 3;

                }

            }
        }
        WinLineCounter.counter = 0;
    }

    public boolean gameOver(){
        return (TotalCount.count == 9);
    }

    public void tap(View view){

        ImageView tapc = (ImageView) view;
        int tappedPos = Integer.parseInt(tapc.getTag().toString());

        if (board[tappedPos] == 2){

            if (TurnDecider.whosTurn == 0){
                board[tappedPos] = 0;
                tapc.setImageResource(R.drawable.o);
                tapc.animate().alphaBy(1f).setDuration(750);
                TurnDecider.whosTurn = 1;
                turnImage();
                TotalCount.count = TotalCount.count + 1;

            }else if (TurnDecider.whosTurn == 1){
                board[tappedPos] = 1;
                tapc.setImageResource(R.drawable.x);
                tapc.animate().alphaBy(1f).setDuration(750);
                TurnDecider.whosTurn = 0;
                turnImage();
                TotalCount.count = TotalCount.count + 1;
            }

        }

        hasWon();

        if (gameOver()){
            Toast.makeText(MainActivity.this, "Try again", Toast.LENGTH_SHORT).show();
        }

    }

    public void randomStart(){
        TurnDecider.whosTurn = new Random().nextInt(2);
    }

    public void resetButton(View view){
        reset();
    }

    public void reset(){

        for (int i = 0; i < board.length; i ++){

            board[i] = 2;

        }

        ArrayList<ImageView>xsandos = new ArrayList<>();

        xsandos.add((ImageView) findViewById(R.id.tl));
        xsandos.add((ImageView) findViewById(R.id.tm));
        xsandos.add((ImageView) findViewById(R.id.tr));
        xsandos.add((ImageView) findViewById(R.id.ml));
        xsandos.add((ImageView) findViewById(R.id.mm));
        xsandos.add((ImageView) findViewById(R.id.mr));
        xsandos.add((ImageView) findViewById(R.id.bl));
        xsandos.add((ImageView) findViewById(R.id.bm));
        xsandos.add((ImageView) findViewById(R.id.br));

        for (ImageView imageView : xsandos){
            imageView.animate().alpha(0f);
        }


        ImageView lHt = findViewById(R.id.lineHt);
        ImageView lVl = findViewById(R.id.lineVl);
        ImageView lVm = findViewById(R.id.lineVm);
        ImageView lVr = findViewById(R.id.lineVr);
        ImageView lHm = findViewById(R.id.lineHm);
        ImageView lHb = findViewById(R.id.lineHb);
        ImageView lHur = findViewById(R.id.lineHur);
        ImageView lHdl = findViewById(R.id.lineHdl);

            lHt.animate().alpha(0f);
            lHm.animate().alpha(0f);
            lHb.animate().alpha(0f);
            lVl.animate().alpha(0f);
            lVm.animate().alpha(0f);
            lVr.animate().alpha(0f);
            lHur.animate().alpha(0f);
            lHdl.animate().alpha(0f);


        cutSwitch = false;
        randomStart();
        turnImage();
        TotalCount.count = 0;
        WinLineCounter.counter = 0;
        if (TurnDecider.whosTurn == 0){
            Toast.makeText(MainActivity.this, "Os have first move!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "Xs have first move!", Toast.LENGTH_SHORT).show();
        }
    }

}
