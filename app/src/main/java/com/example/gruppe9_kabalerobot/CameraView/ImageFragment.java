package com.example.gruppe9_kabalerobot.CameraView;

import android.graphics.Bitmap;
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
    private Bitmap bitmap;
    private Haarcascade haarcascade;

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

        imageView = (ImageView) view.findViewById(R.id.imageView);

        haarcascade = new Haarcascade(getActivity());

        //Run recognition
        haarcascade.runCardRecognition(imageView,bitmap);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    //endregion

}
