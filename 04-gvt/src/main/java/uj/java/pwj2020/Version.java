package uj.java.pwj2020;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class Version{

    public static void addVersion(int number, String message) throws IOException{
        Path file = Paths.get(".gvt/versions");
        Files.write(file, Arrays.asList(String.valueOf(number), message), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }
}
