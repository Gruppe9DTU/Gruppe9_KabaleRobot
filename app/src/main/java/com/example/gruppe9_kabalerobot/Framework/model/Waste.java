package com.example.gruppe9_kabalerobot.Framework.model;

import java.util.ArrayList;
import java.util.List;

public class Waste {
    private List<Card> knowncards = new ArrayList<Card>();
    private List<Card> unknowncards;
    private int iterator = 0;
    private boolean pileStatus; //TODO Why is this here? Why not just check the status and return that?

    public Waste(List<Card> cards, boolean pileStatus) {
        unknowncards = cards;
        this.pileStatus = pileStatus;
    }

    public Card takeCard() {
        Card temp = knowncards.remove(iterator);
        if(iterator == knowncards.size()-1)
            iterator--;
        return temp;
    }

    public Card revealCard() {
        if(unknowncards.size() > 0) {
            Card revealed = unknowncards.remove(unknowncards.size() - 1);
            addToKnown(revealed);
            iterator = knowncards.size() - 1;
            return revealed;
        }
        else {
            if(iterator > knowncards.size()-1) {
                iterator = 0;
            }
            Card temp = knowncards.get(iterator);
            iterator++;
            return temp;
        }
    }

    public void addToKnown(Card card) { knowncards.add(card); }
    public void addListToKnown(List<Card> cards) { knowncards = cards; }

    public List<Card> getKnownCards() { return knowncards; }

    public int wasteSize() { return unknowncards.size(); }

    public Card lookAtTop(){
        return knowncards.size() > 0 ? knowncards.get(iterator) : null;
        //TODO Cleanup: delete if works
//        try {
//            return knowncards.get(iterator);
//        }catch (IndexOutOfBoundsException e){
//            //
//        }
//        return null;
    }

    public void setPileStatus(boolean pileStatus) { this.pileStatus = pileStatus; }
    public boolean getPileStatus() { return pileStatus; }
}
