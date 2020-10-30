package uj.java.pwj2020.collections;

import java.util.ArrayList;
import java.util.List;

public class BattleshipGeneratorImpl implements BattleshipGenerator{

    static final int mapSize = 10;
    int singleMast = 4;
    int doubleMast = 3;
    int tripleMast = 2;
    int quadrupleMast = 1;
    char[][] map = new char[mapSize][mapSize];
    List<Integer> leftFields = new ArrayList<Integer>();

    @Override
    public String generateMap() {
        fillListWithNumbers(leftFields);

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
        int index = getRandomNumberFromLeftFields();

    }

    private boolean thereAreLeftShips(){
        return singleMast == 0 && doubleMast == 0 && tripleMast == 0 && quadrupleMast == 0 ? false : true;
    }

    public static void main(String[] args){
        BattleshipGeneratorImpl b= new BattleshipGeneratorImpl();


        b.fillWithWater();
        /*b.map[5][5] = '#';
        b.map[5][4] = '#';
        b.map[4][4] = '#';*/
        /*b.map[5][5] = '#';
        b.map[4][5] = '#';
        b.map[3][5] = '#';*/
        b.map[9][9] = '#';
        b.map[9][8] = '#';
        b.displayMap();
        System.out.println(b.getShipSizeIfChanged(9,7));

    }


    private int getShipSizeIfChanged(int x, int y){
        /*
        ZASTANOWIC SIE CZY NIE DOCHODZI DO SYTUACJI ZE ROZPATRUJEMY JUZ PUNKT KTORY JEST STATKIEM

         */
        makeShip(x,y);
        int shipSize = getShipSize(x,y,-100,-100);
        makeWater(x,y);

        return shipSize;
    }

    private int getShipSize(int x, int y, int px, int py){
        int shipSize = 0;
        if(isShip(x,y)){
            shipSize++;
            if(!(x-1 == px && y == py)) shipSize += getShipSize(x-1,y,x,y);
            if(!(x+1 == px && y == py)) shipSize += getShipSize(x+1,y,x,y);
            if(!(x == px && y+1 == py)) shipSize += getShipSize(x,y+1,x,y);
            if(!(x == px && y-1 == py)) shipSize += getShipSize(x,y-1,x,y);
        }

        return shipSize;
    }



    private boolean isShip(int x, int y){
        if(x >= 0 && x < mapSize && y >= 0 && y < mapSize){
            return map[x][y] == '#' ? true : false;
        }
        return false;
    }

    private void makeShip(int x, int y){
        map[x][y] = '#';
    }

    private void makeWater(int x, int y){
        map[x][y] = '.';
    }

    private int getRandomNumberFromLeftFields(){
        int index = (int)(Math.random()*leftFields.size());
        int number = leftFields.get(index);

        return number;
    }


    private void fillListWithNumbers(List<Integer> list){
        for(int i = 0; i < Math.pow(mapSize,2); i++) list.add(i);
    }

    private void displayMap(){
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    private void fillWithWater(){
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                map[i][j] = '.';
            }
        }
    }
}
