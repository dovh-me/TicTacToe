package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Arrays;
import java.util.LinkedList;

public class Game {
    private final LinkedList<Integer> turns = new LinkedList();

    private final Tile[] tiles = new Tile[9];

    private int tilesToWin = 3;

    Player player1 = new Player(1,"O");
    Player player2 = new Player(2,"X");

    Player nowPlaying;

    public void setTurns(){
        if(turns.isEmpty()){
            this.nowPlaying = player1;
        }
        else{
            this.nowPlaying = turns.getLast() == player1.getPlayerID() ? player2: player1;
        }
        turns.add(this.nowPlaying.getPlayerID());

    }

    public boolean checkWin(Tile playedTile){
        if(horizontalCheck(playedTile) || verticalCheck(playedTile) || crossCheck(playedTile)){
            return true;
        }
        return false;
    }

    public Player getNowPlaying() {
        return nowPlaying;
    }

    public void setNowPlaying(Player nowPlaying) {
        this.nowPlaying = nowPlaying;
    }

    public void setTiles(Button... buttons){
        Integer counter = 0;
        for (Button button : buttons) {
            tiles[counter] = new Tile(button, counter.toString(), GridPane.getColumnIndex(button),GridPane.getRowIndex(button));
            counter++;
        }
    }

    public Tile getPlayedTile(Button button){
        for (Tile tile : tiles) {
            if(tile.getTile() == button){
                return tile;
            }
        }
        return null;
    }

    public Tile getTileByPosition(Integer colIndex, Integer rowIndex){
        for (Tile tile: tiles){
            int[] tilePosition = tile.getTilePosition();
            if(tilePosition[0] == colIndex && tilePosition[1] == rowIndex) return tile;
        }
        return null;
    }

    //Ending game - Checking who won!!!
    public boolean horizontalCheck(Tile currentTile){
        if(checkLeft(currentTile) && checkRight(currentTile)) {
            System.out.println("Horizontal win!");
            return true;
        }
        return false;
    }
    public boolean verticalCheck(Tile currentTile){
        if(checkAbove(currentTile) && checkBelow(currentTile)) {
            System.out.println("Vertical win!");
            return true;
        }
        return false;
    }

    public boolean crossCheck(Tile currentTile){
        int currentRow = currentTile.getRowIndex();
        int currentCol = currentTile.getColIndex();
        if((currentCol == currentRow) && currentCol != 1){
            return crossCheckRightToLeftToLeftToRight(currentTile);
        }else if((currentCol == 0 && currentRow == tilesToWin -1) || (currentCol == tilesToWin -1 && currentRow == 0)) {
            return crossCheckLeftToRightToRightToLeft(currentTile);
        }else if(currentCol == 1 && (currentRow == currentCol)){
            return crossCheckLeftToRightToRightToLeft(currentTile) || crossCheckRightToLeftToLeftToRight(currentTile);
        }
        return false;
    }

    public boolean crossCheckLeftToRightToRightToLeft(Tile currentTile){
        if(checkRightAbove(currentTile) && checkLeftBelow(currentTile)){
            System.out.println("LeftToRightToRightToLeft win!");
            return true;
        }
        return false;
    }

    public boolean crossCheckRightToLeftToLeftToRight(Tile currentTile){
        if(checkLeftAbove(currentTile) && checkRightBelow(currentTile)){
            System.out.println("RightToLeftToLeftToRight win!");
            return true;
        }
        return false;
    }

    public boolean checkLeft(Tile currentTile){
        int colNumber = currentTile.getColIndex();
        if(colNumber == 0) return true;
        Tile tileToLeft = getTileByPosition(currentTile.getColIndex() -1, currentTile.getRowIndex());
        if(currentTile.playedBy == tileToLeft.playedBy) {
            System.out.println(
                    "Checking " + Arrays.toString(currentTile.getTilePosition()) + " vs " + Arrays.toString(tileToLeft.getTilePosition())
            );
            return checkLeft(tileToLeft);
        }
        else
            return false;
    }
    public boolean checkRight(Tile currentTile){
        int colNumber = currentTile.getColIndex();
        if(colNumber == tilesToWin -1) return true;
        Tile tileToRight = getTileByPosition(currentTile.getColIndex() +1, currentTile.getRowIndex());
        if(currentTile.playedBy == tileToRight.playedBy) {
            System.out.println(
                    "Checking " + Arrays.toString(currentTile.getTilePosition()) + " vs " + Arrays.toString(tileToRight.getTilePosition())
            );
            return checkRight(tileToRight);
        }
        else
            return false;
    }

