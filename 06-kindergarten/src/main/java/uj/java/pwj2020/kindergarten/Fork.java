package uj.java.pwj2020.kindergarten;

public class Fork{

    private State state = State.CLEAN;

    public void makeDirty(){
        this.state = State.DIRTY;
    }

    public void makeClean(){
        this.state = State.CLEAN;
    }

    public State getState(){
        return this.state;
    }

    enum State{
        DIRTY, CLEAN
    }
}
