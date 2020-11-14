package uj.java.pwj2020.kindergarten;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Kindergarten {

    public static void main(String[] args) throws IOException {
        init();

        final var fileName = "3kids.in";
        System.out.println("File name: " + fileName);
        var childrenList = getChildrenFromFile(fileName);
    }

    public static List<Child> getChildrenFromFile(String fileName){
        var list = new ArrayList<Child>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            int childrenAmount = Integer.parseInt(br.readLine());//unnecessary however it is in file
            String line;
            while((line = br.readLine()) != null){
                addChildToList(list, line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return list;
    }

    private static void addChildToList(ArrayList<Child> list, String line){
        String[] childDetails = line.split(" ");
        String name = childDetails[0];
        int hungerSpeedMs = Integer.parseInt(childDetails[1]);
        list.add(new HungryChild(name,hungerSpeedMs));
    }


    private static void init() throws IOException {
        Files.deleteIfExists(Path.of("out.txt"));
        System.setErr(new PrintStream(new FileOutputStream("out.txt")));
        new Thread(Kindergarten::runKindergarden).start();
    }

    private static void runKindergarden() {
        try {
            Thread.sleep(10100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            List<String> errLines = Files.readAllLines(Path.of("out.txt"));
            System.out.println("Children cries count: " + errLines.size());
            errLines.forEach(System.out::println);
            System.exit(errLines.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
