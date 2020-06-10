package com.example.gruppe9_kabalerobot.Framework;

public class SolitaireController {
    PreviousStatesContainer prevStates;

    public SolitaireController() {
         this.prevStates = new PreviousStatesContainer();
    }

    public String takeMove() {
        GameLogic game = new GameLogic();
        MoveAlgorithm moveAlgo = new MoveAlgorithm(game);
        return moveAlgo.getBestMove(prevStates.getLatestSolutionToState(game.printGame()));
    }
}
