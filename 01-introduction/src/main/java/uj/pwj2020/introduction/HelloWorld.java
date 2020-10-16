package uj.pwj2020.introduction;

public class HelloWorld {

    public static void main(String[] args){

        if(args.length > 0){
            for(String arg: args){
                System.out.print(arg + "\n");
            }
        }else{
            System.out.println("No input parameters provided");
        }


    }
}
