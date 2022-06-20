package com.example.hunter1.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hunter1.R;
import com.google.android.material.button.MaterialButton;

public class Activity_Menu extends AppCompatActivity {

    private MaterialButton menu_BTN_playGame;
    private MaterialButton menu_BTN_topTen;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViews();

        menu_BTN_playGame.setOnClickListener(view -> {
            Intent intent = new Intent(this, Activity_Main_Sensors.class);
            startActivity(intent);
        });

        menu_BTN_topTen.setOnClickListener(view -> {
            Intent intent = new Intent(this, Activity_Top_Ten.class);
            startActivity(intent);
        });
    }

    private void findViews() {
        menu_BTN_playGame= findViewById(R.id.menu_BTN_startGame);
        menu_BTN_topTen= findViewById(R.id.menu_BTN_topTen);
    }
}
