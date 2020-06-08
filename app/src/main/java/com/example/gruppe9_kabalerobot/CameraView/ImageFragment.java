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

import com.example.gruppe9_kabalerobot.Haarcascade.Haarcascade;
import com.example.gruppe9_kabalerobot.R;

public class ImageFragment extends Fragment {

    //region Fields

    private ImageView imageView;
    private Bitmap bitmap, cascadeResult;
    private Haarcascade haarcascade;
    private ProgressDialog loadingDialog;

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

        haarcascade = new Haarcascade(getActivity());

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

            cascadeResult = haarcascade.runCardRecognition(bitmap);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try {
                getActivity().runOnUiThread(() -> imageView.setImageBitmap(cascadeResult));
            }
            catch (NullPointerException e){
                System.out.println("WARNING!: runOnUIThread encounted Nullpointer at: " + e.getMessage());
            }

            loadingDialog.dismiss();
        }
    };



}
