package GUIEngine;

import GameEngine.GameEngine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class settingsButtonGUI implements EventHandler<ActionEvent>{
    private int numPlayers = 2;
    private int choiceOfGrid = 0;

    @Override
    public void handle(ActionEvent e) {

        // In the event that person goes to Settings before pressing Start
        if(GUIMain.getNumPlayersCB().getValue()!=null) {
            numPlayers = Integer.parseInt(GUIMain.getNumPlayersCB().getValue().toString());
        }

        if(GUIMain.getGridChoiceCB().getValue()!=null) {
            String recordedValue = GUIMain.getGridChoiceCB().getValue().toString();
            choiceOfGrid = recordedValue.equals("9x6")?0:1;
        }

        GUIMain.get_gameEngine().set_numPlayers(numPlayers);
        GUIMain.get_gameEngine().set_gridSize(choiceOfGrid);



        Button settingsButton = (Button) e.getSource();
        StackPane gp = (StackPane) settingsButton.getParent();
        Scene sc = gp.getScene();
        Stage stage = (Stage) sc.getWindow();

        stage.setScene(GUIMain.createSettingsPage());

        //stage.show();

    }

}
