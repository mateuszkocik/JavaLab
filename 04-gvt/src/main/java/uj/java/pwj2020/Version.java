package uj.java.pwj2020;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Version{

    public static void addVersion(List<String> message) throws IOException{
        int newVersion = increaseVersion();
        Path file = Paths.get(Gvt.versionsPath,String.valueOf(newVersion));
        Files.createFile(file);
        Files.write(file,message);
    }


    public static List<String> showVersionMessage(int n) throws IOException{
        var versionMessage = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader(Gvt.versionsPath + String.valueOf(n)));
        String line;
        while((line = reader.readLine()) != null){
            versionMessage.add(line);
        }

        return versionMessage;
    }

    public static int getLatestVersion() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(Gvt.versionPath));
        String line;
        if((line=reader.readLine()) == null) return -1;

        return Integer.valueOf(line);
    }

    public static int increaseVersion() throws IOException{
        int newVersion = getLatestVersion() + 1;
        setVersion(newVersion);

        return newVersion;
    }

    public static void setVersion(int version) throws IOException{
        Path file = Paths.get(Gvt.versionPath);
        Files.write(file,Arrays.asList(String.valueOf(version)));
    }




}
