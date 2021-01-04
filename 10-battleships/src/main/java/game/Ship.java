package game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ship{

    private List<Cell> cells;

    public Ship(){
        this.cells = new ArrayList<>();
    }

    public void addCell(Cell cell){
        cells.add(cell);
    }

    public boolean isNeighbour(Cell cell){
        for(Cell c : cells) if(c.isNeighbour(cell)) return true;
        return false;
    }

    public List<Cell> getNeighbours(List<Cell> availableCells){
        var neighbours = availableCells.stream()
                .filter(this::isNeighbour)
                .distinct()
                .collect(Collectors.toList());
        neighbours.removeAll(cells);
        return neighbours;
    }

    public BattleshipCommand getCommand(){
        return isFlooded() ? BattleshipCommand.FLOODED : BattleshipCommand.HIT;
    }

    public boolean isFlooded(){
        for(Cell c : cells) if(c.getType() != CellType.HIT) return false;
        return true;
    }

    public boolean isInShip(Cell cell){
        return cells.contains(cell);
    }
}
