package uj.java.pwj2020;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Gvt{
    public static final String objectsPath = ".gvt/objects/";
    public static final String headPath = ".gvt/head";
    public static final String versionsPath = ".gvt/versions/";
    public static final String versionPath = ".gvt/version";

    public static void main(String... args){
        if(args.length > 0){
            try{
                String command = args[0];
                if(Command.valueOf(command.toUpperCase()) != Command.INIT){
                    {
                        checkIfInitialized();
                    }
                }
                switch(Command.valueOf(command.toUpperCase())){
                    case INIT:{
                        Init.init();
                        break;
                    }
                    case ADD:{
                        processAdd(args);
                        break;
                    }
                    case DETACH:{
                        processDetach(args);
                        break;
                    }
                    case COMMIT:{
                        processCommit(args);
                        break;
                    }
                    case HISTORY:{
                        processHistory(args);
                        break;
                    }
                    case VERSION:{
                        processVersion(args);
                        break;
                    }
                    case CHECKOUT:{
                        processCheckout(args);
                        break;
                    }
                }
            }catch(IllegalArgumentException e){
                System.out.println("Unknown command " + args[0]);
                System.exit(1);
            }catch(IOException e){
                System.out.println("Underlying system problem. See ERR for details.");
                System.exit(-3);
                e.printStackTrace();
            }
        }else{
            System.out.println("Please specify command.");
            System.exit(1);
        }
    }

    private static void processCheckout(String[] args) throws IOException{
        if(checkIfArgIsNumber(args) && Version.versionExist(Integer.valueOf(args[1]))){
            int version = Integer.valueOf(args[1]);
            Checkout.checkout(version);
            System.out.println("Version " + version + " checked out successfully.");

        }else{
            System.out.println("Invalid version number: " + args[1] + ".");
            System.exit(40);
        }
    }

    private static void processVersion(String[] args) throws IOException{
        int version = getVersionFromArgs(args);
        if(Version.versionExist(version)){
            System.out.println("Version: " + version);
            var list = Version.showVersionMessage(version);
            int i = 0;
            for(; i < list.size() - 1; i++) System.out.println(list.get(i));
            System.out.print(list.get(i));
        }else{
            System.out.println("Invalid version number: " + version + ".");
            System.exit(60);
        }
    }

    private static int getVersionFromArgs(String[] args) throws IOException{
        int version;
        if(args.length == 1) version = Version.getLatestVersion();
        else version = Integer.parseInt(args[1]);
        return version;
    }

    private static void processHistory(String[] args) throws IOException{
        if(args.length == 3){
            if(args[1].equals("-last")){
                History.showLastN(Integer.parseInt(args[2]));
            }else{
                History.showAllVersions();
            }
        }else{
            History.showAllVersions();
        }
    }

    private static void processCommit(String[] args){
        try{
            checkIfFileIsSpecified(args, "commit", 50);
            Commit.commit(args[1]);
            addVersionWithMessage(args, "Committed file: ");
        }catch(IOException e){
            System.out.println("File " + args[1] + " cannot be commited, see ERR for details.");
            System.err.println(e);
            System.exit(-52);
        }
    }

    private static void processDetach(String[] args){
        try{
            checkIfFileIsSpecified(args, "detach", 30);
            if(Head.checkIfFileNameIsInHead(args[1])){
                Detach.detach(args[1]);
                addVersionWithMessage(args, "Detached file: ");
            }else{
                System.out.println("File " + args[1] + " is not added to gvt.");
            }
        }catch(IOException e){
            System.out.println("File " + args[1] + " cannot be detached, see ERR for details.");
            System.err.println(e);
            System.exit(31);
        }
    }

    private static void processAdd(String[] args) throws IOException{
        checkIfFileIsSpecified(args, "add", 20);
        try{
            Add.add(args[1]);
        }catch(IOException e){
            System.out.println("File " + args[1] + " cannot be added, see ERR for details.");
            System.err.println(e);
            System.exit(22);
        }
        addVersionWithMessage(args, "Added file: ");
    }

    private static void addVersionWithMessage(String[] args, String info) throws IOException{
        var versionMessage = new ArrayList<String>();
        versionMessage.add(info + args[1]);
        if(args.length == 4){
            versionMessage.add(args[3]);
        }
        Version.addVersion(versionMessage);
    }


    private static boolean checkIfArgIsNumber(String[] args){
        try{
            Integer.parseInt(args[1]);
        }catch(NumberFormatException e){
            return false;
        }catch(IndexOutOfBoundsException e){
            return false;
        }
        return true;
    }

    private static void checkIfInitialized(){
        Path gvtDir = Paths.get(".gvt");
        if(Files.notExists(gvtDir)){
            System.out.println("Current directory is not initialized. Please use \"init\" command to initialize.");
            System.exit(-2);
        }
    }

    private static void checkIfFileIsSpecified(String[] args, String command, int errorCode){
        if(args.length == 1){
            System.out.println("Please specify file to " + command + ".");
            System.exit(errorCode);
        }
    }

    enum Command{
        INIT, ADD, DETACH, CHECKOUT, COMMIT, VERSION, HISTORY;
    }


}
