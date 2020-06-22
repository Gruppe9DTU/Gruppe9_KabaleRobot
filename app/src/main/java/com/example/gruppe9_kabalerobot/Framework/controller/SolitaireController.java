package com.example.gruppe9_kabalerobot.Framework.controller;

import com.example.gruppe9_kabalerobot.Framework.model.PreviousState;
import com.example.gruppe9_kabalerobot.Framework.model.PreviousStatesContainer;

/**
 * Controller class to access and run the game.
 */
public class SolitaireController {
    private PreviousStatesContainer prevStates;

    public SolitaireController() {
         this.prevStates = PreviousStatesContainer.getInstance();
    }

    /**
     * Takes a full CardTranslator and inserts it into the SolitaireLogic and asks the algorithms for best move for current position.
     *
     * @param translator    Instance of CardTranslator, needs cards to work
     * @return              Instructions to the user
     */
    public String takeMove(CardTranslator translator) {
        //Setup
        SolitaireLogic game = new SolitaireLogic();
        translator.insertCards(game);
        //Find move suggestion
        MoveAlgorithm moveAlgo = new MoveAlgorithm(game);
        String moveSuggestion = moveAlgo.getBestMove(prevStates.getLatestSolutionToState(game.getGameState()));
        //Save and return suggestion
        prevStates.addPreviousMove(new PreviousState(game.getGameState(), moveAlgo.getMoveChosen()));
        return moveSuggestion;
    }

    /**
     * Resets the PreviousStateController, so no knowledge of previous moves should be remembered
     */
    public void resetMemory() {
        prevStates.resetMemory();
    }
}
