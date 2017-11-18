package GUIEngine;

import Exceptions.UnexpectedExitException;
import GameEngine.GameState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 *  An {@link EventHandler} class associated with the Exit button
 *
 *  @author adsrc
 *  @author brihijoshi
 *  @version 1.0
 */


public class exitButtonGUI implements EventHandler<ActionEvent> {

    /** A function that serialises the {@link GameState} to handle unexpected exits and mid-game exits
     *
     * @param e the {@link ActionEvent} of the exit button clicked
     */

    @Override
    public void handle(ActionEvent e) {

        try{
            throw new UnexpectedExitException("Game exited.");
        }
        catch (UnexpectedExitException e1){


            if (!GUIMain.checkEndGame()) {

                try {
                    GUIMain.get_gameEngine().get_gc().saveGameState();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }


            MenuItem source = (MenuItem) e.getSource();
            Stage stage = (Stage) source.getParentPopup().getOwnerWindow();

            stage.setScene(GUIMain.createStartPage());

        }
    }


}
