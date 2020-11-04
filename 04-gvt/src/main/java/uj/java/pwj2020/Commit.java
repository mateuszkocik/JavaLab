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
                addFileToObjects(fileName, sha1);
                System.out.println("File " + fileName + " committed successfully.");
            }else{
                System.out.println("File " + fileName + " is not added to gvt.");
            }


        }else{
            System.out.println("File " + fileName + " does not exist.");
            System.exit(51);
        }
    }

    private static void addFileToObjects(String fileName, String sha1) throws IOException{
        String newFilePath = Gvt.objectsPath + sha1;
        if(Files.notExists(Paths.get(newFilePath))){
            BufferedReader fr = new BufferedReader(new FileReader(fileName));
            BufferedWriter fw = new BufferedWriter(new FileWriter(newFilePath));
            String line;
            while((line = fr.readLine()) != null){
                fw.write(line);
            }
        }
    }
}
