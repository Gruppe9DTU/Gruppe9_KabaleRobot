package com.example.gruppe9_kabalerobot.CardPlacment;

import com.example.gruppe9_kabalerobot.Framework.controller.GameLogic;
import com.example.gruppe9_kabalerobot.Framework.model.Card;

import java.util.ArrayList;
import java.util.List;

public class CardTranslator {
    CardPlacement placement;

    public CardTranslator(CardPlacement placement) {
        this.placement = placement;
    }

    public void insertCards(GameLogic game) {
        game.getWaste().addListToKnown(translateCardList(placement.getWaste()));

    }

    private List<Card> translateCardList(List<CardObj> cardObjs) {
        List<Card> cards = new ArrayList<>();
        for(CardObj cardObj : cardObjs) {
            Card card = new Card(cardObj.getSuit(), cardObj.getValue());
            cards.add(card);
        }
        return cards;
    }
}
