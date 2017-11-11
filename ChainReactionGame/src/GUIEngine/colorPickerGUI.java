package GUIEngine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class colorPickerGUI implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        System.out.println(GUIMain.getPlayer_1().getValue()); //to be used to compare cell color

        ColorPicker source = (ColorPicker) event.getSource();

        for (int i = 0; i < 8; i++) {
            if(source.getValue().equals(GUIMain.getArray_CP().get(i).getValue()) && !source.equals(GUIMain.getArray_CP().get(i))) {
                Popup errorPopup = new Popup();
                errorPopup.setAutoHide(true);
                errorPopup.setX(300);
                errorPopup.setY(200);
                errorPopup.getContent().addAll(new Label("Wrong color selected"));
                Double posX = new Double(300);
                GridPane parent = (GridPane) source.getParent();
                Scene scene = (Scene) parent.getScene();
                Stage stage = (Stage) scene.getWindow();
                

                errorPopup.show(stage);

                source.setValue(Color.LAVENDER);
            }
            else{
                System.out.println("no probs");
            }
        }

    }
}
