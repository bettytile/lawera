package com.example.dell.lawera;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Test extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences settings;
    String levelOne;
    public static final String PREFS_NAME = "TEST_LEVEL_PREFS";

    MediaPlayer mp;
    DatabaseHandler mydb;
    String name, category,level,levelpref;
    Dialog myDialog;
    Intent intent;
//    TextView score;
    private int mScore = 0;
    private int questionNumber=0;
    CardView cardView;
    SharedPreferences.Editor editor;
    ImageView ch_one_iv,ch_two_iv,ch_three_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setElevation(0);
//        score = (TextView) findViewById(R.id.score_ok_button);
        mydb = new DatabaseHandler(this);
        name = getIntent().getStringExtra("keyName");
        category = getIntent().getStringExtra("CategoryKey");
        level = getIntent().getStringExtra("LevelKey");
        levelpref = getIntent().getStringExtra("pref");
        myDialog = new Dialog(this);
        settings = getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2
        editor.putString("LevelOne", "1");
        editor.putString("LevelTwo", "0");
        editor.putString("LevelThree", "0");
        editor.putString("LevelFour", "0");
        editor.putString("LevelFive", "0");
        editor.putString("LevelSix", "0");
        editor.putString("LevelSeven", "0");
        editor.putString("LevelEight", "0");
        editor.putString("LevelNine", "0");
        editor.putString("LevelTen", "0");//3
        editor.apply();

        final Cursor res = mydb.getDataByLevel(level);
        final ImageView ch_one_iv=findViewById(R.id.ch_one_item_image);
        final ImageView ch_two_iv=findViewById(R.id.ch_two_item_image);
        final ImageView ch_three_iv=findViewById(R.id.ch_three_item_image);
        final CardView cardView = findViewById(R.id.ch_one_card);
        final TextView sc = findViewById(R.id.score);


        enableButtons();
        generateTest(res, ch_one_iv, ch_two_iv, ch_three_iv, cardView);
//        final Cursor res = mydb.getdatabyname(name);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mp = MediaPlayer.create(Test.this, res.getInt(9));
                mp.start();

            }
        });

       final FloatingActionButton fab_next = (FloatingActionButton) findViewById(R.id.fab_next);
        fab_next.setEnabled(false);
        fab_next.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.disabled_fab)));
        Glide.with(this).load(R.drawable.next_white).into(fab_next);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

generateTest(res, ch_one_iv, ch_two_iv, ch_three_iv, cardView);

                enableButtons();
                fab_next.setEnabled(false);
                fab_next.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.disabled_fab)));
            }
        });
    }
    public void getQuestions(){
        final Cursor res = mydb.getDataByLevel(level);
    }

public void calculateResult(String word, String choice){

//      questionNumber = questionNumber+1;

    if (word.equals(choice)){
        notifyResult("yes");
        mScore = mScore + 1;
        questionNumber = questionNumber+1;
        FloatingActionButton fab_next = (FloatingActionButton) findViewById(R.id.fab_next);
        fab_next.setEnabled(true);
        fab_next.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
        disableButtons();
    }else{
        notifyResult("no");
        questionNumber = questionNumber+1;
        disableButtons();
    }
}
public void generateTest(Cursor res,ImageView ch_one_iv,ImageView ch_two_iv,ImageView ch_three_iv,CardView cardView){
    res.moveToNext();
    if (res.isAfterLast()){

        notifyResult("score");
        res.moveToFirst();
    }
    mp = MediaPlayer.create(Test.this, res.getInt(9));
    final String word = res.getString(4);
    final String ch_one_txt = res.getString(5);
    final String ch_two_txt = res.getString(7);
    final String ch_three_txt = res.getString(11);
    int ch_one = res.getInt(6);
    int ch_two = res.getInt(8);
    int ch_three = res.getInt(12);

    Glide.with(getApplicationContext()).load(ch_one).fitCenter().into(ch_one_iv);
    Glide.with(getApplicationContext()).load(ch_two).fitCenter().into(ch_two_iv);
    Glide.with(getApplicationContext()).load(ch_three).fitCenter().into(ch_three_iv);
    TextView itemword = findViewById(R.id.questionTextView);
    itemword.setText(word);

    cardView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            calculateResult(word,ch_one_txt);
        }
    });
    ch_two_iv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            calculateResult(word,ch_two_txt);
        }
    });
    ch_three_iv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            calculateResult(word,ch_three_txt);
        }
    });

    mp.start();

}


    public void notifyResult(String status) {

        if (status == "yes") {
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(50);
//            PlaySound("yes");
            myDialog.setContentView(R.layout.pop_up_positive);

        }if (status == "score") {
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(300);
//            PlaySound("yes");

            myDialog.setContentView(R.layout.pop_up_score);
            TextView score = myDialog.findViewById(R.id.score_text);

            score.setText(String.valueOf(mScore)+"/"+String.valueOf(questionNumber));

            myDialog.show();
            updateUserLevel(levelpref,mScore,questionNumber);

        } if (status=="no"){
//            PlaySound("no");
            myDialog.setContentView(R.layout.pop_up_negative);
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(300);
            FloatingActionButton fab_next = (FloatingActionButton) findViewById(R.id.fab_next);
            fab_next.setEnabled(true);
            fab_next.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));

        }

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
                Intent i = new Intent();
                i.putExtra("CategoryKey",category);
                setResult(RESULT_OK,i);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.score_ok_button:
                intent = new Intent(this,Learn.class);
                startActivity(intent);
                break;
            case R.id.ok_button:

                myDialog.dismiss();

                break;
        }
    }


    public void updateUserLevel(String levelpref, int score, int questionNumber){
        int range = questionNumber - 1;
        if (score>=range){
            settings = getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
            editor = settings.edit();
            editor.putString(levelpref, "1");
            editor.commit();
        }
    }
    public void disableButtons(){
        ImageView ch_one_iv=findViewById(R.id.ch_one_item_image);
        ImageView ch_two_iv=findViewById(R.id.ch_two_item_image);
        ImageView ch_three_iv=findViewById(R.id.ch_three_item_image);
        CardView cardView = findViewById(R.id.ch_one_card);
        ch_one_iv.setEnabled(false);
        ch_two_iv.setEnabled(false);
        ch_three_iv.setEnabled(false);
        cardView.setEnabled(false);
    }
    public void enableButtons(){
        ImageView ch_one_iv=findViewById(R.id.ch_one_item_image);
        ImageView ch_two_iv=findViewById(R.id.ch_two_item_image);
        ImageView ch_three_iv=findViewById(R.id.ch_three_item_image);
        CardView cardView = findViewById(R.id.ch_one_card);
        ch_one_iv.setEnabled(true);
        ch_two_iv.setEnabled(true);
        ch_three_iv.setEnabled(true);
        cardView.setEnabled(true);
    }
//    public void updateScore (int point){
//        score.setText("" + mScore);
//    }
}
