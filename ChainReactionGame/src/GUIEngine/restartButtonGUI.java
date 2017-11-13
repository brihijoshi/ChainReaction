package GUIEngine;

import GameEngine.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;


public class restartButtonGUI implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {

        MenuItem source = (MenuItem) e.getSource();


        ArrayList<Player> players = GUIMain.get_gameEngine().get_gc().get_players();

        for (Player p:
             players) {
            p.set_isAlive(true);
            p.set_isKillable(false);
            p.set_isActive(false);
        }

//        for (int i = 0; i < players.size(); i++) {
//            players.get(i).set_isAlive(true);
//            players.get(i).set_isKillable(false);
//            players.get(i).set_isActive(false);
//        }

        players.get(0).set_isActive(true);
        players.get(0).set_isKillable(true);

        MenuButton parent = (MenuButton) source.getParentPopup().getOwnerNode();
        BorderPane bp = (BorderPane) parent.getParent();
        GridPane grid = (GridPane) bp.getCenter();

        Color firstColor = Color.web(players.get(0).get_colour());

        for(int i = 0; i < GUIMain.get_numRows(); i++) {
            for (int j = 0; j < GUIMain.get_numCols(); j++) {

                GUIMain.addOrbAndAnimate(grid, i, j, 0, firstColor);

            }
        }

        GUIMain.changeGridColor(grid, firstColor);


    }
}
