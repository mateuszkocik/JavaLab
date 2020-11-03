package uj.java.pwj2020;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

    public static void main(String... args) {
        if(args.length > 0){
            try{
                String command = args[0];
                if(command.equals("init")){
                    init();
                }else if(command.equals("add")){
                    checkIfInitialized();
                    checkIfFileIsSpecified(args);
                    String fileName = args[2];



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



    private static void checkIfFileIsSpecified(String[] args){
        if(args.length == 1){
            System.out.println("Please specify file to add.");
            System.exit(20);
        }
    }

    private static void add() throws IOException{

    }

    private static void checkIfInitialized(){
        Path gvtDir = Paths.get(".gvt");
        if(Files.notExists(gvtDir)){
            System.out.println("Current directory is not initialized. Please use \"init\" command to initialize.");
            System.exit(-2);
        }
    }

    private static void addVersion(int number, String message) throws IOException{
        Path file = Paths.get(".gvt/versions");
        Files.write(file, Arrays.asList(String.valueOf(number), message), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }

    private static String generateSha1FromFile(String filePath) throws IOException{
        FileInputStream fileInputStream = new FileInputStream(filePath);
        MessageDigest digest = null;
        byte[] resultByteArray = null;
        try{
            digest = MessageDigest.getInstance("SHA-1");
            DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, digest);
            byte[] bytes = new byte[1024];
            // read all file content
            while (digestInputStream.read(bytes) > 0);
            resultByteArray = digest.digest();
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        return bytesToHexString(resultByteArray);
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            int value = b & 0xFF;
            if (value < 16) {
                // if value less than 16, then it's hex String will be only
                // one character, so we need to append a character of '0'
                sb.append("0");
            }
            sb.append(Integer.toHexString(value).toUpperCase());
        }
        return sb.toString();
    }

    private static void init() throws IOException{
        Path gvtDir = Paths.get(".gvt");
        if(Files.notExists(gvtDir)){
            Files.createDirectory(gvtDir);
            makeFileInGVT("versions");
            makeDirectoryInGVT("objects");

            addVersion(0,"GVT initialized.");
            System.out.println("Current directory initialized successfully.");
        }else{
            System.out.println("Current directory is already initialized.");
            System.exit(10);
        }
    }

    private static void makeFileInGVT(String file) throws  IOException{
        Path filePath = Paths.get(".gvt/" + file);
        Files.createFile(filePath);
    }

    private static void makeDirectoryInGVT(String directory) throws IOException{
        Path directoryPath = Paths.get(".gvt/" + directory);
        Files.createDirectory(directoryPath);
    }




}
