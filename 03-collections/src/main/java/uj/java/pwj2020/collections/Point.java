package uj.java.pwj2020.collections;

import java.util.ArrayList;

final class Point {
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
        return "("+x+","+y+")";
    }

    public static ArrayList<Point> makeListWithWaterPoints(int size){
        var list = new ArrayList<Point>();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                list.add(new Point(i,j,'.'));
            }
        }
        return list;
    }



}