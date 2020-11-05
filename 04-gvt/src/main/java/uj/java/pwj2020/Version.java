package uj.java.pwj2020;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Version{

    public static void addVersion(List<String> message) throws IOException{
        int newVersion = increaseVersion();
        Path file = Paths.get(Gvt.versionsPath, String.valueOf(newVersion));
        Files.createFile(file);
        Files.write(file, message);
        fillVersionHead(newVersion);
    }

    private static void fillVersionHead(int newVersion) throws IOException{
        BufferedReader fr = new BufferedReader(new FileReader(Gvt.headPath));
        FileOutputStream fw = new FileOutputStream(Gvt.versionsPath + String.valueOf(newVersion) + "_h");
        int ch;
        while((ch = fr.read()) != -1){
            fw.write(ch);
        }
        fr.close();
        fw.close();
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
        if((line = reader.readLine()) == null) return -1;

        return Integer.valueOf(line);
    }

    public static int increaseVersion() throws IOException{
        int newVersion = getLatestVersion() + 1;
        setVersion(newVersion);

        return newVersion;
    }

    public static void setVersion(int version) throws IOException{
        Path file = Paths.get(Gvt.versionPath);
        Files.write(file, Arrays.asList(String.valueOf(version)));
    }

    public static boolean versionExist(int version) throws IOException{
        int latestVersion = getLatestVersion();
        if(version >= 0 && version <= latestVersion){
            return true;
        }
        return false;
    }


}
