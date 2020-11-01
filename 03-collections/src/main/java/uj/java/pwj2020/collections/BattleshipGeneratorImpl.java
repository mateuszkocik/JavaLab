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
    List<Point> leftFields;
    static final char shipChar = '#';
    static final char waterChar = '.';

    public static void main(String[] args){
        BattleshipGeneratorImpl b = new BattleshipGeneratorImpl();
        b.initLeftFields();

        b.generateMap();
        b.displayMap();
    }


    private void initLeftFields(){
        leftFields = new ArrayList<Point>();
        for(Point p : map){
            leftFields.add(p);
        }
    }

    @Override
    public String generateMap(){
        while(thereAreLeftShips()){
            ArrayList<Point> list;
            if(quadrupleMast > 0){
                list = generateRandomXPoints(4);
                for(Point p : list){

                }
                if(list.size() == 4){
                    changeFromListToShips(list);
                    deleteFromLeftFields(list);
                }
            quadrupleMast--;
            }else if(tripleMast > 0){
                tripleMast--;
            }else if(doubleMast > 0){
                doubleMast--;
            }else{
                singleMast--;
            }
            break;

        }

        return "a";

    }

    private boolean thereAreLeftShips() {
        return !(singleMast == 0 && doubleMast == 0 && tripleMast == 0 && quadrupleMast == 0);
    }


    private ArrayList<Point> generateRandomXPoints(int x){
        var availablePoints = new ArrayList<Point>();
        var randomPoints = new ArrayList<Point>();


        Point firstPoint = getRandomPointFromLeftFields();
        randomPoints.add(firstPoint);
        addNeighboursToAvailable(availablePoints,randomPoints,firstPoint);
        System.out.println("First point " + firstPoint);
        for(int i = 1; i < x; i++){
            if(availablePoints.size() > 0){
                Point p = getRandomPointFromList(availablePoints);
                System.out.println(i + "   " + p);
                randomPoints.add(p);
                availablePoints.remove(p);
                addNeighboursToAvailable(availablePoints,randomPoints,p);

            }
        }

        return randomPoints;
    }

    private void deleteFromLeftFields(ArrayList<Point> list){
        /*System.out.println("lista " + list.toString());
        leftFields.removeAll(list);*/
        leftFields.removeAll(list);
    }

    private void changeFromListToShips(List<Point> list){
        for(Point p : list){
            if(p != null) p.sign = shipChar;
        }
    }

    private Point getRandomPointFromList(List<Point> list){
        int index = (int)(Math.random()*list.size());
        return list.get(index);
    }

    private void addNeighboursToAvailable(List<Point> availablePoints, List<Point> randomPoints, Point point){
        List<Point> temp = getNeighbours(point);
        System.out.println("Temp: " + temp);
        for(Point p : temp){
            if(!availablePoints.contains(p) && !randomPoints.contains(p)) availablePoints.add(p);
        }

        System.out.println("Available: " + availablePoints.toString());
        System.out.println("Random: " + randomPoints.toString());
    }

    private List<Point> getNeighbours(Point point){
        List<Point> list = new ArrayList<>();
        if(getLeftPoint(point) != null) list.add(getLeftPoint(point));
        if(getRightPoint(point) != null) list.add(getRightPoint(point));
        if(getTopPoint(point) != null) list.add(getTopPoint(point));
        if(getBottomPoint(point) != null) list.add(getBottomPoint(point));

        return list;
    }

    private boolean isShip(Point point){
        if(point.x >= 0 && point.x < mapSize && point.y >= 0 && point.y < mapSize){
            return point.sign == shipChar;
        }
        return false;
    }

    private void makeShip(Point p){
        p.sign = shipChar;
    }

    private void makeWater(Point p){
        p.sign= waterChar;
    }

    private Point getRandomPointFromLeftFields(){
        int index = (int)(Math.random()*leftFields.size());
        return leftFields.get(index);
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
