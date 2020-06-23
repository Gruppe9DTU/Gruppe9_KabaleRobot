package com.example.gruppe9_kabalerobot.Framework.model;

import com.example.gruppe9_kabalerobot.Framework.model.Card;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    private Card testCard = new Card(0, 1);

    @Test
    public void testReadSuit() {
        testCard.setSuit(0);

        assertEquals("Hjerter", testCard.readSuit());

        testCard.setSuit(1);

        assertEquals("Spar", testCard.readSuit());

        testCard.setSuit(2);

        assertEquals("Ruder", testCard.readSuit());

        testCard.setSuit(3);

        assertEquals("Klør", testCard.readSuit());
    }

    @Test
    public void testToString() {
        testCard.setSuit(0);
        testCard.setValue(1);
        assertEquals("Hjerter Es", testCard.toString());
        testCard.setValue(11);
        assertEquals("Hjerter Knægt", testCard.toString());
        testCard.setValue(12);
        assertEquals("Hjerter Dronning", testCard.toString());
        testCard.setValue(13);
        assertEquals("Hjerter Konge", testCard.toString());
        testCard.setValue(4);
        assertEquals("Hjerter 4", testCard.toString());
    }

}