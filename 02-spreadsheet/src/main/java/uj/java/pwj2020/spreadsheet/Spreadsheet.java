package uj.java.pwj2020.spreadsheet;

public class Spreadsheet {

    private String[][] input;
    private String[][] result;

    public String[][] calculate(String[][] input) {
        this.input = input;
        initResultArray();
        for(int i = 0; i < input.length; i++){
            for(int j = 0; j < input[i].length; j++){
                result[i][j] = getValue(i,j);
            }
        }
        return result;
    }

    public void initResultArray(){
        result = new String[input.length][];
        for(int i = 0; i < input.length; i++){
            result[i] = new String[input[i].length];
        }
    }

    private String getValue(int i, int j){
        String value = result[i][j];
        String inputStr = input[i][j];

        if(value == null){
            if(isReference(inputStr)){
                value = getValueFromReference(inputStr);
            }else if(isFormula(inputStr)){
                value = getValueFromFormula(inputStr);
            }else{
                value = inputStr;
            }
        }
        return value;
    }

    private String getValueFromFormula(String inputStr) {
        String formula = getFormula(inputStr);
        String v1 = getFirstValueFromFormula(inputStr);
        String v2 = getSecondValueFromFormula(inputStr);

        return calculateFormula(formula, v1, v2);
    }

    private String calculateFormula(String formula, String s1, String s2) {
        Integer i1 = Integer.parseInt(s1);
        Integer i2 = Integer.parseInt(s2);
        switch (Formula.valueOf(formula)){
            case ADD:
                return sum(i1, i2);
            case SUB:
                return sub(i1, i2);
            case MUL:
                return mul(i1, i2);
            case DIV:
                return div(i1, i2);
            case MOD:
                return mod(i1, i2);
            default:
                return null;
        }
    }

    private String getSecondValueFromFormula(String inputStr) {
        String v2 = getSecondArgFromFormula(inputStr);
        if(isReference(v2)){
            v2 = getValueFromReference(v2);
        }
        return v2;
    }

    private String getFirstValueFromFormula(String inputStr) {
        String v1 = getFirstArgFromFormula(inputStr);
        if(isReference(v1)){
            v1 = getValueFromReference(v1);
        }
        return v1;
    }

    private String getValueFromReference(String value) {
        int firstIndex = getFirstIndexFromRef(value);
        int secondIndex = getSecondIndexFromRef(value);
        return getValue(firstIndex,secondIndex);
    }

    private String sum(Integer i1, Integer i2){
        return String.valueOf(i1+i2);
    }

    private String sub(Integer i1, Integer i2){
        return String.valueOf(i1-i2);
    }

    private String mul(Integer i1, Integer i2){
        return String.valueOf(i1*i2);
    }

    private String div(Integer i1, Integer i2){
        return String.valueOf(i1/i2);
    }

    private String mod(Integer i1, Integer i2){
        return String.valueOf(i1%i2);
    }

    private String getFormula(String input){
        int from = input.indexOf('=') + 1;
        int to = input.indexOf('(');

        return input.substring(from,to);
    }

    private String getFirstArgFromFormula(String formula){
        int from = formula.indexOf('(') + 1;
        int to = formula.indexOf(',');

        return formula.substring(from,to).trim();
    }

    private String getSecondArgFromFormula(String formula){
        int from = formula.indexOf(',') + 1;
        int to = formula.indexOf(')');

        return formula.substring(from,to).trim();
    }

    private int getFirstIndexFromRef(String input){
        return Integer.parseInt(input.substring(2)) - 1;
    }

    private int getSecondIndexFromRef(String input) {
        char letter = input.charAt(1);
        return letter - 'A';
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

    public enum Formula{
        ADD("ADD"),
        SUB("SUB"),
        MUL("MUL"),
        DIV("DIV"),
        MOD("MOD");

        private String name;

        private Formula(String name){
            this.name = name;
        }

    }
}
