package uj.java.pwj2020.kindergarten;

import java.util.PriorityQueue;

public class Teacher{

    private PriorityQueue<HungryChild> starvingChildren;
    private HungryChild[] children;
    private final int AMOUNT;

    public Teacher(int amount){
        starvingChildren = new PriorityQueue<>(amount);
        this.AMOUNT = amount;
    }

    public boolean canIEat(int id){
        HungryChild child = children[id];
        if(platesAreFree(child) && neighborsAreLessHungry(child)) {
            if(starvingChildren.contains(child)) starvingChildren.remove(child);
            return true;
        }
        return false;
    }

    private boolean neighborsAreLessHungry(HungryChild child){
        HungryChild leftChild = getLeftChild(child);
        HungryChild rightChild = getRightChild(child);
        if(!starvingChildren.contains(leftChild) && !starvingChildren.contains(rightChild)) //Neighbours not starving
            return true;
        int leftIndex = getStarvingIndex(leftChild);
        int rightIndex = getStarvingIndex(rightChild);

        return child.getId() < leftIndex && child.getId() < rightIndex; //Any neighbour was starving before
    }

    private int getStarvingIndex(HungryChild child){
        if(starvingChildren.contains(child)){
            int index = 0;
            var iterator = starvingChildren.iterator();
            while(iterator.next() != child) index++;
            return index;
        }
        return Integer.MAX_VALUE;
    }

    private HungryChild getLeftChild(HungryChild child){
        return children[(child.getId() - 1) % AMOUNT];
    }

    private HungryChild getRightChild(HungryChild child){
        return children[(child.getId() + 1) % AMOUNT];
    }

    private boolean platesAreFree(HungryChild child){
        return !getLeftChild(child).isEating() && !getRightChild(child).isEating();
    }

    public void imStarving(int id){
        HungryChild starvingChild = children[id];
        if(!starvingChildren.contains(starvingChild))
            starvingChildren.add(starvingChild);
    }

    public void setChildren(HungryChild[] children){
        this.children = children;
    }
}
