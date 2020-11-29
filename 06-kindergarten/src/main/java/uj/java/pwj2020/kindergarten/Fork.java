package uj.java.pwj2020.kindergarten;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static uj.java.pwj2020.kindergarten.Fork.State.CLEAN;

public class Fork{

    private State state = CLEAN;
    public ReadWriteLock rwl = new ReentrantReadWriteLock(true);
    public boolean reserved = false;
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

    public void setReservation(HungryChild child){
        reserved = true;
        reservedFor = child.name();
    }

    public void cancelReservation(){
        reserved = false;
        reservedFor = null;
    }

    public boolean isClear(){
        return state == CLEAN;
    }

    public boolean isReserved(){
        return reserved == true;
    }


    enum State{
        DIRTY, CLEAN
    }
}