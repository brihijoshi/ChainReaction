package GUIEngine;

import GameEngine.GameEngine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;

public class startButtonGUI implements EventHandler<ActionEvent> {

    private int numPlayers = 2;
    private int choiceOfGrid = 0;


    @Override
    public void handle(ActionEvent e)
    {

        //Just as we press start, we clear out the game.ser and undo.se file

        File file_game = new File("game.ser");
        File file_undo = new File("undo.ser");

        if (!file_game.exists() && !file_undo.exists()){
            GUIMain.get_gameEngine().set_choice(0);     // means that you are starting a new game

            if (GUIMain.getNumPlayersCB().getValue() != null) {
                numPlayers = Integer.parseInt(GUIMain.getNumPlayersCB().getValue().toString());
            }

            if (GUIMain.getGridChoiceCB().getValue() != null) {
                String recordedValue = GUIMain.getGridChoiceCB().getValue().toString();
                choiceOfGrid = recordedValue.equals("9x6") ? 0 : 1;
            }

            GUIMain.get_gameEngine().set_numPlayers(numPlayers);
            GUIMain.get_gameEngine().set_gridSize(choiceOfGrid);

            //Populating the HashMap with the default values

            GUIMain.getPlayercolor().put(1, ColorUtil.colorToHex(GUIMain.getPlayer_1().getValue()));
            GUIMain.getPlayercolor().put(2, ColorUtil.colorToHex(GUIMain.getPlayer_2().getValue()));
            GUIMain.getPlayercolor().put(3, ColorUtil.colorToHex(GUIMain.getPlayer_3().getValue()));
            GUIMain.getPlayercolor().put(4, ColorUtil.colorToHex(GUIMain.getPlayer_4().getValue()));
            GUIMain.getPlayercolor().put(5, ColorUtil.colorToHex(GUIMain.getPlayer_5().getValue()));
            GUIMain.getPlayercolor().put(6, ColorUtil.colorToHex(GUIMain.getPlayer_6().getValue()));
            GUIMain.getPlayercolor().put(7, ColorUtil.colorToHex(GUIMain.getPlayer_7().getValue()));
            GUIMain.getPlayercolor().put(8, ColorUtil.colorToHex(GUIMain.getPlayer_8().getValue()));

            GUIMain.get_gameEngine().setplayer_colors(GUIMain.getPlayercolor());

            try {
                GUIMain.get_gameEngine().startGame();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            Button startButton = (Button) e.getSource();
            StackPane gp = (StackPane) startButton.getParent();
            Scene sc = gp.getScene();
            Stage stage = (Stage) sc.getWindow();

            stage.setScene(GUIMain.createGamePage());
        }

        else {
            
            if (file_game.delete() && file_undo.delete()) {

                GUIMain.get_gameEngine().set_choice(0);     // means that you are starting a new game

                if (GUIMain.getNumPlayersCB().getValue() != null) {
                    numPlayers = Integer.parseInt(GUIMain.getNumPlayersCB().getValue().toString());
                }

                if (GUIMain.getGridChoiceCB().getValue() != null) {
                    String recordedValue = GUIMain.getGridChoiceCB().getValue().toString();
                    choiceOfGrid = recordedValue.equals("9x6") ? 0 : 1;
                }

                GUIMain.get_gameEngine().set_numPlayers(numPlayers);
                GUIMain.get_gameEngine().set_gridSize(choiceOfGrid);

                //Populating the HashMap with the default values

                GUIMain.getPlayercolor().put(1, ColorUtil.colorToHex(GUIMain.getPlayer_1().getValue()));
                GUIMain.getPlayercolor().put(2, ColorUtil.colorToHex(GUIMain.getPlayer_2().getValue()));
                GUIMain.getPlayercolor().put(3, ColorUtil.colorToHex(GUIMain.getPlayer_3().getValue()));
                GUIMain.getPlayercolor().put(4, ColorUtil.colorToHex(GUIMain.getPlayer_4().getValue()));
                GUIMain.getPlayercolor().put(5, ColorUtil.colorToHex(GUIMain.getPlayer_5().getValue()));
                GUIMain.getPlayercolor().put(6, ColorUtil.colorToHex(GUIMain.getPlayer_6().getValue()));
                GUIMain.getPlayercolor().put(7, ColorUtil.colorToHex(GUIMain.getPlayer_7().getValue()));
                GUIMain.getPlayercolor().put(8, ColorUtil.colorToHex(GUIMain.getPlayer_8().getValue()));

                GUIMain.get_gameEngine().setplayer_colors(GUIMain.getPlayercolor());

                try {
                    GUIMain.get_gameEngine().startGame();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                Button startButton = (Button) e.getSource();
                StackPane gp = (StackPane) startButton.getParent();
                Scene sc = gp.getScene();
                Stage stage = (Stage) sc.getWindow();

                stage.setScene(GUIMain.createGamePage());

                //stage.show();
            } else {
                System.out.println("Cant start the game.....");
            }

        }

    }

}
