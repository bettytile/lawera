package com.example.dell.lawera;


import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class LessonItemFragment extends Fragment {
    MediaPlayer mp;
    DatabaseHandler mydb;
    String name, category;

    public static LessonItemFragment newInstance() {
        LessonItemFragment fragment = new LessonItemFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_lesson_item, container, false);
        mydb = new DatabaseHandler(getContext());
        name = getActivity().getIntent().getStringExtra("keyName");
        category = getActivity().getIntent().getStringExtra("CategoryKey");

        final Cursor res = mydb.getdatabyname(name);

        while (res.moveToNext()){
            mp = MediaPlayer.create(getContext(), res.getInt(9));
            String word = res.getString(4);
            int img = res.getInt(3);
            ImageView itemImage=rootView.findViewById(R.id.item_image);
            Glide.with(this).load(img).fitCenter().into(itemImage);
            TextView itemword = rootView.findViewById(R.id.itemtextView);
            itemword.setText(word);
        }
        mp.start();

        FloatingActionButton fab_next = (FloatingActionButton) rootView.findViewById(R.id.fab_next);
        Glide.with(this).load(R.drawable.next_white).into(fab_next);
        fab_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, PracticeOneFragment.newInstance());
                transaction.commit();
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

}
