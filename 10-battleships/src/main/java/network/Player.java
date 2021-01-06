package network;

import game.Game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.InetAddress;
import java.net.Socket;

public abstract class Player{

    private final InetAddress inetAddress;
    private final int port;
    private final BufferedWriter out;
    private final BufferedReader in;
    private Game game;
    private Socket socket;

    public Player(){

    }





    

}
