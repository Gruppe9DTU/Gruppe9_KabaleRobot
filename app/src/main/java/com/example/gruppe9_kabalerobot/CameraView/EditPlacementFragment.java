package com.example.gruppe9_kabalerobot.CameraView;

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


    public EditPlacementFragment(CardPlacement cardPlacement){
        this.cardPlacement = cardPlacement;
    }

    public EditPlacementFragment(){}

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

            if(waste.getText().length() > 0) editTextToCardDecoder(waste.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(foundation1.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(foundation2.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(foundation3.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(foundation4.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(tab1Bag.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(tab2Bag.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(tab3Bag.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(tab4Bag.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(tab5Bag.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(tab6Bag.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(tab7Bag.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(tab1For.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(tab2For.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(tab3For.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(tab4For.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(tab5For.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(tab6For.getText().toString());
            if(.getText().length() > 0) insertIntoTableau(tab7For, cardPlacement.getTableau7(), false);
            if(.getText().length() > 0) editTextToCardDecoder(hiddencardsTab1.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(hiddencardsTab2.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(hiddencardsTab3.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(hiddencardsTab4.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(hiddencardsTab5.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(hiddencardsTab6.getText().toString());
            if(.getText().length() > 0) editTextToCardDecoder(hiddencardsTab7.getText().toString());


        }

    }

    //Set wastepile

    //Add to waste

    //Add to foundation
    private void insertIntoFoundation(EditText textField, List<CardObj> foundation, int index) {
        foundation.set(index, editTextToCardDecoder(textField));
    }
    //Add to hiddencard List (could be done here)
    private void insertIntoHiddenCards(EditText textField, List<Integer> hiddenCards, int index) {
        hiddenCards.set(index, Integer.parseInt(textField.getText().toString()));
    }
    //Add to start of tableau
    //Add to end of tableau
    private void insertIntoTableau(EditText textField, List<CardObj> tableau, boolean atEnd) {
        if(atEnd) tableau.add(editTextToCardDecoder(textField));
        else tableau.add(0, editTextToCardDecoder(textField);
    }

    private CardObj editTextToCardDecoder(EditText textField) {
        String text = textField.getText().toString();
        int value = Integer.parseInt(text.substring(0, 2));
        int suit = 0;
        switch(text.substring(2,3)) {
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




/*
    private void buildDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View mView = getLayoutInflater().inflate(R.layout.spinner_dialog,null);
        builder.setTitle("Test");
        typeSpinner = mView.findViewById(R.id.type);
        valueSpinner = mView.findViewById(R.id.value);
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.test));
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adapterValue = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.test));
        adapterValue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapterType);
        valueSpinner.setAdapter(adapterValue);

        builder.setPositiveButton("OK", this);
        builder.setNegativeButton("Annuller",this);

        builder.setView(mView);
        AlertDialog dialog = builder.create();
        dialog.show();


    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {




        dialogInterface.dismiss();
    }

 */
}
