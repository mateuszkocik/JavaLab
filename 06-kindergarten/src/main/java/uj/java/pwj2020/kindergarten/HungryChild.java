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
            //remainingTime = getRemainingTime();
            //boolean neighboursHaveMoreTime = leftChild.remainingTime > remainingTime && rightChild.remainingTime > remainingTime;
            if(forksAreFree()){
                if(happiness() < 70){
                    eatAndCleanForks();
                }
            }else{
                if(areForksReservedForMe()){
                    eatAndCancelReservation();
                }else{
                    if(happiness() < 30){
                        if(forksAreNotReserved()){

                        }
                    }
                }
            }
        }
    }

    private void reserveForks(){
        lockWriteLockForks();
        leftFork.setReservation(this);
        rightFork.setReservation(this);
        unlockWriteLockForks();
    }

    private boolean forksAreFree(){
        return forksAreClean() && forksAreNotReserved();
    }

    private boolean forksAreNotReserved(){
        lockReadLockForks();
        boolean result = leftFork.isReserved() && rightFork.isReserved();
        unlockReadLockForks();
        return result;
    }


    private void eatAndCancelReservation(){
        setStateInForks(DIRTY);
        eat();
        cancelReservation();
        setStateInForks(CLEAN);
    }

    public boolean areForksReservedForMe(){
        return leftFork.reserved
                && rightFork.reserved
                && leftFork.reservedFor == name()
                && rightFork.reservedFor == name();
    }


    public void eatAndCleanForks(){
        setStateInForks(DIRTY);
        eat();
        setStateInForks(CLEAN);
    }

    public void cancelReservation(){
        lockWriteLockForks();
        leftFork.cancelReservation();
        rightFork.cancelReservation();
        unlockWriteLockForks();
    }

    public void setStateInForks(Fork.State state){
        lockWriteLockForks();
        leftFork.setState(state);
        rightFork.setState(state);
        unlockWriteLockForks();
    }

    private void lockReadLockForks(){
        leftFork.rwl.readLock().unlock();
        rightFork.rwl.readLock().unlock();
    }

    private void unlockReadLockForks(){
        leftFork.rwl.readLock().unlock();
        rightFork.rwl.readLock().unlock();
    }

    private void unlockWriteLockForks(){
        leftFork.rwl.writeLock().unlock();
        rightFork.rwl.writeLock().unlock();
    }

    private void lockWriteLockForks(){
        leftFork.rwl.writeLock().lock();
        rightFork.rwl.writeLock().lock();
    }

    public boolean forksAreClean(){
        lockReadLockForks();
        boolean result = leftFork.isClear() && rightFork.isClear();
        unlockReadLockForks();
        return result;
    }

    public int getRemainingTime(){
        return happiness() * hungerSpeed();
    }

}
