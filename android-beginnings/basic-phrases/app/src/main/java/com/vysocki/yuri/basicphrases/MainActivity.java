package com.vysocki.yuri.basicphrases;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    //some comment to test
    public void playSound(View view) {

        String pressedButton = view.getTag().toString();

        int audioId = getResources().getIdentifier(pressedButton, "raw", "com.vysocki.yuri.basicphrases");


        mediaPlayer =  MediaPlayer.create(this, audioId);
        mediaPlayer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //



    }
}
