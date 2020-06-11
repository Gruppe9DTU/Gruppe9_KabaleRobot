package com.example.gruppe9_kabalerobot.Framework.controller;

import com.example.gruppe9_kabalerobot.Framework.model.Card;
import com.example.gruppe9_kabalerobot.Framework.model.Foundation;
import com.example.gruppe9_kabalerobot.Framework.model.Tableau;
import com.example.gruppe9_kabalerobot.Framework.model.Waste;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    Waste waste;
    Tableau[] tableau;
    Foundation[] foundation;

    public GameLogic() {
        waste = new Waste(null, false);
        tableau = new Tableau[7];
        foundation = new Foundation[4];
    }

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
        wasteNfoundation = waste.wasteSize() + "|"+ shownWaste + "     " + foundationString;
        System.out.println(wasteNfoundation);
        //Tableau
        String tableauLengths = "";
        String tableauValues = "";
        for(int i = 0 ; i < tableau.length ; i++) {
            tableauLengths += " " + tableau[i].countHiddenCards() + "  ";
            tableauValues += tableau[i].isEmpty() ? "Emp " : tableau[i].getTopCard().shortString() + " " ;
        }

        return (tableauLengths + "\n" + tableauValues);
    }

    public Tableau[] getTableau() {
        return tableau;
    }

    public Foundation[] getFoundation() {
        return foundation;
    }

    public Waste getWaste() { return waste; }

    public void setTableau(Tableau[] tableau){
        this.tableau = tableau;
    }
    public void setTableaus(List<Integer> hiddenCards, List<List<Card>> transTableaus) {
        for (int i = 0 ; i < 7 ; i++) {
            tableau[i] = new Tableau(hiddenCards.get(i), transTableaus.get(i));
        }
    }

    public void setFoundation(Foundation[] foundation){
        this.foundation = foundation;
    }

    public void setFoundations(List<Card> cards) {
        for(int i = 0 ; i < 4 ; i++) {
            foundation[i] = new Foundation(cards.get(i));
        }
    }

    public void setWaste(Waste waste) {
        this.waste = waste;
    }
}
