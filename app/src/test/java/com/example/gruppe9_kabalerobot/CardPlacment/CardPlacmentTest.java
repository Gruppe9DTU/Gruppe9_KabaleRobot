package com.example.gruppe9_kabalerobot.CardPlacment;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CardPlacmentTest {

    CardPlacement cardPlacement = new CardPlacement();
    ArrayList<CardObj> coordinates = new ArrayList<>();
    ArrayList<CardObj> winningCard = new ArrayList<>();

    @Test
    public void testSort() {
        addCoordinates();
        ArrayList<CardObj> testStack = new ArrayList<>(coordinates);
        cardPlacement.sortCards(testStack);

        winningCard.add(coordinates.get(1));
        winningCard.add(coordinates.get(3));
        winningCard.add(coordinates.get(4));

        //Extra card
        assertEquals(cardPlacement.extraCard.get(0), coordinates.get(0));
        assertEquals(cardPlacement.extraCard.size(),1);

        //Winning stack
        assertEquals(cardPlacement.winningStack,winningCard);
        assertEquals(cardPlacement.winningStack.size(),3);

        //Stack 1
        assertEquals(cardPlacement.stack1.get(0), coordinates.get(7));
        assertEquals(cardPlacement.stack1.get(1), coordinates.get(5));
        assertEquals(cardPlacement.stack1.size(),2);

        //Stack 2
        assertEquals(cardPlacement.stack2.get(0), coordinates.get(6));
        assertEquals(cardPlacement.stack2.size(),1);

        //Stack 3
        assertEquals(cardPlacement.stack3.get(0),coordinates.get(8));
        assertEquals(cardPlacement.stack3.size(),1);

        //Stack 4
        assertEquals(cardPlacement.stack4.get(0),coordinates.get(9));
        assertEquals(cardPlacement.stack4.get(1),coordinates.get(11));
        assertEquals(cardPlacement.stack4.get(2),coordinates.get(2));
        assertEquals(cardPlacement.stack4.get(3),coordinates.get(10));
        assertEquals(cardPlacement.stack4.size(),4);

        //Stack 5
        assertEquals(cardPlacement.stack5.get(0),coordinates.get(14));
        assertEquals(cardPlacement.stack5.get(1),coordinates.get(13));
        assertEquals(cardPlacement.stack5.get(2),coordinates.get(12));
        assertEquals(cardPlacement.stack5.size(),3);

        //Stack 6
        assertEquals(cardPlacement.stack6.get(0),coordinates.get(16));
        assertEquals(cardPlacement.stack6.get(1),coordinates.get(15));
        assertEquals(cardPlacement.stack6.size(),2);

    }


    /**
     * Test data for testing
     */
    private void addCoordinates(){
        coordinates.add(new CardObj(1,6,12,1)); //0 extra card
        coordinates.add(new CardObj(2,6,4,3));  //1 winning stack
        coordinates.add(new CardObj(4,3,7,3));  //2 stack 4, pos 2
        coordinates.add(new CardObj(3,6,4,0));  //3 winning stack
        coordinates.add(new CardObj(5,6,2,0));  //4 winning stack

        coordinates.add(new CardObj(1,3,4,3));  //5 stack 1, pos 1
        coordinates.add(new CardObj(2,1,10,3)); //6 stack 2 pos 0
        coordinates.add(new CardObj(1,1,1,2));  //7 stack 1, pos 0
        coordinates.add(new CardObj(3,1,8,3));  //8 stack 3 pos 0
        coordinates.add(new CardObj(4,1,9,3));  //9 stack 4 pos 0
        coordinates.add(new CardObj(4,4,9,3));  //10 stack 4 pos 3
        coordinates.add(new CardObj(4,2,9,3));  //11 stack 4 pos 1


        coordinates.add(new CardObj(5,3,9,3));  //12 stack 5 pos 2
        coordinates.add(new CardObj(5,2,9,3));  //13 stack 5 pos 1
        coordinates.add(new CardObj(5,1,9,3));  //14 stack 5 pos 0

        coordinates.add(new CardObj(6,2,9,3));  //15 stack 6 pos 1
        coordinates.add(new CardObj(6,1,9,3));  //16 stack 6 pos 0
    }

}