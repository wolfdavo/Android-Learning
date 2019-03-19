package com.example.wolfd.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void convert(View view){

        EditText editText = (EditText) findViewById(R.id.dollarOrigin);
        Double dollarOrigin = Double.parseDouble(editText.getText().toString());

        Double rateNZ = 1.49;

        Double rateUS = 1.00;

        Double rate = 1.00;

        if (spinnerOrigin)


        Double dollarProductD = (dollarOrigin * rate);
        String dollarProduct = Double.toString(dollarProductD);
        EditText edtText = (EditText) findViewById(R.id.dollarProduct);
        edtText.setText(String.format(dollarProduct, "%.2f"));

    }

}