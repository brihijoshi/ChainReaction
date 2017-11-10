package GUIEngine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class colorPickerGUI implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        System.out.println(GUIMain.getPlayer_1().getValue()); //to be used to compare cell color
    }
}
