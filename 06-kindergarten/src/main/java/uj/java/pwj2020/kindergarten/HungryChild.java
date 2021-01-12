package uj.java.pwj2020.kindergarten;


public class HungryChild extends Child implements Runnable{

    private final int id;
    private boolean isEating;
    private final Teacher teacher;

    public HungryChild(String name, int hungerSpeedMs, int id, Teacher teacher){
        super(name, hungerSpeedMs);
        this.id = id;
        this.isEating = false;
        this.teacher = teacher;
    }

    @Override
    public void run(){
        while(true){
            if(happiness() < 75){
                boolean allowedToEat = false;
                synchronized(teacher){ //Only one child can ask teacher if he is allowed eat
                    if(happiness() < 40)
                        teacher.imStarving(id);
                    allowedToEat = teacher.canIEat(id);
                    if(allowedToEat)isEating = true;
                }
                if(allowedToEat){
                    eat();
                    isEating = false;
                }
            }
        }
    }


    public boolean isEating(){
        return isEating;
    }

    public int getId(){
        return id;
    }
}
