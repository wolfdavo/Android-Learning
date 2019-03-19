package com.example.guessthecelebrity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    String sourceCode;

    ArrayList<String> imageURLSandNames;

    ArrayList<String> allTheNames;

    int randomIndexForWhatCeleb;
    int random;

    String url;
    String name;

    ImageView celebImage;

    public static class DownloadHtmlCode extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder rawCode = new StringBuilder();
            URL url;
            HttpURLConnection urlConnection;

            try{
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1){
                    char current = (char) data;

                    rawCode.append(current);

                    data = reader.read();
                }

                return rawCode.toString();

            } catch (Exception e){
                e.printStackTrace();
                return "Failed";
            }

        }
    }

    public static class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... url) {

            URL imageUrl;
            HttpURLConnection urlConnection;

            try {
                imageUrl = new URL(url[0]);
                urlConnection = (HttpURLConnection) imageUrl.openConnection();
                urlConnection.connect();
                InputStream in = urlConnection.getInputStream();
                return BitmapFactory.decodeStream(in);

            }catch (Exception e){
                e.printStackTrace();
                return null;
            }

        }
    }

    public void extractImageUrls(){
        Pattern p = Pattern.compile("<div class=\"image\">\n\t\t\t\t\t\t<img src=\"(.*?)\"/>");

        Matcher m = p.matcher(sourceCode);

        imageURLSandNames = new ArrayList<>();

        while (m.find()){
            imageURLSandNames.add(m.group(1));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allTheNames = new ArrayList<String>();

        celebImage = findViewById(R.id.celebView);

        DownloadHtmlCode getSourceCode = new DownloadHtmlCode();

        try {
            sourceCode = getSourceCode.execute("http://www.posh24.se/kandisar").get();
        }catch(Exception e){
            e.printStackTrace();
        }

        extractImageUrls();

        Log.i("Array of URLS:", imageURLSandNames.toString());
        Log.i("Array count:", Integer.toString(imageURLSandNames.size()));

        filterUrlAndName();

        Log.i("Names", allTheNames.toString());

    }

    public void guess(View view){

        String tagString = String.valueOf(view.getTag());
        int tagint = Integer.parseInt(tagString);

        Log.i("test", tagString + " " + String.valueOf(random));

        if (tagint == random){
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
        }
        filterUrlAndName();
    }

    public void filterUrlAndName(){

        randomIndexForWhatCeleb = new Random().nextInt(imageURLSandNames.size());

        String urlAndName = imageURLSandNames.get(randomIndexForWhatCeleb);

        String[] urlAndNameSep = urlAndName.split("(\" alt=\")");

        url = urlAndNameSep[0];

        name = urlAndNameSep[1];

        for (int i = 0; i < 3; i++){
            int random = new Random().nextInt(imageURLSandNames.size());

            String urlAndNameEx = imageURLSandNames.get(random);

            String[] urlAndNameAr = urlAndNameEx.split("(\" alt=\")");

            allTheNames.add(urlAndNameAr[1]);

        }

        setImageView();

        setButtonText();

        Log.i("Test", url + " " + name);

    }

    public void setImageView(){
        ImageDownloader task = new ImageDownloader();
        Bitmap image;

        try {
            image = task.execute(url).get();
            celebImage.setImageBitmap(image);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setButtonText(){
        random = new Random().nextInt(4);

        ArrayList<Button>buttons = new ArrayList<>();
        buttons.add((Button) findViewById(R.id.b1));
        buttons.add((Button) findViewById(R.id.b2));
        buttons.add((Button) findViewById(R.id.b3));
        buttons.add((Button) findViewById(R.id.b4));

        buttons.get(random).setText(name);

        for (Button b : buttons){
            if (buttons.indexOf(b) != random){
                b.setText(allTheNames.get(new Random().nextInt(allTheNames.size())));
            }
        }

    }

}
