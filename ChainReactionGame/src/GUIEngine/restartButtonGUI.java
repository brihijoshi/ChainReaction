package GUIEngine;

import GameEngine.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * An {@link EventHandler} class associated with the Restart button
 *
 *  @author adsrc
 *  @author brihijoshi
 *  @version 1.0
 *
 */

public class restartButtonGUI implements EventHandler<ActionEvent> {

    /**
     *
     * A function that takes the {@link ArrayList} of {@link Player} from the {@link GameEngine}
     * and refreshes it by setting every player as alive and not killable and not active.
     * It then sets the first player as the current player which is active and killable along with
     * creating the GUI grid with the color of the first player.
     *
     * It also disables the Undo button
     *
     * @param e the {@link ActionEvent} of the Restart button clicked
     */

    @Override
    public void handle(ActionEvent e) {

        GUIMain.setEnd_shown(false);


        MenuItem source = (MenuItem) e.getSource();

        Stage stage = (Stage) source.getParentPopup().getOwnerWindow();
        BorderPane bp = (BorderPane) stage.getScene().getRoot();
        GridPane grid = (GridPane) bp.getCenter();


        ArrayList<Player> players = GUIMain.get_gameEngine().get_gc().get_players();


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


        GUIMain.setCurrentPlayer(ColorUtil.colorToHex(firstColor));
        GUIMain.getRedoButton().setDisable(true);

    }
}
