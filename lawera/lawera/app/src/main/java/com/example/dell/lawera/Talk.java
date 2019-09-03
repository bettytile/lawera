package com.example.dell.lawera;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Talk extends AppCompatActivity {
    DatabaseHandler database;
    Custom custom;
    RecyclerView recyclerView;
    WordsRecyclerAdapter recycler;
    CustomWordsAdapter recycler_two;
    List<Item> datamodel;
    List<CustomItem> datamodel_two;
    Sentence sentence;
    ImageView want,dontwant,love,dontlove;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setElevation(0);

        displayItem("general");
        want = findViewById(R.id.want);
        dontwant = findViewById(R.id.dont);
        love = findViewById(R.id.love);
        dontlove = findViewById(R.id.dont_love);
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(Talk.this, R.raw.ilove);
                mp.start();
            }
        });
        dontlove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(Talk.this, R.raw.idontlove);
                mp.start();
            }
        });
        want.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(Talk.this, R.raw.iwant);
                mp.start();
            }
        });
        dontwant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(Talk.this, R.raw.dontwant);
                mp.start();
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.talk_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_letmetalk:
                                displayItem("general");
                                break;
                            case R.id.action_my_words:
                                displayItem("cloths");
//                                loadCustomData();
                                break;
                            case R.id.action_feelings:
                                displayItem("feelings");
                                break;
                            case R.id.action_food_drink:
                                 displayItem("food");
                                break;
                            case R.id.action_objects:
                                displayItem("furniture");

                                break;

                        }
                        return true;
                    }
                });

    }
    public void displayItem(String category){
        datamodel = new ArrayList<Item>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_items_list);
        database = new DatabaseHandler(Talk.this);
//        sentence = new Sentence(this);
        datamodel = database.getdatabycategory(category);
        recycler = new WordsRecyclerAdapter(this,datamodel);

        Log.i("dm1",""+datamodel);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recycler);
    }
//    public void loadCustomData(){
//        datamodel_two = new ArrayList<CustomItem>();
//        recyclerView = (RecyclerView) findViewById(R.id.recycler_items_list);
//        custom = new Custom(Talk.this);
////        sentence = new Sentence(this);
//        datamodel_two = custom.getdata();
//        recycler_two = new CustomWordsAdapter(this,datamodel_two);
//
//        Log.i("dm2",""+datamodel_two);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(recycler_two);
//    }
    public void loaddata(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_plus) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
