package com.example.dell.lawera;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Routines extends AppCompatActivity implements View.OnClickListener {
    ImageView first,two,three,four,five,six;
    TextView toolbar_title;
    MediaPlayer mp;
    String routine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setElevation(0);


        first = findViewById(R.id.first);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        toolbar_title = findViewById(R.id.toolbar_title);


        routine = getIntent().getStringExtra("routine");
        switch (routine){
            case "night":
                toolbar_title.setText("ማታ ማታ");
                Glide.with(this).load(R.drawable.washingroutin).into(first);
                Glide.with(this).load(R.drawable.dinner).into(two);
                Glide.with(this).load(R.drawable.washingroutin).into(three);
                Glide.with(this).load(R.drawable.waringclothe).into(four);
                Glide.with(this).load(R.drawable.sleeping).into(five);
//                Glide.with(this).load(R.drawable.waringclothe).into(four);
                break;
            case "morning":
                toolbar_title.setText("ጠዋት ጠዋት");
                Glide.with(this).load(R.drawable.wakeup).into(first);
                Glide.with(this).load(R.drawable.washingroutin).into(two);
                Glide.with(this).load(R.drawable.waringclothe).into(three);
                Glide.with(this).load(R.drawable.eatingroutin).into(four);
//                Glide.with(this).load(R.drawable.wakeup).into(five);
                break;
        }

    }
//    public String time(String string){
//
//        return string;
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            String string = getIntent().getStringExtra("routine");
            case R.id.first:
                if (routine.equals("night")){
                    mp = MediaPlayer.create(Routines.this, R.raw.washhands);
                    mp.start();
                }else {
                    mp = MediaPlayer.create(Routines.this, R.raw.wakeupp);
                    mp.start();
                }
                break;
            case R.id.two:
                if (getIntent().getStringExtra("routine").equals("night")){
                    mp = MediaPlayer.create(Routines.this, R.raw.dinnereating);
                    mp.start();
                }else {
                    mp = MediaPlayer.create(Routines.this, R.raw.washhands);
                    mp.start();
                }
                break;
            case R.id.three:
                if (getIntent().getStringExtra("routine").equals("night")){
                    mp = MediaPlayer.create(Routines.this, R.raw.washhands);
                    mp.start();
                }else {
                    mp = MediaPlayer.create(Routines.this, R.raw.wearchoth);
                    mp.start();
                }
                break;
            case R.id.four:
                if (getIntent().getStringExtra("routine").equals("night")){
                    mp = MediaPlayer.create(Routines.this, R.raw.wearchoth);
                    mp.start();
                }else {
                    mp = MediaPlayer.create(Routines.this, R.raw.breakfasteat);
                    mp.start();
                }
                break;
            case R.id.five:
                if (getIntent().getStringExtra("routine").equals("night")) {
                    mp = MediaPlayer.create(Routines.this, R.raw.sleep);
                    mp.start();
                }
//                }else {
//                    mp = MediaPlayer.create(Routines.this, R.raw.washhands);
//                    mp.start();
//                }
                break;
        }
    }
}
