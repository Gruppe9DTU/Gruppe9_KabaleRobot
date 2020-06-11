package com.example.gruppe9_kabalerobot.Socket;

public class Server {

    final int SERVERPORT = 6000;

    private static Server instance;

    private Server(){}

    public static synchronized Server getInstance(){
        if(instance == null){
            instance = new Server();
        }
        return instance;
    }

}
