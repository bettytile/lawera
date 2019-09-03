package com.example.dell.lawera;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class WordsFragment extends Fragment{

    CardView animals, furniture, shapes, colors, food,cloths;
    public static WordsFragment newInstance() {
        WordsFragment fragment = new WordsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_words, container, false);
        animals = rootView.findViewById(R.id.animals_card);
        furniture =rootView.findViewById(R.id.furniture_card);
        cloths = rootView.findViewById(R.id.cloths_card);
        food = rootView.findViewById(R.id.food_card);
        shapes = rootView.findViewById(R.id.shapes_card);
        colors = rootView.findViewById(R.id.colors_card);

        return rootView;
    }


}