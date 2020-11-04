package uj.java.pwj2020;

import java.io.IOException;

public class History{

    public static void showAllVersions()throws IOException{
        showLastN(Integer.MAX_VALUE);
    }

    public static void showLastN(int n) throws IOException{
        int latestVersion = Version.getLatestVersion();
        int i = (latestVersion - n) < 0 ? 0 : latestVersion - n + 1;
        for(; i <= latestVersion; i++){
            System.out.println(i + ": " + Version.showVersionMessage(i).get(0));
        }
    }
}
