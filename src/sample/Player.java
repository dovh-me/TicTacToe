package sample;

public class Player {
    private int playerID;
    private String symbol;

    public Player(int playerID, String symbol) {
        this.playerID = playerID;
        this.symbol = symbol;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
