package com.example.dell.lawera;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

public class Learn extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    public static final String PREFS_NAME = "TEST_LEVEL_PREFS";
    SharedPreferences settings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setElevation(0);

        SharedPreferences  testLevels = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

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

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = WordsFragment.newInstance();
                                break;
                            case R.id.action_item2:
                                selectedFragment = RoutineFragment.newInstance();
                                break;
                            case R.id.action_item3:
                                selectedFragment = TestFragment.newInstance();
                                break;
                            case R.id.action_item4:
                                selectedFragment = MeFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, WordsFragment.newInstance());
        transaction.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_plus:
                Intent i = new Intent(this,CustomInsert.class);
                startActivity(i);
////                NavUtils.navigateUpFromSameTask(this);
//                Intent i = new Intent();
//                i.putExtra("CategoryKey",category);
//                setResult(RESULT_OK,i);
//                finish();
//                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.animals_card:
                intent = new Intent(this,LessonItemsList.class);
                intent.putExtra("CategoryKey","animals");
                startActivity(intent);
                break;
            case R.id.cloths_card:
                intent = new Intent(this,LessonItemsList.class);
                intent.putExtra("CategoryKey","cloths");
                startActivity(intent);
                break;
            case R.id.furniture_card:
                intent = new Intent(this,LessonItemsList.class);
                intent.putExtra("CategoryKey","furniture");
                startActivity(intent);
                break;
            case R.id.colors_card:
                intent = new Intent(this,LessonItemsList.class);
                intent.putExtra("CategoryKey","colors");
                startActivity(intent);
                break;
            case R.id.shapes_card:
                intent = new Intent(this,LessonItemsList.class);
                intent.putExtra("CategoryKey","shapes");
                startActivity(intent);
                break;
            case R.id.food_card:
                intent = new Intent(this,LessonItemsList.class);
                intent.putExtra("CategoryKey","food");
                startActivity(intent);
                break;
        }
    }
    public void onClickk(View view) {

        switch (view.getId()){
            case R.id.test_one_card:
                intent = new Intent(this,Test.class);
                intent.putExtra("LevelKey","1");
                intent.putExtra("pref","LevelTwo");
                startActivity(intent);
                break;
            case R.id.test_two_card:
                intent = new Intent(this,Test.class);
                intent.putExtra("LevelKey","2");
                intent.putExtra("pref","LevelThree");
                startActivity(intent);
                break;
            case R.id.test_three_card:
                intent = new Intent(this,Test.class);
                intent.putExtra("LevelKey","3");
                intent.putExtra("pref","LevelFour");
                startActivity(intent);
                break;
            case R.id.test_four_card:
                intent = new Intent(this,Test.class);
                intent.putExtra("LevelKey","4");
                intent.putExtra("pref","LevelFive");
                startActivity(intent);
                break;

        }
    }

}
