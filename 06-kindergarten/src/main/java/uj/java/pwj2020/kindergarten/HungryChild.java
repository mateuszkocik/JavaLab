package uj.java.pwj2020.kindergarten;

public class HungryChild extends Child implements Runnable, Comparable<Child>{

    public Fork leftFork;
    public Fork rightFork;
    private Waiter waiter;

    public HungryChild(String name, int hungerSpeedMs){
        super(name, hungerSpeedMs);
    }

    @Override
    public int compareTo(Child o){
        return this.hungerSpeed() > o.hungerSpeed() ? 1 : -1;
    }

    synchronized public void cleanForks(){
        leftFork.makeClean();
        rightFork.makeClean();
    }

    synchronized void makeForksDirty(){
        leftFork.makeDirty();
        rightFork.makeDirty();
    }

    @Override
    public void run(){
        System.out.println("Child : " + name() + " run");
        while(true){
            waiter.canIStartEating(this);
        }

    }

    public void setWaiter(Waiter waiter){
        this.waiter = waiter;
    }

    public void setLeftFork(Fork leftFork){
        this.leftFork = leftFork;
    }

    public void setRightFork(Fork rightFork){
        this.rightFork = rightFork;
    }

    public Fork getRightFork(){
        return rightFork;
    }
}
