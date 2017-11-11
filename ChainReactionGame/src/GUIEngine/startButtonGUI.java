package GUIEngine;

import GameEngine.GameEngine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class startButtonGUI implements EventHandler<ActionEvent>{

    private int numPlayers = 2;
    private int choiceOfGrid = 0;


    @Override
    public void handle(ActionEvent e)
    {



        if(GUIMain.getNumPlayersCB().getValue()!=null) {
            numPlayers = Integer.parseInt(GUIMain.getNumPlayersCB().getValue().toString());
        }

        if(GUIMain.getGridChoiceCB().getValue()!=null) {
            String recordedValue = GUIMain.getGridChoiceCB().getValue().toString();
            choiceOfGrid = recordedValue.equals("9x6")?0:1;
        }

        GUIMain.get_gameEngine().set_numPlayers(numPlayers);
        GUIMain.get_gameEngine().set_gridSize(choiceOfGrid);

        //Populating the HashMap with the default values

        GUIMain.getPlayercolor().put(1,ColorUtil.colorToHex(GUIMain.getPlayer_1().getValue()));
        GUIMain.getPlayercolor().put(2,ColorUtil.colorToHex(GUIMain.getPlayer_2().getValue()));
        GUIMain.getPlayercolor().put(3,ColorUtil.colorToHex(GUIMain.getPlayer_3().getValue()));
        GUIMain.getPlayercolor().put(4,ColorUtil.colorToHex(GUIMain.getPlayer_4().getValue()));
        GUIMain.getPlayercolor().put(5,ColorUtil.colorToHex(GUIMain.getPlayer_5().getValue()));
        GUIMain.getPlayercolor().put(6,ColorUtil.colorToHex(GUIMain.getPlayer_6().getValue()));
        GUIMain.getPlayercolor().put(7,ColorUtil.colorToHex(GUIMain.getPlayer_7().getValue()));
        GUIMain.getPlayercolor().put(8,ColorUtil.colorToHex(GUIMain.getPlayer_8().getValue()));

        GUIMain.get_gameEngine().setplayer_colors(GUIMain.getPlayercolor());

        try
        {
            GUIMain.get_gameEngine().startGame();
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }

        Button startButton = (Button) e.getSource();
        StackPane gp = (StackPane) startButton.getParent();
        Scene sc = gp.getScene();
        Stage stage = (Stage) sc.getWindow();

        stage.setScene(GUIMain.createGamePage());

        stage.show();

    }

}
