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
        String[][] result = new String[input.length][];
        for(int i = 0; i < input.length; i++){
            result[i] = new String[input[i].length];
        }
        process(input,result);

        /*for(int i = 0; i < input.length; i++){
            for(int j = 0; j < input[i].length; j++){
                System.out.print(result[i][j]+",");
            }
            System.out.println();
        }*/

        return result;
    }

    public void process(String[][] input, String[][] result){
        for(int i = 0; i < input.length; i++){
            for(int j = 0; j < input[i].length; j++){
                result[i][j] = getValue(input, result,i,j);
            }
        }
    }

    private String getValue(String[][] input, String[][] result, int i, int j){
        if(result[i][j] == null){
            System.out.println(input[i][j]);

            if(isReference(input[i][j])){
                result[i][j] = getValueFromReference(input, result, i, j);
                return result[i][j];
            }else if(isFormula(input[i][j])){
                String formula = getFormula(input[i][j]);
                String v1 = getFirstValueFromFormula(input[i][j]);
                String v2 = getSecondValueFromFormula(input[i][j]);
                if(isReference(v1)){
                    v1 = getValueFromReference(input,result,v1);
                }
                if(isReference(v2)){
                    v2 = getValueFromReference(input,result,v2);
                }
                switch (formula){
                    case "ADD":
                        result[i][j] = sum(v1,v2);
                        return result[i][j];
                    case "SUB":
                        result[i][j] = sub(v1,v2);
                        return result[i][j];
                    case "MUL":
                        result[i][j] = mul(v1,v2);
                        return result[i][j];
                    case "DIV":
                        result[i][j] = div(v1,v2);
                        return result[i][j];
                    case "MOD":
                        result[i][j] = mod(v1,v2);
                        return result[i][j];
                    default: return result[i][j];
                }
            }else{
                result[i][j] = new String(input[i][j]);
                return result[i][j];
            }
        }else{
            return result[i][j];
        }
    }

    private String getValueFromReference(String[][] input, String[][] result, String value) {
        int firstIndex = getFirstIndexFromRef(value);
        int secondIndex = getSecondIndexFromRef(value);

        return getValue(input, result,firstIndex,secondIndex);
    }


    private String getValueFromReference(String[][] input, String[][] result, int i, int j) {
        int firstIndex = getFirstIndexFromRef(input[i][j]);
        int secondIndex = getSecondIndexFromRef(input[i][j]);
        return getValue(input, result,firstIndex,secondIndex);
    }


    private String sum(String v1, String v2){
        Integer i1 = Integer.parseInt(v1);
        Integer i2 = Integer.parseInt(v2);

        return String.valueOf(i1+i2);
    }

    private String sub(String v1, String v2){
        Integer i1 = Integer.parseInt(v1);
        Integer i2 = Integer.parseInt(v2);

        return String.valueOf(i1-i2);
    }

    private String mul(String v1, String v2){
        Integer i1 = Integer.parseInt(v1);
        Integer i2 = Integer.parseInt(v2);

        return String.valueOf(i1*i2);
    }

    private String div(String v1, String v2){
        Integer i1 = Integer.parseInt(v1);
        Integer i2 = Integer.parseInt(v2);

        return String.valueOf(i1/i2);
    }

    private String mod(String v1, String v2){
        Integer i1 = Integer.parseInt(v1);
        Integer i2 = Integer.parseInt(v2);

        return String.valueOf(i1%i2);
    }

    private String getFormula(String input){
        int from = input.indexOf('=') + 1;
        int to = input.indexOf('(');

        return input.substring(from,to);
    }

    private String getFirstValueFromFormula(String formula){
        int from = formula.indexOf('(') + 1;
        int to = formula.indexOf(',');
        System.out.println(formula + " from " + from + " to " + to);


        return formula.substring(from,to).trim();
    }

    private String getSecondValueFromFormula(String formula){
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

}
