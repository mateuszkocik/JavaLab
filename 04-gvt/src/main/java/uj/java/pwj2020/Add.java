package uj.java.pwj2020;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class Add{

    public static void add(String fileName) throws IOException{
        if(Files.exists(Paths.get(fileName))){
            String sha1 = Sha1File.generateSha1FromFile(fileName);
            if(checkIfFileIsInHead(fileName,sha1)){
                System.out.println("File " + fileName + " already added.");
                System.exit(1);
            }

            if(checkIfFileNameIsInHead(fileName)) replaceSha1InHead(fileName, sha1);
            else addFileToHead(fileName, sha1);

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
            BufferedReader fr = new BufferedReader(new FileReader(fileName));
            BufferedWriter fw = new BufferedWriter(new FileWriter(newFilePath));
            String line;
            while((line = fr.readLine()) != null){
                fw.write(line);
            }
        }
    }

    private static void replaceSha1InHead(String fileName, String sha1) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuffer inputBuffer = new StringBuffer();
        String line;
        String oldSha1 = "";
        while ((line = reader.readLine()) != null) {
            if(line.equals(fileName)){
                inputBuffer.append(line);
                inputBuffer.append('\n');
                line = reader.readLine();
                oldSha1 = line;
            }
            inputBuffer.append(line);
            inputBuffer.append('\n');
        }
        reader.close();

        String inputStr = inputBuffer.toString();
        inputStr.replaceAll(oldSha1, sha1);
        FileOutputStream fileOut = new FileOutputStream(fileName);
        fileOut.write(inputStr.getBytes());
        fileOut.close();
    }

    private static boolean checkIfFileNameIsInHead(String fileName) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(Gvt.headPath));
        String nameFromHead;
        while((nameFromHead = reader.readLine()) != null){
            if(fileName.equals(nameFromHead)) return true;
            reader.readLine();
        }
        return false;
    }

    private static boolean checkIfFileIsInHead(String fileName, String sha1) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(Gvt.headPath));
        String nameFromHead;
        while((nameFromHead = reader.readLine()) != null){
            String sha1FromHead = reader.readLine();
            if(fileName.equals(nameFromHead) && sha1.equals(sha1FromHead)) return true;
        }

        return false;
    }

    private static void addFileToHead(String fileName, String sha1) throws IOException{
        Path file = Paths.get(Gvt.headPath);
        Files.write(file, Arrays.asList(fileName, sha1), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }
}
