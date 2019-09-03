package com.example.dell.lawera;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TestFragment extends Fragment{
    public static final String PREFS_NAME = "TEST_LEVEL_PREFS";
    CardView testOne,testTwo,testThree,testFour,testFive,testSix,testSeven,testEight,testNine,testTen;
    SharedPreferences settings;
    String levelOne,levelTwo,levelThree,levelFour;
    Intent intent;
    TextView trial;
    public static TestFragment newInstance() {
        TestFragment fragment = new TestFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test, container, false);

        settings = getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        levelOne = settings.getString("LevelOne", null);
        levelTwo = settings.getString("LevelTwo", null);
        levelThree = settings.getString("LevelThree", null);
        levelFour = settings.getString("LevelFour", null);//2
//        trial = rootView.findViewById(R.id.l);
//        trial.setText(levelOne);
        testOne = rootView.findViewById(R.id.test_one_card);
        testTwo = rootView.findViewById(R.id.test_two_card);
        testThree = rootView.findViewById(R.id.test_three_card);
        testFour = rootView.findViewById(R.id.test_four_card);
//        testFive = rootView.findViewById(R.id.test_one_card);
//        testSix = rootView.findViewById(R.id.test_one_card);
//        testSeven = rootView.findViewById(R.id.test_one_card);
//        testEight = rootView.findViewById(R.id.test_one_card);
//        testNine = rootView.findViewById(R.id.test_one_card);

        checkValue(testOne,levelOne);
        checkValue(testTwo,levelTwo);
        checkValue(testThree,levelThree);
        checkValue(testFour,levelFour);



        return rootView;
    }
public void checkValue(CardView cardView,String value){
        if(value.contains("0")){
            cardView.setEnabled(false);
//            cardView.setCardBackgroundColor(ColorStateList.valueOf(getResources().getColor(R.color.disabled_fab)));
        }if (value.equals("1")){
            cardView.setEnabled(true);
        }


}


}

