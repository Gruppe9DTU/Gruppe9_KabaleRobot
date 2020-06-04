package com.example.gruppe9_kabalerobot.Haarcascade;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.gruppe9_kabalerobot.R;

import org.opencv.android.Utils;
import org.opencv.core.Core;
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

public class Haarcascade {

    private Activity activity;

    public Haarcascade(Activity activity) {
        this.activity = activity;
    }

    public void runCardRecognition(ImageView image, Bitmap bitmap) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat matrix = new Mat();
        Utils.bitmapToMat(bitmap,matrix);
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

        Bitmap ne=Bitmap.createBitmap(matrix.width(),matrix.height(), Bitmap.Config.RGB_565);
        Utils.matToBitmap(matrix,ne);
        image.setImageBitmap(ne);
    }


    private CascadeClassifier initClassifier(int res){
        CascadeClassifier cascade=null;
        try {
            InputStream is = activity.getResources().openRawResource(res);
            File cascadeDir = activity.getDir("cascade", Context.MODE_PRIVATE);
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
