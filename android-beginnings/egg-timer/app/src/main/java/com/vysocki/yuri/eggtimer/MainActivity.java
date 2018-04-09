package com.vysocki.yuri.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar countdownSeekBar;
    TextView countdownTextView;
    Button controllerButton;
    CountDownTimer countDownTimer;
    Boolean counterIsActive = false;
    int countdownInitialProgress = 30;
    int countdownInitialMax = 600;

    public void startCountdown() {
        counterIsActive = true;
        countdownSeekBar.setEnabled(false);
        controllerButton.setText(R.string.button_controllerButtonStop);

        countDownTimer = new CountDownTimer(countdownSeekBar.getProgress() * 1000 + 100, 1000) {

            @Override
            public void onTick(long l) {
                updateCountdownTextView((int) l / 1000);
            }

            @Override
            public void onFinish() {
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                mediaPlayer.start();
                resetCountdown();
            }
        }.start();
    }

    public void resetCountdown() {
        countdownTextView.setText(R.string.textView_countdownTextView);
        controllerButton.setText(R.string.button_controllerButtonStart);
        countdownSeekBar.setProgress(countdownInitialProgress);
        countDownTimer.cancel();
        countdownSeekBar.setEnabled(true);
        counterIsActive = false;
    }

    public void updateCountdownTextView(int secondsLeft) {
        int minutes = (int) secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String firstString = Integer.toString(minutes);
        String secondString = Integer.toString(seconds);
        if (seconds <= 9) {
            secondString = "0" + secondString;
        }
        countdownTextView.setText(firstString + ":" + secondString);
    }

    public void controlCountdown(View view) {

        if (!counterIsActive) {
            startCountdown();
        } else {
            resetCountdown();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countdownSeekBar = findViewById(R.id.countdownSeekBar);
        countdownTextView = findViewById(R.id.countdownTextView);
        controllerButton = findViewById(R.id.controllerButton);

        // Initial SeekBar setup
        countdownSeekBar.setMax(countdownInitialMax);
        countdownSeekBar.setProgress(countdownInitialProgress);

        countdownSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateCountdownTextView(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
