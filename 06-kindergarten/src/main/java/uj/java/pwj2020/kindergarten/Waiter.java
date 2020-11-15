package uj.java.pwj2020.kindergarten;

import java.util.ArrayDeque;
import java.util.Queue;

public class Waiter{

    private Queue<HungryChild> queue = new ArrayDeque<>();


    public void canIStartEating(HungryChild child){
        if(child.leftFork.getState() == Fork.State.CLEAN && child.rightFork.getState() == Fork.State.CLEAN){
            child.makeForksDirty();
            child.eat();
            child.cleanForks();
            System.out.println(queue.size());
        }else{
            synchronized(queue){
                if(!queue.contains(child)){
                    queue.add(child);
                }
            }
        }
    }


}
