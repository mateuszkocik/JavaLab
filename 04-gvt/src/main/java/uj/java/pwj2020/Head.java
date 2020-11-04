package uj.java.pwj2020;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class Head{

    public static boolean checkIfFileNameIsInHead(String fileName) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(Gvt.headPath));
        String nameFromHead;
        while((nameFromHead = reader.readLine()) != null){
            if(fileName.equals(nameFromHead)) return true;
            reader.readLine();
        }
        return false;
    }
    /*
    public static boolean checkIfFileIsInHead(String fileName, String sha1) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(Gvt.headPath));
        String nameFromHead;
        while((nameFromHead = reader.readLine()) != null){
            String sha1FromHead = reader.readLine();
            if(fileName.equals(nameFromHead) && sha1.equals(sha1FromHead)) return true;
        }

        return false;
    }*/

    public static void addFileNameToHead(String fileName) throws IOException{
        Path file = Paths.get(Gvt.headPath);
        Files.write(file, Arrays.asList(fileName, "File is not committed"), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }

    /*public static void addFileToHead(String fileName, String sha1) throws IOException{
        Path file = Paths.get(Gvt.headPath);
        Files.write(file, Arrays.asList(fileName, sha1), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }*/

    public static void replaceSha1InHead(String fileName, String sha1) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuffer inputBuffer = new StringBuffer();
        String line;
        String oldSha1 = "";
        while((line = reader.readLine()) != null){
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

    public static void deleteFileFromHead(String fileName) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuffer inputBuffer = new StringBuffer();
        String line;
        while((line = reader.readLine()) != null){
            if(line.equals(fileName)){
                line = reader.readLine();
            }else{
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
        }
    }


}
