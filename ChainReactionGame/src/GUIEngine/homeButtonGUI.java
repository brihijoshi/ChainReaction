package GUIEngine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  An {@link EventHandler} class associated with the Home button in the Settings page
 *
 *  @author brihijoshi
 *  @version 1.0
 */

public class homeButtonGUI implements EventHandler<ActionEvent> {

    /**
     * A function that shows a {@link PopOver} if the same {@link Color} is chosen for different players and does not
     * allow the exit of the page in that case
     *
     * @param e the {@link ActionEvent} of the home button clicked
     */

    @Override
    public void handle(ActionEvent e) {

        if (!duplicates(GUIMain.getArray_CP())) {
            Button homeButton = (Button) e.getSource();
            StackPane gp = (StackPane) homeButton.getParent();
            Scene sc = gp.getScene();
            Stage stage = (Stage) sc.getWindow();

            stage.setScene(GUIMain.createStartPage());

            stage.show();
        } else {
            Button source = (Button) e.getSource();

            StackPane stp = new StackPane();
            Label lb = new Label("Same color selected!!");
            stp.getChildren().add(lb);
            PopOver popOver = new PopOver(source);
            popOver.setDetachable(true);
            popOver.setDetached(true);
            popOver.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
            popOver.setContentNode(stp);
            popOver.setTitle("ERROR");
            popOver.show(source);
        }

    }

    /**
     * A function that makes use of a {@link HashSet} to store the unique values of the {@link ColorPicker} while iterating
     * over the {@link ArrayList} of {@link ColorPicker}. If a repetition occurs, exits the function returning <code>true</code>
     *
     * @param x an {@link ArrayList} of the {@link ColorPicker} representing the color of each player
     * @return a Boolean indicating whether the same color has been selected in any {@link ColorPicker}
     */

    public static boolean duplicates(ArrayList<ColorPicker> x) {
        Set<String> set = new HashSet<String>();

        Iterator iter = x.iterator();

        while(iter.hasNext()) {

            ColorPicker c = (ColorPicker) iter.next();
            if (set.contains(ColorUtil.colorToHex(c.getValue()))) {
                return true;
            } else {
                set.add(ColorUtil.colorToHex(c.getValue()));
            }

        }

        return false;
    }

}