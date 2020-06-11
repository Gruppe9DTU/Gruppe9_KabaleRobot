package com.example.gruppe9_kabalerobot.Client;

public class Client {

    final int ClientPORT = 6000;

    private static Client instance;

    private Client(){}

    public static synchronized Client getInstance(){
        if(instance == null){
            instance = new Client();
        }
        return instance;
    }

}
