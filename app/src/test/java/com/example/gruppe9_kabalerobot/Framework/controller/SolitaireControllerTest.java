package com.example.gruppe9_kabalerobot.Framework.controller;

import com.example.gruppe9_kabalerobot.CardPlacement.CardObj;
import com.example.gruppe9_kabalerobot.CardPlacement.CardPlacement;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SolitaireControllerTest {
    CardPlacement place;

    @Before
    public void setup() {
        place = new CardPlacement();
        SolitaireController game = new SolitaireController();
        game.resetMemory(); // Needs to reset the memory of gamestates, since it is a singleton
    }

    /**
     * Tests to see if it remembers a previous move suggestion that has been saved to the PreviousStateContainer, by not giving the same move twice.
     * Essentially a test if the Singleton works
     */
    @Test
    public void takeMove102() {
        //Setup CardPlacement with a possible move
            //Tableau 1
        List<CardObj> tableau1 = new ArrayList<>();
        CardObj card = new CardObj(0, 0, 1, 0);
        tableau1.add(card);
        place.setTableau1(tableau1);
        List<Integer> hiddenCards = new ArrayList<>();
        for(int i = 0 ; i < 7 ; i++) hiddenCards.add(1);
        place.setHiddenCards(hiddenCards);

        //Setup Rest of the classes
            //SolitaireController
        SolitaireController game = new SolitaireController();
            //CardTranslator
        CardTranslator cardTrans = new CardTranslator(place);

        //Take same 'game state' twice
        String move1 = game.takeMove(cardTrans);
        System.out.println(move1);
        String move2 = game.takeMove(cardTrans);
        System.out.println(move2);

        //Check that the response 2nd time isn't the same as the first time
        //First should ask for moving an Ace of Hearts
        assertEquals("Ryk Ace of Hearts til Foundation", move1);
        //There should be no more solutions possible
        assertEquals("Turn over a card from the tableau with the highest amount of hidden cards", move2);
    }

    /**
     * Tests if it remembers previous moves, when the Controller has been reset.
     * Essentially a test if the Singleton of PreviousStateContainer works
     */
    @Test
    public void takeMove103() {
        //Setup CardPlacement with a possible move
        //Tableau 1
        List<CardObj> tableau1 = new ArrayList<>();
        CardObj card = new CardObj(0, 0, 1, 0);
        tableau1.add(card);
        place.setTableau1(tableau1);
        List<Integer> hiddenCards = new ArrayList<>();
        for(int i = 0 ; i < 7 ; i++) hiddenCards.add(1);
        place.setHiddenCards(hiddenCards);

        //Setup Rest of the classes
        //SolitaireController
        SolitaireController game = new SolitaireController();
        //CardTranslator
        CardTranslator cardTrans = new CardTranslator(place);

        //Take same 'game state' twice
        String move1 = game.takeMove(cardTrans);
        System.out.println(move1);
        game = new SolitaireController();
        String move2 = game.takeMove(cardTrans);
        System.out.println(move2);

        //Check that the response 2nd time isn't the same as the first time
        //First should ask for moving an Ace of Hearts
        assertEquals("Ryk Ace of Hearts til Foundation", move1);
        //There should be no more solutions possible
        assertEquals("Turn over a card from the tableau with the highest amount of hidden cards", move2);
    }

    /**
     * Tests if it can reset the memory
     */
    @Test
    public void takeMove104() {
        //Setup CardPlacement with a possible move
        //Hidden cards
        List<Integer> hiddenCards = new ArrayList<>();
        for(int i = 0 ; i < 7 ; i++) hiddenCards.add(0);
        place.setHiddenCards(hiddenCards);
        //Tableau 1
        List<CardObj> tableau1 = new ArrayList<>();
        CardObj card = new CardObj(0, 0, 1, 0);
        tableau1.add(card);
        place.setTableau1(tableau1);

        //Setup Rest of the classes
        //SolitaireController
        SolitaireController game = new SolitaireController();
        //CardTranslator
        CardTranslator cardTrans = new CardTranslator(place);

        //Take same 'game state' twice
        String move1 = game.takeMove(cardTrans);
        System.out.println(move1);
        game.resetMemory();
        String move2 = game.takeMove(cardTrans);
        System.out.println(move2);

        //Check that the response 2nd time isn't the same as the first time
        assertEquals(move1, move2);
    }
}