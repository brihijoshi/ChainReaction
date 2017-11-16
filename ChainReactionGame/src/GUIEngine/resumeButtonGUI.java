package GUIEngine;

import GameEngine.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class resumeButtonGUI implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {

        GUIMain.get_gameEngine().set_choice(1);



        GameController gc = new GameController();

        GUIMain.get_gameEngine().set_gc(gc);


        try {

            GUIMain.get_gameEngine().get_gc().loadGameState();
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }

        GUIMain.get_gameEngine().set_numPlayers(gc.get_players().size());

        if(GUIMain.get_gameEngine().get_gc().get_grid().get_grid().size() == 9) {
            GUIMain.get_gameEngine().set_gridSize(0);
        }
        else {
            GUIMain.get_gameEngine().set_gridSize(1);
        }

        try {
            GUIMain.get_gameEngine().startGame();
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }

        Button resumeButton = (Button) e.getSource();
        StackPane gp = (StackPane) resumeButton.getParent();
        Scene sc = gp.getScene();
        Stage stage = (Stage) sc.getWindow();

        stage.setScene(GUIMain.createGamePage());

        //stage.show();





    }



}
