package com.example.hunter1;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hunter1.objects.Player;
import com.example.hunter1.objects.Square;

import java.util.ArrayList;

public class GameManager {

    private ArrayList<Square> squares;
    private ArrayList<Player> scores;
    private int currentHunterIndex = 5;
    private int currentBearIndex = 9;
    private int score= 0;
    private int lives = 3;

    public GameManager(AppCompatActivity activity_main) {

        squares = GameDataManager.buildDataSquaresArray(activity_main);

    }



    public void updateSortedScoresToScoreObjectsArray(ArrayList<Integer> scoresList){

        for (int i = 0; i < scoresList.size(); i++) {
            scores.get(i).setScore(scoresList.get(i));
        }
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

    public ArrayList<Player> getScores() {
        return scores;
    }


}
