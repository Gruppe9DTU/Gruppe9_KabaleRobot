package com.example.gruppe9_kabalerobot.Framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MoveAlgorithm {

    private List<Tableau> tableaus;
    private List<Foundation> foundations;
    private Card waste;
    private boolean wastePile;

    public MoveAlgorithm(GameLogic game) {
        //TODO Decide on Lists vs arrays
        this.tableaus = Arrays.asList(game.getTableau());       //Get list of tableau
        this.foundations = Arrays.asList(game.getFoundation()); //Get list of foundations
        this.waste = game.getWaste().lookAtTop();               //Get the top card of waste (1-card rule)
        this.wastePile = game.getWaste().getPileStatus();       //Get if there is a wastepile to draw from or not
    }

    public String getBestMove(PreviousState preState) {
        int latestMove;
        String bestMove;

        //Gets latest move done
        if(preState == null) latestMove = 0;
        else latestMove = preState.getMove();

        bestMove = moveChecker(latestMove);

        return bestMove;
    }

    private String moveChecker(int latestMove) {
        String bestMove;
        switch (++latestMove) { //Skips previous move, goes to first if none were made before

            case 1:
                bestMove = checkAce();
                if (!bestMove.equals("")) break;

            case 2:
                bestMove = kingCheck();
                if (!bestMove.equals("")) break;

            case 3:
                bestMove = revealHiddenCard();
                if (!bestMove.equals("")) break;

            case 4:
                bestMove = moveTableau();
                if (!bestMove.equals("")) break;

            case 5:
                bestMove = moveToFoundation();
                if (!bestMove.equals("")) break;

            case 6:
                bestMove = typeStreak();
                if (!bestMove.equals("")) break;

            case 7:
                bestMove = foundationToTableau();
                if (!bestMove.equals("")) break;

            case 8:
                bestMove = revealCardFromWaste();
                if (!bestMove.equals("")) break;

            default:
                if(latestMove == 1) bestMove = "Intet træk blev fundet for nuværende spil";
                else bestMove = "Der kunne ikke findes noget nyt træk for denne position af spillet";
        }
        return bestMove;
    }

    /**
     * If there is an ace move it to the corresponding foundation
     *
     * @return Instructions to Player
     */
    public String checkAce() {
        Collections.sort(tableaus,Tableau.AllCardsCompare); //TODO Why not just hidden cards?

        for (Tableau tableau : tableaus) {
            Card[] visibleCards = tableau.getVisibleCards();

            //Check if first visible card in tableau is es
            if(visibleCards.length > 0) {
                Card card = visibleCards[visibleCards.length - 1];

                if (card.getValue() == 1) {
                    return "Ryk " + card.toString() + " til Foundation";
                }
            }
        }
        return "";
    }

    /**
     * If there is at minimum one King and one empty space, finds best suitable King. Best suitable King is found from which one frees the highest amount of cards.
     *
     * @return Instructions to Player
     */
    public String kingCheck() {
        //Copy Tableaus and sort
        Collections.sort(tableaus, Tableau.HiddenCardsCompare); //Sort so first found has highest amount of hidden cards
        List<Card> kingsAvailable = new ArrayList<Card>();
        int emptySpaces = 0;

        //Find available kings and check for empty spaces
        for (Tableau tableau : tableaus) {
            if (tableau.isEmpty()) {
                emptySpaces++;
            } else if (tableau.getVisibleCards().length > 0) {//Check if first card is king
                Card card = tableau.getVisibleCards()[0];

                if (card.getValue() == 13 && tableau.countHiddenCards() != 0) {
                    kingsAvailable.add(card);
                }
            }
        }

        if (kingsAvailable.size() > 1 && emptySpaces > 0) {
            boolean bestKingFound = false;
            int currSearchValue = 13; //Start by searching for best king to move

            int redKingScore = 0, blackKingScore = 0, currMostFreedCards = 0;

            while (!bestKingFound && currSearchValue > 0) {
                //Reset scores for round
                redKingScore = 0; blackKingScore = 0; currMostFreedCards = 0;

                for (Card currKing : kingsAvailable) {
                    //Looking through for X value card
                    for (Tableau tab : tableaus) {

                        //There are cards to check
                        if (tab.getVisibleCards().length > 0) {
                            Card backCard = tab.getVisibleCards()[0]; //Take card from the back of the stack

                            //Is the card we're looking for
                            if (backCard.getValue() == currSearchValue && tab.countHiddenCards() > currMostFreedCards) {

                                if (isCompatibleToKingsStack(currKing, backCard)) { //Check if card matches kings stack
                                    if (currKing.getSuit() % 2 == 0) {
                                        redKingScore = 1; //Set score to 1-0 for red
                                        blackKingScore = 0;
                                    } else {
                                        blackKingScore = 1; //Set score to 1-0 for black
                                        redKingScore = 0;
                                    }
                                    currMostFreedCards = tab.countHiddenCards(); //Set new highscore
                                }
                                bestKingFound = true; //Only one answer is found

                            } else if (backCard.getValue() == currSearchValue && currMostFreedCards == tab.countHiddenCards()) {
                                if (isCompatibleToKingsStack(currKing, backCard)) { //Check if card matches kings stack

                                    if(currKing.getSuit() % 2 == 0) redKingScore++; else blackKingScore++; //Add score
                                }
                                bestKingFound = (redKingScore + blackKingScore == 1); //Multiple answers are found
                            }
                        }
                    }
                }
                if (!bestKingFound) currSearchValue--; //Go down a value in case we need to continue search
            }

            String bestKing = null;
            for (Card king : kingsAvailable) {
                if (redKingScore > blackKingScore && king.getSuit() % 2 == 0) { //If red king is best
                    bestKing = king.toString();
                    break;
                } else if (blackKingScore > redKingScore && king.getSuit() % 2 == 1) { //If black king is best
                    bestKing = king.toString();
                    break;
                } else if (redKingScore == blackKingScore) { //If it's either or
                    bestKing = "any king";
                    break;
                }
            }
            return "Move " + bestKing + " to an empty space";
        } else if (kingsAvailable.size() == 1 && emptySpaces > 0) { //Only one king found
            return "Move " + kingsAvailable.get(0).toString() + " to an empty space";
        }
        return "";
    }

    /**
     * Checks if card can be part of the stack of a king
     *
     * @param currKing King which stacks we compare
     * @param backCard Card to check if compatible
     * @return True if compatible, else false
     */
    private boolean isCompatibleToKingsStack(Card currKing, Card backCard) {
        return backCard.getValue() % 2 == 0 && backCard.getSuit() % 2 != currKing.getSuit() % 2
                || backCard.getValue() % 2 == 1 && backCard.getSuit() % 2 == currKing.getSuit() % 2;
    }

    /**
     * Checks if there is a tableau where the player can turn over a hidden card
     *
     * @return Instruction to player
     */
    public String revealHiddenCard() {
        for (Tableau tableau : tableaus) {
            if (tableau.getVisibleCards() == null || tableau.getVisibleCards().length == 0
                    && tableau.countHiddenCards() > 0) {
                return "Turn over a card from the tableau with the highest amount of hidden cards";
            }
        }
        return "";
    }

    /**
     * Control if taking a card from foundation to tableau opens up other interactions
     *
     * @return Instructions to player
     */
    public String foundationToTableau() {
        //TODO Should this be sorted?
        for (Foundation foundation : foundations) {
            if (foundation.countCards() > 0) {  //If there is a card in the foundation
                Card foundationCard = foundation.peekCard(); //Set current possible card

                for (Tableau tableau : tableaus) {
                    Card[] tableauCards = tableau.getVisibleCards();
                    //Check if possible to place card
                    if (tableauCards.length != 0 &&
                            tableauCards[tableauCards.length - 1].getValue() - 1 == foundationCard.getValue()
                            && tableauCards[tableauCards.length - 1].getSuit() % 2 != foundationCard.getSuit() % 2) { //Check if possible to move card from foundation to tableau
                        //Check if it opens up possibilities
                        //First check tableaus
                        for (Tableau otherTableau : tableaus) {
                            if (tableau != otherTableau) {
                                for (Card card : otherTableau.getVisibleCards()) {
                                    if (card.getValue() == foundationCard.getValue() - 1 && card.getSuit() % 2 != foundationCard.getSuit() % 2) {
                                        return "Ryk " + foundationCard.toString() + " fra grundbunken ned på rækken med " + tableauCards[tableauCards.length - 1].toString();
                                    }
                                }
                            }
                        }
                        //Then check waste
                        if (waste != null && waste.getValue() == foundationCard.getValue() - 1 && waste.getSuit() % 2 != foundationCard.getSuit() % 2) {
                            return "Ryk " + foundationCard.toString() + " fra grundbunken ned på rækken med " + tableauCards[tableauCards.length - 1].toString();
                        }
                    }
                }
            }
        }
        return "";
    }

    /**
     * Checks if cards from tableau, other than Aces, can be moved to foundation
     *
     * @return Instructions for player
     */
    public String moveToFoundation() {
        //Check if possible to move from tableau
        for (Tableau tableau : tableaus) {
            if (!tableau.isEmpty()) {
                Card card = tableau.getTopCard();

                for (Foundation foundation : foundations) {

                    //check first if card can be moved to foundation
                    if (foundation.countCards() > 0 &&
                            card.getValue() == foundation.peekCard().getValue() + 1 &&
                            card.getSuit() == foundation.peekCard().getSuit()) {

                        //If creating empty space, controls King is there to replace or next card in foundation is able to be put up aswell
                        if (tableau.getVisibleCards().length - 1 != 0 || tableau.countHiddenCards() != 0 || //Is card left behind
                                checkForMoveableCardFromValue(13) || //Is there a king to take the space
                                checkForMoveableCardFromSuitAndValue(card.getSuit(), card.getValue() + 1)) { //Is the card needed for another card
                            return "Move " + card.toString() + " to it's respective foundation";
                        }
                    }
                }
            }
        }
        //Check if possible to move from waste
        if (waste != null) {
            for (Foundation foundation : foundations) {
                if (foundation.countCards() > 0 &&
                        waste.getValue() == foundation.peekCard().getValue() + 1 &&
                        waste.getSuit() == foundation.peekCard().getSuit()) {
                    return "Move " + waste.toString() + " to it's respective foundation";
                }
            }
        }
        return "";
    }

    /**
     * Searches tableaus for moveable card of given value
     *
     * @param value Value to search for
     * @return True if card has been found
     */
    private boolean checkForMoveableCardFromValue(int value) {
        boolean result = false;
        for (Tableau tableau : tableaus) {
            result = tableau.searchMoveableCardByValue(value) && !(value == 13 && tableau.countHiddenCards() == 0); //True if card found and it isn't a King on an empty space
            if (result) break;
        }
        return result || waste != null && (waste.getValue() == value); //returns true if found in tableau or in waste
    }

    /**
     * Searches tableaus for moveable card of given suit and value
     *
     * @param suit  Suit to search for
     * @param value Value to search for
     * @return True if card has been found
     */
    private boolean checkForMoveableCardFromSuitAndValue(int suit, int value) {
        boolean result = false;
        for (Tableau tableau : tableaus) {
            result = tableau.searchMoveableCardBySuitAndValue(suit, value);
            if (result) break;
        }
        return result || waste != null && (waste.getSuit() == suit && waste.getValue() == value); //returns true if found in tableau or in waste
    }

    /**
     * Moves cards in tableaus to another tableaus to reveal hidden card or create empty space
     *
     * @return Instructions for player
     */
    public String moveTableau() {
        Card[] cards, cards2;
        String move = "";

        for (Tableau tableau : tableaus) {
            cards = tableau.getVisibleCards();
            for (Tableau tableau2 : tableaus) {
                cards2 = tableau2.getVisibleCards();

                //Hvis en af bunkerne er tomme er der ingen grund til at sammenligne dem
                if (cards.length - 1 >= 0 && cards2.length - 1 >= 0) {
                    //Hvis der er mere end ét kort tilstæde i byggestablen og det nederste kort passer på det øverste kort i en anden byggestabel, ryk alle de synlige kort fra byggestablen over til den anden byggestabel
                    if (cards.length - 1 != 0 && cards[0].getValue() == cards2[cards2.length - 1].getValue() - 1 && cards[0].getSuit() % 2 != cards2[cards2.length - 1].getSuit() % 2) {
                        move = "Tag alle de synlige kort fra byggestablen med det nederste kort " + cards[0] + " og placer dem på " + cards2[cards2.length - 1].toString();
                        if (cards2.length - 2 >= 0 && cards[0].getSuit() == cards2[cards2.length - 2].getSuit()) {
                            return move;
                        }
                    }
                }
            }
        }
        return move;
    }

    /**
     * Moves top card of tableau or waste to another tableau if the suits match.
     * For example if you can move a 4 of hearts to a 5 of spades and 5 of clubs, prioritize the one that has a 6 of hearts in the same tableau.
     *
     * @return Instructions for player
     */
    //Hvis muligt sørg for at “typerne” passer. F.eks. hvis du kan rykke en hjerter 4 til to forskellige 5’er så prioriter den som har en hjerter 6
    public String typeStreak() {
        Card[] cards, cards2;
        String move = "", prioMove = "";
        for (Tableau tableau : tableaus) {
            cards = tableau.getVisibleCards();

            for (Tableau tableau2 : tableaus) {
                cards2 = tableau2.getVisibleCards();

                //Hvis en af bunkerne er tomme er der ingen grund til at sammenligne dem
                if (cards.length - 1 >= 0 && cards2.length - 1 >= 0) {

                    //Hvis øverste kort i tableu passer med anden tableus øverste kort lig den på hvis "typerne" passer ellers vent
                    if (cards[cards.length - 1].getValue() == cards2[cards2.length - 1].getValue() - 1 && cards[cards.length - 1].getSuit() % 2 != cards2[cards2.length - 1].getSuit() % 2) {
                        move = "Tag " + cards[cards.length - 1] + " og placer kortet på " + cards2[cards2.length - 1].toString();
                        if (cards2.length - 2 >= 0 && cards[cards.length - 1].getSuit() == cards2[cards2.length - 2].getSuit()) {
                            return move;
                        }
                    }
                }
            }

            //hvis waste passer så lig den på
            if (waste != null && cards.length - 1 >= 0 && cards[cards.length - 1].getValue() - 1 == waste.getValue() && cards[cards.length - 1].getSuit() % 2 != waste.getSuit() % 2) {
                move = "Tag " + waste.toString() + " og placer kortet på " + cards[cards.length - 1].toString();
                if (cards.length - 2 >= 0 && waste.getSuit() == cards[cards.length - 2].getSuit()) {
                    prioMove = move;
                }
            }
        }

        return !prioMove.equals("") ? prioMove : move;
    }

    /**
     * Translator to tell if possible to draw card from the waste pile
     *
     * @return Instructions to player
     */
    public String revealCardFromWaste() {
        return wastePile ? "Vend et kort fra grundbunken" : "";
    }
}
