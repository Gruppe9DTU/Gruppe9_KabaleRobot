package com.example.gruppe9_kabalerobot.Framework.controller;

import com.example.gruppe9_kabalerobot.CardPlacment.CardTranslator;
import com.example.gruppe9_kabalerobot.Framework.model.PreviousStatesContainer;

public class SolitaireController {
    PreviousStatesContainer prevStates;

    public SolitaireController() {
         this.prevStates = new PreviousStatesContainer();
    }

    public String takeMove(CardTranslator translator) {
        GameLogic game = new GameLogic(); //TODO Needs information
        translator.insertCards(game);

        MoveAlgorithm moveAlgo = new MoveAlgorithm(game);
        return moveAlgo.getBestMove(prevStates.getLatestSolutionToState(game.printGame()));
    }
}
