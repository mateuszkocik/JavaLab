package uj.java.pwj2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Init{
    public static void init() throws IOException{
        Path gvtDir = Paths.get(".gvt");
        if(Files.notExists(gvtDir)){
            Files.createDirectory(gvtDir);
            makeFileInGVT("head");
            makeFileInGVT("version");
            makeDirectoryInGVT("versions");
            makeDirectoryInGVT("objects");

            Version.addVersion(Arrays.asList("GVT initialized."));
            System.out.println("Current directory initialized successfully.");
        }else{
            System.out.println("Current directory is already initialized.");
            System.exit(10);
        }
    }

    private static void makeFileInGVT(String file) throws  IOException{
        Path filePath = Paths.get(".gvt/" + file);
        Files.createFile(filePath);
    }

    private static void makeDirectoryInGVT(String directory) throws IOException{
        Path directoryPath = Paths.get(".gvt/" + directory);
        Files.createDirectory(directoryPath);
    }
}