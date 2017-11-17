package GUIEngine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class endPageExitGUI implements EventHandler<javafx.event.ActionEvent> {

    static Stage stage;
    //@Override
    public void handle(ActionEvent e) {
        System.out.println("BUTTON HOME PAGE CLICKED");


        stage.setScene(GUIMain.createStartPage());

        Button btn = (Button) e.getSource();
        btn.getScene().getWindow().hide();



    }

    static void getInputs(Stage s){
        stage = s;
    }
}
