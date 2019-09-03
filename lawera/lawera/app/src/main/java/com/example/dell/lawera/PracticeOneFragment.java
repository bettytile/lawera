package com.example.dell.lawera;


import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class PracticeOneFragment extends Fragment {
    MediaPlayer mp;
    DatabaseHandler mydb;
    String name, category;
    Dialog myDialog;
    Test test;


    public static PracticeOneFragment newInstance() {
        PracticeOneFragment fragment = new PracticeOneFragment();
        return fragment;
    }

        @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mydb = new DatabaseHandler(getContext());
        name = getActivity().getIntent().getStringExtra("keyName");
        category = getActivity().getIntent().getStringExtra("CategoryKey");
        myDialog = new Dialog(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView =  inflater.inflate(R.layout.fragment_practice_one, container, false);
        final Cursor res = mydb.getdatabyname(name);
        ImageView ch_one_iv=rootView.findViewById(R.id.ch_one_item_image);
        ImageView ch_two_iv=rootView.findViewById(R.id.ch_two_item_image);
        ImageView ch_three_iv=rootView.findViewById(R.id.ch_three_item_image);
        CardView cardView = rootView.findViewById(R.id.ch_one_card);

        res.moveToNext();
        if (res.isAfterLast()){
            res.moveToFirst();
        }
            mp = MediaPlayer.create(getContext(), res.getInt(9));
            final String word = res.getString(4);
            final String ch_one_txt = res.getString(5);
            final String ch_two_txt = res.getString(7);
            final String ch_three_txt = res.getString(11);
            int ch_one = res.getInt(6);
            int ch_two = res.getInt(8);
            int ch_three = res.getInt(12);

            Glide.with(this).load(ch_one).fitCenter().into(ch_one_iv);
            Glide.with(this).load(ch_two).fitCenter().into(ch_two_iv);
            Glide.with(this).load(ch_three).fitCenter().into(ch_three_iv);
            TextView itemword = rootView.findViewById(R.id.questionTextView);
            itemword.setText(word);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    check(word,ch_one_txt);
                }
            });
            ch_two_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    check(word,ch_two_txt);
                }
            });
            ch_three_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    check(word,ch_three_txt);
                }
            });

        mp.start();

        FloatingActionButton fab_next = (FloatingActionButton) rootView.findViewById(R.id.fab_next);
        Glide.with(getContext()).load(R.drawable.next_white).into(fab_next);
        fab_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, PracticeTwoFragment.newInstance());
                transaction.commit();
            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }
    public void check(String word, String choice){
        if (word.equals(choice)){
            ShowPopup(getView(),"yes");
        }else{
            ShowPopup(getView(),"no");
        }
    }

    public void ShowPopup(View v,String status) {

        Button okButton;
        if (status == "yes") {
            Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(50);
            PlaySound("yes");
            myDialog.setContentView(R.layout.pop_up_positive);
        } else {
            PlaySound("no");
            myDialog.setContentView(R.layout.pop_up_negative);
            Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(300);

        }
        okButton = (Button) myDialog.findViewById(R.id.ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
    public void PlaySound(String status){
        if(status=="yes"){
            mp = MediaPlayer.create(getContext(), R.raw.happykids);
            mp.start();
        }else {
            mp = MediaPlayer.create(getContext(), R.raw.sound_wrong);
            mp.start();
        }
    }
//    @Override
//
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//
//        inflater.inflate(R.menu.lesson_menu, menu);
//
//        super.onCreateOptionsMenu(menu, inflater);
//
//    }


}
