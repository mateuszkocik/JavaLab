package game;

import java.io.*;

public class Map{

    protected final int SIZE = 10;
    protected Cell[][] map;

    public Map(){
        this.map = new Cell[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                map[i][j] = new Cell((char) ('A' + i), j, CellType.UNKNOWN);
            }
        }
    }

    public Map(File file){
        try{
            FileReader fr = new FileReader(file);
            for(int i = 0; i < SIZE; i++){
                for(int j = 0; j < SIZE; j++){
                    char cellTypeChar = (char) fr.read();
                    map[i][j] = new Cell((char) ('A' + i), j, CellType.getTypeFromChar(cellTypeChar));
                }
            }
        }catch(IOException e){
            System.err.println("Problem with reading from file");
            e.printStackTrace();
        }

    }

    public Cell getCell(char x, int y){
        return map['A' - x][y];
    }

    public void setCell(char x, int y, CellType type){
        map['A' - x][y].setType(type);
    }

    public void print(){
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                System.out.print(map[i][j].getType());
            }
            System.out.println();
        }
    }

}
