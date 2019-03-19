package com.example.gridlayoutchallenge;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void playSound(View view){

        //NOTE: when using .getId, this is getting the computer's integer id that it has assigned to the object, not the string id we assigned.
        int id = view.getId();

        //Here we create an empty string, then we use a bit of code that gets resources from the view that initiated the method, then it gets the resource entry name (the id we assigned it)
        // that corresponds with the computers unique numerical id (id).
        String ourId = "";
        ourId = view.getResources().getResourceEntryName(id);

        //We then declare another integer variable, this is equal to the computers numerical id for the media file we want to play. We get this by getting a resource, then getting an
        //identifier (remember this is the computers id not ours). This function searches for a file by name (string) which is equal to the string ourId we defined above.
        // then we must tell the computer where to look for the said file, in our case this is "raw". Then, we need to tell it what package the file exists in.
        //NOTE: it is important that the name of the media file that we want to play is identical to the id that we assigned to the corresponding button.
        // For example, birdNoises trying to play birdnoises.mp3 will not work. It is case sensitive, so the button must be called birdnoises.
        int resourceId = getResources().getIdentifier(ourId, "raw", "com.example.gridlayoutchallenge");

        //Here we declare a media player for the media file we found in the above code. (resourceId).
        MediaPlayer sound = MediaPlayer.create(this, resourceId);
        sound.start();

    }
}
