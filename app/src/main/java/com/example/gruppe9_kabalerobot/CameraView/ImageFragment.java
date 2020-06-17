package com.example.gruppe9_kabalerobot.CameraView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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

import java.util.ArrayList;
import java.util.List;

public class ImageFragment extends Fragment implements View.OnClickListener {

    //region Fields

    private ImageView imageView;
    private Bitmap bitmap, rectanglesDrawn;
    private Button continueToMove;
    private ImageButton backButton;
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

        continueToMove = view.findViewById(R.id.continueToMove);
        backButton = view.findViewById(R.id.back_button);

        continueToMove.setOnClickListener(this);
        backButton.setOnClickListener(this);

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
    public void onClick(View view) {

        if (view == continueToMove){

            //TODO: Uncomment when correct data is available
/*
            constructCards();

            cardPlacement.sortCards(cardObjList);

            translator = new CardTranslator(cardPlacement);

            suggestedMove = solitaireController.takeMove(translator);

            */
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

           // c.sendImage(bitmap);

            //dataArray = c.recieveData();

            if (dataArray==null){
                cancel(true);
            }

            //rectanglesDrawn = openCV.drawRectangles(bitmap,dataArray);

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

    private void constructCards(){
        solitaireController = new SolitaireController();
        cardPlacement = new CardPlacement();
        cardObjList = new ArrayList<>();

        for(int i = 0; i<dataArray.length; i++){

            CardObj cardObj = new CardObj(dataArray[i][0],dataArray[i][1],dataArray[i][4],dataArray[i][5]);
            cardObjList.add(cardObj);

        }

    }
}
