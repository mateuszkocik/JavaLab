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




    public static void main(String[] args){
        Spreadsheet s = new Spreadsheet();
        String[][] input = {
                {"1","2","3"},
                {"4","5","6"},
                {"$A1","$C1","$B3"},
                {"=ADD(10,$A1)","=SUB($C3,$A1)","0"}};

        System.out.println(s.calculate(input));


    }

    public String[][] calculate(String[][] input) {
        String[][] result = new String[4][];
        result[0] = new String[3];
        result[1] = new String[3];
        result[2] = new String[3];
        result[3] = new String[3];

        process(input,result);

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
                int firstIndex = getFirstIndexFromRef(input[i][j]);
                int secondIndex = getSecondIndexFromRef(input[i][j]);
                result[i][j] = new String(getValue(input,result,firstIndex,secondIndex));
                return result[i][j];
            }else if(isFormula(input[i][j])){
                String formula = getFormula(input[i][j]);
                String v1 = getFirstValueFromFormula(input[i][j]);
                String v2 = getSecondValueFromFormula(input[i][j]);


                switch (formula){
                    case "ADD":
                        result[i][j] = new String(sum(v1,v2));
                        return result[i][j];
                    case "SUB":
                        result[i][j] = new String(sub(v1,v2));
                        return result[i][j];
                    case "MUL":
                        result[i][j] = new String(mul(v1,v2));
                        return result[i][j];
                    case "DIV":
                        result[i][j] = new String(div(v1,v2));
                        return result[i][j];
                    case "MOD":
                        result[i][j] = new String(mod(v1,v2));
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
        char letter = input.charAt(1);
        return letter - 'A';
    }

    private int getSecondIndexFromRef(String input) {
        return Integer.parseInt(input.substring(2)) - 1;
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
