package game;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnemyMap extends Map{

    private final List<Cell> availableCells;

    public EnemyMap(){
        super();
        availableCells = Arrays.stream(map)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }

    public Cell getRandomCell(){
        int index = (int) (Math.random() * availableCells.size());
        Cell cell = availableCells.get(index);
        availableCells.remove(index);

        return cell;
    }

    public void makeNeighboursWater(List<Cell> neighbours){
        availableCells.removeAll(neighbours);
        neighbours.forEach(c -> c.setType(CellType.WATER));
    }

    public void makeAvailableWater(){
        availableCells.forEach(c -> c.setType(CellType.WATER));
    }


    public List<Cell> getAvailableCells(){
        return availableCells;
    }
}
