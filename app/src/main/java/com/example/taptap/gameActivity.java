package com.example.taptap;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static android.graphics.Color.rgb;

public class gameActivity extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer music;
    private int CurrentProgress;
    private Button tapButton;
    private ProgressBar progressBar;
    private TextView signText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

      //  music = MediaPlayer.create(getApplicationContext(),R.raw.bg_music);
      //  music.start();

        Intent intent = getIntent();
        String levelSelected = intent.getStringExtra("levelSelected");
        float period = intent.getFloatExtra("period", (float) 200.0);

        signText=findViewById(R.id.signText);
        progressBar = findViewById(R.id.progressBar);
        tapButton = findViewById(R.id.tapButton);

        signText.setShadowLayer(5,4,4,rgb(62,58,58));
        tapButton.setShadowLayer(5,4,4,rgb(62,58,58));
        tapButton.setOnClickListener(this);
        CurrentProgress = 50;
        progressBar.setProgress(CurrentProgress);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                if (CurrentProgress >= 100 || CurrentProgress <= 0) {
                    timer.cancel();
                    runOnUiThread(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void run() {
                            processUserResult();
                        }
                    });
                }else {
                    progressBar.setProgress(CurrentProgress=CurrentProgress-2);
                }
            }
        }, 0, (long) period);
        //wait 0 ms before doing the action and do it every peiord(ms)
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        if (CurrentProgress > 0 && CurrentProgress < 100) {
            progressBar.setProgress(CurrentProgress = CurrentProgress+3);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void processUserResult() {
        if (CurrentProgress >= 100) {
            Intent intent = getIntent();
            String levelPassed = intent.getStringExtra("levelSelected");
            float period = intent.getFloatExtra("period", (float) 200.0);


            Intent intent1 = new Intent(this, winActivity.class);
            intent1.putExtra("levelPassed",levelPassed);
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
            startActivity(intent1, bundle);
        } else if (CurrentProgress <= 0) {
            Intent intent = getIntent();
            String levelLost = intent.getStringExtra("levelSelected");
            float period = intent.getFloatExtra("period", (float) 200.0);
            Intent intent1 = new Intent(this, lossActivity.class);
            intent1.putExtra("period",period);
            intent1.putExtra("levelLost",levelLost);
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
            startActivity(intent1, bundle);
        }
    }
  //  @Override
  //  protected void onPause() {
    //    super.onPause();
    //    music.release();
    //    finish();
   // }
}
