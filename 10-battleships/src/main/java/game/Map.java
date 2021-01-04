package game;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static game.CellType.*;

public class Map{

    protected final int SIZE = 10;
    protected Cell[][] map;

    public Map(){
        this.map = new Cell[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                map[i][j] = new Cell((char) ('A' + i), j, UNKNOWN);
            }
        }
    }

    public Map(File file){
        this.map = new Cell[SIZE][SIZE];
        try{
            FileReader fr = new FileReader(file);
            for(int i = 0; i < SIZE; i++){
                for(int j = 0; j < SIZE; j++){
                    char cellTypeChar = (char) fr.read();
                    while(!(cellTypeChar == SHIP.getCharacter() || cellTypeChar == WATER.getCharacter()))
                        cellTypeChar = (char) fr.read();
                    map[i][j] = new Cell((char) ('A' + i), j + 1, getTypeFromChar(cellTypeChar));
                }
            }
        }catch(IOException e){
            System.err.println("Problem with reading from file");
            e.printStackTrace();
        }

    }

    public Cell getCell(char x, int y){
        return map[x - 'A'][y - 1];
    }

    public void print(){
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                System.out.print(map[i][j].getType());
            }
            System.out.println();
        }
    }

    public List<Cell> getShipCells(){
        return Arrays.stream(map)
                .flatMap(Arrays::stream)
                .filter(c -> c.getType() == SHIP)
                .collect(Collectors.toList());
    }
}
