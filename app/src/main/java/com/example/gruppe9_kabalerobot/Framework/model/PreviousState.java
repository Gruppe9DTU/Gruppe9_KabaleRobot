package com.example.gruppe9_kabalerobot.Framework.model;

/**
 * Class to act as a save state, by saving a picture (String) of a previous state and what move was made at that state
 */
public class PreviousState {
    private String state;   //Saved image of game state
    private int move;       //Move made on saved game state

    /**
     * Constructor for a saved state
     *
     * @param state Game state
     * @param move  Integer reference to latest move provided by MoveAlgorithm
     */
    public PreviousState(String state, int move) {
        this.state = state;
        this.move = move;
    }

    /**
     * Getters for PreviousState class
     */
    public String getState() { return state; }
    public int getMove() { return move; }
}