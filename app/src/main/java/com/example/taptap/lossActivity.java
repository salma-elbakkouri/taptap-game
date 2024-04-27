package com.example.taptap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.graphics.Color.rgb;

public class lossActivity extends AppCompatActivity {

    MediaPlayer failSound;
    private Button retryButton;
    private Button homeButton;
    private TextView levelText;
    private TextView lossTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loss);

        Intent intent = getIntent();
        String levelLost = intent.getStringExtra("levelLost");

        failSound = MediaPlayer.create(getApplicationContext(),R.raw.fail);
        failSound.start();

        homeButton = findViewById(R.id.homeButton);
        lossTextView = findViewById(R.id.lossTextView);
        levelText = findViewById(R.id.levelText);


        lossTextView.setShadowLayer(5,4,4,rgb(62,58,58));

        lossTextView.setText("Aww , you lost\nlevel "+levelLost);
        retryButton = findViewById(R.id.nextButton);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent (getApplicationContext(),firstActivity.class);
                startActivity(intent);
            }
        });
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String levelSelected =  levelText.getText().toString();
                float period = intent.getFloatExtra("period", (float) 200.0);
                Intent intent1 =new Intent(getApplicationContext(), gameActivity.class);
                intent1.putExtra("levelSelected",levelSelected);
                intent1.putExtra("period",period);
                startActivity(intent1);

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        failSound.release();
        finish();
    }
}