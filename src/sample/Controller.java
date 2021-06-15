package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    Button zeroZero,zeroOne,zeroTwo,oneZero,oneOne,oneTwo,twoZero,twoOne,twoTwo,newGameButton;
    @FXML
    GridPane board;
    @FXML
    AnchorPane winAnchorPane;
    @FXML
    Label winnerLabel;

    Game game = new Game();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game.setTurns();
        game.setTiles(zeroZero,zeroOne,zeroTwo,oneZero,oneOne,oneTwo,twoZero,twoOne,twoTwo);
    }

    @FXML
    public void buttonClick(ActionEvent event){
        Button clickedButton = (Button) event.getSource();
        System.out.println("Button Clicked!");
        Tile playedTile = game.getPlayedTile(clickedButton);
        System.out.println(Arrays.toString(playedTile.getTilePosition()));
        if (playedTile.isPlayed) return;
        clickedButton.setText(game.nowPlaying.getSymbol());
        playedTile.setPlayed(true);
        playedTile.setPlayedBy(game.getNowPlaying());
        if(game.checkWin(playedTile)) {
            winAnchorPane.setVisible(true);
            board.setDisable(true);
            winnerLabel.setText(game.getNowPlaying().getSymbol() + " WON!");
        }else if(game.gameOver()){
            winAnchorPane.setVisible(true);
            board.setDisable(true);
            winnerLabel.setText("DRAW!");
        }

        game.setTurns();
    }

    public void newGame(){
        board.setDisable(false);
        winAnchorPane.setVisible(false);
        game.resetTiles();
        game = new Game();
        game.setTurns();
        game.setTiles(zeroZero,zeroOne,zeroTwo,oneZero,oneOne,oneTwo,twoZero,twoOne,twoTwo);
    }
    public void closeWindow(){
        Stage thisStage = (Stage) board.getScene().getWindow();
        thisStage.close();
    }
}
