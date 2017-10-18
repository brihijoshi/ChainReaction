package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    // for help on transitions : https://gist.github.com/jewelsea/1475424


    @Override
    public void start(Stage primaryStage) throws Exception{

        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(5.5);
        root.setVgap(5.5);
        root.setGridLinesVisible(true);

        RowConstraints rc = new RowConstraints(80);
        rc.setVgrow(Priority.ALWAYS);
        ColumnConstraints cc = new ColumnConstraints(80);
        cc.setHgrow(Priority.ALWAYS);



        for(int i=0;i<9;i++){
            root.getRowConstraints().add(rc);

        }

        for(int j=0;j<6;j++) {

            root.getColumnConstraints().add(cc);

        }

        Border cellBorder = new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));

        StackPane cellContainer = new StackPane();
        cellContainer.setBorder(cellBorder);
        Group cell = new Group();
        Sphere a = new Sphere(12);
        Sphere b = new Sphere(12);
        Sphere c = new Sphere(12);





        b.setTranslateX(12);
        b.setTranslateZ(12);
        c.setTranslateX(6);
        c.setTranslateY(-12);


        PhongMaterial smaterial = new PhongMaterial();
        smaterial.setDiffuseColor(Color.RED);
        c.setMaterial(smaterial);
        a.setMaterial(smaterial);
        b.setMaterial(smaterial);
        cell.getChildren().addAll(a, b, c);


        final Rotate rotationTransform = new Rotate(0, 6, 0);
        cell.getTransforms().add(rotationTransform);

        // rotate a square using timeline attached to the rotation transform's angle property.
        final Timeline rotationAnimation = new Timeline();
        rotationAnimation.getKeyFrames()
                .add(
                        new KeyFrame(
                                Duration.seconds(5),
                                new KeyValue(
                                        rotationTransform.angleProperty(),
                                        360
                                )
                        )
                );
        rotationAnimation.setCycleCount(Animation.INDEFINITE);
        rotationAnimation.play();


        GridPane.setHalignment(cell, HPos.CENTER);
        GridPane.setValignment(cell, VPos.CENTER);
        StackPane.setMargin(cell, new Insets(2,2,2,2));
        cellContainer.getChildren().add(cell);
        root.add(cellContainer, 0,0);


        Group cell1 = new Group();
        Sphere a1 = new Sphere(12);
        Sphere b1 = new Sphere(12);
        Sphere c1 = new Sphere(12);

        b1.setTranslateX(12);
        b1.setTranslateZ(12);
        c1.setTranslateX(6);
        c1.setTranslateY(-12);


        PhongMaterial smaterial1 = new PhongMaterial();
        smaterial1.setDiffuseColor(Color.RED);
        c1.setMaterial(smaterial);
        a1.setMaterial(smaterial);
        b1.setMaterial(smaterial);
        cell1.getChildren().addAll(a1, b1, c1);


        final Rotate rotationTransform1 = new Rotate(0, 6, 0);
        cell1.getTransforms().add(rotationTransform1);

        // rotate a square using timeline attached to the rotation transform's angle property.
        final Timeline rotationAnimation1 = new Timeline();
        rotationAnimation1.getKeyFrames()
                .add(
                        new KeyFrame(
                                Duration.seconds(5),
                                new KeyValue(
                                        rotationTransform1.angleProperty(),
                                        360
                                )
                        )
                );
        rotationAnimation1.setCycleCount(Animation.INDEFINITE);
        rotationAnimation1.play();


        root.add(cell1, 1,1);






        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 1000, Color.BLACK));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
