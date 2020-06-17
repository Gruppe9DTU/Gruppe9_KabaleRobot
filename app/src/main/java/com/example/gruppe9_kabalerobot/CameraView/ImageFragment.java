package com.example.gruppe9_kabalerobot.CameraView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

import java.util.ArrayList;
import java.util.List;

public class ImageFragment extends Fragment {

    //region Fields

    private ImageView imageView;
    private Bitmap bitmap, rectanglesDrawn;
    private OpenCV openCV;
    private ProgressDialog loadingDialog;
    private Client c = Client.getInstance();
    private int[][] dataArray;
    private String suggestedMove;


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

        openCV = new OpenCV();

        // Set imageview to the picture you have taken

        imageView.setImageBitmap(bitmap);

        // Run recognition

        cascadeBackground.execute();

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onStop() {
        super.onStop();
        loadingDialog.dismiss();
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().getSupportFragmentManager().popBackStack();
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

            c.sendImage(bitmap);

            dataArray = c.recieveData();

            rectanglesDrawn = openCV.drawRectangles(bitmap,dataArray);

            //TODO: Uncomment when correct data is available

            constructCards();
/*
            cardPlacement.sortCards(cardObjList);

            translator = new CardTranslator(cardPlacement);

            suggestedMove = solitaireController.takeMove(translator);

            */

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            getActivity().runOnUiThread(() -> {
                imageView.setImageBitmap(rectanglesDrawn);
                //Toast.makeText(getActivity(), suggestedMove, Toast.LENGTH_LONG).show();
            });

            loadingDialog.dismiss();
        }
    };

    //endregion

    private void constructCards(){
      // solitaireController = new SolitaireController();
       // cardPlacement = new CardPlacement();
       // cardObjList = new ArrayList<>();

        for(int i = 0; i<dataArray.length; i++){

            CardObj cardObj = new CardObj(dataArray[i][0],dataArray[i][1],dataArray[i][4],dataArray[i][5]);
            cardObjList.add(cardObj);
            System.out.println(cardObjList.get(0));
            System.out.println("Suit " +cardObj.getSuit() + " Number " + cardObj.getValue() );

        }

    }

}
