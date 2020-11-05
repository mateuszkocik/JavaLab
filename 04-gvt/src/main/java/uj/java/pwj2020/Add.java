package uj.java.pwj2020;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Add{

    public static void add(String fileName) throws IOException{
        if(Files.exists(Paths.get(fileName))){
            if(!Head.checkIfFileNameIsInHead(fileName)){
                Head.addFileToHead(fileName);
                addFileToObjects(fileName);
                System.out.println("File " + fileName + " added successfully.");
            }else{
                System.out.println("File " + fileName + " already added.");
            }
        }else{
            System.out.println("File " + fileName + " not found.");
            System.exit(21);
        }
    }

    public static void addFileToObjects(String fileName) throws IOException{
        String sha1 = Sha1File.generateSha1FromFile(fileName);
        String newFilePath = Gvt.objectsPath + sha1;

        if(Files.notExists(Paths.get(newFilePath))){
            FileInputStream fr = new FileInputStream(fileName);
            FileOutputStream fw = new FileOutputStream(newFilePath);
            int ch;
            while((ch = fr.read()) != -1){
                fw.write(ch);
            }
            fr.close();
            fw.close();
        }
    }
}
