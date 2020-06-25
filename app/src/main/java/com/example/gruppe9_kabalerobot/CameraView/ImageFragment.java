package com.example.gruppe9_kabalerobot.CameraView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gruppe9_kabalerobot.CardPlacement.CardObj;
import com.example.gruppe9_kabalerobot.CardPlacement.CardPlacement;
import com.example.gruppe9_kabalerobot.CardPlacement.OpenCV;
import com.example.gruppe9_kabalerobot.Client.Client;
import com.example.gruppe9_kabalerobot.Framework.controller.CardTranslator;
import com.example.gruppe9_kabalerobot.Framework.controller.SolitaireController;
import com.example.gruppe9_kabalerobot.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ImageFragment extends Fragment implements View.OnClickListener {

    //region Fields

    private ImageView imageView;
    private Bitmap bitmap, rectanglesDrawn, scaledImage;
    private FloatingActionButton continueToMove, edit;
    private ImageButton backButton;
    private OpenCV openCV;
    private ProgressDialog loadingDialog;
    private Client c = Client.getInstance();
    private int[][] dataArray;
    private String suggestedMove;
    private boolean enteredEdit = false;


    // Card and algorithm
    private List<CardObj> cardObjList;
    private CardPlacement cardPlacement;
    private CardTranslator translator;
    private SolitaireController solitaireController;

    //endregion

    //region Lifecycle

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bitmap = getArguments().getParcelable("path");

        View view = inflater.inflate(R.layout.fragment_image, container, false);

        imageView = view.findViewById(R.id.imageView);

        continueToMove = view.findViewById(R.id.continueToMove);
        backButton = view.findViewById(R.id.back_button);
        edit = view.findViewById(R.id.editPlacement);

        continueToMove.setOnClickListener(this);
        backButton.setOnClickListener(this);
        edit.setOnClickListener(this);

        scaledImage = bitmap;

        openCV = new OpenCV();

        // Set imageview to the picture you have taken

        imageView.setImageBitmap(scaledImage);

        // Run recognition
        // Check status of AsyncTask before running it, if we are already wait for a message
        if (cascadeBackground.getStatus() == AsyncTask.Status.PENDING) {
            cascadeBackground.execute();
        }

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onClick(View view) {
        

        if (view == continueToMove) {

            if (dataArray.length != 0 && dataArray != null){
                constructCards();

            cardPlacement.sortCards(cardObjList, bitmap.getWidth(), bitmap.getHeight());

            System.out.println("Width of bitmap: " + bitmap.getWidth() + " Height of bitmap: " + bitmap.getHeight());
            if (cardPlacement.getTableau1().size() > 0)
                System.out.println("Tableau 1 size: " + cardPlacement.getTableau1().size() + " suit: " + cardPlacement.getTableau1().get(0).getSuit() + " value: " + cardPlacement.getTableau1().get(0).getValue());
            if (cardPlacement.getTableau2().size() > 0)
                System.out.println("Tableau 2 size: " + cardPlacement.getTableau2().size() + " suit: " + cardPlacement.getTableau2().get(0).getSuit() + " value: " + cardPlacement.getTableau2().get(0).getValue());
            if (cardPlacement.getTableau3().size() > 0)
                System.out.println("Tableau 3 size: " + cardPlacement.getTableau3().size() + " suit: " + cardPlacement.getTableau3().get(0).getSuit() + " value: " + cardPlacement.getTableau3().get(0).getValue());
            if (cardPlacement.getTableau4().size() > 0)
                System.out.println("Tableau 4 size: " + cardPlacement.getTableau4().size() + " suit: " + cardPlacement.getTableau4().get(0).getSuit() + " value: " + cardPlacement.getTableau4().get(0).getValue());
            if (cardPlacement.getTableau5().size() > 0)
                System.out.println("Tableau 5 size: " + cardPlacement.getTableau5().size() + " suit: " + cardPlacement.getTableau5().get(0).getSuit() + " value: " + cardPlacement.getTableau5().get(0).getValue());
            if (cardPlacement.getTableau6().size() > 0)
                System.out.println("Tableau 6 size: " + cardPlacement.getTableau6().size() + " suit: " + cardPlacement.getTableau6().get(0).getSuit() + " value: " + cardPlacement.getTableau6().get(0).getValue());
            if (cardPlacement.getTableau7().size() > 0)
                System.out.println("Tableau 7 size: " + cardPlacement.getTableau7().size() + " suit: " + cardPlacement.getTableau7().get(0).getSuit() + " value: " + cardPlacement.getTableau7().get(0).getValue());

            System.out.println("Foundation size: " + cardPlacement.getFoundations().size());
            System.out.println("Waste size: " + cardPlacement.getWaste().size());

            translator = new CardTranslator(cardPlacement);

            buildDialog(solitaireController.takeMove(translator));
            }
            else buildToast();

        }
        else if (view == edit){

            if (dataArray.length != 0 && dataArray != null) {
                constructCards();
                cardPlacement.sortCards(cardObjList, bitmap.getWidth(), bitmap.getHeight());
                enteredEdit = true;
                getActivity().getSupportFragmentManager().popBackStack();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new EditPlacementFragment(cardPlacement))
                        .addToBackStack(null)
                        .commit();
            }
            else {
                enteredEdit = true;
                getActivity().getSupportFragmentManager().popBackStack();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new EditPlacementFragment())
                        .addToBackStack(null)
                        .commit();
            }


        }
        else if (view == backButton){
            getActivity().getSupportFragmentManager().popBackStack();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        loadingDialog.dismiss();
    }

    @Override
    public void onPause() {
        super.onPause();
        // We have to check if we have entered edit.
        // When replacing fragments onPause is called before change.
        if (!enteredEdit) getActivity().getSupportFragmentManager().popBackStack();
        loadingDialog.dismiss();
    }

    //endregion

    //region AsyncTask


    @SuppressLint("StaticFieldLeak")
    private AsyncTask<Void, Void, Void> cascadeBackground = new AsyncTask<Void, Void, Void>() {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            loadingDialog = ProgressDialog.show(getActivity(), "",
                    "Indlæser. Vent venligst", true);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            c.sendImage(scaledImage);

            dataArray = c.recieveData();

            if (dataArray==null){
                cancel(true);
            }

            rectanglesDrawn = openCV.drawRectangles(scaledImage,dataArray);

            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();

            getActivity().runOnUiThread(() -> {
                Toast.makeText(getActivity(), "Fandt ingen kort. Tag et nyt billede og prøv igen...", Toast.LENGTH_LONG).show();
            });
            loadingDialog.dismiss();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            getActivity().runOnUiThread(() -> {
                imageView.setImageBitmap(rectanglesDrawn);
            });

            loadingDialog.dismiss();

        }
    };

    //endregion

    //region Support methods

    /**
     * This method constructs the CardObjList from the data recieved from the python server.
     * This method also initialises classes regarding Solitaire Framework and Algorithm
     */
    private void constructCards(){
        solitaireController = new SolitaireController();
        cardPlacement = new CardPlacement();
        cardObjList = new ArrayList<>();

        for(int i = 0; i<dataArray.length; i++){

            CardObj cardObj = new CardObj(dataArray[i][0],dataArray[i][1],dataArray[i][4],dataArray[i][5]);
            cardObjList.add(cardObj);
        }
    }

    /**
     * This method builds a dialog with the message text of the suggested move
     * This String is returned from the SolitaireController class
     * @param suggestedMove
     */
    private void buildDialog(String suggestedMove) {
        new AlertDialog.Builder(getContext())
                .setTitle("Det foretrukkende træk")
                .setMessage(suggestedMove)
                .setPositiveButton("Tak", (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }
    
    private void buildToast(){
        getActivity().runOnUiThread(() -> Toast.makeText(getActivity(), "Der blev ikke fundet nogle kort og derfor ikke fortsætte", Toast.LENGTH_SHORT).show());
    }

    //endregion


}
