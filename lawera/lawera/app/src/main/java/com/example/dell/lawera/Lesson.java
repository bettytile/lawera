package com.example.dell.lawera;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Lesson extends AppCompatActivity {
    MediaPlayer mp;
    DatabaseHandler mydb;
    String name, category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setElevation(0);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, LessonItemFragment.newInstance());
        transaction.commit();


        mydb = new DatabaseHandler(this);
        name = getIntent().getStringExtra("keyName");
        category = getIntent().getStringExtra("CategoryKey");

        final Cursor res = mydb.getdatabyname(name);

        res.moveToNext();
            mp = MediaPlayer.create(Lesson.this, res.getInt(9));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = mydb.getdatabyname(name);
                res.moveToNext();
                    mp = MediaPlayer.create(Lesson.this, res.getInt(9));
                    mp.start();

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lesson_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
//            case R.id.action_next:
//                mydb = new DatabaseHandler(this);
//                name = getIntent().getStringExtra("keyName");
//                category = getIntent().getStringExtra("CategoryKey");
//
//                final Cursor res = mydb.getdatabyname(name);
//                res.moveToNext();
//                if (res.isAfterLast()){
//                    res.moveToFirst();
//                }
//                Toast.makeText(getApplicationContext(),"j",Toast.LENGTH_LONG).show();
//                break;
            case android.R.id.home:
                Intent intent = new Intent();
                intent.putExtra("CategoryKey",category);
                setResult(RESULT_OK,intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
