package uj.java.pwj2020;

import java.io.IOException;

public class Detach{

    public static void detach(String fileName) throws IOException{
        if(Head.checkIfFileNameIsInHead(fileName)){
            Head.deleteFileFromHead(fileName);

            System.out.println("File " + fileName + " detached successfully.");
        }else{
            System.out.println("File " + fileName + " is not added to gvt.");
        }

    }
}
