package game;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnemyMap extends Map{

    private List<Cell> availableCells;

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

    public void removeFromAvailable(List<Cell> cells){
        availableCells.removeAll(cells);
    }
}
