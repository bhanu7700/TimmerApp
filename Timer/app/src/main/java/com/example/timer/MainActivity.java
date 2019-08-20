package com.example.timer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    Boolean counterIsActive = false;
    Button controllbutton;
    CountDownTimer countDownTimer;

    public void updatetimer(int secleft){

        int minute = (int) secleft/60;
        int seconds = secleft-minute*60;

        String scondstring = Integer.toString(seconds);

        if(seconds <= 9) {
            scondstring = "0" + scondstring;

        }

            textView.setText(Integer.toString(minute)+":"+ scondstring);





    }

    public void controle(View view){
        if(counterIsActive == false) {

            counterIsActive = true;
            seekBar.setEnabled(false);
            controllbutton.setText("Stop!");

           countDownTimer =  new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    updatetimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {

                    textView.setText("0:00");
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.sound);
                    mplayer.start();


                }
            }.start();
        }else{
            textView.setText("0:30");
            seekBar.setProgress(30);
            controllbutton.setText("Go!");
            countDownTimer.cancel();
            seekBar.setEnabled(true);
            counterIsActive=false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar =(SeekBar) findViewById(R.id.seekBar);
         textView = (TextView) findViewById(R.id.textView);
         controllbutton = (Button) findViewById(R.id.controllbutton);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updatetimer(progress);

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
