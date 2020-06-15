package com.example.gruppe9_kabalerobot.Client;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.Mat;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

public class Client {

    private Socket socket;

    private final int serverPort = 7777;
    private final String server_ip = "192.168.0.27";

    private static Client instance;
    private BufferedReader reader;
    private ByteArrayOutputStream stream;
    private BufferedOutputStream output;

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

        try{
            stream = new ByteArrayOutputStream();

            imageToSend.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            byte[] byteArray = stream.toByteArray();

            output = new BufferedOutputStream(socket.getOutputStream());

            output.write((Integer.toString(stream.size())).getBytes());
            output.flush();
            Thread.sleep(200);
            output.write(byteArray);
            output.flush();

            imageToSend.recycle();
        }
        catch (Exception e1){
            e1.printStackTrace();
        }


    }

    public void recieveData() {

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String fromServer;
            System.out.println("BEFORE recieveData");
            while ((fromServer=reader.readLine())!=null) {
                System.out.println("Recieved: " + fromServer);
            }
            System.out.println("AFTER recieveData");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void closeAllStreams() {
        try {
            reader.close();
            output.close();
            stream.close();
        }catch (IOException e){
            System.out.println("WARING! Could not close streams" + e.getMessage());
        }
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
