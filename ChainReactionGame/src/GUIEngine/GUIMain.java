package GUIEngine;

import GameEngine.GameEngine;
import GameEngine.Grid;
import GameEngine.Player;
import javafx.animation.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class GUIMain extends Application {



    private static int _numRows;
    private static int _numCols;

    private static GameEngine _gameEngine;

    private static Button startButton;
    private static Button resumeButton;
    private static Button settingsButton;
    private static Button instructionsButton;
    private static Button redoButton;
    private static Button homeButton;

    private static boolean end_shown = false;

    private static ComboBox numPlayersCB;
    private static ComboBox gridChoiceCB;

    private static ArrayList<ColorPicker> array_CP = new ArrayList<>(8);


    private static ColorPicker player_1 = new ColorPicker(Color.RED);
    private static ColorPicker player_2 = new ColorPicker(Color.BLUE);
    private static ColorPicker player_3 = new ColorPicker(Color.GREEN);
    private static ColorPicker player_4 = new ColorPicker(Color.YELLOW);
    private static ColorPicker player_5 = new ColorPicker(Color.ORANGE);
    private static ColorPicker player_6 = new ColorPicker(Color.PINK);
    private static ColorPicker player_7 = new ColorPicker(Color.BROWN);
    private static ColorPicker player_8 = new ColorPicker(Color.PURPLE);


    private static MediaPlayer mediaPlayer;

    private static String currentPlayer;
    private static HashMap<Integer, String> playercolor;


    public static int get_numRows() {
        return _numRows;
    }

    public static void set_numRows(int _numRows) {
        GUIMain._numRows = _numRows;
    }

    public static int get_numCols() {
        return _numCols;
    }

    public static void set_numCols(int _numCols) {
        GUIMain._numCols = _numCols;
    }

    public static GameEngine get_gameEngine() {
        return _gameEngine;
    }

    public static void set_gameEngine(GameEngine _gameEngine) {
        GUIMain._gameEngine = _gameEngine;
    }

    public static Button getStartButton() {
        return startButton;
    }

    public static void setStartButton(Button startButton) {
        GUIMain.startButton = startButton;
    }

    public static Button getResumeButton() {
        return resumeButton;
    }

    public static void setResumeButton(Button resumeButton) {
        GUIMain.resumeButton = resumeButton;
    }

    public static Button getSettingsButton() {
        return settingsButton;
    }

    public static void setSettingsButton(Button settingsButton) {
        GUIMain.settingsButton = settingsButton;
    }

    public static Button getInstructionsButton() {
        return instructionsButton;
    }

    public static void setInstructionsButton(Button instructionsButton) {
        GUIMain.instructionsButton = instructionsButton;
    }

    public static Button getRedoButton() {
        return redoButton;
    }

    public static void setRedoButton(Button redoButton) {
        GUIMain.redoButton = redoButton;
    }

    public static Button getHomeButton() {
        return homeButton;
    }

    public static void setHomeButton(Button homeButton) {
        GUIMain.homeButton = homeButton;
    }

    public static boolean isEnd_shown() {
        return end_shown;
    }

    public static void setEnd_shown(boolean end_shown) {
        GUIMain.end_shown = end_shown;
    }

    public static ComboBox getNumPlayersCB() {
        return numPlayersCB;
    }

    public static void setNumPlayersCB(ComboBox numPlayersCB) {
        GUIMain.numPlayersCB = numPlayersCB;
    }

    public static ComboBox getGridChoiceCB() {
        return gridChoiceCB;
    }

    public static void setGridChoiceCB(ComboBox gridChoiceCB) {
        GUIMain.gridChoiceCB = gridChoiceCB;
    }

    public static ArrayList<ColorPicker> getArray_CP() {
        return array_CP;
    }

    public static void setArray_CP(ArrayList<ColorPicker> array_CP) {
        GUIMain.array_CP = array_CP;
    }

    public static ColorPicker getPlayer_1() {
        return player_1;
    }

    public static void setPlayer_1(ColorPicker player_1) {
        GUIMain.player_1 = player_1;
    }

    public static ColorPicker getPlayer_2() {
        return player_2;
    }

    public static void setPlayer_2(ColorPicker player_2) {
        GUIMain.player_2 = player_2;
    }

    public static ColorPicker getPlayer_3() {
        return player_3;
    }

    public static void setPlayer_3(ColorPicker player_3) {
        GUIMain.player_3 = player_3;
    }

    public static ColorPicker getPlayer_4() {
        return player_4;
    }

    public static void setPlayer_4(ColorPicker player_4) {
        GUIMain.player_4 = player_4;
    }

    public static ColorPicker getPlayer_5() {
        return player_5;
    }

    public static void setPlayer_5(ColorPicker player_5) {
        GUIMain.player_5 = player_5;
    }

    public static ColorPicker getPlayer_6() {
        return player_6;
    }

    public static void setPlayer_6(ColorPicker player_6) {
        GUIMain.player_6 = player_6;
    }

    public static ColorPicker getPlayer_7() {
        return player_7;
    }

    public static void setPlayer_7(ColorPicker player_7) {
        GUIMain.player_7 = player_7;
    }

    public static ColorPicker getPlayer_8() {
        return player_8;
    }

    public static void setPlayer_8(ColorPicker player_8) {
        GUIMain.player_8 = player_8;
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
        GUIMain.mediaPlayer = mediaPlayer;
    }

    public static String getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(String currentPlayer) {
        GUIMain.currentPlayer = currentPlayer;
    }

    public static HashMap<Integer, String> getPlayercolor() {
        return playercolor;
    }

    public static void setPlayercolor(HashMap<Integer, String> playercolor) {
        GUIMain.playercolor = playercolor;
    }





    public static BorderPane setEmptyGrid(GridPane root, int numRows, int numColumns, Color color) {

        BorderPane bp = new BorderPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(5);
        root.setVgap(5);
        root.setGridLinesVisible(true);

        RowConstraints rc = new RowConstraints(80);
        ColumnConstraints cc = new ColumnConstraints(80);


        if (numRows == 9) {

            rc.setMaxHeight(78);
            rc.setVgrow(Priority.ALWAYS);
            cc.setMaxWidth(78);
            cc.setHgrow(Priority.ALWAYS);
        }
        else {
            rc.setMaxHeight(45);
            rc.setVgrow(Priority.ALWAYS);
            cc.setMaxWidth(45);
            cc.setHgrow(Priority.ALWAYS);
        }


        for (int i = 0; i < numRows; i++) {
            root.getRowConstraints().add(rc);

        }

        for (int j = 0; j < numColumns; j++) {
            root.getColumnConstraints().add(cc);
        }
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {

                StackPane cellContainer = new StackPane();
                cellContainer.setBorder(makeBorder(color));
                cellContainer.setOnMouseClicked(new turnGUI());
                Group cell = new Group();
                cell.setPickOnBounds(false);
                StackPane.setMargin(cell, new Insets(2, 2, 2, 2));
                cellContainer.getChildren().add(cell);
                root.add(cellContainer, j, i);

            }
        }

        bp.setCenter(root);

        MenuBar mBar = new MenuBar();
        Menu menu = new Menu("Options");
        menu.getItems().addAll(new MenuItem("Restart"), new MenuItem("Exit"));
        menu.getItems().get(0).setOnAction(new restartButtonGUI());
        menu.getItems().get(1).setOnAction(new exitButtonGUI());
        mBar.setStyle("-fx-font-size: 15px; -fx-background-color: whitesmoke; -fx-border-color: white; -fx-text-fill: white;");

        mBar.getMenus().add(menu);
        bp.setTop(mBar);

        redoButton = new Button();
        ImageUtil img_util = new ImageUtil();
        Image redo_img = img_util.getImage("assets/undo.png");

        ImageView redoimg_view = new ImageView(redo_img);
        redoimg_view.setFitHeight(80);
        redoimg_view.setFitWidth(80);
        redoButton.setGraphic(redoimg_view);
        redoButton.setStyle("-fx-background-color: transparent; -fx-text-alignment: center; -fx-font-family: \"Helvetica\"; -fx-font-size: 20px; -fx-font-weight: bold;");
        redoButton.setOnAction(new undoButtonGUI());
        DropShadow shadow = new DropShadow();

        redoButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        redoButton.setEffect(shadow);
                    }
                });

        redoButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        redoButton.setEffect(null);
                    }
                });



        redoButton.setDisable(true);
        bp.setRight(redoButton);


        Image bg_image = img_util.getImage("assets/gamepage.png");

        Screen screen = Screen.getPrimary();

        Rectangle2D bounds = screen.getVisualBounds();

        bp.setBackground(new Background(new BackgroundImage(bg_image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(bounds.getWidth(), bounds.getHeight(), false,
                false, true, true))));

        return bp;

    }

    public static Border makeBorder(Color color) {
        return new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    }

    public static void changeGridColor(GridPane grid, Color color) {

        ObservableList<Node> cells = grid.getChildren();
        for (int i = 1; i < cells.size(); i++) {
            cells.get(i).setStyle("-fx-border-color: #" + color.toString().substring(2));
        }


    }

    public static void playExplode() {

        String musicFile = "assets/explode.mp3";

        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public static void playError() {

        String musicFile = "assets/error.mp3";

        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

    }


    public static void addOrbAndAnimate(GridPane root, int row, int column, int numSpheres, Color color) {


        int size = root.getChildren().size();
        int rowsize = 0;

        if (size == 55) {
            rowsize = 6;
        } else {
            rowsize = 10;
        }

        int removal = (row * rowsize) + column + 1;

        StackPane oldStackPane = (StackPane) root.getChildren().get(removal);

        StackPane cellContainer = new StackPane();
        cellContainer.setBorder(oldStackPane.getBorder());
        cellContainer.setOnMouseClicked(new turnGUI());
        Group cell = new Group();
        cell.setPickOnBounds(false);



        PhongMaterial smaterial = new PhongMaterial();
        smaterial.setDiffuseColor(color);


        if (size == 151) {
            switch (numSpheres) {
                case 1:

                    Sphere a = new Sphere(7.5);
                    a.setMaterial(smaterial);
                    cell.getChildren().addAll(a);
                    break;

                case 2:

                    a = new Sphere(7.5);
                    Sphere b = new Sphere(7.5);

                    b.setTranslateX(7.5);
                    b.setTranslateZ(7.5);

                    a.setMaterial(smaterial);
                    b.setMaterial(smaterial);
                    cell.getChildren().addAll(a, b);

                    rotateOrbs(cell);

                    break;

                case 3:

                    a = new Sphere(7.5);
                    b = new Sphere(7.5);
                    Sphere c = new Sphere(7.5);

                    b.setTranslateX(7.5);
                    b.setTranslateZ(7.5);
                    c.setTranslateX(3.75);
                    c.setTranslateY(-7.5);

                    a.setMaterial(smaterial);
                    b.setMaterial(smaterial);
                    c.setMaterial(smaterial);
                    cell.getChildren().addAll(a, b, c);

                    rotateOrbs(cell);
                    break;


            }
        } else {
            switch (numSpheres) {
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

                case 3:

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
        }

        GridPane.setHalignment(cell, HPos.CENTER);
        GridPane.setValignment(cell, VPos.CENTER);
        StackPane.setMargin(cell, new Insets(2, 2, 2, 2));
        cellContainer.getChildren().add(cell);
        root.add(cellContainer, column, row);
        root.getChildren().remove(cellContainer);
        root.getChildren().set(removal, cellContainer);



    }

    public static void rotateOrbs(Group cell) {

        final Rotate rotationTransform = new Rotate(0, 0, 0);
        cell.getTransforms().add(rotationTransform);

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



    public static boolean checkEndGame() {


        int count = 0;
        for (int i = 0; i < get_gameEngine().get_gc().get_players().size(); i++) {
            if (get_gameEngine().get_gc().get_players().get(i).get_isAlive()) {
                count++;
            }
        }


        return (count == 1);
    }

    public static Scene createStartPage() {


        startButton = new Button("  Start  ");
        startButton.setOnAction(new startButtonGUI());
        resumeButton = new Button("Resume");
        resumeButton.setOnAction(new resumeButtonGUI());
        settingsButton = new Button("Settings");
        settingsButton.setOnAction(new settingsButtonGUI());
        instructionsButton = new Button("How to Play?");
        instructionsButton.setOnAction(new instructionsButtonGUI());


        DropShadow shadow = new DropShadow();


        try {
            if (!GameEngine.checkResume()) {

                resumeButton.setDisable(true);
            } else {
                resumeButton.setDisable(false);
            }
        } catch (Exception v) {
            v.printStackTrace();
        }


        startButton.setStyle("-fx-background-color: #b2d969; -fx-text-alignment: center; -fx-font-family: \"Oxygen Mono\"; -fx-font-size: 20px; -fx-font-weight: bolder;");

        startButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        startButton.setEffect(shadow);
                    }
                });

        startButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        startButton.setEffect(null);
                    }
                });


        resumeButton.setStyle("-fx-background-color: #6069a3; -fx-text-alignment: center; -fx-font-family: \"Oxygen Mono\"; -fx-font-size: 20px; -fx-font-weight: bolder;");

        resumeButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        resumeButton.setEffect(shadow);
                    }
                });

        resumeButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        resumeButton.setEffect(null);
                    }
                });


        settingsButton.setStyle("-fx-background-color: coral; -fx-text-alignment: center; -fx-font-family: \"Oxygen Mono\"; -fx-font-size: 20px; -fx-font-weight: bolder;");

        settingsButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        settingsButton.setEffect(shadow);
                    }
                });

        settingsButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        settingsButton.setEffect(null);
                    }
                });
        instructionsButton.setStyle("-fx-background-color: #fff587; -fx-text-alignment: center; -fx-font-family: \"Oxygen Mono\"; -fx-font-size: 20px; -fx-font-weight: bolder;");

        instructionsButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        instructionsButton.setEffect(shadow);
                    }
                });

        instructionsButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        instructionsButton.setEffect(null);
                    }
                });

        StackPane root = new StackPane();

        ObservableList<String> grid_options = FXCollections.observableArrayList("9x6", "15x10");
        gridChoiceCB = new ComboBox(grid_options);
        String gridChoiceCBValue = GUIMain.get_gameEngine().get_gridSize() == 0 ? "9x6" : "15x10";
        gridChoiceCB.setValue(gridChoiceCBValue);


        ObservableList<String> player_options = FXCollections.observableArrayList("2", "3", "4", "5", "6", "7", "8");
        numPlayersCB = new ComboBox<>(player_options);
        numPlayersCB.setValue(GUIMain.get_gameEngine().get_numPlayers());

        GridPane homeGrid = new GridPane();

        RowConstraints rc = new RowConstraints(70);
        rc.setVgrow(Priority.ALWAYS);
        rc.setValignment(VPos.CENTER);
        ColumnConstraints cc = new ColumnConstraints(80);
        cc.setHgrow(Priority.ALWAYS);
        cc.setHalignment(HPos.CENTER);

        root.setAlignment(Pos.CENTER);

        homeGrid.setAlignment(Pos.CENTER);
        homeGrid.setHgap(5);
        homeGrid.setVgap(5);

        for (int i = 0; i < 10; i++) {
            homeGrid.getRowConstraints().add(rc);

        }

        for (int j = 0; j < 12; j++) {
            homeGrid.getColumnConstraints().add(cc);
        }

        StackPane startButton_sp = new StackPane(startButton);
        StackPane resumeButton_sp = new StackPane(resumeButton);
        StackPane settingsButton_sp = new StackPane(settingsButton);
        StackPane instructionsButton_sp = new StackPane(instructionsButton);

        Label title = new Label("Chain Reaction");
        title.setStyle("-fx-background-color: transparent; -fx-text-alignment: center; -fx-font-family: \"Press Start 2P\"; -fx-font-size: 60px; -fx-font-weight: bolder; -fx-text-fill: #f4ab15;");

        Label gridstyleLabel = new Label("Grid Size:");
        gridstyleLabel.setStyle("-fx-background-color: transparent; -fx-text-alignment: center; -fx-font-family: \"Oxygen Mono\"; -fx-font-size: 20px; -fx-font-weight: bolder; -fx-text-fill: #ffcbf0;");

        Label numPlayersLabel = new Label("Number of Players:");
        numPlayersLabel.setStyle("-fx-background-color: transparent; -fx-text-alignment: center; -fx-font-family: \"Oxygen Mono\"; -fx-font-size: 20px; -fx-font-weight: bolder; -fx-text-fill: #ffcbf0;");

        GridPane.setHalignment(startButton_sp, HPos.CENTER);
        GridPane.setHalignment(resumeButton_sp, HPos.CENTER);
        GridPane.setHalignment(settingsButton_sp, HPos.CENTER);
        GridPane.setHalignment(gridChoiceCB, HPos.CENTER);


        gridChoiceCB.setStyle("-fx-font-size: 20px; -fx-background-color: #fffded; -fx-border-color: #ffbe6b;");
        numPlayersCB.setStyle("-fx-font-size: 20px; -fx-background-color: #fffded; -fx-border-color: #ffbe6b;");
        gridChoiceCB.setVisibleRowCount(3);
        numPlayersCB.setVisibleRowCount(3);


        homeGrid.add(startButton_sp, 2, 5, 2, 1);
        homeGrid.add(gridstyleLabel, 5, 4, 2, 1);
        homeGrid.add(gridChoiceCB, 5, 5, 2, 1);
        homeGrid.add(numPlayersLabel, 8, 4, 3, 1);
        homeGrid.add(numPlayersCB, 8, 5, 2, 1);
        homeGrid.add(resumeButton_sp, 4, 7, 2, 1);
        homeGrid.add(settingsButton_sp, 6, 7, 2, 1);
        homeGrid.add(instructionsButton_sp, 4, 8, 4, 1);

        ImageUtil imageUtil = new ImageUtil();
        Image bgImage = imageUtil.getImage("assets/startpage1.jpg");

        Screen screen = Screen.getPrimary();

        Rectangle2D bounds = screen.getVisualBounds();

        homeGrid.setBackground(new Background(new BackgroundImage(bgImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, new BackgroundSize(bounds.getWidth(),
                bounds.getHeight(), false, false, true, true))));

        homeGrid.add(title, 1, 1, 10, 2);

        Scene scene = new Scene(homeGrid, 1200, 1000);

        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Press+Start+2P");
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Oxygen+Mono");


        return scene;


    }

    public static Scene createSettingsPage() {


        homeButton = new Button();
        ImageUtil img_util = new ImageUtil();
        Image back_img = img_util.getImage("assets/back.png");
        ImageView backimg_view = new ImageView(back_img);
        backimg_view.setFitHeight(50);
        backimg_view.setFitWidth(50);


        DropShadow shadow = new DropShadow();

        homeButton.setGraphic(backimg_view);
        homeButton.setOnAction(new homeButtonGUI());
        homeButton.setBackground(Background.EMPTY);

        homeButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        homeButton.setEffect(shadow);
                    }
                });
        //Removing the shadow when the mouse cursor is off
        homeButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        homeButton.setEffect(null);
                    }
                });

        StackPane homeButton_sp = new StackPane(homeButton);


        StackPane root = new StackPane();




        playercolor.put(1, "#" + Integer.toHexString(player_1.getValue().hashCode()));
        player_1.setStyle("-fx-background-color: #669999 ;-fx-background-radius: 0 15 15 0;");
        playercolor.put(2, "#" + Integer.toHexString(player_2.getValue().hashCode()));
        player_2.setStyle("-fx-background-color: #669999 ;-fx-background-radius: 0 15 15 0;");
        playercolor.put(3, "#" + Integer.toHexString(player_3.getValue().hashCode()));
        player_3.setStyle("-fx-background-color: #669999 ;-fx-background-radius: 0 15 15 0;");
        playercolor.put(4, "#" + Integer.toHexString(player_4.getValue().hashCode()));
        player_4.setStyle("-fx-background-color: #669999 ;-fx-background-radius: 0 15 15 0;");
        playercolor.put(5, "#" + Integer.toHexString(player_5.getValue().hashCode()));
        player_5.setStyle("-fx-background-color: #669999 ;-fx-background-radius: 0 15 15 0;");
        playercolor.put(6, "#" + Integer.toHexString(player_6.getValue().hashCode()));
        player_6.setStyle("-fx-background-color: #669999 ;-fx-background-radius: 0 15 15 0;");
        playercolor.put(7, "#" + Integer.toHexString(player_7.getValue().hashCode()));
        player_7.setStyle("-fx-background-color: #669999 ;-fx-background-radius: 0 15 15 0;");
        playercolor.put(8, "#" + Integer.toHexString(player_8.getValue().hashCode()));
        player_8.setStyle("-fx-background-color: #669999 ;-fx-background-radius: 0 15 15 0;");

        _gameEngine.setplayer_colors(playercolor);


        GridPane setting_grid = new GridPane();

        RowConstraints rc = new RowConstraints(70);
        rc.setVgrow(Priority.ALWAYS);
        rc.setValignment(VPos.CENTER);
        ColumnConstraints cc = new ColumnConstraints(80);
        cc.setHgrow(Priority.ALWAYS);
        cc.setHalignment(HPos.CENTER);
        root.setAlignment(Pos.CENTER);
        setting_grid.setAlignment(Pos.CENTER);
        setting_grid.setHgap(5);
        setting_grid.setVgap(5);

        for (int i = 0; i < 12; i++) {
            setting_grid.getRowConstraints().add(rc);

        }

        for (int j = 0; j < 14; j++) {
            setting_grid.getColumnConstraints().add(cc);
        }




        Label title = new Label("SETTINGS");
        title.setStyle("-fx-background-color: transparent; -fx-text-alignment: center; -fx-font-family: \"Oxygen Mono\"; -fx-font-size: 70px; -fx-font-weight: bolder; -fx-text-fill: #328009;");


        Label player1_label = new Label("Player 1:");
        player1_label.setStyle("-fx-background-color: transparent; -fx-text-alignment: center; -fx-font-family: \"Oxygen Mono\"; -fx-font-size: 20px; -fx-font-weight: bolder; -fx-text-fill: #08807b;");


        Label player2_label = new Label("Player 2:");
        player2_label.setStyle("-fx-background-color: transparent; -fx-text-alignment: center; -fx-font-family: \"Oxygen Mono\"; -fx-font-size: 20px; -fx-font-weight: bolder; -fx-text-fill: #08807b;");


        Label player3_label = new Label("Player 3:");
        player3_label.setStyle("-fx-background-color: transparent; -fx-text-alignment: center; -fx-font-family: \"Oxygen Mono\"; -fx-font-size: 20px; -fx-font-weight: bolder; -fx-text-fill: #08807b;");


        Label player4_label = new Label("Player 4:");
        player4_label.setStyle("-fx-background-color: transparent; -fx-text-alignment: center; -fx-font-family: \"Oxygen Mono\"; -fx-font-size: 20px; -fx-font-weight: bolder; -fx-text-fill: #08807b;");


        Label player5_label = new Label("Player 5:");
        player5_label.setStyle("-fx-background-color: transparent; -fx-text-alignment: center; -fx-font-family: \"Oxygen Mono\"; -fx-font-size: 20px; -fx-font-weight: bolder; -fx-text-fill: #08807b;");


        Label player6_label = new Label("Player 6:");
        player6_label.setStyle("-fx-background-color: transparent; -fx-text-alignment: center; -fx-font-family: \"Oxygen Mono\"; -fx-font-size: 20px; -fx-font-weight: bolder; -fx-text-fill: #08807b;");


        Label player7_label = new Label("Player 7:");
        player7_label.setStyle("-fx-background-color: transparent; -fx-text-alignment: center; -fx-font-family: \"Oxygen Mono\"; -fx-font-size: 20px; -fx-font-weight: bolder; -fx-text-fill: #08807b;");


        Label player8_label = new Label("Player 8:");
        player8_label.setStyle("-fx-background-color: transparent; -fx-text-alignment: center; -fx-font-family: \"Oxygen Mono\"; -fx-font-size: 20px; -fx-font-weight: bolder; -fx-text-fill: #08807b;");



        Image bg_image = img_util.getImage("assets/settingspage.jpg");

        Screen screen = Screen.getPrimary();

        Rectangle2D bounds = screen.getVisualBounds();

        setting_grid.setBackground(new Background(new BackgroundImage(bg_image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, new BackgroundSize(bounds.getWidth(), bounds.getHeight(), false, false, true, true))));


        setting_grid.add(title, 2, 1, 10, 2);

        setting_grid.add(player1_label, 3, 3, 2, 1);
        setting_grid.add(player_1, 8, 3, 2, 1);

        setting_grid.add(player2_label, 3, 4, 2, 1);
        setting_grid.add(player_2, 8, 4, 2, 1);

        setting_grid.add(player3_label, 3, 5, 2, 1);
        setting_grid.add(player_3, 8, 5, 2, 1);

        setting_grid.add(player4_label, 3, 6, 2, 1);
        setting_grid.add(player_4, 8, 6, 2, 1);

        setting_grid.add(player5_label, 3, 7, 2, 1);
        setting_grid.add(player_5, 8, 7, 2, 1);

        setting_grid.add(player6_label, 3, 8, 2, 1);
        setting_grid.add(player_6, 8, 8, 2, 1);

        setting_grid.add(player7_label, 3, 9, 2, 1);
        setting_grid.add(player_7, 8, 9, 2, 1);

        setting_grid.add(player8_label, 3, 10, 2, 1);
        setting_grid.add(player_8, 8, 10, 2, 1);


        setting_grid.add(homeButton_sp, 0, 1, 1, 1);

        ArrayList<ColorPicker> cp_array = new ArrayList<>();
        cp_array.add(player_1);
        cp_array.add(player_2);
        cp_array.add(player_3);
        cp_array.add(player_4);
        cp_array.add(player_5);
        cp_array.add(player_6);
        cp_array.add(player_7);
        cp_array.add(player_8);


        for (int i = 7; i > _gameEngine.get_numPlayers() - 1; i--) {
            cp_array.get(i).setDisable(true);
        }

        for (int i = 0; i < _gameEngine.get_numPlayers(); i++) {
            cp_array.get(i).setDisable(false);
        }


        Scene scene = new Scene(setting_grid, 1200, 1000);
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Oxygen+Mono");


        return scene;


    }

    public static void createEndPage(GridPane grid, Stage stage, int winner) {

        Stage winnerDialog = new Stage();

        String musicFile = "assets/win.mp3";

        ArrayList<Player> players = get_gameEngine().get_gc().get_players();

        Button restartButton = new Button("Play Again");
        restartButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {



                for (int i = 0; i < players.size(); i++) {
                    players.get(i).set_isAlive(true);
                    players.get(i).set_isKillable(false);
                    players.get(i).set_isActive(false);
                }

                players.get(0).set_isActive(true);
                players.get(0).set_isKillable(true);

                Color firstColor = Color.web(players.get(0).get_colour());

                for (int i = 0; i < get_numRows(); i++) {
                    for (int j = 0; j < get_numCols(); j++) {

                        addOrbAndAnimate(grid, i, j, 0, firstColor);
                        get_gameEngine().get_gc().get_grid().get_grid().get(i).get(j).set_currmass(0);


                    }
                }

                changeGridColor(grid, firstColor);



                setCurrentPlayer(ColorUtil.colorToHex(firstColor));
                getRedoButton().setDisable(true);
                getRedoButton().setDisable(true);

                stage.setScene(createGamePage());

                Button btn = (Button) event.getSource();
                btn.getScene().getWindow().hide();


            }
        });

        Button exitButton = new Button("Back to Home Page");
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {



                stage.setScene(createStartPage());

                Button btn = (Button) event.getSource();
                btn.getScene().getWindow().hide();
            }
        });

        restartButton.setStyle("-fx-background-color: #b2d969; -fx-text-alignment: center; -fx-font-family: \"Oxygen Mono\"; -fx-font-size: 20px; -fx-font-weight: bolder;");
        exitButton.setStyle("-fx-background-color: #b2d969; -fx-text-alignment: center; -fx-font-family: \"Oxygen Mono\"; -fx-font-size: 20px; -fx-font-weight: bolder;");
        exitButton.setWrapText(true);

        Label winner_label = new Label("Player " + winner + " won the game!");

        RowConstraints rc = new RowConstraints(50);
        rc.setValignment(VPos.CENTER);
        ColumnConstraints cc = new ColumnConstraints(100);
        cc.setHalignment(HPos.CENTER);


        GridPane endgame_grid = new GridPane();



        endgame_grid.add(winner_label, 2, 0, 4, 10);
        endgame_grid.add(restartButton, 0, 12, 2, 2);
        endgame_grid.add(exitButton, 6, 12, 2, 2);
        endgame_grid.setStyle("-fx-background-color: black;");
        restartButton.setAlignment(Pos.BOTTOM_LEFT);

        for (int i = 0; i < 15; i++) {
            endgame_grid.getRowConstraints().add(rc);
        }

        for (int i = 0; i < 8; i++) {
            endgame_grid.getColumnConstraints().add(cc);
        }

        winner_label.setStyle("-fx-background-color: transparent; -fx-text-alignment: center; -fx-font-family: \"Press Start 2P\"; -fx-font-size: 30px; -fx-font-weight: bolder; -fx-text-fill: #f4ab15; -fx-line-spacing: 1em;");
        winner_label.setWrapText(true);
        Scene winnerScene = new Scene(endgame_grid, 820, 750, Color.BLACK);

        winnerScene.getStylesheets().add("https://fonts.googleapis.com/css?family=Press+Start+2P");


        winnerDialog.setScene(winnerScene);
        winnerDialog.initOwner(stage);

        if (!end_shown) {
            winnerDialog.show();
            Media sound = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer1 = new MediaPlayer(sound);
            mediaPlayer1.play();
            mediaPlayer.setVolume(0);
            end_shown = true;
        }
        setEnd_shown(false);


    }


    public static Scene createGamePage() {

        if (_gameEngine.get_choice() == 0) {
            GridPane root = new GridPane();


            if (_gameEngine.get_gridSize() == 0) {
                _numRows = 9;
                _numCols = 6;
            } else {
                _numRows = 15;
                _numCols = 10;
            }

            Color firstplayer = Color.web(GUIMain.get_gameEngine().get_gc().get_players().get(0).get_colour());


            BorderPane bp = setEmptyGrid(root, _numRows, _numCols, firstplayer);


            GUIMain.setCurrentPlayer(GUIMain.get_gameEngine().get_gc().get_players().get(0).get_colour());


            Scene sc = new Scene(bp, 800, 1000, Color.BLACK);

            return sc;
        } else {

            GridPane root = new GridPane();


            if (_gameEngine.get_gridSize() == 0) {
                _numRows = 9;
                _numCols = 6;
            } else {
                _numRows = 15;
                _numCols = 10;
            }

            Color firstplayer = null;

            int i;
            for (i = 0; i < _gameEngine.get_numPlayers(); i++) {
                if (_gameEngine.get_gc().get_players().get(i).get_isActive()) {
                    firstplayer = Color.web(_gameEngine.get_gc().get_players().get(i).get_colour());
                    break;
                }
            }


            BorderPane bp = setEmptyGrid(root, _numRows, _numCols, firstplayer);
            Grid grid = _gameEngine.get_gc().get_grid();
            convertGridToGUI(root, grid);

            changeGridColor(root, firstplayer);


            GUIMain.setCurrentPlayer(_gameEngine.get_gc().get_players().get(i).get_colour());

            Scene sc = new Scene(bp, 800, 1000, Color.BLACK);

            return sc;


        }


    }


    public static void convertGridToGUI(GridPane gp, Grid g) {
        for (int i = 0; i < g.get_grid().size(); i++) {
            for (int j = 0; j < g.get_grid().get(0).size(); j++) {
                if (g.get_grid().get(i).get(j).get_currmass() != 0) {
                    Color clr = Color.web(g.get_grid().get(i).get(j).get_color());
                    int currmass = g.get_grid().get(i).get(j).get_currmass();
                    addOrbAndAnimate(gp, i, j, currmass, clr);
                }

            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        _gameEngine = new GameEngine(2, 0);
        Screen screen = Screen.getPrimary();

        Rectangle2D bounds = screen.getVisualBounds();

        playercolor = new HashMap<Integer, String>();

        array_CP.add(player_1);
        array_CP.add(player_2);
        array_CP.add(player_3);
        array_CP.add(player_4);
        array_CP.add(player_5);
        array_CP.add(player_6);
        array_CP.add(player_7);
        array_CP.add(player_8);

        primaryStage.setScene(createStartPage());
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    if (_gameEngine.get_gc() != null) {
                        if (!checkEndGame()) {
                            GUIMain.get_gameEngine().get_gc().saveGameState();
                        }
                    }

                } catch (Exception e2) {
                    e2.printStackTrace();
                }

            }
        });
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

}




