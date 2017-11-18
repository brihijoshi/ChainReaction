package GUIEngine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * An {@link EventHandler} class associated with the Settings button in the home page
 *
 *  @author adsrc
 *  @author brihijoshi
 *  @version 1.0
 *
 */

public class settingsButtonGUI implements EventHandler<ActionEvent> {
    private int numPlayers = 2;
    private int choiceOfGrid = 0;

    /**
     * A function that reads the <code>_gridSize</code> and <code>_numPlayers</code> from the
     * {@link ComboBox} present in the home page and generates <code>_numPlayers</code> number
     * of active {@link ColorPicker} objects. In case no option is selected, a default value
     * of <code>0</code> is kept for <code>_gridSize</code> and <code>2</code> is kept for
     * <code>_numPlayers</code>.
     *
     * @param e the {@link ActionEvent} of the Settings button clicked
     */

    @Override
    public void handle(ActionEvent e) {


        if (GUIMain.getNumPlayersCB().getValue() != null) {
            numPlayers = Integer.parseInt(GUIMain.getNumPlayersCB().getValue().toString());
        }

        if (GUIMain.getGridChoiceCB().getValue() != null) {
            String recordedValue = GUIMain.getGridChoiceCB().getValue().toString();
            choiceOfGrid = recordedValue.equals("9x6") ? 0 : 1;
        }

        GUIMain.get_gameEngine().set_numPlayers(numPlayers);
        GUIMain.get_gameEngine().set_gridSize(choiceOfGrid);


        Button settingsButton = (Button) e.getSource();
        StackPane gp = (StackPane) settingsButton.getParent();
        Scene sc = gp.getScene();
        Stage stage = (Stage) sc.getWindow();

        stage.setScene(GUIMain.createSettingsPage());


    }

}
