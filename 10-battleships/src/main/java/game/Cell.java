package game;

public class Cell{

    private final char x;
    private final int y;
    private CellType type;

    public Cell(char x, int y, CellType type){
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public boolean isNeighbour(Cell cell){
        return Math.abs(cell.x - x) < 1 && Math.abs(cell.y - y) < 1;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public String toString(){
        return String.valueOf(type.character);
    }

    public CellType getType(){
        return type;
    }

    public void setType(CellType type){
        this.type = type;
    }


}

enum CellType{
    WATER('.'),
    SHIP('#'),
    UNKNOWN('?'),
    MISS('~'),
    HIT('@');

    final char character;

    CellType(char character){
        this.character = character;
    }

    static CellType getTypeFromChar(char c){
        switch(c){
            case '.':
                return WATER;
            case '#':
                return SHIP;
            case '?':
                return UNKNOWN;
            case '~':
                return MISS;
            case '@':
                return HIT;
            default:
                return null;
        }
    }

}
