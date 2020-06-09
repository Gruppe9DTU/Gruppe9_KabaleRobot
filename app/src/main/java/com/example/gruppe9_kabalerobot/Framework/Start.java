package com.example.gruppe9_kabalerobot.Framework;

import com.example.gruppe9_kabalerobot.Framework.Gamelogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Start {
    public static void main(String[] args) {
//        Deck deck = new Deck();
        Gamelogic gamelogic = new Gamelogic();

        List<Tableau> tableaus = Arrays.asList(gamelogic.getTableau());
        List<Foundation> foundations = Arrays.asList(gamelogic.getFoundation());
        MoveAlgoritm move = new MoveAlgoritm(tableaus, foundations, gamelogic.waste.lookAtTop(), gamelogic.getWaste().getPileStatus());

        System.out.println(move.getBestMove());
    }
}