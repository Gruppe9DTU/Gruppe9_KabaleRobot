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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.BitmapCompat;
import androidx.fragment.app.Fragment;

import com.example.gruppe9_kabalerobot.Client.Client;
import com.example.gruppe9_kabalerobot.Haarcascade.Haarcascade;
import com.example.gruppe9_kabalerobot.R;

public class ImageFragment extends Fragment {

    //region Fields

    private ImageView imageView;
    private Bitmap bitmap, rectanglesDrawn;
    private Haarcascade haarcascade;
    private ProgressDialog loadingDialog;
    private Client c = Client.getInstance();
    private int[] dataArray;

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

            rectanglesDrawn = haarcascade.drawRectangles(bitmap,dataArray);

            //c.closeAllStreams();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(rectanglesDrawn);
                }
            });

            loadingDialog.dismiss();
        }
    };

    //endregion

}
