package com.example.gruppe9_kabalerobot.Framework.model;

/**
 * Class to act as a playing card from a normal deck of cards
 */
public class Card {
    private int suit;   //What suit that card is: Heart, spade, etc.
    private int value;  //What value it has, from ace to king (1-13)

    /***
     * Constructor for the Card object. A playing card.
     *
     * @param suit a numerical value of suit, 0 to 3
     * @param value a numerical value of the card from 1 to 13.
     */
    public Card (int suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    /**
     * Getters and Setters for Card class
     */
    public int getSuit() { return this.suit; }
    public void setSuit(int suit) { this.suit = suit; }

    public int getValue() { return this.value; }
    public void setValue(int value) { this.value = value; }

    /***
     * A method to get the suit in string form
     *
     * @return A string explaining the suit, depending on the value of Card.suit
     */
    public String readSuit() {
        switch(suit) {
            case 0:
                return "Hjerter";
            case 1:
                return "Spar";
            case 2:
                return "Ruder";
            case 3:
                return "Klør";
            default:
                return "[Fejl i læsning af kulør]";
        }
    }

    /***
     * A method to convert a card to a string
     *
     * @return Full description of the card
     */
    public String toString() {
        switch(value){
            case 1:
                return readSuit() + " Es";
            case 11:
                return readSuit() + " Knægt";
            case 12:
                return readSuit() + " Dronning";
            case 13:
                return readSuit() + " Konge";
            default:
                return readSuit() + " "+ value;
        }
    }

    /**
     * A method to give a short print of the card
     *
     * @return  Short description of the card
     */
    public String shortString() {
        switch (value) {
            case 1:
                return "Ac" + readSuit().charAt(0);
            case 10:
                return "10" + readSuit().charAt(0);
            case 11:
                return "Ja" + readSuit().charAt(0);
            case 12:
                return "Qu" + readSuit().charAt(0);
            case 13:
                return "Ki" + readSuit().charAt(0);
            default:
                return "0" + value + readSuit().charAt(0);
        }
    }
}