package com.example.gruppe9_kabalerobot.CardPlacement;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CardPlacementTest {

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
        assertEquals(cardPlacement.getWaste().get(0), coordinates.get(0));
        assertEquals(cardPlacement.getWaste().size(),1);

        //Winning stack
        assertEquals(cardPlacement.getFoundations(),winningCard);
        assertEquals(cardPlacement.getFoundations().size(),3);

        //Stack 1
        assertEquals(cardPlacement.getTableau1().get(0), coordinates.get(5));
        assertEquals(cardPlacement.getTableau1().get(1), coordinates.get(7));
        assertEquals(cardPlacement.getTableau1().size(),2);

        //Stack 2
        assertEquals(cardPlacement.getTableau2().get(0), coordinates.get(6));
        assertEquals(cardPlacement.getTableau2().size(),1);
        int hiddenStack2 = cardPlacement.getHiddenCards().get(1);
        assertEquals(hiddenStack2,1);


        //Stack 3
        assertEquals(cardPlacement.getTableau3().get(0),coordinates.get(8));
        assertEquals(cardPlacement.getTableau3().size(),1);

        //Stack 4
        assertEquals(cardPlacement.getTableau4().get(0),coordinates.get(10));
        assertEquals(cardPlacement.getTableau4().get(1),coordinates.get(2));
        assertEquals(cardPlacement.getTableau4().get(2),coordinates.get(11));
        assertEquals(cardPlacement.getTableau4().get(3),coordinates.get(9));
        assertEquals(cardPlacement.getTableau4().size(),4);

        //Stack 5
        assertEquals(cardPlacement.getTableau5().get(0),coordinates.get(12));
        assertEquals(cardPlacement.getTableau5().get(1),coordinates.get(13));
        assertEquals(cardPlacement.getTableau5().get(2),coordinates.get(14));
        assertEquals(cardPlacement.getTableau5().size(),3);

        //Stack 6
        assertEquals(cardPlacement.getTableau6().get(0),coordinates.get(15));
        assertEquals(cardPlacement.getTableau6().get(1),coordinates.get(16));
        assertEquals(cardPlacement.getTableau6().size(),2);
        int hiddenStack6 = cardPlacement.getHiddenCards().get(5);
        assertEquals(hiddenStack6,2);

        assertEquals(cardPlacement.getTableau7().get(0),coordinates.get(19));
        assertEquals(cardPlacement.getTableau7().get(1),coordinates.get(18));
        assertEquals(cardPlacement.getTableau7().get(2),coordinates.get(20));
        assertEquals(cardPlacement.getTableau7().get(3),coordinates.get(17));
        assertEquals(cardPlacement.getTableau7().size(),4);
    }


    /**
     * Test data for testing
     */
    private void addCoordinates(){
        coordinates.add(new CardObj(1,7,12,1)); //0 extra card
        coordinates.add(new CardObj(2,7,4,3));  //1 winning stack
        coordinates.add(new CardObj(4,3,7,3));  //2 stack 4, pos 1
        coordinates.add(new CardObj(3,7,4,0));  //3 winning stack
        coordinates.add(new CardObj(5,7,2,0));  //4 winning stack



        coordinates.add(new CardObj(1,3,4,3));  //5 stack 1, pos 0
        coordinates.add(new CardObj(2,1,10,3)); //6 stack 2 pos 0
        coordinates.add(new CardObj(1,1,1,2));  //7 stack 1, pos 1
        coordinates.add(new CardObj(3,1,8,3));  //8 stack 3 pos 0
        coordinates.add(new CardObj(4,1,9,3));  //9 stack 4 pos 3
        coordinates.add(new CardObj(4,4,9,3));  //10 stack 4 pos 0
        coordinates.add(new CardObj(4,2,9,3));  //11 stack 4 pos 2


        coordinates.add(new CardObj(5,3,9,3));  //12 stack 5 pos 0
        coordinates.add(new CardObj(5,2,9,3));  //13 stack 5 pos 1
        coordinates.add(new CardObj(5,1,9,3));  //14 stack 5 pos 2

        coordinates.add(new CardObj(6,2,9,3));  //15 stack 6 pos 0
        coordinates.add(new CardObj(6,1,9,3));  //16 stack 6 pos 1


        coordinates.add(new CardObj(7,1,9,3));  //17 stack 7 pos 3
        coordinates.add(new CardObj(7,3,9,3));  //18 stack 7 pos 1
        coordinates.add(new CardObj(7,4,9,3));  //19 stack 7 pos 0
        coordinates.add(new CardObj(7,2,9,3));  //20 stack 7 pos 2

        coordinates.add(new CardObj(2,2,0,0));  //21 hidden card stack 2
        coordinates.add(new CardObj(6,3,0,0));  //22 hidden card stack 6
        coordinates.add(new CardObj(6,4,0,0));  //23 hidden card stack 6
    }

}