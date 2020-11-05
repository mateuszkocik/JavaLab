package uj.java.pwj2020;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Commit{

    public static void commit(String fileName) throws IOException{
        if(Files.exists(Paths.get(fileName))){
            if(Head.checkIfFileNameIsInHead(fileName)){
                String sha1 = Sha1File.generateSha1FromFile(fileName);
                Head.replaceSha1InHead(fileName, sha1);
                Add.addFileToObjects(fileName);
                System.out.println("File " + fileName + " committed successfully.");
            }else{
                System.out.println("File " + fileName + " is not added to gvt.");
            }


        }else{
            System.out.println("File " + fileName + " does not exist.");
            System.exit(51);
        }
    }


}
