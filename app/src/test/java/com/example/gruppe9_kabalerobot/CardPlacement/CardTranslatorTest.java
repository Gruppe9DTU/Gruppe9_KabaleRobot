package com.example.gruppe9_kabalerobot.CardPlacement;

import com.example.gruppe9_kabalerobot.Framework.controller.GameLogic;
import com.example.gruppe9_kabalerobot.Framework.model.Card;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CardTranslatorTest {
    private CardPlacement placement;
    private CardTranslator translator;
    private GameLogic game;

    @Before
    public void setup() {
        this.placement = new CardPlacement();
        this.translator = new CardTranslator(placement);
        this.game = new GameLogic();
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

    }

    /**
     * Only Tableaus are filled
     */
    @Test
    public void insertCards103() {
        //Insert cards into CardPlacement
        List<CardObj> tab1 = new ArrayList<>(); //List with one card
        tab1.add(new CardObj(0, 0, 10, 0)); //10 of Hearts
        placement.setTableau1(tab1);

        List<CardObj> tab2 = new ArrayList<>(); //List with more cards cards
        tab2.add(new CardObj(0, 0, 11, 0)); //11 of Hearts
        tab2.add(new CardObj(0, 0, 10, 1)); //10 of Spades
        placement.setTableau2(tab2);

        List<CardObj> tab3 = new ArrayList<>(); //List with no cards
        placement.setTableau3(tab3);

        //Setup expected Lists
        List<Card> tab1Exp = new ArrayList<>();
        tab1Exp.add(new Card(0, 10)); //10 of Hearts
        List<Card> tab2Exp = new ArrayList<>();
        tab2Exp.add(new Card(0, 11)); //Jack of Hearts
        tab2Exp.add(new Card(1, 10)); //10 of Spades
        List<Card> tab3Exp = new ArrayList<>();

        //Translate cards
        translator.insertCards(game);

        //AssertEqual expected Lists by reading from game
        assertEquals(tab1Exp.toString(), Arrays.asList(game.getTableau()[0].getVisibleCards()).toString());
        assertEquals(tab2Exp.toString(), Arrays.asList(game.getTableau()[1].getVisibleCards()).toString());
        assertEquals(tab3Exp.toString(), Arrays.asList(game.getTableau()[2].getVisibleCards()).toString());
    }

    /**
     * All are filled
     */
    @Test
    public void insertCards104() {

    }
}