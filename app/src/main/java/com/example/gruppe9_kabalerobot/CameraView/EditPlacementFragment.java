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
import android.widget.Toast;
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
    private boolean error = false;


    public EditPlacementFragment(CardPlacement cardPlacement){
        this.cardPlacement = cardPlacement;
    }

    public EditPlacementFragment(){
        if (cardPlacement==null) {
            this.cardPlacement = new CardPlacement();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) { super.onCreate(savedInstanceState);}


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

        fillLoadedData();
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
            cardPlacement.setWastePile(isWasteDeck);
            if(waste.getText().length() > 0) insertIntoWaste(waste,cardPlacement.getWaste());
            if(foundation1.getText().length() > 0) insertIntoFoundation(foundation1,cardPlacement.getFoundations());
            if(foundation2.getText().length() > 0) insertIntoFoundation(foundation2,cardPlacement.getFoundations());
            if(foundation3.getText().length() > 0) insertIntoFoundation(foundation3,cardPlacement.getFoundations());
            if(foundation4.getText().length() > 0) insertIntoFoundation(foundation4,cardPlacement.getFoundations());
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

            if (!error) {

                translator = new CardTranslator(cardPlacement);

                buildDialog(solitaireController.takeMove(translator));

            }

            error = false;

            cardPlacement = new CardPlacement();
        }

    }


    /**
     * This method adds the typed card to the provided list
     * @param textField text input field from UI
     * @param waste list of CardObj in waste
     */
    private void insertIntoWaste(EditText textField, List<CardObj> waste) {
        //This is for a single card only, add index if we want more cards
        CardObj cardObj = editTextToCardDecoder(textField);
        if (cardObj== null){
            error = true;
            buildToast();
            return;
        }

        if(waste.size() > 0) waste.set(0, cardObj);
        else waste.add(editTextToCardDecoder(textField));
    }

    /**
     * This method adds the typed card to the provided list
     * @param textField text input field from UI
     * @param foundation list of CardObj in foundation
     */
    private void insertIntoFoundation(EditText textField, List<CardObj> foundation) {
        //Look through list for matching suit, if so replace
        CardObj newCard = editTextToCardDecoder(textField);
        if (newCard==null){
            error = true;
            buildToast();
            return;
        }

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

    /**
     * This method adds the typed card to the provided list at the specific index
     * @param textField text input field from UI
     * @param hiddenCards list of CardObj in hiddencards at index Tableau
     * @param index the Tableau index
     */
    private void insertIntoHiddenCards(EditText textField, List<Integer> hiddenCards, int index) {
        hiddenCards.set(index, Integer.parseInt(textField.getText().toString()));
    }

    /**
     * This method adds the typed card to the provided list either at top or bottom
     * @param textField text input field from UI
     * @param tableau list of CardObj in the Tableau
     * @param atEnd boolean value to determine if CardObj is at top or bottom of Tableau
     */
    private void insertIntoTableau(EditText textField, List<CardObj> tableau, boolean atEnd) {
        CardObj cardObj = editTextToCardDecoder(textField);
        if (cardObj== null){
            error = true;
            buildToast();
            return;
        }

        if(atEnd) tableau.add(0, cardObj);
        else tableau.add(editTextToCardDecoder(textField));
    }

    /**
     * This method decodes the provided text to a CardObj
     * @param textField text input field from UI
     * @return the new CardObj recieved from textfield String
     */
    private CardObj editTextToCardDecoder(EditText textField) {
        String textTemp = textField.getText().toString();
        String text = textTemp.length() == 2 ? "0" + textTemp : textTemp;
        int value = -1;
        try {

            value = Integer.parseInt(text.substring(0, 2));

        }catch (NumberFormatException e){
            error = true;
            buildToast();
        }
        if (value>13 || value<0){
            error = true;
            buildToast();
            return null;
        }
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
            default:
                error = true;
                buildToast();
                return null;
        }
        return new CardObj(0, 0, value, suit);
    }

    /**
     * This method translates a specific CardObj into a string
     * @param obj a card object
     * @return Card String
     */
    private String cardToEditTextDecoder(CardObj obj) {
        String value, suit="";
        if(obj.getValue() < 10) {
            value = "0" + obj.getValue();
        } else value = Integer.toString(obj.getValue());
        switch(obj.getSuit()) {
            case 1:
                suit = "H";
                break;
            case 2:
                suit = "S";
                break;
            case 3:
                suit = "D";
                break;
            case 4:
                suit = "C";
                break;
        }
        return value+suit;
    }

    /**
     * This method build a dialog with a specific message
     * @param move suggested move from algorithm
     */
    private void buildDialog(String move) {
        new AlertDialog.Builder(getContext())
                .setTitle("Det foretrukkende træk")
                .setMessage(move)
                .setPositiveButton("Tak", (dialogInterface, i) -> dialogInterface.dismiss())
                .show();

    }

    private void buildToast() {
        getActivity().runOnUiThread(() -> Toast.makeText(getActivity(), "Du har skrevet et ugyldigt kort. Prøv igen", Toast.LENGTH_SHORT).show());
    }

    private void fillLoadedData() {

        if (cardPlacement!=null)
        {
            wasteToggle.setChecked(cardPlacement.isWastePile());
            if (!cardPlacement.getWaste().isEmpty()) {
                waste.setText(cardToEditTextDecoder(cardPlacement.getWaste().get(0)));
            }

            if (!cardPlacement.getFoundations().isEmpty()) {
                foundation1.setText(cardToEditTextDecoder(cardPlacement.getFoundations().get(0)));
            }
            if (cardPlacement.getFoundations().size()>1) {
                foundation2.setText(cardToEditTextDecoder(cardPlacement.getFoundations().get(1)));
            }
            if (cardPlacement.getFoundations().size()>2) {
                foundation3.setText(cardToEditTextDecoder(cardPlacement.getFoundations().get(2)));
            }
            if (cardPlacement.getFoundations().size()>3) {
                foundation4.setText(cardToEditTextDecoder(cardPlacement.getFoundations().get(3)));
            }
            if (!cardPlacement.getTableau1().isEmpty() && cardPlacement.getTableau1().size()>1) {
                tab1For.setText(cardToEditTextDecoder(cardPlacement.getTableau1().get(cardPlacement.getTableau1().size() - 1)));
            }
            if (!cardPlacement.getTableau2().isEmpty() && cardPlacement.getTableau2().size()>1) {
                tab2For.setText(cardToEditTextDecoder(cardPlacement.getTableau2().get(cardPlacement.getTableau2().size() - 1)));
            }
            if (!cardPlacement.getTableau3().isEmpty() && cardPlacement.getTableau3().size()>1) {
                tab3For.setText(cardToEditTextDecoder(cardPlacement.getTableau3().get(cardPlacement.getTableau3().size() - 1)));
            }
            if (!cardPlacement.getTableau4().isEmpty() && cardPlacement.getTableau4().size()>1) {
                tab4For.setText(cardToEditTextDecoder(cardPlacement.getTableau4().get(cardPlacement.getTableau4().size() - 1)));
            }
            if (!cardPlacement.getTableau5().isEmpty() && cardPlacement.getTableau5().size()>1) {
                tab5For.setText(cardToEditTextDecoder(cardPlacement.getTableau5().get(cardPlacement.getTableau5().size() - 1)));
            }
            if (!cardPlacement.getTableau6().isEmpty() && cardPlacement.getTableau6().size()>1) {
                tab6For.setText(cardToEditTextDecoder(cardPlacement.getTableau6().get(cardPlacement.getTableau6().size() - 1)));
            }
            if (!cardPlacement.getTableau7().isEmpty() && cardPlacement.getTableau7().size()>1) {
                tab7For.setText(cardToEditTextDecoder(cardPlacement.getTableau7().get(cardPlacement.getTableau7().size() - 1)));
            }

            if (!cardPlacement.getTableau1().isEmpty()){
                tab1Bag.setText(cardToEditTextDecoder(cardPlacement.getTableau1().get(0)));
            }
            if (!cardPlacement.getTableau2().isEmpty()) {
                tab2Bag.setText(cardToEditTextDecoder(cardPlacement.getTableau2().get(0)));
            }
            if (!cardPlacement.getTableau3().isEmpty()) {
                tab3Bag.setText(cardToEditTextDecoder(cardPlacement.getTableau3().get(0)));
            }
            if (!cardPlacement.getTableau4().isEmpty()) {
                tab4Bag.setText(cardToEditTextDecoder(cardPlacement.getTableau4().get(0)));
            }
            if (!cardPlacement.getTableau5().isEmpty()) {
                tab5Bag.setText(cardToEditTextDecoder(cardPlacement.getTableau5().get(0)));
            }
            if (!cardPlacement.getTableau6().isEmpty()) {
                tab6Bag.setText(cardToEditTextDecoder(cardPlacement.getTableau6().get(0)));
            }
            if (!cardPlacement.getTableau7().isEmpty()) {
                tab7Bag.setText(cardToEditTextDecoder(cardPlacement.getTableau7().get(0)));
            }

            if (!cardPlacement.getHiddenCards().isEmpty()) {
                hiddencardsTab1.setText(""+cardPlacement.getHiddenCards().get(0));
            }
            if (cardPlacement.getHiddenCards().size()>1) {
                hiddencardsTab2.setText(""+cardPlacement.getHiddenCards().get(1));
            }
            if (cardPlacement.getHiddenCards().size()>2) {
                hiddencardsTab3.setText(""+cardPlacement.getHiddenCards().get(2));
            }
            if (cardPlacement.getHiddenCards().size()>3) {
                hiddencardsTab4.setText(""+cardPlacement.getHiddenCards().get(3));
            }
            if (cardPlacement.getHiddenCards().size()>4) {
                hiddencardsTab5.setText(""+cardPlacement.getHiddenCards().get(4));
            }
            if (cardPlacement.getHiddenCards().size()>5) {
                hiddencardsTab6.setText(""+cardPlacement.getHiddenCards().get(5));
            }
            if (cardPlacement.getHiddenCards().size()>6) {
                hiddencardsTab7.setText(""+cardPlacement.getHiddenCards().get(6));
            }
        }
    }

}
