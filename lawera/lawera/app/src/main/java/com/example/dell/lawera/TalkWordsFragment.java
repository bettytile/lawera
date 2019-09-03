package com.example.dell.lawera;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class TalkWordsFragment extends Fragment {
    DatabaseHandler database;
    RecyclerView recyclerView;
    WordsRecyclerAdapter recycler;
    List<Item> datamodel;
    Sentence sentence;



    public static TalkWordsFragment newInstance() {
        TalkWordsFragment fragment = new TalkWordsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_talk_words, container, false);
        datamodel = new ArrayList<Item>();
        recyclerView = (RecyclerView) rootview.findViewById(R.id.recycler_items_list);
        database = new DatabaseHandler(getActivity());
//        sentence = new Sentence(getActivity());
        datamodel = database.getdata();
        recycler = new WordsRecyclerAdapter(getContext(),datamodel);


        Log.i("HIteshdata",""+datamodel);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(),3);
//      RecyclerView.LayoutManager reLayoutManager =new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recycler);
        return rootview;
    }

}
