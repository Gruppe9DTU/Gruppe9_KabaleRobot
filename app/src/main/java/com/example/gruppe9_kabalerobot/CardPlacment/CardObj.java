package com.example.gruppe9_kabalerobot.CardPlacment;

public class CardObj {

    private int x, y, value, suit;
    public CardObj(int x, int y, int value, int suit){
        this.x = x;
        this.y = y;
        this.suit = suit;
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

    public int getSuit() { return suit; }

    public int getValue() { return value; }
}
