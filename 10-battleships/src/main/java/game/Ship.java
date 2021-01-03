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
        return availableCells.stream()
                .filter(this::isNeighbour)
                .distinct()
                .collect(Collectors.toList());
    }

    public BattleshipCommand getCommand(Cell cell){
        cell.setType(CellType.HIT);
        return isFlooded() ? BattleshipCommand.FLOODED : BattleshipCommand.HIT;
    }

    private boolean isFlooded(){
        for(Cell c : cells) if(c.getType() != CellType.HIT) return false;
        return true;
    }
}
