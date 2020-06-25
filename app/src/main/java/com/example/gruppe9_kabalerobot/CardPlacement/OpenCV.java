package com.example.gruppe9_kabalerobot.CardPlacement;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class OpenCV {

    public OpenCV() {
    }


    public Bitmap drawRectangles(Bitmap image, int[][] data ){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat matrix = new Mat();
        Utils.bitmapToMat(image,matrix);
        Mat gray = new Mat();
        Imgproc.cvtColor(matrix, gray, Imgproc.COLOR_BGR2GRAY);

        for(int i = 0; i<data.length; i++){

            Imgproc.rectangle(matrix, new Point(data[i][0], data[i][1]), //x y
                    new Point(data[i][2] + data[i][0], data[i][3] + data[i][1]), //w h
                    new Scalar(0, 255 , 0),4);

        }
        Bitmap ne=Bitmap.createBitmap(matrix.width(),matrix.height(), Bitmap.Config.RGB_565);
        Utils.matToBitmap(matrix,ne);
        return ne;
    }
}
