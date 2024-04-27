package com.example.taptap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import static android.graphics.Color.rgb;

public class levelsActivity extends AppCompatActivity {

    private Button playButton ;
    private SeekBar levelseekbar;
    private TextView levelText;
    private TextView dragText;
    private ImageView returnButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        getIntent();

        playButton =findViewById(R.id.playButton);
        levelseekbar =findViewById(R.id.seekBar);
        levelText = findViewById(R.id.levelText);
        dragText = findViewById(R.id.dragTextView);
        returnButton = findViewById(R.id.returnButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                levelsActivity.this.finish();
            }
        });

        levelText.setShadowLayer(5,4,4,rgb(62,58,58));
        playButton.setShadowLayer(5,4,4,rgb(62,58,58));
        dragText.setShadowLayer(5,4,4,rgb(62,58,58));

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String levelSelected =  levelText.getText().toString();
                Float levelNum = Float.parseFloat(levelSelected);
                float period = Math.abs((1/levelNum)*1000);

                Intent intent = new Intent(getApplicationContext(), gameActivity.class);
                intent.putExtra("period",period);
                intent.putExtra("levelSelected",levelSelected);
                startActivity(intent);
            }

        });

        levelseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                int min = 1;
                if (progress<min)
                {
                    progress = min;
                }
                levelText.setText(String.valueOf(progress));
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