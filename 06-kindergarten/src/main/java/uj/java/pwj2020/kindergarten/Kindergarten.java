package uj.java.pwj2020.kindergarten;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
public class Kindergarten{

    public static void main(String[] args) throws IOException{
        init();
        final var fileName = args[0];
        System.out.println("File name: " + fileName);

        var children = getChildrenFromFile(fileName);
        startDinner(children);
    }

    private static void startDinner(HungryChild[] children){
        for(HungryChild child : children){
            var thread = new Thread(child);
            thread.start();
        }
    }

    public static HungryChild[] getChildrenFromFile(String fileName){
        HungryChild[] childrenArray = null;
        try{
            Scanner s = new Scanner(new File(fileName));
            int childrenAmount = s.nextInt();
            childrenArray = new HungryChild[childrenAmount];
            Teacher teacher = new Teacher(childrenAmount);
            for(int i = 0; i < childrenAmount; i++){
                childrenArray[i] = new HungryChild(s.next(), s.nextInt(), i, teacher);
            }
            teacher.setChildren(childrenArray);
        }catch(IOException e){
            e.printStackTrace();
        }

        return childrenArray;
    }

    private static void init() throws IOException{
        Files.deleteIfExists(Path.of("out.txt"));
        System.setErr(new PrintStream(new FileOutputStream("out.txt")));
        new Thread(Kindergarten::runKindergarden).start();
    }

    private static void runKindergarden(){
        try{
            Thread.sleep(10100);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        try{
            List<String> errLines = Files.readAllLines(Path.of("out.txt"));
            System.out.println("Children cries count: " + errLines.size());
            errLines.forEach(System.out::println);
            System.exit(errLines.size());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
