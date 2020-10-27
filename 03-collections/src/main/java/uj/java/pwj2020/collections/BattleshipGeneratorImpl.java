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
        for(int i =0; i < leftFields.size(); i++){
            System.out.print(leftFields.get(i) + ",");
        }

        return "a";

    }

    public static void main(String[] args){
        BattleshipGeneratorImpl b= new BattleshipGeneratorImpl();
        b.generateMap();
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
