package com.example.dell.lawera;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class RoutineFragment extends Fragment {

    public static RoutineFragment newInstance() {
        RoutineFragment fragment = new RoutineFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_routine, container, false);

        ImageView morning= rootView.findViewById(R.id.dayIv);
        final ImageView night = rootView.findViewById(R.id.nightIv);
        CardView night_card = rootView.findViewById(R.id.evening_card);
        CardView morning_card = rootView.findViewById(R.id.morning_card);
//        Glide.with(this).load(item_img).fitCenter().into(item_image);
//        Glide.with(this).load(R.drawable.routinmoon).into(night);
//        Glide.with(this).load(R.drawable.routinsun).into(morning);
        morning_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Routines.class);
                intent.putExtra("routine","morning");
                startActivity(intent);
            }
        });
        night_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Routines.class);
                intent.putExtra("routine","night");
                startActivity(intent);
            }
        });


        return rootView;
    }
}