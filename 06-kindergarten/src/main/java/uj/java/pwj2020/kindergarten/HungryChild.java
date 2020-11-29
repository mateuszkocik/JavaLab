package uj.java.pwj2020.kindergarten;


public class HungryChild extends Child implements Runnable{

    public Fork leftFork;
    public Fork rightFork;

    public HungryChild(String name, int hungerSpeedMs){
        super(name, hungerSpeedMs);
        leftFork = null;
        rightFork = null;
    }

    @Override
    public void run(){
        while(true){
            if(happiness() < 75){
                synchronized(leftFork){
                    synchronized(rightFork){
                        if(forksAreNotReserved()){
                            eat();
                        }else if(areForksReservedForMe()){
                            eat();
                            cancelReservation();
                        }

                        if(happiness() < 40){
                            reserveForks();
                        }
                    }
                }
            }
        }
    }

    private void reserveForks(){
        leftFork.setReservation(this);
        rightFork.setReservation(this);

    }
    private boolean forksAreNotReserved(){
        return !leftFork.isReserved() && !rightFork.isReserved();
    }


    public boolean areForksReservedForMe(){
        return  leftFork.isReserved()
                && rightFork.isReserved()
                && leftFork.reservedFor == name()
                && rightFork.reservedFor == name();
    }

    public void cancelReservation(){
        leftFork.cancelReservation();
        rightFork.cancelReservation();
    }

}
