package GUIEngine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class resumeButtonGUI implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {

        GUIMain.get_gameEngine().set_choice(1);

        try {
            GUIMain.get_gameEngine().startGame();
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }



    }



}
