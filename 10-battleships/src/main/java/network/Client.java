package network;

import game.BattleshipCommand;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class Client extends Player{

    public Client(int port, File mapFile){
        super(port,mapFile);
    }

    @Override
    public void startGame(){
        try{
            Socket socket = new Socket(getAddress(),port);
            setInputOutput(socket);
            send(BattleshipCommand.START, game.getNextCell());
        }catch(IOException e){
            System.err.println("Error while starting client");
            e.printStackTrace();
        }
        playGame();
    }
}
