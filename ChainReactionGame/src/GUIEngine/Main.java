package GUIEngine;

import GameEngine.Grid;
import javafx.animation.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {



    private static Button startButton;
    private static Button resumeButton;
    private static Button settingsButton;


    public static Button getStartButton() {
        return startButton;
    }

    public static Button getResumeButton() {
        return resumeButton;
    }

    public static Button getSettingsButton() {
        return settingsButton;
    }


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
        for(int i=0;i<numRows;i++){
            for(int j=0;j<numColumns;j++){

                StackPane cellContainer = new StackPane();
                cellContainer.setBorder(makeBorder(color));
                cellContainer.setOnMouseClicked(new turnGUI());
                Group cell = new Group();
                cell.setPickOnBounds(false);
                StackPane.setMargin(cell, new Insets(2,2,2,2));
                cellContainer.getChildren().add(cell);
                root.add(cellContainer, j,i);

            }
        }

    }

    public Border makeBorder(Color color){
        // changes colour of the stackpane
        return new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    }

    public void changeGridColor(GridPane grid, Color color){
        //To be done when we store the Array of the grid objects so that we can retrieve the current orbs and then change the colour

        ObservableList<Node> cells = grid.getChildren();
        for (int i = 1; i < cells.size(); i++) {
            cells.get(i).setStyle("-fx-border-color: #" + color.toString().substring(2));
        }


    }

    public void addOrbAndAnimate(GridPane root, int row, int column, int numSpheres, Color color){
        //To be done when we store the Array of the grid objects so that we can retrieve the current orbs and then replace it
        //To test it on the present situation only
        StackPane cellContainer = new StackPane();
        cellContainer.setBorder(makeBorder(Color.RED));
        cellContainer.setOnMouseClicked(new turnGUI());
        Group cell = new Group();
        cell.setPickOnBounds(false);


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

        final Rotate rotationTransform = new Rotate(0, 0, 0);
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

    public Scene createStartPage() {

        // Instantiating the buttons
        startButton = new Button("  Start  ");
        resumeButton = new Button("Resume");
        settingsButton = new Button("Settings");

        // Customising the buttons
        startButton.setStyle("-fx-background-color: chartreuse; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 20px; -fx-font-weight: bold;");
        resumeButton.setStyle("-fx-background-color: cornflowerblue; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 20px; -fx-font-weight: bold;");
        settingsButton.setStyle("-fx-background-color: coral; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 20px; -fx-font-weight: bold;");

        StackPane root = new StackPane();

        //Making the drop down menus
        ObservableList<String> grid_options = FXCollections.observableArrayList("9x6", "15x10");
        ComboBox comboBox_grid=new ComboBox(grid_options);


        ObservableList<String> player_options = FXCollections.observableArrayList("2", "3", "4", "5", "6", "7", "8");
        ComboBox comboBox_player=new ComboBox(player_options);

        //Making the Grid for the home page
        GridPane home_grid=new GridPane();

        RowConstraints rc = new RowConstraints(70);
        rc.setVgrow(Priority.ALWAYS);
        rc.setValignment(VPos.CENTER);
        ColumnConstraints cc = new ColumnConstraints(80);
        cc.setHgrow(Priority.ALWAYS);
        cc.setHalignment(HPos.CENTER);
        root.setAlignment(Pos.CENTER);
        home_grid.setAlignment(Pos.CENTER);
        home_grid.setHgap(5);
        home_grid.setVgap(5);

        for(int i=0;i<10;i++){
            home_grid.getRowConstraints().add(rc);

        }

        for(int j=0;j<12;j++) {
            home_grid.getColumnConstraints().add(cc);
        }

        StackPane startButton_sp = new StackPane(startButton);
        StackPane resumeButton_sp = new StackPane(resumeButton);
        StackPane settingsButton_sp = new StackPane(settingsButton);

        Label title = new Label("Chain Reaction");
        title.setStyle("-fx-background-color: black; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 100px; -fx-font-weight: bold; -fx-text-fill: red;");

        GridPane.setHalignment(startButton_sp, HPos.CENTER);
        GridPane.setHalignment(resumeButton_sp, HPos.CENTER);
        GridPane.setHalignment(settingsButton_sp, HPos.CENTER);
        GridPane.setHalignment(comboBox_grid, HPos.CENTER);


        comboBox_grid.setStyle("-fx-font-size: 20px; -fx-background-color: black; -fx-border-color: white;");
        comboBox_player.setStyle("-fx-font-size: 20px; -fx-background-color: black; -fx-border-color: white;");
        home_grid.add(startButton_sp,2,5,2,1);
        home_grid.add(comboBox_grid,5,5,2,1);
        home_grid.add(comboBox_player,8,5,2,1);
        home_grid.add(resumeButton_sp,4,7,2,1);
        home_grid.add(settingsButton_sp, 6, 7,2,1);
        home_grid.setStyle("-fx-background-color: black");
        home_grid.add(title, 1, 1, 10,2);

        //home_grid.setGridLinesVisible(true);
        //root.getChildren().add(home_grid);

        return new Scene(home_grid, 1200, 1000, Color.BLACK);



    }

    public Scene createSettingsPage() {

        GridPane root = new GridPane();


        return null;
    }


    @Override
    public void start(Stage primaryStage) throws Exception{


        primaryStage.setScene(createStartPage());
        primaryStage.show();


//        GridPane root = new GridPane();
//
//        setEmptyGrid(root, 9, 6, Color.RED);
//
//
//        addOrbAndAnimate(root, 0, 0, 2, Color.RED);
//
//
//        addOrbAndAnimate(root, 1, 1, 3, Color.RED);
//
//        changeGridColor(root, Color.ROSYBROWN);
//
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 800, 1000, Color.BLACK));
//        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }

}

class turnGUI implements EventHandler<MouseEvent> {



     @Override
     public void handle(MouseEvent e) {


        /**
         *  Type reference:
         *
         *  0 - Corner
         *  1 - Edge
         *  2 - Normal
         *
         * **/

        System.out.println("Mouse click detected");

        StackPane source = (StackPane) e.getSource();
        GridPane grid = (GridPane) source.getParent();

        int cur_orbs = source.getChildren().size();
        int new_orbs = cur_orbs + 1;

        ObservableList<Node> cells = grid.getChildren();

        int position = cells.indexOf(source);
        int gridSize = cells.size();

        ObservableList<Node> orbs = source.getChildren();
        PathTransition pt = new PathTransition();


     }


}

