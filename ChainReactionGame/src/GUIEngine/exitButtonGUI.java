package GUIEngine;

import Exceptions.UnexpectedExitException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class exitButtonGUI implements EventHandler<ActionEvent> {

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
