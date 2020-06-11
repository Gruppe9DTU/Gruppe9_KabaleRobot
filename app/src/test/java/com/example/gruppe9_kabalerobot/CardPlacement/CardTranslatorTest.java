package com.example.gruppe9_kabalerobot.CardPlacement;

import com.example.gruppe9_kabalerobot.Framework.controller.GameLogic;
import com.example.gruppe9_kabalerobot.Framework.model.Card;

import org.junit.Assert;
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
        assertEquals(foundationListExpected.get(0),game.getFoundation()[0].peekCard());
        assertEquals(foundationListExpected.get(1),game.getFoundation()[1].peekCard());
        assertEquals(foundationListExpected.get(2),game.getFoundation()[2].peekCard());
        assertEquals(foundationListExpected.get(3),game.getFoundation()[3].peekCard());
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
        tab2Exp.add(new Card(0, 11)); //11 of Hearts
        tab2Exp.add(new Card(1, 10)); //10 of Spades
        List<Card> tab3Exp = new ArrayList<>();

        //Translate cards
        translator.insertCards(game);

        //AssertEqual expected Lists by reading from game
        assertEquals(tab1Exp, Arrays.asList(game.getTableau()[1].getVisibleCards()));
        assertEquals(tab2Exp, Arrays.asList(game.getTableau()[2].getVisibleCards()));
        assertEquals(tab3Exp, Arrays.asList(game.getTableau()[3].getVisibleCards()));
    }

    /**
     * All are filled
     */
    @Test
    public void insertCards104() {

    }
}