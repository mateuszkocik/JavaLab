package uj.java.pwj2020.kindergarten;

import static uj.java.pwj2020.kindergarten.Fork.State.*;

public class HungryChild extends Child implements Runnable{

    public Fork leftFork;
    public Fork rightFork;
    public int remainingTime;
    public HungryChild leftChild;
    public HungryChild rightChild;

    public HungryChild(String name, int hungerSpeedMs){
        super(name, hungerSpeedMs);
        leftFork = null;
        rightFork = null;
        remainingTime = Integer.MAX_VALUE;
    }





    @Override
    public void run(){
        while(true){
            remainingTime = getRemainingTime();
            if(happiness() < 70 && forksAreClean() && leftChild.remainingTime > remainingTime && rightChild.remainingTime > remainingTime){
                eatAndCleanForks();
            }
        }
    }

    public void eatAndCleanForks(){
        setStateInForks(DIRTY);
        eat();
        setStateInForks(CLEAN);
    }

    public void setStateInForks(Fork.State state){
        leftFork.rwl.writeLock().lock();
        rightFork.rwl.writeLock().lock();
        leftFork.setState(state);
        rightFork.setState(state);
        leftFork.rwl.writeLock().unlock();
        rightFork.rwl.writeLock().unlock();
    }

    public boolean forksAreClean(){
        return leftFork.getState() == CLEAN && rightFork.getState() == CLEAN;
    }

    public int getRemainingTime(){
        return happiness()*hungerSpeed();
    }

}
