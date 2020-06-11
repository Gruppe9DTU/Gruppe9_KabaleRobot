package com.example.gruppe9_kabalerobot.CardPlacement;

import com.example.gruppe9_kabalerobot.Framework.controller.GameLogic;
import com.example.gruppe9_kabalerobot.Framework.model.Card;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardTranslatorTest {
    private CardPlacement placement;
    private CardTranslator translator;
    private GameLogic game;

    @Before
    public void setup() {
        this.placement = new CardPlacement();
        this.translator = new CardTranslator(placement);
    }

    /**
     * Only Waste are filled
     */
    @Test
    public void insertCards101() {
        //Insert cards into CardPlacement
        //Setup expected Lists

        //Translate cards
        translator.insertCards(game);

        //AssertEqual expected Lists by reading from game
    }

    /**
     * Only Foundation are filled
     */
    @Test
    public void insertCards102() {

        //Setup expected Lists
        List<CardObj> foundationList = new ArrayList<>();
        foundationList.add(new CardObj(0,0,10,0)); //10 of hearts
        foundationList.add(new CardObj(0,0,9,1)); //9 of spades
        foundationList.add(new CardObj(0,0,8,2)); //8 of diamonds
        foundationList.add(new CardObj(0,0,7,3)); //7 of clubs

        List<Card> foundationListExpected = new ArrayList<>();
        foundationListExpected.add(new Card(0,10)); //10 of hearts
        foundationListExpected.add(new Card(1,9)); //9 of spades
        foundationListExpected.add(new Card(2,8)); //8 of diamonds
        foundationListExpected.add(new Card(3,7)); //7 of clubs

        //Insert cards into CardPlacement
        placement.setFoundations(foundationList);


        //Translate cards
        translator.insertCards(game);

        //AssertEqual expected Lists by reading from game
        Assert.assertEquals(Arrays.asList(game.getFoundation()),foundationListExpected);
    }

    /**
     * Only Tableaus are filled
     */
    @Test
    public void insertCards103() {

    }

    /**
     * All are filled
     */
    @Test
    public void insertCards104() {

    }
}