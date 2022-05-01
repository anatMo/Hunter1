package com.example.hunter1;

import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class GameManager {

    private ArrayList<Square> squares;
    private int currentHunterIndex = 5;
    private int currentBearIndex = 9;
    private int score= 0;
    private int lives = 3;

    public GameManager(Activity_Main activity_main) {

        squares = GameDataManager.buildData(activity_main);
    }

    public int getLives() {
        return lives;
    }

    public void reduceLives() {
        lives--;
    }
    public boolean isDead() {
        return lives <= 0;
    }

    public ArrayList<Square> getSquares() {
        return squares;
    }
}
