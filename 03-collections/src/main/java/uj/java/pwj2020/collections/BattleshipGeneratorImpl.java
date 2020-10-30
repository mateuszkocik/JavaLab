package uj.java.pwj2020.collections;

import java.util.ArrayList;
import java.util.List;

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

    private void insertQuadrupleMast() {

    }

    private boolean thereAreLeftShips(){
        return singleMast == 0 && doubleMast == 0 && tripleMast == 0 && quadrupleMast == 0 ? false : true;
    }

    public static void main(String[] args){
        BattleshipGeneratorImpl b= new BattleshipGeneratorImpl();
        b.makeShip(b.getPoint(9,9));
        b.makeShip(b.getPoint(9,8));
        b.makeShip(b.getPoint(9,7));



        /*b.map[5][5] = '#';
        b.map[5][4] = '#';
        b.map[4][4] = '#';*/
        /*b.map[5][5] = '#';
        b.map[4][5] = '#';
        b.map[3][5] = '#';
        b.map[9][9] = '#';
        b.map[9][8] = '#';*/
        b.displayMap();
        System.out.println(b.getShipSizeIfChanged(b.getPoint(9,6)));

    }


    private int getShipSizeIfChanged(Point point){
        makeShip(point);
        int shipSize = getShipSize(point,null);
        makeWater(point);

        return shipSize;
    }

    private int getShipSize(Point point, Point previousPoint){
        if(point == null) return 0;
        int shipSize = 0;
        if(isShip(point)){
            shipSize++;
            if(getLeftPoint(point) != null && !getLeftPoint(point).equals(previousPoint)) shipSize += getShipSize(getLeftPoint(point),point);
            if(getRightPoint(point) != null && !getRightPoint(point).equals(previousPoint)) shipSize += getShipSize(getRightPoint(point),point);
            if(getTopPoint(point) != null && !getTopPoint(point).equals(previousPoint)) shipSize += getShipSize(getTopPoint(point),point);
            if(getBottomPoint(point) != null && !getBottomPoint(point).equals(previousPoint)) shipSize += getShipSize(getBottomPoint(point),point);
        }

        return shipSize;
    }



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
