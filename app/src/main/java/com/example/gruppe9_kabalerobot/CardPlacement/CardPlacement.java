package com.example.gruppe9_kabalerobot.CardPlacement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class CardPlacement  {

    private List<CardObj> coordinates = new ArrayList<>();
    //TODO No control of wastepile?
    private List<CardObj> waste = new ArrayList<>();
    private List<CardObj> foundations = new ArrayList<>();
    private List<Integer> hiddenCards = new ArrayList<>(7);
    private List<CardObj> tableau1 = new ArrayList<>();
    private List<CardObj> tableau2 = new ArrayList<>();
    private List<CardObj> tableau3 = new ArrayList<>();
    private List<CardObj> tableau4 = new ArrayList<>();
    private List<CardObj> tableau5 = new ArrayList<>();
    private List<CardObj> tableau6 = new ArrayList<>();
    private List<CardObj> tableau7 = new ArrayList<>();


    public CardPlacement(){
        for (int i = 0; i <7 ; i++) {
            hiddenCards.add(0);
        }
    }
    /**
     * Method that sorts the cards
     */
    public void sortCards(List<CardObj> coordinates, double screenWidth, double screenHeight){

        this.coordinates = coordinates;
        sort(screenWidth,screenHeight);
       // upperRow(screenWidth,screenHeight);
        //stacks();
        //sortStacks();
    }

    private void sort(double screenWidth, double screenHeight) {

        for(CardObj obj: coordinates){

            if(obj.getY()<screenHeight*0.3){

                if(obj.getX()<=screenWidth*(2.0/7.0)){
                    waste.add(obj);
                }
                else if (obj.getX()<=screenWidth*(4.0/7.0) && obj.getX()>screenWidth*(2.0/7.0)){
                    foundations.add(obj);
                }
                else if (obj.getX()<=screenWidth*(5.0/7.0) && obj.getX()>screenWidth*(4.0/7.0)){
                    foundations.add(obj);
                }
                else if (obj.getX()<=screenWidth*(6.0/7.0) && obj.getX()>screenWidth*(5.0/7.0)){
                    foundations.add(obj);
                }
                else if (obj.getX()<=screenWidth && obj.getX()>screenWidth*6/7){
                    foundations.add(obj);
                }

            }
            else {
                if(obj.getX()<=screenWidth*(1.0/7.0)){
                    if(obj.getSuit() == 0) hiddenCards.add(0, hiddenCards.get(0)+1);
                    else tableau1.add(obj);
                }
                else if (obj.getX()<=screenWidth*(2.0/7.0) && obj.getX()>screenWidth*(1.0/7.0)){
                    if(obj.getSuit() == 0) hiddenCards.add(1, hiddenCards.get(1)+1);
                    else tableau2.add(obj);
                }
                else if (obj.getX()<=screenWidth*(3.0/7.0) && obj.getX()>screenWidth*(2.0/7.0)){
                    if(obj.getSuit() == 0) hiddenCards.add(2, hiddenCards.get(2)+1);
                    else tableau3.add(obj);
                }
                else if (obj.getX()<=screenWidth*(4.0/7.0) && obj.getX()>screenWidth*(3.0/7.0)){
                    if(obj.getSuit() == 0) hiddenCards.add(3, hiddenCards.get(3)+1);
                    else tableau4.add(obj);
                }
                else if (obj.getX()<=screenWidth*(5.0/7.0) && obj.getX()>screenWidth*(4.0/7.0)) {
                    if(obj.getSuit() == 0) hiddenCards.add(4, hiddenCards.get(4)+1);
                    else tableau5.add(obj);
                }
                else if (obj.getX()<=screenWidth*(6.0/7.0) && obj.getX()>screenWidth*(5.0/7.0)){
                    if(obj.getSuit() == 0) hiddenCards.add(5, hiddenCards.get(5)+1);
                    else tableau6.add(obj);
                }
                else if (obj.getX()<=screenWidth && obj.getX()>screenWidth*(6.0/7.0)){
                    if(obj.getSuit() == 0) hiddenCards.add(6, hiddenCards.get(6)+1);
                    else tableau7.add(obj);
                }
            }
        }
    }


    /**
     * This method create to threads that sort the stacks for their y-value.
     */
    private void sortStacks(){

        compareY(tableau1,0);
        Collections.reverse(tableau1);
        compareY(tableau2,1);
        Collections.reverse(tableau2);
        compareY(tableau3,2);
        Collections.reverse(tableau3);

        compareY(tableau4,3);
        Collections.reverse(tableau4);
        compareY(tableau5,4);
        Collections.reverse(tableau5);
        compareY(tableau6,5);
        Collections.reverse(tableau6);
        compareY(tableau7,6);
        Collections.reverse(tableau7);

    }


    /**
     * This method finds out which stack the cards from the arraylist coordinates are going to.
     */
    private void stacks(){
        ArrayList<CardObj> test = new ArrayList<>(coordinates);
        for (CardObj x: test ) {
            if (waste.contains(x) || foundations.contains(x)) {
                coordinates.remove(x);
            }
        }
        compareX(coordinates);

        int stackNumber = 1;

        for (int i = 0; i < coordinates.size() ; i++) {

            if (i == 0) {
                stackList(stackNumber,i);
                continue;
            }
            if (stackNumber == 7){
                stackList(stackNumber,i);
                continue;
            }
            //Checks if the X coordinate is inside of 10% else it is going to the next stack.
            int indexCheck = coordinates.get(i).getX();
            int upperTail = (int) (coordinates.get(i-1).getX()*1.1);
            int lowerTail = (int) (coordinates.get(i-1).getX()*0.9);

            if ( indexCheck >= lowerTail  &&
                    indexCheck <= upperTail ){
                stackList(stackNumber,i);

            } else {
                stackNumber++;
                stackList(stackNumber,i);
            }
        }
    }

    /**
     * Method to find out which stack the card should be added to.
     * @param stackNumber Is a variable that tells which stack the card is going in
     * @param i tells the index of from the coordinate array that we wants to add to the stack.
     */
    private void stackList(int stackNumber, int i) {
        switch (stackNumber) {
            case 1:
                tableau1.add(coordinates.get(i));
                break;
            case 2:
                tableau2.add(coordinates.get(i));
                break;
            case 3:
                tableau3.add(coordinates.get(i));
                break;
            case 4:
                tableau4.add(coordinates.get(i));
                break;
            case 5:
                tableau5.add(coordinates.get(i));
                break;
            case 6:
                tableau6.add(coordinates.get(i));
                break;
            case 7:
                tableau7.add(coordinates.get(i));
                break;
        }
    }


    /**
     * Extra card is at index 0 after run of this method
     * This finds the upperrow, the extra cards and the winning stack
     */
    private void upperRow(double screenWidth, double screenHeight){

        compareY(coordinates,-1);
        Collections.reverse(coordinates);

        List<Integer> upperList = new ArrayList<>();


        for (CardObj obj:coordinates) {
            if (obj.getY()<=screenHeight*0.2){

            }
        }

        compareX(waste);

        if(doesWasteExist()){
            //Lower- and upperTail are +/- 10% of the y-coordinat
            int lowerTail = (int) (waste.get(0).getY()*0.9);
            int upperTail = (int) (waste.get(0).getY()*1.1);
            for (int i = 1; i < waste.size() ; i++) {
                if( waste.get(i).getY() >= lowerTail && waste.get(i).getY() <= upperTail) {
                    foundations.add(waste.get(i));
                }
            }
            waste.subList(1, waste.size()).clear();
            coordinates.remove(waste);
        }
        else {
            int lowerTail = (int) (waste.get(0).getY()*0.9);
            int upperTail = (int) (waste.get(0).getY()*1.1);
            for (int i = 0; i < waste.size() ; i++) {
                if( waste.get(i).getY() >= lowerTail && waste.get(i).getY() <= upperTail) {
                    foundations.add(waste.get(i));
                }
            }
            waste.clear();
            coordinates.remove(foundations);
        }
    }

    /**
     * Checks if we have a waste stack or not
     * @return true or false
     */
    private boolean doesWasteExist(){

        //Checks the distance between waste index 1 and 2 are bigger than the uppertail.
        int upperTail = (int) (waste.get(0).getX()*1.2);
        return (waste.get(1).getX() - waste.get(0).getX()) >= upperTail;
    }

    /**
     * Sort the arraylist for x
     * @param list the given list that i want to sort
     */
    private void  compareX(List<CardObj> list){
        Collections.sort(list, new Comparator<CardObj>() {
            @Override
            public int compare(CardObj a, CardObj o2) {
                return Integer.compare(a.getX(), o2.getX());
            }
        });
    }

    /**
     * Sort the arraylist for y
     * @param list the given list that i want to sort
     */
    private void compareY(List<CardObj> list, int hiddenCardIndex ) {
        Collections.sort(list, new Comparator<CardObj>() {
            @Override
            public int compare(CardObj o1, CardObj o2) {
                return Integer.compare(o1.getY(), o2.getY());
            }
        });

        int hiddenCount = 0;
        if (hiddenCardIndex != -1) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getSuit() == 0) {
                    hiddenCount++;
                    list.remove(i);
                    i--;
                }
            }
            hiddenCards.set(hiddenCardIndex,hiddenCount);
        }
    }

    /**
     * Getter for hiddencards
     * @return the hiddencards
     */
    public List<Integer> getHiddenCards() { return hiddenCards; }
    public void setHiddenCards(List<Integer> hiddenCards) { this.hiddenCards = hiddenCards; }

    /**
     * Getter and setters for tableaus
     */



    public List<CardObj> getTableau1() {
        return tableau1;
    }
    public void setTableau1(List<CardObj> cardObjs) { this.tableau1 = cardObjs; }

    public List<CardObj> getTableau2() {
        return tableau2;
    }
    public void setTableau2(List<CardObj> cardObjs) { this.tableau2 = cardObjs; }

    public List<CardObj> getTableau3() {
        return tableau3;
    }
    public void setTableau3(List<CardObj> cardObjs) { this.tableau3 = cardObjs; }

    public List<CardObj> getTableau4() {
        return tableau4;
    }
    public void setTableau4(List<CardObj> cardObjs) { this.tableau4 = cardObjs; }

    public List<CardObj> getTableau5() {
        return tableau5;
    }
    public void setTableau5(List<CardObj> cardObjs) { this.tableau5 = cardObjs; }

    public List<CardObj> getTableau6() {
        return tableau6;
    }
    public void setTableau6(List<CardObj> cardObjs) { this.tableau6 = cardObjs; }

    public List<CardObj> getTableau7() {
        return tableau7;
    }
    public void setTableau7(List<CardObj> cardObjs) { this.tableau7 = cardObjs; }

    public ArrayList<List<CardObj>> getTableaus() {
        ArrayList<List<CardObj>> tableaus = new ArrayList<>();
        tableaus.add(tableau1);
        tableaus.add(tableau2);
        tableaus.add(tableau3);
        tableaus.add(tableau4);
        tableaus.add(tableau5);
        tableaus.add(tableau6);
        tableaus.add(tableau7);
        return tableaus;
    }

    /**
     * Getters and setters for foundations
     */
    public List<CardObj> getFoundations() {
        return foundations;
    }

    public void setFoundations(List<CardObj> foundations) { this.foundations = foundations; }

    /**
     * Getters and setters for waste
     */
    public List<CardObj> getWaste() {
        return waste;
    }

    public void setWaste(List<CardObj> waste) { this.waste = waste; }
}