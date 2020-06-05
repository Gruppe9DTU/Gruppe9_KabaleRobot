package com.example.gruppe9_kabalerobot.CardPlacment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gruppe9_kabalerobot.CardPlacment.CardObj;
import com.example.gruppe9_kabalerobot.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Cardplacment  {

    int semaphore1 = 0, semaphore2 = 0;

    ArrayList<CardObj> coordinates = new ArrayList<>();
    ArrayList<CardObj> extraCard = new ArrayList<>();
    ArrayList<CardObj> winningStack = new ArrayList<>();
    ArrayList<CardObj> stack1 = new ArrayList<>();
    ArrayList<CardObj> stack2 = new ArrayList<>();
    ArrayList<CardObj> stack3 = new ArrayList<>();
    ArrayList<CardObj> stack4 = new ArrayList<>();
    ArrayList<CardObj> stack5 = new ArrayList<>();
    ArrayList<CardObj> stack6 = new ArrayList<>();

    /**
     * Method that sorts the cards
     */
   public void SortCards(){
        addCoordinates();
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

    }

    /**
     * This method is made for running thread with,to sort the stacks for the y-coordinate
     */
    private void sortStack1(){

        compareY(stack1);
        compareY(stack2);
        compareY(stack3);

        //semaphore to make sure both threads finish at the same time
        while(semaphore1 == 0 ){
            semaphore2 = 1;
        }
    }

    /**
     * This method is made for running thread with,to sort the stacks for the y-coordinate
     */
    private void sortStack2(){

        compareY(stack4);
        compareY(stack5);
        compareY(stack6);

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
            if (extraCard.contains(x) || winningStack.contains(x)) {
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
            //Checks if the X coordinate is inside of 10% else it is going to the next stack.
            if (coordinates.get(i).getX() >= (coordinates.get(i-1).getX()*0.9) &&
                    coordinates.get(i).getX() <= (coordinates.get(i-1).getX()*1.1)){
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
                stack1.add(coordinates.get(i));
                break;
            case 2:
                stack2.add(coordinates.get(i));
                break;
            case 3:
                stack3.add(coordinates.get(i));
                break;
            case 4:
                stack4.add(coordinates.get(i));
                break;
            case 5:
                stack5.add(coordinates.get(i));
                break;
            case 6:
                stack6.add(coordinates.get(i));
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
            extraCard.add(coordinates.get(i));
        }

        compareX(extraCard);

        //Lower- and upperTail are +/- 10% of the x-coordinat
        int lowerTail = (int) (extraCard.get(0).getY()*0.9);
        int upperTail = (int) (extraCard.get(0).getY()*1.1);
        for (int i = 1; i <extraCard.size() ; i++) {

            if( extraCard.get(i).getY() >= lowerTail && extraCard.get(i).getY() <= upperTail) {
                winningStack.add(extraCard.get(i));
            }
        }
        extraCard.subList(1,extraCard.size()).clear();
        coordinates.remove(extraCard);
    }

    /**
     * Sort the arraylist for x
     * @param list the given list that i want to sort
     */
    private void  compareX(ArrayList<CardObj> list){
        Collections.sort(list, new Comparator<CardObj>() {
            @Override
            public int compare(CardObj a, CardObj o2) {
                return Integer.compare(a.x, o2.x);
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
                return Integer.compare(o1.y, o2.y);
            }
        });

    }


    /**
     * Test data for testing
     */
    private void addCoordinates(){
        coordinates.add(new CardObj(1,4,12,1));
        coordinates.add(new CardObj(2,4,4,3));
        coordinates.add(new CardObj(3,4,4,0));
        coordinates.add(new CardObj(4,2,7,3));


        coordinates.add(new CardObj(1,1,1,2));
        coordinates.add(new CardObj(1,2,4,3));
        coordinates.add(new CardObj(2,1,10,3));
        coordinates.add(new CardObj(3,1,8,3));
        coordinates.add(new CardObj(4,1,9,3));
    }

    /**
     * Getter for stack1
     * @return the stack1
     */
    public ArrayList<CardObj> getStack1() {
        return stack1;
    }

    /**
     * Getter for stack2
     * @return the stack2
     */
    public ArrayList<CardObj> getStack2() {
        return stack2;
    }

    /**
     * Getter for stack3
     * @return the stack3
     */
    public ArrayList<CardObj> getStack3() {
        return stack3;
    }

    /**
     * Getter for stack4
     * @return the stack4
     */
    public ArrayList<CardObj> getStack4() {
        return stack4;
    }

    /**
     * Getter for stack5
     * @return the stack5
     */
    public ArrayList<CardObj> getStack5() {
        return stack5;
    }

    /**
     * Getter for stack6
     * @return the stack6
     */
    public ArrayList<CardObj> getStack6() {
        return stack6;
    }

    /**
     * Getter for winningStack
     * @return the winnngStack
     */
    public ArrayList<CardObj> getWinningStack() {
        return winningStack;
    }

    /**
     * Getter for extraCard
     * @return the extraCard
     */
    public ArrayList<CardObj> getExtraCard() {
        return extraCard;
    }
}