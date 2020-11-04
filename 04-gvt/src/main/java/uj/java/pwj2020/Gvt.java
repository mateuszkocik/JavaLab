package uj.java.pwj2020;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Gvt {
    public static final String objectsPath = ".gvt/objects/";
    public static final String headPath = ".gvt/head";
    public static final String versionsPath = ".gvt/versions/";
    public static final String versionPath = ".gvt/version";

    public static void main(String... args) {
        if(args.length > 0){
            try{
                String command = args[0];
                if(command.equals("init")){
                    Init.init();
                }else if(command.equals("add")){
                    checkIfInitialized();
                    checkIfFileIsSpecified(args,20);
                    Add.add(args[1]);
                    var versionMessage = new ArrayList<String>();
                    versionMessage.add("Added file: " + args[1]);
                    if(args.length == 4){
                        versionMessage.add(args[3].substring(1,args[3].length()-1));
                    }
                    Version.addVersion(versionMessage);


                }else if(command.equals("detach")){
                    checkIfInitialized();
                    checkIfFileIsSpecified(args,30);


                }else if(command.equals("checkout")){

                }else if(command.equals("commit")){

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
                        for(String s: Version.showVersionMessage(version)) System.out.println(s);
                    }else{
                        System.out.println("Invalid version number: " + version+ ".");
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


    private static void checkIfInitialized(){
        Path gvtDir = Paths.get(".gvt");
        if(Files.notExists(gvtDir)){
            System.out.println("Current directory is not initialized. Please use \"init\" command to initialize.");
            System.exit(-2);
        }
    }

    private static void checkIfFileIsSpecified(String[] args, int errorCode){
        if(args.length == 1){
            System.out.println("Please specify file to add.");
            System.exit(errorCode);
        }
    }


}
