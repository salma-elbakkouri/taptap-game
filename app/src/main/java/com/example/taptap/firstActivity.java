package com.example.taptap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import static android.graphics.Color.rgb;

public class firstActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView playClick;
    private TextView gameTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        playClick = findViewById(R.id.play_icon);
        playClick.setOnClickListener(this);

        gameTitle = findViewById(R.id.gameTtitle);
        gameTitle.setShadowLayer(5, 4, 4, rgb(62, 58, 58));




    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, levelsActivity.class);
        startActivity(intent);
    }
}