package uj.java.pwj2020.spreadsheet;

public class Spreadsheet {

    public String[][] calculate(String[][] input) {
        return null;
    }

    public boolean isFormula(String input){
        return input.charAt(0) == '=' ? true : false;
    }

    public boolean isReference(String input){
        return input.charAt(0) == '$' ? true : false;
    }


    public boolean isNumber(String input){
        return isFormula(input) || isReference(input) ? false : true;
    }

}
