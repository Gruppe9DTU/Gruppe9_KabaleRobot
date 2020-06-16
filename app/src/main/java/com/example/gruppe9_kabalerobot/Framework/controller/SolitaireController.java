package com.example.gruppe9_kabalerobot.Framework.controller;

import com.example.gruppe9_kabalerobot.Framework.model.PreviousState;
import com.example.gruppe9_kabalerobot.Framework.model.PreviousStatesContainer;

public class SolitaireController {
    private PreviousStatesContainer prevStates;

    public SolitaireController() {
         this.prevStates = new PreviousStatesContainer();
    }

    public String takeMove(CardTranslator translator) {
        //Setup
        SolitarieLogic game = new SolitarieLogic();
        translator.insertCards(game);
        //Find move suggestion
        MoveAlgorithm moveAlgo = new MoveAlgorithm(game);
        String moveSuggestion = moveAlgo.getBestMove(prevStates.getLatestSolutionToState(game.getGameState()));
        //Save and return suggestion
        prevStates.addPreviousMove(new PreviousState(game.getGameState(), moveAlgo.getMoveChosen()));
        return moveSuggestion;
    }
}
