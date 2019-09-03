package com.example.dell.lawera;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LessonItemsList extends AppCompatActivity {
    DatabaseHandler database;
    RecyclerView recyclerView;
    CategoryRecycleAdapter recycler;
    List<Item> datamodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_items_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setElevation(0);
        String selected_category = getIntent().getStringExtra("CategoryKey");
        TextView toolbarText = findViewById(R.id.lesson_toolbar_title);
        switch (selected_category){
            case "animals":
                toolbarText.setText("እንስሳት");
                break;
            case "shapes":
                toolbarText.setText("የሰውነት ክፍል");
                break;
            case "colors":
                toolbarText.setText("ቀለማት");
                break;
            case "furniture":
                toolbarText.setText("የቤት እቃዎች");
                break;
            case "food":
                toolbarText.setText("ምግብ");
                break;
            case "cloths":
                toolbarText.setText("አልባሳት");
                break;
        }



        datamodel = new ArrayList<Item>();
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        database = new DatabaseHandler(LessonItemsList.this);
        datamodel = database.getdatabycategory(selected_category);
        recycler = new CategoryRecycleAdapter(LessonItemsList.this,datamodel);


        Log.i("HIteshdata",""+datamodel);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
//      RecyclerView.LayoutManager reLayoutManager =new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recycler);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 2404){
            if (data != null){
                String selected_category = data.getStringExtra("CategoryKey");
            }
        }
    }
}
