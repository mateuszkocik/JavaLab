package uj.java.pwj2020.kindergarten;

public class Fork{

    public boolean reserved = false;
    public String reservedFor = null;

    public void setReservation(HungryChild child){
        reserved = true;
        reservedFor = child.name();
    }

    public void cancelReservation(){
        reserved = false;
        reservedFor = null;
    }

    public boolean isReserved(){
        return reserved == true;
    }


}