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

    public String getCommandText(){
        return command;
    }

    public static BattleshipCommand getBattleshipCommand(String command){
        switch(command){
            case "start":
                return START;
            case "pudło":
                return MISS;
            case "trafiony":
                return HIT;
            case "trafiony zatopiony":
                return FLOODED;
            case "ostatni zatopiony":
                return LAST_FLOODED;
            default:
                return null;
        }
    }

}
