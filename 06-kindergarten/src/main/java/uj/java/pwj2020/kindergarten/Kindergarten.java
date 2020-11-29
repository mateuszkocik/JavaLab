package uj.java.pwj2020.kindergarten;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
public class Kindergarten{

    public static void main(String[] args) throws IOException{
        init();
        final var fileName = "10kids.in";
        System.out.println("File name: " + fileName);

        var children = getChildrenFromFile(fileName);
        setForks(children);
        startDinner(children);
    }

    private static void startDinner(HungryChild[] children){
        for(HungryChild child : children){
            var thread = new Thread(child);
            thread.start();
        }
    }

    private static void setForks(HungryChild[] children){
        int childrenAmount = children.length;
        for(int i = 0; i < childrenAmount; i++){
            children[i].rightFork = new Fork();
            children[i].leftFork = i != 0 ? children[i - 1].rightFork : null;
            children[i].rightChild = children[(i + 1) % childrenAmount];
            children[i].leftChild = children[(i - 1 + childrenAmount) % childrenAmount];

        }
        children[0].leftFork = children[childrenAmount - 1].rightFork;
    }

    public static HungryChild[] getChildrenFromFile(String fileName){
        HungryChild[] childrenArray = null;
        try{
            Scanner s = new Scanner(new File(fileName));
            int childrenAmount = s.nextInt();
            childrenArray = new HungryChild[childrenAmount];
            for(int i = 0; i < childrenAmount; i++){
                childrenArray[i] = new HungryChild(s.next(), s.nextInt());
            }
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
