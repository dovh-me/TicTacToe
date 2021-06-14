package sample;

import javafx.scene.control.Button;

public class Tile {
    Button tile;
    String tileId;
    boolean isPlayed;
    Player playedBy;
    private int colIndex;

    public int getColIndex() {
        return colIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    private int rowIndex;

    public Button getTile() {
        return tile;
    }

    public void setTile(Button tile) {
        this.tile = tile;
    }

    public String getTileId() {
        return tileId;
    }

    public void setTileId(String tileId) {
        this.tileId = tileId;
    }

    public Player getPlayedBy() {
        return playedBy;
    }

    public void setPlayedBy(Player playedBy) {
        this.playedBy = playedBy;
    }

    public Tile(Button tile, String tileId, Integer colIndex, Integer rowIndex) {
        this.tile = tile;
        this.tileId = tileId;
        this.rowIndex = rowIndex != null ? rowIndex : 0;
        this.colIndex = colIndex != null ? colIndex : 0;
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
    }

    public int[] getTilePosition(){
        return new int[]{this.colIndex,this.rowIndex};
    }

    public void resetTileText(){
        this.tile.setText("");
    }
}
