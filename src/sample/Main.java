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

    public void setEmptyGrid(GridPane root, int numRows, int numColumns, Color color){

        root.setAlignment(Pos.CENTER);
        root.setHgap(5);
        root.setVgap(5);
        root.setGridLinesVisible(true);

        RowConstraints rc = new RowConstraints(80);
        rc.setVgrow(Priority.ALWAYS);
        ColumnConstraints cc = new ColumnConstraints(80);
        cc.setHgrow(Priority.ALWAYS);

        for(int i=0;i<numRows;i++){
            root.getRowConstraints().add(rc);

        }

        for(int j=0;j<numColumns;j++) {
            root.getColumnConstraints().add(cc);
        }
        for(int i=0;i<9;i++){
            for(int j=0;j<6;j++){

                StackPane cellContainer = new StackPane();
                cellContainer.setBorder(getBorderColor(color));
                Group cell = new Group();
                StackPane.setMargin(cell, new Insets(2,2,2,2));
                cellContainer.getChildren().add(cell);
                root.add(cellContainer, j,i);

            }
        }
    }

    public Border getBorderColor(Color color){
        // changes colour of the stackpane
        return new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    }

    public void changeGridColor(GridPane grid, Color color){
        //To be done when we store the Array of the grid objects so that we can retrieve the current orbs and then change the colour
    }

    public void addOrbandAnimate(GridPane root, int row, int column, int numSpheres, Color color){
        //To be done when we store the Array of the grid objects so that we can retrieve the current orbs and then replace it
        //To test it on the present situation only
        StackPane cellContainer = new StackPane();
        cellContainer.setBorder(getBorderColor(Color.RED));
        Group cell = new Group();


        //To set the Sphere color
        PhongMaterial smaterial = new PhongMaterial();
        smaterial.setDiffuseColor(color);

        switch (numSpheres){
            case 1:

                Sphere a = new Sphere(12);
                a.setMaterial(smaterial);
                cell.getChildren().addAll(a);
                break;

            case 2:

                a = new Sphere(12);
                Sphere b = new Sphere(12);

                b.setTranslateX(12);
                b.setTranslateZ(12);

                a.setMaterial(smaterial);
                b.setMaterial(smaterial);
                cell.getChildren().addAll(a, b);

                rotateOrbs(cell);

                break;

            case  3:

                a = new Sphere(12);
                b = new Sphere(12);
                Sphere c = new Sphere(12);

                b.setTranslateX(12);
                b.setTranslateZ(12);
                c.setTranslateX(6);
                c.setTranslateY(-12);

                a.setMaterial(smaterial);
                b.setMaterial(smaterial);
                c.setMaterial(smaterial);
                cell.getChildren().addAll(a, b, c);

                rotateOrbs(cell);
                break;


        }

        GridPane.setHalignment(cell, HPos.CENTER);
        GridPane.setValignment(cell, VPos.CENTER);
        StackPane.setMargin(cell, new Insets(2,2,2,2));
        cellContainer.getChildren().add(cell);

        root.add(cellContainer, column, row);


    }

    public void rotateOrbs(Group cell){

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
    }





    @Override
    public void start(Stage primaryStage) throws Exception{

        GridPane root = new GridPane();

        setEmptyGrid(root, 9, 6, Color.RED);


        addOrbandAnimate(root, 0, 0, 3, Color.RED);


        addOrbandAnimate(root, 1, 1, 2, Color.RED);



        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 1000, Color.BLACK));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
