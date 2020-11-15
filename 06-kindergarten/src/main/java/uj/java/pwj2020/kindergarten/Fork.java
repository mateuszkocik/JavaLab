package uj.java.pwj2020.kindergarten;

public class Fork{

    private State state = State.DIRTY;


    public void makeDirty(){
        this.state = State.DIRTY;
    }

    public void makeClean(){
        this.state = State.CLEAN;
    }

    enum State{
        DIRTY, CLEAN
    }
}
