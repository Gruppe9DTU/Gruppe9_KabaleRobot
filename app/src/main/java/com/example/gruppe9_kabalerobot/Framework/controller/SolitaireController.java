package com.example.gruppe9_kabalerobot.Framework.controller;

import com.example.gruppe9_kabalerobot.CardPlacement.CardTranslator;
import com.example.gruppe9_kabalerobot.Framework.model.PreviousStatesContainer;

public class SolitaireController {
    PreviousStatesContainer prevStates;

    public SolitaireController() {
         this.prevStates = new PreviousStatesContainer();
    }

    public String takeMove(CardTranslator translator) {
        SolitarieLogic game = new SolitarieLogic();
        translator.insertCards(game);

        MoveAlgorithm moveAlgo = new MoveAlgorithm(game);
        return moveAlgo.getBestMove(prevStates.getLatestSolutionToState(game.getGameState()));
    }
}
