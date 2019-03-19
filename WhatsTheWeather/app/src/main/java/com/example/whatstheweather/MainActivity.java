package com.example.whatstheweather;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText whichCity;

    ImageView background;

    TextView overView;
    TextView temp;

    public class WeatherDownlaoder extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            URL url;
            HttpURLConnection urlConnection;
            StringBuilder result = new StringBuilder();

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1){
                    char current = (char) data;

                    result.append(current);

                    data = reader.read();
                }
                return result.toString();

            }catch(Exception e){
                e.printStackTrace();
                return null;
            }



        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                String newline = System.getProperty("line.separator");

                JSONObject jsonObject = new JSONObject(s);

                JSONObject tempGet = jsonObject.getJSONObject("main");

                String temperature = tempGet.getString("temp");

                temp.setText(temperature + "°C");

                String weather = jsonObject.getString("weather");

                Log.i("Weather: ", weather);

                JSONArray arr = new JSONArray(weather);

                String overViewSet = "";

                for (int i = 0; i< arr.length(); i++){
                    JSONObject jsonPart = arr.getJSONObject(i);

                    String main = jsonPart.getString("main");
                    String description = jsonPart.getString("description");

                    if (!main.equals("") && !description.equals("")){

                        overViewSet += newline;

                        overViewSet += main + " - " + description;

                        overViewSet += newline;


                    }
                    switch (main) {
                        case "Clouds":
                        case "Cloudy":
                            background.setImageResource(R.drawable.cloudy);
                            break;
                        case "Stormy":
                        case "Rain":
                        case "Thunderstorms":
                            background.setImageResource(R.drawable.storm);
                            break;
                        case "Clear":
                        case "Sunny":
                            background.setImageResource(R.drawable.sunny);
                            break;
                    }

                }
                overView.setText(overViewSet);

            }catch (Exception e){
                Toast.makeText(MainActivity.this, "Invalid city name", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        whichCity = findViewById(R.id.editText);
        background = findViewById(R.id.background);
        background.setImageResource(R.drawable.cloudy);
        overView = findViewById(R.id.overviewBody);
        temp = findViewById(R.id.tempBody);
        whichCity.clearFocus();
        whichCity.setText(null);

    }

    public void getWeather(View view){

        try {
            WeatherDownlaoder task = new WeatherDownlaoder();
            String encodedCityName = URLEncoder.encode(whichCity.getText().toString(), "UTF-8");
            task.execute("https://openweathermap.org/data/2.5/weather?q=" + encodedCityName + "&appid=b6907d289e10d714a6e88b30761fae22");

            InputMethodManager mngr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            mngr.hideSoftInputFromWindow(whichCity.getWindowToken(),0);

        }catch (Exception e) {
            e.printStackTrace();
        }


    }

}
