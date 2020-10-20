package uj.java.pwj2020.spreadsheet;


public class Spreadsheet {

    private enum Formula{
        ADD("ADD"),
        SUB("SUB"),
        MUL("MUL"),
        DIV("DIV"),
        MOD("MOD");

        private String name;

        private Formula(String name){
            this.name = name;
        }

        public String getName(){
            return this.name;
        }
    }

    public String[][] calculate(String[][] input) {
        return null;
    }

    public String process(String[][] input){
        for(int i = 0; i < input.length; i++){
            for(int j = 0; j < input[i].length; j++){

            }
        }
    }

    private String getValue(String[][] input, String[][] result, int i, int j){
        if(result[i][j] == null){
            if(isReference(input[i][j])){

            }else if(isFormula(input[i][j])){

            }else{
                result[i][j] = input[i][j];
            }
        }else{
            return result[i][j];
        }
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
