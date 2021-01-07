package game;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static game.CellType.*;

public class Game{

    private List<Ship> myShips;
    private final Map myMap;
    private final List<Ship> enemyShips;
    private final EnemyMap enemyMap;
    private Cell previousCell;
    boolean active;


    public Game(File mapFile){
        this.myMap = new Map(mapFile);
        this.enemyShips = new ArrayList<>();
        this.enemyMap = new EnemyMap();
        this.previousCell = null;
        this.active = true;
        getShipsFromMyMap();
    }

    public Cell getNextCell(){
        previousCell = enemyMap.getRandomCell();
        return previousCell;
    }

    public void processBattleshipCommand(BattleshipCommand command){
        switch(command){
            case MISS:
                previousCell.setType(WATER);
                break;
            case HIT:
                hitShip();
                break;
            case FLOODED:
            case LAST_FLOODED:
                floodShip();
                break;
        }
    }

    private void floodShip(){
        Ship floodedShip = hitShip();
        enemyMap.makeNeighboursWater(floodedShip.getNeighbours(enemyMap.getAvailableCells()));
    }

    private Ship hitShip(){
        previousCell.setType(SHIP);
        return extendShip(previousCell, enemyShips);
    }

    public BattleshipCommand shootTheCell(char x, int y){
        Cell cell = myMap.getCell(x, y);
        BattleshipCommand command = BattleshipCommand.MISS;
        cell.setType(MISS);
        if(isShip(cell)){
            cell.setType(HIT);
            Ship ship = getShipByCell(cell);
            command = ship.getCommand();
            if(everyShipFlooded()) command = BattleshipCommand.LAST_FLOODED;
        }
        return command;
    }

    private boolean everyShipFlooded(){
        for(Ship ship : myShips)
            if(!ship.isFlooded())
                return false;
        return true;
    }

    private Ship getShipByCell(Cell cell){
        for(Ship ship : myShips)
            if(ship.isInShip(cell))
                return ship;
        return null;
    }

    private boolean isShip(Cell cell){
        CellType cellType = cell.getType();
        return cellType == SHIP || cellType == HIT;
    }


    private void getShipsFromMyMap(){
        var shipCells = myMap.getShipCells();
        myShips = new ArrayList<>();
        for(Cell shipCell : shipCells){
            extendShip(shipCell, myShips);
        }
    }

    private Ship extendShip(Cell shipCell, List<Ship> ships){
        Ship ship = getShipWhichCanBeExtended(shipCell, ships);
        if(ship != null){
            ship.addCell(shipCell);
        }else{
            ship = new Ship();
            ship.addCell(shipCell);
            ships.add(ship);
        }
        return ship;
    }

    private Ship getShipWhichCanBeExtended(Cell shipCell, List<Ship> ships){
        for(Ship ship : ships)
            if(ship.isNeighbour(shipCell))
                return ship;
        return null;
    }

    public boolean isActive(){
        return active;
    }

    public void showResults(){
        enemyMap.print();
        System.out.println();
        myMap.print();
        active = false;
    }
}
