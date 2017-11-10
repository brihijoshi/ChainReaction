package GUIEngine;

import GameEngine.GameEngine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class homeButtonGUI implements EventHandler<ActionEvent>{

    @Override
    public void handle(ActionEvent e) {

        Button homeButton = (Button) e.getSource();
        StackPane gp = (StackPane) homeButton.getParent();
        Scene sc = gp.getScene();
        Stage stage = (Stage) sc.getWindow();

        stage.setScene(GUIMain.createStartPage());

        stage.show();

    }

}