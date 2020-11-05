package uj.java.pwj2020;

import java.io.*;
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

    /*public static void addFileNameToHead(String fileName) throws IOException{
        Path file = Paths.get(Gvt.headPath);
        Files.write(file, Arrays.asList(fileName, fileName + " is not committed"), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }*/

    public static void addFileToHead(String fileName) throws IOException{
        String sha1 = Sha1File.generateSha1FromFile(fileName);
        Path file = Paths.get(Gvt.headPath);
        Files.write(file, Arrays.asList(fileName, sha1), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }

    public static void replaceSha1InHead(String fileName, String sha1) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(Gvt.headPath));
        StringBuffer inputBuffer = new StringBuffer();
        String line;
        while((line = reader.readLine()) != null){
            if(line.contains(fileName)){
                inputBuffer.append(line);
                inputBuffer.append('\n');
                inputBuffer.append(sha1);
                inputBuffer.append('\n');
                reader.readLine();
            }else{
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
        }
        reader.close();
        String inputStr = inputBuffer.toString();
        FileOutputStream fileOut = new FileOutputStream(Gvt.headPath);
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
