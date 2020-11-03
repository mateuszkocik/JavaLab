package uj.java.pwj2020;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Gvt {
    public static final String objectsPath = ".gvt/objects/";
    public static final String headPath = ".gvt/head";

    public static void main(String... args) {
        if(args.length > 0){
            try{
                String command = args[0];
                if(command.equals("init")){
                    Init.init();
                }else if(command.equals("add")){
                    checkIfInitialized();
                    checkIfFileIsSpecified(args);
                    Add.add(args[2]);

                }else if(command.equals("detach")){

                }else if(command.equals("checkout")){

                }else if(command.equals("commit")){

                }else if(command.equals("history")){

                }else if(command.equals("version")){

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

    private static void checkIfFileIsSpecified(String[] args){
        if(args.length == 1){
            System.out.println("Please specify file to add.");
            System.exit(20);
        }
    }


}
