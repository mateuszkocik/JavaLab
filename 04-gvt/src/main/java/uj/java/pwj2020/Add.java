package uj.java.pwj2020;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Add{

    public static void add(String fileName) throws IOException{
        if(Files.exists(Paths.get(fileName))){
            if(!Head.checkIfFileNameIsInHead(fileName)){
                Head.addFileNameToHead(fileName);
            }else{
                System.out.println("File " + fileName + " already added.");
            }
        }else{
            System.out.println("File " + fileName + " not found.");
            System.exit(21);
        }


        /*if(Files.exists(Paths.get(fileName))){
            String sha1 = Sha1File.generateSha1FromFile(fileName);
            if(Head.checkIfFileIsInHead(fileName, sha1)){
                System.out.println("File " + fileName + " already added.");
                System.exit(1);
            }

            if(Head.checkIfFileNameIsInHead(fileName)) Head.replaceSha1InHead(fileName, sha1);
            else Head.addFileToHead(fileName, sha1);

            addFileToObjects(fileName, sha1);
            System.out.println("File " + fileName + " added successfully.");

        }else{
            System.out.println("File " + fileName + " not found.");
            System.exit(21);
        }*/
    }




}
