package com.example.gruppe9_kabalerobot;

import androidx.appcompat.app.AppCompatActivity;

import org.opencv.android.*;

public class KortGenkendelse extends AppCompatActivity {
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch(status) {
                case LoaderCallbackInterface.SUCCESS:
                    break;
            }
        }

    };

    @Override
    public void onResume() {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, this, mLoaderCallback);
    }

}
