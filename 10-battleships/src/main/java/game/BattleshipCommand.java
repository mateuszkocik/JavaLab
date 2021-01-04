package game;

public enum BattleshipCommand{
    START("start"),
    MISS("pudło"),
    HIT("trafiony"),
    FLOODED("trafiony zatopiony"),
    LAST_FLOODED("ostatni zatopiony");

    String command;

    BattleshipCommand(String command){
        this.command = command;
    }
}
