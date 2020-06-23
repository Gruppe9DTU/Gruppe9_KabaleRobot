package com.example.gruppe9_kabalerobot.CameraView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.example.gruppe9_kabalerobot.CardPlacement.CardObj;
import com.example.gruppe9_kabalerobot.CardPlacement.CardPlacement;
import com.example.gruppe9_kabalerobot.Framework.controller.CardTranslator;
import com.example.gruppe9_kabalerobot.Framework.controller.SolitaireController;
import com.example.gruppe9_kabalerobot.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class EditPlacementFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private EditText waste, foundation1, foundation2, foundation3, foundation4;
    private EditText tab1For, tab2For, tab3For, tab4For, tab5For, tab6For, tab7For;
    private EditText tab1Bag, tab2Bag, tab3Bag, tab4Bag, tab5Bag, tab6Bag, tab7Bag;
    private EditText hiddencardsTab1,hiddencardsTab2,hiddencardsTab3,
            hiddencardsTab4,hiddencardsTab5,hiddencardsTab6,hiddencardsTab7;
    private ToggleButton wasteToggle;
    private FloatingActionButton done;
    private boolean isWasteDeck = true;
    private CardPlacement cardPlacement;
    private CardTranslator translator;
    private SolitaireController solitaireController = new SolitaireController();


    public EditPlacementFragment(CardPlacement cardPlacement){
        this.cardPlacement = cardPlacement;
    }

    public EditPlacementFragment(){
        this.cardPlacement = new CardPlacement();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) { super.onCreate(savedInstanceState); }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_edit_placement, container, false);

        waste               = view.findViewById(R.id.wasteCard);
        foundation1         = view.findViewById(R.id.foundation1);
        foundation2         = view.findViewById(R.id.foundation2);
        foundation3         = view.findViewById(R.id.foundation3);
        foundation4         = view.findViewById(R.id.foundation4);
        tab1For             = view.findViewById(R.id.tab1F);
        tab2For             = view.findViewById(R.id.tab2F);
        tab3For             = view.findViewById(R.id.tab3F);
        tab4For             = view.findViewById(R.id.tab4F);
        tab5For             = view.findViewById(R.id.tab5F);
        tab6For             = view.findViewById(R.id.tab6F);
        tab7For             = view.findViewById(R.id.tab7F);
        tab1Bag             = view.findViewById(R.id.tab1B);
        tab2Bag             = view.findViewById(R.id.tab2B);
        tab3Bag             = view.findViewById(R.id.tab3B);
        tab4Bag             = view.findViewById(R.id.tab4B);
        tab5Bag             = view.findViewById(R.id.tab5B);
        tab6Bag             = view.findViewById(R.id.tab6B);
        tab7Bag             = view.findViewById(R.id.tab7B);
        hiddencardsTab1     = view.findViewById(R.id.hiddencardsTab1);
        hiddencardsTab2     = view.findViewById(R.id.hiddencardsTab2);
        hiddencardsTab3     = view.findViewById(R.id.hiddencardsTab3);
        hiddencardsTab4     = view.findViewById(R.id.hiddencardsTab4);
        hiddencardsTab5     = view.findViewById(R.id.hiddencardsTab5);
        hiddencardsTab6     = view.findViewById(R.id.hiddencardsTab6);
        hiddencardsTab7     = view.findViewById(R.id.hiddencardsTab7);
        wasteToggle         = view.findViewById(R.id.toggleWaste);
        done                = view.findViewById(R.id.done);

        wasteToggle.setOnCheckedChangeListener(this);
        done.setOnClickListener(this);






        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean value) {

        // If changed, the boolean value will change
        isWasteDeck = value;

    }

    @Override
    public void onClick(View view) {

        if (view == done){

            if(waste.getText().length() > 0) insertIntoWaste(waste,cardPlacement.getWaste());
            if(foundation1.getText().length() > 0) insertIntoFoundation(foundation1,cardPlacement.getFoundations(),0);
            if(foundation2.getText().length() > 0) insertIntoFoundation(foundation2,cardPlacement.getFoundations(),1);
            if(foundation3.getText().length() > 0) insertIntoFoundation(foundation3,cardPlacement.getFoundations(),2);
            if(foundation4.getText().length() > 0) insertIntoFoundation(foundation4,cardPlacement.getFoundations(),3);
            if(tab1For.getText().length() > 0) insertIntoTableau(tab1For, cardPlacement.getTableau1(), false);
            if(tab2For.getText().length() > 0) insertIntoTableau(tab2For, cardPlacement.getTableau2(), false);
            if(tab3For.getText().length() > 0) insertIntoTableau(tab3For, cardPlacement.getTableau3(), false);
            if(tab4For.getText().length() > 0) insertIntoTableau(tab4For, cardPlacement.getTableau4(), false);
            if(tab5For.getText().length() > 0) insertIntoTableau(tab5For, cardPlacement.getTableau5(), false);
            if(tab6For.getText().length() > 0) insertIntoTableau(tab6For, cardPlacement.getTableau6(), false);
            if(tab7For.getText().length() > 0) insertIntoTableau(tab7For, cardPlacement.getTableau7(), false);
            if(tab1Bag.getText().length() > 0) insertIntoTableau(tab1Bag, cardPlacement.getTableau1(), true);
            if(tab2Bag.getText().length() > 0) insertIntoTableau(tab2Bag, cardPlacement.getTableau2(), true);
            if(tab3Bag.getText().length() > 0) insertIntoTableau(tab3Bag, cardPlacement.getTableau3(), true);
            if(tab4Bag.getText().length() > 0) insertIntoTableau(tab4Bag, cardPlacement.getTableau4(), true);
            if(tab5Bag.getText().length() > 0) insertIntoTableau(tab5Bag, cardPlacement.getTableau5(), true);
            if(tab6Bag.getText().length() > 0) insertIntoTableau(tab6Bag, cardPlacement.getTableau6(), true);
            if(tab7Bag.getText().length() > 0) insertIntoTableau(tab7Bag, cardPlacement.getTableau7(), true);
            if(hiddencardsTab1.getText().length() > 0) insertIntoHiddenCards(hiddencardsTab1,cardPlacement.getHiddenCards(),0);
            if(hiddencardsTab2.getText().length() > 0) insertIntoHiddenCards(hiddencardsTab2,cardPlacement.getHiddenCards(),1);
            if(hiddencardsTab3.getText().length() > 0) insertIntoHiddenCards(hiddencardsTab3,cardPlacement.getHiddenCards(),2);
            if(hiddencardsTab4.getText().length() > 0) insertIntoHiddenCards(hiddencardsTab4,cardPlacement.getHiddenCards(),3);
            if(hiddencardsTab5.getText().length() > 0) insertIntoHiddenCards(hiddencardsTab5,cardPlacement.getHiddenCards(),4);
            if(hiddencardsTab6.getText().length() > 0) insertIntoHiddenCards(hiddencardsTab6,cardPlacement.getHiddenCards(),5);
            if(hiddencardsTab7.getText().length() > 0) insertIntoHiddenCards(hiddencardsTab7,cardPlacement.getHiddenCards(),6);

            translator = new CardTranslator(cardPlacement);

            buildDialog(solitaireController.takeMove(translator));

            cardPlacement = new CardPlacement();
        }

    }

    //Set wastepile

    //Add to waste
    private void insertIntoWaste(EditText textField, List<CardObj> waste) {
        //This is for a single card only, add index if we want more cards
        if(waste.size() > 0) waste.set(0, editTextToCardDecoder(textField));
        else waste.add(editTextToCardDecoder(textField));
    }
    //Add to foundation
    private void insertIntoFoundation(EditText textField, List<CardObj> foundation, int index) {
        //Look through list for matching suit, if so replace
        CardObj newCard = editTextToCardDecoder(textField);
        int i = 0;
        for(CardObj obj : foundation) {
            if(obj.getSuit() == newCard.getSuit()) {
                foundation.set(i, newCard);
                return;
            }
            i++;
        }
        //else add
        foundation.add(newCard);
    }
    //Add to hiddencard List (could be done here)
    private void insertIntoHiddenCards(EditText textField, List<Integer> hiddenCards, int index) {
        hiddenCards.set(index, Integer.parseInt(textField.getText().toString()));
    }
    //Add to start of tableau
    //Add to end of tableau
    private void insertIntoTableau(EditText textField, List<CardObj> tableau, boolean atEnd) {
        if(atEnd) tableau.add(0, editTextToCardDecoder(textField));
        else tableau.add(editTextToCardDecoder(textField));
    }

    private CardObj editTextToCardDecoder(EditText textField) {
        String textTemp = textField.getText().toString();
        String text = textTemp.length() == 2 ? "0" + textTemp : textTemp;
        int value = Integer.parseInt(text.substring(0, 2));
        int suit = 0;
        switch(text.substring(2,3).toUpperCase()) {
            case "H":
                suit = 1;
                break;
            case "S":
                suit = 2;
                break;
            case "D":
                suit = 3;
                break;
            case "C":
                suit = 4;
                break;
        }
        return new CardObj(0, 0, value, suit);
    }

//    private String cardToEditTextDecoder(CardObj obj) {
//        String value, suit;
//        if(obj.getValue() < 10) {
//            value = "0" + obj.getValue();
//        } else value = Integer.toString(obj.getValue());
//        suit = Integer.toString(obj.getSuit());
//        return value+suit;
//    }

    private void buildDialog(String move) {
        new AlertDialog.Builder(getContext())
                .setTitle("Det fortrukkende træk")
                .setMessage(move)
                .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        getActivity().getSupportFragmentManager().popBackStack();
                        dialogInterface.dismiss();

                    }
                })
                .show();

    }

}
