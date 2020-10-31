package uj.java.pwj2020.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BattleshipGeneratorImpl implements BattleshipGenerator{

    static final int mapSize = 10;
    int singleMast = 4;
    int doubleMast = 3;
    int tripleMast = 2;
    int quadrupleMast = 1;
    List<Point> map = Point.makeListWithWaterPoints(mapSize);
    List<Point> leftFields = List.copyOf(map);


    @Override
    public String generateMap() {
        while(thereAreLeftShips()){
            if(quadrupleMast > 0){
                insertQuadrupleMast();
            }else if(tripleMast > 0){

            }else if(doubleMast > 0){

            }else{

            }

        }


        return "a";

    }

    private List<Points>


    private boolean isShip(Point point){
        if(point.x >= 0 && point.x < mapSize && point.y >= 0 && point.y < mapSize){
            return point.sign == '#' ? true : false;
        }
        return false;
    }

    private void makeShip(Point p){p.sign = '#';}

    private void makeWater(Point p){p.sign='.';}

    private Point getRandomPointFromLeftFields(){
        int index = (int)(Math.random()*leftFields.size());
        Point point = leftFields.get(index);

        return point;
    }

    private Point getLeftPoint(Point p){
        return getPoint(p.x-1,p.y);
    }

    private Point getRightPoint(Point p){
        return getPoint(p.x+1,p.y);
    }

    private Point getTopPoint(Point x){
        return getPoint(x.x,x.y+1);
    }

    private Point getBottomPoint(Point x){
        return getPoint(x.x,x.y-1);
    }


    private Point getPoint(int x, int y){
        if(x >= 0 && x < mapSize && y >= 0 && y < mapSize){
            return map.get(x*mapSize+y);
        }
        return null;
    }


    private void displayMap(){
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                System.out.print(getPoint(i,j).sign);
            }
            System.out.println();
        }
    }

}
