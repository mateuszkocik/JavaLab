package uj.java.pwj2020.map2d;

public class Key<R,C>{

    private R row;
    private C column;

    public R getRow(){
        return row;
    }

    public void setRow(R row){
        this.row = row;
    }

    public C getColumn(){
        return column;
    }

    public void setColumn(C column){
        this.column = column;
    }
}
