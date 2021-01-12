package uj.java.pwj2020.kindergarten;

import java.util.ArrayList;
import java.util.List;

public class Teacher{

    private List<HungryChild> starvingChildren;
    private HungryChild[] children;
    private final int AMOUNT;

    public Teacher(int amount){
        starvingChildren = new ArrayList<>(amount);
        this.AMOUNT = amount;
    }

    public boolean canIEat(int id){
        HungryChild child = children[id];
        if(platesAreFree(child) && neighborsAreLessHungry(child)){
            if(starvingChildren.contains(child))
                starvingChildren.remove(child);
            return true;
        }
        return false;
    }

    private boolean neighborsAreLessHungry(HungryChild child){
        HungryChild leftChild = getLeftChild(child);
        HungryChild rightChild = getRightChild(child);
        if(!starvingChildren.contains(leftChild) && !starvingChildren.contains(rightChild)) //Neighbours not starving
            return true;
        int leftIndex = starvingChildren.indexOf(leftChild);
        int rightIndex = starvingChildren.indexOf(rightChild);

        return child.getId() < leftIndex && child.getId() < rightIndex; //Any neighbour was starving before
    }

    private HungryChild getLeftChild(HungryChild child){
        return children[(child.getId() - 1 + AMOUNT) % AMOUNT];
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
