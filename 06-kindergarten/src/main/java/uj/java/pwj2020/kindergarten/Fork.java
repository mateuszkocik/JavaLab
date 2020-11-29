package uj.java.pwj2020.kindergarten;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Fork{

    private State state = State.CLEAN;
    public ReadWriteLock rwl = new ReentrantReadWriteLock(true);
    public String reservedFor = null;

    public void setState(State state){
        this.state = state;
    }

    public State getState(){
        rwl.readLock().lock();
        var result = state;
        rwl.readLock().unlock();
        return result;
    }

    enum State{
        DIRTY, CLEAN, RESERVED
    }
}