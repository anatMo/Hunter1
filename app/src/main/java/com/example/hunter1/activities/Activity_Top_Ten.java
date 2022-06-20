package com.example.hunter1.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hunter1.R;
import com.example.hunter1.fragments.Fragment_Map;
import com.example.hunter1.fragments.Fragment_Scores;


public class Activity_Top_Ten extends AppCompatActivity {

    private Fragment_Scores fragment_scores;
    private Fragment_Map fragment_map;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_ten);

        fragment_scores = new Fragment_Scores(this);
        Log.d("Anat", "fragment_scores");

//        fragment_scores.setCallBack_listToActivity(callBack_activityTitle);
//        fragment_scores.setCallBack_listAnimalClicked(callBack_listAnimalClicked);
        getSupportFragmentManager().beginTransaction().add(R.id.topTen_LAY_scores, fragment_scores).commit();

//        fragment_details = new Fragment_Details();
//        fragment_details.setCallBack_ActivityTitle(callBack_activityTitle);
//        getSupportFragmentManager().beginTransaction().add(R.id.animals_LAY_details, fragment_details).commit();

        fragment_map = new Fragment_Map(this);
        getSupportFragmentManager().beginTransaction().add(R.id.topTen_LAY_map, fragment_map).commit();
    }

}
