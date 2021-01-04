package game;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static game.CellType.*;

public class Game{

    private List<Ship> myShips;
    private Map myMap;
    private List<Ship> enemyShips;
    private EnemyMap enemyMap;
    private Cell previousCell;


    public Game(File mapFile){
        this.myMap = new Map(mapFile);
        this.enemyShips = new ArrayList<>();
        this.enemyMap = new EnemyMap();
        this.previousCell = null;
        getShipsFromMyMap();
    }

    public Cell processBattleshipCommand(BattleshipCommand command){
        switch(command){
            case MISS -> previousCell.setType(WATER);
            case HIT -> hitShip();
            case FLOODED -> floodShip();
            case LAST_FLOODED -> {
                floodShip();
                System.out.println("Wygrana");
                showResults();
            }
        }
        previousCell = enemyMap.getRandomCell();
        return previousCell;
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
        if(isShip(cell)){
            cell.setType(HIT);
            if(everyShipFlooded()) return BattleshipCommand.LAST_FLOODED;
            Ship ship = getShipByCell(cell);
            if(ship.isFlooded()) return BattleshipCommand.FLOODED;
            return BattleshipCommand.HIT;
        }else{
            cell.setType(MISS);
            return BattleshipCommand.MISS;
        }

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

    private void showResults(){
        enemyMap.print();
        System.out.println();
        myMap.print();
    }
}
