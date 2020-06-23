package com.example.gruppe9_kabalerobot.Framework.controller;

import com.example.gruppe9_kabalerobot.CardPlacement.CardObj;
import com.example.gruppe9_kabalerobot.CardPlacement.CardPlacement;
import com.example.gruppe9_kabalerobot.Framework.model.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to help translate from CardObj to Card
 */
public class CardTranslator {
    private CardPlacement placement;

    /**
     * Constructor for CardTranslator
     *
     * @param placement Class that has the placement and information of the cards there have been taken a picture of
     */
    public CardTranslator(CardPlacement placement) {
        this.placement = placement;
    }

    /**
     * Inserts that cards into a game of Solitaire
     *
     * @param game  The game the cards are to be inserted into
     */
    public void insertCards(SolitaireLogic game) {
        //Waste
        game.getWaste().addListToKnown(translateCardList(placement.getWaste()));
        game.getWaste().setWastePile(placement.isWastePile());
        //Foundation
        game.setFoundations(translateCardList(placement.getFoundations()));
        //Tableau
        List<List<Card>> transTableaus = new ArrayList<>();
        for(List<CardObj> cardObj : placement.getTableaus()) {
            transTableaus.add(translateCardList(cardObj));
        }
        game.setTableaus(placement.getHiddenCards(), transTableaus);
    }

    /**
     * Creates instances of Cards for every CardObj there is
     *
     * @param cardObjs  List of CardObj's
     * @return          A list of Card's
     */
    private List<Card> translateCardList(List<CardObj> cardObjs) {
        List<Card> cards = new ArrayList<>();
        for(CardObj cardObj : cardObjs) {
            Card card = new Card(cardObj.getSuit(), cardObj.getValue());
            cards.add(card);
        }
        return cards;
    }
}