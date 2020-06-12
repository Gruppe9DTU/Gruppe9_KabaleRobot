package com.example.gruppe9_kabalerobot.Client;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.Mat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private Socket socket;

    final int serverPort = 8888;
    private final String server_ip = "192.168.0.51";

    private static Client instance;

    private Client(){
        new Thread(new ClientThread()).start();
    }

    public static synchronized Client getInstance(){
        if(instance == null){
            instance = new Client();
        }
        return instance;
    }

    public void sendImage(Bitmap imageToSend) {
        Mat matrix = new Mat();
        Utils.bitmapToMat(imageToSend, matrix);
    }

    class ClientThread implements Runnable {
        @Override
        public void run() {
            try {
                InetAddress serverAddr = InetAddress.getByName(server_ip);
                socket = new Socket(serverAddr, serverPort);

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
