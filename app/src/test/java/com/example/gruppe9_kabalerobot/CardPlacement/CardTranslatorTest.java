package com.example.gruppe9_kabalerobot.CardPlacement;

import com.example.gruppe9_kabalerobot.Framework.controller.SolitarieLogic;
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
    private SolitarieLogic game;

    @Before
    public void setup() {
        this.placement = new CardPlacement();
        this.translator = new CardTranslator(placement);
        this.game = new SolitarieLogic();
    }

    /**
     * Only Waste for one card are filled
     */
    @Test
    public void insertCards101() {

        //Setup expected Lists
        List<CardObj> wasteList = new ArrayList<>();
        wasteList.add(new CardObj(0,0,10,0)); //10 of hearts

        List<Card> wasteListExpected = new ArrayList<>();
        wasteListExpected.add(new Card(0,10)); //10 of hearts

        //Insert cards into CardPlacement
        placement.setWaste(wasteList);


        //Translate cards
        translator.insertCards(game);

        //AssertEqual expected Lists by reading from game
        assertEquals(wasteListExpected.get(0).toString(),game.getWaste().lookAtTop().toString());
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
        assertEquals(foundationListExpected.get(0).toString(),game.getFoundation()[0].peekCard().toString());
        assertEquals(foundationListExpected.get(1).toString(),game.getFoundation()[1].peekCard().toString());
        assertEquals(foundationListExpected.get(2).toString(),game.getFoundation()[2].peekCard().toString());
        assertEquals(foundationListExpected.get(3).toString(),game.getFoundation()[3].peekCard().toString());
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

        List<CardObj> wasteList = new ArrayList<>();
        wasteList.add(new CardObj(0,0,10,0)); //10 of hearts

        List<CardObj> foundationList = new ArrayList<>();
        foundationList.add(new CardObj(0,0,10,0)); //10 of hearts
        foundationList.add(new CardObj(0,0,9,1)); //9 of spades
        foundationList.add(new CardObj(0,0,8,2)); //8 of diamonds
        foundationList.add(new CardObj(0,0,7,3)); //7 of clubs

        //Insert cards into CardPlacement
        List<CardObj> tab1 = new ArrayList<>(); //List with one card
        tab1.add(new CardObj(0, 0, 10, 0)); //10 of Hearts

        List<CardObj> tab2 = new ArrayList<>(); //List with more cards cards
        tab2.add(new CardObj(0, 0, 11, 0)); //11 of Hearts
        tab2.add(new CardObj(0, 0, 10, 1)); //10 of Spades

        List<CardObj> tab3 = new ArrayList<>(); //List with no cards

        placement.setWaste(wasteList);
        placement.setFoundations(foundationList);
        placement.setTableau1(tab1);
        placement.setTableau2(tab2);
        placement.setTableau3(tab3);

        //Setup expected Lists
        List<Card> wasteListExpected = new ArrayList<>();
        wasteListExpected.add(new Card(0,10)); //10 of hearts

        List<Card> foundationListExpected = new ArrayList<>();
        foundationListExpected.add(new Card(0,10)); //10 of hearts
        foundationListExpected.add(new Card(1,9)); //9 of spades
        foundationListExpected.add(new Card(2,8)); //8 of diamonds
        foundationListExpected.add(new Card(3,7)); //7 of clubs

        List<Card> tab1Exp = new ArrayList<>();
        tab1Exp.add(new Card(0, 10)); //10 of Hearts
        List<Card> tab2Exp = new ArrayList<>();
        tab2Exp.add(new Card(0, 11)); //Jack of Hearts
        tab2Exp.add(new Card(1, 10)); //10 of Spades
        List<Card> tab3Exp = new ArrayList<>();

        //Translate cards
        translator.insertCards(game);

        //AssertEqual expected Lists by reading from game
        assertEquals(wasteListExpected.get(0).toString(),game.getWaste().lookAtTop().toString());

        assertEquals(foundationListExpected.get(0).toString(),game.getFoundation()[0].peekCard().toString());
        assertEquals(foundationListExpected.get(1).toString(),game.getFoundation()[1].peekCard().toString());
        assertEquals(foundationListExpected.get(2).toString(),game.getFoundation()[2].peekCard().toString());
        assertEquals(foundationListExpected.get(3).toString(),game.getFoundation()[3].peekCard().toString());

        assertEquals(tab1Exp.toString(), Arrays.asList(game.getTableau()[0].getVisibleCards()).toString());
        assertEquals(tab2Exp.toString(), Arrays.asList(game.getTableau()[1].getVisibleCards()).toString());
        assertEquals(tab3Exp.toString(), Arrays.asList(game.getTableau()[2].getVisibleCards()).toString());


    }
}