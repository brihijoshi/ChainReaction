package GUIEngine;

import GameEngine.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class endPageRestartGUI  implements EventHandler<ActionEvent> {

    static GridPane grid;
    static Stage stage;
    public void handle(ActionEvent e) {

        ArrayList<Player> players = GUIMain.get_gameEngine().get_gc().get_players();

        System.out.println("BUTTON PLAY CLICKED");

        for (int i = 0; i < players.size(); i++) {
            players.get(i).set_isAlive(true);
            players.get(i).set_isKillable(false);
            players.get(i).set_isActive(false);
        }

        players.get(0).set_isActive(true);
        players.get(0).set_isKillable(true);

        Color firstColor = Color.web(players.get(0).get_colour());

        for (int i = 0; i < GUIMain.get_numRows(); i++) {
            for (int j = 0; j < GUIMain.get_numCols(); j++) {

                GUIMain.addOrbAndAnimate(grid, i, j, 0, firstColor);
                GUIMain.get_gameEngine().get_gc().get_grid().get_grid().get(i).get(j).set_currmass(0);


            }
        }

        GUIMain.changeGridColor(grid, firstColor);

        System.out.println("_____ inside restart handler _______");
        System.out.println("color of the guy that goes first" + firstColor);
        System.out.println(" ________ end restart handler ________");

        GUIMain.setCurrentPlayer(ColorUtil.colorToHex(firstColor));
        GUIMain.getRedoButton().setDisable(true);
        GUIMain.getRedoButton().setDisable(true);

        stage.setScene(GUIMain.createGamePage());

        Button btn = (Button) e.getSource();
        btn.getScene().getWindow().hide();

    }

    public static void setInputs(GridPane g, Stage s){
        grid = g;
        stage = s;

    }

}
