package com.example.hunter1.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.hunter1.R;
import com.example.hunter1.TopTenManager;

public class Fragment_Scores extends Fragment {

    private AppCompatActivity activity;
    private TopTenManager topTenManager;


    public Fragment_Scores(AppCompatActivity activity){

        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("pttt", "onCreateView - Fragment_Scores");

//        MSP.getMe().putStringToSP("TopTen","Hiiiiiiii");
//
//        String s = MSP.getMe().getStringFromSP("TopTen","");;
//
//        Log.d("Anat", s);
        topTenManager = new TopTenManager();

        View view = inflater.inflate(R.layout.fragment_scores, container, false);
        topTenManager.findViews(view);
        topTenManager.initViews();



        return view;
    }



}
