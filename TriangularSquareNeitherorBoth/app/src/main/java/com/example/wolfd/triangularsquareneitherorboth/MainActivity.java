package com.example.wolfd.triangularsquareneitherorboth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Integer.getInteger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //get the number the user inputs as an int
    class InputNum{

        int userNumber;

        EditText textInput = (EditText) findViewById(R.id.textNumber);
        public boolean textIsEmpty (){
            if (textInput.getText().toString().isEmpty()){
                return true;
            }else {
                String textInputS = textInput.getText().toString();
                userNumber = Integer.parseInt(textInputS);
                return false;
            }
        }


    }


    public boolean tri(int userNumber) {
      //nth number * nth number + 1 / 2 = triangular number.
      //so, (triangular number?) = n*(n+1)/2
      //     2(triangular number?) = n*n  (disregard +1 for now because we can round down)
      //    Sqrt(2(triangular number?)) = n
      //     if n*(n+1)/2 = whole integer then return true
      int triTest = userNumber * 2;

      Double triTestD = new Double(triTest);

      triTestD = Math.sqrt(triTestD);

      triTestD = Math.floor(triTestD);  //rounds down to whole number.

      triTest = triTestD.intValue();

      if (((triTest * (triTest + 1)) / 2) == userNumber ){
          return true;
      }else {
          return false;
      }
    }

    public boolean square(int userNumber){

        final int testerNum = userNumber;

        Double userNumd = new Double(userNumber);

        userNumd = Math.sqrt(userNumd);

        userNumd = Math.floor(userNumd);

        userNumd = userNumd * userNumd;

        if (userNumd == testerNum){
            return true;
        }else {
            return false;
        }

    }

    public void checkNumPress (View view) {

        InputNum i = new InputNum();

        if (i.textIsEmpty()){
            Toast.makeText(MainActivity.this, "Please enter a number", Toast.LENGTH_SHORT).show();
        }else {

            if (square(i.userNumber) && (tri(i.userNumber))) {
                Toast.makeText(MainActivity.this, "The number is triangular AND square", Toast.LENGTH_SHORT).show();
                ;
            } else if (square(i.userNumber)) {
                Toast.makeText(MainActivity.this, "The number is SQUARE", Toast.LENGTH_SHORT).show();
            } else if (tri(i.userNumber)) {
                Toast.makeText(MainActivity.this, "The number is TRIANGULAR", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "The number is neither triangular nor square", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

