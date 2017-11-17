package GUIEngine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class homeButtonGUI implements EventHandler<ActionEvent> {

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