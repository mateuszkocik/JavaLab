package uj.java.pwj2020.collections;

import java.util.ArrayList;
import java.util.List;

public class BattleshipGeneratorImpl implements BattleshipGenerator{

    static final int mapSize = 10;
    int singleMast = 4;
    int doubleMast = 3;
    int tripleMast = 2;
    int quadrupleMast = 1;
    List<Point> map = makeListWithWaterPoints(mapSize);
    List<Point> leftFields;
    static final char shipChar = '#';
    static final char waterChar = '.';


    private void initLeftFields(){
        leftFields = new ArrayList<Point>();
        for(Point p : map){
            leftFields.add(p);
        }
    }

    @Override
    public String generateMap(){
        initLeftFields();
        while(thereAreLeftShips()){
            int nextShipSize;
            ArrayList<Point> list;
            if(quadrupleMast > 0){
                nextShipSize = 4;
            }else if(tripleMast > 0){
                nextShipSize = 3;
            }else if(doubleMast > 0){
                nextShipSize = 2;
            }else{
                nextShipSize = 1;
            }
            list = generateRandomXPoints(nextShipSize);
            if(list.size() == nextShipSize){
                changeFromListToShips(list);
                deleteFromLeftFields(list);
                makeUnavailable(list);
                if(list.size() == 1) singleMast--;
                if(list.size() == 2) doubleMast--;
                if(list.size() == 3) tripleMast--;
                if(list.size() == 4) quadrupleMast--;
            }
        }

        return convertMapToString();

    }

    private void makeUnavailable(ArrayList<Point> list){
        for(Point p: list){
            var neighbours = getNeighbours(p);
            var corners = getCornerPoints(p);

            leftFields.removeAll(neighbours);
            leftFields.removeAll(corners);
        }
    }

    private boolean thereAreLeftShips(){
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
        leftFields.removeAll(list);
    }

    private void changeFromListToShips(List<Point> list){
        for(Point p : list){
            if(p != null) p.sign = shipChar;
        }
    }

    private Point getRandomPointFromList(List<Point> list){
        return list.get((int)(Math.random()*list.size()));
    }

    private void addNeighboursToAvailable(List<Point> availablePoints, List<Point> randomPoints, Point point){
        List<Point> temp = getNeighbours(point);
        for(Point p : temp){
            if(!availablePoints.contains(p) && !randomPoints.contains(p)) availablePoints.add(p);
        }
    }

    private List<Point> getNeighbours(Point point){
        List<Point> list = new ArrayList<>();
        if(getLeftPoint(point) != null) list.add(getLeftPoint(point));
        if(getRightPoint(point) != null) list.add(getRightPoint(point));
        if(getTopPoint(point) != null) list.add(getTopPoint(point));
        if(getBottomPoint(point) != null) list.add(getBottomPoint(point));

        return list;
    }

    private List<Point> getCornerPoints(Point point){
        List<Point> list = new ArrayList<>();
        if(getTopLeftPoint(point) != null) list.add(getTopLeftPoint(point));
        if(getTopRightPoint(point) != null) list.add(getTopRightPoint(point));
        if(getBottomRightPoint(point) != null) list.add(getBottomRightPoint(point));
        if(getBottomLeftPoint(point) != null) list.add(getBottomLeftPoint(point));

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

    private Point getTopLeftPoint(Point x){
        return getPoint(x.x-1,x.y+1);
    }

    private Point getTopRightPoint(Point x){
        return getPoint(x.x+1,x.y+1);
    }

    private Point getBottomLeftPoint(Point x){
        return getPoint(x.x-1,x.y-1);
    }

    private Point getBottomRightPoint(Point x){
        return getPoint(x.x+1,x.y-1);
    }


    private Point getPoint(int x, int y){
        if(x >= 0 && x < mapSize && y >= 0 && y < mapSize){
            return map.get(x*mapSize+y);
        }
        return null;
    }

    private ArrayList<Point> makeListWithWaterPoints(int size) {
        var list = new ArrayList<Point>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                list.add(new Point(i, j, '.'));
            }
        }
        return list;
    }

    private String convertMapToString(){
        StringBuilder sb = new StringBuilder();
        for(Point p : map){
            sb.append(p.sign);
        }

        return sb.toString();
    }

    private void displayMap(){
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                System.out.print(getPoint(i,j).sign);
            }
            System.out.println();
        }
    }

    class Point {
        final int x, y;
        char sign;

        Point(int x, int y, char sign) {
            this.x = x;
            this.y = y;
            this.sign = sign;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            return y == point.y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }

    }

}

