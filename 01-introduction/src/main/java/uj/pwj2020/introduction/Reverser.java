package uj.pwj2020.introduction;

public class Reverser {

    public String reverse(String input) {
        if(input != null){
            char[] result = input.strip().toCharArray();

            for(int i = 0; i < result.length/2; i++){
                char t = result[i];
                int index = result.length-i-1;
                result[i] = result[index];
                result[index] = t;
            }

            return String.valueOf(result);
        }

        return null;
    }

    public String reverseWords(String input) {
        if(input != null){
            String temp = input.strip();
            String result = "";
            for(int i = 0; i < temp.length(); i++){
                String word;
                int t = temp.indexOf(" ", i);
                if(t == -1) t = temp.length();
                word = temp.substring(i,t);
                result = " " + word + result;
                i = t;
            }

            return result.substring(1);
        }
        return null;

    }

}
