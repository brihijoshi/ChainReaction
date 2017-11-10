package GUIEngine;

import GameEngine.GameEngine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class settingsButtonGUI implements EventHandler<ActionEvent>{

    @Override
    public void handle(ActionEvent e) {

        Button settingsButton = (Button) e.getSource();
        StackPane gp = (StackPane) settingsButton.getParent();
        Scene sc = gp.getScene();
        Stage stage = (Stage) sc.getWindow();

        stage.setScene(GUIMain.createSettingsPage());

        stage.show();

    }

}
