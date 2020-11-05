package uj.java.pwj2020;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Checkout{
    public static void checkout(int version) throws IOException{
        String versionPath = Gvt.versionsPath + String.valueOf(version + "_h");
        var committedFilesList = getCommittedFiles(versionPath);
        replaceCommittedFiles(committedFilesList);
    }


    private static List getCommittedFiles(String headPath) throws IOException{
        var list = new ArrayList<String>();
        BufferedReader headReader = new BufferedReader(new FileReader(headPath));
        String line;
        while((line = headReader.readLine()) != null){
            list.add(line);
        }
        return list;
    }

    private static void replaceCommittedFiles(List<String> committedFiles) throws IOException{
        for(int i = 0; i < committedFiles.size(); i += 2){
            FileOutputStream fw = new FileOutputStream(committedFiles.get(i));
            FileInputStream fr = new FileInputStream(Gvt.objectsPath + committedFiles.get(i + 1));
            int c;
            while((c = fr.read()) != -1){
                fw.write(c);
            }
        }
    }
}
