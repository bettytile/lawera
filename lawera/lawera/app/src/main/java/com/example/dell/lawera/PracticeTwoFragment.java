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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class PracticeTwoFragment extends Fragment {
    MediaPlayer mp;
    DatabaseHandler mydb;
    String name, category;
    Dialog myDialog;

    public static PracticeTwoFragment newInstance() {
        PracticeTwoFragment fragment = new PracticeTwoFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mydb = new DatabaseHandler(getContext());
        name = getActivity().getIntent().getStringExtra("keyName");
        category = getActivity().getIntent().getStringExtra("CategoryKey");
        myDialog = new Dialog(getContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_practice_two, container, false);

        final Cursor res = mydb.getdatabyname(name);
        ImageView item_image =rootView.findViewById(R.id.item_image);
        TextView ch_one_tv = rootView.findViewById(R.id.ch_one_TextView);
        TextView ch_two_tv = rootView.findViewById(R.id.ch_two_TextView);
        TextView ch_three_tv = rootView.findViewById(R.id.ch_three_TextView);


        while (res.moveToNext()){
            mp = MediaPlayer.create(getContext(), res.getInt(9));
            final String word = res.getString(4);
            final String ch_one_txt = res.getString(5);
            final String ch_two_txt = res.getString(7);
            final String ch_three_txt = res.getString(11);
            int item_img = res.getInt(3);

            Glide.with(this).load(item_img).fitCenter().into(item_image);
            ch_one_tv.setText(ch_one_txt);
            ch_two_tv.setText(ch_two_txt);
            ch_three_tv.setText(ch_three_txt);
            ch_one_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    check(word,ch_one_txt);
                }
            });
            ch_two_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    check(word,ch_two_txt);
                }
            });
            ch_three_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    check(word,ch_three_txt);
                }
            });
        }
        mp.start();
        FloatingActionButton fab_next = (FloatingActionButton) rootView.findViewById(R.id.fab_next);
        Glide.with(getContext()).load(R.drawable.next_white).into(fab_next);
        fab_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, LessonItemFragment.newInstance());
                transaction.commit();
            }
        });
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
        if (status == "yes"){
            Vibrator vibrator = (Vibrator)getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(50);
            PlaySound("yes");
            myDialog.setContentView(R.layout.pop_up_positive);
        }else {
            myDialog.setContentView(R.layout.pop_up_negative);
            Vibrator vibrator = (Vibrator)getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(300);
            PlaySound("no");
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

}
