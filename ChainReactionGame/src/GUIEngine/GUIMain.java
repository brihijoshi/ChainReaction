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

/**
 * The Main class that coordinates the GUI objects, GUI pages and the linking between the GUI and the backend.
 *
 *  @author adsrc
 *  @author brihijoshi
 *  @version 1.2
 *
 */


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

    /**
     * @return the number of rows
     */

    public static int get_numRows() {
        return _numRows;
    }

    /**
     * Sets the number of rows
     *
     * @param _numRows the num of rows obtained
     */
    public static void set_numRows(int _numRows) {
        GUIMain._numRows = _numRows;
    }

    /**
     * @return the number of columns
     */

    public static int get_numCols() {
        return _numCols;
    }

    /**
     * Sets the number of columns
     *
     * @param _numCols the number of columns obtained
     */

    public static void set_numCols(int _numCols) {
        GUIMain._numCols = _numCols;
    }

    /**
     * @return the {@link GameEngine} object associated with the GUI
     */

    public static GameEngine get_gameEngine() {
        return _gameEngine;
    }

    /**
     * Sets the {@link GameEngine} object associated with the GUI
     *
     * @param _gameEngine the {@link GameEngine} object associated with the GUI
     */

    public static void set_gameEngine(GameEngine _gameEngine) {
        GUIMain._gameEngine = _gameEngine;
    }

    /**
     * @return the {@link Button} associated with <code>startButton</code>
     */

    public static Button getStartButton() {
        return startButton;
    }

    /**
     * Set the {@link Button} associated with <code>startButton</code>
     * @param startButton the {@link Button} associated starting the game
     */

    public static void setStartButton(Button startButton) {
        GUIMain.startButton = startButton;
    }

    /**
     *
     * @return the {@link Button} associated with <code>resumeButton</code>
     */

    public static Button getResumeButton() {
        return resumeButton;
    }

    /**
     * Set the {@link Button} associated with <code>resumeButton</code>
     * @param resumeButton the {@link Button} associated with resuming the game
     */

    public static void setResumeButton(Button resumeButton) {
        GUIMain.resumeButton = resumeButton;
    }

    /**
     *
     * @return the {@link Button} associated with <code>settingsButton</code>
     */

    public static Button getSettingsButton() {
        return settingsButton;
    }

    /**
     * Set the {@link Button} associated with <code>settingsButton</code>
     * @param settingsButton the {@link Button} associated with going to the Settings page
     */

    public static void setSettingsButton(Button settingsButton) {
        GUIMain.settingsButton = settingsButton;
    }

    /**
     *
     * @return the {@link Button} associated with <code>instructionsButton</code>
     */

    public static Button getInstructionsButton() {
        return instructionsButton;
    }

    /**
     * Set the {@link Button} associated with <code>instructionsButton</code>
     * @param instructionsButton the {@link Button} associated for reading the game instructions
     */

    public static void setInstructionsButton(Button instructionsButton) {
        GUIMain.instructionsButton = instructionsButton;
    }

    /**
     *
     * @return the {@link Button} associated with <code>redoButton</code>
     */

    public static Button getRedoButton() {
        return redoButton;
    }

    /**
     * Set the {@link Button} associated with <code>redoButton</code>
     * @param redoButton the {@link Button} associated to Undo the last played move
     */

    public static void setRedoButton(Button redoButton) {
        GUIMain.redoButton = redoButton;
    }

    /**
     *
     * @return the {@link Button} associated with <code>homeButton</code>
     */

    public static Button getHomeButton() {
        return homeButton;
    }

    /**
     * Set the {@link Button} associated with <code>homeButton</code>

     * @param homeButton the {@link Button} used to get back to the Home Page from the Setting Page

     */


    public static void setHomeButton(Button homeButton) {
        GUIMain.homeButton = homeButton;
    }

    /**
     *
     * @return a Boolean that checks whether the end game page has been displayed
     */

    public static boolean isEnd_shown() {
        return end_shown;
    }

    /**
     * Set a Boolean <code>true</code> if the end page has been shown once
     * @param end_shown a Boolean that checks whether the end game page has been displayed
     */

    public static void setEnd_shown(boolean end_shown) {
        GUIMain.end_shown = end_shown;
    }

    /**
     *
     * @return the {@link ComboBox} associated with the Number of Players on the home page
     */

    public static ComboBox getNumPlayersCB() {
        return numPlayersCB;
    }

    /**
     * Sets the {@link ComboBox} associated with the Number of Players on the home page
     * @param numPlayersCB the {@link ComboBox} associated with the Number of Players on the home page
     */

    public static void setNumPlayersCB(ComboBox numPlayersCB) {
        GUIMain.numPlayersCB = numPlayersCB;
    }

    /**
     *
     * @return the {@link ComboBox} associated with Choice of Grid on the home page
     */

    public static ComboBox getGridChoiceCB() {
        return gridChoiceCB;
    }

    /**
     * Set the {@link ComboBox} associated with the Choice of Grid on the home page
     * @param gridChoiceCB the {@link ComboBox} associated with the Choice of Grid on the home page
     */

    public static void setGridChoiceCB(ComboBox gridChoiceCB) {
        GUIMain.gridChoiceCB = gridChoiceCB;
    }

    /**
     *
     * @return an {@link ArrayList} of {@link ColorPicker} objects of the players
     */

    public static ArrayList<ColorPicker> getArray_CP() {
        return array_CP;
    }

    /**
     * Set an {@link ArrayList} of {@link ColorPicker} objects of the players
     * @param array_CP an {@link ArrayList} of {@link ColorPicker} objects of the players
     */

    public static void setArray_CP(ArrayList<ColorPicker> array_CP) {
        GUIMain.array_CP = array_CP;
    }

    /**
     *
     * @return the {@link ColorPicker} object associated with Player 1
     */

    public static ColorPicker getPlayer_1() {
        return player_1;
    }

    /**
     * Set the {@link ColorPicker} object associated with Player 1
     * @param player_1 the {@link ColorPicker} object associated with Player 1
     */

    public static void setPlayer_1(ColorPicker player_1) {
        GUIMain.player_1 = player_1;
    }

    /**
     *
     * @return the {@link ColorPicker} object associated with Player 2
     */

    public static ColorPicker getPlayer_2() {
        return player_2;
    }

    /**
     * Set the {@link ColorPicker} object associated with Player 2
     * @param player_2 the {@link ColorPicker} object associated with Player 2
     */

    public static void setPlayer_2(ColorPicker player_2) {
        GUIMain.player_2 = player_2;
    }

    /**
     *
     * @return the {@link ColorPicker} object associated with Player 3
     */

    public static ColorPicker getPlayer_3() {
        return player_3;
    }

    /**
     * Set the {@link ColorPicker} object associated with Player 3
     * @param player_3 the {@link ColorPicker} object associated with Player 3
     */

    public static void setPlayer_3(ColorPicker player_3) {
        GUIMain.player_3 = player_3;
    }

    /**
     *
     * @return the {@link ColorPicker} object associated with Player 4
     */

    public static ColorPicker getPlayer_4() {
        return player_4;
    }

    /**
     *
     * Set the {@link ColorPicker} object associated with Player 4
     * @param player_4 the {@link ColorPicker} object associated with Player 4
     */

    public static void setPlayer_4(ColorPicker player_4) {
        GUIMain.player_4 = player_4;
    }

    /**
     *
     * @return the {@link ColorPicker} object associated with Player 5
     */

    public static ColorPicker getPlayer_5() {
        return player_5;
    }

    /**
     * Set the {@link ColorPicker} object associated with Player 5
     * @param player_5 the {@link ColorPicker} object associated with Player 5
     */

    public static void setPlayer_5(ColorPicker player_5) {
        GUIMain.player_5 = player_5;
    }

    /**
     *
     * @return the {@link ColorPicker} object associated with Player 6
     */

    public static ColorPicker getPlayer_6() {
        return player_6;
    }

    /**
     * Set the {@link ColorPicker} object associated with Player 6
     * @param player_6 the {@link ColorPicker} object associated with Player 6
     */

    public static void setPlayer_6(ColorPicker player_6) {
        GUIMain.player_6 = player_6;
    }

    /**
     *
     * @return the {@link ColorPicker} object associated with Player 7
     */

    public static ColorPicker getPlayer_7() {
        return player_7;
    }

    /**
     * Set the {@link ColorPicker} object associated with Player 7
     * @param player_7 the {@link ColorPicker} object associated with Player 7
     */

    public static void setPlayer_7(ColorPicker player_7) {
        GUIMain.player_7 = player_7;
    }

    /**
     *
     * @return the {@link ColorPicker} object associated with Player 8
     */

    public static ColorPicker getPlayer_8() {
        return player_8;
    }

    /**
     * Set the the {@link ColorPicker} object associated with Player 8
     * @param player_8 the {@link ColorPicker} object associated with Player 8
     */

    public static void setPlayer_8(ColorPicker player_8) {
        GUIMain.player_8 = player_8;
    }

    /**
     *
     * @return the {@link MediaPlayer} object associated with playing the sounds
     */

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * Set the {@link MediaPlayer} object associated with playing the sounds
     * @param mediaPlayer the {@link MediaPlayer} object associated with playing the sounds
     */

    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
        GUIMain.mediaPlayer = mediaPlayer;
    }

    /**
     *
     * @return a String that is associated with the color of the current player
     */

    public static String getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Set a String that is associated with the color of the current player
     * @param currentPlayer a String that is associated with the color of the current player
     */

    public static void setCurrentPlayer(String currentPlayer) {
        GUIMain.currentPlayer = currentPlayer;
    }

    /**
     *
     * @return a {@link HashMap} that stores the colors players as key and their color as value
     */

    public static HashMap<Integer, String> getPlayercolor() {
        return playercolor;
    }

    /**
     * Set a {@link HashMap} that stores the colors players as key and their color as value
     * @param playercolor a {@link HashMap} that stores the colors players as key and their color as value
     */

    public static void setPlayercolor(HashMap<Integer, String> playercolor) {
        GUIMain.playercolor = playercolor;
    }


    /**
     *
     * A function that creates an empty grid for the start page.
     *
     * The {@link GridPane} root is the GUI Grid where the game takes place. Each index of the {@link GridPane}
     * has a {@link StackPane} which again contains a {@link Group}. The {@link StackPane} color is changed to
     * the parameter <code>color</code>. According to the size of the {@link GridPane}, its {@link RowConstraints}
     * and {@link ColumnConstraints} are decided.The <code>redoButton</code> and the <code>MenuBar</code> is also created.
     *
     *
     * @param root a {@link GridPane} GUI Grid where the game takes place
     * @param numRows an Integer denoting the number of rows in the grid
     * @param numColumns an Integer denoting the number of column in the grid
     * @param color a {@link Color} object which is used to set the color of the {@link StackPane}
     * @return a {@link BorderPane} object that contains the {@link GridPane}, the Buttons and the MenuBar
     */
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


    /**
     * Create a new {@link Border} object with the {@link Color} <code>color</code>
     *
     * @param color the {@link Color} object which is used to set the border
     * @return a {@link Border} object with the given color
     */

    public static Border makeBorder(Color color) {
        return new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    }

    /**
     * Changes the color of the {@link GridPane} <code>grid</code> to the given {@link Color}
     *
     * @param grid the {@link GridPane} to be changed
     * @param color the {@link Color} to which the <code>grid</code> has to change
     */

    public static void changeGridColor(GridPane grid, Color color) {

        ObservableList<Node> cells = grid.getChildren();
        for (int i = 1; i < cells.size(); i++) {
            cells.get(i).setStyle("-fx-border-color: #" + color.toString().substring(2));
        }


    }

    /**
     *  Plays the pop sound effect when a turn is played
     */
    public static void playExplode() {

        String musicFile = "assets/explode.mp3";

        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    /**
     * Plays the error sound in case of an invalid move
     */
    public static void playError() {

        String musicFile = "assets/error.mp3";

        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

    }


    /**
     * This function is used to add specified number of spheres to the cell,
     * of the specified color.
     *
     * Given a row, column this function adds specified number of {@link Sphere} objects
     * to the {@link StackPane} at the specified position and animates them for rotation.
     *
     * Here, we decide the size of the spheres to be added depending upon the size of the
     * GUI grid.
     *
     * @param root {@link GridPane} that represents the GUI grid
     * @param row  row number to which we have to add
     * @param column column number to which we have to add
     * @param numSpheres number of spheres that we have to add
     * @param color {@link Color} object denoting the color the new spheres have to be
     */
    public static void addOrbAndAnimate(GridPane root, int row, int column, int numSpheres, Color color) {

        int rowsize = 0;
        double radius;

        int size = GUIMain.get_gameEngine().get_gridSize();

        rowsize = size == 0 ? 6 : 10;
        radius  = size == 0 ? 12 : 7.5;

        int removal = (row * rowsize) + column + 1;

        StackPane oldStackPane = (StackPane) root.getChildren().get(removal);

        StackPane cellContainer = new StackPane();
        cellContainer.setBorder(oldStackPane.getBorder());
        cellContainer.setOnMouseClicked(new turnGUI());
        Group cell = new Group();
        cell.setPickOnBounds(false);



        PhongMaterial smaterial = new PhongMaterial();
        smaterial.setDiffuseColor(color);



        switch (numSpheres) {

            case 1:

                Sphere a = new Sphere(radius);
                a.setMaterial(smaterial);
                cell.getChildren().addAll(a);
                break;

            case 2:

                a = new Sphere(radius);
                Sphere b = new Sphere(radius);

                b.setTranslateX(7.5);
                b.setTranslateZ(7.5);

                a.setMaterial(smaterial);
                b.setMaterial(smaterial);
                cell.getChildren().addAll(a, b);

                rotateOrbs(cell);

                break;

            case 3:

                a = new Sphere(radius);
                b = new Sphere(radius);
                Sphere c = new Sphere(radius);

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

        GridPane.setHalignment(cell, HPos.CENTER);
        GridPane.setValignment(cell, VPos.CENTER);
        StackPane.setMargin(cell, new Insets(2, 2, 2, 2));
        cellContainer.getChildren().add(cell);
        root.add(cellContainer, column, row);
        root.getChildren().remove(cellContainer);
        root.getChildren().set(removal, cellContainer);



    }

    /**
     * A utility function that adds {@link Rotate} transitions to a
     * specified {@link Group} object.
     *
     * @param cell {@link Group} object to be animated
     */
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


    /**
     * Checks for the end game condition
     *
     * It iterates over the player ArrayList and returns whether
     * the number of players having their <code>_isAlive</code>
     * attribute set to <code>true</code> is 1 or not.
     *
     * If it is 1, it means game has ended.
     *
     * @return boolean indicating if end-game condition is reached
     */
    public static boolean checkEndGame() {


        int count = 0;
        for (int i = 0; i < get_gameEngine().get_gc().get_players().size(); i++) {
            if (get_gameEngine().get_gc().get_players().get(i).get_isAlive()) {
                count++;
            }
        }


        return (count == 1);
    }

    /**
     * Creates a {@link Scene} containing the GUI of the start page
     *
     * @return a {@link Scene} object containing the GUI of the Start page
     */
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

    /**
     * Returns a {@link Scene} containing GUI of the Settings page
     *
     * @return returns a scene containing GUI of the Settings page
     */
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

    /**
     * Returns a {@link Scene} containing GUI of the End page.
     *
     * @param grid {@link GridPane} object representing the GUI grid
     * @param stage {@link Stage} object containing the application
     * @param winner number of the winner player
     */
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
        DropShadow shadow = new DropShadow();


        restartButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        restartButton.setEffect(shadow);
                    }
                });

        restartButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        restartButton.setEffect(null);
                    }
                });

        Button exitButton = new Button("Home Page");
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {



                stage.setScene(createStartPage());

                Button btn = (Button) event.getSource();
                btn.getScene().getWindow().hide();
            }
        });

        exitButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        exitButton.setEffect(shadow);
                    }
                });

        exitButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        exitButton.setEffect(null);
                    }
                });

        ImageUtil endGIFUtil = new ImageUtil();
        Image endGif = endGIFUtil.getImage("assets/firecracker.gif");
        ImageView endGIFView1 = new ImageView(endGif);
        ImageView endGIFView2 = new ImageView(endGif);


        restartButton.setStyle("-fx-background-color: #d96b58; -fx-text-alignment: center; -fx-font-family: \"Oxygen Mono\"; -fx-font-size: 20px; -fx-font-weight: bolder;");
        exitButton.setStyle("-fx-background-color: #d96b58; -fx-text-alignment: center; -fx-font-family: \"Oxygen Mono\"; -fx-font-size: 20px; -fx-font-weight: bolder;");
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
        endgame_grid.add(endGIFView1, 0 , 8, 2, 2);
        endgame_grid.add(endGIFView2, 6 , 8, 2, 2);

        endgame_grid.setStyle("-fx-background-color: black;");
        restartButton.setAlignment(Pos.BOTTOM_LEFT);

        for (int i = 0; i < 15; i++) {
            endgame_grid.getRowConstraints().add(rc);
        }

        for (int i = 0; i < 8; i++) {
            endgame_grid.getColumnConstraints().add(cc);
        }

        winner_label.setStyle("-fx-background-color: transparent; -fx-text-alignment: center; -fx-font-family: \"Press Start 2P\"; -fx-font-size: 40px; -fx-font-weight: bolder; -fx-text-fill: #f4ab15; -fx-line-spacing: 1em;");
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


    /**
     *  Creates a {@link Scene} object containing the Game page
     *
     * @return a {@link Scene} object containing the Game page
     */
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
                if (_gameEngine.get_gc().get_players().get(i).get_isActive() && _gameEngine.get_gc().get_players().get(i).get_isAlive()) {
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


    /**
     *  A utility function, that, given a serializable {@link Grid}
     *  object, populates a {@link GridPane} from it.
     *
     * @param gp {@link GridPane} object to be populated
     * @param g {@link Grid} object to be used as source
     */
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

    /**
     * This is the main Start method of the application which is launched
     * when the application is clicked on.
     *
     * It initializes the <code>_gameEngine</code> for the {@link GUIMain}
     * class with the defaault number of players and grid size. It is also
     * responsible for initializing the <code>ArrayList</code> of {@link ColorPicker}
     * objects.
     *
     * @param primaryStage Primary {@link Stage} of the application
     * @throws Exception In case of serialization errors
     */
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

    /**
     * The main method which is called at launch.
     *
     * It launched the GUI application
     *
     * @param args Arguments passed
     */
    public static void main(String[] args) {
        launch(args);
    }

}




