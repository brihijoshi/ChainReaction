package GUIEngine;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 260, 80);
        stage.setScene(scene);

        VBox vb = new VBox();

        Rectangle rect = new Rectangle (100, 40, 100, 100);
        rect.setArcHeight(50);
        rect.setArcWidth(50);
        rect.setFill(Color.VIOLET);

        final Duration SEC_2 = Duration.millis(10000);
        final Duration SEC_3 = Duration.millis(10000);

        FadeTransition ft = new FadeTransition(SEC_3);
        ft.setFromValue(1.0f);
        ft.setToValue(0.3f);

        ft.setAutoReverse(true);
        TranslateTransition tt = new TranslateTransition(SEC_2);
        tt.setFromX(-100f);
        tt.setToX(100f);

        tt.setAutoReverse(true);
        RotateTransition rt = new RotateTransition(SEC_3);
        rt.setByAngle(180f);

        rt.setAutoReverse(true);
        ScaleTransition st = new ScaleTransition(SEC_2);
        st.setByX(1.5f);
        st.setByY(1.5f);

        st.setAutoReverse(true);

        ParallelTransition pt = new ParallelTransition(rect, ft, tt, rt, st);
        pt.play();
        vb.getChildren().add(rect);

        scene.setRoot(vb);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}