package com.example.hunter1.objects;

import java.io.Serializable;

public class Player implements Serializable {

    private int score;
    private String location;

    public Player(){ }


    public int getScore() {
        return score;
    }

    public Player setScore(int score) {
        this.score = score;
        return this;
    }



}
