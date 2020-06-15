package com.example.gruppe9_kabalerobot.Client;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.Mat;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

public class Client {

    private Socket socket;
    private static Bitmap bitmap;

    private final int serverPort = 8888;
    private final String server_ip = "192.168.0.51";

    private static Client instance;

    private Client(){
        new Thread(new ClientThread()).start();
    }

    public static synchronized Client getInstance(Bitmap newBitmap){
        bitmap = newBitmap;
        if(instance == null){
            instance = new Client();
        }
        return instance;
    }

    public void sendImage(Bitmap imageToSend) {
        //Mat matrix = new Mat();
        //Utils.bitmapToMat(imageToSend, matrix);
        //matrix.reshape(0,1);

        //long imgSize = matrix.total()*matrix.elemSize();

        try{
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            imageToSend.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            byte[] byteArray = stream.toByteArray();

            BufferedOutputStream output = new BufferedOutputStream(socket.getOutputStream());
            //System.out.println("TESSSS: "+stream.size());

            output.write((Integer.toString(stream.size())).getBytes());
            //System.out.println("DASDASDASD: "+ Arrays.toString((Integer.toString(stream.size())).getBytes()));
            output.flush();
            Thread.sleep(200);
            output.write(byteArray);
            output.flush();
            output.close();

            imageToSend.recycle();
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

    }

    class ClientThread implements Runnable {
        @Override
            public void run() {
            try {
                InetAddress serverAddr = InetAddress.getByName(server_ip);
                socket = new Socket(serverAddr, serverPort);
                sendImage(bitmap);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
