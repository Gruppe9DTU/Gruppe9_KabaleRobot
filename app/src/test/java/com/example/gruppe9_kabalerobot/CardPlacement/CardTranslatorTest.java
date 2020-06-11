package com.example.gruppe9_kabalerobot.CardPlacement;

import com.example.gruppe9_kabalerobot.Framework.controller.GameLogic;

import org.junit.Before;
import org.junit.Test;

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