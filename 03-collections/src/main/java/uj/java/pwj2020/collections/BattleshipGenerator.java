package uj.java.pwj2020.collections;

public interface BattleshipGenerator {

    String generateMap();

    static BattleshipGenerator defaultInstance() {
        return new BattleshipGeneratorImpl();
    }




}
