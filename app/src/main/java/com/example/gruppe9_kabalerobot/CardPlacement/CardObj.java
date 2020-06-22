package com.example.gruppe9_kabalerobot.CardPlacement;

/**
 * Class to act as an Card, with the information of where it is on a matrix
 */
public class CardObj {
    private int x, y, value, suit;

    public CardObj(int x, int y, int value, int suit){
        this.x = x;
        this.y = y;
        this.suit = suit;
        this. value = value;
    }

    /**
     * Getters and setters
     */
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getSuit() { return suit; }
    public int getValue() { return value; }
}