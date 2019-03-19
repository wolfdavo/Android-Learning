package com.example.downloadingwebcontent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    //declare new class that extends AsyncTask. Then define what the async task takes in as a parameter, its progress, and what it returns.
    public static class DownloadTask extends AsyncTask<String, Void, String>{

        //String... means a string array. multiple strings (urls) can be fed into the function and called by index.
        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            //this variable type is basically a connection to the internet. Similar to a browser in core capabilities.
            HttpURLConnection urlConnection = null;

            try {
                //gets the string fed into the function and sets it as our URL value. Has to be inside try catch because the string fed in could be a
                //malformed url.
                url = new URL(urls[0]);

                //sets our "browser" to open connection with our url.
                urlConnection = (HttpURLConnection) url.openConnection();
                //this initializes an input stream. Not entirely sure what this is **MORE RESEARCH**
                InputStream in = urlConnection.getInputStream();
                //sets a reader to the input stream
                InputStreamReader reader = new InputStreamReader(in);
                //the reader only reads one thing at a time, and is written to return -1 once all information has been read. We use a
                //while loop to take advantage of this and read all the data.
                int data = reader.read();

                while (data != -1){
                    char current = (char) data;

                    result += current;

                    data = reader.read();
                }

                return result;

            } catch (Exception e) {
                e.printStackTrace();

                return "Failed";
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize object of DownloadTask class. (remember DownloadTask class is defined above)
        DownloadTask task = new DownloadTask();

        //new string variable == null
        String result = null;

        try{
            //set string variable to the returned string when task (DownloadTask class object) is executed with given url.
            result = task.execute("https://zappycode.com/").get();
        } catch (Exception e){
            //catch and print any thrown exceptions
            e.printStackTrace();
        }

        //print the string variable set in the try catch
        Log.i("RESULT", result);
    }
}
