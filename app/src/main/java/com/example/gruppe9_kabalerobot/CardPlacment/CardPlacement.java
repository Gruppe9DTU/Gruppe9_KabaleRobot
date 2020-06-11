package com.example.gruppe9_kabalerobot.CardPlacment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class CardPlacement  {

    int semaphore1 = 0, semaphore2 = 0;

    ArrayList<CardObj> coordinates = new ArrayList<>();
    ArrayList<CardObj> waste = new ArrayList<>();
    ArrayList<CardObj> foundation = new ArrayList<>();
    ArrayList<CardObj> tableau1 = new ArrayList<>();    //TODO We are not getting hidden cards atm.
    ArrayList<CardObj> tableau2 = new ArrayList<>();
    ArrayList<CardObj> tableau3 = new ArrayList<>();
    ArrayList<CardObj> tableau4 = new ArrayList<>();
    ArrayList<CardObj> tableau5 = new ArrayList<>();
    ArrayList<CardObj> tableau6 = new ArrayList<>();
    ArrayList<CardObj> tableau7 = new ArrayList<>();

    /**
     * Method that sorts the cards
     */
    public void sortCards(ArrayList<CardObj> coordinates){

        this.coordinates = coordinates;
        upperRow();
        stacks();
        sortStacks();
    }


    /**
     * This method create to threads that sort the stacks for their y-value.
     */
    private void sortStacks(){

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                sortStack1();
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                sortStack2();
            }
        });
        t2.start();

        while(semaphore1 == 0 && semaphore2 == 0);

    }

    /**
     * This method is made for running thread with,to sort the stacks for the y-coordinate
     */
    private void sortStack1(){

        compareY(tableau1);
        compareY(tableau2);
        compareY(tableau3);
        compareY(tableau7);

        //semaphore to make sure both threads finish at the same time
        while(semaphore1 == 0 ){
            semaphore2 = 1;
        }
    }

    /**
     * This method is made for running thread with,to sort the stacks for the y-coordinate
     */
    private void sortStack2(){

        compareY(tableau4);
        compareY(tableau5);
        compareY(tableau6);

        //semaphore to make sure both threads finish at the same time
        while (semaphore2 == 0){
            semaphore1 = 1;
        }
    }

    /**
     * This method finds out which stack the cards from the arraylist coordinates are going to.
     */
    private void stacks(){
        ArrayList<CardObj> test = new ArrayList<>(coordinates);
        for (CardObj x: test ) {
            if (waste.contains(x) || foundation.contains(x)) {
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
    private void upperRow(){

        compareY(coordinates);
        Collections.reverse(coordinates);

        for (int i = 0; i<5;i++) {
            waste.add(coordinates.get(i));
        }

        compareX(waste);

        //Lower- and upperTail are +/- 10% of the x-coordinat
        int lowerTail = (int) (waste.get(0).getY()*0.9);
        int upperTail = (int) (waste.get(0).getY()*1.1);
        for (int i = 1; i < waste.size() ; i++) {

            if( waste.get(i).getY() >= lowerTail && waste.get(i).getY() <= upperTail) {
                foundation.add(waste.get(i));
            }
        }
        waste.subList(1, waste.size()).clear();
        coordinates.remove(waste);
    }

    /**
     * Sort the arraylist for x
     * @param list the given list that i want to sort
     */
    private void  compareX(ArrayList<CardObj> list){
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
    private void compareY(ArrayList<CardObj> list ){
        Collections.sort(list, new Comparator<CardObj>() {
            @Override
            public int compare(CardObj o1, CardObj o2) {
                return Integer.compare(o1.getY(), o2.getY());
            }
        });

    }

    /**
     * Getter for stack1
     * @return the stack1
     */
    public ArrayList<CardObj> getTableau1() {
        return tableau1;
    }

    /**
     * Getter for stack2
     * @return the stack2
     */
    public ArrayList<CardObj> getTableau2() {
        return tableau2;
    }

    /**
     * Getter for stack3
     * @return the stack3
     */
    public ArrayList<CardObj> getTableau3() {
        return tableau3;
    }

    /**
     * Getter for stack4
     * @return the stack4
     */
    public ArrayList<CardObj> getTableau4() {
        return tableau4;
    }

    /**
     * Getter for stack5
     * @return the stack5
     */
    public ArrayList<CardObj> getTableau5() {
        return tableau5;
    }

    /**
     * Getter for stack7
     * @return the stack7
     */
    public ArrayList<CardObj> getTableau6() {
        return tableau6;
    }

    /**
     * Getter for stack6
     * @return the stack6
     */
    public ArrayList<CardObj> getTableau7() {
        return tableau7;
    }
    /**
     * Getter for winningStack
     * @return the winnngStack
     */
    public ArrayList<CardObj> getFoundation() {
        return foundation;
    }

    /**
     * Getter for extraCard
     * @return the extraCard
     */
    public ArrayList<CardObj> getWaste() {
        return waste;
    }
}