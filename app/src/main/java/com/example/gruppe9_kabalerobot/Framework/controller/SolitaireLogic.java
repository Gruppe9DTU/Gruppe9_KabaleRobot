package com.example.gruppe9_kabalerobot.Framework.controller;

import com.example.gruppe9_kabalerobot.Framework.model.Card;
import com.example.gruppe9_kabalerobot.Framework.model.Foundation;
import com.example.gruppe9_kabalerobot.Framework.model.Tableau;
import com.example.gruppe9_kabalerobot.Framework.model.Waste;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to illustrate a game of Solitaire
 */
public class SolitaireLogic {
    private Waste waste;
    private Tableau[] tableau;
    private Foundation[] foundation;

    /**
     * Constructor for the controller
     */
    public SolitaireLogic() {
        waste = new Waste(false, null);
        tableau = new Tableau[7];
        for(int i = 0 ; i < 7 ; i++){
            tableau[i] = new Tableau(0, null);
        }
        foundation = new Foundation[4];
    }

    /**
     * A printet version of the current game state
     */
    public String getGameState() {
        //Waste
        String wasteNfoundation, shownWaste = "";
//        for(Card c : waste.getKnownCards()) { shownWaste += c.shortString() + "|"; }
        if(waste.getKnownCards().size() != 0) {
            shownWaste += waste.getKnownCards().get(waste.getKnownCards().size() - 1).shortString();
        } else shownWaste += "Emp ";
        //Foundation
        String foundationString = "";
        for(int i = 0 ; i < foundation.length ; i++){
            foundationString += foundation[i].countCards() > 0 ? foundation[i].peekCard().shortString()+ " " : "Emp ";
        }
        wasteNfoundation = waste.isWastePilePresent() ? "W" : "Emp";
        wasteNfoundation += "|"+ shownWaste + "     " + foundationString;
        System.out.println(wasteNfoundation);
        //Tableau
        String tableauLengths = "";
        String tableauValues = "";
        for(int i = 0 ; i < tableau.length ; i++) {
            tableauLengths += " " + tableau[i].countHiddenCards() + "  ";
            tableauValues += tableau[i].getVisibleCards().size() == 0 ? "Emp " : tableau[i].getTopCard().shortString() + " " ;
        }

        return (tableauLengths + "\n" + tableauValues);
    }

    /**
     * Getters and setters for Tableau and Foundation Lists, and Waste class
     */
    public Tableau[] getTableau() { return tableau; }
    public void setTableaus(List<Integer> hiddenCards, List<List<Card>> transTableaus) {
        for (int i = 0 ; i < 7 ; i++) {
            if(!transTableaus.isEmpty())
                tableau[i] = new Tableau(hiddenCards.get(i), transTableaus.get(i));
            else tableau[i] = new Tableau(hiddenCards.get(i), new ArrayList<>());
        }
    }

    public Foundation[] getFoundation() { return foundation; }
    public void setFoundations(List<Card> cards) {
        for(int i = 0 ; i < 4 ; i++) {
            if(i < cards.size() && cards.get(i) != null)
                foundation[i] = new Foundation(cards.get(i));
            else foundation[i] = new Foundation();
        }
    }

    public Waste getWaste() { return waste; }
    public void setWaste(Waste waste) {
        this.waste = waste;
    }

    /**
     * Used for tests, to easily set the tableau
     * @param foundation    Foundation to overwrite this
     */
    public void setFoundation(Foundation[] foundation){
        this.foundation = foundation;
    }
}