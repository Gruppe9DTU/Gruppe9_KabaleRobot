package com.example.gruppe9_kabalerobot;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.opencv.android.*;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class KortGenkendelse extends AppCompatActivity implements View.OnClickListener {
    Button button;
    TextView textView;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);

        image = findViewById(R.id.image123);
        image.setImageResource(R.drawable.e212);

        textView = findViewById(R.id.hello);

        if (OpenCVLoader.initDebug()){
            textView.setText("YES SUCCESS");
        } else {
            textView.setText("FAILED");
        }
    }

    @Override
    public void onClick(View v) {
        if (v == button){
            try {
                run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat matrix;
        matrix = Utils.loadResource(this, R.drawable.e212);
        Mat gray = new Mat();
        Imgproc.cvtColor(matrix, gray, Imgproc.COLOR_BGR2GRAY);
        CascadeClassifier cascade_2 = initClassifier(R.raw.a15cascade);
        CascadeClassifier cascade_5 = initClassifier(R.raw.cascade);

        MatOfRect c2 = new MatOfRect();
        MatOfRect c5 = new MatOfRect();
        cascade_2.detectMultiScale(matrix,c2);
        cascade_5.detectMultiScale(matrix,c5);

        for (Rect rect : c2.toArray()) {
            Imgproc.rectangle(matrix, new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(255, 0, 0));
        }
        for (Rect rect : c5.toArray()) {
            Imgproc.rectangle(matrix, new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
        }

        Imgproc.cvtColor(matrix, matrix, Imgproc.COLOR_BGR2RGB);
        Bitmap ne=Bitmap.createBitmap(matrix.width(),matrix.height(), Bitmap.Config.RGB_565);
        Utils.matToBitmap(matrix,ne);
        image.setImageBitmap(ne);


    }


    CascadeClassifier initClassifier(int res){
        CascadeClassifier cascade=null;
        try {
            InputStream is = getResources().openRawResource(res);
            File cascadeDir = getDir("cascade", Context.MODE_PRIVATE);
            File mCascadeFile = new File(cascadeDir, "haarcascade_mcs_nose.xml");
            FileOutputStream os = new FileOutputStream(mCascadeFile);

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            os.close();

            cascade = new CascadeClassifier(mCascadeFile.getAbsolutePath());
            if (cascade.empty()) {
                System.out.println("feck");
                cascade = null;
            } else
                System.out.println("yay");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("oh no");
        }
        return cascade;
    }

}
