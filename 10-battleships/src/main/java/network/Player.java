package network;

import game.BattleshipCommand;
import game.Cell;
import game.Game;

import java.io.*;
import java.net.*;

import static game.BattleshipCommand.*;

public abstract class Player{

    private BufferedReader in;
    private BufferedWriter out;
    protected final Game game;
    protected int port;

    public Player(int port, File mapFile){
        this.game = new Game(mapFile);
        this.port = port;
    }

    public abstract void startGame();

    protected void playGame(){
        while(true){
            try{
                send(receive(), game.getNextCell());
            }catch(IOException e){
                System.err.println("Error while playing game");
                e.printStackTrace();
            }
        }
    }

    public void send(BattleshipCommand command, Cell cell) throws IOException{
        var messageBuilder = new StringBuilder();
        messageBuilder.append(command.getCommandText());
        if(command != LAST_FLOODED){
            messageBuilder.append(';').append(cell.getCords());
        }
        out.write(messageBuilder.toString());
        System.out.println(messageBuilder.toString());
        out.newLine();
        out.flush();
        endGameWhenLastFlooded(command,false);
    }

    private BattleshipCommand receive() throws IOException{
        BattleshipCommand receivedCommand;
        String message = in.readLine();
        System.out.println(message);
        String[] messageParts = message.split(";");
        receivedCommand = getBattleshipCommand(messageParts[0]);
        game.processBattleshipCommand(receivedCommand);
        endGameWhenLastFlooded(receivedCommand,true);
        char x = messageParts[1].charAt(0);
        int y = Integer.parseInt(messageParts[1].substring(1));
        return game.shootTheCell(x,y);
    }

    private void endGameWhenLastFlooded(BattleshipCommand receivedCommand, boolean win){
        if(receivedCommand == LAST_FLOODED){
            String message = win ? "Wygrana" : "Przegrana";
            System.out.println(message);
            game.showResults();
            System.exit(0);
        }
    }

    public void setInputOutput(Socket socket){
        try{
            this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static InetAddress getAddress(){
        try{
            NetworkInterface en0;
            en0 = NetworkInterface.getByName("en0");
            return en0.inetAddresses()
                    .filter(a -> a instanceof Inet4Address)
                    .findFirst()
                    .orElse(InetAddress.getLocalHost());
        }catch(SocketException | UnknownHostException e){
            e.printStackTrace();
        }
        return null;
    }

}
