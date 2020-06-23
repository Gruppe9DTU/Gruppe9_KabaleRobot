package com.example.gruppe9_kabalerobot.Framework.model;

import java.util.List;

/**
 * Class to act as Waste of a game of Solitaire. Waste is where the rest of the cards in the deck is placed in the beginning and the player looks through throughout the game to find usable cards.
 * This Class is simplified to looks as an instance of the game, and not collect information of the cards in Waste throughout the game
 */
public class Waste {
    private boolean wastePilePresent;   //Boolean to mark if there is a pile of cards to take a card from
    private List<Card> knownCards;      //List of known cards

    /**
     * Constructor for Waste class
     *
     * @param wastePilePresent  Boolean to mark the presence of a waste pile
     * @param knowCards         List of instances of Card
     */
    public Waste(boolean wastePilePresent, List<Card> knowCards) {
        this.wastePilePresent = wastePilePresent;
        this.knownCards = knowCards;
    }

    /**
     * Adds a list of cards to the list of known cards
     *
     * @param cards List of Card to be added
     */
    public void addListToKnown(List<Card> cards) { knownCards = cards; }

    /**
     * Getter for list of cards, from the pile of known cards in Waste
     *
     * @return  List of instances of Card
     */
    public List<Card> getKnownCards() { return knownCards; }

    /**
     * Getter for card on top of the known cards pile in Waste
     *
     * @return  Instance of Card
     */
    public Card lookAtTop(){ return knownCards != null && knownCards.size() > 0 ? knownCards.get(0) : null; }

    /**
     * Getter for status of pile of cards in Waste
     *
     * @return  True if there are cards to be revealed
     */
    public boolean isWastePilePresent() { return wastePilePresent;}
    public void setWastePile(boolean status) { this.wastePilePresent = status; }
}