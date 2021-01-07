package network;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Player{

    public Server(int port, File mapFile){
        super(port,mapFile);
    }

    @Override
    public void startGame(){
        try{
            ServerSocket serverSocket = new ServerSocket(port,1, getAddress());
            Socket socket = serverSocket.accept();
            setInputOutput(socket);
        }catch(IOException e){
            System.err.println("Error while starting server");
            e.printStackTrace();
        }
        playGame();
    }

}
