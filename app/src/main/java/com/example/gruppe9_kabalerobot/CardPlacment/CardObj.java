package com.example.gruppe9_kabalerobot.CardPlacment;

public class CardObj {

    int x, y, value, suits;
    public CardObj(int x, int y, int value, int suits){
        this.x = x;
        this.y = y;
        this.suits = suits;
        this. value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
