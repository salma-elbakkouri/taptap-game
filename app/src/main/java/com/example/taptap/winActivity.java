package com.example.taptap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.graphics.Color.rgb;

public class winActivity extends AppCompatActivity {

    MediaPlayer winSound;
    private TextView levelText;
    private Button nextLevel;
    private Button homeButton;
    private Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

       winSound = MediaPlayer.create(getApplicationContext(),R.raw.win);
       winSound.start();

        Intent intent = getIntent();
       String levelPassed = intent.getStringExtra("levelPassed");
       levelText=findViewById(R.id.lossTextView);
       //
        int index = levelPassed.indexOf(".");
        String levelInt ="";
        if (index !=-1)
        {
            levelInt = levelPassed.substring(0, index);
        }
        else
        {
            levelInt = levelPassed;
        }


       levelText.setText("you won level "+levelInt);
       nextLevel = findViewById(R.id.nextButton);
       homeButton=findViewById(R.id.homeButton);
       nextButton =findViewById(R.id.nextButton);


       nextButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = getIntent();
               String levelSelected = intent.getStringExtra("levelPassed");

               float levelNum =Float.parseFloat(levelSelected);
               float newLevel = levelNum+1;
               float period=Math.abs((1/(newLevel))*1000);
               levelSelected = String.valueOf(newLevel);
               Intent intent1 = new Intent(getApplicationContext(), gameActivity.class);
               intent1.putExtra("levelSelected",levelSelected);
               intent1.putExtra("period",period);
               startActivity(intent1);

           }
       });
       nextLevel.setShadowLayer(5,4,4,rgb(62,58,58));
       levelText.setShadowLayer(5,4,4,rgb(62,58,58));
       homeButton.setShadowLayer(5,4,4,rgb(62,58,58));

       homeButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent =new Intent (getApplicationContext(),firstActivity.class);
               startActivity(intent);
           }
       });


    }
     @Override
     protected void onPause() {
        super.onPause();
        winSound.release();
        finish();
    }
}