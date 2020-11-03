package uj.java.pwj2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class Add{

    public static void add(String fileName) throws IOException{
        String sha1 = Sha1File.generateSha1FromFile(fileName);
        if(Files.exists(Paths.get(fileName))){
            if(checkIfFileIsInHead(fileName,sha1)){
                System.out.println("File " + fileName + " already added.");
                System.exit(1);
            }

            addFileToHead(fileName,sha1);
            addFileToObjects(fileName,sha1);
            System.out.println("File " + fileName + " added successfully.");


        }else{
            System.out.println("File " + fileName + " not found.");
            System.exit(21);
        }
    }

    private static void addFileToObjects(String fileName, String sha1)throws IOException{
        String newFilePath = Gvt.objectsPath + sha1;
        if(Files.notExists(Paths.get(newFilePath))){
            var fw = new FileWriter(newFilePath);
            var fr = new FileReader(fileName);
            int c;
            while((c = fr.read()) != -1){
                fw.write(c);
            }
        }
    }

    private static boolean checkIfFileIsInHead(String fileName, String sha1) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String nameFromHead;
        while((nameFromHead = reader.readLine()) != null){
            String sha1FromHead = reader.readLine();
            if(fileName.equals(nameFromHead) && sha1.equals(sha1FromHead)) return true;
        }

        return false;
    }

    private static void addFileToHead(String fileName, String sha1) throws IOException{
        Path file = Paths.get(Gvt.headPath);
        Files.write(file, Arrays.asList(sha1), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }
}
