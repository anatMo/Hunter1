package com.example.hunter1;

import android.widget.ImageView;

public class Square {

    private int index;
    private ImageView hunterIMG;
    private ImageView bearIMG;

    public Square(){

    }

    public int getIndex() {
        return index;
    }

    public Square setIndex(int index) {
        this.index = index;
        return this;
    }

    public ImageView getHunterIMG() {
        return hunterIMG;
    }

    public Square setHunterIMG(ImageView hunterIMG) {
        this.hunterIMG = hunterIMG;
        return this;
    }

    public ImageView getBearIMG() {
        return bearIMG;
    }

    public Square setBearIMG(ImageView bearIMG) {
        this.bearIMG = bearIMG;
        return this;
    }
}
