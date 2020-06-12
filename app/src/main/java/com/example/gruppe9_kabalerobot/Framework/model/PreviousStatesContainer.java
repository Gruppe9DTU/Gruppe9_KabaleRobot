package com.example.gruppe9_kabalerobot.Framework.model;

import java.util.ArrayList;
import java.util.List;

public class PreviousStatesContainer {

    private List<PreviousState> previousStates = new ArrayList<PreviousState>();

    public PreviousStatesContainer(){}

    public void addPreviousMove(PreviousState preState) {
        previousStates.add(preState);
    }

    /**
     * Compares current state of game to ealier game states
     *
     * @param currState String of current state of game
     * @return          Latest solution for that state
     */
    public PreviousState getLatestSolutionToState(String currState) {
        PreviousState state = null;

        for (PreviousState preState : previousStates) {
            if(currState.equals(preState.getState())){
                state = preState;
            }
        }
        return state;
    }
}