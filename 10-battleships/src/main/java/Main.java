import network.Client;
import network.Player;
import network.Server;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class Main{
    public static void main(String[] args){
        exitWhenNotValidArgs(args);
        String mode = args[1];
        int port = Integer.parseInt(args[3]);
        File file = new File(args[5]);
        Player player;
        player = mode.equals("server") ? new Server(port,file) : new Client(port,file);
        player.startGame();
    }

    public static void exitWhenNotValidArgs(String[] args){
        if(!validateArgs(args)) System.exit(0);
    }

    public static boolean validateArgs(String[] args){
        boolean isValid = false;
        try{
            isValid =
                    args.length == 6
                            && args[0].equals("-mode")
                            && (args[1].equals("client") || args[1].equals("server"))
                            && args[2].equals("-port")
                            && Integer.parseInt(args[3]) >= 0 && Integer.parseInt(args[3]) < 65535
                            && args[4].equals("-map");
            Paths.get(args[5]);
        }catch(NumberFormatException e){
            System.out.println("Wrong port number");
        }catch(InvalidPathException e){
            System.out.println("Wrong map path");
        }

        return isValid;
    }

}
