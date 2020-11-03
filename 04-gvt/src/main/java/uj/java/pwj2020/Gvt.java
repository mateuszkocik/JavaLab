package uj.java.pwj2020;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Gvt {

    public static void main(String... args) {
        if(args.length > 0){
            try{
                String command = args[0];
                if(command.equals("init")){
                    init();
                }else if(command.equals("add")){
                    System.out.println("Current directory is not initialized. Please use \\\"init\\\" command to initialize.");
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

    private static void init() throws IOException{
        Path gvtDir = Paths.get(".gvt");
        if(Files.notExists(gvtDir)){
            Files.createDirectory(gvtDir);
            System.out.println("Current directory initialized successfully.");
        }else{
            System.out.println("Current directory is already initialized.");
            System.exit(10);
        }
    }




}
