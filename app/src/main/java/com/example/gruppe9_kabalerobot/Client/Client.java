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

    private final int serverPort = 8888;
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
            imageToSend.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            byte[] byteArray = stream.toByteArray();

            output.write((Integer.toString(stream.size())).getBytes());
            output.flush();
            Thread.sleep(200);
            output.write(byteArray);
            output.flush();
        }
        catch (IOException | InterruptedException e1){
            e1.printStackTrace();
        }


    }

    public int[][] recieveData() {

        try {
            String fromServer, result="";
            while ((fromServer=reader.readLine())!=null) {
                System.out.println("Recieved: " + fromServer);
                result += fromServer;
                // The last element of that data will be two square brackets
                if (fromServer.contains("]")){
                    break;
                }
            }
            return stringToIntArrays(result);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int[][] stringToIntArrays(String result){
        String s = result.replaceAll("[\\[\\]]", " ").replaceAll("\\s+", " ").replaceAll(","," ");
        System.out.println("with replace " + s);
        String[] splitString = s.trim().split(" ");
        int[] intArray = Arrays.stream(splitString).mapToInt(Integer::parseInt).toArray();
        int[][] newIntArray = new int[intArray.length/6][6];
        int j=0;

        for (int i=0; i < intArray.length; i+=6){
            for (int k=0; k<6; k++){
                newIntArray[j][k] = intArray[k+i];
                if (k==5){
                    j++;
                }
            }
        }
        System.out.println(Arrays.deepToString(newIntArray));
        return newIntArray;
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
                stream = new ByteArrayOutputStream();
                output = new BufferedOutputStream(socket.getOutputStream());
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
