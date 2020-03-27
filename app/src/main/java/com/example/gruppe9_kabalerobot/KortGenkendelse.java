package com.example.gruppe9_kabalerobot;

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
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;

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
        image.setImageResource(R.drawable.test4);

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
        System.out.println("running camera");
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat matrix;
        //capture.read(matrix);
        matrix = Utils.loadResource(this, R.drawable.test4, CvType.CV_8UC4);
        //ic.imwrite("test.png", matrix);
        Mat gray = new Mat();
        Imgproc.cvtColor(matrix, gray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.medianBlur(gray, gray, 5);
        Mat circles = new Mat();



        Mat hsv = new Mat();
        Imgproc.cvtColor(matrix, hsv, Imgproc.COLOR_BGR2HSV);
        Scalar lower_bound = new Scalar(187, 182, 188);
        Scalar upper_bound = new Scalar(250, 251, 251);
        Mat dst = new Mat();
        Core.inRange(matrix, lower_bound, upper_bound, dst);
        Mat detect = new Mat();
        Core.bitwise_and(hsv, hsv, detect, dst);

        Imgproc.HoughCircles(gray, circles, Imgproc.HOUGH_GRADIENT, 1.0, (double) gray.rows()/16, 100.0, 30.0, 1, 50);

        for (int x=0; x < circles.cols(); x++) {
            double[] c = circles.get(0, x);
            Point center = new Point(Math.round(c[0]), Math.round(c[1]));
            Imgproc.circle(detect, center, 1, new Scalar(187, 182, 188), 1, 2, 0);
            int radius = (int) Math.round(c[2]);
            Imgproc.circle(detect, center, radius, new Scalar(250, 251, 251), 3, 8, 0);
        }


        Bitmap ne=Bitmap.createBitmap(matrix.width(),matrix.height(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(detect,ne);
        image.setImageBitmap(ne);


    }

}
