package uj.java.pwj2020.map2d;

public class Key<R,C>{

    private R row;
    private C column;

    public Key(R row, C column){
        this.row = row;
        this.column = column;
    }

    public R getRow(){
        return row;
    }

    public C getColumn(){
        return column;
    }
}
