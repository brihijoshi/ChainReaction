package GUIEngine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.action.Action;

public class colorPickerGUI implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        System.out.println(GUIMain.getPlayer_1().getValue()); //to be used to compare cell color

        ColorPicker source = (ColorPicker) event.getSource();

        for (int i = 0; i < 8; i++) {
            if(source.getValue().equals(GUIMain.getArray_CP().get(i).getValue()) && !source.equals(GUIMain.getArray_CP().get(i))) {
//                Popup errorPopup = new Popup();
//                errorPopup.setAutoHide(true);
//                errorPopup.setX(300);
//                errorPopup.setY(200);
//                errorPopup.getContent().addAll(new Label("Wrong color selected"));
//                Double posX = new Double(300);
//                GridPane parent = (GridPane) source.getParent();
//                Scene scene = (Scene) parent.getScene();
//                Stage stage = (Stage) scene.getWindow();
//
//
//                errorPopup.show(stage);
//
//                source.setValue(Color.LAVENDER);

//                NotificationPane notificationPane = new NotificationPane();
//                notificationPane.getStyleClass().add(NotificationPane.STYLE_CLASS_DARK);
//                notificationPane.setText("Same color selected!!");
//                notificationPane.getActions().add(new Action() {
//                    @Override
//
//                    public void handle(ActionEvent e){
//                        notificationPane.hide();
//                    }
//                });


//                Notifications.create()
//                        .title("Same color selected!")
//                        .text("Please select some other color.")
//                        .showWarning();

//                StackPane stp = new StackPane();
//                Label lb= new Label("Same color selected!!");
//                stp.getChildren().add(lb);
//
//                PopOver popOver = new PopOver(source);
//                popOver.setDetachable(true);
//                popOver.setDetached(true);
//                popOver.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
//                popOver.setContentNode(stp);
//                popOver.setTitle("ERROR");
//                popOver.show(source);



            }
            else{
                System.out.println("no probs");
            }
        }

    }
}