    public boolean checkAbove(Tile currentTile){
        int rowNumber = currentTile.getRowIndex();
        if(rowNumber == 0) return true;
        Tile tileAbove = getTileByPosition(currentTile.getColIndex(), currentTile.getRowIndex() -1);
        if(currentTile.playedBy == tileAbove.playedBy) {
            System.out.println(
                    "Checking " + Arrays.toString(currentTile.getTilePosition()) + " vs " + Arrays.toString(tileAbove.getTilePosition())
            );
            return checkAbove(tileAbove);
        }
        else
            return false;
    }

    public boolean checkBelow(Tile currentTile){
        int rowNumber = currentTile.getRowIndex();
        if(rowNumber == tilesToWin -1) return true;
        Tile tileBelow = getTileByPosition(currentTile.getColIndex(), currentTile.getRowIndex() +1);
        if(currentTile.playedBy == tileBelow.playedBy) {
            System.out.println(
                    "Checking " + Arrays.toString(currentTile.getTilePosition()) + " vs " + Arrays.toString(tileBelow.getTilePosition())
            );
            return checkBelow(tileBelow);
        }
        else
            return false;
    }

    //checks from right bottom corner
    public boolean checkLeftAbove(Tile currentTile){
        int rowNumber = currentTile.getRowIndex();
        if(rowNumber == 0) return true;
        Tile tileLeftAbove = getTileByPosition(currentTile.getColIndex() -1, currentTile.getRowIndex() -1);
        if(currentTile.playedBy == tileLeftAbove.playedBy) {
            System.out.println(
                    "Checking " + Arrays.toString(currentTile.getTilePosition()) + " vs " + Arrays.toString(tileLeftAbove.getTilePosition())
            );
            return checkLeftAbove(tileLeftAbove);
        }
        else
            return false;
    }

    //checks from right top corner
    public boolean checkLeftBelow (Tile currentTile){
        int rowNumber = currentTile.getRowIndex();
        if(rowNumber == tilesToWin -1) return true;
        Tile tileLeftBelow = getTileByPosition(currentTile.getColIndex() -1, currentTile.getRowIndex() +1);
        if(currentTile.playedBy == tileLeftBelow.playedBy) {
            System.out.println(
                    "Checking " + Arrays.toString(currentTile.getTilePosition()) + " vs " + Arrays.toString(tileLeftBelow.getTilePosition())
            );
            return checkLeftBelow(tileLeftBelow);
        }
        else
            return false;
    }

    //checks from left bottom corner
    public boolean checkRightAbove(Tile currentTile){
        int rowNumber = currentTile.getRowIndex();
        if(rowNumber == 0) return true;
        Tile tileRightAbove = getTileByPosition(currentTile.getColIndex() +1, currentTile.getRowIndex() -1);
        if(currentTile.playedBy == tileRightAbove.playedBy) {
            System.out.println(
                    "Checking " + Arrays.toString(currentTile.getTilePosition()) + " vs " + Arrays.toString(tileRightAbove.getTilePosition())
            );
            return checkRightAbove(tileRightAbove);
        }
        else
            return false;
    }

    //checks left top corner
    public boolean checkRightBelow(Tile currentTile){
        int rowNumber = currentTile.getRowIndex();
        if(rowNumber == tilesToWin -1) return true;
        Tile tileRightBelow = getTileByPosition(currentTile.getColIndex() +1, currentTile.getRowIndex() +1);
        if(currentTile.playedBy == tileRightBelow.playedBy) {
            System.out.println(
                    "Checking " + Arrays.toString(currentTile.getTilePosition()) + " vs " + Arrays.toString(tileRightBelow.getTilePosition())
            );
            return checkRightBelow(tileRightBelow);
        }
        else
            return false;
    }

    public void resetTiles(){
        for (Tile tile : this.tiles){
            tile.resetTileText();
        }
    }
}
