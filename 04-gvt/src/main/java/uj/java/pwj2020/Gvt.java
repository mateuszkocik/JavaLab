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
                if(command.equals("init")){
                    Init.init();
                }else if(command.equals("add")){
                    checkIfInitialized();
                    checkIfFileIsSpecified(args, "add", 20);
                    Add.add(args[1]);
                    var versionMessage = new ArrayList<String>();
                    versionMessage.add("Added file: " + args[1]);
                    if(args.length == 4){
                        versionMessage.add(args[3]);
                    }
                    Version.addVersion(versionMessage);


                }else if(command.equals("detach")){
                    checkIfInitialized();
                    checkIfFileIsSpecified(args, "detach", 30);
                    if(Head.checkIfFileNameIsInHead(args[1])){
                        Detach.detach(args[1]);
                        var versionMessage = new ArrayList<String>();
                        versionMessage.add("Detached file: " + args[1]);
                        if(args.length == 4){
                            versionMessage.add(args[3]);
                        }
                        Version.addVersion(versionMessage);
                    }else{
                        System.out.println("File " + args[1] + " is not added to gvt.");
                    }

                }else if(command.equals("checkout")){
                    //nie wiedzialem co robic jesli ktos poda wiecej niz 1 parametr
                    checkIfInitialized();
                    if(checkIfArgIsNumber(args) && Version.versionExist(args)){
                        int version = Integer.valueOf(args[1]);
                        Checkout.checkout(version);
                        System.out.println("Version " + version + " checked out successfully.");

                    }else{
                        System.out.println("Invalid version number: " + args[1] + ".");
                        System.exit(40);
                    }
                }else if(command.equals("commit")){
                    checkIfInitialized();
                    checkIfFileIsSpecified(args, "commit", 50);
                    Commit.commit(args[1]);

                    var versionMessage = new ArrayList<String>();
                    versionMessage.add("Committed file: " + args[1]);
                    if(args.length == 4){
                        versionMessage.add(args[3]);
                    }
                    Version.addVersion(versionMessage);
                }else if(command.equals("history")){

                    if(args.length == 3){
                        if(args[1].equals("-last")){
                            History.showLastN(Integer.parseInt(args[2]));
                        }else{
                            History.showAllVersions();
                        }
                    }else{
                        History.showAllVersions();
                    }

                }else if(command.equals("version")){
                    int version;
                    if(args.length == 1) version = Version.getLatestVersion();
                    else version = Integer.parseInt(args[1]);

                    if(version >= 0 && version <= Version.getLatestVersion()){
                        System.out.println("Version: " + version);
                        var list = Version.showVersionMessage(version);
                        int i = 0;
                        for(; i < list.size() - 1; i++) System.out.println(list.get(i));
                        System.out.print(list.get(i));

                    }else{
                        System.out.println("Invalid version number: " + version + ".");
                        System.exit(60);
                    }

                }else{
                    System.out.println("Unknown command " + command);
                }
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


}
